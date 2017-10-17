// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.psi.PsiFile;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import java.util.HashSet;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public class OCOverrideImplementActionContext extends OCObjCActionContext<OCMethodSymbol>
{
    protected Set<OCMethodSymbol> myAbstractMethods;
    protected Set<OCPropertySymbol> myPropertiesWithUnavailableIvars;
    
    public OCOverrideImplementActionContext(final OCClassSymbol ocClassSymbol, final OCObjectType ocObjectType, final PsiElement psiElement) {
        super(ocClassSymbol, psiElement, ocObjectType);
        this.myAbstractMethods = new HashSet<OCMethodSymbol>();
        this.myPropertiesWithUnavailableIvars = new HashSet<OCPropertySymbol>();
    }
    
    @Override
    public List<? extends OCSymbol> getSymbolsToModify() {
        return Collections.singletonList((OCSymbol)this.myImplementationSymbol);
    }
    
    @Override
    protected Class<OCMethodSymbol> getMemberSymbolClass() {
        return OCMethodSymbol.class;
    }
    
    public boolean isAbstract(final OCMethodSymbol ocMethodSymbol) {
        return this.myAbstractMethods.contains(ocMethodSymbol);
    }
    
    public boolean evaluateAvailableIvars(final PsiFile p0, final List<OCMethodSymbol> p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //     4: ifeq            463
        //     7: new             Ljava/util/HashSet;
        //    10: dup            
        //    11: invokespecial   java/util/HashSet.<init>:()V
        //    14: astore          4
        //    16: new             Ljava/util/HashSet;
        //    19: dup            
        //    20: invokespecial   java/util/HashSet.<init>:()V
        //    23: astore          5
        //    25: new             Ljava/util/ArrayList;
        //    28: dup            
        //    29: invokespecial   java/util/ArrayList.<init>:()V
        //    32: astore          6
        //    34: aload_2        
        //    35: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    40: astore          7
        //    42: aload           7
        //    44: invokeinterface java/util/Iterator.hasNext:()Z
        //    49: ifeq            115
        //    52: aload           7
        //    54: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    59: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    62: astore          8
        //    64: aload           8
        //    66: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    71: astore          9
        //    73: aload           9
        //    75: ifnull          112
        //    78: aload           4
        //    80: aload           9
        //    82: aload           5
        //    84: invokedynamic   process:(Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Ljava/util/Set;)Lcom/intellij/util/Processor;
        //    89: astore          10
        //    91: aload           9
        //    93: aload           10
        //    95: iconst_0       
        //    96: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processAccessorMethods:(Lcom/intellij/util/Processor;Z)Z
        //   101: pop            
        //   102: aload           10
        //   104: aload           8
        //   106: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   111: pop            
        //   112: goto            42
        //   115: aload           4
        //   117: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   122: astore          7
        //   124: aload           7
        //   126: invokeinterface java/util/Iterator.hasNext:()Z
        //   131: ifeq            273
        //   134: aload           7
        //   136: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   141: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   144: astore          8
        //   146: aload           8
        //   148: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedIvar:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   153: astore          9
        //   155: aload           9
        //   157: ifnull          270
        //   160: aload           9
        //   162: aload_1        
        //   163: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:(Lcom/intellij/psi/PsiFile;)Z
        //   168: ifeq            270
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   177: athrow         
        //   178: aload           8
        //   180: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   185: ifne            214
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   194: athrow         
        //   195: aload           5
        //   197: aload           8
        //   199: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   204: ifeq            270
        //   207: goto            214
        //   210: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   213: athrow         
        //   214: aload_0        
        //   215: getfield        com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.myPropertiesWithUnavailableIvars:Ljava/util/Set;
        //   218: aload           8
        //   220: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   225: pop            
        //   226: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   229: dup            
        //   230: aload           9
        //   232: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   235: invokestatic    com/intellij/psi/search/searches/ReferencesSearch.search:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/util/Query;
        //   238: invokeinterface com/intellij/util/Query.findFirst:()Ljava/lang/Object;
        //   243: ifnull          270
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   252: athrow         
        //   253: aload           6
        //   255: aload           8
        //   257: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   262: pop            
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   269: athrow         
        //   270: goto            124
        //   273: aload           6
        //   275: invokeinterface java/util/List.isEmpty:()Z
        //   280: ifne            463
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: ldc             "Instance variable"
        //   292: aload           6
        //   294: invokeinterface java/util/List.size:()I
        //   299: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;I)Ljava/lang/String;
        //   302: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   305: ldc             " for "
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: ldc             "property"
        //   312: aload           6
        //   314: invokeinterface java/util/List.size:()I
        //   319: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;I)Ljava/lang/String;
        //   322: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   325: ldc             " "
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: aload           6
        //   332: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   337: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   340: ldc             ", "
        //   342: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   345: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   348: aload           6
        //   350: invokeinterface java/util/List.size:()I
        //   355: iconst_1       
        //   356: if_icmple       375
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   365: athrow         
        //   366: ldc             " are"
        //   368: goto            377
        //   371: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   374: athrow         
        //   375: ldc             " is"
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: ldc             " used, but will not be available when "
        //   382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   385: aload           6
        //   387: invokeinterface java/util/List.size:()I
        //   392: iconst_1       
        //   393: if_icmple       405
        //   396: ldc             "their"
        //   398: goto            407
        //   401: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   404: athrow         
        //   405: ldc             "its"
        //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: ldc             " accessors are overridden. Do you want to proceed?"
        //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   418: astore          7
        //   420: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   423: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   428: ifeq            445
        //   431: new             Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   434: dup            
        //   435: aload           7
        //   437: invokespecial   com/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException.<init>:(Ljava/lang/String;)V
        //   440: athrow         
        //   441: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   444: athrow         
        //   445: aload           7
        //   447: aload_3        
        //   448: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   451: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   454: ifeq            463
        //   457: iconst_0       
        //   458: ireturn        
        //   459: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementActionContext.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   462: athrow         
        //   463: iconst_1       
        //   464: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;Ljava/lang/String;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                               
        //  -----  -----  -----  -----  -----------------------------------------------------------------------------------
        //  155    171    174    178    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  160    188    191    195    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  178    207    210    214    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  195    246    249    253    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  214    263    266    270    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  273    359    362    366    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  283    371    371    375    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  377    401    401    405    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  420    441    441    445    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  445    459    459    463    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0178:
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
    
    public boolean isIvarAvailable(final OCPropertySymbol ocPropertySymbol) {
        try {
            if (!this.myPropertiesWithUnavailableIvars.contains(ocPropertySymbol)) {
                return true;
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static CommonRefactoringUtil.RefactoringErrorHintException b(final CommonRefactoringUtil.RefactoringErrorHintException ex) {
        return ex;
    }
}
