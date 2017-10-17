// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.refactoring.rename.ResolveSnapshotProvider;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import java.util.Collections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Ref;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCSelfSuperUsage;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.refactoring.rename.UnresolvableCollisionUsageInfo;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCMethodDefinitionUsage;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCBlockDefinitionUsage;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCParameterUsage;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCFunctionDefinitionUsage;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import java.util.Collection;
import com.intellij.refactoring.util.TextOccurrencesUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCUsageInfo;
import com.intellij.openapi.util.Pair;
import java.util.Set;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCGeneratedCallUsage;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCGeneratedMethodUsage;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.intellij.refactoring.changeSignature.ChangeInfo;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.refactoring.changeSignature.ChangeSignatureUsageProcessor;

public class OCChangeSignatureUsageProcessor implements ChangeSignatureUsageProcessor
{
    protected static final Logger LOG;
    private static final String BLOCK_CONFLICT;
    
    public UsageInfo[] findUsages(final ChangeInfo changeInfo) {
        try {
            if (!(changeInfo instanceof OCChangeInfo)) {
                return UsageInfo.EMPTY_ARRAY;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a((OCChangeInfo)changeInfo, false);
    }
    
    private static UsageInfo[] a(@NotNull final OCChangeInfo ocChangeInfo, final boolean b) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor", "findUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCallable method = ocChangeInfo.getMethod();
        try {
            if (!method.isValid()) {
                return UsageInfo.EMPTY_ARRAY;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HashSet set = new HashSet();
        Label_0122: {
            try {
                if (ocChangeInfo.getGenerated().getMethodReference() == null) {
                    break Label_0122;
                }
                final Object o = set;
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final OCGeneratedInfo ocGeneratedInfo = ocChangeInfo2.getGenerated();
                final PsiElement psiElement = ocGeneratedInfo.getMethodReference();
                final OCGeneratedMethodUsage ocGeneratedMethodUsage = new OCGeneratedMethodUsage(psiElement);
                ((Set<OCGeneratedMethodUsage>)o).add(ocGeneratedMethodUsage);
                final OCChangeInfo ocChangeInfo3 = ocChangeInfo;
                final OCGeneratedInfo ocGeneratedInfo2 = ocChangeInfo3.getGenerated();
                final String s = ocGeneratedInfo2.getCallString();
                if (s != null) {
                    break Label_0122;
                }
                break Label_0122;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Object o = set;
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final OCGeneratedInfo ocGeneratedInfo = ocChangeInfo2.getGenerated();
                final PsiElement psiElement = ocGeneratedInfo.getMethodReference();
                final OCGeneratedMethodUsage ocGeneratedMethodUsage = new OCGeneratedMethodUsage(psiElement);
                ((Set<OCGeneratedMethodUsage>)o).add(ocGeneratedMethodUsage);
                final OCChangeInfo ocChangeInfo3 = ocChangeInfo;
                final OCGeneratedInfo ocGeneratedInfo2 = ocChangeInfo3.getGenerated();
                final String s = ocGeneratedInfo2.getCallString();
                if (s != null) {
                    ((Set<OCGeneratedMethodUsage>)set).add((OCGeneratedMethodUsage)new OCGeneratedCallUsage(ocChangeInfo.getGenerated().getMethodReference()));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        a(ocChangeInfo, method, b, (Set<UsageInfo>)set);
        final Iterator<Pair<Integer, Runnable>> iterator = ocChangeInfo.getGenerated().getCallbacks().iterator();
        while (iterator.hasNext()) {
            ((Set<OCGeneratedMethodUsage>)set).add((OCGeneratedMethodUsage)new OCUsageInfo<PsiElement>(ocChangeInfo.getContext()) {
                final /* synthetic */ Pair val$pair = iterator.next();
                
                @Override
                public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final PsiElement psiElement, @NotNull final Project project) {
                    try {
                        if (ocChangeInfo == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor$1", "processUsage"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        if (psiElement == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor$1", "processUsage"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        if (project == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor$1", "processUsage"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    ((Runnable)this.val$pair.getSecond()).run();
                    return true;
                }
                
                @Override
                public int getUsageRank() {
                    return (int)this.val$pair.getFirst();
                }
                
                public int hashCode() {
                    return 0;
                }
                
                public boolean equals(final Object o) {
                    try {
                        if (this == o) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return false;
                }
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            });
        }
        return ((Set<OCGeneratedMethodUsage>)set).toArray(new UsageInfo[((Set)set).size()]);
    }
    
    private static void a(@NotNull final OCChangeInfo ocChangeInfo, final OCCallable ocCallable, final boolean b, final Set<UsageInfo> set) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor", "findAllMethodUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0078: {
            try {
                a(ocCallable, ocChangeInfo, set, b, false);
                if (ocCallable instanceof OCBlockExpression) {
                    return;
                }
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b2 = ocChangeInfo2.isChangeUsages();
                if (!b2) {
                    return;
                }
                break Label_0078;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b2 = ocChangeInfo2.isChangeUsages();
                if (!b2) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        OCSearchUtil.processMembersHierarchyPsi(ocCallable.getSymbol(), (Processor<PsiElement>)(psiElement -> {
            try {
                if (ocChangeInfo == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor", "lambda$findAllMethodUsages$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0068: {
                try {
                    if (!(psiElement instanceof OCCallable)) {
                        return true;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCCallable ocCallable2 = ocCallable;
                    final boolean b2 = Comparing.equal((Object)psiElement2, (Object)ocCallable2);
                    if (!b2) {
                        break Label_0068;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCCallable ocCallable2 = ocCallable;
                    final boolean b2 = Comparing.equal((Object)psiElement2, (Object)ocCallable2);
                    if (!b2) {
                        a((OCCallable)psiElement, ocChangeInfo, set, b, true);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return true;
        }), ocChangeInfo.isChangeAncestors(), true);
    }
    
    private static void a(final OCCallable ocCallable, final OCChangeInfo ocChangeInfo, final Set<UsageInfo> set, final boolean b, final boolean b2) {
        Label_0063: {
            if (ocChangeInfo.isChangeUsages()) {
                OCSearchUtil.findOneMethodUsages((PsiElement)ocCallable, set);
                final String name = ocChangeInfo.getMethod().getName();
                Label_0043: {
                    try {
                        if (name == null) {
                            break Label_0063;
                        }
                        final String s = name;
                        final boolean b3 = s.isEmpty();
                        if (!b3) {
                            break Label_0043;
                        }
                        break Label_0063;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s = name;
                        final boolean b3 = s.isEmpty();
                        if (!b3) {
                            TextOccurrencesUtil.findNonCodeUsages((PsiElement)ocCallable, name, true, true, ocChangeInfo.getNewName(), set);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            try {
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        Label_0189: {
            Label_0123: {
                Label_0096: {
                    try {
                        if (ocChangeInfo.getGenerated().getMethodReference() != null) {
                            break Label_0189;
                        }
                        final PsiElement psiElement = (PsiElement)ocCallable;
                        final boolean b4 = psiElement instanceof OCFunctionDeclaration;
                        if (b4) {
                            break Label_0096;
                        }
                        break Label_0123;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final PsiElement psiElement = (PsiElement)ocCallable;
                        final boolean b4 = psiElement instanceof OCFunctionDeclaration;
                        if (b4) {
                            set.add(new OCFunctionDefinitionUsage((OCFunctionDeclaration)ocCallable, b2));
                            break Label_0189;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (ocCallable instanceof OCBlockExpression) {
                        set.add(new OCBlockDefinitionUsage((OCBlockExpression)ocCallable));
                        break Label_0189;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                if (ocCallable instanceof OCMethod) {
                    set.add(new OCMethodDefinitionUsage((OCMethod)ocCallable, b2));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        final List parameters = ocCallable.getParameters();
        try {
            if (parameters == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        for (final OCParameterInfo ocParameterInfo : ocChangeInfo.getNewParameters()) {
            if (ocParameterInfo.getUsages() != null) {
                final Iterator<PsiReference> iterator = ocParameterInfo.getUsages().iterator();
                while (iterator.hasNext()) {
                    set.add(new OCParameterUsage(iterator.next(), ocParameterInfo, ocCallable, b2));
                }
            }
            Label_0481: {
                Label_0331: {
                    try {
                        if (ocParameterInfo.getOldIndex() < 0) {
                            break Label_0481;
                        }
                        final OCParameterInfo ocParameterInfo2 = ocParameterInfo;
                        final int n = ocParameterInfo2.getOldIndex();
                        final List list = parameters;
                        final int n2 = list.size();
                        if (n >= n2) {
                            break Label_0331;
                        }
                        break Label_0331;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                    try {
                        final OCParameterInfo ocParameterInfo2 = ocParameterInfo;
                        final int n = ocParameterInfo2.getOldIndex();
                        final List list = parameters;
                        final int n2 = list.size();
                        if (n >= n2) {
                            break Label_0481;
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw a(ex10);
                    }
                }
                final PsiNamedElement psiNamedElement = (PsiNamedElement)parameters.get(ocParameterInfo.getOldIndex());
                if (!Comparing.equal(psiNamedElement.getName(), ocParameterInfo.getName())) {
                    for (final PsiReference psiReference : ReferencesSearch.search((PsiElement)psiNamedElement, psiNamedElement.getUseScope())) {
                        try {
                            if (ocParameterInfo.getName().isEmpty()) {
                                set.add((OCParameterUsage)new UnresolvableCollisionUsageInfo(psiReference.getElement(), psiNamedElement) {
                                    @Override
                                    public String getDescription() {
                                        return OCBundle.message("changeSignature.usages.cantBeUnnamed", psiNamedElement.getName());
                                    }
                                });
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                        set.add(new OCParameterUsage(psiReference, ocParameterInfo, ocCallable, b2));
                    }
                }
            }
        }
        final Set map2Set = ContainerUtil.map2Set((Object[])ocChangeInfo.getNewParameters(), ocParameterInfo -> ocParameterInfo.getOldIndex());
        int n3 = 0;
        while (true) {
            Label_0621: {
                try {
                    if (n3 >= parameters.size()) {
                        break;
                    }
                    if (map2Set.contains(n3)) {
                        break Label_0621;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
                final PsiNamedElement psiNamedElement2 = (PsiNamedElement)parameters.get(n3);
                final Iterator iterator3 = ReferencesSearch.search((PsiElement)psiNamedElement2, psiNamedElement2.getUseScope()).iterator();
                while (iterator3.hasNext()) {
                    set.add((OCParameterUsage)new UnresolvableCollisionUsageInfo(iterator3.next().getElement(), psiNamedElement2) {
                        @Override
                        public String getDescription() {
                            return OCBundle.message("changeSignature.usages.cantBeRemoved", psiNamedElement2.getName());
                        }
                    });
                }
            }
            ++n3;
        }
        for (final OCReferenceExpression ocReferenceExpression : ocChangeInfo.getOldMethodDescriptor().getSelfReferences()) {
            try {
                if (!ocReferenceExpression.isValid()) {
                    continue;
                }
                set.add(new OCSelfSuperUsage(ocReferenceExpression));
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
        }
    }
    
    public static boolean canProceedWithConflict(final String s) {
        try {
            if (!s.startsWith(OCChangeSignatureUsageProcessor.BLOCK_CONFLICT)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public MultiMap<PsiElement, String> findConflicts(final ChangeInfo p0, final Ref<UsageInfo[]> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //     4: ifne            15
        //     7: invokestatic    com/intellij/util/containers/MultiMap.empty:()Lcom/intellij/util/containers/MultiMap;
        //    10: areturn        
        //    11: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: new             Lcom/intellij/util/containers/MultiMap;
        //    18: dup            
        //    19: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //    22: astore_3       
        //    23: aload_1        
        //    24: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getMethod:()Lcom/intellij/psi/PsiElement;
        //    29: astore          4
        //    31: aload_1        
        //    32: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //    35: astore          5
        //    37: new             Ljava/lang/StringBuilder;
        //    40: dup            
        //    41: invokespecial   java/lang/StringBuilder.<init>:()V
        //    44: aload           5
        //    46: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    52: ldc             " '"
        //    54: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    57: aload           5
        //    59: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: ldc             "'"
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    73: astore          6
        //    75: aload           5
        //    77: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //    80: ifne            580
        //    83: aload_2        
        //    84: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    87: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //    90: astore          7
        //    92: aload           7
        //    94: arraylength    
        //    95: istore          8
        //    97: iconst_0       
        //    98: istore          9
        //   100: iload           9
        //   102: iload           8
        //   104: if_icmpge       246
        //   107: aload           7
        //   109: iload           9
        //   111: aaload         
        //   112: astore          10
        //   114: aload           10
        //   116: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   119: astore          11
        //   121: aload           11
        //   123: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   126: ifeq            154
        //   129: aload_3        
        //   130: aload           10
        //   132: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   135: ldc             "changeSignature.usages.cantConvertSelectorToFuncBlock"
        //   137: iconst_0       
        //   138: anewarray       Ljava/lang/Object;
        //   141: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   144: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           11
        //   156: ifnull          240
        //   159: aload           11
        //   161: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
        //   166: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   169: ifeq            240
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload           11
        //   181: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
        //   186: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   194: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   197: ifeq            240
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload_3        
        //   208: aload           11
        //   210: ldc             "changeSignature.usages.ambiguousReference"
        //   212: iconst_1       
        //   213: anewarray       Ljava/lang/Object;
        //   216: dup            
        //   217: iconst_0       
        //   218: aload           5
        //   220: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   223: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getName:()Ljava/lang/String;
        //   226: aastore        
        //   227: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   230: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: iinc            9, 1
        //   243: goto            100
        //   246: aload_1        
        //   247: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //   250: iconst_1       
        //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Z)[Lcom/intellij/usageView/UsageInfo;
        //   254: astore          7
        //   256: aload           5
        //   258: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   261: ifeq            580
        //   264: aload           5
        //   266: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   269: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   272: ifne            580
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: aload           5
        //   284: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   287: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getMethodReference:()Lcom/intellij/psi/PsiElement;
        //   290: ifnonnull       580
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: aload_2        
        //   301: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   304: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //   307: astore          8
        //   309: aload           8
        //   311: arraylength    
        //   312: istore          9
        //   314: iconst_0       
        //   315: istore          10
        //   317: iload           10
        //   319: iload           9
        //   321: if_icmpge       410
        //   324: aload           8
        //   326: iload           10
        //   328: aaload         
        //   329: astore          11
        //   331: aload           11
        //   333: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   336: astore          12
        //   338: aload           12
        //   340: ifnull          404
        //   343: invokestatic    com/jetbrains/cidr/lang/OCLanguage.getInstance:()Lcom/jetbrains/cidr/lang/OCLanguage;
        //   346: aload           12
        //   348: invokeinterface com/intellij/psi/PsiElement.getLanguage:()Lcom/intellij/lang/Language;
        //   353: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   356: ifne            404
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload_3        
        //   367: aload           11
        //   369: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   372: ldc             "changeSignature.usages.cantConvertLanguage"
        //   374: iconst_1       
        //   375: anewarray       Ljava/lang/Object;
        //   378: dup            
        //   379: iconst_0       
        //   380: aload           12
        //   382: invokeinterface com/intellij/psi/PsiElement.getLanguage:()Lcom/intellij/lang/Language;
        //   387: invokevirtual   com/intellij/lang/Language.getDisplayName:()Ljava/lang/String;
        //   390: aastore        
        //   391: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   394: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: iinc            10, 1
        //   407: goto            317
        //   410: aload           7
        //   412: arraylength    
        //   413: ifne            462
        //   416: aload_3        
        //   417: aload           4
        //   419: new             Ljava/lang/StringBuilder;
        //   422: dup            
        //   423: invokespecial   java/lang/StringBuilder.<init>:()V
        //   426: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.BLOCK_CONFLICT:Ljava/lang/String;
        //   429: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: ldc             " "
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: ldc             "changeSignature.usages.noUsagesFound"
        //   439: iconst_0       
        //   440: anewarray       Ljava/lang/Object;
        //   443: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   446: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   449: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   452: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   455: goto            580
        //   458: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload           7
        //   464: arraylength    
        //   465: iconst_1       
        //   466: if_icmple       515
        //   469: aload_3        
        //   470: aload           4
        //   472: new             Ljava/lang/StringBuilder;
        //   475: dup            
        //   476: invokespecial   java/lang/StringBuilder.<init>:()V
        //   479: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.BLOCK_CONFLICT:Ljava/lang/String;
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: ldc             " "
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: ldc             "changeSignature.usages.moreThan1Found"
        //   492: iconst_0       
        //   493: anewarray       Ljava/lang/Object;
        //   496: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   505: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   508: goto            580
        //   511: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   514: athrow         
        //   515: aload           5
        //   517: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   520: aload           7
        //   522: iconst_0       
        //   523: aaload         
        //   524: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   527: iconst_1       
        //   528: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   531: ifeq            580
        //   534: aload_3        
        //   535: aload           4
        //   537: new             Ljava/lang/StringBuilder;
        //   540: dup            
        //   541: invokespecial   java/lang/StringBuilder.<init>:()V
        //   544: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.BLOCK_CONFLICT:Ljava/lang/String;
        //   547: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   550: ldc             " "
        //   552: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   555: ldc             "changeSignature.usages.usageInsideFunction"
        //   557: iconst_0       
        //   558: anewarray       Ljava/lang/Object;
        //   561: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   564: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   567: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   570: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   579: athrow         
        //   580: aload           5
        //   582: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeFunction:()Z
        //   585: ifeq            735
        //   588: aload           4
        //   590: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   593: ifeq            623
        //   596: goto            603
        //   599: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   602: athrow         
        //   603: aload           4
        //   605: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   608: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   613: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   616: goto            624
        //   619: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   622: athrow         
        //   623: aconst_null    
        //   624: astore          7
        //   626: aload           7
        //   628: ifnull          663
        //   631: aload           7
        //   633: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   636: astore          9
        //   638: aload           9
        //   640: ifnull          655
        //   643: aload           9
        //   645: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   648: goto            658
        //   651: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   654: athrow         
        //   655: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   658: astore          8
        //   660: goto            668
        //   663: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   666: astore          8
        //   668: aload           5
        //   670: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameters:()[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   673: aload           5
        //   675: invokedynamic   fun:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;)Lcom/intellij/util/Function;
        //   680: invokestatic    com/intellij/util/containers/ContainerUtil.map:([Ljava/lang/Object;Lcom/intellij/util/Function;)Ljava/util/List;
        //   683: astore          9
        //   685: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   688: dup            
        //   689: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   692: aload           9
        //   694: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   697: astore          10
        //   699: aload           4
        //   701: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   706: aload           7
        //   708: aload           4
        //   710: aload           8
        //   712: aload           10
        //   714: aload_3        
        //   715: aload           6
        //   717: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Lcom/intellij/util/containers/MultiMap;Ljava/lang/String;)Lcom/intellij/util/Processor;
        //   722: aload_1        
        //   723: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getNewName:()Ljava/lang/String;
        //   728: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols:(Lcom/intellij/openapi/project/Project;Lcom/intellij/util/Processor;Ljava/lang/String;)Z
        //   731: pop            
        //   732: goto            1067
        //   735: aload           5
        //   737: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //   740: ifeq            1067
        //   743: aload_2        
        //   744: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   747: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //   750: astore          7
        //   752: iconst_0       
        //   753: istore          8
        //   755: aload           7
        //   757: astore          9
        //   759: aload           9
        //   761: arraylength    
        //   762: istore          10
        //   764: iconst_0       
        //   765: istore          11
        //   767: iload           11
        //   769: iload           10
        //   771: if_icmpge       808
        //   774: aload           9
        //   776: iload           11
        //   778: aaload         
        //   779: astore          12
        //   781: aload           12
        //   783: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   786: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   789: ifeq            802
        //   792: iinc            8, 1
        //   795: goto            802
        //   798: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   801: athrow         
        //   802: iinc            11, 1
        //   805: goto            767
        //   808: iload           8
        //   810: iconst_1       
        //   811: if_icmple       890
        //   814: aload           7
        //   816: astore          9
        //   818: aload           9
        //   820: arraylength    
        //   821: istore          10
        //   823: iconst_0       
        //   824: istore          11
        //   826: iload           11
        //   828: iload           10
        //   830: if_icmpge       890
        //   833: aload           9
        //   835: iload           11
        //   837: aaload         
        //   838: astore          12
        //   840: aload           12
        //   842: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   845: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   848: ifeq            884
        //   851: aload_3        
        //   852: aload           12
        //   854: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   857: ldc             "changeSignature.usages.functionHasSeveralDefinitions"
        //   859: iconst_1       
        //   860: anewarray       Ljava/lang/Object;
        //   863: dup            
        //   864: iconst_0       
        //   865: aload           5
        //   867: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   870: aastore        
        //   871: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   874: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   877: goto            884
        //   880: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   883: athrow         
        //   884: iinc            11, 1
        //   887: goto            826
        //   890: aload           5
        //   892: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   895: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getMethodParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   898: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   901: astore          9
        //   903: aload           9
        //   905: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.resolveClassDeclaration:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   908: astore          10
        //   910: aload           10
        //   912: ifnonnull       922
        //   915: aload           5
        //   917: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewContainerClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   920: astore          10
        //   922: aload           10
        //   924: ifnull          1067
        //   927: aload           5
        //   929: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.isNameChanged:()Z
        //   932: ifne            954
        //   935: goto            942
        //   938: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   941: athrow         
        //   942: aload           9
        //   944: ifnull          1067
        //   947: goto            954
        //   950: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   953: athrow         
        //   954: aload           5
        //   956: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   959: aload           10
        //   961: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   966: aload           4
        //   968: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   973: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   975: aload           5
        //   977: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   980: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   983: ifeq            1026
        //   986: goto            993
        //   989: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   992: athrow         
        //   993: aload           5
        //   995: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   998: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1001: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.isInstanceMethod:()Z
        //  1006: ifeq            1026
        //  1009: goto            1016
        //  1012: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1015: athrow         
        //  1016: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //  1019: goto            1029
        //  1022: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1025: athrow         
        //  1026: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //  1029: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/intellij/openapi/project/Project;Ljava/lang/Class;Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //  1032: astore          11
        //  1034: aload           11
        //  1036: iconst_1       
        //  1037: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setIncludeSelfImplementation:(Z)V
        //  1040: aload           11
        //  1042: iconst_1       
        //  1043: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setAncestors:(Z)V
        //  1046: aload           11
        //  1048: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //  1051: aload_3        
        //  1052: aload           4
        //  1054: aload           6
        //  1056: invokedynamic   process:(Lcom/intellij/util/containers/MultiMap;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/intellij/util/Processor;
        //  1061: invokeinterface com/intellij/util/Query.forEach:(Lcom/intellij/util/Processor;)Z
        //  1066: pop            
        //  1067: aload_1        
        //  1068: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getMethod:()Lcom/intellij/psi/PsiElement;
        //  1073: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1076: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //  1081: astore          7
        //  1083: new             Lcom/intellij/util/containers/HashSet;
        //  1086: dup            
        //  1087: invokespecial   com/intellij/util/containers/HashSet.<init>:()V
        //  1090: astore          8
        //  1092: aload_1        
        //  1093: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getNewParameters:()[Lcom/intellij/refactoring/changeSignature/ParameterInfo;
        //  1098: astore          9
        //  1100: aload           9
        //  1102: arraylength    
        //  1103: istore          10
        //  1105: iconst_0       
        //  1106: istore          11
        //  1108: iload           11
        //  1110: iload           10
        //  1112: if_icmpge       1197
        //  1115: aload           9
        //  1117: iload           11
        //  1119: aaload         
        //  1120: astore          12
        //  1122: aload           12
        //  1124: invokeinterface com/intellij/refactoring/changeSignature/ParameterInfo.getName:()Ljava/lang/String;
        //  1129: invokevirtual   java/lang/String.isEmpty:()Z
        //  1132: ifne            1191
        //  1135: aload           8
        //  1137: aload           12
        //  1139: invokeinterface com/intellij/refactoring/changeSignature/ParameterInfo.getName:()Ljava/lang/String;
        //  1144: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //  1149: ifne            1191
        //  1152: goto            1159
        //  1155: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1158: athrow         
        //  1159: aload_3        
        //  1160: aload           4
        //  1162: ldc             "changeSignature.usages.atLeasTwoParams"
        //  1164: iconst_1       
        //  1165: anewarray       Ljava/lang/Object;
        //  1168: dup            
        //  1169: iconst_0       
        //  1170: aload           12
        //  1172: invokeinterface com/intellij/refactoring/changeSignature/ParameterInfo.getName:()Ljava/lang/String;
        //  1177: aastore        
        //  1178: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1181: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1184: goto            1191
        //  1187: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1190: athrow         
        //  1191: iinc            11, 1
        //  1194: goto            1108
        //  1197: aload           7
        //  1199: ifnull          1265
        //  1202: aload_1        
        //  1203: invokeinterface com/intellij/refactoring/changeSignature/ChangeInfo.getNewParameters:()[Lcom/intellij/refactoring/changeSignature/ParameterInfo;
        //  1208: astore          9
        //  1210: aload           9
        //  1212: arraylength    
        //  1213: istore          10
        //  1215: iconst_0       
        //  1216: istore          11
        //  1218: iload           11
        //  1220: iload           10
        //  1222: if_icmpge       1265
        //  1225: aload           9
        //  1227: iload           11
        //  1229: aaload         
        //  1230: astore          12
        //  1232: aload           12
        //  1234: invokeinterface com/intellij/refactoring/changeSignature/ParameterInfo.getName:()Ljava/lang/String;
        //  1239: aload           7
        //  1241: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //  1246: aload_3        
        //  1247: aload           4
        //  1249: invokedynamic   process:(Lcom/intellij/util/containers/MultiMap;Lcom/intellij/psi/PsiElement;)Lcom/intellij/util/Processor;
        //  1254: iconst_0       
        //  1255: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processLocalSymbols:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor;Z)Z
        //  1258: pop            
        //  1259: iinc            11, 1
        //  1262: goto            1218
        //  1265: aload_2        
        //  1266: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //  1269: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //  1272: astore          9
        //  1274: aload           9
        //  1276: arraylength    
        //  1277: istore          10
        //  1279: iconst_0       
        //  1280: istore          11
        //  1282: iload           11
        //  1284: iload           10
        //  1286: if_icmpge       1436
        //  1289: aload           9
        //  1291: iload           11
        //  1293: aaload         
        //  1294: astore          12
        //  1296: aload           12
        //  1298: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelectorExpressionUsage;
        //  1301: ifeq            1362
        //  1304: aload           12
        //  1306: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //  1309: astore          13
        //  1311: aload           13
        //  1313: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //  1316: ifeq            1359
        //  1319: aload           13
        //  1321: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1324: invokestatic    com/jetbrains/cidr/lang/resolve/OCSelectorAdHocResolver.getActionTargetContext:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //  1327: ifnonnull       1359
        //  1330: goto            1337
        //  1333: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1336: athrow         
        //  1337: aload_3        
        //  1338: aload           13
        //  1340: ldc             "changeSignature.usages.unrelatedSelector"
        //  1342: iconst_0       
        //  1343: anewarray       Ljava/lang/Object;
        //  1346: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1349: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1352: goto            1359
        //  1355: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1358: athrow         
        //  1359: goto            1430
        //  1362: aload           12
        //  1364: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage;
        //  1367: ifeq            1430
        //  1370: aload           12
        //  1372: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //  1375: astore          13
        //  1377: aload           13
        //  1379: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //  1382: ifeq            1430
        //  1385: aload           13
        //  1387: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //  1390: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //  1395: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1398: ifnonnull       1430
        //  1401: goto            1408
        //  1404: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1407: athrow         
        //  1408: aload_3        
        //  1409: aload           13
        //  1411: ldc             "changeSignature.usages.unrelatedSelector"
        //  1413: iconst_0       
        //  1414: anewarray       Ljava/lang/Object;
        //  1417: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1420: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //  1423: goto            1430
        //  1426: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1429: athrow         
        //  1430: iinc            11, 1
        //  1433: goto            1282
        //  1436: aload_3        
        //  1437: areturn        
        //    Signature:
        //  (Lcom/intellij/refactoring/changeSignature/ChangeInfo;Lcom/intellij/openapi/util/Ref<[Lcom/intellij/usageView/UsageInfo;>;)Lcom/intellij/util/containers/MultiMap<Lcom/intellij/psi/PsiElement;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     11     15     Ljava/lang/IllegalArgumentException;
        //  121    147    150    154    Ljava/lang/IllegalArgumentException;
        //  154    172    175    179    Ljava/lang/IllegalArgumentException;
        //  159    200    203    207    Ljava/lang/IllegalArgumentException;
        //  179    233    236    240    Ljava/lang/IllegalArgumentException;
        //  256    275    278    282    Ljava/lang/IllegalArgumentException;
        //  264    293    296    300    Ljava/lang/IllegalArgumentException;
        //  338    359    362    366    Ljava/lang/IllegalArgumentException;
        //  343    397    400    404    Ljava/lang/IllegalArgumentException;
        //  410    458    458    462    Ljava/lang/IllegalArgumentException;
        //  462    511    511    515    Ljava/lang/IllegalArgumentException;
        //  515    573    576    580    Ljava/lang/IllegalArgumentException;
        //  580    596    599    603    Ljava/lang/IllegalArgumentException;
        //  588    619    619    623    Ljava/lang/IllegalArgumentException;
        //  638    651    651    655    Ljava/lang/IllegalArgumentException;
        //  781    795    798    802    Ljava/lang/IllegalArgumentException;
        //  840    877    880    884    Ljava/lang/IllegalArgumentException;
        //  922    935    938    942    Ljava/lang/IllegalArgumentException;
        //  927    947    950    954    Ljava/lang/IllegalArgumentException;
        //  942    986    989    993    Ljava/lang/IllegalArgumentException;
        //  954    1009   1012   1016   Ljava/lang/IllegalArgumentException;
        //  993    1022   1022   1026   Ljava/lang/IllegalArgumentException;
        //  1122   1152   1155   1159   Ljava/lang/IllegalArgumentException;
        //  1135   1184   1187   1191   Ljava/lang/IllegalArgumentException;
        //  1311   1330   1333   1337   Ljava/lang/IllegalArgumentException;
        //  1319   1352   1355   1359   Ljava/lang/IllegalArgumentException;
        //  1377   1401   1404   1408   Ljava/lang/IllegalArgumentException;
        //  1385   1423   1426   1430   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0179:
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
    
    public boolean processUsage(final ChangeInfo changeInfo, final UsageInfo usageInfo, final boolean b, final UsageInfo[] array) {
        try {
            if (!(usageInfo instanceof OCUsageInfo)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ((OCUsageInfo)usageInfo).processUsage((OCChangeInfo)changeInfo, usageInfo.getElement(), changeInfo.getMethod().getProject());
    }
    
    public static void processParameters(final OCChangeInfo p0, final OCCallable p1, final OCParameterList p2, final Project p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore          5
        //     3: aload_2        
        //     4: ifnull          361
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameters:()[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    11: astore          6
        //    13: aload_2        
        //    14: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //    19: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    24: astore          7
        //    26: aload           7
        //    28: invokeinterface java/util/Iterator.hasNext:()Z
        //    33: ifeq            361
        //    36: aload           7
        //    38: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    43: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //    46: astore          8
        //    48: aload           8
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    55: astore          9
        //    57: iload           5
        //    59: aload           6
        //    61: arraylength    
        //    62: if_icmplt       137
        //    65: iload           5
        //    67: ifne            122
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload           6
        //    79: arraylength    
        //    80: ifne            122
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload           9
        //    92: ifnull          122
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload           9
        //   104: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   109: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   112: ifne            361
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   125: ldc             "Invalid parameter is involved into rename procedure!"
        //   127: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   130: goto            361
        //   133: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload           8
        //   139: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   144: astore          10
        //   146: aload           10
        //   148: ifnull          355
        //   151: aload           10
        //   153: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   158: astore          11
        //   160: aload           6
        //   162: iload           5
        //   164: aaload         
        //   165: astore          12
        //   167: aload           9
        //   169: ifnull          248
        //   172: aload           10
        //   174: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   179: aload           10
        //   181: aload           8
        //   183: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTypeTextWithModifiers:(Lcom/jetbrains/cidr/lang/psi/OCDeclaration;)Ljava/lang/String;
        //   186: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Ljava/lang/String;
        //   189: astore          13
        //   191: aload           12
        //   193: aload           13
        //   195: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.isTypeChanged:(Ljava/lang/String;)Z
        //   198: ifeq            248
        //   201: aload           10
        //   203: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   208: astore          14
        //   210: aload           14
        //   212: ifnull          248
        //   215: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   218: dup            
        //   219: aload           14
        //   221: aload           12
        //   223: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   226: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   229: aload_3        
        //   230: aconst_null    
        //   231: aload           10
        //   233: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   238: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload_0        
        //   249: iload           4
        //   251: aload_1        
        //   252: aload           12
        //   254: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameterName:(ZLcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;)Ljava/lang/String;
        //   257: astore          13
        //   259: aload           13
        //   261: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.isParameterNameEmpty:(Ljava/lang/String;)Z
        //   264: istore          14
        //   266: aload           11
        //   268: ifnull          307
        //   271: iload           14
        //   273: ifeq            295
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aload           11
        //   285: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   288: goto            355
        //   291: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: aload           11
        //   297: aload           13
        //   299: aload           11
        //   301: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.replaceWithIdentifier:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   304: goto            355
        //   307: iload           14
        //   309: ifne            355
        //   312: aload           13
        //   314: aload           12
        //   316: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   319: aload_1        
        //   320: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.paramDeclarationByNameAndType:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   323: astore          15
        //   325: aload           15
        //   327: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   332: ifnull          355
        //   335: aload           10
        //   337: aload           15
        //   339: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   344: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   347: pop            
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: iinc            5, 1
        //   358: goto            26
        //   361: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  57     70     73     77     Ljava/lang/IllegalArgumentException;
        //  65     83     86     90     Ljava/lang/IllegalArgumentException;
        //  77     95     98     102    Ljava/lang/IllegalArgumentException;
        //  90     115    118    122    Ljava/lang/IllegalArgumentException;
        //  102    133    133    137    Ljava/lang/IllegalArgumentException;
        //  210    241    244    248    Ljava/lang/IllegalArgumentException;
        //  266    276    279    283    Ljava/lang/IllegalArgumentException;
        //  271    291    291    295    Ljava/lang/IllegalArgumentException;
        //  325    348    351    355    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public static void changeConstructorOrMethodCall(@Nullable final OCElement ocElement, final OCChangeInfo ocChangeInfo) {
        int n = 0;
        PsiElement nameIdentifier = null;
        Object o = null;
        if (ocElement instanceof OCCppNewExpression) {
            o = ((OCCppNewExpression)ocElement).getArgumentList();
        }
        else if (ocElement instanceof OCDeclarator) {
            o = PsiTreeUtil.getChildOfType((PsiElement)ocElement, (Class)OCArgumentList.class);
            if (o == null) {
                o = ocElement.add((PsiElement)OCElementFactory.callExpression(ocChangeInfo.getNewName(), Collections.emptyList(), ocChangeInfo.getContext()).getArgumentList());
            }
        }
        else if (ocElement instanceof OCConstructorFieldInitializer) {
            o = ((OCConstructorFieldInitializer)ocElement).getArgumentList();
        }
        else if (ocElement instanceof OCCallExpression) {
            o = ((OCCallExpression)ocElement).getArgumentList();
            final OCExpression functionReferenceExpression = ((OCCallExpression)ocElement).getFunctionReferenceExpression();
            if (functionReferenceExpression instanceof OCReferenceExpression) {
                final OCReferenceElement referenceElement = ((OCReferenceExpression)functionReferenceExpression).getReferenceElement();
                PsiElement nameIdentifier2 = null;
                Label_0178: {
                    try {
                        if (referenceElement != null) {
                            nameIdentifier2 = referenceElement.getNameIdentifier();
                            break Label_0178;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    nameIdentifier2 = null;
                }
                nameIdentifier = nameIdentifier2;
            }
            else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                nameIdentifier = ((OCQualifiedExpression)functionReferenceExpression).getNameIdentifier();
            }
        }
        final ArrayList<String> list = new ArrayList<String>();
        final OCParameterInfo[] newParameters = ocChangeInfo.getNewParameters();
        for (int length = newParameters.length, i = 0; i < length; ++i) {
            list.add(newParameters[i].getParameterStubText(false, false, (PsiElement)ocElement));
        }
        final OCCallExpression callExpression = OCElementFactory.callExpression(ocChangeInfo.getNewName(), list, ocChangeInfo.getContext());
        if (o != null) {
            for (final OCExpression ocExpression : callExpression.getArguments()) {
                final OCParameterInfo ocParameterInfo = ocChangeInfo.getNewParameters()[n++];
                if (ocParameterInfo.getOldIndex() >= 0) {
                    try {
                        if (ocParameterInfo.getOldIndex() >= ((OCArgumentList)o).getArguments().size()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    OCChangeUtil.replaceHandlingMacros((PsiElement)ocExpression, (PsiElement)((OCArgumentList)o).getArguments().get(ocParameterInfo.getOldIndex()));
                }
            }
            OCImportSymbolFix.fixAllSymbolsRecursively(OCChangeUtil.replaceHandlingMacros((PsiElement)o, (PsiElement)callExpression.getArgumentList()));
        }
        else if (ocElement instanceof OCCppNewExpression) {
            OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)OCChangeUtil.add((PsiElement)ocElement, callExpression.getArgumentList()));
        }
        Label_0462: {
            try {
                if (nameIdentifier == null) {
                    return;
                }
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b = ocChangeInfo2.isNameChanged();
                if (b) {
                    break Label_0462;
                }
                return;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b = ocChangeInfo2.isNameChanged();
                if (b) {
                    OCElementUtil.replaceWithIdentifier(nameIdentifier, ocChangeInfo.getNewName(), (PsiElement)callExpression);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    public static PsiElement generateCall(@Nullable final OCExpression p0, final List<OCExpression> p1, final OCChangeInfo p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //    11: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    17: astore          4
        //    19: aload_2        
        //    20: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    23: aload           4
        //    25: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    28: ifne            108
        //    31: aload_0        
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    35: ifeq            116
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    54: ifnull          116
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload           4
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    69: ifeq            116
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_2        
        //    80: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    83: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getName:()Ljava/lang/String;
        //    88: aload           4
        //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getName:()Ljava/lang/String;
        //    95: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    98: ifeq            116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: istore          5
        //   119: aload_2        
        //   120: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameters:()[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   123: astore          6
        //   125: aload           6
        //   127: arraylength    
        //   128: istore          7
        //   130: iconst_0       
        //   131: istore          8
        //   133: iload           8
        //   135: iload           7
        //   137: if_icmpge       172
        //   140: aload           6
        //   142: iload           8
        //   144: aaload         
        //   145: astore          9
        //   147: aload_3        
        //   148: aload           9
        //   150: aload_2        
        //   151: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //   154: iload           5
        //   156: aconst_null    
        //   157: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getParameterStubText:(ZZLcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   160: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   165: pop            
        //   166: iinc            8, 1
        //   169: goto            133
        //   172: aload_2        
        //   173: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //   176: ifeq            341
        //   179: aload_3        
        //   180: aload_2        
        //   181: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   184: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.sendMessageExpression:(Ljava/util/List;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   187: astore          6
        //   189: iconst_0       
        //   190: istore          7
        //   192: aload           6
        //   194: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //   199: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   204: astore          8
        //   206: aload           8
        //   208: invokeinterface java/util/Iterator.hasNext:()Z
        //   213: ifeq            296
        //   216: aload           8
        //   218: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   223: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   226: astore          9
        //   228: aload_2        
        //   229: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameters:()[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   232: iload           7
        //   234: iinc            7, 1
        //   237: aaload         
        //   238: astore          10
        //   240: aload           10
        //   242: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   245: iflt            206
        //   248: aload           10
        //   250: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   253: aload_1        
        //   254: invokeinterface java/util/List.size:()I
        //   259: if_icmplt       269
        //   262: goto            206
        //   265: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload_1        
        //   270: aload           10
        //   272: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   275: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   280: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   283: astore          11
        //   285: aload           9
        //   287: aload           11
        //   289: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   292: pop            
        //   293: goto            206
        //   296: aload_2        
        //   297: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   300: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   303: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.METHOD:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   306: if_acmpeq       326
        //   309: aload_2        
        //   310: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewContainerClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   313: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getName:()Ljava/lang/String;
        //   318: aload_2        
        //   319: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   322: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   325: astore_0       
        //   326: aload           6
        //   328: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   333: aload_0        
        //   334: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   337: pop            
        //   338: aload           6
        //   340: areturn        
        //   341: aload_2        
        //   342: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   345: ifeq            357
        //   348: ldc             "block"
        //   350: goto            361
        //   353: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: aload_2        
        //   358: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   361: aload_3        
        //   362: aload_2        
        //   363: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   366: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.callExpression:(Ljava/lang/String;Ljava/util/List;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   369: astore          6
        //   371: iconst_0       
        //   372: istore          7
        //   374: aload           6
        //   376: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   381: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   386: astore          8
        //   388: aload           8
        //   390: invokeinterface java/util/Iterator.hasNext:()Z
        //   395: ifeq            478
        //   398: aload           8
        //   400: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   405: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   408: astore          9
        //   410: aload_2        
        //   411: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameters:()[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   414: iload           7
        //   416: iinc            7, 1
        //   419: aaload         
        //   420: astore          10
        //   422: aload           10
        //   424: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   427: iflt            388
        //   430: aload           10
        //   432: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   435: aload_1        
        //   436: invokeinterface java/util/List.size:()I
        //   441: if_icmplt       451
        //   444: goto            388
        //   447: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: aload_1        
        //   452: aload           10
        //   454: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   457: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   462: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   465: astore          11
        //   467: aload           9
        //   469: aload           11
        //   471: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   474: pop            
        //   475: goto            388
        //   478: aload_2        
        //   479: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   482: ifeq            529
        //   485: aload_2        
        //   486: aload_2        
        //   487: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   490: iconst_1       
        //   491: iconst_1       
        //   492: iconst_0       
        //   493: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.generateMethodDefinition:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/jetbrains/cidr/lang/psi/OCCallable;ZZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   496: astore          8
        //   498: aload           8
        //   500: ifnull          529
        //   503: aload           6
        //   505: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   510: aload           8
        //   512: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   515: pop            
        //   516: aload_2        
        //   517: aload           8
        //   519: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.setNewMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //   522: goto            529
        //   525: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: aload           6
        //   531: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCExpression;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;)Lcom/intellij/psi/PsiElement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     38     41     45     Ljava/lang/IllegalArgumentException;
        //  31     57     60     64     Ljava/lang/IllegalArgumentException;
        //  45     72     75     79     Ljava/lang/IllegalArgumentException;
        //  64     101    104    108    Ljava/lang/IllegalArgumentException;
        //  79     112    112    116    Ljava/lang/IllegalArgumentException;
        //  248    265    265    269    Ljava/lang/IllegalArgumentException;
        //  341    353    353    357    Ljava/lang/IllegalArgumentException;
        //  430    447    447    451    Ljava/lang/IllegalArgumentException;
        //  498    522    525    529    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    public static OCCallable generateMethodDefinition(final OCChangeInfo p0, final OCCallable p1, final boolean p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_1        
        //     4: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //     9: astore          6
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    17: astore          7
        //    19: iload           4
        //    21: ifeq            36
        //    24: aload_0        
        //    25: aload_1        
        //    26: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewInheritedSignature:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)Ljava/lang/String;
        //    29: goto            40
        //    32: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_0        
        //    37: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewSignature:()Ljava/lang/String;
        //    40: astore          8
        //    42: aload_0        
        //    43: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //    46: ifeq            108
        //    49: aload           8
        //    51: aload           7
        //    53: iload_2        
        //    54: ifeq            98
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload           6
        //    66: ifnonnull       90
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_1        
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    80: ifeq            98
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_1       
        //    91: goto            99
        //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iconst_0       
        //    99: iconst_0       
        //   100: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.methodFromSignature:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   103: astore          5
        //   105: goto            350
        //   108: aload_0        
        //   109: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeFunction:()Z
        //   112: ifeq            272
        //   115: aload_0        
        //   116: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   119: astore          9
        //   121: aload           9
        //   123: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.isConstructor:()Z
        //   126: ifeq            191
        //   129: new             Ljava/lang/StringBuilder;
        //   132: dup            
        //   133: invokespecial   java/lang/StringBuilder.<init>:()V
        //   136: aload           8
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: iload_2        
        //   142: ifeq            173
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload           6
        //   154: ifnull          173
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: ldc             "{\n}"
        //   166: goto            175
        //   169: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: ldc             ""
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   181: aload           7
        //   183: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.constructorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   186: astore          10
        //   188: goto            243
        //   191: new             Ljava/lang/StringBuilder;
        //   194: dup            
        //   195: invokespecial   java/lang/StringBuilder.<init>:()V
        //   198: aload           8
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: iload_2        
        //   204: ifeq            228
        //   207: aload           6
        //   209: ifnull          228
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: ldc             "{\n}"
        //   221: goto            230
        //   224: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: ldc             ""
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   236: aload           7
        //   238: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   241: astore          10
        //   243: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   246: aload           10
        //   248: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   251: aload           10
        //   253: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTextWithMacros:()Ljava/lang/String;
        //   258: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   261: pop            
        //   262: aload           10
        //   264: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   267: astore          5
        //   269: goto            350
        //   272: aload_0        
        //   273: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   276: ifeq            332
        //   279: aload           6
        //   281: ifnull          326
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: new             Ljava/lang/StringBuilder;
        //   294: dup            
        //   295: invokespecial   java/lang/StringBuilder.<init>:()V
        //   298: aload           8
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: ldc             "{\n}"
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   311: aload           7
        //   313: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   316: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   319: goto            327
        //   322: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: aconst_null    
        //   327: astore          5
        //   329: goto            350
        //   332: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.$assertionsDisabled:Z
        //   335: ifne            350
        //   338: new             Ljava/lang/AssertionError;
        //   341: dup            
        //   342: invokespecial   java/lang/AssertionError.<init>:()V
        //   345: athrow         
        //   346: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: iload_2        
        //   351: ifeq            415
        //   354: aload           6
        //   356: ifnull          415
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload           5
        //   368: ifnull          415
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload           6
        //   380: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.isValid:()Z
        //   385: ifeq            415
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: aload           5
        //   397: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   402: aload           6
        //   404: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   407: pop            
        //   408: goto            415
        //   411: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   414: athrow         
        //   415: aload           5
        //   417: aload_1        
        //   418: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.replaceDeclarationQualifiers:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   421: iload_3        
        //   422: ifeq            438
        //   425: aload           5
        //   427: aload_1        
        //   428: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.replaceComments:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   431: goto            438
        //   434: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: aload           5
        //   440: ifnull          473
        //   443: aload_0        
        //   444: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   447: ifnull          473
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload_0        
        //   458: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   461: aload           5
        //   463: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.initIndentFromContext:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: aload           5
        //   475: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     32     32     36     Ljava/lang/IllegalArgumentException;
        //  42     57     60     64     Ljava/lang/IllegalArgumentException;
        //  49     69     72     76     Ljava/lang/IllegalArgumentException;
        //  64     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     94     94     98     Ljava/lang/IllegalArgumentException;
        //  121    145    148    152    Ljava/lang/IllegalArgumentException;
        //  129    157    160    164    Ljava/lang/IllegalArgumentException;
        //  152    169    169    173    Ljava/lang/IllegalArgumentException;
        //  191    212    215    219    Ljava/lang/IllegalArgumentException;
        //  207    224    224    228    Ljava/lang/IllegalArgumentException;
        //  272    284    287    291    Ljava/lang/IllegalArgumentException;
        //  279    322    322    326    Ljava/lang/IllegalArgumentException;
        //  332    346    346    350    Ljava/lang/IllegalArgumentException;
        //  350    359    362    366    Ljava/lang/IllegalArgumentException;
        //  354    371    374    378    Ljava/lang/IllegalArgumentException;
        //  366    388    391    395    Ljava/lang/IllegalArgumentException;
        //  378    408    411    415    Ljava/lang/IllegalArgumentException;
        //  415    431    434    438    Ljava/lang/IllegalArgumentException;
        //  438    450    453    457    Ljava/lang/IllegalArgumentException;
        //  443    466    469    473    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
    
    public boolean processPrimaryMethod(final ChangeInfo changeInfo) {
        return changeInfo instanceof OCChangeInfo;
    }
    
    public boolean shouldPreviewUsages(final ChangeInfo changeInfo, final UsageInfo[] array) {
        return false;
    }
    
    public boolean setupDefaultValues(final ChangeInfo changeInfo, final Ref<UsageInfo[]> ref, final Project project) {
        return true;
    }
    
    public void registerConflictResolvers(final List<ResolveSnapshotProvider.ResolveSnapshot> list, @NotNull final ResolveSnapshotProvider resolveSnapshotProvider, final UsageInfo[] array, final ChangeInfo changeInfo) {
        try {
            if (resolveSnapshotProvider == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveSnapshotProvider", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor", "registerConflictResolvers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCChangeSignatureUsageProcessor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.refactoring.changeSignature.ChangeSignatureUsageProcessor");
        BLOCK_CONFLICT = OCBundle.message("changeSignature.usages.cantConvertToBlock", new Object[0]);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
