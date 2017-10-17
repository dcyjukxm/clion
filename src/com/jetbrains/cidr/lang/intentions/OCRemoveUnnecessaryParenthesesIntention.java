// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCRemoveUnnecessaryParenthesesIntention extends PsiElementBaseIntentionAction
{
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //    91: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //    97: astore          4
        //    99: aload           4
        //   101: ifnull          144
        //   104: aload           4
        //   106: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   111: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   114: ifeq            150
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: aload           4
        //   126: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   131: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   134: ifne            150
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   143: athrow         
        //   144: iconst_0       
        //   145: ireturn        
        //   146: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   149: athrow         
        //   150: aload_0        
        //   151: aload_0        
        //   152: invokevirtual   com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.getFamilyName:()Ljava/lang/String;
        //   155: invokevirtual   com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.setText:(Ljava/lang/String;)V
        //   158: aload           4
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   165: astore          5
        //   167: aload           5
        //   169: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   172: ifeq            181
        //   175: iconst_1       
        //   176: ireturn        
        //   177: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   180: athrow         
        //   181: aload           4
        //   183: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   188: astore          6
        //   190: aload           6
        //   192: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   195: ifne            213
        //   198: aload           6
        //   200: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   203: ifeq            219
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   212: athrow         
        //   213: iconst_1       
        //   214: ireturn        
        //   215: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   218: athrow         
        //   219: aload           5
        //   221: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   224: ifeq            332
        //   227: aload           5
        //   229: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   232: iconst_1       
        //   233: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   236: istore          7
        //   238: aload           6
        //   240: iconst_0       
        //   241: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   244: istore          8
        //   246: iload           7
        //   248: iload           8
        //   250: if_icmple       259
        //   253: iconst_1       
        //   254: ireturn        
        //   255: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: iload           7
        //   261: iload           8
        //   263: if_icmpge       272
        //   266: iconst_0       
        //   267: ireturn        
        //   268: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   271: athrow         
        //   272: aload           5
        //   274: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   277: ifeq            330
        //   280: aload           4
        //   282: aload           5
        //   284: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   287: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   292: if_acmpne       330
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   301: athrow         
        //   302: aload           5
        //   304: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   307: aload           6
        //   309: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.isParenthesesNeededInRightArgument:(Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   312: ifeq            328
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   321: athrow         
        //   322: iconst_0       
        //   323: ireturn        
        //   324: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   327: athrow         
        //   328: iconst_1       
        //   329: ireturn        
        //   330: iconst_1       
        //   331: ireturn        
        //   332: iconst_1       
        //   333: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  99     117    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  104    137    140    144    Lcom/intellij/util/IncorrectOperationException;
        //  124    146    146    150    Lcom/intellij/util/IncorrectOperationException;
        //  167    177    177    181    Lcom/intellij/util/IncorrectOperationException;
        //  190    206    209    213    Lcom/intellij/util/IncorrectOperationException;
        //  198    215    215    219    Lcom/intellij/util/IncorrectOperationException;
        //  246    255    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  259    268    268    272    Lcom/intellij/util/IncorrectOperationException;
        //  272    295    298    302    Lcom/intellij/util/IncorrectOperationException;
        //  280    315    318    322    Lcom/intellij/util/IncorrectOperationException;
        //  302    324    324    328    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0124:
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
    
    private static boolean a(final OCExpression ocExpression) {
        Label_0025: {
            try {
                if (!(ocExpression instanceof OCAssignmentExpression)) {
                    return false;
                }
                final OCExpression ocExpression2 = ocExpression;
                final OCType ocType = OCExpectedTypeUtil.getExpectedType(ocExpression2);
                final OCExpression ocExpression3 = ocExpression;
                final boolean b = OCIntType.isBool(ocType, (PsiElement)ocExpression3);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final OCType ocType = OCExpectedTypeUtil.getExpectedType(ocExpression2);
                final OCExpression ocExpression3 = ocExpression;
                final boolean b = OCIntType.isBool(ocType, (PsiElement)ocExpression3);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Remove Unnecessary Parentheses";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public void invoke(@NotNull final Project p0, final Editor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //    91: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    97: astore          4
        //    99: aload           4
        //   101: ifnonnull       109
        //   104: return         
        //   105: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: aload           4
        //   111: astore          5
        //   113: aload           4
        //   115: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   118: astore          6
        //   120: aload           5
        //   122: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   125: ifeq            147
        //   128: aload           5
        //   130: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   133: astore          6
        //   135: aload           5
        //   137: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   142: astore          5
        //   144: goto            120
        //   147: aload           4
        //   149: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   152: ifeq            170
        //   155: aload           4
        //   157: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCParenthesizedExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   165: astore          4
        //   167: goto            147
        //   170: aload           4
        //   172: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   175: ifeq            210
        //   178: aload           4
        //   180: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   185: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   188: ifeq            210
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: aload           4
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   205: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   208: astore          4
        //   210: aload           5
        //   212: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   215: ifeq            389
        //   218: aload           5
        //   220: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   223: iconst_1       
        //   224: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   227: istore          7
        //   229: aload           4
        //   231: iconst_0       
        //   232: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   235: istore          8
        //   237: iload           7
        //   239: iload           8
        //   241: if_icmpgt       259
        //   244: aload           4
        //   246: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   249: ifeq            274
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: aload           6
        //   261: aload           4
        //   263: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   266: pop            
        //   267: goto            386
        //   270: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   273: athrow         
        //   274: iload           7
        //   276: iload           8
        //   278: if_icmpge       299
        //   281: aload           6
        //   283: aload           4
        //   285: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.appendParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   288: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   291: pop            
        //   292: goto            386
        //   295: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   298: athrow         
        //   299: aload           5
        //   301: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   304: ifeq            378
        //   307: aload           6
        //   309: aload           5
        //   311: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   314: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   319: if_acmpne       378
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   328: athrow         
        //   329: aload           5
        //   331: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   334: aload           4
        //   336: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.isParenthesesNeededInRightArgument:(Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   339: ifeq            367
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   348: athrow         
        //   349: aload           6
        //   351: aload           4
        //   353: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.appendParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   356: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   359: pop            
        //   360: goto            386
        //   363: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveUnnecessaryParenthesesIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   366: athrow         
        //   367: aload           6
        //   369: aload           4
        //   371: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   374: pop            
        //   375: goto            386
        //   378: aload           6
        //   380: aload           4
        //   382: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   385: pop            
        //   386: goto            397
        //   389: aload           6
        //   391: aload           4
        //   393: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   396: pop            
        //   397: aload_1        
        //   398: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   401: aload           5
        //   403: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformat:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   406: pop            
        //   407: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  99     105    105    109    Lcom/intellij/util/IncorrectOperationException;
        //  170    191    194    198    Lcom/intellij/util/IncorrectOperationException;
        //  237    252    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  244    270    270    274    Lcom/intellij/util/IncorrectOperationException;
        //  274    295    295    299    Lcom/intellij/util/IncorrectOperationException;
        //  299    322    325    329    Lcom/intellij/util/IncorrectOperationException;
        //  307    342    345    349    Lcom/intellij/util/IncorrectOperationException;
        //  329    363    363    367    Lcom/intellij/util/IncorrectOperationException;
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
