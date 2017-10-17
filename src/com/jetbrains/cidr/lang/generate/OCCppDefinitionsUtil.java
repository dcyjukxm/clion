// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import java.util.Iterator;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.util.ObjectUtils;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public class OCCppDefinitionsUtil
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static SHOULD_GENERATE_DEFINITION shouldGenerateDefinitionsFor(@NotNull final OCFunctionSymbol p0, final boolean p1) {
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
        //    18: ldc             "function"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldGenerateDefinitionsFor"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingPsiFile:()Lcom/intellij/psi/PsiFile;
        //    48: astore_2       
        //    49: aload_2        
        //    50: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    53: ifne            64
        //    56: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.NO:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //    59: areturn        
        //    60: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPureVirtual:()Z
        //    68: ifeq            79
        //    71: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.NO:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //    74: areturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: invokedynamic   process:()Lcom/intellij/util/Processor;
        //    85: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //    88: ifne            99
        //    91: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.NO:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //    94: areturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   103: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   106: ifne            117
        //   109: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.POSSIBLE:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //   112: areturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: iload_1        
        //   118: ifeq            269
        //   121: aload_0        
        //   122: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   125: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   128: if_acmpne       269
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   142: ifne            185
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_0        
        //   153: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //   156: ifeq            269
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_0        
        //   167: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   170: ldc             "operator="
        //   172: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   175: ifeq            269
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload_0        
        //   186: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   189: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   192: astore_3       
        //   193: aload_3        
        //   194: invokeinterface java/util/List.size:()I
        //   199: iconst_1       
        //   200: if_icmpne       269
        //   203: aload_3        
        //   204: iconst_0       
        //   205: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   210: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   213: astore          4
        //   215: aload           4
        //   217: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   220: ifeq            269
        //   223: aload_0        
        //   224: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   227: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   230: aload_2        
        //   231: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   239: aload           4
        //   241: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   244: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   247: aload_2        
        //   248: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   251: ifeq            269
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.POSSIBLE:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //   264: areturn        
        //   265: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.REQUIRED:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //   272: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     60     60     64     Ljava/lang/IllegalArgumentException;
        //  64     75     75     79     Ljava/lang/IllegalArgumentException;
        //  79     95     95     99     Ljava/lang/IllegalArgumentException;
        //  99     113    113    117    Ljava/lang/IllegalArgumentException;
        //  117    131    134    138    Ljava/lang/IllegalArgumentException;
        //  121    145    148    152    Ljava/lang/IllegalArgumentException;
        //  138    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    178    181    185    Ljava/lang/IllegalArgumentException;
        //  215    254    257    261    Ljava/lang/IllegalArgumentException;
        //  223    265    265    269    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
    public static OCMembersContainer getFunctionParent(@NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getFunctionParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCNamespaceLikeSymbol ocNamespaceLikeSymbol = null;
        Label_0081: {
            try {
                if (ocFunctionSymbol.getParent() instanceof OCNamespaceLikeSymbol) {
                    final OCNamespaceLikeSymbol membersContainer;
                    ocNamespaceLikeSymbol = (membersContainer = (OCNamespaceLikeSymbol)ocFunctionSymbol.getParent());
                    break Label_0081;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            OCNamespaceLikeSymbol membersContainer;
            ocNamespaceLikeSymbol = (membersContainer = ocFunctionSymbol.getContainingOCFile().getMembersContainer(0 != 0));
            try {
                if (membersContainer == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getFunctionParent"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return ocNamespaceLikeSymbol;
    }
    
    public static boolean shouldInlineNewDefinitions(@NotNull final OCMembersContainer p0, @NotNull final OCCaretLocation p1) {
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
        //    18: ldc             "parent"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldInlineNewDefinitions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "location"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "shouldInlineNewDefinitions"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getElement:()Lcom/intellij/psi/PsiElement;
        //    92: ifnull          169
        //    95: aload_0        
        //    96: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    99: ifne            152
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_0        
        //   110: invokeinterface com/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   115: aload_1        
        //   116: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getFile:()Lcom/intellij/psi/PsiFile;
        //   119: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   122: ifeq            152
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.b:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Z
        //   136: ifeq            152
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: iconst_0       
        //   147: ireturn        
        //   148: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_1        
        //   153: aload_0        
        //   154: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getParentDefinition:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Lcom/intellij/psi/PsiElement;
        //   157: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/intellij/psi/PsiElement;)Z
        //   160: ifeq            169
        //   163: iconst_0       
        //   164: ireturn        
        //   165: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$BasedOnExistingResult;
        //   173: getfield        com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$BasedOnExistingResult.shouldInline:Z
        //   176: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     125    128    132    Ljava/lang/IllegalArgumentException;
        //  109    139    142    146    Ljava/lang/IllegalArgumentException;
        //  132    148    148    152    Ljava/lang/IllegalArgumentException;
        //  152    165    165    169    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    public static OCGenerateUtil.ReplacePosition getOutsidePreferredPosition(@NotNull final PsiFile psiFile, @NotNull final OCMembersContainer ocMembersContainer, @NotNull final List<OCFunctionSymbol> list) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsidePreferredPosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsidePreferredPosition"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functions", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsidePreferredPosition"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0155: {
            try {
                if (list.isEmpty()) {
                    break Label_0155;
                }
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCFile;
                if (!b) {
                    break Label_0155;
                }
                break Label_0155;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCFile;
                if (!b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final BasedOnExistingResult a = a(ocMembersContainer);
        try {
            if (a.outsideLocation != null) {
                return a((int)a.outsideLocation.second, (PsiFile)a.outsideLocation.first);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        Label_0367: {
            try {
                if (!b(ocMembersContainer) || list.stream().anyMatch(OCFunctionSymbol::isTemplateSymbol)) {
                    break Label_0367;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            final OCFile associatedFile = ((OCFile)psiFile).getAssociatedFile();
            try {
                if (associatedFile == null || associatedFile.isHeader()) {
                    break Label_0367;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            final PsiElement a2 = a(associatedFile, (OCNamespaceSymbol)ObjectUtils.tryCast((Object)ocMembersContainer, (Class)OCNamespaceSymbol.class));
            final ASTNode childByType = a2.getNode().findChildByType((IElementType)OCTokenTypes.RBRACE);
            PsiElement psi = null;
            Label_0323: {
                try {
                    if (childByType != null) {
                        psi = childByType.getPsi();
                        break Label_0323;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                psi = null;
            }
            final PsiElement psiElement = psi;
            try {
                if (psiElement != null) {
                    final int n = psiElement.getTextRange().getStartOffset();
                    return a(n, (PsiFile)associatedFile);
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            final int n = a2.getTextRange().getEndOffset();
            return a(n, (PsiFile)associatedFile);
        }
        final PsiElement a3 = a(psiFile, getParentDefinition(ocMembersContainer), list);
        return a(a3.getTextRange().getEndOffset(), a3.getContainingFile());
    }
    
    @NotNull
    public static List<OCGenerateUtil.Replacement> getGenerateDefinitionReplacements(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer, @NotNull final List<OCFunctionSymbol> list, @NotNull final List<? extends OCCallable> list2, @NotNull final InlinePolicy inlinePolicy) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functions", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "predefinitions", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (inlinePolicy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inlinePolicy", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        if (inlinePolicy.shouldInline(ocCaretLocation, ocMembersContainer)) {
            final ArrayList<OCGenerateUtil.Replacement> list3 = new ArrayList<OCGenerateUtil.Replacement>(list.size());
            Label_0274: {
                try {
                    if (OCCppDefinitionsUtil.$assertionsDisabled) {
                        break Label_0274;
                    }
                    final List<OCFunctionSymbol> list4 = list;
                    final int n = list4.size();
                    final List<? extends OCCallable> list5 = list2;
                    final int n2 = list5.size();
                    if (n != n2) {
                        break Label_0274;
                    }
                    break Label_0274;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final List<OCFunctionSymbol> list4 = list;
                    final int n = list4.size();
                    final List<? extends OCCallable> list5 = list2;
                    final int n2 = list5.size();
                    if (n != n2) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            for (int i = 0; i < list.size(); ++i) {
                final OCFunctionSymbol ocFunctionSymbol = list.get(i);
                final OCCallable ocCallable = (OCCallable)list2.get(i);
                if (ocCallable != null) {
                    final PsiElement lastChild = ocCallable.getLastChild();
                    PsiElement prevSignificantSibling = null;
                    Label_0362: {
                        try {
                            if (!OCElementUtil.isElementSignificant(lastChild)) {
                                prevSignificantSibling = OCElementUtil.getPrevSignificantSibling(lastChild);
                                break Label_0362;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        prevSignificantSibling = lastChild;
                    }
                    final PsiElement psiElement = prevSignificantSibling;
                    Label_0387: {
                        try {
                            if (psiElement == null) {
                                continue;
                            }
                            final PsiElement psiElement2 = psiElement;
                            final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                            final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.SEMICOLON;
                            if (elementType == ocPunctuatorElementType) {
                                break Label_0387;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                        try {
                            final PsiElement psiElement2 = psiElement;
                            final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                            final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.SEMICOLON;
                            if (elementType == ocPunctuatorElementType) {
                                list3.add(new OCGenerateUtil.Replacement(new OCGenerateUtil.ReplacePosition((PsiFile)ocCallable.getContainingOCFile(), psiElement.getTextRange(), (PsiElement)ocCallable, false), OCCallableUtil.defaultFunctionBody(ocFunctionSymbol, OCCallableUtil.getDefaultBaseToCall(ocFunctionSymbol), (PsiElement)ocCallable)));
                            }
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                }
            }
            ArrayList<OCGenerateUtil.Replacement> list6;
            try {
                list6 = list3;
                if (list6 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
                }
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
            return list6;
        }
        List<OCGenerateUtil.Replacement> a;
        try {
            a = a(ocCaretLocation, ocMembersContainer, list, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getGenerateDefinitionReplacements"));
            }
        }
        catch (IllegalArgumentException ex12) {
            throw a(ex12);
        }
        return a;
    }
    
    @NotNull
    public static List<OCGenerateUtil.Replacement> getNewFunctionsReplacements(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCStructLike ocStructLike, @NotNull final OCMembersContainer ocMembersContainer, @NotNull final List<OCFunctionSymbol> list, @Nullable final List<String> list2, @NotNull final InlinePolicy inlinePolicy) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocStructLike == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structDefinition", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (inlinePolicy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inlinePolicy", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        Label_0279: {
            List<OCGenerateUtil.Replacement> list3 = null;
            Label_0244: {
                try {
                    if (!list.isEmpty()) {
                        break Label_0279;
                    }
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        break Label_0244;
                    }
                    return list3;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return list3;
        }
        final StringBuilder sb = new StringBuilder();
        final boolean shouldInline = inlinePolicy.shouldInline(ocCaretLocation, ocMembersContainer);
        final PsiElement correctContextToCalculateNames = OCCallableUtil.getCorrectContextToCalculateNames((PsiElement)ocStructLike);
        final int a = a(ocStructLike, ocCaretLocation, list.get(0));
        final OCVisibility visibilityAtOffset = OCVisibility.getVisibilityAtOffset((PsiElement)ocStructLike, a);
        Label_0346: {
            try {
                if (OCCppDefinitionsUtil.$assertionsDisabled) {
                    break Label_0346;
                }
                final OCVisibility ocVisibility = visibilityAtOffset;
                if (ocVisibility == null) {
                    break Label_0346;
                }
                break Label_0346;
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                final OCVisibility ocVisibility = visibilityAtOffset;
                if (ocVisibility == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        OCVisibility ocVisibility2 = visibilityAtOffset;
        for (int i = 0; i < list.size(); ++i) {
            final OCFunctionSymbol ocFunctionSymbol = list.get(i);
            final OCVisibility visibility = ocFunctionSymbol.getVisibility();
            if (visibility != ocVisibility2) {
                sb.append(visibility).append(": ");
                ocVisibility2 = visibility;
            }
            String s = null;
            Label_0477: {
                Label_0473: {
                    String defaultFunctionBody = null;
                    Label_0468: {
                        Label_0437: {
                            try {
                                if (!shouldInline) {
                                    break Label_0473;
                                }
                                final List<String> list4 = list2;
                                if (list4 == null) {
                                    break Label_0437;
                                }
                                break Label_0437;
                            }
                            catch (IllegalArgumentException ex10) {
                                throw a(ex10);
                            }
                            try {
                                final List<String> list4 = list2;
                                if (list4 == null) {
                                    defaultFunctionBody = OCCallableUtil.defaultFunctionBody(ocFunctionSymbol, OCCallableUtil.getDefaultBaseToCall(ocFunctionSymbol), correctContextToCalculateNames);
                                    break Label_0468;
                                }
                            }
                            catch (IllegalArgumentException ex11) {
                                throw a(ex11);
                            }
                        }
                        defaultFunctionBody = list2.get(i);
                    }
                    s = defaultFunctionBody;
                    break Label_0477;
                }
                s = ";";
            }
            sb.append(OCCallableUtil.functionSignature(ocFunctionSymbol, "", OCCallableUtil.getCorrectContextToCalculateNames((PsiElement)ocStructLike))).append(s);
        }
        try {
            if (a(ocStructLike, a, visibilityAtOffset, ocVisibility2)) {
                sb.append(visibilityAtOffset).append(": ");
            }
        }
        catch (IllegalArgumentException ex12) {
            throw a(ex12);
        }
        final PsiFile containingFile = ocStructLike.getContainingFile();
        final Document document = PsiDocumentManager.getInstance(ocCaretLocation.getProject()).getDocument(containingFile);
        Label_0640: {
            List<OCGenerateUtil.Replacement> list5 = null;
            Label_0605: {
                try {
                    if (document != null) {
                        break Label_0640;
                    }
                    final Logger logger = OCLog.LOG;
                    final StringBuilder sb2 = new StringBuilder();
                    final String s2 = "No document for ";
                    final StringBuilder sb3 = sb2.append(s2);
                    final PsiFile psiFile = containingFile;
                    final StringBuilder sb4 = sb3.append(psiFile);
                    final String s3 = sb4.toString();
                    logger.warn(s3);
                    list5 = Collections.emptyList();
                    if (list5 == null) {
                        break Label_0605;
                    }
                    return list5;
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                try {
                    final Logger logger = OCLog.LOG;
                    final StringBuilder sb2 = new StringBuilder();
                    final String s2 = "No document for ";
                    final StringBuilder sb3 = sb2.append(s2);
                    final PsiFile psiFile = containingFile;
                    final StringBuilder sb4 = sb3.append(psiFile);
                    final String s3 = sb4.toString();
                    logger.warn(s3);
                    list5 = Collections.emptyList();
                    if (list5 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
                    }
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
            return list5;
        }
        final List<OCGenerateUtil.Replacement> singletonList = Collections.singletonList(new OCGenerateUtil.Replacement(new OCGenerateUtil.ReplacePosition(containingFile, TextRange.from(a, 0), correctContextToCalculateNames), sb.toString()));
        Object o = null;
        Label_0697: {
            try {
                if (shouldInline) {
                    o = Collections.emptyList();
                    break Label_0697;
                }
            }
            catch (IllegalArgumentException ex15) {
                throw a(ex15);
            }
            o = a(ocCaretLocation, ocMembersContainer, list, list2);
        }
        final Object o2 = o;
        List concat;
        try {
            concat = ContainerUtil.concat((List)singletonList, (List)o2);
            if (concat == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getNewFunctionsReplacements"));
            }
        }
        catch (IllegalArgumentException ex16) {
            throw a(ex16);
        }
        return (List<OCGenerateUtil.Replacement>)concat;
    }
    
    @Nullable
    public static PsiElement getParentDefinition(@NotNull final OCMembersContainer ocMembersContainer) {
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getParentDefinition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMembersContainer instanceof OCSymbol) {
                final Object o = ((OCSymbol<Object>)ocMembersContainer).locateDefinition();
                return (PsiElement)o;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Object o = ocMembersContainer.getContainingOCFile();
        return (PsiElement)o;
    }
    
    @NotNull
    private static List<OCGenerateUtil.Replacement> a(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer, @NotNull final List<OCFunctionSymbol> list, @Nullable final List<String> list2) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsideReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsideReplacements"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functions", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsideReplacements"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCGenerateUtil.ReplacePosition replacePosition = null;
        Label_0160: {
            try {
                if (a(ocCaretLocation, ocMembersContainer)) {
                    replacePosition = a(ocCaretLocation);
                    break Label_0160;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            replacePosition = getOutsidePreferredPosition(ocCaretLocation.getFile(), ocMembersContainer, list);
        }
        final OCGenerateUtil.ReplacePosition replacePosition2 = replacePosition;
        Label_0249: {
            List<OCGenerateUtil.Replacement> list3 = null;
            Label_0214: {
                try {
                    if (replacePosition2 != null) {
                        break Label_0249;
                    }
                    final Logger logger = OCLog.LOG;
                    final StringBuilder sb = new StringBuilder();
                    final String s = "Can't find outside position for file '";
                    final StringBuilder sb2 = sb.append(s);
                    final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                    final PsiFile psiFile = ocCaretLocation2.getFile();
                    final StringBuilder sb3 = sb2.append(psiFile);
                    final String s2 = "'";
                    final StringBuilder sb4 = sb3.append(s2);
                    final String s3 = sb4.toString();
                    logger.warn(s3);
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        break Label_0214;
                    }
                    return list3;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final Logger logger = OCLog.LOG;
                    final StringBuilder sb = new StringBuilder();
                    final String s = "Can't find outside position for file '";
                    final StringBuilder sb2 = sb.append(s);
                    final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                    final PsiFile psiFile = ocCaretLocation2.getFile();
                    final StringBuilder sb3 = sb2.append(psiFile);
                    final String s2 = "'";
                    final StringBuilder sb4 = sb3.append(s2);
                    final String s3 = sb4.toString();
                    logger.warn(s3);
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsideReplacements"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return list3;
        }
        final StringBuilder sb5 = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            final OCFunctionSymbol ocFunctionSymbol = list.get(i);
            StringBuilder append = null;
            String defaultFunctionBody = null;
            Label_0350: {
                try {
                    append = sb5.append(OCCallableUtil.functionSignature(OCCallableUtil.removeDeclarationSpecifiers(ocFunctionSymbol), OCCallableUtil.getFunctionParentQualifier(ocFunctionSymbol, replacePosition2.context), replacePosition2.context));
                    if (list2 != null) {
                        defaultFunctionBody = list2.get(i);
                        break Label_0350;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                defaultFunctionBody = OCCallableUtil.defaultFunctionBody(ocFunctionSymbol, OCCallableUtil.getDefaultBaseToCall(ocFunctionSymbol), replacePosition2.context);
            }
            append.append(defaultFunctionBody);
        }
        List<OCGenerateUtil.Replacement> singletonList;
        try {
            singletonList = Collections.singletonList(new OCGenerateUtil.Replacement(replacePosition2, sb5.toString()));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getOutsideReplacements"));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return singletonList;
    }
    
    @NotNull
    private static BasedOnExistingResult a(@NotNull final OCMembersContainer ocMembersContainer) {
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getBestLocationBasedOnExistingDefinitions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCFile containingOCFile = null;
        Label_0065: {
            try {
                if (ocMembersContainer instanceof OCSymbol) {
                    containingOCFile = null;
                    break Label_0065;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            containingOCFile = ocMembersContainer.getContainingOCFile();
        }
        final OCCppDefinitionsUtil.1OffsetProcessor 1OffsetProcessor = new OCCppDefinitionsUtil.1OffsetProcessor(containingOCFile);
        BasedOnExistingResult basedOnExistingResult;
        try {
            ocMembersContainer.processMembers(null, (Processor)1OffsetProcessor);
            basedOnExistingResult = new BasedOnExistingResult(1OffsetProcessor.shouldBeInlined(ocMembersContainer), (Pair)1OffsetProcessor.location());
            if (basedOnExistingResult == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getBestLocationBasedOnExistingDefinitions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return basedOnExistingResult;
    }
    
    private static boolean b(@NotNull final OCMembersContainer ocMembersContainer) {
        try {
            if (ocMembersContainer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "canDefinitionsBePlacedToAssocFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCFile containingOCFile = ocMembersContainer.getContainingOCFile();
        Label_0077: {
            try {
                if (containingOCFile == null) {
                    return false;
                }
                final OCFile ocFile = containingOCFile;
                final boolean b = ocFile.isHeader();
                if (!b) {
                    return false;
                }
                break Label_0077;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCFile ocFile = containingOCFile;
                final boolean b = ocFile.isHeader();
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCFile associatedFile = containingOCFile.getAssociatedFile();
        Label_0136: {
            Label_0110: {
                try {
                    if (associatedFile == null) {
                        return false;
                    }
                    final OCFile ocFile2 = associatedFile;
                    final boolean b2 = ocFile2.isHeader();
                    if (b2) {
                        return false;
                    }
                    break Label_0110;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCFile ocFile2 = associatedFile;
                    final boolean b2 = ocFile2.isHeader();
                    if (b2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    if (!(ocMembersContainer instanceof OCTemplateSymbol)) {
                        break Label_0136;
                    }
                    final OCTemplateSymbol ocTemplateSymbol = (OCTemplateSymbol)ocMembersContainer;
                    final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                    final boolean b3 = ocTemplateSymbol2.isTemplateSymbol();
                    if (!b3) {
                        break Label_0136;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final OCTemplateSymbol ocTemplateSymbol = (OCTemplateSymbol)ocMembersContainer;
                final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                final boolean b3 = ocTemplateSymbol2.isTemplateSymbol();
                if (!b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return false;
    }
    
    @Nullable
    private static OCGenerateUtil.ReplacePosition a(@NotNull final OCCaretLocation ocCaretLocation) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getCorrectOutsideInsertPositionNearby"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCaretLocation.getOffsetInFile() == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(ocCaretLocation.getOffsetInFile(), ocCaretLocation.getFile());
    }
    
    @Nullable
    private static OCGenerateUtil.ReplacePosition a(final int p0, @NotNull final PsiFile p1) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCorrectOutsideInsertPositionNearby"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    50: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    53: aload_1        
        //    54: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //    57: astore_2       
        //    58: aload_2        
        //    59: ifnonnull       93
        //    62: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    65: new             Ljava/lang/StringBuilder;
        //    68: dup            
        //    69: invokespecial   java/lang/StringBuilder.<init>:()V
        //    72: ldc             "No document for "
        //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    77: aload_1        
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    81: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    84: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/String;)V
        //    87: aconst_null    
        //    88: areturn        
        //    89: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_1        
        //    94: iload_0        
        //    95: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   100: aload_1        
        //   101: invokestatic    com/intellij/util/ObjectUtils.notNull:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   104: checkcast       Lcom/intellij/psi/PsiElement;
        //   107: astore_3       
        //   108: aload_3        
        //   109: astore          4
        //   111: aconst_null    
        //   112: astore          5
        //   114: aload           4
        //   116: ifnull          415
        //   119: aload           4
        //   121: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   124: ifne            149
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload           4
        //   136: instanceof      Lcom/intellij/psi/PsiFile;
        //   139: ifeq            399
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: iconst_m1      
        //   150: istore          6
        //   152: aconst_null    
        //   153: astore          7
        //   155: aload           4
        //   157: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   160: ifeq            273
        //   163: aload           5
        //   165: ifnull          273
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: iconst_0       
        //   176: istore          8
        //   178: aload           4
        //   180: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   185: astore          9
        //   187: aload           9
        //   189: aload           4
        //   191: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   196: if_acmpeq       273
        //   199: aload           9
        //   201: aload           5
        //   203: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   206: ifeq            219
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: iconst_1       
        //   217: istore          8
        //   219: aload           9
        //   221: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   224: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   227: if_acmpne       261
        //   230: iload           8
        //   232: ifeq            273
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: aload           9
        //   244: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   249: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   252: istore          6
        //   254: aload           4
        //   256: astore          7
        //   258: goto            273
        //   261: aload           9
        //   263: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   268: astore          9
        //   270: goto            187
        //   273: aload           7
        //   275: ifnonnull       379
        //   278: aload           5
        //   280: instanceof      Lcom/intellij/psi/PsiComment;
        //   283: ifeq            322
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: iload_0        
        //   294: aload           5
        //   296: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   301: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   304: if_icmple       322
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: iconst_1       
        //   315: goto            323
        //   318: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: iconst_0       
        //   323: istore          8
        //   325: aload           5
        //   327: aload_3        
        //   328: if_acmpeq       343
        //   331: aload           5
        //   333: ifnonnull       363
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: iload           8
        //   345: ifne            363
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: iload_0        
        //   356: goto            373
        //   359: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: aload           5
        //   365: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   370: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   373: istore          6
        //   375: aload           4
        //   377: astore          7
        //   379: new             Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition;
        //   382: dup            
        //   383: aload_1        
        //   384: iload           6
        //   386: iconst_0       
        //   387: invokestatic    com/intellij/openapi/util/TextRange.from:(II)Lcom/intellij/openapi/util/TextRange;
        //   390: aload           7
        //   392: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.getCorrectContextToCalculateNames:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   395: invokespecial   com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.<init>:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/util/TextRange;Lcom/intellij/psi/PsiElement;)V
        //   398: areturn        
        //   399: aload           4
        //   401: astore          5
        //   403: aload           4
        //   405: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   410: astore          4
        //   412: goto            114
        //   415: aconst_null    
        //   416: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  58     89     89     93     Ljava/lang/IllegalArgumentException;
        //  114    127    130    134    Ljava/lang/IllegalArgumentException;
        //  119    142    145    149    Ljava/lang/IllegalArgumentException;
        //  155    168    171    175    Ljava/lang/IllegalArgumentException;
        //  187    209    212    216    Ljava/lang/IllegalArgumentException;
        //  219    235    238    242    Ljava/lang/IllegalArgumentException;
        //  273    286    289    293    Ljava/lang/IllegalArgumentException;
        //  278    307    310    314    Ljava/lang/IllegalArgumentException;
        //  293    318    318    322    Ljava/lang/IllegalArgumentException;
        //  325    336    339    343    Ljava/lang/IllegalArgumentException;
        //  331    348    351    355    Ljava/lang/IllegalArgumentException;
        //  343    359    359    363    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0293:
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
    private static PsiElement a(@NotNull final PsiFile p0, @Nullable final PsiElement p1, @NotNull final List<OCFunctionSymbol> p2) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "defaultFallbackLocation"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "functions"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "defaultFallbackLocation"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_m1      
        //    89: istore_3       
        //    90: aconst_null    
        //    91: astore          4
        //    93: aload_2        
        //    94: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    99: astore          5
        //   101: aload           5
        //   103: invokeinterface java/util/Iterator.hasNext:()Z
        //   108: ifeq            184
        //   111: aload           5
        //   113: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   118: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   121: astore          6
        //   123: aload           6
        //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.locateFunctionDefinition:()Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   128: astore          7
        //   130: aload           7
        //   132: ifnull          181
        //   135: aload           7
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   142: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   145: istore          8
        //   147: aload_0        
        //   148: aload           7
        //   150: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   155: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   158: ifeq            181
        //   161: iload           8
        //   163: iload_3        
        //   164: if_icmple       181
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: iload           8
        //   176: istore_3       
        //   177: aload           7
        //   179: astore          4
        //   181: goto            101
        //   184: aload           4
        //   186: ifnull          237
        //   189: aload           4
        //   191: dup            
        //   192: ifnonnull       236
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: new             Ljava/lang/IllegalStateException;
        //   205: dup            
        //   206: ldc             "@NotNull method %s.%s must not return null"
        //   208: ldc             2
        //   210: anewarray       Ljava/lang/Object;
        //   213: dup            
        //   214: ldc             0
        //   216: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //   218: aastore        
        //   219: dup            
        //   220: ldc             1
        //   222: ldc             "defaultFallbackLocation"
        //   224: aastore        
        //   225: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   228: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   231: athrow         
        //   232: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: areturn        
        //   237: aload_1        
        //   238: ifnull          308
        //   241: aload_0        
        //   242: aload_1        
        //   243: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   248: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   251: ifeq            308
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: aload_1        
        //   262: dup            
        //   263: ifnonnull       307
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: new             Ljava/lang/IllegalStateException;
        //   276: dup            
        //   277: ldc             "@NotNull method %s.%s must not return null"
        //   279: ldc             2
        //   281: anewarray       Ljava/lang/Object;
        //   284: dup            
        //   285: ldc             0
        //   287: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //   289: aastore        
        //   290: dup            
        //   291: ldc             1
        //   293: ldc             "defaultFallbackLocation"
        //   295: aastore        
        //   296: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   299: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   302: athrow         
        //   303: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: areturn        
        //   308: aload_0        
        //   309: dup            
        //   310: ifnonnull       347
        //   313: new             Ljava/lang/IllegalStateException;
        //   316: dup            
        //   317: ldc             "@NotNull method %s.%s must not return null"
        //   319: ldc             2
        //   321: anewarray       Ljava/lang/Object;
        //   324: dup            
        //   325: ldc             0
        //   327: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //   329: aastore        
        //   330: dup            
        //   331: ldc             1
        //   333: ldc             "defaultFallbackLocation"
        //   335: aastore        
        //   336: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   339: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   342: athrow         
        //   343: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;)Lcom/intellij/psi/PsiElement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  147    167    170    174    Ljava/lang/IllegalArgumentException;
        //  184    195    198    202    Ljava/lang/IllegalArgumentException;
        //  189    232    232    236    Ljava/lang/IllegalArgumentException;
        //  237    254    257    261    Ljava/lang/IllegalArgumentException;
        //  241    266    269    273    Ljava/lang/IllegalArgumentException;
        //  261    303    303    307    Ljava/lang/IllegalArgumentException;
        //  308    343    343    347    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0261:
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
    
    private static int a(@NotNull final OCStructLike ocStructLike, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocStructLike == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structDefinition", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getInsertPositionForFunctionsInsideClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getInsertPositionForFunctionsInsideClass"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newFunction", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "getInsertPositionForFunctionsInsideClass"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int locateNewFunction = OCNewCppFunctionsLocator.locateNewFunction(ocStructLike, ocFunctionSymbol);
        Label_0174: {
            try {
                if (ocCaretLocation.getOffsetInFile() == null) {
                    return locateNewFunction;
                }
                final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                final PsiFile psiFile = ocCaretLocation2.getFile();
                final OCStructLike ocStructLike2 = ocStructLike;
                final PsiFile psiFile2 = ocStructLike2.getContainingFile();
                final boolean b = psiFile.equals(psiFile2);
                if (!b) {
                    return locateNewFunction;
                }
                break Label_0174;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                final PsiFile psiFile = ocCaretLocation2.getFile();
                final OCStructLike ocStructLike2 = ocStructLike;
                final PsiFile psiFile2 = ocStructLike2.getContainingFile();
                final boolean b = psiFile.equals(psiFile2);
                if (!b) {
                    return locateNewFunction;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final int intValue = ocCaretLocation.getOffsetInFile();
        try {
            if (!OCNewCppFunctionsLocator.getValidLocationRange(ocStructLike).contains(intValue)) {
                return locateNewFunction;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (a(ocCaretLocation, (PsiElement)ocStructLike)) {
                return locateNewFunction;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        PsiElement psiElement = null;
        boolean b2 = false;
        PsiElement psiElement2 = ocStructLike.getFirstChild();
        while (true) {
            Label_0301: {
                Label_0308: {
                    Label_0274: {
                        Label_0260: {
                            try {
                                if (psiElement2 == null) {
                                    break;
                                }
                                if (!OCElementUtil.isVisibilityKeyword(psiElement2.getNode())) {
                                    break Label_0260;
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw a(ex8);
                            }
                            b2 = true;
                            break Label_0274;
                        }
                        if (OCElementUtil.getElementType(psiElement2) == OCTokenTypes.COLON) {
                            b2 = false;
                        }
                        try {
                            if (b2 || psiElement2.getTextRange().getEndOffset() < intValue) {
                                break Label_0308;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    break Label_0301;
                }
                psiElement2 = psiElement2.getNextSibling();
                continue;
            }
            psiElement = psiElement2;
            break;
        }
        Label_0357: {
            try {
                if (!(psiElement instanceof PsiWhiteSpace)) {
                    break Label_0357;
                }
                final PsiElement psiElement3 = psiElement;
                final TextRange textRange = psiElement3.getTextRange();
                final int n = intValue;
                final boolean b3 = textRange.contains(n);
                if (b3) {
                    return intValue;
                }
                break Label_0357;
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            try {
                final PsiElement psiElement3 = psiElement;
                final TextRange textRange = psiElement3.getTextRange();
                final int n = intValue;
                final boolean b3 = textRange.contains(n);
                if (b3) {
                    return intValue;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
            try {
                if (psiElement != null) {
                    return psiElement.getTextRange().getEndOffset();
                }
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
        }
        return locateNewFunction;
    }
    
    private static boolean a(@NotNull final OCStructLike p0, final int p1, @NotNull final OCVisibility p2, @Nullable final OCVisibility p3) {
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
        //    18: ldc             "definition"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldRestoreVisibility"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "initial"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "shouldRestoreVisibility"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: aload_3        
        //    90: if_acmpne       99
        //    93: iconst_0       
        //    94: ireturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   105: astore          4
        //   107: aload           4
        //   109: ifnull          180
        //   112: aload           4
        //   114: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   119: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   122: iload_1        
        //   123: if_icmplt       168
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           4
        //   135: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   138: ifeq            154
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_1       
        //   149: ireturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           4
        //   156: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getVisibilityFromElement:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   159: ifnull          168
        //   162: iconst_0       
        //   163: ireturn        
        //   164: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload           4
        //   170: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   175: astore          4
        //   177: goto            107
        //   180: iconst_0       
        //   181: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     95     95     99     Ljava/lang/IllegalArgumentException;
        //  107    126    129    133    Ljava/lang/IllegalArgumentException;
        //  112    141    144    148    Ljava/lang/IllegalArgumentException;
        //  133    150    150    154    Ljava/lang/IllegalArgumentException;
        //  154    164    164    168    Ljava/lang/IllegalArgumentException;
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
    
    private static boolean a(@NotNull final OCCaretLocation ocCaretLocation, @Nullable final PsiElement psiElement) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "isLocationOutsideParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0076: {
            Label_0064: {
                try {
                    if (OCCppDefinitionsUtil.$assertionsDisabled) {
                        break Label_0076;
                    }
                    final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                    final PsiElement psiElement2 = ocCaretLocation2.getElement();
                    if (psiElement2 == null) {
                        break Label_0064;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCCaretLocation ocCaretLocation2 = ocCaretLocation;
                    final PsiElement psiElement2 = ocCaretLocation2.getElement();
                    if (psiElement2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (!PsiTreeUtil.isAncestor(psiElement, ocCaretLocation.getElement(), false)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCCaretLocation p0, @NotNull final OCMembersContainer p1) {
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
        //    18: ldc             "location"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldGenerateOutsideDefinitionAtCaret"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "parent"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "shouldGenerateOutsideDefinitionAtCaret"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getElement:()Lcom/intellij/psi/PsiElement;
        //    92: ifnonnull       101
        //    95: iconst_0       
        //    96: ireturn        
        //    97: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload_1        
        //   102: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   105: ifne            155
        //   108: aload_1        
        //   109: invokeinterface com/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   114: aload_0        
        //   115: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getFile:()Lcom/intellij/psi/PsiFile;
        //   118: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   121: ifeq            145
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_1        
        //   132: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.b:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Z
        //   135: ifne            153
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: iconst_1       
        //   146: goto            154
        //   149: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: iconst_0       
        //   154: ireturn        
        //   155: aload_0        
        //   156: aload_1        
        //   157: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getParentDefinition:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;)Lcom/intellij/psi/PsiElement;
        //   160: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.a:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/intellij/psi/PsiElement;)Z
        //   163: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     97     97     101    Ljava/lang/IllegalArgumentException;
        //  101    124    127    131    Ljava/lang/IllegalArgumentException;
        //  108    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    149    149    153    Ljava/lang/IllegalArgumentException;
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
    
    @NotNull
    private static PsiElement a(final OCFile ocFile, OCNamespaceSymbol ocNamespaceSymbol) {
        final ArrayList<OCNamespaceSymbol> list = new ArrayList<OCNamespaceSymbol>();
        while (true) {
            Label_0026: {
                try {
                    if (ocNamespaceSymbol == null) {
                        break;
                    }
                    final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                    final boolean b = ocNamespaceSymbol2 instanceof OCStructSymbol;
                    if (!b) {
                        break Label_0026;
                    }
                    break Label_0026;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                    final boolean b = ocNamespaceSymbol2 instanceof OCStructSymbol;
                    if (!b) {
                        list.add(0, ocNamespaceSymbol);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            ocNamespaceSymbol = (OCNamespaceSymbol)ocNamespaceSymbol.getParent();
        }
        OCNamespaceLikeSymbol membersContainer = ocFile.getMembersContainer(false);
        for (final OCNamespaceSymbol ocNamespaceSymbol3 : list) {
            final CommonProcessors.FindFirstProcessor<OCNamespaceSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCNamespaceSymbol>() {
                final /* synthetic */ VirtualFile val$virtualFile = ocFile.getContainingFile().getVirtualFile();
                
                protected boolean accept(final OCNamespaceSymbol ocNamespaceSymbol) {
                    return this.val$virtualFile != null && this.val$virtualFile.equals(ocNamespaceSymbol.getContainingFile());
                }
            };
            try {
                membersContainer.processMembers(ocNamespaceSymbol3.getName(), (com.intellij.util.Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)findFirstProcessor, OCNamespaceSymbol.class));
                if (!findFirstProcessor.isFound()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            membersContainer = (OCNamespaceLikeSymbol)findFirstProcessor.getFoundValue();
        }
        Label_0233: {
            if (membersContainer instanceof OCNamespaceSymbol) {
                final PsiElement locateDefinition = ((OCNamespaceSymbol)membersContainer).locateDefinition();
                PsiElement psiElement = null;
                Label_0198: {
                    try {
                        if (locateDefinition == null) {
                            break Label_0233;
                        }
                        psiElement = locateDefinition;
                        if (psiElement == null) {
                            break Label_0198;
                        }
                        return psiElement;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        psiElement = locateDefinition;
                        if (psiElement == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "findInsertionParentInFile"));
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                return psiElement;
            }
            try {
                if (ocFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil", "findInsertionParentInFile"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return (PsiElement)ocFile;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCCppDefinitionsUtil.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum SHOULD_GENERATE_DEFINITION
    {
        NO, 
        POSSIBLE, 
        REQUIRED;
    }
    
    public enum InlinePolicy
    {
        INLINE {
            @Override
            public boolean shouldInline(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer) {
                try {
                    if (ocCaretLocation == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$1", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (ocMembersContainer == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$1", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return true;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }, 
        OUTSIDE {
            @Override
            public boolean shouldInline(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer) {
                try {
                    if (ocCaretLocation == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$2", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (ocMembersContainer == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$2", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return false;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }, 
        PREFERRED {
            @Override
            public boolean shouldInline(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCMembersContainer ocMembersContainer) {
                try {
                    if (ocCaretLocation == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$3", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (ocMembersContainer == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy$3", "shouldInline"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return OCCppDefinitionsUtil.shouldInlineNewDefinitions(ocMembersContainer, ocCaretLocation);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        
        public abstract boolean shouldInline(@NotNull final OCCaretLocation p0, @NotNull final OCMembersContainer p1);
        
        public static InlinePolicy get(final boolean b) {
            return b ? InlinePolicy.INLINE : InlinePolicy.OUTSIDE;
        }
    }
    
    private static class BasedOnExistingResult
    {
        public final boolean shouldInline;
        @Nullable
        public final Pair<PsiFile, Integer> outsideLocation;
        
        private BasedOnExistingResult(final boolean shouldInline, @Nullable final Pair<PsiFile, Integer> outsideLocation) {
            this.shouldInline = shouldInline;
            this.outsideLocation = outsideLocation;
            assert !this.shouldInline;
        }
    }
}
