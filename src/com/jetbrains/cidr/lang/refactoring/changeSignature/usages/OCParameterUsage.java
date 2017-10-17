// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.intellij.psi.PsiElement;

public class OCParameterUsage extends OCUsageInfo<PsiElement>
{
    private OCParameterInfo myNewParam;
    private boolean myInherited;
    private OCCallable myCallable;
    
    public OCParameterUsage(@NotNull final PsiReference psiReference, final OCParameterInfo myNewParam, final OCCallable myCallable, final boolean myInherited) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage", "<init>"));
        }
        super(psiReference);
        this.myNewParam = myNewParam;
        this.myCallable = myCallable;
        this.myInherited = myInherited;
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo p0, @NotNull final PsiElement p1, @NotNull final Project p2) {
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
        //    18: ldc             "changeInfo"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processUsage"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processUsage"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "project"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processUsage"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.myNewParam:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   136: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   139: invokevirtual   java/lang/String.isEmpty:()Z
        //   142: ifeq            151
        //   145: iconst_1       
        //   146: ireturn        
        //   147: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   157: ifne            166
        //   160: iconst_1       
        //   161: ireturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_1        
        //   167: aload_0        
        //   168: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.myInherited:Z
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.myCallable:Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.myNewParam:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   179: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameterName:(ZLcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;)Ljava/lang/String;
        //   182: astore          4
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.myNewParam:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   188: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.isReferenceMode:()Z
        //   191: ifeq            406
        //   194: aload_2        
        //   195: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   200: astore          5
        //   202: aload           5
        //   204: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   207: ifne            216
        //   210: iconst_0       
        //   211: ireturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload           5
        //   218: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   223: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   226: ifne            269
        //   229: aload           5
        //   231: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   236: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   239: ifne            269
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload           5
        //   251: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   256: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   259: ifeq            306
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload           5
        //   271: new             Ljava/lang/StringBuilder;
        //   274: dup            
        //   275: invokespecial   java/lang/StringBuilder.<init>:()V
        //   278: ldc             "*"
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: aload           4
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: aload_2        
        //   292: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   295: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   298: pop            
        //   299: goto            403
        //   302: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: aload           5
        //   308: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   313: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   316: ifeq            368
        //   319: aload           5
        //   321: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   326: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   329: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.isGetAddress:()Z
        //   334: ifeq            368
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload           5
        //   346: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   351: aload           4
        //   353: aload_2        
        //   354: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   357: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   360: pop            
        //   361: goto            403
        //   364: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: aload           5
        //   370: new             Ljava/lang/StringBuilder;
        //   373: dup            
        //   374: invokespecial   java/lang/StringBuilder.<init>:()V
        //   377: ldc             "(*"
        //   379: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: aload           4
        //   384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   387: ldc             ")"
        //   389: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   392: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   395: aload_2        
        //   396: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   399: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   402: pop            
        //   403: goto            461
        //   406: aload_2        
        //   407: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   410: ifeq            435
        //   413: aload_2        
        //   414: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   417: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   422: aload           4
        //   424: aload_2        
        //   425: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.replaceWithIdentifier:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   428: goto            461
        //   431: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: aload_2        
        //   436: instanceof      Lcom/intellij/psi/PsiNamedElement;
        //   439: ifeq            461
        //   442: aload_2        
        //   443: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   446: aload           4
        //   448: invokeinterface com/intellij/psi/PsiNamedElement.setName:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   453: pop            
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCParameterUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: iconst_1       
        //   462: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    147    147    151    Ljava/lang/IllegalArgumentException;
        //  151    162    162    166    Ljava/lang/IllegalArgumentException;
        //  202    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    242    245    249    Ljava/lang/IllegalArgumentException;
        //  229    262    265    269    Ljava/lang/IllegalArgumentException;
        //  249    302    302    306    Ljava/lang/IllegalArgumentException;
        //  306    337    340    344    Ljava/lang/IllegalArgumentException;
        //  319    364    364    368    Ljava/lang/IllegalArgumentException;
        //  406    431    431    435    Ljava/lang/IllegalArgumentException;
        //  435    454    457    461    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0249:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
