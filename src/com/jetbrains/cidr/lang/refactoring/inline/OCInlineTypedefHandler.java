// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeCloneVisitor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCNormalizeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCInlineTypedefHandler extends OCInlineActionHandlerBase<OCDeclarator>
{
    @Override
    protected String getElementKind(final OCDeclarator ocDeclarator) {
        return "typedef";
    }
    
    public boolean canInlineElement(final PsiElement psiElement) {
        Label_0031: {
            try {
                if (!(psiElement instanceof OCDeclarator)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final OCDeclaration ocDeclaration = (OCDeclaration)psiElement3;
                final boolean b = ocDeclaration.isTypedef();
                if (b) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final OCDeclaration ocDeclaration = (OCDeclaration)psiElement3;
                final boolean b = ocDeclaration.isTypedef();
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected String checkUsageValid(final PsiElement p0, final OCDeclarator p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //     6: astore_3       
        //     7: aload_1        
        //     8: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //    10: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    16: astore          4
        //    18: aload           4
        //    20: ifnull          67
        //    23: aload           4
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    30: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    33: ifeq            67
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineTypedefHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: aload_3        
        //    44: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    47: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    50: ifeq            67
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineTypedefHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    59: athrow         
        //    60: ldc             "Can't inline the function type in the return type of another function"
        //    62: areturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineTypedefHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    66: athrow         
        //    67: aconst_null    
        //    68: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  18     36     39     43     Ljava/lang/IllegalStateException;
        //  23     53     56     60     Ljava/lang/IllegalStateException;
        //  43     63     63     67     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    @Override
    protected void inlineUsage(final PsiElement psiElement, final OCDeclarator ocDeclarator, final PsiElement psiElement2, final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        if (psiElement instanceof OCCppNamespaceQualifier) {
            OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)OCElementFactory.declarationFromText("int " + ocDeclarator.getType().getCanonicalName(psiElement) + "::x;", psiElement).getDeclarators().get(0).getNamespaceQualifier());
            return;
        }
        try {
            if (!(psiElement instanceof OCReferenceElement)) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCTypeElement ocTypeElement = (OCTypeElement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCTypeElement.class);
        final OCSymbol symbol = ocDeclarator.getSymbol();
        final OCType type = ocDeclarator.getType();
        Label_0134: {
            try {
                if (ocTypeElement == null) {
                    return;
                }
                final OCSymbol ocSymbol = symbol;
                if (ocSymbol == null) {
                    return;
                }
                break Label_0134;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                if (ocSymbol == null) {
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        if (ocTypeElement.getParent() instanceof OCDeclaration) {
            for (final OCDeclaration ocDeclaration : OCNormalizeUtil.normalizeDeclaration((OCDeclaration)ocTypeElement.getParent())) {
                final OCDeclarator ocDeclarator2 = ocDeclaration.getDeclarators().get(0);
                final OCDeclaration declaration = OCElementFactory.declarationStatement(ocDeclarator2.getName(), a(symbol, type, ocDeclarator2.getType(), ocDeclarator.getContainingFile()), ocDeclarator2.getInitializer(), (PsiElement)ocDeclarator2).getDeclaration();
                OCElementUtil.replaceDeclarationQualifiers((PsiElement)declaration, (PsiElement)ocDeclaration);
                OCElementUtil.replaceDeclarationQualifiers((PsiElement)declaration.getTypeElement(), (PsiElement)ocDeclaration.getTypeElement());
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocDeclaration, (PsiElement)declaration);
            }
        }
        else {
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocTypeElement, (PsiElement)OCElementFactory.typeElementFromText(a(symbol, type, ocTypeElement.getType(), ocTypeElement.getContainingFile()).getBestNameInContext(psiElement), psiElement));
        }
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineTypedef";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineTypedefHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static OCType a(final OCSymbol ocSymbol, final OCType ocType, final OCType ocType2, final PsiFile psiFile) {
        return ocType2.transformType(new OCTypeCloneVisitor(false) {
            @Override
            public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
                if (ocReferenceType.getReference(psiFile).resolveToSymbols(psiFile).contains(ocSymbol)) {
                    return ocReferenceType.isConst() ? ocType.cloneWithConstModifier(ocSymbol.getProject()) : ocType;
                }
                return super.visitReferenceType(ocReferenceType);
            }
        });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
