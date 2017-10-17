// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCUsageInfo;
import com.intellij.refactoring.util.NonCodeUsageInfo;
import java.util.ArrayList;
import com.intellij.usages.UsageInfo2UsageAdapter;
import com.intellij.usages.Usage;
import javax.swing.Action;
import org.jetbrains.annotations.Nullable;
import com.intellij.usages.UsageView;
import com.intellij.refactoring.ui.ConflictsDialog;
import java.util.Iterator;
import com.intellij.psi.PsiNamedElement;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Set;
import java.util.Collection;
import com.intellij.util.containers.HashSet;
import java.util.Arrays;
import com.intellij.refactoring.rename.RenameUtil;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.OCUsageViewDescriptor;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.usageView.UsageViewDescriptor;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.intellij.refactoring.changeSignature.ChangeInfo;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.rename.OCRenameProcessor;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.util.Key;
import com.intellij.refactoring.changeSignature.ChangeSignatureProcessorBase;

public class OCChangeSignatureProcessor extends ChangeSignatureProcessorBase
{
    public static Key<Boolean> CHECK_CONFLICTS;
    private OCCallableKind myCallableKind;
    private OCCallable myCallable;
    private String myCommandName;
    private OCRenameProcessor.RenameUsages myOption;
    
    protected OCChangeSignatureProcessor(final Project project, final OCCallableKind myCallableKind, final OCCallable myCallable, final OCChangeInfo ocChangeInfo, final String myCommandName) {
        super(project, (ChangeInfo)ocChangeInfo);
        this.myCallableKind = myCallableKind;
        this.myCallable = myCallable;
        this.myCommandName = myCommandName;
    }
    
    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        OCUsageViewDescriptor ocUsageViewDescriptor;
        try {
            ocUsageViewDescriptor = new OCUsageViewDescriptor((PsiElement)this.myCallable, RefactoringBundle.message("0.to.change.signature", new Object[] { this.myCallableKind.toString() }));
            if (ocUsageViewDescriptor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
            throw b(ex2);
        }
        return (UsageViewDescriptor)ocUsageViewDescriptor;
    }
    
