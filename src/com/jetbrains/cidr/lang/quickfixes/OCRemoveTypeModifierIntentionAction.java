// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.function.Function;
import java.util.List;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCRemoveTypeModifierIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private OCElementType myModifier;
    
    public OCRemoveTypeModifierIntentionAction(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCElementType myModifier) {
        super(ocSymbolWithQualifiedName);
        this.myModifier = myModifier;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return "Make " + this.getSubject(ocSymbol) + " non-" + this.myModifier.getName();
    }
    
    @NotNull
    public String getFamilyName() {
        String string;
        try {
            string = "Remove '" + this.myModifier.getName() + "'";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    protected String getSubject(final OCSymbol ocSymbol) {
        return ocSymbol.getNameWithKindLowercase();
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //     4: ifeq            26
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    13: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    16: ifne            32
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: iconst_0       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    39: if_acmpne       84
        //    42: aload_1        
        //    43: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConst:()Z
        //    49: ifeq            78
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    58: athrow         
        //    59: aload_1        
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    65: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    68: if_acmpne       84
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: iconst_0       
        //    79: ireturn        
        //    80: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    83: athrow         
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    88: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    91: if_acmpne       117
        //    94: aload_1        
        //    95: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    98: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isBlockModifiable:()Z
        //   101: ifne            117
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   110: athrow         
        //   111: iconst_0       
        //   112: ireturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: iconst_1       
        //   118: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      19     22     26     Ljava/lang/IllegalStateException;
        //  7      28     28     32     Ljava/lang/IllegalStateException;
        //  32     52     55     59     Ljava/lang/IllegalStateException;
        //  42     71     74     78     Ljava/lang/IllegalStateException;
        //  59     80     80     84     Ljava/lang/IllegalStateException;
        //  84     104    107    111    Ljava/lang/IllegalStateException;
        //  94     113    113    117    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    protected void invoke(final OCSymbol ocSymbol) {
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        ocSymbol.processSameSymbols((Processor)collectProcessor);
        final Iterator<Object> iterator = collectProcessor.getResults().stream().map((Function<? super Object, ?>)OCSymbol::locateDefinition).filter(Objects::nonNull).map((Function<? super Object, ?>)OCElementUtil::createPsiElementPointer).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()).iterator();
        while (iterator.hasNext()) {
            final PsiElement element = iterator.next().getElement();
            try {
                if (element == null) {
                    continue;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            if (!this.a(element) && element instanceof OCDeclarator) {
                try {
                    if (!(element.getParent() instanceof OCDeclaration)) {
                        continue;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                final OCTypeElement typeElement = ((OCDeclaration)element.getParent()).getTypeElement();
                Label_0193: {
                    try {
                        if (typeElement == null) {
                            continue;
                        }
                        final OCRemoveTypeModifierIntentionAction ocRemoveTypeModifierIntentionAction = this;
                        final OCTypeElement ocTypeElement = typeElement;
                        final boolean b = ocRemoveTypeModifierIntentionAction.a((PsiElement)ocTypeElement);
                        if (!b) {
                            break Label_0193;
                        }
                        continue;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCRemoveTypeModifierIntentionAction ocRemoveTypeModifierIntentionAction = this;
                        final OCTypeElement ocTypeElement = typeElement;
                        final boolean b = ocRemoveTypeModifierIntentionAction.a((PsiElement)ocTypeElement);
                        if (b) {
                            continue;
                        }
                        this.a(typeElement.getParent());
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
            }
        }
    }
    
    private boolean a(final PsiElement psiElement) {
        final ASTNode[] children = psiElement.getNode().getChildren((TokenSet)null);
        int n = children.length - 1;
        while (true) {
            Label_0044: {
                Label_0073: {
                    try {
                        if (n < 0) {
                            return false;
                        }
                        if (children[n].getElementType() != this.myModifier) {
                            break Label_0073;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    break Label_0044;
                }
                --n;
                continue;
            }
            final PsiElement psi = children[n].getPsi();
            try {
                if (psi != null) {
                    OCChangeUtil.delete(psi);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            return true;
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
