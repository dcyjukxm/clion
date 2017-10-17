// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCConvertLiteralIntentionAction extends OCPsiElementQuickFix<OCExpression> implements HighPriorityAction
{
    private OCType myLeftType;
    private OCType myRightType;
    
    public OCConvertLiteralIntentionAction(final OCExpression ocExpression, final OCType myLeftType, final OCType myRightType) {
        super((PsiElement)OCParenthesesUtils.diveIntoParentheses(ocExpression));
        this.myLeftType = myLeftType;
        this.myRightType = myRightType;
    }
    
    protected String getTextInternal() {
        return "Convert literal type: add '@'";
    }
    
    @NotNull
    public String getFamilyName() {
        String textInternal;
        try {
            textInternal = this.getTextInternal();
            if (textInternal == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return textInternal;
    }
    
    @Override
    public boolean isAvailable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //     4: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    12: astore_1       
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myLeftType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    17: ifnull          67
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myRightType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    24: ifnull          67
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    33: athrow         
        //    34: aload_1        
        //    35: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    38: ifeq            67
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    47: athrow         
        //    48: aload_1        
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    54: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/intellij/psi/PsiElement;)Z
        //    57: ifne            73
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    66: athrow         
        //    67: iconst_0       
        //    68: ireturn        
        //    69: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    72: athrow         
        //    73: aload_1        
        //    74: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //    77: astore_2       
        //    78: aload_2        
        //    79: ifnull          102
        //    82: aload_2        
        //    83: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //    86: ifne            102
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: iconst_0       
        //    97: ireturn        
        //    98: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   101: athrow         
        //   102: aload_0        
        //   103: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myLeftType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   106: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isObjCRootType:()Z
        //   109: istore_3       
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myLeftType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   114: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToStringCompatible:()Z
        //   117: ifne            131
        //   120: iload_3        
        //   121: ifeq            148
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   130: athrow         
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myRightType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   135: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToChar:()Z
        //   138: ifne            217
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   147: athrow         
        //   148: aload_0        
        //   149: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myLeftType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   152: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   155: ldc             "NSNumber *"
        //   157: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   160: ifne            181
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   169: athrow         
        //   170: iload_3        
        //   171: ifeq            225
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   180: athrow         
        //   181: aload_0        
        //   182: getfield        com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.myRightType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   185: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   188: ifeq            225
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   197: athrow         
        //   198: aload_1        
        //   199: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   204: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsObjectLiterals:(Lcom/intellij/psi/PsiFile;)Z
        //   207: ifeq            225
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   216: athrow         
        //   217: iconst_1       
        //   218: goto            226
        //   221: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   224: athrow         
        //   225: iconst_0       
        //   226: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  13     27     30     34     Ljava/lang/IllegalStateException;
        //  20     41     44     48     Ljava/lang/IllegalStateException;
        //  34     60     63     67     Ljava/lang/IllegalStateException;
        //  48     69     69     73     Ljava/lang/IllegalStateException;
        //  78     89     92     96     Ljava/lang/IllegalStateException;
        //  82     98     98     102    Ljava/lang/IllegalStateException;
        //  110    124    127    131    Ljava/lang/IllegalStateException;
        //  120    141    144    148    Ljava/lang/IllegalStateException;
        //  131    163    166    170    Ljava/lang/IllegalStateException;
        //  148    174    177    181    Ljava/lang/IllegalStateException;
        //  170    191    194    198    Ljava/lang/IllegalStateException;
        //  181    210    213    217    Ljava/lang/IllegalStateException;
        //  198    221    221    225    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    protected void invoke(final PsiFile psiFile, @NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCConvertLiteralIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCChangeUtil.changeText(psiFile.getProject(), psiFile, ocExpression.getRangeWithMacros().getStartOffset(), 0, "@", false);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
