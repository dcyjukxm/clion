// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.command.WriteCommandAction;

class OCDeclareMembersHandler$2 extends WriteCommandAction {
    protected void run(@NotNull final Result p0) throws Throwable {
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
        //    18: ldc             "result"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "run"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$targetDeclFinal:Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    48: astore_2       
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;
        //    53: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext.getTarget:()Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext$Target;
        //    56: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext$Target.PRIVATE_CATEGORY:Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext$Target;
        //    59: if_acmpne       147
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$targetSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    66: ifnonnull       147
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    75: athrow         
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;
        //    80: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext.getImplementationSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    83: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    86: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //    89: astore_3       
        //    90: aload_3        
        //    91: ifnull          147
        //    94: new             Ljava/lang/StringBuilder;
        //    97: dup            
        //    98: invokespecial   java/lang/StringBuilder.<init>:()V
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;
        //   105: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   108: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   111: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: ldc             "()"
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: aload_3        
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.interfaceByName:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   131: astore_2       
        //   132: aload_3        
        //   133: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclarationBase.getParent:()Lcom/intellij/psi/PsiElement;
        //   138: aload_2        
        //   139: aload_3        
        //   140: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   143: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   146: astore_2       
        //   147: aload_2        
        //   148: ifnonnull       156
        //   151: return         
        //   152: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   155: athrow         
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$membersToAdd:Ljava/util/List;
        //   160: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   165: astore_3       
        //   166: aload_3        
        //   167: invokeinterface java/util/Iterator.hasNext:()Z
        //   172: ifeq            369
        //   175: aload_3        
        //   176: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   181: checkcast       Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember;
        //   184: astore          4
        //   186: aload           4
        //   188: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.symbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   191: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   194: ifeq            259
        //   197: aload_2        
        //   198: aload           4
        //   200: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.member:Lcom/intellij/psi/PsiElement;
        //   203: aload           4
        //   205: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.anchor:Lcom/intellij/psi/PsiElement;
        //   208: ifnull          250
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   217: athrow         
        //   218: aload           4
        //   220: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.anchor:Lcom/intellij/psi/PsiElement;
        //   223: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   228: ifeq            250
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   237: athrow         
        //   238: aload           4
        //   240: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.anchor:Lcom/intellij/psi/PsiElement;
        //   243: goto            251
        //   246: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   249: athrow         
        //   250: aconst_null    
        //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   254: astore          5
        //   256: goto            361
        //   259: aload           4
        //   261: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.member:Lcom/intellij/psi/PsiElement;
        //   264: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   267: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCNormalizeUtil.normalizeDeclarator:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   270: astore          6
        //   272: aload           4
        //   274: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.symbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   277: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   280: ifeq            325
        //   283: aload           6
        //   285: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   290: astore          6
        //   292: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.$assertionsDisabled:Z
        //   295: ifne            325
        //   298: aload           6
        //   300: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   303: ifne            325
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   312: athrow         
        //   313: new             Ljava/lang/AssertionError;
        //   316: dup            
        //   317: invokespecial   java/lang/AssertionError.<init>:()V
        //   320: athrow         
        //   321: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   324: athrow         
        //   325: aload_0        
        //   326: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.this$0:Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler;
        //   329: aload           4
        //   331: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$1AddMember.symbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   334: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   337: ifeq            353
        //   340: aload_2        
        //   341: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getInstanceVariablesList:()Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   346: goto            354
        //   349: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   352: athrow         
        //   353: aload_2        
        //   354: aload           6
        //   356: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.moveDeclaration:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   359: astore          5
        //   361: aload           5
        //   363: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //   366: goto            166
        //   369: aload_0        
        //   370: getfield        com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.val$elementsToDelete:Ljava/util/List;
        //   373: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   378: astore_3       
        //   379: aload_3        
        //   380: invokeinterface java/util/Iterator.hasNext:()Z
        //   385: ifeq            436
        //   388: aload_3        
        //   389: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   394: checkcast       Lcom/intellij/psi/PsiElement;
        //   397: astore          4
        //   399: aload           4
        //   401: ifnull          433
        //   404: aload           4
        //   406: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   411: ifeq            433
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   420: athrow         
        //   421: aload           4
        //   423: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   426: goto            433
        //   429: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   432: athrow         
        //   433: goto            379
        //   436: return         
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      40     40     44     Ljava/lang/Throwable;
        //  49     69     72     76     Ljava/lang/Throwable;
        //  147    152    152    156    Ljava/lang/Throwable;
        //  186    211    214    218    Ljava/lang/Throwable;
        //  197    231    234    238    Ljava/lang/Throwable;
        //  218    246    246    250    Ljava/lang/Throwable;
        //  292    306    309    313    Ljava/lang/Throwable;
        //  298    321    321    325    Ljava/lang/Throwable;
        //  325    349    349    353    Ljava/lang/Throwable;
        //  399    414    417    421    Ljava/lang/Throwable;
        //  404    426    429    433    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0218:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCDeclareMembersHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}