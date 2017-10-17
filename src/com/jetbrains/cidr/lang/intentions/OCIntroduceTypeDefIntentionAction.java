// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCIntroduceTypeDefIntentionAction extends PsiElementBaseIntentionAction
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //    91: iconst_0       
        //    92: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //    95: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    98: astore          4
        //   100: aload           4
        //   102: ifnonnull       111
        //   105: iconst_0       
        //   106: ireturn        
        //   107: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: aload_3        
        //   112: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //   114: iconst_0       
        //   115: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   118: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   121: astore          5
        //   123: aload           5
        //   125: ifnull          158
        //   128: aload           5
        //   130: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   133: ifne            158
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   142: athrow         
        //   143: aload           5
        //   145: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   148: ifeq            164
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   157: athrow         
        //   158: iconst_0       
        //   159: ireturn        
        //   160: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   163: athrow         
        //   164: aload           4
        //   166: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   171: ifnull          180
        //   174: iconst_0       
        //   175: ireturn        
        //   176: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   179: athrow         
        //   180: aload           5
        //   182: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   187: astore          6
        //   189: aload           6
        //   191: invokeinterface java/util/List.size:()I
        //   196: ifeq            217
        //   199: aload           6
        //   201: invokeinterface java/util/List.size:()I
        //   206: iconst_1       
        //   207: if_icmple       223
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   216: athrow         
        //   217: iconst_0       
        //   218: ireturn        
        //   219: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   222: athrow         
        //   223: aload           4
        //   225: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   230: astore          7
        //   232: aload           4
        //   234: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   239: ifnull          293
        //   242: aload           7
        //   244: iconst_0       
        //   245: iconst_0       
        //   246: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/jetbrains/cidr/lang/types/OCType;ZZ)Z
        //   249: ifeq            293
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: aload_0        
        //   260: new             Ljava/lang/StringBuilder;
        //   263: dup            
        //   264: invokespecial   java/lang/StringBuilder.<init>:()V
        //   267: ldc             "Introduce typedef for type "
        //   269: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   272: aload           7
        //   274: aload_3        
        //   275: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: invokevirtual   com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.setText:(Ljava/lang/String;)V
        //   287: iconst_1       
        //   288: ireturn        
        //   289: invokestatic    com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   292: athrow         
        //   293: iconst_0       
        //   294: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  100    107    107    111    Lcom/intellij/util/IncorrectOperationException;
        //  123    136    139    143    Lcom/intellij/util/IncorrectOperationException;
        //  128    151    154    158    Lcom/intellij/util/IncorrectOperationException;
        //  143    160    160    164    Lcom/intellij/util/IncorrectOperationException;
        //  164    176    176    180    Lcom/intellij/util/IncorrectOperationException;
        //  189    210    213    217    Lcom/intellij/util/IncorrectOperationException;
        //  199    219    219    223    Lcom/intellij/util/IncorrectOperationException;
        //  232    252    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  242    289    289    293    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCDeclarator ocDeclarator = (OCDeclarator)PsiTreeUtil.getParentOfType(psiElement, (Class)OCDeclarator.class, false);
        if (ocDeclarator != null) {
            final String name = ocDeclarator.getName();
            if (ocDeclarator.getParent() instanceof OCDeclaration) {
                final OCDeclaration ocDeclaration = (OCDeclaration)ocDeclarator.getParent();
                final OCDeclaration ocDeclaration2 = (OCDeclaration)ocDeclaration.copy();
                final PsiFile containingFile = psiElement.getContainingFile();
                final PsiElement firstChild = OCElementFactory.typeElementFromText("typedef int", (PsiElement)containingFile).getFirstChild();
                ocDeclaration2.addBefore(firstChild.getNextSibling(), ocDeclaration2.getFirstChild());
                ocDeclaration2.addBefore(firstChild, ocDeclaration2.getFirstChild());
                OCChangeUtil.addBefore((PsiElement)containingFile, ocDeclaration2, (PsiElement)ocDeclaration);
                ocDeclaration.replace((PsiElement)OCElementFactory.declarationByNameAndType(name, OCElementFactory.typeElementFromText(name, (PsiElement)ocDeclaration).getType(), (PsiElement)ocDeclaration));
            }
        }
    }
    
    private static boolean a(@NotNull final OCType ocType, final boolean b, final boolean b2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction", "isComplexType"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0091: {
            Label_0068: {
                try {
                    if (!(ocType instanceof OCPointerType)) {
                        break Label_0091;
                    }
                    final boolean b3 = b2;
                    if (b3) {
                        return true;
                    }
                    break Label_0068;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final boolean b3 = b2;
                    if (b3) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return a(((OCPointerType)ocType).getRefType(), true, b2);
            try {
                if (ocType instanceof OCReferenceType) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        try {
            if (ocType instanceof OCFunctionType) {
                return true;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        return false;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Introduce Typedef";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCIntroduceTypeDefIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
