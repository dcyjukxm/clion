// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCSplitDeclarationAndAssignmentIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Split into declaration and assignment";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    48: ifne            57
        //    51: iconst_0       
        //    52: ireturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    56: athrow         
        //    57: aload_2        
        //    58: aload_3        
        //    59: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    62: astore          4
        //    64: aload           4
        //    66: ifnull          83
        //    69: aload           4
        //    71: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    76: goto            84
        //    79: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    82: athrow         
        //    83: aconst_null    
        //    84: astore          5
        //    86: aload           5
        //    88: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    91: ifeq            199
        //    94: aload           4
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   101: ifnull          199
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: aload           4
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   121: ifne            199
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   130: athrow         
        //   131: aload           4
        //   133: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   138: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   143: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaIntroducer;
        //   146: ifne            199
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   155: athrow         
        //   156: aload           4
        //   158: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;.class
        //   160: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   163: ifnull          199
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: aload           5
        //   175: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   178: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConst:()Z
        //   181: ifne            199
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   190: athrow         
        //   191: iconst_1       
        //   192: goto            200
        //   195: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   198: athrow         
        //   199: iconst_0       
        //   200: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     53     53     57     Lcom/intellij/util/IncorrectOperationException;
        //  64     79     79     83     Lcom/intellij/util/IncorrectOperationException;
        //  86     104    107    111    Lcom/intellij/util/IncorrectOperationException;
        //  94     124    127    131    Lcom/intellij/util/IncorrectOperationException;
        //  111    149    152    156    Lcom/intellij/util/IncorrectOperationException;
        //  131    166    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  156    184    187    191    Lcom/intellij/util/IncorrectOperationException;
        //  173    195    195    199    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    48: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    51: aload_2        
        //    52: aload_3        
        //    53: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    56: astore          4
        //    58: aload           4
        //    60: ifnonnull       68
        //    63: return         
        //    64: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: aload           4
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //    75: astore          5
        //    77: aload           4
        //    79: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;.class
        //    81: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    84: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    87: astore          6
        //    89: aload           6
        //    91: ifnull          394
        //    94: aload           4
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   101: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   104: ldc             ";"
        //   106: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   109: ifeq            153
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   118: athrow         
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: aload           5
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: ldc             "=0;"
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   139: aload           4
        //   141: iconst_1       
        //   142: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   148: astore          7
        //   150: goto            197
        //   153: new             Ljava/lang/StringBuilder;
        //   156: dup            
        //   157: invokespecial   java/lang/StringBuilder.<init>:()V
        //   160: ldc             "for("
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: aload           5
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: ldc             "=0;;);"
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   178: aload           4
        //   180: iconst_1       
        //   181: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   184: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   187: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   192: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   195: astore          7
        //   197: aload           4
        //   199: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   204: astore          8
        //   206: aload           8
        //   208: ifnull          225
        //   211: aload           8
        //   213: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   218: goto            226
        //   221: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   224: athrow         
        //   225: aconst_null    
        //   226: astore          9
        //   228: aload           7
        //   230: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   235: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   243: aload           8
        //   245: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   248: pop            
        //   249: aload           8
        //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   254: aload           4
        //   256: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   261: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   264: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   269: astore          10
        //   271: aload           4
        //   273: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   278: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   281: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   284: ifeq            336
        //   287: aload           10
        //   289: ifnull          336
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   298: athrow         
        //   299: aload           9
        //   301: ifnull          336
        //   304: goto            311
        //   307: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   310: athrow         
        //   311: aload           10
        //   313: aload           9
        //   315: aload           4
        //   317: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   320: aload           4
        //   322: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.typeElementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   325: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   328: pop            
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   335: athrow         
        //   336: new             Ljava/lang/StringBuilder;
        //   339: dup            
        //   340: invokespecial   java/lang/StringBuilder.<init>:()V
        //   343: aload           4
        //   345: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   350: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: ldc             ";"
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   364: aload           4
        //   366: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   369: astore          11
        //   371: aload           6
        //   373: aload           11
        //   375: aload           4
        //   377: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   380: pop            
        //   381: aload           4
        //   383: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   385: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   388: aload           7
        //   390: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   393: pop            
        //   394: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  58     64     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  89     112    115    119    Lcom/intellij/util/IncorrectOperationException;
        //  206    221    221    225    Lcom/intellij/util/IncorrectOperationException;
        //  271    292    295    299    Lcom/intellij/util/IncorrectOperationException;
        //  287    304    307    311    Lcom/intellij/util/IncorrectOperationException;
        //  299    329    332    336    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0299:
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
    
    public boolean startInWriteAction() {
        return true;
    }
    
    @Nullable
    private static OCDeclarator a(final Editor p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //     7: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    12: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    17: astore_2       
        //    18: aload_2        
        //    19: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //    21: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAdjacentParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    24: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    27: astore_3       
        //    28: aload_3        
        //    29: ifnonnull       128
        //    32: aload_2        
        //    33: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //    35: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAdjacentParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    38: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    41: astore          4
        //    43: aload           4
        //    45: ifnull          128
        //    48: aload           4
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    55: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    58: ifeq            128
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: aload           4
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    75: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    78: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //    83: invokeinterface java/util/List.size:()I
        //    88: iconst_1       
        //    89: if_icmpne       128
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    98: athrow         
        //    99: aload           4
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   106: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   109: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   114: iconst_0       
        //   115: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   120: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   123: areturn        
        //   124: invokestatic    com/jetbrains/cidr/lang/intentions/OCSplitDeclarationAndAssignmentIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   127: athrow         
        //   128: aload_3        
        //   129: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  43     61     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  48     92     95     99     Lcom/intellij/util/IncorrectOperationException;
        //  68     124    124    128    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
