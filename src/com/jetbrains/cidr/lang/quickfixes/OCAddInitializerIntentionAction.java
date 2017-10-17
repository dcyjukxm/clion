// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInsight.lookup.LookupEx;
import com.intellij.codeInsight.lookup.LookupListener;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.lookup.LookupEvent;
import com.intellij.codeInsight.lookup.LookupAdapter;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCAddInitializerIntentionAction extends OCPsiElementQuickFix<OCDeclarator>
{
    private OCSymbol mySymbol;
    
    public OCAddInitializerIntentionAction(@Nullable final OCDeclarator ocDeclarator, final OCSymbol mySymbol) {
        super((PsiElement)ocDeclarator);
        this.mySymbol = mySymbol;
    }
    
    @Override
    protected String getTextInternal() {
        return "Initialize " + this.mySymbol.getNameWithKindLowercase();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Add initializers";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final OCDeclarator p0) {
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
        //    18: ldc             "declarator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    48: ifnull          109
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    55: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    60: astore_2       
        //    61: aload_2        
        //    62: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    65: ifeq            99
        //    68: aload_2        
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    72: ifne            99
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    81: athrow         
        //    82: aload_2        
        //    83: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnum:()Z
        //    89: ifeq            107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //   109: iconst_0       
        //   110: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  61     75     78     82     Lcom/intellij/util/IncorrectOperationException;
        //  68     92     95     99     Lcom/intellij/util/IncorrectOperationException;
        //  82     103    103    107    Lcom/intellij/util/IncorrectOperationException;
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
    
    @Nullable
    public PsiElement getElementToMakeWritable(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction", "getElementToMakeWritable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return this.myElementPtr.getElement();
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final OCDeclarator ocDeclarator = (OCDeclarator)this.myElementPtr.getElement();
        try {
            if (ocDeclarator != null) {
                this.invoke(psiFile, ocDeclarator, editor);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
    }
    
    protected void invoke(final PsiFile psiFile, @NotNull OCDeclarator ocDeclarator, final Editor editor) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final OCType resolvedType = this.mySymbol.getResolvedType();
        ocDeclarator = (OCDeclarator)OCChangeUtil.replaceHandlingMacros((PsiElement)ocDeclarator, (PsiElement)OCElementFactory.declarationFromText("int " + ocDeclarator.getTextWithMacros() + "=" + resolvedType.getDefaultValue((PsiElement)psiFile), (PsiElement)psiFile, true).getDeclarators().get(0));
        invokeSmartCompletion(ocDeclarator.getInitializer(), resolvedType);
    }
    
    public static void invokeSmartCompletion(@NotNull final OCExpression p0, @NotNull final OCType p1) {
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
        //    18: ldc             "expression"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invokeSmartCompletion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invokeSmartCompletion"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    94: astore_2       
        //    95: aload_1        
        //    96: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //    99: ifeq            124
        //   102: aload_2        
        //   103: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   106: ifne            124
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   115: athrow         
        //   116: iconst_1       
        //   117: goto            125
        //   120: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: iconst_0       
        //   125: istore_3       
        //   126: aload_0        
        //   127: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   132: astore          4
        //   134: aload_0        
        //   135: invokestatic    com/intellij/ide/util/EditorHelper.openInEditor:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/Editor;
        //   138: astore          5
        //   140: aload           5
        //   142: ifnonnull       150
        //   145: return         
        //   146: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   149: athrow         
        //   150: aload           5
        //   152: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   157: aload           4
        //   159: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   162: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   167: aload           5
        //   169: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //   174: aload           4
        //   176: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   179: aload           4
        //   181: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   184: invokeinterface com/intellij/openapi/editor/SelectionModel.setSelection:(II)V
        //   189: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   192: aload_2        
        //   193: aload           5
        //   195: iload_3        
        //   196: aload_0        
        //   197: invokedynamic   run:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;ZLcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Runnable;
        //   202: aload           5
        //   204: invokeinterface com/intellij/openapi/editor/Editor.getComponent:()Ljavax/swing/JComponent;
        //   209: invokestatic    com/intellij/openapi/application/ModalityState.stateForComponent:(Ljava/awt/Component;)Lcom/intellij/openapi/application/ModalityState;
        //   212: invokeinterface com/intellij/openapi/application/Application.invokeLater:(Ljava/lang/Runnable;Lcom/intellij/openapi/application/ModalityState;)V
        //   217: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  95     109    112    116    Lcom/intellij/util/IncorrectOperationException;
        //  102    120    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  140    146    146    150    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
