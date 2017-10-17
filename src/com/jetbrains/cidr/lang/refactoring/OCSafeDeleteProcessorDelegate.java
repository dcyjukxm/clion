// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteReferenceSimpleDeleteUsageInfo;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.refactoring.RefactoringSettings;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import java.util.Comparator;
import com.jetbrains.cidr.lang.search.usages.OCFindUsagesHandler;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import java.util.Collections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCStruct;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteUsageInfo;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.refactoring.safeDelete.SafeDeleteProcessor;
import java.util.ArrayList;
import com.intellij.util.IncorrectOperationException;
import com.intellij.refactoring.safeDelete.NonCodeUsageSearchInfo;
import com.intellij.usageView.UsageInfo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.SafeDeleteProcessorDelegate;

public class OCSafeDeleteProcessorDelegate implements SafeDeleteProcessorDelegate
{
    @Override
    public boolean handlesElement(final PsiElement psiElement) {
        return true;
    }
    
    @Override
    public NonCodeUsageSearchInfo findUsages(@NotNull final PsiElement psiElement, @NotNull final PsiElement[] array, @NotNull final List<UsageInfo> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final ArrayList<UsageInfo> list2 = new ArrayList<UsageInfo>();
        SafeDeleteProcessor.findGenericElementUsages(psiElement, list2, array);
        list.addAll(ContainerUtil.map((Collection)list2, usageInfo -> {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "lambda$findUsages$0"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final PsiElement element = usageInfo.getElement();
            try {
                if (isSafeToDelete(element, psiElement)) {
                    return new SafeDeleteReferenceSimpleDeleteUsageInfo(element, ((SafeDeleteUsageInfo)usageInfo).getReferencedElement(), true) {
                        final /* synthetic */ PsiElement val$usageElement;
                        
                        @Override
                        public void deleteElement() throws IncorrectOperationException {
                            OCChangeUtil.safeDeleteReference(this.val$usageElement);
                        }
                    };
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            return usageInfo;
        }));
        if (a(psiElement)) {
            final ArrayList<Pair> list3 = (ArrayList<Pair>)new ArrayList<Object>();
            a(psiElement, (List<Pair<PsiElement, OCSymbol>>)list3);
            final Iterator<Object> iterator = list3.iterator();
            while (iterator.hasNext()) {
                list.add(new SafeDeleteUsageInfo((PsiElement)iterator.next().getFirst(), psiElement));
            }
        }
        return new NonCodeUsageSearchInfo(SafeDeleteProcessor.getDefaultInsideDeletedCondition(array), psiElement);
    }
    
    public static boolean isSafeToDelete(final PsiElement psiElement, @Nullable final PsiElement psiElement2) {
        Label_0260: {
            Label_0229: {
                try {
                    if (!(psiElement2 instanceof OCStruct)) {
                        if (!(psiElement2 instanceof OCCppNamespace)) {
                            break Label_0229;
                        }
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final OCSymbol symbol = ((OCSymbolDeclarator)psiElement2).getSymbol();
                final OCElement ocElement = (OCElement)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCStruct.class, OCCppNamespace.class, OCDeclarator.class, OCDeclaration.class });
                List<OCDeclarator> list;
                if (ocElement instanceof OCDeclaration) {
                    list = ((OCDeclaration)ocElement).getDeclarators();
                }
                else {
                    if (!(ocElement instanceof OCDeclarator)) {
                        return psiElement2.equals(ocElement);
                    }
                    list = Collections.singletonList(ocElement);
                }
                final Iterator<OCDeclarator> iterator = list.iterator();
                while (iterator.hasNext()) {
                    final OCSymbol symbol2 = iterator.next().getSymbol();
                    OCSymbolWithQualifiedName resolvedOwner = null;
                    Label_0173: {
                        try {
                            if (symbol2 instanceof OCSymbolWithQualifiedName) {
                                resolvedOwner = ((OCSymbolWithQualifiedName)symbol2).getResolvedOwner();
                                break Label_0173;
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        resolvedOwner = null;
                    }
                    OCSymbolWithQualifiedName resolvedOwner2 = resolvedOwner;
                    while (true) {
                        Label_0196: {
                            try {
                                if (resolvedOwner2 == null) {
                                    break;
                                }
                                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = resolvedOwner2;
                                final OCSymbol ocSymbol = symbol;
                                final boolean b = ocSymbolWithQualifiedName.equals(ocSymbol);
                                if (b) {
                                    break Label_0196;
                                }
                                break Label_0196;
                            }
                            catch (IncorrectOperationException ex3) {
                                throw a(ex3);
                            }
                            try {
                                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = resolvedOwner2;
                                final OCSymbol ocSymbol = symbol;
                                final boolean b = ocSymbolWithQualifiedName.equals(ocSymbol);
                                if (b) {
                                    break;
                                }
                            }
                            catch (IncorrectOperationException ex4) {
                                throw a(ex4);
                            }
                        }
                        resolvedOwner2 = resolvedOwner2.getResolvedOwner();
                    }
                    try {
                        if (resolvedOwner2 == null) {
                            return false;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                return true;
                try {
                    if (new OCReadWriteAccessDetector().getExpressionAccess(psiElement) != ReadWriteAccessDetector.Access.Write) {
                        return false;
                    }
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b2 = psiElement3 instanceof OCMethod;
                    if (!b2) {
                        break Label_0260;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final PsiElement psiElement3 = psiElement2;
                final boolean b2 = psiElement3 instanceof OCMethod;
                if (!b2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
        }
        return false;
    }
    
    @Override
    public Collection<? extends PsiElement> getElementsToSearch(@NotNull final PsiElement psiElement, @NotNull final Collection<PsiElement> collection) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "getElementsToSearch"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "getElementsToSearch"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCFindUsagesHandler ocFindUsagesHandler = new OCFindUsagesHandler(psiElement, true);
        final ArrayList list = new ArrayList<Object>();
        for (final PsiElement psiElement2 : ocFindUsagesHandler.getPrimaryElements()) {
            try {
                if (psiElement2 != null) {
                    list.add(psiElement2);
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        for (final PsiElement psiElement3 : ocFindUsagesHandler.getSecondaryElements()) {
            try {
                if (psiElement3 != null) {
                    list.add(psiElement3);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        try {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new Comparator<PsiElement>() {
                private int a(final PsiElement psiElement) {
                    return (psiElement instanceof OCSynthesizeProperty) ? 1 : 0;
                }
                
                @Override
                public int compare(final PsiElement psiElement, final PsiElement psiElement2) {
                    return this.a(psiElement) - this.a(psiElement2);
                }
            });
            if (list.isEmpty()) {
                final List<Object> list2 = null;
                return (Collection<? extends PsiElement>)list2;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final List<Object> list2 = (List<Object>)list;
        return (Collection<? extends PsiElement>)list2;
    }
    
    @Override
    public Collection<PsiElement> getAdditionalElementsToDelete(@NotNull final PsiElement psiElement, @NotNull final Collection<PsiElement> collection, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "getAdditionalElementsToDelete"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate", "getAdditionalElementsToDelete"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (Collection<PsiElement>)Collections.emptyList();
    }
    
    @NotNull
    @Override
    public Collection<String> findConflicts(@NotNull final PsiElement p0, @NotNull final PsiElement[] p1) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findConflicts"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "allElementsToDelete"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "findConflicts"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_1        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    92: ifeq            195
        //    95: aload_1        
        //    96: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    99: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.hasExtraTopLevelDefinitions:()Z
        //   104: ifeq            195
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   113: athrow         
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "File \""
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_1        
        //   127: checkcast       Lcom/intellij/psi/PsiFile;
        //   130: invokeinterface com/intellij/psi/PsiFile.getName:()Ljava/lang/String;
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc             "\" has several top level declarations."
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   146: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //   149: dup            
        //   150: ifnonnull       194
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   159: athrow         
        //   160: new             Ljava/lang/IllegalStateException;
        //   163: dup            
        //   164: ldc             "@NotNull method %s.%s must not return null"
        //   166: ldc             2
        //   168: anewarray       Ljava/lang/Object;
        //   171: dup            
        //   172: ldc             0
        //   174: ldc             "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate"
        //   176: aastore        
        //   177: dup            
        //   178: ldc             1
        //   180: ldc             "findConflicts"
        //   182: aastore        
        //   183: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   186: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   189: athrow         
        //   190: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   193: athrow         
        //   194: areturn        
        //   195: aload_1        
        //   196: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/psi/PsiElement;)Z
        //   199: ifeq            313
        //   202: new             Ljava/util/ArrayList;
        //   205: dup            
        //   206: invokespecial   java/util/ArrayList.<init>:()V
        //   209: astore_3       
        //   210: aload_1        
        //   211: aload_3        
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/psi/PsiElement;Ljava/util/List;)Ljava/util/List;
        //   215: pop            
        //   216: aload_3        
        //   217: invokeinterface java/util/List.isEmpty:()Z
        //   222: ifne            313
        //   225: new             Ljava/lang/StringBuilder;
        //   228: dup            
        //   229: invokespecial   java/lang/StringBuilder.<init>:()V
        //   232: aload_3        
        //   233: iconst_0       
        //   234: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   239: checkcast       Lcom/intellij/openapi/util/Pair;
        //   242: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   245: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   248: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: ldc             " has usage without arguments"
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   264: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   267: dup            
        //   268: ifnonnull       312
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   277: athrow         
        //   278: new             Ljava/lang/IllegalStateException;
        //   281: dup            
        //   282: ldc             "@NotNull method %s.%s must not return null"
        //   284: ldc             2
        //   286: anewarray       Ljava/lang/Object;
        //   289: dup            
        //   290: ldc             0
        //   292: ldc             "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate"
        //   294: aastore        
        //   295: dup            
        //   296: ldc             1
        //   298: ldc             "findConflicts"
        //   300: aastore        
        //   301: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   304: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   307: athrow         
        //   308: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   311: athrow         
        //   312: areturn        
        //   313: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   316: dup            
        //   317: ifnonnull       354
        //   320: new             Ljava/lang/IllegalStateException;
        //   323: dup            
        //   324: ldc             "@NotNull method %s.%s must not return null"
        //   326: ldc             2
        //   328: anewarray       Ljava/lang/Object;
        //   331: dup            
        //   332: ldc             0
        //   334: ldc             "com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate"
        //   336: aastore        
        //   337: dup            
        //   338: ldc             1
        //   340: ldc             "findConflicts"
        //   342: aastore        
        //   343: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   346: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   349: athrow         
        //   350: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   353: athrow         
        //   354: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;[Lcom/intellij/psi/PsiElement;)Ljava/util/Collection<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     107    110    114    Lcom/intellij/util/IncorrectOperationException;
        //  95     153    156    160    Lcom/intellij/util/IncorrectOperationException;
        //  114    190    190    194    Lcom/intellij/util/IncorrectOperationException;
        //  210    271    274    278    Lcom/intellij/util/IncorrectOperationException;
        //  225    308    308    312    Lcom/intellij/util/IncorrectOperationException;
        //  313    350    350    354    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0114:
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
    public UsageInfo[] preprocessUsages(final Project project, final UsageInfo[] array) {
        return array;
    }
    
    @Override
    public void prepareForDeletion(final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (!psiElement.isValid()) {
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (psiElement instanceof OCMethodSelectorPart) {
            final OCMethod ocMethod = (OCMethod)psiElement.getParent();
            final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocMethod, psiElement, true);
            handler.removeParameter(ocMethod.getParameters().indexOf(psiElement));
            handler.invokeSynchronously();
        }
        if (psiElement.getParent() instanceof OCParameterDeclaration) {
            final PsiElement parent = psiElement.getParent().getParent();
            if (parent instanceof OCParameterList) {
                final OCParameterList list = (OCParameterList)parent;
                final OCChangeSignatureHandler handler2 = OCChangeSignatureActionHandler.getHandler((OCCallable)list.getParent().getParent(), psiElement, true);
                handler2.removeParameter(list.getParameters().indexOf(psiElement));
                handler2.invokeSynchronously();
            }
        }
        Label_0267: {
            Label_0166: {
                try {
                    if (psiElement instanceof OCStruct) {
                        break Label_0166;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCCppNamespace;
                    if (b) {
                        break Label_0166;
                    }
                    break Label_0267;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCCppNamespace;
                    if (!b) {
                        break Label_0267;
                    }
                    if (!((OCElement)psiElement).getContainingOCFile().isCpp()) {
                        break Label_0267;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            final OCNamespaceSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (symbol instanceof OCNamespaceSymbol) {
                final ArrayList<PsiElement> list2 = new ArrayList<PsiElement>();
                symbol.processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
                    final OCSymbol definitionSymbol = ocSymbol.getDefinitionSymbol();
                    PsiElement locateDefinition = null;
                    Label_0025: {
                        try {
                            if (definitionSymbol != null) {
                                locateDefinition = definitionSymbol.locateDefinition();
                                break Label_0025;
                            }
                        }
                        catch (IncorrectOperationException ex) {
                            throw a(ex);
                        }
                        locateDefinition = null;
                    }
                    final PsiElement psiElement2 = locateDefinition;
                    Label_0049: {
                        try {
                            if (psiElement2 == null) {
                                return true;
                            }
                            final PsiElement psiElement3 = psiElement;
                            final PsiElement psiElement4 = psiElement2;
                            final boolean b = true;
                            final boolean b2 = PsiTreeUtil.isAncestor(psiElement3, psiElement4, b);
                            if (!b2) {
                                break Label_0049;
                            }
                            return true;
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final PsiElement psiElement3 = psiElement;
                            final PsiElement psiElement4 = psiElement2;
                            final boolean b = true;
                            final boolean b2 = PsiTreeUtil.isAncestor(psiElement3, psiElement4, b);
                            if (!b2) {
                                list2.add(psiElement2);
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                    }
                    return true;
                }));
                final Iterator<PsiElement> iterator = list2.iterator();
                while (iterator.hasNext()) {
                    OCChangeUtil.delete(iterator.next());
                }
            }
        }
        if (a(psiElement)) {
            final Iterator<PsiElement> iterator2 = a(psiElement, new ArrayList<Pair<PsiElement, OCSymbol>>()).iterator();
            while (iterator2.hasNext()) {
                OCChangeUtil.delete(iterator2.next());
            }
        }
    }
    
    private static List<PsiElement> a(final PsiElement psiElement, final List<Pair<PsiElement, OCSymbol>> list) {
        OCSymbol symbol = null;
        PsiElement parent = null;
        Label_0023: {
            try {
                if (psiElement instanceof OCTypeParameterDeclaration) {
                    parent = psiElement;
                    break Label_0023;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            parent = psiElement.getParent();
        }
        final PsiElement psiElement2 = parent;
        final OCTemplateParameterList list2 = (OCTemplateParameterList)psiElement2.getParent();
        final PsiElement parent2 = list2.getParent();
        final int index = list2.getParameters().indexOf(psiElement2);
        final int size = list2.getParameters().size();
        final ArrayList<PsiElement> list3 = new ArrayList<PsiElement>();
        if (parent2 instanceof OCFunctionDeclaration) {
            symbol = ((OCFunctionDeclaration)parent2).getSymbol();
        }
        else if (parent2 instanceof OCDeclaration) {
            final OCTypeElement typeElement = ((OCFunctionDeclaration)parent2).getTypeElement();
            OCType resolve = null;
            Label_0153: {
                try {
                    if (typeElement != null) {
                        resolve = typeElement.getType().resolve(psiElement.getContainingFile());
                        break Label_0153;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                resolve = null;
            }
            final OCType ocType = resolve;
            OCSymbol symbol2 = null;
            Label_0179: {
                try {
                    if (ocType instanceof OCStructType) {
                        symbol2 = ((OCStructType)ocType).getSymbol();
                        break Label_0179;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                symbol2 = null;
            }
            symbol = symbol2;
        }
        try {
            if (index == -1 || symbol == null) {
                return list3;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        symbol.processSameSymbols(ocSymbol2 -> {
            final PsiElement locateDefinition = ocSymbol2.locateDefinition();
            try {
                if (locateDefinition != null) {
                    ReferencesSearch.search(locateDefinition).forEach(psiReference -> {
                        final PsiElement element = psiReference.getElement();
                        if (element instanceof OCTemplateArgumentsOwner) {
                            final OCTypeArgumentList templateArgumentList = ((OCTemplateArgumentsOwner)element).getTemplateArgumentList();
                            try {
                                if (templateArgumentList == null) {
                                    list.add(Pair.create((Object)element, (Object)symbol));
                                    return true;
                                }
                            }
                            catch (IncorrectOperationException ex) {
                                throw a(ex);
                            }
                            if (index < templateArgumentList.getArguments().size()) {
                                list3.add(templateArgumentList.getArguments().get(index));
                            }
                            try {
                                if (size == 1) {
                                    list3.add(templateArgumentList);
                                }
                            }
                            catch (IncorrectOperationException ex2) {
                                throw a(ex2);
                            }
                            return true;
                        }
                        OCLog.LOG.warn("Bad class: " + ((OCTemplateArgumentsOwner)element).getClass());
                        return true;
                    });
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return true;
        });
        return list3;
    }
    
    private static boolean a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeParameterDeclaration;
        //     4: ifne            45
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: ifeq            53
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    27: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //    35: ifeq            53
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    44: athrow         
        //    45: iconst_1       
        //    46: goto            54
        //    49: invokestatic    com/jetbrains/cidr/lang/refactoring/OCSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    52: athrow         
        //    53: iconst_0       
        //    54: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      14     17     21     Lcom/intellij/util/IncorrectOperationException;
        //  7      38     41     45     Lcom/intellij/util/IncorrectOperationException;
        //  21     49     49     53     Lcom/intellij/util/IncorrectOperationException;
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
    
    @Override
    public boolean isToSearchInComments(final PsiElement psiElement) {
        return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS;
    }
    
    @Override
    public boolean isToSearchForTextOccurrences(final PsiElement psiElement) {
        return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA;
    }
    
    @Override
    public void setToSearchInComments(final PsiElement psiElement, final boolean safe_DELETE_SEARCH_IN_COMMENTS) {
        RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS = safe_DELETE_SEARCH_IN_COMMENTS;
    }
    
    @Override
    public void setToSearchForTextOccurrences(final PsiElement psiElement, final boolean safe_DELETE_SEARCH_IN_NON_JAVA) {
        RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA = safe_DELETE_SEARCH_IN_NON_JAVA;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
