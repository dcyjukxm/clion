// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.Nls;
import com.intellij.util.codeInsight.CommentUtilCore;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCAttributesList;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Iterator;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCReplaceTypeWithAutoIntentionAction extends PsiElementBaseIntentionAction
{
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getParentOfType(psiElement, (Class)OCDeclaration.class);
        try {
            if (ocDeclaration == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        b(ocDeclaration);
        final Iterator<OCDeclarator> iterator = ocDeclaration.getDeclarators().iterator();
        while (iterator.hasNext()) {
            a(iterator.next());
        }
        b(ocDeclaration.getNode());
        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
        CodeStyleManager.getInstance(project).adjustLineIndent(containingFile, ocDeclaration.getTextRange());
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, @NotNull final PsiElement p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    94: astore          4
        //    96: aload           4
        //    98: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsCxxAutoType:(Lcom/intellij/psi/PsiFile;)Z
        //   101: ifeq            118
        //   104: aload_3        
        //   105: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/psi/PsiElement;)Z
        //   108: ifne            124
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   117: athrow         
        //   118: iconst_0       
        //   119: ireturn        
        //   120: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: aload_3        
        //   125: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //   127: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   130: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   133: astore          5
        //   135: aload           5
        //   137: ifnull          192
        //   140: aload           5
        //   142: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   145: ifne            192
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   154: athrow         
        //   155: aload           5
        //   157: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   162: invokeinterface java/util/List.isEmpty:()Z
        //   167: ifne            192
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   176: athrow         
        //   177: aload           5
        //   179: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.b:(Lcom/intellij/psi/PsiElement;)Z
        //   182: ifne            198
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   191: athrow         
        //   192: iconst_0       
        //   193: ireturn        
        //   194: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: aload           5
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   205: iconst_0       
        //   206: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   211: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   214: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   219: astore          6
        //   221: aload           6
        //   223: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   226: ifeq            235
        //   229: iconst_0       
        //   230: ireturn        
        //   231: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   234: athrow         
        //   235: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   238: dup            
        //   239: aload           5
        //   241: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   244: astore          7
        //   246: aload           6
        //   248: aload           7
        //   250: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   253: astore          8
        //   255: aload           8
        //   257: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   260: ifeq            291
        //   263: aload           8
        //   265: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   268: astore          9
        //   270: aload           9
        //   272: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   275: ifeq            284
        //   278: iconst_0       
        //   279: ireturn        
        //   280: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   283: athrow         
        //   284: aload           9
        //   286: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   289: astore          8
        //   291: aload           8
        //   293: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   296: ifeq            305
        //   299: iconst_0       
        //   300: ireturn        
        //   301: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   304: athrow         
        //   305: aload           5
        //   307: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;)Z
        //   310: ifeq            332
        //   313: aload           5
        //   315: aload           8
        //   317: aload           7
        //   319: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.b:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   322: ifne            351
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   331: athrow         
        //   332: aload           5
        //   334: aload           8
        //   336: aload           7
        //   338: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   341: ifeq            363
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   350: athrow         
        //   351: aload_0        
        //   352: ldc             "Replace type with 'auto'"
        //   354: invokevirtual   com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.setText:(Ljava/lang/String;)V
        //   357: iconst_1       
        //   358: ireturn        
        //   359: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   362: athrow         
        //   363: iconst_0       
        //   364: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  96     111    114    118    Lcom/intellij/util/IncorrectOperationException;
        //  104    120    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  135    148    151    155    Lcom/intellij/util/IncorrectOperationException;
        //  140    170    173    177    Lcom/intellij/util/IncorrectOperationException;
        //  155    185    188    192    Lcom/intellij/util/IncorrectOperationException;
        //  177    194    194    198    Lcom/intellij/util/IncorrectOperationException;
        //  221    231    231    235    Lcom/intellij/util/IncorrectOperationException;
        //  270    280    280    284    Lcom/intellij/util/IncorrectOperationException;
        //  291    301    301    305    Lcom/intellij/util/IncorrectOperationException;
        //  305    325    328    332    Lcom/intellij/util/IncorrectOperationException;
        //  313    344    347    351    Lcom/intellij/util/IncorrectOperationException;
        //  332    359    359    363    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0155:
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
    
    private static boolean a(@NotNull final OCDeclaration ocDeclaration, @NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaration", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAuto"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commonType", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAuto"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAuto"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        for (final OCDeclarator ocDeclarator : ocDeclaration.getDeclarators()) {
            final OCType resolvedCppReferencedType = OCTypeUtils.getResolvedCppReferencedType(ocDeclarator.getType(), ocResolveContext);
            try {
                if (!ocType.equals(resolvedCppReferencedType, false, ocResolveContext)) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            final OCExpression initializer = ocDeclarator.getInitializer();
            try {
                if (initializer == null) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            final OCType resolvedCppReferencedType2 = OCTypeUtils.getResolvedCppReferencedType(initializer.getType(), ocResolveContext);
            try {
                if (!a(resolvedCppReferencedType, resolvedCppReferencedType2, ocResolveContext)) {
                    return false;
                }
                continue;
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        return true;
    }
    
    private static boolean a(@NotNull final OCType p0, @NotNull final OCType p1, @NotNull final OCResolveContext p2) {
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
        //    18: ldc             "declaratorType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "areSameTypes"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "initializerType"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "areSameTypes"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "resolveContext"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "areSameTypes"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_0        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   136: ifeq            211
        //   139: aload_0        
        //   140: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   143: ifeq            173
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: aload_1        
        //   154: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //   157: ifeq            173
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   166: athrow         
        //   167: iconst_1       
        //   168: ireturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: aload_0        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   177: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   180: astore_3       
        //   181: aload_3        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   185: ifeq            211
        //   188: aload_3        
        //   189: aload_1        
        //   190: iconst_0       
        //   191: aload_2        
        //   192: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   195: ifeq            211
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   204: athrow         
        //   205: iconst_1       
        //   206: ireturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   210: athrow         
        //   211: aload_0        
        //   212: aload_1        
        //   213: iconst_0       
        //   214: aload_2        
        //   215: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   218: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    146    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  139    160    163    167    Lcom/intellij/util/IncorrectOperationException;
        //  153    169    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  181    198    201    205    Lcom/intellij/util/IncorrectOperationException;
        //  188    207    207    211    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0153:
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
    
    private static boolean a(@NotNull final OCDeclaration ocDeclaration) {
        try {
            if (ocDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaration", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "insideForeach"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCForeachStatement ocForeachStatement = (OCForeachStatement)PsiTreeUtil.getParentOfType((PsiElement)ocDeclaration, (Class)OCForeachStatement.class);
        Label_0079: {
            try {
                if (ocForeachStatement == null) {
                    return false;
                }
                final OCForeachStatement ocForeachStatement2 = ocForeachStatement;
                final OCElement ocElement = ocForeachStatement2.getVariableDeclaration();
                final OCDeclaration ocDeclaration2 = ocDeclaration;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocElement, (PsiElement)ocDeclaration2, b);
                if (b2) {
                    break Label_0079;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCForeachStatement ocForeachStatement2 = ocForeachStatement;
                final OCElement ocElement = ocForeachStatement2.getVariableDeclaration();
                final OCDeclaration ocDeclaration2 = ocDeclaration;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocElement, (PsiElement)ocDeclaration2, b);
                if (b2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean b(@NotNull final OCDeclaration ocDeclaration, @NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaration", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAutoInForeach"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarationType", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAutoInForeach"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canUseAutoInForeach"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCForeachStatement ocForeachStatement = (OCForeachStatement)PsiTreeUtil.getParentOfType((PsiElement)ocDeclaration, (Class)OCForeachStatement.class);
        try {
            if (ocForeachStatement == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCExpression collectionExpression = ocForeachStatement.getCollectionExpression();
        try {
            if (collectionExpression == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final OCType collectionElementType = OCCodeInsightUtil.getCollectionElementType(collectionExpression, collectionExpression.getType().resolve(ocResolveContext));
        try {
            if (collectionElementType == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        return a(ocType, OCTypeUtils.getCppReferencedType(collectionElementType), ocResolveContext);
    }
    
    private static void b(@NotNull final OCDeclaration p0) {
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
        //    18: ldc             "declaration"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "modifyTypeElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    50: astore_1       
        //    51: getstatic       com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.$assertionsDisabled:Z
        //    54: ifne            80
        //    57: aload_1        
        //    58: ifnonnull       80
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: new             Ljava/lang/AssertionError;
        //    71: dup            
        //    72: invokespecial   java/lang/AssertionError.<init>:()V
        //    75: athrow         
        //    76: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    79: athrow         
        //    80: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    83: dup            
        //    84: aload_0        
        //    85: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    88: astore_2       
        //    89: aload_0        
        //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //    95: iconst_0       
        //    96: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   101: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   104: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   109: aload_2        
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getCppReferencedType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   116: astore_3       
        //   117: iconst_0       
        //   118: istore          4
        //   120: aload_1        
        //   121: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   126: astore          5
        //   128: aload           5
        //   130: aconst_null    
        //   131: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //   136: astore          6
        //   138: aload           6
        //   140: arraylength    
        //   141: istore          7
        //   143: iconst_0       
        //   144: istore          8
        //   146: iload           8
        //   148: iload           7
        //   150: if_icmpge       279
        //   153: aload           6
        //   155: iload           8
        //   157: aaload         
        //   158: astore          9
        //   160: aload           9
        //   162: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/lang/ASTNode;)Z
        //   165: ifeq            175
        //   168: goto            273
        //   171: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   174: athrow         
        //   175: aload           9
        //   177: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   182: astore          10
        //   184: aload           10
        //   186: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   189: if_acmpne       206
        //   192: aload_3        
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   196: ifne            273
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   205: athrow         
        //   206: aload           10
        //   208: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   211: if_acmpne       242
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   220: athrow         
        //   221: aload_3        
        //   222: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   225: ifeq            242
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   234: athrow         
        //   235: goto            273
        //   238: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   241: athrow         
        //   242: iload           4
        //   244: ifne            264
        //   247: iconst_1       
        //   248: istore          4
        //   250: aload           5
        //   252: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTO_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   255: ldc             "auto"
        //   257: aload           9
        //   259: invokeinterface com/intellij/lang/ASTNode.addLeaf:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Lcom/intellij/lang/ASTNode;)V
        //   264: aload           5
        //   266: aload           9
        //   268: invokeinterface com/intellij/lang/ASTNode.removeChild:(Lcom/intellij/lang/ASTNode;)V
        //   273: iinc            8, 1
        //   276: goto            146
        //   279: getstatic       com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.$assertionsDisabled:Z
        //   282: ifne            309
        //   285: iload           4
        //   287: ifne            309
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   296: athrow         
        //   297: new             Ljava/lang/AssertionError;
        //   300: dup            
        //   301: invokespecial   java/lang/AssertionError.<init>:()V
        //   304: athrow         
        //   305: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   308: athrow         
        //   309: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  51     61     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  57     76     76     80     Lcom/intellij/util/IncorrectOperationException;
        //  160    171    171    175    Lcom/intellij/util/IncorrectOperationException;
        //  184    199    202    206    Lcom/intellij/util/IncorrectOperationException;
        //  192    214    217    221    Lcom/intellij/util/IncorrectOperationException;
        //  206    228    231    235    Lcom/intellij/util/IncorrectOperationException;
        //  221    238    238    242    Lcom/intellij/util/IncorrectOperationException;
        //  279    290    293    297    Lcom/intellij/util/IncorrectOperationException;
        //  285    305    305    309    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0206:
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
    
    private static void a(@NotNull final OCDeclarator p0) {
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
        //    18: ldc             "declarator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "modifyDeclarator"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNode:()Lcom/intellij/lang/ASTNode;
        //    50: astore_1       
        //    51: aload_1        
        //    52: aconst_null    
        //    53: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //    58: astore_2       
        //    59: aload_2        
        //    60: astore_3       
        //    61: aload_3        
        //    62: arraylength    
        //    63: istore          4
        //    65: iconst_0       
        //    66: istore          5
        //    68: iload           5
        //    70: iload           4
        //    72: if_icmpge       164
        //    75: aload_3        
        //    76: iload           5
        //    78: aaload         
        //    79: astore          6
        //    81: aload           6
        //    83: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    88: astore          7
        //    90: aload           7
        //    92: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    95: if_acmpne       105
        //    98: goto            164
        //   101: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   104: athrow         
        //   105: aload           7
        //   107: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   110: if_acmpeq       158
        //   113: aload           7
        //   115: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   118: if_acmpeq       158
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   127: athrow         
        //   128: aload           6
        //   130: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/lang/ASTNode;)Z
        //   133: ifeq            150
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   142: athrow         
        //   143: goto            158
        //   146: invokestatic    com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   149: athrow         
        //   150: aload_1        
        //   151: aload           6
        //   153: invokeinterface com/intellij/lang/ASTNode.removeChild:(Lcom/intellij/lang/ASTNode;)V
        //   158: iinc            5, 1
        //   161: goto            68
        //   164: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  90     101    101    105    Lcom/intellij/util/IncorrectOperationException;
        //  105    121    124    128    Lcom/intellij/util/IncorrectOperationException;
        //  113    136    139    143    Lcom/intellij/util/IncorrectOperationException;
        //  128    146    146    150    Lcom/intellij/util/IncorrectOperationException;
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
    
    private static void b(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "removeExtraWhitespaces"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        for (final ASTNode astNode2 : astNode.getChildren((TokenSet)null)) {
            final IElementType elementType = astNode2.getElementType();
            try {
                if (elementType == OCTokenTypes.EQ) {
                    break;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            Label_0211: {
                if (OCTokenTypes.WHITESPACES.contains(elementType)) {
                    final PsiElement prevSiblingOrParentSibling = OCElementUtil.getPrevSiblingOrParentSibling(astNode2.getPsi());
                    try {
                        if (prevSiblingOrParentSibling == null) {
                            break Label_0211;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    ASTNode astNode3 = prevSiblingOrParentSibling.getNode();
                    if (!(astNode3 instanceof LeafElement)) {
                        astNode3 = astNode3.getLastChildNode();
                    }
                    try {
                        if (OCTokenTypes.WHITESPACES.contains(astNode3.getElementType())) {
                            astNode.removeChild(astNode2);
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                else {
                    try {
                        if (!(astNode2 instanceof LeafElement)) {
                            b(astNode2);
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
            }
        }
    }
    
    private static boolean a(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "shouldPreserveNode"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0081: {
            try {
                if (astNode.getPsi() instanceof OCAttributesList) {
                    return true;
                }
                final ASTNode astNode2 = astNode;
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = psiElement instanceof OCMacroCall;
                if (b) {
                    return true;
                }
                break Label_0081;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = astNode;
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = psiElement instanceof OCMacroCall;
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        final IElementType elementType = astNode.getElementType();
        try {
            if (OCTokenTypes.WHITESPACES.contains(elementType)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        try {
            if (CommentUtilCore.isComment(astNode)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        try {
            if (OCTokenTypes.TYPE_SPECIFIERS.contains(elementType)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        try {
            if (OCTokenTypes.DECLARATION_SPECIFIERS_IN_TYPES.contains(elementType)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return false;
    }
    
    private static boolean a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "isAvailableForElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getParentOfType(psiElement, (Class)OCDeclaration.class);
        try {
            if (ocDeclaration == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final Iterator<OCDeclarator> iterator = ocDeclaration.getDeclarators().iterator();
        while (iterator.hasNext()) {
            final OCExpression initializer = iterator.next().getInitializer();
            try {
                if (initializer == null) {
                    continue;
                }
                final OCExpression ocExpression = initializer;
                final PsiElement psiElement2 = psiElement;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocExpression, psiElement2, b);
                if (b2) {
                    return false;
                }
                continue;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final OCExpression ocExpression = initializer;
                final PsiElement psiElement2 = psiElement;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocExpression, psiElement2, b);
                if (b2) {
                    return false;
                }
                continue;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return true;
    }
    
    private static boolean b(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "canTransformCodeFragment"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        for (final ASTNode astNode : psiElement.getNode().getChildren((TokenSet)null)) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (elementType == OCTokenTypes.EQ) {
                    break;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            Label_0156: {
                try {
                    if (a(astNode)) {
                        break Label_0156;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                final PsiElement psi = astNode.getPsi();
                try {
                    if (!b(psi)) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
        }
        return true;
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Replace type with 'auto'";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCReplaceTypeWithAutoIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCReplaceTypeWithAutoIntentionAction.class.desiredAssertionStatus()) {
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
