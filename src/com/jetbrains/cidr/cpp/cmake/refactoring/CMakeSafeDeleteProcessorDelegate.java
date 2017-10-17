// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.refactoring;

import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteReferenceSimpleDeleteUsageInfo;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteUsageInfo;
import com.intellij.refactoring.RefactoringSettings;
import com.intellij.openapi.project.Project;
import java.util.Collections;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Function;
import java.util.Iterator;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLiteral;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.refactoring.safeDelete.SafeDeleteProcessor;
import java.util.ArrayList;
import com.intellij.util.IncorrectOperationException;
import com.intellij.refactoring.safeDelete.NonCodeUsageSearchInfo;
import com.intellij.usageView.UsageInfo;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.SafeDeleteProcessorDelegate;

public class CMakeSafeDeleteProcessorDelegate implements SafeDeleteProcessorDelegate
{
    @Override
    public boolean handlesElement(final PsiElement psiElement) {
        return psiElement instanceof PsiFile;
    }
    
    @Override
    public NonCodeUsageSearchInfo findUsages(@NotNull final PsiElement psiElement, @NotNull final PsiElement[] array, @NotNull final List<UsageInfo> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "findUsages"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final ArrayList<UsageInfo> list2 = new ArrayList<UsageInfo>();
        SafeDeleteProcessor.findGenericElementUsages(psiElement, list2, array);
        list.addAll(ContainerUtil.map((Collection)list2, usageInfo -> {
            try {
                if (array == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "lambda$findUsages$0"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final PsiElement element = usageInfo.getElement();
            return new SafeDeleteReferenceSimpleDeleteUsageInfo(element, ((SafeDeleteUsageInfo)usageInfo).getReferencedElement(), a(element, array)) {
                final /* synthetic */ PsiElement val$usageElement;
                
                @Override
                public void deleteElement() throws IncorrectOperationException {
                    c(this.val$usageElement);
                }
            };
        }));
        return new NonCodeUsageSearchInfo(SafeDeleteProcessor.getDefaultInsideDeletedCondition(array), psiElement);
    }
    
    private static void c(PsiElement a) {
        a = a(a);
        if (a instanceof CMakeArgument) {
            final CMakeArgument cMakeArgument = (CMakeArgument)a;
            final CMakeCommand command = cMakeArgument.getParentCommandArguments().getCommand();
            try {
                if (a(command.getCMakeCommandName(), cMakeArgument)) {
                    d((PsiElement)cMakeArgument);
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
    }
    
    private static PsiElement a(PsiElement argument) {
        if (argument instanceof CMakeLiteral) {
            argument = (PsiElement)((CMakeLiteral)argument).getArgument();
        }
        return argument;
    }
    
    private static void d(final PsiElement psiElement) {
        final CommandProcessor instance = CommandProcessor.getInstance();
        instance.executeCommand(instance.getCurrentCommandProject(), () -> ApplicationManager.getApplication().runWriteAction(psiElement::delete), instance.getCurrentCommandName(), instance.getCurrentCommandGroupId());
    }
    
    private static boolean a(PsiElement a, final PsiElement[] array) {
        if (a != null) {
            a = a(a);
            if (a instanceof CMakeArgument) {
                final CMakeArgument cMakeArgument = (CMakeArgument)a;
                if (a(cMakeArgument.getParentCommandArguments().getCommandName(), cMakeArgument)) {
                    final List<CMakeArgument> a2 = a(cMakeArgument);
                    final PsiReference e = e(cMakeArgument);
                    if (e == null) {
                        return c(cMakeArgument.getNextArgument());
                    }
                    final PsiElement resolve = e.resolve();
                    if (resolve instanceof PsiFile && a(a2, (PsiFile)resolve)) {
                        final Iterator<CMakeArgument> iterator = a2.iterator();
                        while (iterator.hasNext()) {
                            final PsiReference e2 = e(iterator.next());
                            if (e2 != null) {
                                final PsiElement resolve2 = e2.resolve();
                                if (!(resolve2 instanceof PsiFile)) {
                                    continue;
                                }
                                for (final PsiElement psiElement : array) {
                                    try {
                                        if (psiElement == resolve2) {
                                            iterator.remove();
                                        }
                                    }
                                    catch (IncorrectOperationException ex) {
                                        throw a(ex);
                                    }
                                }
                            }
                        }
                        try {
                            if (a2.isEmpty()) {
                                return false;
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    @Nullable
    private static List<CMakeArgument> a(@NotNull final CMakeArgument cMakeArgument) {
        try {
            if (cMakeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "getGroupOfArguments"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiReference e = e(cMakeArgument);
        if (e != null && e.resolve() instanceof PsiFile) {
            final ArrayList<Object> list = new ArrayList<Object>();
            list.add(cMakeArgument);
            list.addAll(a(cMakeArgument, (Function<CMakeArgument, CMakeArgument>)(cMakeArgument -> cMakeArgument.getPreviousArgument())));
            list.addAll(a(cMakeArgument, (Function<CMakeArgument, CMakeArgument>)(cMakeArgument -> cMakeArgument.getNextArgument())));
            return (List<CMakeArgument>)list;
        }
        return null;
    }
    
    private static boolean a(@Nullable final List<CMakeArgument> list, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalFile", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "groupOfArgumentsReferencesAnotherFiles"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (list != null) {
            for (final CMakeArgument cMakeArgument : list) {
                try {
                    if (!a(psiFile, cMakeArgument)) {
                        return true;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
        }
        return false;
    }
    
    private static List<CMakeArgument> a(CMakeArgument cMakeArgument, final Function<CMakeArgument, CMakeArgument> function) {
        final ArrayList<CMakeArgument> list = new ArrayList<CMakeArgument>();
        while (true) {
            final CMakeArgument cMakeArgument3;
            final CMakeArgument cMakeArgument2 = cMakeArgument3 = (CMakeArgument)function.fun((Object)cMakeArgument);
            try {
                if (cMakeArgument2 == null || e(cMakeArgument3) == null) {
                    break;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            list.add(cMakeArgument3);
            cMakeArgument = cMakeArgument3;
        }
        return list;
    }
    
    private static boolean a(final PsiFile psiFile, final CMakeArgument cMakeArgument) {
        final PsiReference e = e(cMakeArgument);
        if (e != null) {
            final PsiElement resolve = e.resolve();
            try {
                if (!(resolve instanceof PsiFile)) {
                    return false;
                }
                final PsiElement psiElement = resolve;
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiElement.equals(psiFile2);
                if (b) {
                    return true;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = resolve;
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiElement.equals(psiFile2);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static boolean a(final CMakeCommandName p0, final CMakeArgument p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isAddExecutableCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //     4: ifne            133
        //     7: aload_0        
        //     8: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isAddLibraryCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    11: ifne            133
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isSetCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    25: ifne            133
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isAddCustomTargetCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    39: ifne            133
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    48: athrow         
        //    49: aload_0        
        //    50: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isFltkWrapUiCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    53: ifne            133
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    62: athrow         
        //    63: aload_0        
        //    64: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isQtWrapCppCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    67: ifne            133
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: aload_0        
        //    78: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isQtWrapUiCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    81: ifne            133
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: aload_0        
        //    92: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isSetPropertyCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //    95: ifne            133
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   104: athrow         
        //   105: aload_0        
        //   106: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isSetSourceFilesPropertiesCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //   109: ifne            133
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   118: athrow         
        //   119: aload_0        
        //   120: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isTryCompileCommand:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;)Z
        //   123: ifeq            141
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   132: athrow         
        //   133: iconst_1       
        //   134: goto            142
        //   137: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   140: athrow         
        //   141: iconst_0       
        //   142: istore_2       
        //   143: iload_2        
        //   144: ifeq            190
        //   147: aload_1        
        //   148: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.getNextArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   153: astore_3       
        //   154: aload_1        
        //   155: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.getPreviousArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   160: astore          4
        //   162: aload_3        
        //   163: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.c:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;)Z
        //   166: ifne            184
        //   169: aload           4
        //   171: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.c:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;)Z
        //   174: ifeq            190
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   183: athrow         
        //   184: iconst_1       
        //   185: ireturn        
        //   186: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   189: athrow         
        //   190: iconst_0       
        //   191: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      14     17     21     Lcom/intellij/util/IncorrectOperationException;
        //  7      28     31     35     Lcom/intellij/util/IncorrectOperationException;
        //  21     42     45     49     Lcom/intellij/util/IncorrectOperationException;
        //  35     56     59     63     Lcom/intellij/util/IncorrectOperationException;
        //  49     70     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  63     84     87     91     Lcom/intellij/util/IncorrectOperationException;
        //  77     98     101    105    Lcom/intellij/util/IncorrectOperationException;
        //  91     112    115    119    Lcom/intellij/util/IncorrectOperationException;
        //  105    126    129    133    Lcom/intellij/util/IncorrectOperationException;
        //  119    137    137    141    Lcom/intellij/util/IncorrectOperationException;
        //  162    177    180    184    Lcom/intellij/util/IncorrectOperationException;
        //  169    186    186    190    Lcom/intellij/util/IncorrectOperationException;
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
    
    private static boolean c(@Nullable final CMakeArgument cMakeArgument) {
        try {
            if (e(cMakeArgument) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    private static PsiReference e(@Nullable final CMakeArgument cMakeArgument) {
        try {
            if (cMakeArgument == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final CMakeLiteral cMakeLiteral = cMakeArgument.getCMakeLiteral();
        try {
            if (cMakeLiteral == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        for (final PsiReference psiReference : cMakeLiteral.getReferences()) {
            try {
                if (psiReference.resolve() instanceof PsiFile) {
                    return psiReference;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public Collection<? extends PsiElement> getElementsToSearch(@NotNull final PsiElement psiElement, @NotNull final Collection<PsiElement> collection) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "getElementsToSearch"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "getElementsToSearch"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return Collections.singletonList(psiElement);
    }
    
    @Nullable
    @Override
    public Collection<PsiElement> getAdditionalElementsToDelete(@NotNull final PsiElement psiElement, @NotNull final Collection<PsiElement> collection, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "getAdditionalElementsToDelete"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "getAdditionalElementsToDelete"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Collection<String> findConflicts(@NotNull final PsiElement psiElement, @NotNull final PsiElement[] array) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "findConflicts"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allElementsToDelete", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeSafeDeleteProcessorDelegate", "findConflicts"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public UsageInfo[] preprocessUsages(final Project project, final UsageInfo[] array) {
        return array;
    }
    
    @Override
    public void prepareForDeletion(final PsiElement psiElement) throws IncorrectOperationException {
    }
    
    @Override
    public boolean isToSearchInComments(final PsiElement psiElement) {
        return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS;
    }
    
    @Override
    public void setToSearchInComments(final PsiElement psiElement, final boolean safe_DELETE_SEARCH_IN_COMMENTS) {
        RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_COMMENTS = safe_DELETE_SEARCH_IN_COMMENTS;
    }
    
    @Override
    public boolean isToSearchForTextOccurrences(final PsiElement psiElement) {
        return RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA;
    }
    
    @Override
    public void setToSearchForTextOccurrences(final PsiElement psiElement, final boolean safe_DELETE_SEARCH_IN_NON_JAVA) {
        RefactoringSettings.getInstance().SAFE_DELETE_SEARCH_IN_NON_JAVA = safe_DELETE_SEARCH_IN_NON_JAVA;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
