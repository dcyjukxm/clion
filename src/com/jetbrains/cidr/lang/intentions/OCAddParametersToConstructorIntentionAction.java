// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.quickfixes.OCAddFieldInitializerFix;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import java.util.Collections;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateConstructorHandler;
import com.intellij.codeInsight.FileModificationService;
import java.util.List;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;

public class OCAddParametersToConstructorIntentionAction extends OCClassActionHandlerBase<OCStructSymbol, OCFunctionSymbol, OCCppActionContext<OCStructSymbol, OCFunctionSymbol>> implements IntentionAction
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Add as a parameter to constructor";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected String getActionTitle() {
        return "Add Parameter to Constructor";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Constructor to Update";
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCStruct.class;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_3        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: ifeq            94
        //    51: aload_3        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    60: ifeq            94
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: aload_0        
        //    71: aload_2        
        //    72: aload_3        
        //    73: invokevirtual   com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction.getField:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    76: ifnull          94
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    93: athrow         
        //    94: iconst_0       
        //    95: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     63     66     70     Ljava/lang/IllegalStateException;
        //  51     79     82     86     Ljava/lang/IllegalStateException;
        //  70     90     90     94     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    protected OCDeclaratorSymbol getField(final Editor editor, final PsiFile psiFile) {
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        OCDeclarator ocDeclarator = OCElementUtil.getAdjacentParentOfType(element, (Class<? extends OCDeclarator>)OCDeclarator.class);
        Label_0097: {
            if (ocDeclarator == null) {
                final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getContextOfType(element, new Class[] { OCDeclaration.class });
                try {
                    if (ocDeclaration == null || ocDeclaration.getDeclarators().size() <= 0) {
                        break Label_0097;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                ocDeclarator = ocDeclaration.getDeclarators().get(0);
            }
            try {
                if (ocDeclarator == null) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCDeclaratorSymbol symbol = ocDeclarator.getSymbol();
        Label_0145: {
            try {
                if (!(symbol instanceof OCDeclaratorSymbol)) {
                    return null;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT_FIELD;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0145;
                }
                return null;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT_FIELD;
                if (ocSymbolKind == ocSymbolKind2) {
                    return symbol;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCFunctionSymbol> collection) {
        try {
            if (collection.size() > 1) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    protected boolean allowMultiSelection(final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> ocCppActionContext) {
        return false;
    }
    
    @Override
    protected boolean allowEmptySelection(final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> ocCppActionContext) {
        return true;
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        final OCStructSymbol ocStructSymbol = ((OCClassActionHandlerBase<OCStructSymbol, M, C>)this).getParent(project, editor, psiFile);
        final OCDeclaratorSymbol field = this.getField(editor, psiFile);
        Label_0037: {
            try {
                if (ocStructSymbol == null) {
                    break Label_0037;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = field;
                if (ocDeclaratorSymbol == null) {
                    break Label_0037;
                }
                return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>(ocStructSymbol, ((OCSymbolBase<PsiElement>)field).locateDefinition()) {
                    final /* synthetic */ OCDeclaratorSymbol val$field;
                    final /* synthetic */ OCStructSymbol val$parent;
                    
                    @NotNull
                    @Override
                    public Collection<OCFunctionSymbol> getMemberCandidates() {
                        final CommonProcessors.CollectProcessor<OCFunctionSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCFunctionSymbol>() {
                            protected boolean accept(OCFunctionSymbol ocFunctionSymbol) {
                                ocFunctionSymbol = (OCFunctionSymbol)ocFunctionSymbol.getDefinitionSymbol();
                                if (ocFunctionSymbol == null) {
                                    return false;
                                }
                                final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)ocFunctionSymbol.locateFunctionDefinition();
                                final OCConstructorInitializationList list = (ocFunctionDefinition != null) ? ocFunctionDefinition.getConstructorInitializationList() : null;
                                if (list != null) {
                                    final Iterator<OCConstructorFieldInitializer> iterator = list.getInitializers().iterator();
                                    while (iterator.hasNext()) {
                                        final OCReferenceElement referenceElement = iterator.next().getReferenceElement();
                                        if (OCCppActionContext.this.val$field.equals((referenceElement != null) ? referenceElement.resolveToSymbol() : null)) {
                                            return false;
                                        }
                                    }
                                }
                                return true;
                            }
                        };
                        Collection results;
                        try {
                            this.val$parent.processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
                            results = collectProcessor.getResults();
                            if (results == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$1", "getMemberCandidates"));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        return (Collection<OCFunctionSymbol>)results;
                    }
                    
                    private static IllegalStateException a(final IllegalStateException ex) {
                        return ex;
                    }
                };
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = field;
                if (ocDeclaratorSymbol == null) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>(ocStructSymbol, ((OCSymbolBase<PsiElement>)field).locateDefinition(), ocStructSymbol) {
            final /* synthetic */ OCDeclaratorSymbol val$field;
            final /* synthetic */ OCStructSymbol val$parent;
            
            @NotNull
            @Override
            public Collection<OCFunctionSymbol> getMemberCandidates() {
                final CommonProcessors.CollectProcessor<OCFunctionSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCFunctionSymbol>() {
                    protected boolean accept(OCFunctionSymbol ocFunctionSymbol) {
                        ocFunctionSymbol = (OCFunctionSymbol)ocFunctionSymbol.getDefinitionSymbol();
                        if (ocFunctionSymbol == null) {
                            return false;
                        }
                        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)ocFunctionSymbol.locateFunctionDefinition();
                        final OCConstructorInitializationList list = (ocFunctionDefinition != null) ? ocFunctionDefinition.getConstructorInitializationList() : null;
                        if (list != null) {
                            final Iterator<OCConstructorFieldInitializer> iterator = list.getInitializers().iterator();
                            while (iterator.hasNext()) {
                                final OCReferenceElement referenceElement = iterator.next().getReferenceElement();
                                if (field.equals((referenceElement != null) ? referenceElement.resolveToSymbol() : null)) {
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                };
                Collection results;
                try {
                    ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
                    results = collectProcessor.getResults();
                    if (results == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$1", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return (Collection<OCFunctionSymbol>)results;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return null;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> ocCppActionContext, @NotNull final List<OCFunctionSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction", "performAction"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        final OCDeclaratorSymbol field = this.getField(editor, psiFile);
        try {
            if (field == null) {
                return;
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        try {
            if (list.size() == 0) {
                new OCGenerateConstructorHandler() {
                    @NotNull
                    @Override
                    protected OCGenerateConstructorContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
                        OCGenerateConstructorContext ocGenerateConstructorContext;
                        try {
                            ocGenerateConstructorContext = new OCGenerateConstructorContext(ocStructSymbol, psiElement) {
                                @NotNull
                                @Override
                                public Collection<OCDeclaratorSymbol> getMemberCandidates() {
                                    List<OCDeclaratorSymbol> singletonList;
                                    try {
                                        singletonList = Collections.singletonList(field);
                                        if (singletonList == null) {
                                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2$1", "getMemberCandidates"));
                                        }
                                    }
                                    catch (IllegalStateException ex) {
                                        throw b(ex);
                                    }
                                    return singletonList;
                                }
                                
                                private static IllegalStateException b(final IllegalStateException ex) {
                                    return ex;
                                }
                            };
                            if (ocGenerateConstructorContext == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "evaluateActionContext"));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw b(ex);
                        }
                        return ocGenerateConstructorContext;
                    }
                    
                    @NotNull
                    @Override
                    protected List<? extends OCStructSymbol> getParents(@NotNull final PsiElement psiElement) {
                        try {
                            if (psiElement == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "at", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "getParents"));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw b(ex);
                        }
                        List<OCMembersContainer> singletonList;
                        try {
                            singletonList = Collections.singletonList(ocCppActionContext.getParent());
                            if (singletonList == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "getParents"));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw b(ex2);
                        }
                        return (List<? extends OCStructSymbol>)singletonList;
                    }
                    
                    @Override
                    protected boolean enableChooseDialog(final Collection<OCDeclaratorSymbol> collection) {
                        return false;
                    }
                    
                    private static IllegalStateException b(final IllegalStateException ex) {
                        return ex;
                    }
                }.invoke(project, null, psiFile);
                return;
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        Label_0268: {
            try {
                if (OCAddParametersToConstructorIntentionAction.$assertionsDisabled) {
                    break Label_0268;
                }
                final List<OCFunctionSymbol> list2 = list;
                final int n = list2.size();
                final boolean b = true;
                if (n != (b ? 1 : 0)) {
                    break Label_0268;
                }
                break Label_0268;
            }
            catch (IllegalStateException ex8) {
                throw a(ex8);
            }
            try {
                final List<OCFunctionSymbol> list2 = list;
                final int n = list2.size();
                final boolean b = true;
                if (n != (b ? 1 : 0)) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex9) {
                throw a(ex9);
            }
        }
        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)list.get(0).getDefinitionSymbol();
        OCFunctionDefinition ocFunctionDefinition = null;
        Label_0320: {
            try {
                if (ocFunctionSymbol != null) {
                    ocFunctionDefinition = (OCFunctionDefinition)ocFunctionSymbol.locateFunctionDefinition();
                    break Label_0320;
                }
            }
            catch (IllegalStateException ex10) {
                throw a(ex10);
            }
            ocFunctionDefinition = null;
        }
        final OCFunctionDefinition ocFunctionDefinition2 = ocFunctionDefinition;
        try {
            if (ocFunctionDefinition2 == null) {
                return;
            }
        }
        catch (IllegalStateException ex11) {
            throw a(ex11);
        }
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocFunctionDefinition2, (PsiElement)ocFunctionDefinition2);
        handler.addParameter(OCNameSuggester.suggestUniqueName(OCSymbolKind.PARAMETER, field.getName(), null, ContainerUtil.map((Collection)ocFunctionSymbol.getParameterSymbols(), ocDeclaratorSymbol -> ocDeclaratorSymbol.getName())), field.getType(), -1);
        final String s;
        handler.getGeneratedInfo().runOnSuccess(() -> OCAddFieldInitializerFix.addFieldInitializer(ocFunctionDefinition2, field, s));
        handler.setTitle(this.getActionTitle());
        handler.invoke();
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCAddParametersToConstructorIntentionAction.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
