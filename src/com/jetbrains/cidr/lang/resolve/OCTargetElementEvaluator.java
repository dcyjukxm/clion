// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.util.ThreeState;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.TargetElementEvaluatorEx2;

public class OCTargetElementEvaluator extends TargetElementEvaluatorEx2
{
    public static final int DECLARATOR_AS_CONSTRUCTOR_REFERENCE = 4096;
    
    @Override
    public PsiElement getNamedElement(@NotNull final PsiElement p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getNamedElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    50: astore_2       
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    55: ifeq            84
        //    58: aload_2        
        //    59: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    62: astore_3       
        //    63: aload_1        
        //    64: aload_3        
        //    65: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.findParameterNode:()Lcom/intellij/lang/ASTNode;
        //    70: if_acmpeq       84
        //    73: aload_3        
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getParent:()Lcom/intellij/psi/PsiElement;
        //    79: areturn        
        //    80: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_2        
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    88: ifne            133
        //    91: aload_2        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    95: ifne            133
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_2        
        //   106: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceAlias;
        //   109: ifne            133
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_2        
        //   120: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeParameterDeclaration;
        //   123: ifeq            159
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: aload_2        
        //   135: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   138: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   143: if_acmpne       159
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_2        
        //   154: areturn        
        //   155: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_1        
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   163: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   166: if_acmpne       189
        //   169: aload_2        
        //   170: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   173: ifeq            189
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_2        
        //   184: areturn        
        //   185: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: aload_2        
        //   190: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDefineDirective;
        //   193: ifne            210
        //   196: aload_2        
        //   197: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCategoryName;
        //   200: ifeq            216
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload_2        
        //   211: areturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aconst_null    
        //   217: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  63     80     80     84     Ljava/lang/IllegalArgumentException;
        //  84     98     101    105    Ljava/lang/IllegalArgumentException;
        //  91     112    115    119    Ljava/lang/IllegalArgumentException;
        //  105    126    129    133    Ljava/lang/IllegalArgumentException;
        //  119    146    149    153    Ljava/lang/IllegalArgumentException;
        //  133    155    155    159    Ljava/lang/IllegalArgumentException;
        //  159    176    179    183    Ljava/lang/IllegalArgumentException;
        //  169    185    185    189    Ljava/lang/IllegalArgumentException;
        //  189    203    206    210    Ljava/lang/IllegalArgumentException;
        //  196    212    212    216    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0105:
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
    @Override
    public ThreeState isAcceptableReferencedElement(@NotNull final PsiElement psiElement, final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "isAcceptableReferencedElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ThreeState unsure = null;
        Label_0100: {
            ThreeState threeState = null;
            Label_0065: {
                try {
                    if (!(psiElement2 instanceof OCSymbolHolderVirtualPsiElement)) {
                        break Label_0100;
                    }
                    threeState = ThreeState.YES;
                    if (threeState == null) {
                        break Label_0065;
                    }
                    return threeState;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    threeState = ThreeState.YES;
                    if (threeState == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "isAcceptableReferencedElement"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            return threeState;
            try {
                unsure = ThreeState.UNSURE;
                if (unsure == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "isAcceptableReferencedElement"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return unsure;
    }
    
    @Override
    public boolean isAcceptableNamedParent(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "isAcceptableNamedParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0080: {
            Label_0070: {
                try {
                    if (!(psiElement instanceof OCStructLike)) {
                        break Label_0080;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCStructLike ocStructLike = (OCStructLike)psiElement2;
                    final String s = ocStructLike.getName();
                    if (s != null) {
                        break Label_0070;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCStructLike ocStructLike = (OCStructLike)psiElement2;
                    final String s = ocStructLike.getName();
                    if (s != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            return false;
            try {
                if (psiElement instanceof OCCppNamespace) {
                    return false;
                }
                final PsiElement psiElement3 = psiElement;
                final boolean b2 = psiElement3 instanceof OCCppNamespaceAlias;
                if (b2) {
                    return false;
                }
                return super.isAcceptableNamedParent(psiElement);
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        try {
            final PsiElement psiElement3 = psiElement;
            final boolean b2 = psiElement3 instanceof OCCppNamespaceAlias;
            if (b2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return super.isAcceptableNamedParent(psiElement);
    }
    
    @Override
    public PsiElement getElementByReference(@NotNull final PsiReference psiReference, final int n) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "getElementByReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCSymbol<PsiElement> ocSymbol2 = null;
        Label_0165: {
            Label_0132: {
                OCSymbol ocSymbol = null;
                Label_0128: {
                    Label_0103: {
                        Label_0082: {
                            Label_0071: {
                                try {
                                    if ((n & 0x1000) != 0x0) {
                                        break Label_0082;
                                    }
                                    final PsiReference psiReference2 = psiReference;
                                    final PsiElement psiElement = psiReference2.getElement();
                                    final boolean b = psiElement instanceof OCDeclarator;
                                    if (b) {
                                        break Label_0071;
                                    }
                                    break Label_0082;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw b(ex2);
                                }
                                try {
                                    final PsiReference psiReference2 = psiReference;
                                    final PsiElement psiElement = psiReference2.getElement();
                                    final boolean b = psiElement instanceof OCDeclarator;
                                    if (b) {
                                        return psiReference.getElement();
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw b(ex3);
                                }
                            }
                            try {
                                if (!(psiReference instanceof OCReference)) {
                                    break Label_0132;
                                }
                                final PsiReference psiReference3 = psiReference;
                                final boolean b2 = psiReference3 instanceof OCReferenceElement;
                                if (b2) {
                                    break Label_0103;
                                }
                                break Label_0103;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                        }
                        try {
                            final PsiReference psiReference3 = psiReference;
                            final boolean b2 = psiReference3 instanceof OCReferenceElement;
                            if (b2) {
                                ocSymbol = ((OCReferenceElement)psiReference).resolveToSymbolIgnoringSymbolContext();
                                break Label_0128;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                    }
                    ocSymbol = ((OCReference)psiReference).resolveToSymbol();
                }
                ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                break Label_0165;
            }
            if (!(psiReference instanceof OCPolyVariantReference)) {
                return psiReference.resolve();
            }
            ocSymbol2 = (OCSymbol<PsiElement>)ContainerUtil.getFirstItem((List)((OCPolyVariantReference)psiReference).resolveToSymbols());
            try {
                if (ocSymbol2 instanceof OCResolveOverloadsUtil.OCFunctionGroupSymbol) {
                    return (PsiElement)new OCSymbolHolderVirtualPsiElement(ocSymbol2);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        try {
            if (ocSymbol2 != null) {
                return ocSymbol2.locateDefinition();
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return null;
    }
    
    @Override
    public boolean includeSelfInGotoImplementation(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCTargetElementEvaluator", "includeSelfInGotoImplementation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
