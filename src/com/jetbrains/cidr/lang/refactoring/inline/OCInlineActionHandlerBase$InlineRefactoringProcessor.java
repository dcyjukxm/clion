// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiNamedElement;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.util.containers.MultiMap;
import java.util.ArrayList;
import com.intellij.openapi.util.Ref;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCUsageViewDescriptor;
import com.intellij.usageView.UsageViewDescriptor;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import com.intellij.openapi.util.EmptyRunnable;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.psi.PsiFile;
import java.util.Set;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageInfo;
import com.intellij.refactoring.BaseRefactoringProcessor;

private class InlineRefactoringProcessor extends BaseRefactoringProcessor
{
    private String myRefactoringName;
    private T myElement;
    private UsageInfo[] myUsageInfos;
    private String myFailMessage;
    private boolean mySilentMode;
    private boolean myDeleteElement;
    private boolean myWasConflict;
    private PsiElement myElementData;
    private List<String> myWarnings;
    private Set<PsiFile> myFilesToReparse;
    protected Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> myElemsToEscalateVisibility;
    
    private InlineRefactoringProcessor(final String myRefactoringName, final T myElement, final PsiElement myElementData, final List<PsiElement> list, final boolean myDeleteElement, final List<String> myWarnings, final boolean mySilentMode) {
        super(myElement.getProject(), EmptyRunnable.INSTANCE);
        this.myFilesToReparse = new HashSet<PsiFile>();
        this.myElemsToEscalateVisibility = new HashMap<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>>();
        this.myRefactoringName = myRefactoringName;
        this.myElement = myElement;
        this.myElementData = myElementData;
        this.myDeleteElement = myDeleteElement;
        this.mySilentMode = mySilentMode;
        this.myUsageInfos = new UsageInfo[list.size()];
        this.myWarnings = myWarnings;
        int i = 0;
        try {
            while (i < this.myUsageInfos.length) {
                this.myUsageInfos[i] = new UsageInfo((PsiElement)list.get(i));
                ++i;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myWasConflict = false;
    }
    
    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCUsageViewDescriptor ocUsageViewDescriptor;
        try {
            ocUsageViewDescriptor = new OCUsageViewDescriptor((PsiElement)this.myElement, "elements to " + this.myRefactoringName);
            if (ocUsageViewDescriptor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (UsageViewDescriptor)ocUsageViewDescriptor;
    }
    
    @NotNull
    @Override
    protected UsageInfo[] findUsages() {
        UsageInfo[] myUsageInfos;
        try {
            myUsageInfos = this.myUsageInfos;
            if (myUsageInfos == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor", "findUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myUsageInfos;
    }
    
    @NotNull
    @Override
    protected Collection<? extends PsiElement> getElementsToWrite(@NotNull final UsageViewDescriptor p0) {
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
        //    18: ldc             "descriptor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getElementsToWrite"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myDeleteElement:Z
        //    48: ifeq            125
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElement:Lcom/intellij/psi/PsiNamedElement;
        //    55: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/intellij/psi/PsiElement;)Z
        //    58: ifeq            125
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase;
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElement:Lcom/intellij/psi/PsiNamedElement;
        //    76: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase.getElementsToWrite:(Lcom/intellij/psi/PsiNamedElement;)Ljava/util/List;
        //    79: dup            
        //    80: ifnonnull       124
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: new             Ljava/lang/IllegalStateException;
        //    93: dup            
        //    94: ldc             "@NotNull method %s.%s must not return null"
        //    96: ldc             2
        //    98: anewarray       Ljava/lang/Object;
        //   101: dup            
        //   102: ldc             0
        //   104: ldc             "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor"
        //   106: aastore        
        //   107: dup            
        //   108: ldc             1
        //   110: ldc             "getElementsToWrite"
        //   112: aastore        
        //   113: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   116: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   119: athrow         
        //   120: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: areturn        
        //   125: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   128: dup            
        //   129: ifnonnull       166
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "getElementsToWrite"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: areturn        
        //    Signature:
        //  (Lcom/intellij/usageView/UsageViewDescriptor;)Ljava/util/Collection<+Lcom/intellij/psi/PsiElement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     61     64     68     Ljava/lang/IllegalArgumentException;
        //  51     83     86     90     Ljava/lang/IllegalArgumentException;
        //  68     120    120    124    Ljava/lang/IllegalArgumentException;
        //  125    162    162    166    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    protected boolean preprocessUsages(@NotNull final Ref<UsageInfo[]> ref) {
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refUsages", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor", "preprocessUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<UsageInfo> list = new ArrayList<UsageInfo>();
        final MultiMap multiMap = new MultiMap();
        final Iterator<String> iterator = this.myWarnings.iterator();
        while (iterator.hasNext()) {
            multiMap.putValue((Object)this.myElement, (Object)iterator.next());
        }
        for (final UsageInfo usageInfo : (UsageInfo[])ref.get()) {
            final PsiElement element = usageInfo.getElement();
            final String checkUsageValid = OCInlineActionHandlerBase.this.checkUsageValid(element, this.myElement);
            Label_0266: {
                Label_0233: {
                    Label_0211: {
                        Label_0183: {
                            try {
                                if (checkUsageValid == null) {
                                    break Label_0211;
                                }
                                final Ref<UsageInfo[]> ref2 = ref;
                                final Object o = ref2.get();
                                final UsageInfo[] array2 = (UsageInfo[])o;
                                final int n = array2.length;
                                final boolean b = true;
                                if (n == (b ? 1 : 0)) {
                                    break Label_0183;
                                }
                                break Label_0183;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            try {
                                final Ref<UsageInfo[]> ref2 = ref;
                                final Object o = ref2.get();
                                final UsageInfo[] array2 = (UsageInfo[])o;
                                final int n = array2.length;
                                final boolean b = true;
                                if (n == (b ? 1 : 0)) {
                                    this.myFailMessage = checkUsageValid;
                                    return false;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        multiMap.putValue((Object)element, (Object)checkUsageValid);
                        this.myWasConflict = true;
                        break Label_0266;
                        try {
                            if (element == null) {
                                break Label_0266;
                            }
                            final PsiElement psiElement = element;
                            final Class<OCMacroCallArgument> clazz = OCMacroCallArgument.class;
                            final PsiElement psiElement2 = PsiTreeUtil.getParentOfType(psiElement, (Class)clazz);
                            if (psiElement2 != null) {
                                break Label_0233;
                            }
                            break Label_0233;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final PsiElement psiElement = element;
                        final Class<OCMacroCallArgument> clazz = OCMacroCallArgument.class;
                        final PsiElement psiElement2 = PsiTreeUtil.getParentOfType(psiElement, (Class)clazz);
                        if (psiElement2 != null) {
                            this.myFilesToReparse.add(element.getContainingFile());
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                list.add(usageInfo);
            }
        }
        Label_0320: {
            try {
                ref.set((Object)list.toArray(new UsageInfo[list.size()]));
                if (this.mySilentMode) {
                    break Label_0320;
                }
                final InlineRefactoringProcessor inlineRefactoringProcessor = this;
                final MultiMap multiMap2 = multiMap;
                final Ref<UsageInfo[]> ref3 = ref;
                final Object o2 = ref3.get();
                final UsageInfo[] array3 = (UsageInfo[])o2;
                final boolean b2 = inlineRefactoringProcessor.showConflicts((MultiMap<PsiElement, String>)multiMap2, array3);
                if (b2) {
                    break Label_0320;
                }
                return false;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final InlineRefactoringProcessor inlineRefactoringProcessor = this;
                final MultiMap multiMap2 = multiMap;
                final Ref<UsageInfo[]> ref3 = ref;
                final Object o2 = ref3.get();
                final UsageInfo[] array3 = (UsageInfo[])o2;
                final boolean b2 = inlineRefactoringProcessor.showConflicts((MultiMap<PsiElement, String>)multiMap2, array3);
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return false;
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performRefactoring"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase;
        //    48: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase.getFeatureID:()Ljava/lang/String;
        //    51: astore_2       
        //    52: aload_2        
        //    53: ifnull          70
        //    56: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //    59: aload_2        
        //    60: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_1        
        //    71: astore_3       
        //    72: aload_3        
        //    73: arraylength    
        //    74: istore          4
        //    76: iconst_0       
        //    77: istore          5
        //    79: iload           5
        //    81: iload           4
        //    83: if_icmpge       126
        //    86: aload_3        
        //    87: iload           5
        //    89: aaload         
        //    90: astore          6
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase;
        //    96: aload           6
        //    98: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElement:Lcom/intellij/psi/PsiNamedElement;
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElementData:Lcom/intellij/psi/PsiElement;
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElemsToEscalateVisibility:Ljava/util/Map;
        //   117: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase.inlineUsage:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/project/Project;Ljava/util/Map;)V
        //   120: iinc            5, 1
        //   123: goto            79
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myDeleteElement:Z
        //   130: ifeq            186
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myWasConflict:Z
        //   137: ifne            186
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_0        
        //   148: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElement:Lcom/intellij/psi/PsiNamedElement;
        //   151: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/intellij/psi/PsiElement;)Z
        //   154: ifeq            186
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aload_0        
        //   165: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.this$0:Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase;
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElement:Lcom/intellij/psi/PsiNamedElement;
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myElementData:Lcom/intellij/psi/PsiElement;
        //   176: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase.deleteElement:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/psi/PsiElement;)V
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myFilesToReparse:Ljava/util/Set;
        //   190: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   195: astore_3       
        //   196: aload_3        
        //   197: invokeinterface java/util/Iterator.hasNext:()Z
        //   202: ifeq            234
        //   205: aload_3        
        //   206: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   211: checkcast       Lcom/intellij/psi/PsiFile;
        //   214: astore          4
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase$InlineRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   220: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   223: aload           4
        //   225: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   228: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.scheduleReparseFile:(Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //   231: goto            196
        //   234: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  52     63     66     70     Ljava/lang/IllegalArgumentException;
        //  126    140    143    147    Ljava/lang/IllegalArgumentException;
        //  133    157    160    164    Ljava/lang/IllegalArgumentException;
        //  147    179    182    186    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0147:
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
    protected void performPsiSpoilingRefactoring() {
        OCBindUtil.escalateVisibilities(this.myProject, this.myElemsToEscalateVisibility, new VirtualFile[0]);
    }
    
    public void execute() {
        try {
            if (this.mySilentMode) {
                final Ref<UsageInfo[]> ref;
                ApplicationManager.getApplication().runWriteAction(() -> {
                    Ref.create((Object)this.findUsages());
                    try {
                        if (this.preprocessUsages(ref)) {
                            this.performRefactoring((UsageInfo[])ref.get());
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                });
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.run();
    }
    
    @Override
    protected String getCommandName() {
        return this.myRefactoringName;
    }
    
    public String getFailMessage() {
        return this.myFailMessage;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
