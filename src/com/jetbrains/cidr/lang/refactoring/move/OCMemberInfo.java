// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoBase;

public class OCMemberInfo extends MemberInfoBase<OCSymbolHolderVirtualPsiElement>
{
    private OCVisibility myVisibility;
    private OCSymbol mySymbol;
    private boolean isAbstract;
    
    public OCMemberInfo(final OCSymbol ocSymbol, final OCMembersContainer ocMembersContainer) {
        this(ocSymbol, ocSymbol, false, ocMembersContainer);
    }
    
    public OCMemberInfo(final OCSymbol p0, final OCSymbol p1, final boolean p2, final OCMembersContainer p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //     4: dup            
        //     5: aload_2        
        //     6: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //     9: invokespecial   com/intellij/refactoring/classMembers/MemberInfoBase.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    12: aload_0        
        //    13: aload_2        
        //    14: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    17: aload_0        
        //    18: aload_1        
        //    19: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getPresentableName:()Ljava/lang/String;
        //    24: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //    27: aload_0        
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    32: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getDeclaredVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    35: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.myVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    38: iload_3        
        //    39: ifeq            69
        //    42: aload_1        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    46: ifeq            69
        //    49: aload_0        
        //    50: aload_1        
        //    51: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    54: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getNameWithParent:()Ljava/lang/String;
        //    59: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    73: ifeq            102
        //    76: aload_0        
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    81: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    84: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    92: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:Z
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   101: athrow         
        //   102: aload_0        
        //   103: getfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.myVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   106: ifnull          128
        //   109: aload_1        
        //   110: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   115: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   118: if_acmpne       142
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: aload_0        
        //   129: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   132: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.myVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   141: athrow         
        //   142: aload_1        
        //   143: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   146: ifeq            167
        //   149: aload_0        
        //   150: aload_1        
        //   151: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   154: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   157: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isStatic:Z
        //   160: goto            407
        //   163: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   166: athrow         
        //   167: aload_1        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   171: ifeq            216
        //   174: aload_0        
        //   175: aload_1        
        //   176: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   179: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //   182: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isStatic:Z
        //   185: aload_0        
        //   186: aload_1        
        //   187: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   190: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPureVirtual:()Z
        //   193: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:Z
        //   196: aload_0        
        //   197: aload_1        
        //   198: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   201: iload_3        
        //   202: iconst_1       
        //   203: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getSignatureWithoutParamNames:(ZZ)Ljava/lang/String;
        //   206: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //   209: goto            407
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   215: athrow         
        //   216: aload_1        
        //   217: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   220: ifeq            264
        //   223: aload_0        
        //   224: aload_1        
        //   225: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   228: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //   233: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isStatic:Z
        //   236: iload_3        
        //   237: ifne            407
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   246: athrow         
        //   247: aload_0        
        //   248: aload_1        
        //   249: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getSignature:()Ljava/lang/String;
        //   254: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //   257: goto            407
        //   260: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   263: athrow         
        //   264: aload           4
        //   266: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   269: ifeq            326
        //   272: aload_1        
        //   273: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //   276: ifeq            326
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   285: athrow         
        //   286: aload_0        
        //   287: new             Ljava/lang/StringBuilder;
        //   290: dup            
        //   291: invokespecial   java/lang/StringBuilder.<init>:()V
        //   294: ldc             "conforms to "
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: aload_1        
        //   300: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   311: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //   314: aload_0        
        //   315: iconst_1       
        //   316: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:Z
        //   319: goto            407
        //   322: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   325: athrow         
        //   326: aload           4
        //   328: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   331: ifeq            407
        //   334: aload_1        
        //   335: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   338: ifeq            407
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   347: athrow         
        //   348: aload_1        
        //   349: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   352: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   355: aload           4
        //   357: if_acmpeq       407
        //   360: goto            367
        //   363: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   366: athrow         
        //   367: aload_0        
        //   368: new             Ljava/lang/StringBuilder;
        //   371: dup            
        //   372: invokespecial   java/lang/StringBuilder.<init>:()V
        //   375: ldc             "super "
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: aload_1        
        //   381: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   392: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.displayName:Ljava/lang/String;
        //   395: aload_0        
        //   396: iconst_1       
        //   397: putfield        com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.isAbstract:Z
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   406: athrow         
        //   407: aload           4
        //   409: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   412: ifne            430
        //   415: aload           4
        //   417: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   420: ifeq            468
        //   423: goto            430
        //   426: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   429: athrow         
        //   430: aload_1        
        //   431: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   434: ifeq            468
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   443: athrow         
        //   444: aload_1        
        //   445: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   448: aload_0        
        //   449: aload_1        
        //   450: invokedynamic   process:(Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfo;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/Processor;
        //   455: iconst_1       
        //   456: iconst_0       
        //   457: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.processMembersHierarchy:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/util/Processor;ZZ)Z
        //   460: pop            
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   467: athrow         
        //   468: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  42     62     65     69     Ljava/lang/IllegalStateException;
        //  69     95     98     102    Ljava/lang/IllegalStateException;
        //  102    121    124    128    Ljava/lang/IllegalStateException;
        //  109    135    138    142    Ljava/lang/IllegalStateException;
        //  142    163    163    167    Ljava/lang/IllegalStateException;
        //  167    212    212    216    Ljava/lang/IllegalStateException;
        //  216    240    243    247    Ljava/lang/IllegalStateException;
        //  223    260    260    264    Ljava/lang/IllegalStateException;
        //  264    279    282    286    Ljava/lang/IllegalStateException;
        //  272    322    322    326    Ljava/lang/IllegalStateException;
        //  326    341    344    348    Ljava/lang/IllegalStateException;
        //  334    360    363    367    Ljava/lang/IllegalStateException;
        //  348    400    403    407    Ljava/lang/IllegalStateException;
        //  407    423    426    430    Ljava/lang/IllegalStateException;
        //  415    437    440    444    Ljava/lang/IllegalStateException;
        //  430    461    464    468    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0348:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public OCMemberInfo(final OCStructSymbol ocStructSymbol, final OCVisibility myVisibility, final OCMembersContainer ocMembersContainer) {
        this(ocStructSymbol, ocMembersContainer);
        this.myVisibility = myVisibility;
    }
    
    public OCSymbol getSymbol() {
        return this.mySymbol;
    }
    
    @NotNull
    public OCVisibility getVisibility() {
        OCVisibility myVisibility;
        try {
            myVisibility = this.myVisibility;
            if (myVisibility == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo", "getVisibility"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myVisibility;
    }
    
    public String getDisplayNameWithKind() {
        return this.mySymbol.getKindUppercase() + " '" + this.getDisplayName() + "'";
    }
    
    public boolean isAbstract() {
        return this.isAbstract;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
