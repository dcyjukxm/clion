// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCSwitchPropertyDotMethodIntentionAction extends PsiElementBaseIntentionAction
{
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Switch '.' and method notation";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: iconst_2       
        //    90: anewarray       Ljava/lang/Class;
        //    93: dup            
        //    94: iconst_0       
        //    95: ldc             Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;.class
        //    97: aastore        
        //    98: dup            
        //    99: iconst_1       
        //   100: ldc             Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;.class
        //   102: aastore        
        //   103: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   106: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   109: astore          4
        //   111: aload           4
        //   113: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   116: ifeq            222
        //   119: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //   122: dup            
        //   123: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //   126: aload           4
        //   128: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   131: astore          5
        //   133: aload           5
        //   135: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   138: if_acmpne       154
        //   141: aload_0        
        //   142: ldc             "Switch to getter method notation"
        //   144: invokevirtual   com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.setText:(Ljava/lang/String;)V
        //   147: goto            177
        //   150: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   153: athrow         
        //   154: aload           5
        //   156: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   159: if_acmpne       175
        //   162: aload_0        
        //   163: ldc             "Switch to setter method notation"
        //   165: invokevirtual   com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.setText:(Ljava/lang/String;)V
        //   168: goto            177
        //   171: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   174: athrow         
        //   175: iconst_0       
        //   176: ireturn        
        //   177: aload           4
        //   179: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   182: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   187: astore          6
        //   189: aload           6
        //   191: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   194: ifne            212
        //   197: aload           6
        //   199: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   202: ifeq            220
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   211: athrow         
        //   212: iconst_1       
        //   213: goto            221
        //   216: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   219: athrow         
        //   220: iconst_0       
        //   221: ireturn        
        //   222: aload           4
        //   224: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   227: ifeq            337
        //   230: aload_0        
        //   231: ldc             "Switch to '.' notation"
        //   233: invokevirtual   com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.setText:(Ljava/lang/String;)V
        //   236: aload           4
        //   238: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //   246: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   249: astore          5
        //   251: aload           5
        //   253: ifnull          335
        //   256: aload           5
        //   258: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   263: ifnonnull       327
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   272: athrow         
        //   273: aload           5
        //   275: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
        //   280: ifne            327
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   289: athrow         
        //   290: aload           5
        //   292: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   297: ifeq            335
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   306: athrow         
        //   307: aload           5
        //   309: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   314: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getObjCGetterFromSetter:(Ljava/lang/String;)Ljava/lang/String;
        //   317: ifnull          335
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   326: athrow         
        //   327: iconst_1       
        //   328: goto            336
        //   331: invokestatic    com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   334: athrow         
        //   335: iconst_0       
        //   336: ireturn        
        //   337: iconst_0       
        //   338: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  133    150    150    154    Lcom/intellij/util/IncorrectOperationException;
        //  154    171    171    175    Lcom/intellij/util/IncorrectOperationException;
        //  189    205    208    212    Lcom/intellij/util/IncorrectOperationException;
        //  197    216    216    220    Lcom/intellij/util/IncorrectOperationException;
        //  251    266    269    273    Lcom/intellij/util/IncorrectOperationException;
        //  256    283    286    290    Lcom/intellij/util/IncorrectOperationException;
        //  273    300    303    307    Lcom/intellij/util/IncorrectOperationException;
        //  290    320    323    327    Lcom/intellij/util/IncorrectOperationException;
        //  307    331    331    335    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0273:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCSwitchPropertyDotMethodIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCExpression ocExpression = (OCExpression)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCSendMessageExpression.class, OCQualifiedExpression.class });
        if (ocExpression instanceof OCQualifiedExpression) {
            final ReadWriteAccessDetector.Access expressionAccess = new OCReadWriteAccessDetector().getExpressionAccess((PsiElement)ocExpression);
            final OCSymbol resolveToSymbol = ((OCQualifiedExpression)ocExpression).resolveToSymbol();
            Label_0271: {
                String s = null;
                Label_0204: {
                    Label_0183: {
                        try {
                            if (expressionAccess != ReadWriteAccessDetector.Access.Read) {
                                break Label_0271;
                            }
                            if (!(resolveToSymbol instanceof OCPropertySymbol)) {
                                break Label_0183;
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                        s = ((OCPropertySymbol)resolveToSymbol).getGetterName();
                        break Label_0204;
                    }
                    if (!(resolveToSymbol instanceof OCMethodSymbol)) {
                        return;
                    }
                    s = resolveToSymbol.getName();
                }
                final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)OCElementFactory.expressionFromText("[x " + s + "]", psiElement, true);
                OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocSendMessageExpression.getReceiverExpression(), ((OCQualifiedExpression)ocExpression).getQualifier());
                OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocExpression, ocSendMessageExpression);
                return;
            }
            if (expressionAccess == ReadWriteAccessDetector.Access.Write) {
                final PsiElement parent = OCParenthesesUtils.topmostParenthesized(ocExpression).getParent();
                String s2;
                if (resolveToSymbol instanceof OCPropertySymbol) {
                    s2 = ((OCPropertySymbol)resolveToSymbol).getSetterName();
                }
                else {
                    if (!(resolveToSymbol instanceof OCMethodSymbol)) {
                        return;
                    }
                    s2 = resolveToSymbol.getName();
                }
                if (parent instanceof OCAssignmentExpression) {
                    final OCSendMessageExpression ocSendMessageExpression2 = (OCSendMessageExpression)OCElementFactory.expressionFromText("[x " + s2 + "x]", psiElement, true);
                    OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocSendMessageExpression2.getReceiverExpression(), ((OCQualifiedExpression)ocExpression).getQualifier());
                    OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocSendMessageExpression2.getArgumentExpressions().get(0), ((OCAssignmentExpression)parent).getSourceExpression());
                    OCChangeUtil.replaceHandlingMacros(parent, (PsiElement)ocSendMessageExpression2);
                }
            }
        }
        else if (ocExpression instanceof OCSendMessageExpression) {
            final OCMethodSymbol knownResponder = ((OCSendMessageExpression)ocExpression).getProbableResponders().getKnownResponder();
            OCPropertySymbol generatedFromProperty = null;
            Label_0483: {
                try {
                    if (knownResponder != null) {
                        generatedFromProperty = knownResponder.getGeneratedFromProperty();
                        break Label_0483;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                generatedFromProperty = null;
            }
            final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
            String name = null;
            Label_0505: {
                try {
                    if (ocPropertySymbol != null) {
                        name = ocPropertySymbol.getName();
                        break Label_0505;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
                name = null;
            }
            String s3 = name;
            Label_0658: {
                Label_0634: {
                    Label_0612: {
                        Label_0550: {
                            Label_0529: {
                                try {
                                    if (knownResponder == null) {
                                        break Label_0612;
                                    }
                                    final OCMethodSymbol ocMethodSymbol = knownResponder;
                                    final boolean b = ocMethodSymbol.isGetter();
                                    if (b) {
                                        break Label_0529;
                                    }
                                    break Label_0612;
                                }
                                catch (IncorrectOperationException ex6) {
                                    throw a(ex6);
                                }
                                try {
                                    final OCMethodSymbol ocMethodSymbol = knownResponder;
                                    final boolean b = ocMethodSymbol.isGetter();
                                    if (!b) {
                                        break Label_0612;
                                    }
                                    if (s3 != null) {
                                        break Label_0550;
                                    }
                                }
                                catch (IncorrectOperationException ex7) {
                                    throw a(ex7);
                                }
                            }
                            s3 = knownResponder.getName();
                        }
                        final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)OCElementFactory.expressionFromText("x." + s3, psiElement, true);
                        OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocQualifiedExpression.getQualifier(), ((OCSendMessageExpression)ocExpression).getReceiverExpression());
                        OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocExpression, ocQualifiedExpression);
                        return;
                        try {
                            if (knownResponder == null) {
                                return;
                            }
                            final OCMethodSymbol ocMethodSymbol2 = knownResponder;
                            final boolean b2 = ocMethodSymbol2.isSetter();
                            if (b2) {
                                break Label_0634;
                            }
                            return;
                        }
                        catch (IncorrectOperationException ex8) {
                            throw a(ex8);
                        }
                    }
                    try {
                        final OCMethodSymbol ocMethodSymbol2 = knownResponder;
                        final boolean b2 = ocMethodSymbol2.isSetter();
                        if (!b2) {
                            return;
                        }
                        if (s3 != null) {
                            break Label_0658;
                        }
                    }
                    catch (IncorrectOperationException ex9) {
                        throw a(ex9);
                    }
                }
                s3 = OCNameSuggester.getObjCGetterFromSetter(knownResponder.getName());
            }
            final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)OCElementFactory.expressionFromText("x." + s3 + "=x", psiElement, true);
            OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocAssignmentExpression.getSourceExpression(), ((OCSendMessageExpression)ocExpression).getArgumentExpressions().get(0));
            OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(((OCQualifiedExpression)ocAssignmentExpression.getReceiverExpression()).getQualifier(), ((OCSendMessageExpression)ocExpression).getReceiverExpression());
            OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(ocExpression, ocAssignmentExpression);
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
