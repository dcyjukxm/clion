// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.openapi.editor.Document;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateInitWithHandler;
import java.util.Collections;
import java.util.ArrayList;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCStatement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;

public class OCAddParametersToInitWithIntentionAction extends OCClassActionHandlerBase<OCClassSymbol, OCMethodSymbol, OCObjCActionContext<OCMethodSymbol>> implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Add as a parameter to 'initWith...'";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected String getActionTitle() {
        return "Add Parameter to 'initWith...'";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select 'initWith...' Method to Update";
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCClassDeclaration.class;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (a(editor, psiFile) != null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Nullable
    private static OCInstanceVariableSymbol a(final Editor editor, final PsiFile psiFile) {
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        OCDeclarator ocDeclarator = OCElementUtil.getAdjacentParentOfType(element, (Class<? extends OCDeclarator>)OCDeclarator.class);
        Label_0094: {
            if (ocDeclarator == null) {
                final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getContextOfType(element, new Class[] { OCDeclaration.class });
                try {
                    if (ocDeclaration == null || ocDeclaration.getDeclarators().size() <= 0) {
                        break Label_0094;
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
        final OCInstanceVariableSymbol symbol = ocDeclarator.getSymbol();
        try {
            if (symbol instanceof OCInstanceVariableSymbol) {
                return symbol;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (symbol instanceof OCPropertySymbol) {
                return ((OCPropertySymbol)symbol).getAssociatedIvar();
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Nullable
    private static PsiElement a(final OCMethod ocMethod) {
        try {
            if (ocMethod == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCBlockStatement body = ocMethod.getBody();
        try {
            if (body == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final List<OCStatement> statements = body.getStatements();
        Label_0064: {
            try {
                if (statements.size() < 2) {
                    break Label_0064;
                }
                final List<OCStatement> list = statements;
                final int n = 1;
                final OCStatement ocStatement = list.get(n);
                final boolean b = ocStatement instanceof OCIfStatement;
                if (!b) {
                    break Label_0064;
                }
                break Label_0064;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final List<OCStatement> list = statements;
                final int n = 1;
                final OCStatement ocStatement = list.get(n);
                final boolean b = ocStatement instanceof OCIfStatement;
                if (!b) {
                    return null;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        final OCIfStatement ocIfStatement = statements.get(1);
        try {
            if (ocIfStatement.getThenBranch() instanceof OCBlockStatement) {
                return (PsiElement)ocIfStatement.getThenBranch();
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCMethodSymbol> collection) {
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
    protected boolean allowMultiSelection(final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        return false;
    }
    
    @Override
    protected boolean allowEmptySelection(final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        return true;
    }
    
    @NotNull
    @Override
    protected OCObjCActionContext<OCMethodSymbol> evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCObjCActionContext<OCMethodSymbol> ocObjCActionContext;
        try {
            ocObjCActionContext = new OCObjCActionContext<OCMethodSymbol>(ocClassSymbol, psiElement, ocClassSymbol.getResolvedType(true)) {
                @Override
                protected Class<OCMethodSymbol> getMemberSymbolClass() {
                    return OCMethodSymbol.class;
                }
                
                @NotNull
                @Override
                public Collection<OCMethodSymbol> getMemberCandidates() {
                    final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
                    Collection results;
                    try {
                        this.getImplementationSymbol().processMembers(this.getMemberSymbolClass(), (com.intellij.util.Processor<? super OCMethodSymbol>)collectProcessor);
                        results = collectProcessor.getResults();
                        if (results == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$1", "getMemberCandidates"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw b(ex);
                    }
                    return (Collection<OCMethodSymbol>)results;
                }
                
                private static IllegalStateException b(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocObjCActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocObjCActionContext;
    }
    
    @NotNull
    @Override
    protected Condition<OCMethodSymbol> getCandidatesFilter(@NotNull final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        try {
            if (ocObjCActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Condition condition;
        try {
            condition = (ocMethodSymbol -> {
                try {
                    if (!ocMethodSymbol.getName().startsWith("initWith")) {
                        return false;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final OCMethod locateDefinition = ((OCSymbol<OCMethod>)ocMethodSymbol).locateDefinition();
                Label_0051: {
                    try {
                        if (!(locateDefinition instanceof OCMethod)) {
                            return false;
                        }
                        final OCMethod ocMethod = locateDefinition;
                        final OCMethod ocMethod2 = ocMethod;
                        final PsiElement psiElement = a(ocMethod2);
                        if (psiElement != null) {
                            break Label_0051;
                        }
                        return false;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCMethod ocMethod = locateDefinition;
                        final OCMethod ocMethod2 = ocMethod;
                        final PsiElement psiElement = a(ocMethod2);
                        if (psiElement != null) {
                            return true;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                return false;
            });
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (Condition<OCMethodSymbol>)condition;
    }
    
    @Override
    protected void performAction(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final OCObjCActionContext<OCMethodSymbol> p3, @NotNull final List<OCMethodSymbol> p4) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "performAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "performAction"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   132: athrow         
        //   133: aload           5
        //   135: ifnonnull       178
        //   138: new             Ljava/lang/IllegalArgumentException;
        //   141: dup            
        //   142: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   144: ldc             3
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "chosenCandidates"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "performAction"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   177: athrow         
        //   178: invokestatic    com/intellij/codeInsight/FileModificationService.getInstance:()Lcom/intellij/codeInsight/FileModificationService;
        //   181: aload_3        
        //   182: invokevirtual   com/intellij/codeInsight/FileModificationService.prepareFileForWrite:(Lcom/intellij/psi/PsiFile;)Z
        //   185: ifne            193
        //   188: return         
        //   189: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   192: athrow         
        //   193: aload_2        
        //   194: aload_3        
        //   195: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   198: astore          6
        //   200: aload           6
        //   202: ifnonnull       210
        //   205: return         
        //   206: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   209: athrow         
        //   210: aload           5
        //   212: invokeinterface java/util/List.size:()I
        //   217: ifne            241
        //   220: new             Lcom/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$2;
        //   223: dup            
        //   224: aload_0        
        //   225: aload           6
        //   227: invokespecial   com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$2.<init>:(Lcom/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction;Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;)V
        //   230: aload_1        
        //   231: aload_2        
        //   232: aload_3        
        //   233: invokevirtual   com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$2.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   236: return         
        //   237: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   240: athrow         
        //   241: getstatic       com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.$assertionsDisabled:Z
        //   244: ifne            277
        //   247: aload           5
        //   249: invokeinterface java/util/List.size:()I
        //   254: iconst_1       
        //   255: if_icmpeq       277
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   264: athrow         
        //   265: new             Ljava/lang/AssertionError;
        //   268: dup            
        //   269: invokespecial   java/lang/AssertionError.<init>:()V
        //   272: athrow         
        //   273: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   276: athrow         
        //   277: aload           5
        //   279: iconst_0       
        //   280: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   285: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   288: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   293: astore          7
        //   295: aload           7
        //   297: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   300: ifne            308
        //   303: return         
        //   304: invokestatic    com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   307: athrow         
        //   308: aload           7
        //   310: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   312: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   315: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   318: astore          8
        //   320: aload           7
        //   322: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   325: aload           7
        //   327: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.getHandler:(Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;
        //   330: astore          9
        //   332: aload           9
        //   334: aload           6
        //   336: iconst_1       
        //   337: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getNonCollidingName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Ljava/lang/String;
        //   340: aload           6
        //   342: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getNonCollidingName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/lang/String;
        //   345: aload           6
        //   347: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   352: iconst_m1      
        //   353: iconst_0       
        //   354: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.addParameter:(Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;IZ)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   359: astore          10
        //   361: aload           9
        //   363: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.getGeneratedInfo:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   368: aload_0        
        //   369: aload           9
        //   371: aload           8
        //   373: aload_1        
        //   374: aload           6
        //   376: aload           10
        //   378: aload_2        
        //   379: aload           7
        //   381: aload           4
        //   383: aload_3        
        //   384: invokedynamic   run:(Lcom/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/generate/actions/OCObjCActionContext;Lcom/intellij/psi/PsiFile;)Ljava/lang/Runnable;
        //   389: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.runOnSuccess:(Ljava/lang/Runnable;)V
        //   392: aload           9
        //   394: aload_0        
        //   395: invokevirtual   com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction.getActionTitle:()Ljava/lang/String;
        //   398: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.setTitle:(Ljava/lang/String;)V
        //   403: aload           9
        //   405: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.invoke:()V
        //   410: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCObjCActionContext<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     129    129    133    Ljava/lang/IllegalStateException;
        //  133    174    174    178    Ljava/lang/IllegalStateException;
        //  178    189    189    193    Ljava/lang/IllegalStateException;
        //  200    206    206    210    Ljava/lang/IllegalStateException;
        //  210    237    237    241    Ljava/lang/IllegalStateException;
        //  241    258    261    265    Ljava/lang/IllegalStateException;
        //  247    273    273    277    Ljava/lang/IllegalStateException;
        //  295    304    304    308    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCAddParametersToInitWithIntentionAction.class.desiredAssertionStatus()) {
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
