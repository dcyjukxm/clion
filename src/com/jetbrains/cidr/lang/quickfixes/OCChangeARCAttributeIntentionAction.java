// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCChangeARCAttributeIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private ARCAttribute myAttribute;
    
    public OCChangeARCAttributeIntentionAction(final OCSymbol ocSymbol, final ARCAttribute myAttribute) {
        super(ocSymbol);
        this.myAttribute = myAttribute;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return "Make " + ocSymbol.getNameWithKindLowercase() + " " + this.myAttribute.getTokenName();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change ARC attribute";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return OCSearchScope.isInProjectSources(ocSymbol);
    }
    
    @Override
    protected void invoke(final OCSymbol ocSymbol) {
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getParentOfType(locateDefinition, (Class)OCDeclaration.class);
        try {
            if (ocDeclaration == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCTypeElement typeElement = ocDeclaration.getTypeElement();
        try {
            if (typeElement == null) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        a((PsiElement)typeElement);
        a(locateDefinition);
        OCChangeUtil.changeText(ocDeclaration.getProject(), ocDeclaration.getContainingFile(), ocDeclaration.getTextOffset(), 0, this.myAttribute.getTokenName() + " ", true);
    }
    
    private static void a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //     6: aconst_null    
        //     7: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //    12: astore_1       
        //    13: aload_1        
        //    14: arraylength    
        //    15: istore_2       
        //    16: iconst_0       
        //    17: istore_3       
        //    18: iload_3        
        //    19: iload_2        
        //    20: if_icmpge       113
        //    23: aload_1        
        //    24: iload_3        
        //    25: aaload         
        //    26: astore          4
        //    28: aload           4
        //    30: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    35: astore          5
        //    37: aload           5
        //    39: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRONG_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    42: if_acmpeq       90
        //    45: aload           5
        //    47: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WEAK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    50: if_acmpeq       90
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    59: athrow         
        //    60: aload           5
        //    62: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNSAFE_UNRETAINED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    65: if_acmpeq       90
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    74: athrow         
        //    75: aload           5
        //    77: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTORELEASING_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    80: if_acmpne       107
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    89: athrow         
        //    90: aload           4
        //    92: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeARCAttributeIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   106: athrow         
        //   107: iinc            3, 1
        //   110: goto            18
        //   113: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  37     53     56     60     Ljava/lang/IllegalStateException;
        //  45     68     71     75     Ljava/lang/IllegalStateException;
        //  60     83     86     90     Ljava/lang/IllegalStateException;
        //  75     100    103    107    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
