// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.ElementDescriptionLocation;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ElementDescriptionProvider;

public class OCElementDescriptionProvider implements ElementDescriptionProvider
{
    public String getElementDescription(@NotNull final PsiElement p0, @NotNull final ElementDescriptionLocation p1) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/search/OCElementDescriptionProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getElementDescription"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "location"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/search/OCElementDescriptionProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getElementDescription"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    92: ifeq            111
        //    95: aload_1        
        //    96: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   101: ifne            117
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aconst_null    
        //   112: areturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_1        
        //   118: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   121: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   126: astore_3       
        //   127: aload_3        
        //   128: ifnull          145
        //   131: aload_3        
        //   132: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCForeignSymbol;
        //   135: ifeq            151
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aconst_null    
        //   146: areturn        
        //   147: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: instanceof      Lcom/intellij/usageView/UsageViewShortNameLocation;
        //   155: ifne            200
        //   158: aload_2        
        //   159: instanceof      Lcom/intellij/usageView/UsageViewLongNameLocation;
        //   162: ifne            200
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_2        
        //   173: instanceof      Lcom/intellij/ide/util/DeleteNameDescriptionLocation;
        //   176: ifne            200
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_2        
        //   187: instanceof      Lcom/intellij/refactoring/util/NonCodeSearchDescriptionLocation;
        //   190: ifeq            211
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_3        
        //   201: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   206: areturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload_2        
        //   212: instanceof      Lcom/intellij/usageView/UsageViewTypeLocation;
        //   215: ifeq            229
        //   218: aload_3        
        //   219: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKindLowercase:()Ljava/lang/String;
        //   224: areturn        
        //   225: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload_2        
        //   230: instanceof      Lcom/intellij/ide/util/DeleteTypeDescriptionLocation;
        //   233: ifeq            269
        //   236: aload_3        
        //   237: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKindLowercase:()Ljava/lang/String;
        //   242: astore          4
        //   244: aload_2        
        //   245: checkcast       Lcom/intellij/ide/util/DeleteTypeDescriptionLocation;
        //   248: invokevirtual   com/intellij/ide/util/DeleteTypeDescriptionLocation.isPlural:()Z
        //   251: ifeq            266
        //   254: aload           4
        //   256: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;)Ljava/lang/String;
        //   259: goto            268
        //   262: invokestatic    com/jetbrains/cidr/lang/search/OCElementDescriptionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload           4
        //   268: areturn        
        //   269: aload_2        
        //   270: instanceof      Lcom/intellij/usageView/UsageViewNodeTextLocation;
        //   273: ifeq            370
        //   276: aload_3        
        //   277: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   282: astore          4
        //   284: aload_3        
        //   285: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   288: ifeq            336
        //   291: new             Ljava/lang/StringBuilder;
        //   294: dup            
        //   295: invokespecial   java/lang/StringBuilder.<init>:()V
        //   298: aload           4
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: ldc             " of "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload_3        
        //   309: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   312: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   317: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   320: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   331: astore          4
        //   333: goto            367
        //   336: aload_3        
        //   337: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   342: ifeq            367
        //   345: new             Ljava/lang/StringBuilder;
        //   348: dup            
        //   349: invokespecial   java/lang/StringBuilder.<init>:()V
        //   352: ldc             "predefinition of "
        //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   357: aload           4
        //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   365: astore          4
        //   367: aload           4
        //   369: areturn        
        //   370: aload_3        
        //   371: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   376: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     104    107    111    Ljava/lang/IllegalArgumentException;
        //  95     113    113    117    Ljava/lang/IllegalArgumentException;
        //  127    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    147    147    151    Ljava/lang/IllegalArgumentException;
        //  151    165    168    172    Ljava/lang/IllegalArgumentException;
        //  158    179    182    186    Ljava/lang/IllegalArgumentException;
        //  172    193    196    200    Ljava/lang/IllegalArgumentException;
        //  186    207    207    211    Ljava/lang/IllegalArgumentException;
        //  211    225    225    229    Ljava/lang/IllegalArgumentException;
        //  244    262    262    266    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0172:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