    @Override
    protected boolean preprocessUsages(@NotNull final Ref<UsageInfo[]> ref) {
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refUsages", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "preprocessUsages"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        final MultiMap multiMap = new MultiMap();
        ChangeSignatureProcessorBase.collectConflictsFromExtensions(ref, (MultiMap<PsiElement, String>)multiMap, this.myChangeInfo);
        final UsageInfo[] array = (UsageInfo[])ref.get();
        RenameUtil.addConflictDescriptions(array, (MultiMap<PsiElement, String>)multiMap);
        final HashSet set = new HashSet((Collection)Arrays.asList(array));
        Label_0281: {
            Label_0237: {
                Label_0130: {
                    try {
                        RenameUtil.removeConflictUsages((Set<UsageInfo>)set);
                        if (multiMap.isEmpty()) {
                            break Label_0281;
                        }
                        final OCChangeSignatureProcessor ocChangeSignatureProcessor = this;
                        final OCCallable ocCallable = ocChangeSignatureProcessor.myCallable;
                        final Project project = ocCallable.getProject();
                        final Key<Boolean> key = OCChangeSignatureProcessor.CHECK_CONFLICTS;
                        final Object o = project.getUserData((Key)key);
                        final Boolean b = Boolean.FALSE;
                        if (o != b) {
                            break Label_0130;
                        }
                        break Label_0281;
                    }
                    catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCChangeSignatureProcessor ocChangeSignatureProcessor = this;
                        final OCCallable ocCallable = ocChangeSignatureProcessor.myCallable;
                        final Project project = ocCallable.getProject();
                        final Key<Boolean> key = OCChangeSignatureProcessor.CHECK_CONFLICTS;
                        final Object o = project.getUserData((Key)key);
                        final Boolean b = Boolean.FALSE;
                        if (o == b) {
                            break Label_0281;
                        }
                        if (!ApplicationManager.getApplication().isUnitTestMode()) {
                            break Label_0237;
                        }
                    }
                    catch (CommonRefactoringUtil.RefactoringErrorHintException ex3) {
                        throw b(ex3);
                    }
                }
                final StringBuilder sb = new StringBuilder();
                for (final String s : multiMap.values()) {
                    try {
                        if (sb.length() != 0) {
                            sb.append("; ");
                        }
                    }
                    catch (CommonRefactoringUtil.RefactoringErrorHintException ex4) {
                        throw b(ex4);
                    }
                    sb.append(s);
                }
                throw new CommonRefactoringUtil.RefactoringErrorHintException(sb.toString());
            }
            final ConflictsDialog prepareConflictsDialog = this.prepareConflictsDialog((MultiMap<PsiElement, String>)multiMap, array);
            Label_0268: {
                try {
                    if (prepareConflictsDialog.showAndGet()) {
                        break Label_0281;
                    }
                    final ConflictsDialog conflictsDialog = prepareConflictsDialog;
                    final boolean b2 = conflictsDialog.isShowConflicts();
                    if (b2) {
                        break Label_0268;
                    }
                    return false;
                }
                catch (CommonRefactoringUtil.RefactoringErrorHintException ex5) {
                    throw b(ex5);
                }
                try {
                    final ConflictsDialog conflictsDialog = prepareConflictsDialog;
                    final boolean b2 = conflictsDialog.isShowConflicts();
                    if (b2) {
                        this.prepareSuccessful();
                    }
                }
                catch (CommonRefactoringUtil.RefactoringErrorHintException ex6) {
                    throw b(ex6);
                }
            }
            return false;
        }
        final UsageInfo[] array2 = ((Set<UsageInfo>)set).toArray(new UsageInfo[((Set)set).size()]);
        Label_0344: {
            try {
                ref.set((Object)array2);
                this.prepareSuccessful();
                if (this.myChangeInfo.isNameChanged()) {
                    final OCRenameProcessor.RenameUsages myOption = OCRenameProcessor.validateUsages((PsiNamedElement)this.myCallable, ref);
                    break Label_0344;
                }
            }
            catch (CommonRefactoringUtil.RefactoringErrorHintException ex7) {
                throw b(ex7);
            }
            final OCRenameProcessor.RenameUsages myOption = OCRenameProcessor.RenameUsages.RENAME;
            try {
                this.myOption = myOption;
                if (this.myOption != OCRenameProcessor.RenameUsages.CANCEL) {
                    return true;
                }
            }
            catch (CommonRefactoringUtil.RefactoringErrorHintException ex8) {
                throw b(ex8);
            }
        }
        return false;
    }
    
    @Override
    protected boolean isPreviewUsages(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "isPreviewUsages"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        Label_0069: {
            try {
                if (super.isPreviewUsages(array)) {
                    break Label_0069;
                }
                final OCChangeSignatureProcessor ocChangeSignatureProcessor = this;
                final OCRenameProcessor.RenameUsages renameUsages = ocChangeSignatureProcessor.myOption;
                final OCRenameProcessor.RenameUsages renameUsages2 = OCRenameProcessor.RenameUsages.SHOW_USAGES;
                if (renameUsages == renameUsages2) {
                    break Label_0069;
                }
                return false;
            }
            catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
                throw b(ex2);
            }
            try {
                final OCChangeSignatureProcessor ocChangeSignatureProcessor = this;
                final OCRenameProcessor.RenameUsages renameUsages = ocChangeSignatureProcessor.myOption;
                final OCRenameProcessor.RenameUsages renameUsages2 = OCRenameProcessor.RenameUsages.SHOW_USAGES;
                if (renameUsages == renameUsages2) {
                    return true;
                }
            }
            catch (CommonRefactoringUtil.RefactoringErrorHintException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    protected ConflictsDialog createConflictsDialog(@NotNull final MultiMap<PsiElement, String> multiMap, final UsageInfo[] array) {
        try {
            if (multiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conflicts", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "createConflictsDialog"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        ConflictsDialog conflictsDialog;
        try {
            conflictsDialog = new ConflictsDialog(this.myProject, multiMap, null) {
                @Override
                protected Runnable getDoRefactoringRunnable(@Nullable final UsageView usageView) {
                    final Collection collection;
                    return () -> {
                        a(array, usageView);
                        OCChangeSignatureProcessor.this.execute(collection.toArray(new UsageInfo[collection.size()]));
                    };
                }
                
                @NotNull
                protected Action getOKAction() {
                    final Action okAction = super.getOKAction();
                    for (final String s : multiMap.values()) {
                        try {
                            if (OCChangeSignatureUsageProcessor.canProceedWithConflict(s)) {
                                continue;
                            }
                            okAction.setEnabled(false);
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                    }
                    Action action;
                    try {
                        action = okAction;
                        if (action == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor$1", "getOKAction"));
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    return action;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (conflictsDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "createConflictsDialog"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
            throw b(ex2);
        }
        return conflictsDialog;
    }
    
    private static Collection<? extends UsageInfo> a(final UsageInfo[] array, @Nullable final UsageView usageView) {
        final MultiMap linked = MultiMap.createLinked();
        for (final UsageInfo usageInfo : array) {
            final PsiElement element = usageInfo.getElement();
            try {
                if (element != null) {
                    linked.putValue((Object)element, (Object)usageInfo);
                }
            }
            catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
                throw b(ex);
            }
        }
        if (usageView != null) {
            for (final Usage usage : usageView.getExcludedUsages()) {
                try {
                    if (!(usage instanceof UsageInfo2UsageAdapter)) {
                        continue;
                    }
                    linked.remove((Object)((UsageInfo2UsageAdapter)usage).getElement());
                }
                catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
                    throw b(ex2);
                }
            }
        }
        return (Collection<? extends UsageInfo>)linked.values();
    }
    
    @Override
    protected void performRefactoring(@NotNull final UsageInfo[] p0) {
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
        //    18: ldc             "usages"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performRefactoring"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //    43: athrow         
        //    44: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //    47: ldc             "refactoring.changeSignature"
        //    49: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //    52: aload_0        
        //    53: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.myChangeInfo:Lcom/intellij/refactoring/changeSignature/ChangeInfo;
        //    56: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //    59: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    62: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    65: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    70: astore_2       
        //    71: aload_1        
        //    72: invokedynamic   compare:()Ljava/util/Comparator;
        //    77: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //    84: aload_1        
        //    85: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //    90: invokestatic    com/intellij/util/containers/ContainerUtil.filter:([Ljava/lang/Object;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //    93: iconst_0       
        //    94: anewarray       Lcom/intellij/refactoring/util/NonCodeUsageInfo;
        //    97: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   102: checkcast       [Lcom/intellij/refactoring/util/NonCodeUsageInfo;
        //   105: invokestatic    com/intellij/refactoring/rename/RenameUtil.renameNonCodeUsages:(Lcom/intellij/openapi/project/Project;[Lcom/intellij/refactoring/util/NonCodeUsageInfo;)V
        //   108: aload_0        
        //   109: aload_1        
        //   110: invokespecial   com/intellij/refactoring/changeSignature/ChangeSignatureProcessorBase.performRefactoring:([Lcom/intellij/usageView/UsageInfo;)V
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.myChangeInfo:Lcom/intellij/refactoring/changeSignature/ChangeInfo;
        //   117: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //   120: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   123: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getMethodReference:()Lcom/intellij/psi/PsiElement;
        //   126: ifnonnull       190
        //   129: aload_0        
        //   130: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.myChangeInfo:Lcom/intellij/refactoring/changeSignature/ChangeInfo;
        //   133: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getMethod:()Lcom/intellij/psi/PsiElement;
        //   138: astore_3       
        //   139: aload_3        
        //   140: ifnull          190
        //   143: aload_3        
        //   144: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   149: ifeq            190
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   158: athrow         
        //   159: aload_2        
        //   160: aload_3        
        //   161: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   166: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   169: ifne            190
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   178: athrow         
        //   179: aload_3        
        //   180: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.selectElement:(Lcom/intellij/psi/PsiElement;)V
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor.b:(Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;)Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //   189: athrow         
        //   190: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                               
        //  -----  -----  -----  -----  -----------------------------------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  139    152    155    159    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  143    172    175    179    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        //  159    183    186    190    Lcom/intellij/refactoring/util/CommonRefactoringUtil$RefactoringErrorHintException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0159:
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
    @Override
    protected Collection<? extends PsiElement> getElementsToWrite(@NotNull final UsageViewDescriptor usageViewDescriptor) {
        try {
            if (usageViewDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "getElementsToWrite"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<PsiElement>();
        ArrayList<Object> list2;
        try {
            list.addAll(super.getElementsToWrite(usageViewDescriptor));
            list.addAll(((OCChangeInfo)this.myChangeInfo).getGenerated().getFilesToWrite());
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureProcessor", "getElementsToWrite"));
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex2) {
            throw b(ex2);
        }
        return (Collection<? extends PsiElement>)list2;
    }
    
    public void runSynchronously() {
        this.doRun();
    }
    
    @Override
    protected String getCommandName() {
        try {
            if (this.myCommandName != null) {
                return this.myCommandName;
            }
        }
        catch (CommonRefactoringUtil.RefactoringErrorHintException ex) {
            throw b(ex);
        }
        return super.getCommandName();
    }
    
    static {
        OCChangeSignatureProcessor.CHECK_CONFLICTS = (Key<Boolean>)Key.create("CHECK_CONFLICTS");
    }
    
    private static CommonRefactoringUtil.RefactoringErrorHintException b(final CommonRefactoringUtil.RefactoringErrorHintException ex) {
        return ex;
    }
}
