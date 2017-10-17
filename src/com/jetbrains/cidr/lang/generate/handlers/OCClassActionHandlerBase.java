// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NonNls;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Disposer;
import java.awt.event.ItemEvent;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import java.awt.event.ItemListener;
import com.intellij.codeInsight.generation.ClassMember;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import javax.swing.JComponent;
import java.util.Map;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.util.Collection;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import org.jetbrains.annotations.Contract;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.lang.LanguageCodeInsightActionHandler;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public abstract class OCClassActionHandlerBase<P extends OCMembersContainer, M extends OCSymbolWithParent<?, ?>, C extends OCActionContext<P, M>> implements LanguageCodeInsightActionHandler
{
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        final Project project = psiFile.getProject();
        try {
            if (DumbService.isDumb(project)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCActionContext<P, M> evaluateActionContext = this.evaluateActionContext(project, editor, psiFile);
        Label_0049: {
            try {
                if (evaluateActionContext == null) {
                    return false;
                }
                final OCActionContext<P, M> ocActionContext = evaluateActionContext;
                final boolean b = ocActionContext.isValid();
                if (b) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCActionContext<P, M> ocActionContext = evaluateActionContext;
                final boolean b = ocActionContext.isValid();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final OCActionContext<P, M> evaluateActionContext = this.evaluateActionContext(project, editor, psiFile);
        try {
            if (this.checkContext(project, editor, (C)evaluateActionContext)) {
                this.invoke(project, editor, psiFile, (C)evaluateActionContext);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    protected void invoke(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final C p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "invoke"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: new             Ljava/util/HashMap;
        //   136: dup            
        //   137: invokespecial   java/util/HashMap.<init>:()V
        //   140: astore          5
        //   142: aload_1        
        //   143: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   146: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   148: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   151: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   154: astore          6
        //   156: new             Ljava/util/ArrayList;
        //   159: dup            
        //   160: invokespecial   java/util/ArrayList.<init>:()V
        //   163: astore          7
        //   165: aload_0        
        //   166: aload_3        
        //   167: aload_2        
        //   168: aload           4
        //   170: aload           6
        //   172: aload           7
        //   174: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.loadOptions:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Ljava/util/List;)V
        //   177: aload           7
        //   179: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   182: astore          8
        //   184: aload           8
        //   186: invokeinterface java/util/Iterator.hasNext:()Z
        //   191: ifeq            227
        //   194: aload           8
        //   196: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   201: checkcast       Lcom/intellij/openapi/util/Pair;
        //   204: astore          9
        //   206: aload           5
        //   208: aload           9
        //   210: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   213: aload           9
        //   215: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   218: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   223: pop            
        //   224: goto            184
        //   227: aload           4
        //   229: aload           5
        //   231: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCActionContext.setOptionValues:(Ljava/util/Map;)V
        //   234: aload           4
        //   236: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCActionContext.getSymbolsToModify:()Ljava/util/List;
        //   239: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   244: astore          8
        //   246: aload           8
        //   248: invokeinterface java/util/Iterator.hasNext:()Z
        //   253: ifeq            325
        //   256: aload           8
        //   258: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   263: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   266: astore          9
        //   268: aload           9
        //   270: ifnull          322
        //   273: aload           9
        //   275: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   278: ifne            322
        //   281: goto            288
        //   284: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: aload_1        
        //   289: aload_2        
        //   290: ldc             "error.out.of.project.element"
        //   292: iconst_1       
        //   293: anewarray       Ljava/lang/Object;
        //   296: dup            
        //   297: iconst_0       
        //   298: aload           9
        //   300: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   305: aastore        
        //   306: invokestatic    com/intellij/refactoring/RefactoringBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   309: aload_0        
        //   310: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getActionTitle:()Ljava/lang/String;
        //   313: aconst_null    
        //   314: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   317: return         
        //   318: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: goto            246
        //   325: invokestatic    com/intellij/codeInsight/FileModificationService.getInstance:()Lcom/intellij/codeInsight/FileModificationService;
        //   328: aload           4
        //   330: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCActionContext.getSymbolsToModify:()Ljava/util/List;
        //   333: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   338: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   341: invokevirtual   com/intellij/codeInsight/FileModificationService.preparePsiElementsForWrite:(Ljava/util/Collection;)Z
        //   344: ifne            352
        //   347: return         
        //   348: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: aload_0        
        //   353: aload_1        
        //   354: aload_2        
        //   355: aload_3        
        //   356: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.locateCandidate:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   359: astore          8
        //   361: aload           8
        //   363: ifnonnull       385
        //   366: new             Ljava/util/ArrayList;
        //   369: dup            
        //   370: aload           4
        //   372: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCActionContext.getMemberCandidates:()Ljava/util/Collection;
        //   375: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   378: goto            390
        //   381: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   384: athrow         
        //   385: aload           8
        //   387: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   390: astore          9
        //   392: aload_0        
        //   393: aload           9
        //   395: aload_0        
        //   396: aload           4
        //   398: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getCandidatesFilter:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Lcom/intellij/openapi/util/Condition;
        //   401: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/util/List;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //   404: astore          9
        //   406: aload_0        
        //   407: aload           9
        //   409: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/util/List;)Ljava/util/List;
        //   412: astore          9
        //   414: aload           8
        //   416: ifnonnull       473
        //   419: aload_0        
        //   420: aload           4
        //   422: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.allowEmptySelection:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Z
        //   425: ifne            473
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: aload           9
        //   437: invokeinterface java/util/List.isEmpty:()Z
        //   442: ifeq            473
        //   445: goto            452
        //   448: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: aload_1        
        //   453: aload_2        
        //   454: aload_0        
        //   455: aload           4
        //   457: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getNoMembersMessage:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Ljava/lang/String;
        //   460: aload_0        
        //   461: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getActionTitle:()Ljava/lang/String;
        //   464: aconst_null    
        //   465: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   468: return         
        //   469: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: new             Ljava/util/HashSet;
        //   476: dup            
        //   477: aload_0        
        //   478: aload           4
        //   480: aload_2        
        //   481: aload_3        
        //   482: aload           9
        //   484: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.getSelectedCandidates:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Ljava/util/List;)Ljava/util/Collection;
        //   487: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //   490: astore          10
        //   492: aload           10
        //   494: aload           9
        //   496: invokeinterface java/util/Set.retainAll:(Ljava/util/Collection;)Z
        //   501: pop            
        //   502: aload_0        
        //   503: aload_3        
        //   504: aload_2        
        //   505: aload           9
        //   507: aload           10
        //   509: aload           4
        //   511: aload           6
        //   513: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Ljava/util/List;Ljava/util/Set;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;)Ljava/util/List;
        //   516: astore          11
        //   518: aload           11
        //   520: ifnull          574
        //   523: aload_0        
        //   524: aload           4
        //   526: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.allowEmptySelection:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Z
        //   529: ifne            556
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload           11
        //   541: invokeinterface java/util/List.isEmpty:()Z
        //   546: ifne            574
        //   549: goto            556
        //   552: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: aload_0        
        //   557: aload_1        
        //   558: aload_2        
        //   559: aload_3        
        //   560: aload           4
        //   562: aload           11
        //   564: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.performAction:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Ljava/util/List;)V
        //   567: goto            574
        //   570: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   573: athrow         
        //   574: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;TC;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  268    281    284    288    Ljava/lang/IllegalArgumentException;
        //  273    318    318    322    Ljava/lang/IllegalArgumentException;
        //  325    348    348    352    Ljava/lang/IllegalArgumentException;
        //  361    381    381    385    Ljava/lang/IllegalArgumentException;
        //  414    428    431    435    Ljava/lang/IllegalArgumentException;
        //  419    445    448    452    Ljava/lang/IllegalArgumentException;
        //  435    469    469    473    Ljava/lang/IllegalArgumentException;
        //  518    532    535    539    Ljava/lang/IllegalArgumentException;
        //  523    549    552    556    Ljava/lang/IllegalArgumentException;
        //  539    567    570    574    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0435:
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
    
    @Contract("_, _, null -> false")
    protected boolean checkContext(@NotNull final Project project, @Nullable final Editor editor, @Nullable final C c) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "checkContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0062: {
            try {
                if (c == null) {
                    break Label_0062;
                }
                final OCActionContext<P, M> ocActionContext = c;
                final boolean b = ocActionContext.isValid();
                if (!b) {
                    break Label_0062;
                }
                return true;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCActionContext<P, M> ocActionContext = c;
                final boolean b = ocActionContext.isValid();
                if (!b) {
                    CommonRefactoringUtil.showErrorHint(project, editor, "Action is invalid for the current selection", this.getActionTitle(), (String)null);
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return true;
    }
    
    protected abstract void performAction(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final C p3, @NotNull final List<M> p4);
    
    @Nullable
    protected P getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "getParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "getParent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (editor == null) {
                return (P)((OCFile)psiFile).getSameNamedClass();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCSymbolDeclarator ocSymbolDeclarator = (OCSymbolDeclarator)PsiTreeUtil.getContextOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), new Class[] { this.getParentClass() });
        try {
            if (ocSymbolDeclarator != null) {
                final OCMembersContainer ocMembersContainer = ocSymbolDeclarator.getSymbol();
                return (P)ocMembersContainer;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCMembersContainer ocMembersContainer = null;
        return (P)ocMembersContainer;
    }
    
    protected boolean enableChooseDialog(final Collection<M> collection) {
        return true;
    }
    
    protected void loadOptions(final PsiFile psiFile, @Nullable final Editor editor, @NotNull final C c, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    protected static <T, Comp extends JComponent> T getOption(final Map<OCOption, Object> map, final OCOption<T, Comp> ocOption) {
        return map.get(ocOption);
    }
    
    protected <T, Comp extends JComponent> T getOption(final C c, final OCOption<T, Comp> ocOption) {
        return getOption(c.getOptionValues(), ocOption);
    }
    
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Nullable
    protected C evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        final OCMembersContainer parent = this.getParent(project, editor, psiFile);
        Label_0034: {
            try {
                if (parent == null) {
                    return null;
                }
                final OCMembersContainer ocMembersContainer = parent;
                final OCFile ocFile = ocMembersContainer.getContainingOCFile();
                final boolean b = OCSearchScope.isInProjectSources((PsiElement)ocFile);
                if (b) {
                    break Label_0034;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCMembersContainer ocMembersContainer = parent;
                final OCFile ocFile = ocMembersContainer.getContainingOCFile();
                final boolean b = OCSearchScope.isInProjectSources((PsiElement)ocFile);
                if (b) {
                    final OCActionContext<P, M> evaluateActionContext = this.evaluateActionContext((P)parent, OCGenerateUtil.getElementAt(editor, psiFile));
                    return (C)evaluateActionContext;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final OCActionContext<P, M> evaluateActionContext = null;
        return (C)evaluateActionContext;
    }
    
    @Nullable
    protected abstract C evaluateActionContext(final P p0, final PsiElement p1);
    
    protected boolean allowEmptySelection(final C c) {
        return false;
    }
    
    protected boolean allowMultiSelection(final C c) {
        return true;
    }
    
    @Nullable
    public String getActionName() {
        return null;
    }
    
    @Nullable
    public String getActionDescription() {
        return null;
    }
    
    protected abstract String getActionTitle();
    
    protected abstract String getMembersChooserTitle();
    
    protected abstract Class<? extends OCSymbolDeclarator> getParentClass();
    
    protected String getNoMembersMessage(@NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return c.getParentNameUppercase() + " has no members to " + StringUtil.decapitalize(this.getActionTitle());
    }
    
    @NotNull
    protected Condition<M> getCandidatesFilter(@NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Condition alwaysTrue;
        try {
            alwaysTrue = Conditions.alwaysTrue();
            if (alwaysTrue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Condition<M>)alwaysTrue;
    }
    
    @Nullable
    protected M locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "locateCandidate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    protected Collection<M> getSelectedCandidates(@NotNull final C p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final List<M> p3) {
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
        //    18: ldc             "actionContext"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getSelectedCandidates"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getSelectedCandidates"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   107: ldc             "candidates"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "getSelectedCandidates"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_2        
        //   134: ifnonnull       186
        //   137: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   140: dup            
        //   141: ifnonnull       185
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: new             Ljava/lang/IllegalStateException;
        //   154: dup            
        //   155: ldc             "@NotNull method %s.%s must not return null"
        //   157: ldc             2
        //   159: anewarray       Ljava/lang/Object;
        //   162: dup            
        //   163: ldc             0
        //   165: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   167: aastore        
        //   168: dup            
        //   169: ldc             1
        //   171: ldc             "getSelectedCandidates"
        //   173: aastore        
        //   174: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   177: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   180: athrow         
        //   181: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: areturn        
        //   186: aload_2        
        //   187: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //   192: astore          5
        //   194: aload           5
        //   196: invokeinterface com/intellij/openapi/editor/SelectionModel.hasSelection:()Z
        //   201: ifeq            278
        //   204: new             Lcom/intellij/openapi/util/TextRange;
        //   207: dup            
        //   208: aload           5
        //   210: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionStart:()I
        //   215: aload           5
        //   217: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionEnd:()I
        //   222: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   225: astore          6
        //   227: aload           4
        //   229: aload           6
        //   231: invokedynamic   value:(Lcom/intellij/openapi/util/TextRange;)Lcom/intellij/openapi/util/Condition;
        //   236: invokestatic    com/intellij/util/containers/ContainerUtil.filter:(Ljava/util/Collection;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //   239: dup            
        //   240: ifnonnull       277
        //   243: new             Ljava/lang/IllegalStateException;
        //   246: dup            
        //   247: ldc             "@NotNull method %s.%s must not return null"
        //   249: ldc             2
        //   251: anewarray       Ljava/lang/Object;
        //   254: dup            
        //   255: ldc             0
        //   257: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   259: aastore        
        //   260: dup            
        //   261: ldc             1
        //   263: ldc             "getSelectedCandidates"
        //   265: aastore        
        //   266: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   269: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   272: athrow         
        //   273: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: areturn        
        //   278: aload_3        
        //   279: aload_2        
        //   280: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   285: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   290: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   295: astore          6
        //   297: aload           6
        //   299: ldc             Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;.class
        //   301: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   304: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   307: astore          7
        //   309: aload           7
        //   311: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   314: ifeq            329
        //   317: aload           7
        //   319: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   324: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   327: astore          7
        //   329: aload           7
        //   331: ifnull          348
        //   334: aload           7
        //   336: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   341: goto            349
        //   344: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aconst_null    
        //   349: astore          8
        //   351: aload           8
        //   353: ifnull          516
        //   356: aload           4
        //   358: aload           8
        //   360: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   365: ifeq            429
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: aload           8
        //   377: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   380: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   383: dup            
        //   384: ifnonnull       428
        //   387: goto            394
        //   390: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   393: athrow         
        //   394: new             Ljava/lang/IllegalStateException;
        //   397: dup            
        //   398: ldc             "@NotNull method %s.%s must not return null"
        //   400: ldc             2
        //   402: anewarray       Ljava/lang/Object;
        //   405: dup            
        //   406: ldc             0
        //   408: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   410: aastore        
        //   411: dup            
        //   412: ldc             1
        //   414: ldc             "getSelectedCandidates"
        //   416: aastore        
        //   417: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   420: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   423: athrow         
        //   424: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   427: athrow         
        //   428: areturn        
        //   429: aload           8
        //   431: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   436: astore          8
        //   438: aload           8
        //   440: ifnull          516
        //   443: aload           4
        //   445: aload           8
        //   447: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   452: ifeq            516
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload           8
        //   464: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   467: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   470: dup            
        //   471: ifnonnull       515
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: new             Ljava/lang/IllegalStateException;
        //   484: dup            
        //   485: ldc             "@NotNull method %s.%s must not return null"
        //   487: ldc             2
        //   489: anewarray       Ljava/lang/Object;
        //   492: dup            
        //   493: ldc             0
        //   495: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   497: aastore        
        //   498: dup            
        //   499: ldc             1
        //   501: ldc             "getSelectedCandidates"
        //   503: aastore        
        //   504: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   507: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   510: athrow         
        //   511: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   514: athrow         
        //   515: areturn        
        //   516: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   519: dup            
        //   520: ifnonnull       557
        //   523: new             Ljava/lang/IllegalStateException;
        //   526: dup            
        //   527: ldc             "@NotNull method %s.%s must not return null"
        //   529: ldc             2
        //   531: anewarray       Ljava/lang/Object;
        //   534: dup            
        //   535: ldc             0
        //   537: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   539: aastore        
        //   540: dup            
        //   541: ldc             1
        //   543: ldc             "getSelectedCandidates"
        //   545: aastore        
        //   546: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   549: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   552: athrow         
        //   553: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   556: athrow         
        //   557: areturn        
        //    Signature:
        //  (TC;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Ljava/util/List<TM;>;)Ljava/util/Collection<TM;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    144    147    151    Ljava/lang/IllegalArgumentException;
        //  137    181    181    185    Ljava/lang/IllegalArgumentException;
        //  227    273    273    277    Ljava/lang/IllegalArgumentException;
        //  329    344    344    348    Ljava/lang/IllegalArgumentException;
        //  351    368    371    375    Ljava/lang/IllegalArgumentException;
        //  356    387    390    394    Ljava/lang/IllegalArgumentException;
        //  375    424    424    428    Ljava/lang/IllegalArgumentException;
        //  438    455    458    462    Ljava/lang/IllegalArgumentException;
        //  443    474    477    481    Ljava/lang/IllegalArgumentException;
        //  462    511    511    515    Ljava/lang/IllegalArgumentException;
        //  516    553    553    557    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0375:
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
    private List<M> a(@NotNull final List<M> list, @NotNull final Condition<M> condition) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "filterCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "filterCandidates"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<M> list2 = new ArrayList<M>(list.size());
        final HashSet<M> set = new HashSet<M>();
        for (final OCSymbolWithParent<?, ?> ocSymbolWithParent : list) {
            Label_0171: {
                try {
                    if (set.contains(ocSymbolWithParent)) {
                        continue;
                    }
                    final Condition<M> condition2 = condition;
                    final OCSymbolWithParent<?, ?> ocSymbolWithParent2 = ocSymbolWithParent;
                    final boolean b = condition2.value((Object)ocSymbolWithParent2);
                    if (b) {
                        break Label_0171;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final Condition<M> condition2 = condition;
                    final OCSymbolWithParent<?, ?> ocSymbolWithParent2 = ocSymbolWithParent;
                    final boolean b = condition2.value((Object)ocSymbolWithParent2);
                    if (!b) {
                        continue;
                    }
                    list2.add((M)ocSymbolWithParent);
                    set.add((M)ocSymbolWithParent);
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        ArrayList<M> list3;
        try {
            list3 = list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "filterCandidates"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return list3;
    }
    
    @NotNull
    private List<M> a(@NotNull final List<M> p0) {
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
        //    18: ldc             "candidates"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "sortCandidates"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/util/ArrayList;
        //    47: dup            
        //    48: aload_1        
        //    49: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //    52: astore_2       
        //    53: new             Ljava/util/HashMap;
        //    56: dup            
        //    57: invokespecial   java/util/HashMap.<init>:()V
        //    60: astore_3       
        //    61: iconst_0       
        //    62: istore          4
        //    64: iload           4
        //    66: aload_1        
        //    67: invokeinterface java/util/List.size:()I
        //    72: if_icmpge       105
        //    75: aload_3        
        //    76: aload_1        
        //    77: iload           4
        //    79: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    84: iload           4
        //    86: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    89: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    94: pop            
        //    95: iinc            4, 1
        //    98: goto            64
        //   101: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_2        
        //   106: aload_3        
        //   107: invokedynamic   compare:(Ljava/util/Map;)Ljava/util/Comparator;
        //   112: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //   115: aload_2        
        //   116: dup            
        //   117: ifnonnull       154
        //   120: new             Ljava/lang/IllegalStateException;
        //   123: dup            
        //   124: ldc             "@NotNull method %s.%s must not return null"
        //   126: ldc             2
        //   128: anewarray       Ljava/lang/Object;
        //   131: dup            
        //   132: ldc             0
        //   134: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase"
        //   136: aastore        
        //   137: dup            
        //   138: ldc             1
        //   140: ldc             "sortCandidates"
        //   142: aastore        
        //   143: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   146: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   149: athrow         
        //   150: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: areturn        
        //    Signature:
        //  (Ljava/util/List<TM;>;)Ljava/util/List<TM;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  64     101    101    105    Ljava/lang/IllegalArgumentException;
        //  105    150    150    154    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166_1:
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
    private List<M> a(final PsiFile psiFile, @Nullable final Editor editor, @NotNull final List<M> list, Set<M> set, @NotNull final C c, final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "chooseCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCClassActionHandlerBase", "chooseCandidates"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Project project = psiFile.getProject();
        Label_0217: {
            Label_0179: {
                Label_0121: {
                    try {
                        if (!this.enableChooseDialog(list)) {
                            break Label_0121;
                        }
                        final List<M> list2 = list;
                        final boolean b = list2.isEmpty();
                        if (b) {
                            break Label_0121;
                        }
                        break Label_0179;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final List<M> list2 = list;
                        final boolean b = list2.isEmpty();
                        if (!b) {
                            break Label_0179;
                        }
                        if (!this.allowEmptySelection(c)) {
                            break Label_0179;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                final ArrayList<M> list3 = new ArrayList<M>((Collection<? extends M>)list);
                try {
                    if (!set.isEmpty()) {
                        list3.retainAll(set);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                return this.a(list3);
                try {
                    if (!set.isEmpty() || !ApplicationManager.getApplication().isUnitTestMode()) {
                        break Label_0217;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            set = new HashSet<Object>(list);
        }
        final ArrayList<Object> list4 = new ArrayList<Object>();
        this.loadOptions(psiFile, editor, c, ocCodeStyleSettings, (List<Pair<OCOption, Object>>)list4);
        final OCMemberChooser memberChooser = this.createMemberChooser(project, (List<Pair<OCOption, Object>>)list4, c, list);
        memberChooser.setTitle(this.getMembersChooserTitle());
        OCMemberChooserObject[] array = this.getChooserNodes(memberChooser, list, c, 0);
        int n = 0;
        while (true) {
            try {
                if (array.length != 0 || n >= list4.size()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            array = this.getChooserNodes(memberChooser, list, c, n + 1);
            ++n;
        }
        memberChooser.resetElements(array);
        final ArrayList list5 = new ArrayList<OCMemberChooserObject>(set.size());
        for (final OCMemberChooserObject ocMemberChooserObject : array) {
            try {
                if (set.contains(ocMemberChooserObject.getSymbol())) {
                    list5.add(ocMemberChooserObject);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        memberChooser.selectElements(list5.toArray((ClassMember[])new OCMemberChooserObject[list5.size()]));
        final JComponent[] optionControls = memberChooser.getOptionControls();
        for (int j = 0; j < optionControls.length; ++j) {
            final OCOption ocOption = (OCOption)list4.get(j).getFirst();
            ocOption.addItemListener(optionControls[j], new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    OCClassActionHandlerBase.this.optionValueChanged(memberChooser, list, ocOption, c);
                }
            });
        }
        Label_0549: {
            Label_0535: {
                try {
                    if (ApplicationManager.getApplication().isUnitTestMode()) {
                        break Label_0535;
                    }
                    final OCMemberChooser ocMemberChooser = memberChooser;
                    ocMemberChooser.show();
                    final OCMemberChooser ocMemberChooser2 = memberChooser;
                    final int n2 = ocMemberChooser2.getExitCode();
                    if (n2 != 0) {
                        break Label_0535;
                    }
                    break Label_0549;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                try {
                    final OCMemberChooser ocMemberChooser = memberChooser;
                    ocMemberChooser.show();
                    final OCMemberChooser ocMemberChooser2 = memberChooser;
                    final int n2 = ocMemberChooser2.getExitCode();
                    if (n2 != 0) {
                        return null;
                    }
                    break Label_0549;
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            Disposer.dispose(memberChooser.getDisposable());
        }
        final ArrayList<M> list6 = new ArrayList<M>();
        final List<OCMemberChooserObject> chosenElements = memberChooser.getChosenElements();
        if (chosenElements != null) {
            final Iterator<OCMemberChooserObject> iterator = chosenElements.iterator();
            while (iterator.hasNext()) {
                list6.add((M)iterator.next().getSymbol());
            }
        }
        else {
            try {
                if (!this.allowEmptySelection(c)) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
        }
        final Map<OCOption, Object> optionSelections = memberChooser.getOptionSelections();
        try {
            if (ocCodeStyleSettings != null) {
                this.saveOptions(psiFile, ocCodeStyleSettings, optionSelections);
            }
        }
        catch (IllegalArgumentException ex12) {
            throw a(ex12);
        }
        c.setOptionValues(optionSelections);
        return list6;
    }
    
    protected OCMemberChooser createMemberChooser(final Project project, final List<Pair<OCOption, Object>> list, final C c, final List<M> list2) {
        return new OCMemberChooser(new OCMemberChooserObject[0], this.allowEmptySelection(c), this.allowMultiSelection(c), list, null, project) {
            @Nullable
            protected String getHelpId() {
                return OCClassActionHandlerBase.this.getHelpID();
            }
        };
    }
    
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<M> collection, final C c, final int n) {
        return (OCMemberChooserObject[])ContainerUtil.map2Array((Collection)collection, (Class)OCMemberChooserObject.class, ocSymbolWithParent -> new OCMemberChooserObject(ocSymbolWithParent, map));
    }
    
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<M> collection, final OCOption ocOption, final C c) {
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Nullable
    @NonNls
    protected String getHelpID() {
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
