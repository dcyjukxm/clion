// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareMembersHandler;

public class OCMoveToPrivateCategoryIntentionAction extends OCDeclareMembersHandler implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Move to private category";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_3        
        //    48: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.locateCandidate:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    51: astore          4
        //    53: aload           4
        //    55: ifnull          131
        //    58: aload           4
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    65: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    68: ifeq            131
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: aload           4
        //    80: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    83: ifeq            131
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: ldc             ""
        //    95: aload           4
        //    97: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   105: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   110: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   113: ifne            131
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   122: athrow         
        //   123: iconst_1       
        //   124: goto            132
        //   127: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   130: athrow         
        //   131: iconst_0       
        //   132: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  53     71     74     78     Ljava/lang/IllegalStateException;
        //  58     86     89     93     Ljava/lang/IllegalStateException;
        //  78     116    119    123    Ljava/lang/IllegalStateException;
        //  93     127    127    131    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    protected OCDeclareActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final OCDeclareActionContext evaluateActionContext = super.evaluateActionContext(ocClassSymbol, psiElement);
        OCDeclareActionContext ocDeclareActionContext;
        try {
            evaluateActionContext.setTarget(OCDeclareActionContext.Target.PRIVATE_CATEGORY);
            ocDeclareActionContext = evaluateActionContext;
            if (ocDeclareActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclareActionContext;
    }
    
    protected boolean enableChooseDialog(final Collection<OCMemberSymbol> collection) {
        return false;
    }
    
    @Nullable
    protected OCMemberSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "locateCandidate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getParentOfType(element, (Class)OCMethod.class, false, new Class[] { OCBlockStatement.class });
        try {
            if (ocMethod != null) {
                return ((OCSymbolDeclarator<OCMemberSymbol>)ocMethod).getSymbol();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCDeclarator ocDeclarator = OCElementUtil.getAdjacentParentOfType(element, (Class<? extends OCDeclarator>)OCDeclarator.class);
        try {
            if (ocDeclarator == null) {
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCPropertySymbol symbol = ocDeclarator.getSymbol();
        try {
            if (symbol instanceof OCPropertySymbol) {
                return symbol;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    protected OCClassSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction", "getParent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCMemberSymbol locateCandidate = this.locateCandidate(project, editor, psiFile);
        try {
            if (locateCandidate != null) {
                return ((OCSymbolWithParent<T, OCClassSymbol>)locateCandidate).getParent();
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
