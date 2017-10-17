// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.refactoring.util.OCNormalizeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.intentions.OCDeclareMethodInInterfaceIntentionAction;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;

public class OCChangeVisibilityIntentionAction extends OCSymbolQuickFix<OCSymbolWithParent<?, ?>>
{
    private OCVisibility myNewVisibility;
    private OCDeclaration myNewDeclaration;
    
    public OCChangeVisibilityIntentionAction(@Nullable final OCSymbolWithParent ocSymbolWithParent, final OCVisibility myNewVisibility) {
        super(ocSymbolWithParent);
        this.myNewVisibility = myNewVisibility;
    }
    
    @Override
    protected String getTextInternal(final OCSymbolWithParent ocSymbolWithParent) {
        return "Make " + ocSymbolWithParent.getNameWithKindLowercase() + " " + this.myNewVisibility;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change visibility";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCSymbolWithParent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //     4: ifeq            62
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.myNewVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    11: ifnull          62
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    25: ifeq            54
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_1        
        //    36: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    39: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
        //    44: ifnonnull       62
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    53: athrow         
        //    54: iconst_1       
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: iconst_0       
        //    63: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     47     50     54     Ljava/lang/IllegalStateException;
        //  35     58     58     62     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public void invoke(final OCSymbolWithParent ocSymbolWithParent) {
        Label_0039: {
            Label_0025: {
                try {
                    if (!(ocSymbolWithParent instanceof OCMethodSymbol)) {
                        break Label_0039;
                    }
                    final OCSymbolWithParent<PsiElement, OCInterfaceSymbol> ocSymbolWithParent2 = (OCSymbolWithParent<PsiElement, OCInterfaceSymbol>)ocSymbolWithParent;
                    final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = this;
                    final OCVisibility ocVisibility = ocChangeVisibilityIntentionAction.myNewVisibility;
                    final boolean b = OCVisibility.shouldBeDeclaredInInterface(ocSymbolWithParent2, ocVisibility);
                    if (b) {
                        break Label_0025;
                    }
                    break Label_0039;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCSymbolWithParent<PsiElement, OCInterfaceSymbol> ocSymbolWithParent2 = (OCSymbolWithParent<PsiElement, OCInterfaceSymbol>)ocSymbolWithParent;
                    final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = this;
                    final OCVisibility ocVisibility = ocChangeVisibilityIntentionAction.myNewVisibility;
                    final boolean b = OCVisibility.shouldBeDeclaredInInterface(ocSymbolWithParent2, ocVisibility);
                    if (b) {
                        a((OCMethodSymbol)ocSymbolWithParent);
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (!(ocSymbolWithParent instanceof OCInstanceVariableSymbol)) {
                    if (!(ocSymbolWithParent instanceof OCSymbolWithQualifiedName)) {
                        return;
                    }
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        OCDeclaration a = this.a(ocSymbolWithParent.locateDefinition(), ocSymbolWithParent);
        Label_0310: {
            if (ocSymbolWithParent instanceof OCInstanceVariableSymbol) {
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbolWithParent.getAssociatedSymbol();
                if (ocInstanceVariableSymbol != null) {
                    final OCFile containingOCFile = ocInstanceVariableSymbol.getContainingOCFile();
                    Label_0129: {
                        try {
                            if (containingOCFile == null) {
                                break Label_0129;
                            }
                            final FileModificationService fileModificationService = FileModificationService.getInstance();
                            final OCFile ocFile = containingOCFile;
                            final boolean b2 = fileModificationService.prepareFileForWrite((PsiFile)ocFile);
                            if (b2) {
                                break Label_0129;
                            }
                            break Label_0129;
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final FileModificationService fileModificationService = FileModificationService.getInstance();
                            final OCFile ocFile = containingOCFile;
                            final boolean b2 = fileModificationService.prepareFileForWrite((PsiFile)ocFile);
                            if (b2) {
                                this.a(ocInstanceVariableSymbol.locateDefinition(), ocSymbolWithParent);
                            }
                        }
                        catch (IllegalStateException ex5) {
                            throw a(ex5);
                        }
                    }
                }
                else if (OCVisibility.shouldBeDeclaredInInterface(ocSymbolWithParent, this.myNewVisibility)) {
                    final OCInterfaceSymbol parent = ocSymbolWithParent.getParent();
                    OCInterfaceSymbol ocInterfaceSymbol = null;
                    Label_0204: {
                        try {
                            if (parent instanceof OCInterfaceSymbol) {
                                ocInterfaceSymbol = parent.getMainInterface();
                                break Label_0204;
                            }
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                        ocInterfaceSymbol = ((OCImplementationSymbol)parent).getInterface();
                    }
                    final OCInterfaceSymbol ocInterfaceSymbol2 = ocInterfaceSymbol;
                    try {
                        if (ocInterfaceSymbol2 == null || a == null) {
                            break Label_0310;
                        }
                    }
                    catch (IllegalStateException ex7) {
                        throw a(ex7);
                    }
                    final OCFile containingOCFile2 = ocInterfaceSymbol2.getContainingOCFile();
                    final OCClassDeclaration resolveClassDeclaration = OCElementUtil.resolveClassDeclaration(ocInterfaceSymbol2);
                    Label_0255: {
                        try {
                            if (resolveClassDeclaration == null) {
                                break Label_0310;
                            }
                            final OCFile ocFile2 = containingOCFile2;
                            if (ocFile2 != null) {
                                break Label_0255;
                            }
                            break Label_0310;
                        }
                        catch (IllegalStateException ex8) {
                            throw a(ex8);
                        }
                        try {
                            final OCFile ocFile2 = containingOCFile2;
                            if (ocFile2 == null) {
                                break Label_0310;
                            }
                            if (!FileModificationService.getInstance().prepareFileForWrite((PsiFile)containingOCFile2)) {
                                break Label_0310;
                            }
                        }
                        catch (IllegalStateException ex9) {
                            throw a(ex9);
                        }
                    }
                    final PsiElement psiElement = (PsiElement)a;
                    a = OCChangeUtil.add((PsiElement)resolveClassDeclaration.getInstanceVariablesList(), a);
                    this.putToParent(ocSymbolWithParent, a, (PsiElement)resolveClassDeclaration.getInstanceVariablesList());
                    OCChangeUtil.delete(psiElement);
                }
            }
        }
        this.myNewDeclaration = a;
    }
    
    public OCDeclaration getNewDeclaration() {
        return this.myNewDeclaration;
    }
    
    private static void a(final OCMethodSymbol ocMethodSymbol) {
        try {
            if (!ocMethodSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> {
                final OCFile containingOCFile = ocSymbol.getContainingOCFile();
                Label_0027: {
                    try {
                        if (containingOCFile == null) {
                            break Label_0027;
                        }
                        final OCFile ocFile = containingOCFile;
                        final boolean b = ocFile.isHeader();
                        if (!b) {
                            break Label_0027;
                        }
                        return false;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCFile ocFile = containingOCFile;
                        final boolean b = ocFile.isHeader();
                        if (!b) {
                            return true;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }))) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCMethodSymbol associatedSymbol = ocMethodSymbol.getAssociatedSymbol();
        PsiElement psiElement = null;
        OCMethodSymbol ocMethodSymbol2 = null;
        Label_0112: {
            if ("".equals(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getCategoryName())) {
                psiElement = ocMethodSymbol.locateDefinition();
                ocMethodSymbol2 = associatedSymbol;
            }
            else {
                Label_0110: {
                    try {
                        if (associatedSymbol == null || !"".equals(((OCSymbolWithParent<T, OCClassSymbol>)associatedSymbol).getParent().getCategoryName())) {
                            break Label_0110;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    psiElement = associatedSymbol.locateDefinition();
                    ocMethodSymbol2 = ocMethodSymbol;
                    break Label_0112;
                }
                ocMethodSymbol2 = ocMethodSymbol;
            }
        }
        final OCFile containingOCFile = ocMethodSymbol.getContainingOCFile();
        try {
            if (containingOCFile == null) {
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            new OCDeclareMethodInInterfaceIntentionAction() {
                @Nullable
                @Override
                protected OCMethodSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
                    try {
                        if (project == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "locateCandidate"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    return ocMethodSymbol2;
                }
                
                @Nullable
                protected OCClassSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
                    try {
                        if (project == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "getParent"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        if (psiFile == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "getParent"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                    try {
                        if (ocMethodSymbol2 != null) {
                            return ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol2).getParent();
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                    return null;
                }
                
                private static IllegalArgumentException c(final IllegalArgumentException ex) {
                    return ex;
                }
            }.invoke(containingOCFile.getProject(), null, (PsiFile)containingOCFile);
            if (psiElement != null) {
                OCChangeUtil.delete(psiElement);
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
    }
    
    @Nullable
    private OCDeclaration a(final PsiElement p0, final OCDeclaration p1, final PsiElement p2, final OCSymbol p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnull          50
        //     4: aload_3        
        //     5: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    10: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //    13: ifne            50
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    22: athrow         
        //    23: aload_3        
        //    24: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    30: if_acmpeq       50
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    39: athrow         
        //    40: aload_3        
        //    41: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    46: astore_3       
        //    47: goto            0
        //    50: aload_3        
        //    51: ifnull          185
        //    54: iload           5
        //    56: ifeq            165
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: aload           4
        //    68: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    71: ifeq            104
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    80: athrow         
        //    81: aload_1        
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.myNewVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    86: aload_1        
        //    87: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.ivarScopeSpecifier:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    90: aload_3        
        //    91: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    96: pop            
        //    97: goto            153
        //   100: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   103: athrow         
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.myNewVisibility:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   108: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.getElementType:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   111: astore          6
        //   113: aload           6
        //   115: ifnonnull       124
        //   118: aconst_null    
        //   119: areturn        
        //   120: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   123: athrow         
        //   124: aload_1        
        //   125: aload           6
        //   127: aload_1        
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   131: aload_3        
        //   132: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   137: pop            
        //   138: aload_1        
        //   139: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   142: aload_1        
        //   143: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   146: aload_3        
        //   147: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   152: pop            
        //   153: aload_1        
        //   154: aload_1        
        //   155: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   158: aload_3        
        //   159: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   164: pop            
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_3        
        //   168: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   173: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   176: astore          6
        //   178: aload_2        
        //   179: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   182: aload           6
        //   184: areturn        
        //   185: aconst_null    
        //   186: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      16     19     23     Ljava/lang/IllegalStateException;
        //  4      33     36     40     Ljava/lang/IllegalStateException;
        //  50     59     62     66     Ljava/lang/IllegalStateException;
        //  54     74     77     81     Ljava/lang/IllegalStateException;
        //  66     100    100    104    Ljava/lang/IllegalStateException;
        //  113    120    120    124    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    private OCDeclaration a(@Nullable final PsiElement psiElement, final OCSymbolWithParent ocSymbolWithParent) {
        Serializable s = null;
        Label_0018: {
            try {
                if (ocSymbolWithParent instanceof OCInstanceVariableSymbol) {
                    s = OCInstanceVariablesList.class;
                    break Label_0018;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            s = OCStruct.class;
        }
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType(psiElement, new Class[] { s });
        try {
            if (contextOfType == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCDeclaration normalizeDeclarator = null;
        if (psiElement instanceof OCDeclarator) {
            normalizeDeclarator = OCNormalizeUtil.normalizeDeclarator((OCDeclarator)psiElement);
        }
        else if (psiElement instanceof OCStructLike) {
            normalizeDeclarator = (OCDeclaration)psiElement.getParent().getParent();
        }
        try {
            if (normalizeDeclarator != null) {
                return this.putToParent(ocSymbolWithParent, normalizeDeclarator, contextOfType);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    public OCDeclaration putToParent(final OCSymbolWithParent ocSymbolWithParent, final OCDeclaration ocDeclaration, final PsiElement psiElement) {
        OCVisibility ocVisibility = null;
        Label_0032: {
            try {
                if (psiElement instanceof OCStructLike) {
                    ocVisibility = ((OCStructLike)psiElement).getDefaultVisibility();
                    break Label_0032;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocVisibility = OCVisibility.getDefaultObjCVisibility(ocSymbolWithParent.getKind());
        }
        final OCVisibility ocVisibility2 = ocVisibility;
        try {
            if (this.myNewVisibility == ocVisibility2) {
                return this.a(psiElement, ocDeclaration, psiElement.getFirstChild(), ocSymbolWithParent, false);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        PsiElement psiElement2 = psiElement.getFirstChild();
        while (true) {
            Label_0094: {
                try {
                    if (psiElement2 == null) {
                        break;
                    }
                    final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = this;
                    final OCVisibility ocVisibility3 = ocChangeVisibilityIntentionAction.myNewVisibility;
                    final PsiElement psiElement3 = psiElement2;
                    final OCVisibility ocVisibility4 = OCVisibility.getVisibilityFromElement(psiElement3);
                    if (ocVisibility3 == ocVisibility4) {
                        break Label_0094;
                    }
                    break Label_0094;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCChangeVisibilityIntentionAction ocChangeVisibilityIntentionAction = this;
                    final OCVisibility ocVisibility3 = ocChangeVisibilityIntentionAction.myNewVisibility;
                    final PsiElement psiElement3 = psiElement2;
                    final OCVisibility ocVisibility4 = OCVisibility.getVisibilityFromElement(psiElement3);
                    if (ocVisibility3 == ocVisibility4) {
                        return this.a(psiElement, ocDeclaration, psiElement2.getNextSibling(), ocSymbolWithParent, false);
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            psiElement2 = psiElement2.getNextSibling();
        }
        return this.a(psiElement, ocDeclaration, (PsiElement)ocDeclaration, ocSymbolWithParent, true);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
