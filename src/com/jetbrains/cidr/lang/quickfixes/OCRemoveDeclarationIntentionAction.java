// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Iterator;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;

public class OCRemoveDeclarationIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    protected OCSymbol mySymbol;
    private String myText;
    private String myFamilyName;
    
    public OCRemoveDeclarationIntentionAction(final String s, final OCSymbol mySymbol) {
        super(null);
        this.mySymbol = mySymbol;
        this.myText = "Remove " + s + " of " + this.mySymbol.getNameWithKindLowercase();
        this.myFamilyName = "Remove " + s;
    }
    
    public OCRemoveDeclarationIntentionAction(final String s, @Nullable final PsiElement psiElement) {
        super(psiElement);
        this.myText = "Remove " + s;
        this.myFamilyName = "Remove " + s;
    }
    
    public OCRemoveDeclarationIntentionAction(final OCSymbol mySymbol) {
        super(null);
        this.mySymbol = mySymbol;
        this.myText = "Remove declaration of " + this.mySymbol.getNameWithKindLowercase();
        this.myFamilyName = "Remove declaration";
    }
    
    @Override
    protected String getTextInternal() {
        return this.myText;
    }
    
    @NotNull
    public String getFamilyName() {
        String myFamilyName;
        try {
            myFamilyName = this.myFamilyName;
            if (myFamilyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return myFamilyName;
    }
    
    @Override
    public boolean startInWriteAction() {
        Label_0029: {
            try {
                if (this.mySymbol == null) {
                    break Label_0029;
                }
                final OCRemoveDeclarationIntentionAction ocRemoveDeclarationIntentionAction = this;
                final OCSymbol ocSymbol = ocRemoveDeclarationIntentionAction.mySymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.PARAMETER;
                if (ocSymbolKind != ocSymbolKind2) {
                    break Label_0029;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final OCRemoveDeclarationIntentionAction ocRemoveDeclarationIntentionAction = this;
                final OCSymbol ocSymbol = ocRemoveDeclarationIntentionAction.mySymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.PARAMETER;
                if (ocSymbolKind != ocSymbolKind2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isAvailable() {
        final PsiElement validElementOrNull = this.getValidElementOrNull();
        try {
            if (validElementOrNull == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        try {
            if (this.mySymbol == null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        try {
            if (OCCodeInsightUtil.insideConditionalHeader(validElementOrNull)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw c(ex3);
        }
        Label_0064: {
            try {
                if (!OCCodeInsightUtil.insideLoopHeader(validElementOrNull)) {
                    return true;
                }
                final PsiElement psiElement = validElementOrNull;
                final Class<OCForStatement> clazz = OCForStatement.class;
                final PsiElement psiElement2 = PsiTreeUtil.getParentOfType(psiElement, (Class)clazz);
                if (psiElement2 != null) {
                    break Label_0064;
                }
                return false;
            }
            catch (IncorrectOperationException ex4) {
                throw c(ex4);
            }
            try {
                final PsiElement psiElement = validElementOrNull;
                final Class<OCForStatement> clazz = OCForStatement.class;
                final PsiElement psiElement2 = PsiTreeUtil.getParentOfType(psiElement, (Class)clazz);
                if (psiElement2 != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw c(ex5);
            }
        }
        return false;
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        PsiElement locateDefinition;
        if (this.mySymbol != null) {
            locateDefinition = this.mySymbol.locateDefinition();
        }
        else {
            final PsiElement element = this.myElementPtr.getElement();
            PsiElement context = null;
            Label_0103: {
                try {
                    if (element != null) {
                        context = element.getContext();
                        break Label_0103;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw c(ex2);
                }
                context = null;
            }
            locateDefinition = context;
        }
        Label_0138: {
            try {
                if (locateDefinition == null) {
                    return;
                }
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final PsiElement psiElement = locateDefinition;
                final PsiFile psiFile2 = psiElement.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
                break Label_0138;
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
            try {
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final PsiElement psiElement = locateDefinition;
                final PsiFile psiFile2 = psiElement.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw c(ex4);
            }
        }
        if (locateDefinition instanceof OCLabeledStatement) {
            final OCStatement statement = ((OCLabeledStatement)locateDefinition).getStatement();
            try {
                if (statement != null) {
                    locateDefinition.replace((PsiElement)statement);
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw c(ex5);
            }
            OCChangeUtil.delete(locateDefinition);
        }
        else {
            Label_0291: {
                try {
                    if (this.mySymbol == null || this.mySymbol.getKind() != OCSymbolKind.PARAMETER) {
                        break Label_0291;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw c(ex6);
                }
                final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType(locateDefinition, (Class)OCCallable.class);
                try {
                    if (ocCallable == null) {
                        return;
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw c(ex7);
                }
                ApplicationManager.getApplication().runWriteAction(() -> deleteUsages(locateDefinition));
                final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocCallable, (PsiElement)ocCallable, true);
                handler.removeParameter(this.mySymbol.getName(), true);
                handler.invoke();
                return;
            }
            deleteUsages(locateDefinition);
            OCChangeUtil.delete(locateDefinition);
        }
    }
    
    protected static void deleteUsages(final PsiElement psiElement) {
        final Iterator<PsiReference> iterator = ReferencesSearch.search(psiElement, psiElement.getUseScope()).findAll().iterator();
        while (iterator.hasNext()) {
            OCChangeUtil.safeDeleteReference(iterator.next().getElement());
        }
    }
    
    @Nullable
    protected PsiElement getValidElementOrNull() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: ifnull          62
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    16: astore_1       
        //    17: aload_1        
        //    18: ifnull          60
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    25: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    28: ifeq            60
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    37: athrow         
        //    38: aload_1        
        //    39: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    42: ifeq            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    51: athrow         
        //    52: aload_1        
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: aconst_null    
        //    61: areturn        
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //    66: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //    71: astore_1       
        //    72: aload_1        
        //    73: ifnull          117
        //    76: aload_1        
        //    77: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    80: ifeq            117
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    89: athrow         
        //    90: aload_1        
        //    91: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    96: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    99: ifeq            117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: aload_1        
        //   110: goto            118
        //   113: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   116: athrow         
        //   117: aconst_null    
        //   118: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  17     31     34     38     Lcom/intellij/util/IncorrectOperationException;
        //  21     45     48     52     Lcom/intellij/util/IncorrectOperationException;
        //  38     56     56     60     Lcom/intellij/util/IncorrectOperationException;
        //  72     83     86     90     Lcom/intellij/util/IncorrectOperationException;
        //  76     102    105    109    Lcom/intellij/util/IncorrectOperationException;
        //  90     113    113    117    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
