// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.awt.event.MouseEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.lang.LanguageCodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.PresentableCodeInsightActionHandler;

public class OCGotoSuperHandler implements PresentableCodeInsightActionHandler, LanguageCodeInsightActionHandler
{
    @Override
    public void update(@NotNull final Editor editor, @NotNull final PsiFile psiFile, final Presentation presentation) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler", "update"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler", "update"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        presentation.setText("S_uper Definition");
        presentation.setDescription("");
    }
    
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        return psiFile instanceof OCFile;
    }
    
    @Nullable
    public static OCGotoAction getAction(final PsiElement psiElement) {
        final List<? extends OCSymbol> a = a(psiElement);
        Label_0025: {
            try {
                if (a == null) {
                    return null;
                }
                final List<? extends OCSymbol> list = a;
                final boolean b = list.isEmpty();
                if (!b) {
                    break Label_0025;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final List<? extends OCSymbol> list = a;
                final boolean b = list.isEmpty();
                if (!b) {
                    return new OCGotoActionSync(psiElement) {
                        @Override
                        protected List<? extends OCSymbol> evaluateTargets() {
                            final List access$000 = a(psiElement);
                            int n = true ? 1 : 0;
                            for (final OCSymbol ocSymbol : access$000) {
                                boolean b;
                                if (ocSymbol instanceof OCProtocolSymbol) {
                                    b = true;
                                }
                                else if (ocSymbol instanceof OCMethodSymbol) {
                                    b = (((OCMethodSymbol)ocSymbol).getParent() instanceof OCProtocolSymbol);
                                }
                                else {
                                    b = (ocSymbol instanceof OCFunctionSymbol && ocSymbol.isPredeclaration());
                                }
                                n &= (b ? 1 : 0);
                            }
                            this.myIcon = ((n != 0) ? AllIcons.Gutter.ImplementingMethod : AllIcons.Gutter.OverridingMethod);
                            this.myName = "Go to ";
                            final OCSymbol ocSymbol2 = access$000.isEmpty() ? null : access$000.get(0);
                            if (ocSymbol2 instanceof OCMemberSymbol) {
                                this.myName = this.myName + "super " + ocSymbol2.getKind().getNameLowercase() + " in " + ((OCMemberSymbol)ocSymbol2).getParent().getNameWithKindLowercase();
                            }
                            else if (ocSymbol2 instanceof OCFunctionSymbol) {
                                final OCSymbolWithQualifiedName<OCElement> parent = ((OCFunctionSymbol)ocSymbol2).getParent();
                                if (parent != null) {
                                    this.myName = this.myName + "base function in " + parent.getNameWithKindLowercase();
                                }
                            }
                            else {
                                this.myName += "super type";
                            }
                            return (List<? extends OCSymbol>)access$000;
                        }
                    };
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    private static List<? extends OCSymbol> a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //     4: ifeq            35
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSuperSymbols:()Ljava/util/List;
        //    30: areturn        
        //    31: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    39: ifne            56
        //    42: aload_0        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    46: ifeq            231
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    65: astore_1       
        //    66: aload_1        
        //    67: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    70: ifeq            135
        //    73: aload_0        
        //    74: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    77: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //    82: ifnull          135
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    96: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.isPossibleStructMember:()Z
        //   101: ifeq            135
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: new             Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;
        //   114: dup            
        //   115: aload_1        
        //   116: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   119: iconst_0       
        //   120: iconst_0       
        //   121: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZZ)V
        //   124: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.findAll:()Ljava/util/Collection;
        //   127: checkcast       Ljava/util/List;
        //   130: areturn        
        //   131: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_1        
        //   136: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   139: ifeq            228
        //   142: new             Lcom/intellij/util/CommonProcessors$CollectUniquesProcessor;
        //   145: dup            
        //   146: invokespecial   com/intellij/util/CommonProcessors$CollectUniquesProcessor.<init>:()V
        //   149: astore_2       
        //   150: aload_1        
        //   151: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   154: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //   157: astore_3       
        //   158: aload_3        
        //   159: iconst_0       
        //   160: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setIncludeSelfImplementation:(Z)V
        //   163: aload_3        
        //   164: iconst_0       
        //   165: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setInheritors:(Z)V
        //   168: aload_3        
        //   169: iconst_1       
        //   170: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setAncestors:(Z)V
        //   173: aload_1        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   177: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   182: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   185: ifeq            200
        //   188: aload_3        
        //   189: iconst_1       
        //   190: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setImplementationsThenInterfaces:(Z)V
        //   193: goto            205
        //   196: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_3        
        //   201: iconst_1       
        //   202: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setInterfacesThenImplementations:(Z)V
        //   205: aload_3        
        //   206: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   209: aload_2        
        //   210: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //   215: pop            
        //   216: new             Ljava/util/ArrayList;
        //   219: dup            
        //   220: aload_2        
        //   221: invokevirtual   com/intellij/util/CommonProcessors$CollectUniquesProcessor.getResults:()Ljava/util/Collection;
        //   224: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   227: areturn        
        //   228: goto            289
        //   231: aload_0        
        //   232: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   235: ifeq            289
        //   238: aload_0        
        //   239: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   242: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   247: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   250: astore_1       
        //   251: aload_1        
        //   252: ifnull          289
        //   255: new             Ljava/util/ArrayList;
        //   258: dup            
        //   259: invokespecial   java/util/ArrayList.<init>:()V
        //   262: astore_2       
        //   263: aload_1        
        //   264: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   267: dup            
        //   268: aload_0        
        //   269: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   274: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   277: aload_2        
        //   278: invokedynamic   process:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   283: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   286: pop            
        //   287: aload_2        
        //   288: areturn        
        //   289: aconst_null    
        //   290: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;)Ljava/util/List<+Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     31     35     Ljava/lang/IllegalArgumentException;
        //  35     49     52     56     Ljava/lang/IllegalArgumentException;
        //  66     85     88     92     Ljava/lang/IllegalArgumentException;
        //  73     104    107    111    Ljava/lang/IllegalArgumentException;
        //  92     131    131    135    Ljava/lang/IllegalArgumentException;
        //  158    196    196    200    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    public static PsiElement getContext(@Nullable final PsiElement psiElement) {
        Object o = PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCFunctionDeclaration.class, OCMethod.class, OCClassDeclaration.class, OCStruct.class });
        if (o instanceof OCFunctionDeclaration) {
            o = ((OCFunctionDeclaration)o).getDeclarator();
        }
        return (PsiElement)o;
    }
    
    public void invoke(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/navigation/OCGotoSuperHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCGotoAction action = getAction(getContext(psiFile.findElementAt(editor.getCaretModel().getOffset())));
        try {
            if (action != null) {
                action.navigate(null, editor);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
