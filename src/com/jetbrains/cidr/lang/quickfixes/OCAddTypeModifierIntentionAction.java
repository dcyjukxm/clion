// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.List;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.Processor;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.lang.ASTNode;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCAddTypeModifierIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private OCElementType myModifier;
    private String mySubject;
    private boolean myProcessSameSymbols;
    
    public OCAddTypeModifierIntentionAction(@Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCElementType myModifier, @Nullable final String mySubject, final boolean myProcessSameSymbols) {
        super(ocSymbolWithQualifiedName);
        this.myProcessSameSymbols = true;
        this.myModifier = myModifier;
        this.mySubject = mySubject;
        this.myProcessSameSymbols = myProcessSameSymbols;
    }
    
    public OCAddTypeModifierIntentionAction(@Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCElementType ocElementType, final boolean b) {
        this(ocSymbolWithQualifiedName, ocElementType, (ocSymbolWithQualifiedName != null) ? ocSymbolWithQualifiedName.getNameWithKindLowercase() : null, b);
    }
    
    public OCAddTypeModifierIntentionAction(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCElementType ocElementType) {
        this(ocSymbolWithQualifiedName, ocElementType, (ocSymbolWithQualifiedName != null) ? ocSymbolWithQualifiedName.getNameWithKindLowercase() : null, true);
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return "Make " + this.mySubject + " " + this.myModifier.getName();
    }
    
    @NotNull
    public String getFamilyName() {
        String string;
        try {
            string = "Add '" + this.myModifier.getName() + "'";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
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
        //    22: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: iconst_0       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    39: if_acmpne       84
        //    42: aload_1        
        //    43: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConst:()Z
        //    49: ifne            78
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    58: athrow         
        //    59: aload_1        
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    65: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    68: if_acmpne       84
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: iconst_0       
        //    79: ireturn        
        //    80: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    83: athrow         
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    88: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    91: if_acmpne       136
        //    94: aload_1        
        //    95: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    98: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isBlockModifiable:()Z
        //   101: ifne            130
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   110: athrow         
        //   111: aload_1        
        //   112: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   117: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   120: if_acmpeq       136
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   129: athrow         
        //   130: iconst_0       
        //   131: ireturn        
        //   132: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   135: athrow         
        //   136: aload_0        
        //   137: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   140: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VIRTUAL_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   143: if_acmpne       169
        //   146: aload_1        
        //   147: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   150: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
        //   153: ifeq            169
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   162: athrow         
        //   163: iconst_0       
        //   164: ireturn        
        //   165: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   168: athrow         
        //   169: iconst_1       
        //   170: ireturn        
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
        //  94     123    126    130    Ljava/lang/IllegalStateException;
        //  111    132    132    136    Ljava/lang/IllegalStateException;
        //  136    156    159    163    Ljava/lang/IllegalStateException;
        //  146    165    165    169    Ljava/lang/IllegalStateException;
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
        final ArrayList<ASTNode> list = (ArrayList<ASTNode>)new ArrayList<Object>();
        final Processor processor = p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
            //     6: astore_3       
            //     7: aload_3        
            //     8: ifnull          30
            //    11: aload_3        
            //    12: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //    17: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
            //    20: ifne            36
            //    23: goto            30
            //    26: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    29: athrow         
            //    30: iconst_1       
            //    31: ireturn        
            //    32: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    35: athrow         
            //    36: aload_3        
            //    37: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //    42: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
            //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
            //    50: astore          4
            //    52: aload           4
            //    54: ifnonnull       63
            //    57: iconst_1       
            //    58: ireturn        
            //    59: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    62: athrow         
            //    63: aload           4
            //    65: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getNode:()Lcom/intellij/lang/ASTNode;
            //    70: astore          5
            //    72: aload           5
            //    74: ifnull          131
            //    77: aload           5
            //    79: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
            //    84: ifnull          131
            //    87: goto            94
            //    90: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    93: athrow         
            //    94: aload           5
            //    96: aload_0        
            //    97: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.myModifier:Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //   100: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
            //   105: ifnonnull       131
            //   108: goto            115
            //   111: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   114: athrow         
            //   115: aload_1        
            //   116: aload           5
            //   118: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   123: pop            
            //   124: goto            131
            //   127: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddTypeModifierIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   130: athrow         
            //   131: iconst_1       
            //   132: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  7      23     26     30     Ljava/lang/IllegalStateException;
            //  11     32     32     36     Ljava/lang/IllegalStateException;
            //  52     59     59     63     Ljava/lang/IllegalStateException;
            //  72     87     90     94     Ljava/lang/IllegalStateException;
            //  77     108    111    115    Ljava/lang/IllegalStateException;
            //  94     124    127    131    Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0094:
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
        };
        Label_0046: {
            try {
                if (this.myProcessSameSymbols) {
                    ocSymbol.processSameSymbols(processor);
                    break Label_0046;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            processor.process((Object)ocSymbol);
        }
        final OCFile containingOCFile = ocSymbol.getContainingOCFile();
        for (final ASTNode parent : list) {
            parent.addChild(OCElementFactory.spaceFromText((PsiElement)containingOCFile).getNode(), parent.getFirstChildNode());
            CodeEditUtil.addChild(parent, OCElementFactory.typeModifierFromText(this.myModifier.getName(), (PsiElement)containingOCFile), parent.getFirstChildNode());
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
