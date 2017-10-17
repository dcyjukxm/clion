// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.containers.MultiMap;
import com.intellij.util.Processor;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.openapi.util.Pair;
import java.util.Stack;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;

public enum OCVisibility
{
    NULL, 
    PUBLIC, 
    PACKAGE, 
    PROTECTED, 
    PRIVATE;
    
    @Override
    public String toString() {
        try {
            if (this == OCVisibility.PUBLIC) {
                return "public";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this == OCVisibility.PACKAGE) {
                return "package";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this == OCVisibility.PROTECTED) {
                return "protected";
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this == OCVisibility.PRIVATE) {
                return "private";
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return super.toString();
    }
    
    public static OCVisibility getDefaultObjCVisibility(final OCSymbolKind ocSymbolKind) {
        try {
            if (ocSymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
                return OCVisibility.PROTECTED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCVisibility.PRIVATE;
    }
    
    @Nullable
    public static OCVisibility getVisibilityFromElement(final PsiElement psiElement) {
        IElementType elementType = OCElementUtil.getElementType(psiElement);
        if (elementType == OCElementTypes.OBJC_KEYWORD) {
            elementType = OCElementUtil.getObjCKeywordElementType(psiElement.getNode());
        }
        return getVisibilityFromElementType(elementType);
    }
    
    @Nullable
    public static OCVisibility getVisibilityFromElementType(final IElementType elementType) {
        try {
            if (elementType == OCTokenTypes.PUBLIC_KEYWORD) {
                return OCVisibility.PUBLIC;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (elementType == OCTokenTypes.PACKAGE_KEYWORD) {
                return OCVisibility.PACKAGE;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (elementType == OCTokenTypes.PROTECTED_KEYWORD) {
                return OCVisibility.PROTECTED;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (elementType == OCTokenTypes.PRIVATE_KEYWORD) {
                return OCVisibility.PRIVATE;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Nullable
    public OCElementType getElementType() {
        try {
            switch (this) {
                case PUBLIC: {
                    return OCTokenTypes.PUBLIC_KEYWORD;
                }
                case PACKAGE: {
                    break;
                }
                case PROTECTED: {
                    return OCTokenTypes.PROTECTED_KEYWORD;
                }
                case PRIVATE: {
                    return OCTokenTypes.PRIVATE_KEYWORD;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCTokenTypes.PACKAGE_KEYWORD;
    }
    
    public static OCVisibility min(final OCVisibility ocVisibility, final OCVisibility ocVisibility2) {
        try {
            if (ocVisibility.ordinal() < ocVisibility2.ordinal()) {
                return ocVisibility;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocVisibility2;
    }
    
    public static OCVisibility max(final OCVisibility ocVisibility, final OCVisibility ocVisibility2) {
        try {
            if (ocVisibility.ordinal() < ocVisibility2.ordinal()) {
                return ocVisibility2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocVisibility;
    }
    
    public static boolean isVisible(final OCSymbol ocSymbol, final PsiElement psiElement, @Nullable final OCType ocType) {
        return isVisible(ocSymbol, getVisibility(ocSymbol, psiElement, ocType));
    }
    
    public static boolean isVisible(final OCSymbol p0, final OCVisibility p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getDeclaredVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //     4: astore_2       
        //     5: aload_2        
        //     6: ifnull          42
        //     9: aload_1        
        //    10: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //    13: aload_2        
        //    14: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //    17: if_icmplt       50
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_1        
        //    29: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.shouldBeDeclaredInInterface:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Z
        //    32: ifne            50
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_1       
        //    43: goto            51
        //    46: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_0       
        //    51: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      20     23     27     Ljava/lang/IllegalArgumentException;
        //  9      35     38     42     Ljava/lang/IllegalArgumentException;
        //  27     46     46     50     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    public static boolean shouldBeDeclaredInInterface(final OCSymbol ocSymbol, final OCVisibility ocVisibility) {
        try {
            if (ocVisibility.ordinal() >= OCVisibility.PRIVATE.ordinal() || !(ocSymbol instanceof OCMemberSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent();
        Label_0068: {
            try {
                if (ocClassSymbol instanceof OCImplementationSymbol) {
                    break Label_0068;
                }
                final String s = "";
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final String s2 = ocClassSymbol2.getCategoryName();
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = "";
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final String s2 = ocClassSymbol2.getCategoryName();
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    public static OCVisibility getDeclaredVisibility(final OCSymbol ocSymbol) {
        OCVisibility ocVisibility = null;
        if (ocSymbol instanceof OCInstanceVariableSymbol) {
            ocVisibility = ((OCInstanceVariableSymbol)ocSymbol).getVisibility();
        }
        else if (ocSymbol instanceof OCMemberSymbol) {
            ocVisibility = OCVisibility.PRIVATE;
            OCMemberSymbol associatedSymbol = (OCInstanceVariableSymbol)ocSymbol;
            if (associatedSymbol.getParent() instanceof OCImplementationSymbol) {
                associatedSymbol = associatedSymbol.getAssociatedSymbol();
            }
            try {
                if (associatedSymbol == null || "".equals(((OCSymbolWithParent<T, OCClassSymbol>)associatedSymbol).getParent().getCategoryName())) {
                    return ocVisibility;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ocVisibility = OCVisibility.PUBLIC;
        }
        else if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            ocVisibility = ((OCSymbolWithQualifiedName)ocSymbol).getVisibility();
        }
        return ocVisibility;
    }
    
    @NotNull
    public static OCVisibility getVisibility(@NotNull final OCSymbol p0, @NotNull final PsiElement p1, @Nullable final OCType p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getVisibility"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "contextElement"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getVisibility"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    92: ifeq            265
        //    95: aload_0        
        //    96: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCForeignSymbol;
        //    99: ifeq            158
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   112: dup            
        //   113: ifnonnull       157
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: new             Ljava/lang/IllegalStateException;
        //   126: dup            
        //   127: ldc             "@NotNull method %s.%s must not return null"
        //   129: ldc             2
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: ldc             0
        //   137: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   139: aastore        
        //   140: dup            
        //   141: ldc             1
        //   143: ldc             "getVisibility"
        //   145: aastore        
        //   146: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   149: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   152: athrow         
        //   153: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: areturn        
        //   158: aload_0        
        //   159: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   164: astore_3       
        //   165: aload_3        
        //   166: ifnull          223
        //   169: aload_0        
        //   170: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   173: aload_1        
        //   174: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   177: dup            
        //   178: ifnonnull       222
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: new             Ljava/lang/IllegalStateException;
        //   191: dup            
        //   192: ldc             "@NotNull method %s.%s must not return null"
        //   194: ldc             2
        //   196: anewarray       Ljava/lang/Object;
        //   199: dup            
        //   200: ldc             0
        //   202: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             1
        //   208: ldc             "getVisibility"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: areturn        
        //   223: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   226: dup            
        //   227: ifnonnull       264
        //   230: new             Ljava/lang/IllegalStateException;
        //   233: dup            
        //   234: ldc             "@NotNull method %s.%s must not return null"
        //   236: ldc             2
        //   238: anewarray       Ljava/lang/Object;
        //   241: dup            
        //   242: ldc             0
        //   244: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   246: aastore        
        //   247: dup            
        //   248: ldc             1
        //   250: ldc             "getVisibility"
        //   252: aastore        
        //   253: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   256: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   259: athrow         
        //   260: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: areturn        
        //   265: aload_0        
        //   266: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   269: ifeq            327
        //   272: aload_0        
        //   273: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   276: aload_1        
        //   277: aload_2        
        //   278: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   281: dup            
        //   282: ifnonnull       326
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: new             Ljava/lang/IllegalStateException;
        //   295: dup            
        //   296: ldc             "@NotNull method %s.%s must not return null"
        //   298: ldc             2
        //   300: anewarray       Ljava/lang/Object;
        //   303: dup            
        //   304: ldc             0
        //   306: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   308: aastore        
        //   309: dup            
        //   310: ldc             1
        //   312: ldc             "getVisibility"
        //   314: aastore        
        //   315: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   318: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   321: athrow         
        //   322: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: areturn        
        //   327: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   330: dup            
        //   331: ifnonnull       368
        //   334: new             Ljava/lang/IllegalStateException;
        //   337: dup            
        //   338: ldc             "@NotNull method %s.%s must not return null"
        //   340: ldc             2
        //   342: anewarray       Ljava/lang/Object;
        //   345: dup            
        //   346: ldc             0
        //   348: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   350: aastore        
        //   351: dup            
        //   352: ldc             1
        //   354: ldc             "getVisibility"
        //   356: aastore        
        //   357: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   360: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   363: athrow         
        //   364: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     116    119    123    Ljava/lang/IllegalArgumentException;
        //  109    153    153    157    Ljava/lang/IllegalArgumentException;
        //  165    181    184    188    Ljava/lang/IllegalArgumentException;
        //  169    218    218    222    Ljava/lang/IllegalArgumentException;
        //  223    260    260    264    Ljava/lang/IllegalArgumentException;
        //  265    285    288    292    Ljava/lang/IllegalArgumentException;
        //  272    322    322    326    Ljava/lang/IllegalArgumentException;
        //  327    364    364    368    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    private static OCVisibility a(final OCMemberSymbol ocMemberSymbol, final PsiElement psiElement) {
        final PsiFile containingFile = psiElement.getContainingFile();
        final OCVisibility declaredVisibility = getDeclaredVisibility(ocMemberSymbol);
        try {
            if (declaredVisibility == OCVisibility.PUBLIC) {
                return declaredVisibility;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (declaredVisibility == OCVisibility.PACKAGE) {
            final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(psiElement.getProject());
            try {
                if (!workspace.areFromSamePackage(ocMemberSymbol.getContainingFile(), psiElement.getContainingFile().getVirtualFile())) {
                    return OCVisibility.PUBLIC;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return OCVisibility.PACKAGE;
        }
        final OCImplementation ocImplementation = (OCImplementation)PsiTreeUtil.getContextOfType(psiElement, (Class)OCImplementation.class, false);
        try {
            if (ocImplementation == null) {
                return OCVisibility.PUBLIC;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCObjectType type = ocImplementation.getType();
        final OCType resolve = ((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent().getType().resolve(containingFile);
        Label_0164: {
            Label_0156: {
                try {
                    if (type == null) {
                        break Label_0156;
                    }
                    final OCType ocType = resolve;
                    final boolean b = ocType instanceof OCObjectType;
                    if (!b) {
                        break Label_0156;
                    }
                    break Label_0164;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCType ocType = resolve;
                    final boolean b = ocType instanceof OCObjectType;
                    if (!b) {
                        return OCVisibility.PRIVATE;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (resolve.equalsAfterResolving(type, (PsiElement)containingFile)) {
                    return OCVisibility.PRIVATE;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        try {
            if (((OCObjectType)resolve).isAncestorOf(type)) {
                return OCVisibility.PROTECTED;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return OCVisibility.PUBLIC;
    }
    
    private static OCVisibility a(final OCSymbolWithParent p0, final PsiElement p1, @Nullable final OCType p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     6: astore_3       
        //     7: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    10: dup            
        //    11: aload_3        
        //    12: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    15: astore          4
        //    17: aload_1        
        //    18: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    21: astore          5
        //    23: aload           5
        //    25: ifnull          42
        //    28: aload           5
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    35: goto            43
        //    38: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aconst_null    
        //    43: astore          6
        //    45: aload           6
        //    47: ifnonnull       65
        //    50: aload           5
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //    55: ifne            100
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload           5
        //    67: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    70: ifeq            134
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload           5
        //    82: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    85: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    90: ifnonnull       134
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: aload           5
        //   102: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   107: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   110: astore          5
        //   112: aload           5
        //   114: ifnull          131
        //   117: aload           5
        //   119: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   124: goto            132
        //   127: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aconst_null    
        //   132: astore          6
        //   134: aload           6
        //   136: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   139: ifne            157
        //   142: aload           6
        //   144: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   147: ifeq            173
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           6
        //   159: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   162: aload           4
        //   164: iconst_1       
        //   165: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   168: astore          7
        //   170: goto            194
        //   173: aload           6
        //   175: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   178: ifeq            191
        //   181: aload           6
        //   183: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   186: astore          7
        //   188: goto            194
        //   191: aconst_null    
        //   192: astore          7
        //   194: aload_0        
        //   195: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   200: astore          8
        //   202: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   205: astore          9
        //   207: new             Lcom/intellij/util/containers/MultiMap;
        //   210: dup            
        //   211: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //   214: astore          10
        //   216: aload           6
        //   218: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   221: ifeq            258
        //   224: aload           6
        //   226: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   229: astore          11
        //   231: aload           11
        //   233: ifnull          258
        //   236: aload           10
        //   238: aload           11
        //   240: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   243: aload           11
        //   245: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   248: aload           11
        //   250: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   253: astore          11
        //   255: goto            231
        //   258: aload           8
        //   260: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   263: ifne            274
        //   266: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   269: areturn        
        //   270: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: new             Lcom/jetbrains/cidr/lang/symbols/OCVisibility$InnerFriendFinder;
        //   277: dup            
        //   278: aload           10
        //   280: invokespecial   com/jetbrains/cidr/lang/symbols/OCVisibility$InnerFriendFinder.<init>:(Lcom/intellij/util/containers/MultiMap;)V
        //   283: aload           8
        //   285: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility$InnerFriendFinder.process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   288: ifne            299
        //   291: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   294: areturn        
        //   295: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload           7
        //   301: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   304: ifeq            333
        //   307: aload           9
        //   309: aload           7
        //   311: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   314: aload           8
        //   316: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   319: aload_0        
        //   320: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   323: aload           4
        //   325: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   328: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   331: astore          9
        //   333: aload_2        
        //   334: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   337: ifeq            405
        //   340: aload_2        
        //   341: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   344: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   347: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   352: astore          11
        //   354: aload           11
        //   356: invokeinterface java/util/Iterator.hasNext:()Z
        //   361: ifeq            402
        //   364: aload           11
        //   366: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   371: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   374: astore          12
        //   376: aload           9
        //   378: aload           12
        //   380: aload           8
        //   382: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   385: aload_0        
        //   386: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   389: aload           4
        //   391: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   394: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   397: astore          9
        //   399: goto            354
        //   402: goto            567
        //   405: aload_1        
        //   406: instanceof      Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;
        //   409: ifeq            549
        //   412: aload_1        
        //   413: checkcast       Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;
        //   416: invokeinterface com/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   421: astore          11
        //   423: aload           11
        //   425: ifnull          528
        //   428: aload           11
        //   430: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolKindFilter.ONLY_NAMESPACE_LIKE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolKindFilter;
        //   433: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolFilter;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //   436: astore          12
        //   438: aload           12
        //   440: iconst_1       
        //   441: iconst_0       
        //   442: aload_3        
        //   443: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.resolveToSymbols:(ZZLcom/intellij/psi/PsiFile;)Ljava/util/List;
        //   446: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   451: astore          13
        //   453: aload           13
        //   455: invokeinterface java/util/Iterator.hasNext:()Z
        //   460: ifeq            525
        //   463: aload           13
        //   465: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   470: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   473: astore          14
        //   475: aload           14
        //   477: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   480: ifeq            512
        //   483: aload           9
        //   485: aload           14
        //   487: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   490: aload           8
        //   492: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   495: aload_0        
        //   496: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   499: aload           4
        //   501: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   504: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   507: astore          9
        //   509: goto            522
        //   512: aload           9
        //   514: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   517: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   520: astore          9
        //   522: goto            453
        //   525: goto            546
        //   528: aload           7
        //   530: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   533: ifne            546
        //   536: aload           9
        //   538: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   541: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   544: astore          9
        //   546: goto            567
        //   549: aload           7
        //   551: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   554: ifne            567
        //   557: aload           9
        //   559: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   562: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.max:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   565: astore          9
        //   567: aload           9
        //   569: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  23     38     38     42     Ljava/lang/IllegalArgumentException;
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  50     73     76     80     Ljava/lang/IllegalArgumentException;
        //  65     93     96     100    Ljava/lang/IllegalArgumentException;
        //  112    127    127    131    Ljava/lang/IllegalArgumentException;
        //  134    150    153    157    Ljava/lang/IllegalArgumentException;
        //  258    270    270    274    Ljava/lang/IllegalArgumentException;
        //  274    295    295    299    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    @Nullable
    private static OCSymbolDeclarator a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //     4: ifeq            38
        //     7: aload_0        
        //     8: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    16: ifeq            38
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    32: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    37: astore_0       
        //    38: aload_0        
        //    39: iconst_0       
        //    40: iconst_4       
        //    41: anewarray       Ljava/lang/Class;
        //    44: dup            
        //    45: iconst_0       
        //    46: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //    48: aastore        
        //    49: dup            
        //    50: iconst_1       
        //    51: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //    53: aastore        
        //    54: dup            
        //    55: iconst_2       
        //    56: ldc             Lcom/jetbrains/cidr/lang/psi/OCStructLike;.class
        //    58: aastore        
        //    59: dup            
        //    60: iconst_3       
        //    61: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //    63: aastore        
        //    64: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    67: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    70: astore_1       
        //    71: aload_1        
        //    72: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    75: ifeq            200
        //    78: aload_1        
        //    79: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    82: ifne            200
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_1        
        //    93: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   101: invokeinterface java/util/List.isEmpty:()Z
        //   106: ifne            172
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_1        
        //   117: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   120: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   125: iconst_0       
        //   126: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   131: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   139: ifnull          172
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_1        
        //   150: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   153: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   158: iconst_0       
        //   159: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   164: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   167: areturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_0        
        //   173: iconst_0       
        //   174: iconst_3       
        //   175: anewarray       Ljava/lang/Class;
        //   178: dup            
        //   179: iconst_0       
        //   180: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //   182: aastore        
        //   183: dup            
        //   184: iconst_1       
        //   185: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   187: aastore        
        //   188: dup            
        //   189: iconst_2       
        //   190: ldc             Lcom/jetbrains/cidr/lang/psi/OCStructLike;.class
        //   192: aastore        
        //   193: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   196: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   199: astore_1       
        //   200: aload_1        
        //   201: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   204: ifeq            253
        //   207: aload_1        
        //   208: iconst_0       
        //   209: iconst_1       
        //   210: anewarray       Ljava/lang/Class;
        //   213: dup            
        //   214: iconst_0       
        //   215: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   217: aastore        
        //   218: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   221: ifnull          253
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload_1        
        //   232: iconst_0       
        //   233: iconst_1       
        //   234: anewarray       Ljava/lang/Class;
        //   237: dup            
        //   238: iconst_0       
        //   239: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   241: aastore        
        //   242: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   245: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   248: areturn        
        //   249: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload_1        
        //   254: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   257: ifeq            293
        //   260: aload_1        
        //   261: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   264: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   269: ifnonnull       293
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: aload_1        
        //   280: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   285: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   288: areturn        
        //   289: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_1        
        //   294: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   297: ifeq            311
        //   300: aload_1        
        //   301: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   304: goto            312
        //   307: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: aconst_null    
        //   312: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  71     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     109    112    116    Ljava/lang/IllegalArgumentException;
        //  92     142    145    149    Ljava/lang/IllegalArgumentException;
        //  116    168    168    172    Ljava/lang/IllegalArgumentException;
        //  200    224    227    231    Ljava/lang/IllegalArgumentException;
        //  207    249    249    253    Ljava/lang/IllegalArgumentException;
        //  253    272    275    279    Ljava/lang/IllegalArgumentException;
        //  260    289    289    293    Ljava/lang/IllegalArgumentException;
        //  293    307    307    311    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    private static OCVisibility a(final OCStructSymbol p0, final OCStructSymbol p1, @NotNull final OCSymbol p2, final OCVisibility p3, @NotNull final OCResolveContext p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbolToFind"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "searchCpp"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "context"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "searchCpp"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: new             Ljava/util/HashSet;
        //    92: dup            
        //    93: invokespecial   java/util/HashSet.<init>:()V
        //    96: astore          5
        //    98: new             Ljava/util/Stack;
        //   101: dup            
        //   102: invokespecial   java/util/Stack.<init>:()V
        //   105: astore          6
        //   107: aload_0        
        //   108: ifnull          232
        //   111: aload_0        
        //   112: aload_1        
        //   113: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   116: ifeq            173
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_3        
        //   127: dup            
        //   128: ifnonnull       172
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: new             Ljava/lang/IllegalStateException;
        //   141: dup            
        //   142: ldc             "@NotNull method %s.%s must not return null"
        //   144: ldc             2
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "searchCpp"
        //   160: aastore        
        //   161: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   164: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   167: athrow         
        //   168: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: areturn        
        //   173: aload           6
        //   175: aload_0        
        //   176: aload_3        
        //   177: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   180: if_acmpne       193
        //   183: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   186: goto            194
        //   189: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: aconst_null    
        //   194: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   197: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   200: pop            
        //   201: aload_0        
        //   202: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   205: astore          7
        //   207: aload           7
        //   209: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   212: ifeq            227
        //   215: aload           7
        //   217: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   220: goto            228
        //   223: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aconst_null    
        //   228: astore_0       
        //   229: goto            107
        //   232: aload_3        
        //   233: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PROTECTED:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   236: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.min:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   239: astore_3       
        //   240: iconst_0       
        //   241: istore          7
        //   243: aload           6
        //   245: invokevirtual   java/util/Stack.isEmpty:()Z
        //   248: ifne            556
        //   251: aload           6
        //   253: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   256: checkcast       Lcom/intellij/openapi/util/Pair;
        //   259: astore          8
        //   261: aload           8
        //   263: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   266: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   269: astore          9
        //   271: aload           8
        //   273: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   276: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   279: astore          10
        //   281: aload           9
        //   283: aload_1        
        //   284: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.resolvedNamesEqual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Z
        //   287: ifeq            364
        //   290: iconst_1       
        //   291: istore          7
        //   293: aload           10
        //   295: ifnull          317
        //   298: aload           10
        //   300: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   303: aload_3        
        //   304: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   307: if_icmpgt       364
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: aload_3        
        //   318: dup            
        //   319: ifnonnull       363
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: new             Ljava/lang/IllegalStateException;
        //   332: dup            
        //   333: ldc             "@NotNull method %s.%s must not return null"
        //   335: ldc             2
        //   337: anewarray       Ljava/lang/Object;
        //   340: dup            
        //   341: ldc             0
        //   343: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   345: aastore        
        //   346: dup            
        //   347: ldc             1
        //   349: ldc             "searchCpp"
        //   351: aastore        
        //   352: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   355: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   358: athrow         
        //   359: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: areturn        
        //   364: aload           5
        //   366: aload           9
        //   368: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   373: ifeq            383
        //   376: goto            243
        //   379: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   382: athrow         
        //   383: iconst_1       
        //   384: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   387: astore          11
        //   389: aload           9
        //   391: aload_2        
        //   392: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   397: aload           4
        //   399: aload_2        
        //   400: aload           11
        //   402: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;[Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/intellij/util/Processor;
        //   407: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   410: pop            
        //   411: aload           11
        //   413: iconst_0       
        //   414: aaload         
        //   415: ifnull          488
        //   418: aload           11
        //   420: iconst_0       
        //   421: aaload         
        //   422: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   425: aload_3        
        //   426: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   429: if_icmpgt       488
        //   432: goto            439
        //   435: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   438: athrow         
        //   439: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   442: dup            
        //   443: ifnonnull       487
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: new             Ljava/lang/IllegalStateException;
        //   456: dup            
        //   457: ldc             "@NotNull method %s.%s must not return null"
        //   459: ldc             2
        //   461: anewarray       Ljava/lang/Object;
        //   464: dup            
        //   465: ldc             0
        //   467: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   469: aastore        
        //   470: dup            
        //   471: ldc             1
        //   473: ldc             "searchCpp"
        //   475: aastore        
        //   476: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   479: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   482: athrow         
        //   483: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: areturn        
        //   488: aload           5
        //   490: aload           9
        //   492: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   497: pop            
        //   498: aload           9
        //   500: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   503: astore          12
        //   505: aload           12
        //   507: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   510: ifeq            536
        //   513: aload           6
        //   515: aload           12
        //   517: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   520: aload           10
        //   522: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   525: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   528: pop            
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   535: athrow         
        //   536: aload           9
        //   538: aload           4
        //   540: aload           10
        //   542: aload           6
        //   544: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Ljava/util/Stack;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   549: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   552: pop            
        //   553: goto            243
        //   556: iload           7
        //   558: ifeq            571
        //   561: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   564: goto            574
        //   567: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   570: athrow         
        //   571: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   574: dup            
        //   575: ifnonnull       612
        //   578: new             Ljava/lang/IllegalStateException;
        //   581: dup            
        //   582: ldc             "@NotNull method %s.%s must not return null"
        //   584: ldc             2
        //   586: anewarray       Ljava/lang/Object;
        //   589: dup            
        //   590: ldc             0
        //   592: ldc             "com/jetbrains/cidr/lang/symbols/OCVisibility"
        //   594: aastore        
        //   595: dup            
        //   596: ldc             1
        //   598: ldc             "searchCpp"
        //   600: aastore        
        //   601: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   604: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   607: athrow         
        //   608: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   611: athrow         
        //   612: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  107    119    122    126    Ljava/lang/IllegalArgumentException;
        //  111    131    134    138    Ljava/lang/IllegalArgumentException;
        //  126    168    168    172    Ljava/lang/IllegalArgumentException;
        //  173    189    189    193    Ljava/lang/IllegalArgumentException;
        //  207    223    223    227    Ljava/lang/IllegalArgumentException;
        //  293    310    313    317    Ljava/lang/IllegalArgumentException;
        //  298    322    325    329    Ljava/lang/IllegalArgumentException;
        //  317    359    359    363    Ljava/lang/IllegalArgumentException;
        //  364    379    379    383    Ljava/lang/IllegalArgumentException;
        //  389    432    435    439    Ljava/lang/IllegalArgumentException;
        //  418    446    449    453    Ljava/lang/IllegalArgumentException;
        //  439    483    483    487    Ljava/lang/IllegalArgumentException;
        //  505    529    532    536    Ljava/lang/IllegalArgumentException;
        //  556    567    567    571    Ljava/lang/IllegalArgumentException;
        //  574    608    608    612    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    public static OCVisibility getMaxInheritanceVisibility(final OCStructSymbol ocStructSymbol, final OCStructSymbol ocStructSymbol2, final PsiFile psiFile) {
        final HashSet<OCStructSymbol> set = new HashSet<OCStructSymbol>();
        final Stack<Pair> stack = new Stack<Pair>();
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)psiFile);
        stack.push(Pair.create((Object)ocStructSymbol2, (Object)OCVisibility.PUBLIC));
        OCVisibility ocVisibility2 = OCVisibility.PRIVATE;
        while (!stack.isEmpty()) {
            final Pair pair = stack.pop();
            final OCStructSymbol ocStructSymbol4 = (OCStructSymbol)pair.first;
            final OCVisibility ocVisibility3 = (OCVisibility)pair.second;
            if (ocStructSymbol4.equals(ocStructSymbol)) {
                ocVisibility2 = min(ocVisibility2, ocVisibility3);
            }
            try {
                if (!set.add(ocStructSymbol4)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final Stack<Pair> stack2;
            final OCVisibility ocVisibility4;
            ocStructSymbol4.processBaseClasses(ocResolveContext, (ocStructSymbol3, ocVisibility) -> {
                try {
                    if (ocStructSymbol3 instanceof OCStructSymbol) {
                        stack2.push(Pair.create((Object)ocStructSymbol3, (Object)max(ocVisibility4, ocVisibility)));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return true;
            });
        }
        return ocVisibility2;
    }
    
    @Nullable
    public static OCVisibility getVisibilityAtOffset(@NotNull final PsiElement psiElement, final int n) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCVisibility", "getVisibilityAtOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiElement instanceof OCStructLike)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCStructLike ocStructLike = (OCStructLike)psiElement;
        PsiElement psiElement2 = psiElement.getContainingFile().findElementAt(n);
        while (true) {
            try {
                if (psiElement2 == null || psiElement2.getParent() == psiElement) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            psiElement2 = psiElement2.getParent();
        }
        if (psiElement2 != null) {
            psiElement2 = psiElement2.getPrevSibling();
        }
        while (psiElement2 != null) {
            final OCVisibility visibilityFromElement = getVisibilityFromElement(psiElement2);
            try {
                if (visibilityFromElement != null) {
                    return visibilityFromElement;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            psiElement2 = psiElement2.getPrevSibling();
        }
        return ocStructLike.getDefaultVisibility();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class InnerFriendFinder implements Processor<OCSymbol>
    {
        private final MultiMap<String, OCSymbol> ourContext;
        
        public InnerFriendFinder(final MultiMap<String, OCSymbol> ourContext) {
            this.ourContext = ourContext;
        }
        
        public boolean process(final OCSymbol ocSymbol) {
            if (ocSymbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)ocSymbol).isFriend()) {
                for (final OCSymbol ocSymbol2 : this.ourContext.get((Object)ocSymbol.getName())) {
                    boolean b = false;
                    if (((OCFunctionSymbol)ocSymbol).getQualifier() != null) {
                        for (final OCSymbol ocSymbol3 : OCSymbolReference.getGlobalReference(((OCFunctionSymbol)ocSymbol).getQualifiedName(), ((OCFunctionSymbol)ocSymbol).getParent()).resolveToSymbols(true, false, (PsiFile)ocSymbol.getContainingOCFile())) {
                            final OCQualifiedName resolvedQualifiedName = ((OCFunctionSymbol)ocSymbol2).getResolvedQualifiedName();
                            if (ocSymbol3 instanceof OCFunctionSymbol && Comparing.equal((Object)((OCFunctionSymbol)ocSymbol3).getResolvedQualifiedName(), (Object)resolvedQualifiedName)) {
                                b = true;
                                break;
                            }
                        }
                    }
                    else {
                        b = true;
                    }
                    if (b && new OCTypeEqualityVisitor(ocSymbol.getResolvedType(), true, true, true, true, new OCResolveContext()).equal(ocSymbol2.getResolvedType())) {
                        return false;
                    }
                }
            }
            else if (ocSymbol instanceof OCStructSymbol) {
                if (((OCStructSymbol)ocSymbol).isFriend()) {
                    final Iterator<OCSymbol> iterator3 = this.ourContext.get((Object)ocSymbol.getName()).iterator();
                    if (iterator3.hasNext()) {
                        final OCSymbol ocSymbol4 = iterator3.next();
                        return false;
                    }
                }
                if (!((OCStructSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)this)) {
                    return false;
                }
            }
            return true;
        }
    }
}
