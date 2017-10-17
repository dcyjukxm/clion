// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import java.util.Collections;
import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.navigation.GotoRelatedItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.GotoRelatedProvider;

public class OCSwitchToHeaderOrSourceRelatedProvider extends GotoRelatedProvider
{
    @NotNull
    public List<? extends GotoRelatedItem> getItems(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider", "getItems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        OCFile associatedFile = null;
        Label_0075: {
            try {
                if (containingFile instanceof OCFile) {
                    associatedFile = ((OCFile)containingFile).getAssociatedFile();
                    break Label_0075;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            associatedFile = null;
        }
        final OCFile ocFile = associatedFile;
        final NavigatablePsiElement a = a(psiElement, ocFile);
        List<? extends GotoRelatedItem> emptyList = null;
        Label_0249: {
            GotoRelatedItem gotoRelatedItem2 = null;
            OCFile ocFile3 = null;
            String s2 = null;
            Label_0208: {
                Label_0197: {
                    Label_0172: {
                        GotoRelatedItem gotoRelatedItem = null;
                        OCSwitchToHeaderOrSourceRelatedProvider ocSwitchToHeaderOrSourceRelatedProvider = null;
                        NavigatablePsiElement navigatablePsiElement = null;
                        String s = null;
                        Label_0129: {
                            Label_0127: {
                                Label_0106: {
                                    try {
                                        if (a == null) {
                                            break Label_0172;
                                        }
                                        gotoRelatedItem = new(com.jetbrains.cidr.lang.navigation.OCSwitchToHeaderOrSourceRelatedProvider.OCSwitchToHeaderOrSourceRelatedProvider$1.class);
                                        ocSwitchToHeaderOrSourceRelatedProvider = this;
                                        navigatablePsiElement = a;
                                        final OCFile ocFile2 = ocFile;
                                        if (ocFile2 != null) {
                                            break Label_0106;
                                        }
                                        break Label_0127;
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                    try {
                                        gotoRelatedItem = new(com.jetbrains.cidr.lang.navigation.OCSwitchToHeaderOrSourceRelatedProvider.OCSwitchToHeaderOrSourceRelatedProvider$1.class);
                                        ocSwitchToHeaderOrSourceRelatedProvider = this;
                                        navigatablePsiElement = a;
                                        final OCFile ocFile2 = ocFile;
                                        if (ocFile2 == null) {
                                            break Label_0127;
                                        }
                                        if (!ocFile.isHeader()) {
                                            break Label_0127;
                                        }
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                }
                                s = "Headers";
                                break Label_0129;
                            }
                            s = "Sources";
                        }
                        new GotoRelatedItem(s) {
                            public void navigate() {
                                NavigationUtil.activateFileWithPsiElement((PsiElement)a);
                            }
                        };
                        final List<? extends GotoRelatedItem> singletonList = Collections.singletonList((GotoRelatedItem)gotoRelatedItem);
                        if (singletonList == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider", "getItems"));
                        }
                        return singletonList;
                        try {
                            if (ocFile == null) {
                                break Label_0249;
                            }
                            gotoRelatedItem2 = new(com.intellij.navigation.GotoRelatedItem.class);
                            ocFile3 = ocFile;
                            final OCFile ocFile4 = ocFile;
                            final boolean b = ocFile4.isHeader();
                            if (b) {
                                break Label_0197;
                            }
                            break Label_0197;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    try {
                        gotoRelatedItem2 = new(com.intellij.navigation.GotoRelatedItem.class);
                        ocFile3 = ocFile;
                        final OCFile ocFile4 = ocFile;
                        final boolean b = ocFile4.isHeader();
                        if (b) {
                            s2 = "Headers";
                            break Label_0208;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                s2 = "Sources";
            }
            new GotoRelatedItem((PsiElement)ocFile3, s2);
            final List<? extends GotoRelatedItem> singletonList2 = Collections.singletonList(gotoRelatedItem2);
            if (singletonList2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider", "getItems"));
            }
            return singletonList2;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider", "getItems"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return emptyList;
    }
    
    @Nullable
    private static NavigatablePsiElement b(final PsiElement p0, final OCFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //     4: ifeq            21
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    11: ifeq            27
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aconst_null    
        //    22: areturn        
        //    23: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    36: astore_2       
        //    37: aload_2        
        //    38: ifnull          54
        //    41: aload_2        
        //    42: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aconst_null    
        //    55: astore_3       
        //    56: aload_3        
        //    57: ifnull          105
        //    60: aload_1        
        //    61: ifnull          91
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_1        
        //    72: aload_3        
        //    73: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    78: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    81: ifeq            105
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_3        
        //    92: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    97: checkcast       Lcom/intellij/psi/NavigatablePsiElement;
        //   100: areturn        
        //   101: invokestatic    com/jetbrains/cidr/lang/navigation/OCSwitchToHeaderOrSourceRelatedProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aconst_null    
        //   106: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      23     23     27     Ljava/lang/IllegalArgumentException;
        //  37     50     50     54     Ljava/lang/IllegalArgumentException;
        //  56     64     67     71     Ljava/lang/IllegalArgumentException;
        //  60     84     87     91     Ljava/lang/IllegalArgumentException;
        //  71     101    101    105    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
    private static NavigatablePsiElement a(PsiElement parent, final OCFile ocFile) {
        while (true) {
            try {
                if (parent == null || parent instanceof PsiFile) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final NavigatablePsiElement b = b(parent, ocFile);
            try {
                if (b != null) {
                    return b;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
