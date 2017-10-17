// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.List;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.ObjectUtils;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.codeInsight.intention.IntentionAction;

public abstract class OCGenerateDefinitionsIntentionActionBase implements IntentionAction
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String message;
        try {
            message = OCBundle.message("generate.definitions.intention.familyName", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return message;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0061: {
            try {
                if (OCGenerateDefinitionsIntentionActionBase.$assertionsDisabled) {
                    return a(a(editor, psiFile));
                }
                final Editor editor2 = editor;
                if (editor2 == null) {
                    break Label_0061;
                }
                return a(a(editor, psiFile));
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final Editor editor2 = editor;
                if (editor2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return a(a(editor, psiFile));
    }
    
    public void invoke(@NotNull final Project p0, final Editor p1, final PsiFile p2) throws IncorrectOperationException {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    48: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    51: aload_2        
        //    52: aload_3        
        //    53: invokestatic    com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    56: astore          4
        //    58: aload           4
        //    60: invokestatic    com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Z
        //    63: ifeq            138
        //    66: aload_3        
        //    67: invokestatic    com/jetbrains/cidr/lang/generate/OCCaretLocation.byFile:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;
        //    70: aload           4
        //    72: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getFunctionParent:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //    75: aload           4
        //    77: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    80: aload           4
        //    82: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.locateFunctionDefinition:()Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    85: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase.isInline:()Z
        //    92: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy.get:(Z)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //    95: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getGenerateDefinitionReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //    98: astore          5
        //   100: aload_1        
        //   101: ldc             "generate.definitions.commandName"
        //   103: iconst_0       
        //   104: anewarray       Ljava/lang/Object;
        //   107: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   110: aconst_null    
        //   111: aload_1        
        //   112: aload           5
        //   114: invokedynamic   run:(Lcom/intellij/openapi/project/Project;Ljava/util/List;)Ljava/lang/Runnable;
        //   119: aload           5
        //   121: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.getAffectedFiles:(Ljava/util/List;)Ljava/util/List;
        //   124: getstatic       com/intellij/psi/PsiFile.EMPTY_ARRAY:[Lcom/intellij/psi/PsiFile;
        //   127: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   132: checkcast       [Lcom/intellij/psi/PsiFile;
        //   135: invokestatic    com/intellij/openapi/command/WriteCommandAction.runWriteCommandAction:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Runnable;[Lcom/intellij/psi/PsiFile;)V
        //   138: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
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
    
    public boolean startInWriteAction() {
        return false;
    }
    
    protected abstract boolean isInline();
    
    @Nullable
    private static OCFunctionSymbol a(@NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase", "findFunction"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsIntentionActionBase", "findFunction"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)PsiTreeUtil.getParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class)OCFunctionDeclaration.class);
        try {
            if (ocFunctionDeclaration != null) {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ocFunctionDeclaration.getSymbol();
                return (OCFunctionSymbol)ObjectUtils.tryCast((Object)ocSymbolWithQualifiedName, (Class)OCFunctionSymbol.class);
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = null;
        return (OCFunctionSymbol)ObjectUtils.tryCast((Object)ocSymbolWithQualifiedName, (Class)OCFunctionSymbol.class);
    }
    
    @Contract("null -> false")
    private static boolean a(final OCFunctionSymbol ocFunctionSymbol) {
        Label_0022: {
            try {
                if (ocFunctionSymbol == null) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final boolean b = true;
                final OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION should_GENERATE_DEFINITION = OCCppDefinitionsUtil.shouldGenerateDefinitionsFor(ocFunctionSymbol2, b);
                final OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION should_GENERATE_DEFINITION2 = OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION.POSSIBLE;
                if (should_GENERATE_DEFINITION == should_GENERATE_DEFINITION2) {
                    break Label_0022;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final boolean b = true;
                final OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION should_GENERATE_DEFINITION = OCCppDefinitionsUtil.shouldGenerateDefinitionsFor(ocFunctionSymbol2, b);
                final OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION should_GENERATE_DEFINITION2 = OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION.POSSIBLE;
                if (should_GENERATE_DEFINITION == should_GENERATE_DEFINITION2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGenerateDefinitionsIntentionActionBase.class.desiredAssertionStatus()) {
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
