// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import java.util.Iterator;
import com.intellij.openapi.progress.ProgressManager;
import java.util.List;
import com.intellij.openapi.util.Ref;
import com.intellij.codeInsight.daemon.impl.DefaultHighlightVisitorBasedInspection;
import java.util.LinkedHashSet;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.refactoring.OCSafeDeleteProcessorDelegate;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Set;
import com.intellij.openapi.util.Key;
import com.intellij.codeInspection.GlobalSimpleInspectionTool;

public class OCGlobalUnusedInspection extends GlobalSimpleInspectionTool
{
    private static Key<Set<OCSymbol>> USED_SYMBOLS;
    private static Key<Set<OCSymbol>> ACCESSED_SYMBOLS;
    private static Key<Set<String>> USED_MACRO_NAMES;
    private static Key<Set<VirtualFile>> ANALYZED_FILES;
    private ProblemsHolder myProblemsHolder;
    
    public OCGlobalUnusedInspection(final ProblemsHolder myProblemsHolder) {
        this.myProblemsHolder = myProblemsHolder;
    }
    
    public OCGlobalUnusedInspection() {
    }
    
    public static void markSymbolAsUsed(@Nullable final OCSymbol ocSymbol, final PsiElement psiElement) {
        Label_0023: {
            try {
                if (ocSymbol == null) {
                    return;
                }
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol;
                final boolean b = OCSearchScope.isInProjectSources(ocInstanceVariableSymbol);
                if (!b) {
                    return;
                }
                break Label_0023;
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol;
                final boolean b = OCSearchScope.isInProjectSources(ocInstanceVariableSymbol);
                if (!b) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
        }
        final Project project = ocSymbol.getProject();
        if (project != null) {
            final Set set = (Set)project.getUserData((Key)OCGlobalUnusedInspection.USED_SYMBOLS);
            final Set set2 = (Set)project.getUserData((Key)OCGlobalUnusedInspection.USED_MACRO_NAMES);
            final Set set3 = (Set)project.getUserData((Key)OCGlobalUnusedInspection.ACCESSED_SYMBOLS);
            if (set != null) {
                synchronized (set) {
                    if (ocSymbol instanceof OCStructSymbol && OCSafeDeleteProcessorDelegate.isSafeToDelete(psiElement, ((OCStructSymbol)ocSymbol).locateDefinition())) {
                        return;
                    }
                    boolean b2 = false;
                    Label_0153: {
                        Label_0152: {
                            try {
                                set.add(ocSymbol);
                                if (!OCUnusedCodeInspection.isWritableSymbol(ocSymbol) || new OCReadWriteAccessDetector().getExpressionAccess(psiElement) != ReadWriteAccessDetector.Access.Write) {
                                    break Label_0152;
                                }
                            }
                            catch (IllegalStateException ex3) {
                                throw b(ex3);
                            }
                            b2 = true;
                            break Label_0153;
                        }
                        b2 = false;
                    }
                    final boolean b3 = b2;
                    try {
                        if (!b3) {
                            set3.add(ocSymbol);
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw b(ex4);
                    }
                    try {
                        if (ocSymbol instanceof OCMacroSymbol) {
                            set2.add(ocSymbol.getName());
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw b(ex5);
                    }
                    Object o = null;
                    if (ocSymbol instanceof OCMethodSymbol) {
                        o = ((OCMethodSymbol)ocSymbol).getGeneratedFromProperty();
                    }
                    boolean b5 = false;
                    Label_0325: {
                        Label_0324: {
                            Label_0296: {
                                Label_0276: {
                                    Label_0251: {
                                        try {
                                            if (!(ocSymbol instanceof OCInstanceVariableSymbol)) {
                                                break Label_0276;
                                            }
                                            final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = (OCInstanceVariableSymbol)ocSymbol;
                                            final OCInstanceVariableSymbol ocInstanceVariableSymbol3 = ocInstanceVariableSymbol2;
                                            final String s = ocInstanceVariableSymbol3.getGeneratedFromProperty();
                                            if (s != null) {
                                                break Label_0251;
                                            }
                                            break Label_0276;
                                        }
                                        catch (IllegalStateException ex6) {
                                            throw b(ex6);
                                        }
                                        try {
                                            final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = (OCInstanceVariableSymbol)ocSymbol;
                                            final OCInstanceVariableSymbol ocInstanceVariableSymbol3 = ocInstanceVariableSymbol2;
                                            final String s = ocInstanceVariableSymbol3.getGeneratedFromProperty();
                                            if (s == null) {
                                                break Label_0276;
                                            }
                                            if (psiElement instanceof OCSynthesizeProperty) {
                                                break Label_0276;
                                            }
                                        }
                                        catch (IllegalStateException ex7) {
                                            throw b(ex7);
                                        }
                                    }
                                    o = ((OCInstanceVariableSymbol)ocSymbol).getAssociatedProperty();
                                    try {
                                        if (o == null) {
                                            return;
                                        }
                                        final Object o2 = o;
                                        final boolean b4 = OCUnusedCodeInspection.isWritableSymbol((OCSymbol)o2);
                                        if (b4) {
                                            break Label_0296;
                                        }
                                        break Label_0324;
                                    }
                                    catch (IllegalStateException ex8) {
                                        throw b(ex8);
                                    }
                                }
                                try {
                                    final Object o2 = o;
                                    final boolean b4 = OCUnusedCodeInspection.isWritableSymbol((OCSymbol)o2);
                                    if (!b4) {
                                        break Label_0324;
                                    }
                                    if (new OCReadWriteAccessDetector().getExpressionAccess(psiElement) != ReadWriteAccessDetector.Access.Write) {
                                        break Label_0324;
                                    }
                                }
                                catch (IllegalStateException ex9) {
                                    throw b(ex9);
                                }
                            }
                            b5 = true;
                            break Label_0325;
                        }
                        b5 = false;
                    }
                    final boolean b6 = b5;
                    try {
                        set.add(o);
                        if (!b6) {
                            set3.add(o);
                        }
                    }
                    catch (IllegalStateException ex10) {
                        throw b(ex10);
                    }
                }
            }
        }
    }
    
    public boolean isEnabledByDefault() {
        return true;
    }
    
    @Nls
    @NotNull
    public String getDisplayName() {
        String s;
        try {
            s = "Global unused analysis";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Nls
    @NotNull
    public String getGroupDisplayName() {
        String s;
        try {
            s = "General";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "getGroupDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public void checkFile(@NotNull final PsiFile psiFile, @NotNull final InspectionManager inspectionManager, @NotNull final ProblemsHolder problemsHolder, @NotNull final GlobalInspectionContext globalInspectionContext, @NotNull final ProblemDescriptionsProcessor problemDescriptionsProcessor) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (inspectionManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "manager", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (problemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problemsHolder", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        try {
            if (globalInspectionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalContext", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        try {
            if (problemDescriptionsProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problemDescriptionsProcessor", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        final Set set = (Set)psiFile.getProject().getUserData((Key)OCGlobalUnusedInspection.ANALYZED_FILES);
        Label_0260: {
            try {
                if (set == null) {
                    return;
                }
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCFile;
                if (b) {
                    break Label_0260;
                }
                return;
            }
            catch (IllegalStateException ex6) {
                throw b(ex6);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCFile;
                if (b) {
                    set.add(psiFile.getVirtualFile());
                }
            }
            catch (IllegalStateException ex7) {
                throw b(ex7);
            }
        }
    }
    
    public void inspectionStarted(@NotNull final InspectionManager inspectionManager, @NotNull final GlobalInspectionContext globalInspectionContext, @NotNull final ProblemDescriptionsProcessor problemDescriptionsProcessor) {
        try {
            if (inspectionManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "manager", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "inspectionStarted"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (globalInspectionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalContext", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "inspectionStarted"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (problemDescriptionsProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problemDescriptionsProcessor", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection", "inspectionStarted"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        inspectionManager.getProject().putUserData((Key)OCGlobalUnusedInspection.USED_SYMBOLS, (Object)new THashSet());
        inspectionManager.getProject().putUserData((Key)OCGlobalUnusedInspection.ACCESSED_SYMBOLS, (Object)new THashSet());
        inspectionManager.getProject().putUserData((Key)OCGlobalUnusedInspection.USED_MACRO_NAMES, (Object)new THashSet());
        inspectionManager.getProject().putUserData((Key)OCGlobalUnusedInspection.ANALYZED_FILES, (Object)new LinkedHashSet());
    }
    
    public void inspectionFinished(@NotNull final InspectionManager p0, @NotNull final GlobalInspectionContext p1, @NotNull final ProblemDescriptionsProcessor p2) {
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
        //    18: ldc             "manager"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "inspectionFinished"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "globalContext"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "inspectionFinished"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "problemDescriptionsProcessor"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "inspectionFinished"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   com/intellij/codeInspection/InspectionManager.getProject:()Lcom/intellij/openapi/project/Project;
        //   136: astore          4
        //   138: aload           4
        //   140: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   143: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   148: checkcast       Ljava/util/Set;
        //   151: astore          5
        //   153: aload           4
        //   155: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ACCESSED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   158: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   163: checkcast       Ljava/util/Set;
        //   166: astore          6
        //   168: aload           4
        //   170: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_MACRO_NAMES:Lcom/intellij/openapi/util/Key;
        //   173: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   178: checkcast       Ljava/util/Set;
        //   181: astore          7
        //   183: aload           4
        //   185: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ANALYZED_FILES:Lcom/intellij/openapi/util/Key;
        //   188: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   193: checkcast       Ljava/util/Set;
        //   196: astore          8
        //   198: new             Ljava/util/ArrayList;
        //   201: dup            
        //   202: invokespecial   java/util/ArrayList.<init>:()V
        //   205: astore          9
        //   207: aload           5
        //   209: ifnull          248
        //   212: aload           6
        //   214: ifnull          248
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   223: athrow         
        //   224: aload           8
        //   226: ifnull          248
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   235: athrow         
        //   236: aload           7
        //   238: ifnonnull       293
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   247: athrow         
        //   248: aload           4
        //   250: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   253: aconst_null    
        //   254: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   259: aload           4
        //   261: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ACCESSED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   264: aconst_null    
        //   265: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   270: aload           4
        //   272: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ANALYZED_FILES:Lcom/intellij/openapi/util/Key;
        //   275: aconst_null    
        //   276: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   281: aload           4
        //   283: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_MACRO_NAMES:Lcom/intellij/openapi/util/Key;
        //   286: aconst_null    
        //   287: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   292: return         
        //   293: new             Lcom/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$1;
        //   296: dup            
        //   297: aload_0        
        //   298: aload_1        
        //   299: invokevirtual   com/intellij/codeInspection/InspectionManager.getProject:()Lcom/intellij/openapi/project/Project;
        //   302: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getProjectSourcesScope:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/GlobalSearchScope;
        //   305: aload           8
        //   307: invokespecial   com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$1.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection;Lcom/intellij/psi/search/GlobalSearchScope;Ljava/util/Set;)V
        //   310: astore          10
        //   312: invokestatic    com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.getInstance:()Lcom/jetbrains/cidr/lang/inspections/OCInspectionToolProvider;
        //   315: invokevirtual   com/jetbrains/cidr/lang/inspections/OCInspectionToolProvider.getStandaloneInspectionClasses:()[Ljava/lang/Class;
        //   318: astore          11
        //   320: aload           11
        //   322: arraylength    
        //   323: istore          12
        //   325: iconst_0       
        //   326: istore          13
        //   328: iload           13
        //   330: iload           12
        //   332: if_icmpge       421
        //   335: aload           11
        //   337: iload           13
        //   339: aaload         
        //   340: astore          14
        //   342: ldc             Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection;.class
        //   344: aload           14
        //   346: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   349: ifeq            415
        //   352: aload           14
        //   354: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //   357: checkcast       Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection;
        //   360: invokevirtual   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection.buildVisitor:()Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor;
        //   363: astore          15
        //   365: aload           15
        //   367: ifnull          405
        //   370: aload           15
        //   372: aload_3        
        //   373: aload_2        
        //   374: aload           10
        //   376: invokevirtual   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.setUpForBatchMode:(Lcom/intellij/codeInspection/ProblemDescriptionsProcessor;Lcom/intellij/codeInspection/GlobalInspectionContext;Lcom/intellij/psi/search/SearchScope;)V
        //   379: aload           15
        //   381: aload_0        
        //   382: getfield        com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.myProblemsHolder:Lcom/intellij/codeInspection/ProblemsHolder;
        //   385: invokevirtual   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.setHolder:(Lcom/intellij/codeInspection/ProblemsHolder;)V
        //   388: aload           9
        //   390: aload           15
        //   392: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   397: pop            
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   404: athrow         
        //   405: goto            415
        //   408: astore          15
        //   410: goto            415
        //   413: astore          15
        //   415: iinc            13, 1
        //   418: goto            328
        //   421: new             Lcom/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$2;
        //   424: dup            
        //   425: aload_0        
        //   426: invokespecial   com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$2.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection;)V
        //   429: astore          11
        //   431: aload           8
        //   433: aload           6
        //   435: aload           7
        //   437: aload           11
        //   439: invokedynamic   process:(Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lcom/intellij/util/containers/MultiMap;)Lcom/intellij/util/Processor;
        //   444: astore          12
        //   446: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   449: aload           4
        //   451: aload           12
        //   453: invokedynamic   run:(Lcom/intellij/openapi/project/Project;Lcom/intellij/util/Processor;)Ljava/lang/Runnable;
        //   458: invokeinterface com/intellij/openapi/application/Application.runReadAction:(Ljava/lang/Runnable;)V
        //   463: ldc             "Evaluating unused symbols"
        //   465: invokestatic    com/intellij/openapi/progress/ProgressManager.progress:(Ljava/lang/String;)V
        //   468: aload           8
        //   470: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   475: astore          13
        //   477: aload           13
        //   479: invokeinterface java/util/Iterator.hasNext:()Z
        //   484: ifeq            571
        //   487: aload           13
        //   489: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   494: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   497: astore          14
        //   499: aload           11
        //   501: aload           14
        //   503: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   506: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   511: astore          15
        //   513: aload           15
        //   515: invokeinterface java/util/Iterator.hasNext:()Z
        //   520: ifeq            568
        //   523: aload           15
        //   525: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   530: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   533: astore          16
        //   535: new             Lcom/intellij/openapi/util/Ref;
        //   538: dup            
        //   539: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   542: astore          17
        //   544: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   547: aload           16
        //   549: aload           17
        //   551: aload           9
        //   553: aload           5
        //   555: invokedynamic   run:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/util/Ref;Ljava/util/List;Ljava/util/Set;)Ljava/lang/Runnable;
        //   560: invokeinterface com/intellij/openapi/application/Application.runReadAction:(Ljava/lang/Runnable;)V
        //   565: goto            513
        //   568: goto            477
        //   571: aload           4
        //   573: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   576: aconst_null    
        //   577: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   582: aload           4
        //   584: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ACCESSED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   587: aconst_null    
        //   588: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   593: aload           4
        //   595: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ANALYZED_FILES:Lcom/intellij/openapi/util/Key;
        //   598: aconst_null    
        //   599: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   604: aload           4
        //   606: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_MACRO_NAMES:Lcom/intellij/openapi/util/Key;
        //   609: aconst_null    
        //   610: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   615: goto            667
        //   618: astore          18
        //   620: aload           4
        //   622: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   625: aconst_null    
        //   626: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   631: aload           4
        //   633: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ACCESSED_SYMBOLS:Lcom/intellij/openapi/util/Key;
        //   636: aconst_null    
        //   637: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   642: aload           4
        //   644: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.ANALYZED_FILES:Lcom/intellij/openapi/util/Key;
        //   647: aconst_null    
        //   648: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   653: aload           4
        //   655: getstatic       com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection.USED_MACRO_NAMES:Lcom/intellij/openapi/util/Key;
        //   658: aconst_null    
        //   659: invokeinterface com/intellij/openapi/project/Project.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   664: aload           18
        //   666: athrow         
        //   667: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  365    398    401    405    Ljava/lang/InstantiationException;
        //  224    241    244    248    Ljava/lang/InstantiationException;
        //  212    229    232    236    Ljava/lang/InstantiationException;
        //  207    217    220    224    Ljava/lang/InstantiationException;
        //  88     128    128    132    Ljava/lang/InstantiationException;
        //  44     84     84     88     Ljava/lang/InstantiationException;
        //  0      40     40     44     Ljava/lang/InstantiationException;
        //  352    405    408    413    Ljava/lang/InstantiationException;
        //  352    405    413    415    Ljava/lang/IllegalAccessException;
        //  198    248    618    667    Any
        //  293    571    618    667    Any
        //  618    620    618    667    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0224:
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
    public String getMainToolId() {
        return new DefaultHighlightVisitorBasedInspection.AnnotatorBasedInspection().getShortName();
    }
    
    static {
        OCGlobalUnusedInspection.USED_SYMBOLS = (Key<Set<OCSymbol>>)Key.create("USED_SYMBOLS");
        OCGlobalUnusedInspection.ACCESSED_SYMBOLS = (Key<Set<OCSymbol>>)Key.create("ACCESSED_SYMBOLS");
        OCGlobalUnusedInspection.USED_MACRO_NAMES = (Key<Set<String>>)Key.create("USED_MACRO_NAMES");
        OCGlobalUnusedInspection.ANALYZED_FILES = (Key<Set<VirtualFile>>)Key.create("ANALYSIS_SCOPE");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
