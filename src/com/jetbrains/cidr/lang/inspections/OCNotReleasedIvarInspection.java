// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCNotReleasedVariablesChecker;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorSink;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.daemon.OCNullAnnotatorSink;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCStatement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTextIntentionAction;
import com.intellij.openapi.util.Pair;
import java.util.Set;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInspection.IntentionWrapper;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import java.util.Collections;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInspection.LocalInspectionToolSession;
import java.awt.Component;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.util.ui.CheckBox;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import com.intellij.openapi.util.Key;

public class OCNotReleasedIvarInspection extends OCInspections.GeneralObjC
{
    private static Key<IvarsInfo> IVARS_INFO_KEY;
    public boolean releaseInDealloc;
    
    public OCNotReleasedIvarInspection() {
        this.releaseInDealloc = true;
    }
    
    public JComponent createOptionsPanel() {
        final JPanel panel = new JPanel(new FlowLayout(0));
        panel.add((Component)new CheckBox("Release should be in dealloc or any of its callees", (InspectionProfileEntry)this, "releaseInDealloc"));
        return panel;
    }
    
    public void inspectionStarted(@NotNull final LocalInspectionToolSession localInspectionToolSession, final boolean b) {
        try {
            if (localInspectionToolSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "inspectionStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!OCCompilerFeatures.isArcDisabled(localInspectionToolSession.getFile())) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final IvarsInfo ivarsInfo = new IvarsInfo();
        localInspectionToolSession.putUserData((Key)OCNotReleasedIvarInspection.IVARS_INFO_KEY, (Object)ivarsInfo);
        localInspectionToolSession.getFile().accept((PsiElementVisitor)new InitialVisitor(ivarsInfo));
    }
    
    public static IvarsInfo startInspection(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "startInspection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCNotReleasedIvarInspection ocNotReleasedIvarInspection = new OCNotReleasedIvarInspection();
        final LocalInspectionToolSession localInspectionToolSession = new LocalInspectionToolSession(psiFile, 0, psiFile.getTextLength());
        ocNotReleasedIvarInspection.inspectionStarted(localInspectionToolSession, true);
        return (IvarsInfo)localInspectionToolSession.getUserData((Key)OCNotReleasedIvarInspection.IVARS_INFO_KEY);
    }
    
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder p0, final boolean p1, @NotNull final LocalInspectionToolSession p2) {
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
        //    18: ldc             "holder"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "buildVisitor"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "session"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "buildVisitor"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: getstatic       com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.IVARS_INFO_KEY:Lcom/intellij/openapi/util/Key;
        //    92: invokevirtual   com/intellij/codeInspection/LocalInspectionToolSession.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //    95: checkcast       Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //    98: astore          4
        //   100: aload_1        
        //   101: invokevirtual   com/intellij/codeInspection/ProblemsHolder.getFile:()Lcom/intellij/psi/PsiFile;
        //   104: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   107: ifeq            122
        //   110: aload           4
        //   112: ifnonnull       175
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: new             Lcom/jetbrains/cidr/lang/psi/visitors/OCVisitor;
        //   125: dup            
        //   126: invokespecial   com/jetbrains/cidr/lang/psi/visitors/OCVisitor.<init>:()V
        //   129: dup            
        //   130: ifnonnull       174
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: new             Ljava/lang/IllegalStateException;
        //   143: dup            
        //   144: ldc             "@NotNull method %s.%s must not return null"
        //   146: ldc             2
        //   148: anewarray       Ljava/lang/Object;
        //   151: dup            
        //   152: ldc             0
        //   154: ldc             "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection"
        //   156: aastore        
        //   157: dup            
        //   158: ldc             1
        //   160: ldc             "buildVisitor"
        //   162: aastore        
        //   163: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   166: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   169: athrow         
        //   170: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: areturn        
        //   175: new             Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$1;
        //   178: dup            
        //   179: aload_0        
        //   180: aload_1        
        //   181: aload           4
        //   183: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$1.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection;Lcom/intellij/codeInspection/ProblemsHolder;Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)V
        //   186: astore          5
        //   188: iconst_0       
        //   189: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   192: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   195: astore          6
        //   197: new             Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$2;
        //   200: dup            
        //   201: aload_0        
        //   202: aload           6
        //   204: aload           5
        //   206: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$2.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/psi/visitors/OCVisitor;)V
        //   209: dup            
        //   210: ifnonnull       247
        //   213: new             Ljava/lang/IllegalStateException;
        //   216: dup            
        //   217: ldc             "@NotNull method %s.%s must not return null"
        //   219: ldc             2
        //   221: anewarray       Ljava/lang/Object;
        //   224: dup            
        //   225: ldc             0
        //   227: ldc             "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection"
        //   229: aastore        
        //   230: dup            
        //   231: ldc             1
        //   233: ldc             "buildVisitor"
        //   235: aastore        
        //   236: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   239: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   242: athrow         
        //   243: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  100    115    118    122    Ljava/lang/IllegalArgumentException;
        //  110    133    136    140    Ljava/lang/IllegalArgumentException;
        //  122    170    170    174    Ljava/lang/IllegalArgumentException;
        //  197    243    243    247    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    
    public void reportWarning(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final PsiElement psiElement, final ProblemsHolder problemsHolder) {
        this.registerProblem(problemsHolder, null, null, problemsHolder.isOnTheFly(), psiElement, ocInstanceVariableSymbol.getNameWithKindUppercase() + " is not released in 'dealloc' method", "CIDR", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new IntentionWrapper((IntentionAction)new OCReleaseVariablesIntentionAction(Collections.singletonList(ocInstanceVariableSymbol)), problemsHolder.getFile()));
    }
    
    public static boolean isIvarReleased(final OCInstanceVariableSymbol ocInstanceVariableSymbol, @NotNull final PsiFile psiFile, @NotNull final IvarsInfo ivarsInfo) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "isIvarReleased"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ivarsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "isIvarReleased"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(ocInstanceVariableSymbol, null, null, psiFile, ivarsInfo);
    }
    
    private static boolean a(final OCInstanceVariableSymbol ocInstanceVariableSymbol, @Nullable final PsiElement psiElement, @Nullable final OCMethodSymbol ocMethodSymbol, @NotNull final PsiFile psiFile, @NotNull final IvarsInfo ivarsInfo) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "isIvarReleased"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ivarsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "isIvarReleased"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0132: {
            try {
                if (!ocInstanceVariableSymbol.getType().resolve(psiFile).isPointerToObjectCompatible()) {
                    return true;
                }
                final IvarsInfo ivarsInfo2 = ivarsInfo;
                final Set set = ivarsInfo2.myReleasedIvars;
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                final boolean b = set.contains(ocInstanceVariableSymbol2);
                if (b) {
                    return true;
                }
                break Label_0132;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final IvarsInfo ivarsInfo2 = ivarsInfo;
                final Set set = ivarsInfo2.myReleasedIvars;
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                final boolean b = set.contains(ocInstanceVariableSymbol2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final Map<Object, PsiElement> map = ivarsInfo.myLocalReleases.get(ocInstanceVariableSymbol);
        Label_0163: {
            try {
                if (ocMethodSymbol == null) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                if (psiElement2 != null) {
                    break Label_0163;
                }
                return false;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                if (psiElement2 == null) {
                    return false;
                }
                if (map == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final PsiElement psiElement3 = map.get(ocMethodSymbol);
        Label_0216: {
            try {
                if (psiElement3 == null) {
                    return false;
                }
                final PsiElement psiElement4 = psiElement;
                final int n = psiElement4.getTextOffset();
                final PsiElement psiElement5 = psiElement3;
                final int n2 = psiElement5.getTextOffset();
                if (n < n2) {
                    break Label_0216;
                }
                return false;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                final PsiElement psiElement4 = psiElement;
                final int n = psiElement4.getTextOffset();
                final PsiElement psiElement5 = psiElement3;
                final int n2 = psiElement5.getTextOffset();
                if (n < n2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return false;
    }
    
    public void inspectionFinished(@NotNull final LocalInspectionToolSession localInspectionToolSession, @NotNull final ProblemsHolder problemsHolder) {
        try {
            if (localInspectionToolSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "inspectionFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (problemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection", "inspectionFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final IvarsInfo ivarsInfo = (IvarsInfo)localInspectionToolSession.getUserData((Key)OCNotReleasedIvarInspection.IVARS_INFO_KEY);
        Label_0125: {
            try {
                if (!OCCompilerFeatures.isArcDisabled(localInspectionToolSession.getFile())) {
                    return;
                }
                final IvarsInfo ivarsInfo2 = ivarsInfo;
                if (ivarsInfo2 == null) {
                    return;
                }
                break Label_0125;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final IvarsInfo ivarsInfo2 = ivarsInfo;
                if (ivarsInfo2 == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        for (final Pair pair : ivarsInfo.myLocalRetainedIvars) {
            try {
                if (isIvarReleased((OCInstanceVariableSymbol)pair.getFirst(), problemsHolder.getFile(), ivarsInfo)) {
                    continue;
                }
                this.reportWarning((OCInstanceVariableSymbol)pair.getFirst(), (PsiElement)pair.getSecond(), problemsHolder);
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        for (final OCMethodSymbol ocMethodSymbol : ivarsInfo.myDeallocs.keySet()) {
            final PsiElement psiElement = ivarsInfo.myDeallocs.get(ocMethodSymbol);
            try {
                if (psiElement == null) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            final PsiFile containingFile = psiElement.getContainingFile();
            if (containingFile.equals(problemsHolder.getFile())) {
                this.registerProblem(problemsHolder, null, null, problemsHolder.isOnTheFly(), psiElement, ocMethodSymbol.getNameWithKindUppercase() + " misses the call to [super dealloc] at the last statement", "CIDR", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new OCChangeTextIntentionAction(containingFile, psiElement.getTextOffset(), 0, "[super dealloc];\n", "Add call to [super dealloc]"));
            }
        }
    }
    
    @Nullable
    public static OCSendMessageExpression getCallToSuper(final OCStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //     4: ifne            13
        //     7: aconst_null    
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_0        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    22: astore_1       
        //    23: aload_1        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    27: ifne            36
        //    30: aconst_null    
        //    31: areturn        
        //    32: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_1        
        //    37: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    40: astore_2       
        //    41: aload_2        
        //    42: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    47: astore_3       
        //    48: aload_2        
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    54: ldc             "dealloc"
        //    56: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    59: ifeq            106
        //    62: aload_3        
        //    63: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    66: ifeq            106
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_3        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    80: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    85: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SUPER:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    88: if_acmpne       106
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: aload_2        
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aconst_null    
        //   107: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  23     32     32     36     Ljava/lang/IllegalArgumentException;
        //  48     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     91     94     98     Ljava/lang/IllegalArgumentException;
        //  76     102    102    106    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    public static OCInstanceVariableSymbol getReceiverIvar(@Nullable final OCExpression ocExpression) {
        return getReceiverIvar(ocExpression, true);
    }
    
    @Nullable
    public static OCInstanceVariableSymbol getReceiverIvar(@Nullable final OCExpression ocExpression, final boolean b) {
        final OCSymbol receiverSymbol = getReceiverSymbol(ocExpression, b);
        try {
            if (receiverSymbol instanceof OCInstanceVariableSymbol) {
                return (OCInstanceVariableSymbol)receiverSymbol;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0048: {
            try {
                if (!(receiverSymbol instanceof OCPropertySymbol)) {
                    return null;
                }
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)receiverSymbol;
                final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocInstanceVariableSymbol;
                final boolean b2 = ocPropertySymbol.isRetained();
                if (!b2) {
                    break Label_0048;
                }
                return null;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)receiverSymbol;
                final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocInstanceVariableSymbol;
                final boolean b2 = ocPropertySymbol.isRetained();
                if (!b2) {
                    return ((OCPropertySymbol)receiverSymbol).getAssociatedIvar();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public static OCSymbol getReceiverSymbol(@Nullable final OCExpression ocExpression, final boolean b) {
        OCSymbol ocSymbol = null;
        if (ocExpression instanceof OCReferenceExpression) {
            ocSymbol = ((OCReferenceExpression)ocExpression).resolveToSymbol();
        }
        else if (ocExpression instanceof OCQualifiedExpression) {
            final OCExpression qualifier = ((OCQualifiedExpression)ocExpression).getQualifier();
            Label_0057: {
                try {
                    if (!b) {
                        break Label_0057;
                    }
                    final OCExpression ocExpression2 = qualifier;
                    final boolean b2 = ocExpression2 instanceof OCReferenceExpression;
                    if (b2) {
                        break Label_0057;
                    }
                    return ocSymbol;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCExpression ocExpression2 = qualifier;
                    final boolean b2 = ocExpression2 instanceof OCReferenceExpression;
                    if (!b2) {
                        return ocSymbol;
                    }
                    if (((OCReferenceExpression)qualifier).getSelfSuperToken() != OCElementTypes.SelfSuperToken.SELF) {
                        return ocSymbol;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            ocSymbol = ((OCQualifiedExpression)ocExpression).resolveToSymbol();
        }
        return ocSymbol;
    }
    
    static {
        OCNotReleasedIvarInspection.IVARS_INFO_KEY = (Key<IvarsInfo>)Key.create("IVARS_INFO_KEY");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class IvarsInfo
    {
        private Set<OCInstanceVariableSymbol> myReleasedIvars;
        private Map<OCInstanceVariableSymbol, Map<OCMethodSymbol, PsiElement>> myLocalReleases;
        private Set<OCSymbol> myTraversedCallables;
        private Map<OCMethodSymbol, PsiElement> myDeallocs;
        private List<Pair<OCInstanceVariableSymbol, PsiElement>> myLocalRetainedIvars;
        
        public IvarsInfo() {
            this.myReleasedIvars = new HashSet<OCInstanceVariableSymbol>();
            this.myLocalReleases = new HashMap<OCInstanceVariableSymbol, Map<OCMethodSymbol, PsiElement>>();
            this.myTraversedCallables = new HashSet<OCSymbol>();
            this.myDeallocs = new HashMap<OCMethodSymbol, PsiElement>();
            this.myLocalRetainedIvars = new ArrayList<Pair<OCInstanceVariableSymbol, PsiElement>>();
        }
    }
    
    private static class CallableVisitor extends OCRecursiveVisitor
    {
        private VirtualFile myContainingFile;
        private boolean isDealloc;
        private OCClassSymbol myClass;
        private IvarsInfo myIvarsInfo;
        private OCClassSymbol myOriginalClass;
        
        private CallableVisitor(final VirtualFile myContainingFile, final OCClassSymbol myClass, final OCClassSymbol myOriginalClass, final boolean isDealloc, @NotNull final IvarsInfo myIvarsInfo) {
            if (myIvarsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor", "<init>"));
            }
            this.myContainingFile = myContainingFile;
            this.myOriginalClass = myOriginalClass;
            this.isDealloc = isDealloc;
            this.myClass = myClass;
            this.myIvarsInfo = myIvarsInfo;
        }
        
        @Override
        public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
            super.visitSendMessageExpression(ocSendMessageExpression);
            if (OCElementUtil.isReleaseCall((PsiElement)ocSendMessageExpression)) {
                final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression(), false);
                try {
                    if (receiverIvar != null) {
                        this.myIvarsInfo.myReleasedIvars.add(receiverIvar);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return;
            }
            for (final OCMethodSymbol ocMethodSymbol : ocSendMessageExpression.getProbableResponders().getFilteredByStaticnessResponders()) {
                final OCPropertySymbol generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
                Label_0112: {
                    try {
                        if (generatedFromProperty == null) {
                            break Label_0112;
                        }
                        final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                        final boolean b = ocMethodSymbol2.isSetter();
                        if (b) {
                            break Label_0112;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                        final boolean b = ocMethodSymbol2.isSetter();
                        if (b) {
                            this.a(generatedFromProperty);
                            continue;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                this.a((OCSymbol)ocMethodSymbol);
            }
        }
        
        @Override
        public void visitQualifiedExpression(final OCQualifiedExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: invokespecial   com/jetbrains/cidr/lang/psi/visitors/OCRecursiveVisitor.visitQualifiedExpression:(Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;)V
            //     5: aload_1        
            //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getParent:()Lcom/intellij/psi/PsiElement;
            //    11: astore_2       
            //    12: aload_1        
            //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    18: astore_3       
            //    19: aload_3        
            //    20: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
            //    23: ifeq            201
            //    26: aload_3        
            //    27: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
            //    30: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    35: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    38: astore          4
            //    40: aload           4
            //    42: ifnull          201
            //    45: aload           4
            //    47: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //    52: aload_0        
            //    53: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    56: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //    61: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //    64: ifeq            201
            //    67: goto            74
            //    70: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    73: athrow         
            //    74: aload_0        
            //    75: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
            //    78: ifeq            126
            //    81: goto            88
            //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_3        
            //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
            //    92: ifeq            126
            //    95: goto            102
            //    98: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   101: athrow         
            //   102: aload_0        
            //   103: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
            //   106: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$000:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
            //   109: aload_3        
            //   110: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
            //   113: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //   118: pop            
            //   119: goto            201
            //   122: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   125: athrow         
            //   126: aload_3        
            //   127: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //   130: ifeq            182
            //   133: aload_2        
            //   134: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
            //   137: ifeq            182
            //   140: goto            147
            //   143: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   146: athrow         
            //   147: aload_2        
            //   148: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
            //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //   156: aload_1        
            //   157: if_acmpne       182
            //   160: goto            167
            //   163: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   166: athrow         
            //   167: aload_0        
            //   168: aload_3        
            //   169: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //   172: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
            //   175: goto            201
            //   178: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   181: athrow         
            //   182: aload_3        
            //   183: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //   186: ifeq            201
            //   189: aload_0        
            //   190: aload_3        
            //   191: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   194: goto            201
            //   197: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   200: athrow         
            //   201: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  40     67     70     74     Ljava/lang/IllegalArgumentException;
            //  45     81     84     88     Ljava/lang/IllegalArgumentException;
            //  74     95     98     102    Ljava/lang/IllegalArgumentException;
            //  88     122    122    126    Ljava/lang/IllegalArgumentException;
            //  126    140    143    147    Ljava/lang/IllegalArgumentException;
            //  133    160    163    167    Ljava/lang/IllegalArgumentException;
            //  147    178    178    182    Ljava/lang/IllegalArgumentException;
            //  182    194    197    201    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
            super.visitReferenceElement(ocReferenceElement);
            if (this.isDealloc) {
                final OCSymbol resolveToSymbol = ocReferenceElement.resolveToSymbol();
                try {
                    if (resolveToSymbol instanceof OCInstanceVariableSymbol) {
                        this.myIvarsInfo.myReleasedIvars.add(resolveToSymbol);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        
        @Override
        public void visitCallExpression(final OCCallExpression ocCallExpression) {
            super.visitCallExpression(ocCallExpression);
            final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
            final OCGetSymbolVisitor ocGetSymbolVisitor = new OCGetSymbolVisitor();
            functionReferenceExpression.accept((PsiElementVisitor)ocGetSymbolVisitor);
            final OCSymbol symbol = ocGetSymbolVisitor.getSymbol();
            try {
                if (symbol instanceof OCFunctionSymbol) {
                    this.a(symbol);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        private void a(final OCPropertySymbol ocPropertySymbol) {
            try {
                if (!ocPropertySymbol.processAccessorMethods((Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                    try {
                        if (ocMethodSymbol.isSetter()) {
                            this.a((OCSymbol)ocMethodSymbol);
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return true;
                }), false) || !ocPropertySymbol.isRetained()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCInstanceVariableSymbol associatedIvar = ocPropertySymbol.getAssociatedIvar();
            try {
                if (associatedIvar != null) {
                    this.myIvarsInfo.myReleasedIvars.add(associatedIvar);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        
        private void a(final OCSymbol p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: astore_2       
            //     2: aload_2        
            //     3: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
            //     8: ifne            18
            //    11: aload_2        
            //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    17: astore_2       
            //    18: aload_1        
            //    19: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //    22: ifeq            142
            //    25: aload_1        
            //    26: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //    29: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    34: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    37: astore_3       
            //    38: aload_0        
            //    39: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    42: ifnull          142
            //    45: aload_2        
            //    46: ifnull          84
            //    49: goto            56
            //    52: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    55: athrow         
            //    56: aload_3        
            //    57: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //    62: aload_0        
            //    63: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    66: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //    71: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //    74: ifne            142
            //    77: goto            84
            //    80: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    83: athrow         
            //    84: aload_0        
            //    85: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    88: aload_3        
            //    89: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
            //    94: ifeq            142
            //    97: goto            104
            //   100: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   103: athrow         
            //   104: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
            //   107: dup            
            //   108: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
            //   111: astore          4
            //   113: aload_0        
            //   114: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //   117: aload_1        
            //   118: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   123: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
            //   125: aload           4
            //   127: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
            //   132: pop            
            //   133: aload           4
            //   135: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
            //   138: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   141: astore_2       
            //   142: aload_2        
            //   143: ifnull          258
            //   146: aload_0        
            //   147: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
            //   150: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$100:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
            //   153: aload_2        
            //   154: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
            //   159: ifne            258
            //   162: goto            169
            //   165: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   168: athrow         
            //   169: aload_0        
            //   170: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myContainingFile:Lcom/intellij/openapi/vfs/VirtualFile;
            //   173: aload_2        
            //   174: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //   179: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   182: ifeq            258
            //   185: goto            192
            //   188: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   191: athrow         
            //   192: aload_0        
            //   193: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
            //   196: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$100:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
            //   199: aload_2        
            //   200: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //   205: pop            
            //   206: aload_2        
            //   207: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
            //   212: astore_3       
            //   213: aload_3        
            //   214: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
            //   217: ifeq            227
            //   220: aload_3        
            //   221: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   226: astore_3       
            //   227: aload_3        
            //   228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
            //   231: ifeq            258
            //   234: aload_0        
            //   235: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
            //   238: istore          4
            //   240: aload_0        
            //   241: iconst_0       
            //   242: putfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
            //   245: aload_3        
            //   246: aload_0        
            //   247: invokeinterface com/intellij/psi/PsiElement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
            //   252: aload_0        
            //   253: iload           4
            //   255: putfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
            //   258: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  38     49     52     56     Ljava/lang/IllegalArgumentException;
            //  45     77     80     84     Ljava/lang/IllegalArgumentException;
            //  56     97     100    104    Ljava/lang/IllegalArgumentException;
            //  142    162    165    169    Ljava/lang/IllegalArgumentException;
            //  146    185    188    192    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private class InitialVisitor extends OCRecursiveVisitor
    {
        private IvarsInfo myIvarsInfo;
        private OCMethodSymbol curMethod;
        
        public InitialVisitor(final IvarsInfo myIvarsInfo) {
            if (myIvarsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$InitialVisitor", "<init>"));
            }
            this.myIvarsInfo = myIvarsInfo;
        }
        
        public void visitFile(final PsiFile psiFile) {
            for (final PsiElement psiElement : psiFile.getChildren()) {
                try {
                    if (psiElement instanceof OCImplementation) {
                        this.myIvarsInfo.myTraversedCallables.clear();
                        this.visitImplementation((OCImplementation)psiElement);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        
        @Override
        public void visitImplementation(final OCImplementation ocImplementation) {
            super.visitImplementation(ocImplementation);
            final VirtualFile virtualFile = ocImplementation.getContainingFile().getVirtualFile();
            try {
                if (virtualFile == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCObjectType ocObjectType = ocImplementation.getType();
            OCImplementationSymbol implementation = null;
            Label_0049: {
                try {
                    if (ocObjectType != null) {
                        implementation = ocObjectType.getImplementation();
                        break Label_0049;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                implementation = null;
            }
            final OCImplementationSymbol ocImplementationSymbol = implementation;
            while (ocObjectType != null) {
                OCImplementationSymbol implementation2 = ocObjectType.getImplementation();
                if (implementation2 == null) {
                    final OCInterfaceSymbol interface1 = ocObjectType.getInterface();
                    OCImplementationSymbol implementation3 = null;
                    Label_0092: {
                        try {
                            if (interface1 != null) {
                                implementation3 = interface1.getImplementation();
                                break Label_0092;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        implementation3 = null;
                    }
                    implementation2 = implementation3;
                    try {
                        if (implementation2 == null) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                final Processor processor = ocMethodSymbol -> {
                    final PsiElement locateDefinition = ocMethodSymbol.locateDefinition();
                    if (locateDefinition instanceof OCMethod) {
                        final boolean equals = "dealloc".equals(ocMethodSymbol.getName());
                        try {
                            if (!this.myIvarsInfo.myTraversedCallables.contains(ocMethodSymbol)) {
                                this.myIvarsInfo.myTraversedCallables.add(ocMethodSymbol);
                                locateDefinition.accept((PsiElementVisitor)new CallableVisitor(virtualFile, (OCClassSymbol)((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent(), (OCClassSymbol)ocImplementationSymbol, equals, this.myIvarsInfo));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        if (equals) {
                            final OCBlockStatement body = ((OCMethod)locateDefinition).getBody();
                            if (body != null) {
                                final int size = body.getStatements().size();
                                OCSendMessageExpression callToSuper = null;
                                Label_0169: {
                                    try {
                                        if (size > 0) {
                                            callToSuper = OCNotReleasedIvarInspection.getCallToSuper(body.getStatements().get(size - 1));
                                            break Label_0169;
                                        }
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    callToSuper = null;
                                }
                                if (callToSuper == null) {
                                    this.myIvarsInfo.myDeallocs.put(ocMethodSymbol, body.getClosingBrace());
                                }
                                else {
                                    this.myIvarsInfo.myDeallocs.put(ocMethodSymbol, null);
                                }
                            }
                        }
                    }
                    return true;
                };
                if (OCNotReleasedIvarInspection.this.releaseInDealloc) {
                    implementation2.processMembers("dealloc", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                    final OCType resolvedFromText = OCReferenceType.resolvedFromText("NSObject", "UIApplicationDelegate", ocImplementation.getContainingFile());
                    final OCType resolvedFromText2 = OCReferenceType.resolvedFromText("SenTest", ocImplementation.getContainingFile());
                    final OCType resolvedFromText3 = OCReferenceType.resolvedFromText("NSManagedObject", ocImplementation.getContainingFile());
                    Label_0293: {
                        Label_0268: {
                            Label_0249: {
                                Label_0224: {
                                    Label_0205: {
                                        try {
                                            if (!(resolvedFromText instanceof OCObjectType)) {
                                                break Label_0224;
                                            }
                                            final OCType ocType = resolvedFromText;
                                            final OCObjectType ocObjectType2 = ocObjectType;
                                            final OCImplementation ocImplementation2 = ocImplementation;
                                            final boolean b = ocType.isCompatible(ocObjectType2, (PsiElement)ocImplementation2);
                                            if (b) {
                                                break Label_0205;
                                            }
                                            break Label_0224;
                                        }
                                        catch (IllegalArgumentException ex5) {
                                            throw a(ex5);
                                        }
                                        try {
                                            final OCType ocType = resolvedFromText;
                                            final OCObjectType ocObjectType2 = ocObjectType;
                                            final OCImplementation ocImplementation2 = ocImplementation;
                                            final boolean b = ocType.isCompatible(ocObjectType2, (PsiElement)ocImplementation2);
                                            if (b) {
                                                implementation2.processMembers("applicationWillTerminate:", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                                            }
                                        }
                                        catch (IllegalArgumentException ex6) {
                                            throw a(ex6);
                                        }
                                    }
                                    try {
                                        if (!(resolvedFromText3 instanceof OCObjectType)) {
                                            break Label_0268;
                                        }
                                        final OCType ocType2 = resolvedFromText3;
                                        final OCObjectType ocObjectType3 = ocObjectType;
                                        final OCImplementation ocImplementation3 = ocImplementation;
                                        final boolean b2 = ocType2.isCompatible(ocObjectType3, (PsiElement)ocImplementation3);
                                        if (b2) {
                                            break Label_0249;
                                        }
                                        break Label_0268;
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw a(ex7);
                                    }
                                }
                                try {
                                    final OCType ocType2 = resolvedFromText3;
                                    final OCObjectType ocObjectType3 = ocObjectType;
                                    final OCImplementation ocImplementation3 = ocImplementation;
                                    final boolean b2 = ocType2.isCompatible(ocObjectType3, (PsiElement)ocImplementation3);
                                    if (b2) {
                                        implementation2.processMembers("didTurnIntoFault", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                                    }
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                            }
                            try {
                                if (!(resolvedFromText2 instanceof OCObjectType)) {
                                    break Label_0293;
                                }
                                final OCType ocType3 = resolvedFromText2;
                                final OCObjectType ocObjectType4 = ocObjectType;
                                final OCImplementation ocImplementation4 = ocImplementation;
                                final boolean b3 = ocType3.isCompatible(ocObjectType4, (PsiElement)ocImplementation4);
                                if (b3) {
                                    break Label_0293;
                                }
                                break Label_0293;
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                        }
                        try {
                            final OCType ocType3 = resolvedFromText2;
                            final OCObjectType ocObjectType4 = ocObjectType;
                            final OCImplementation ocImplementation4 = ocImplementation;
                            final boolean b3 = ocType3.isCompatible(ocObjectType4, (PsiElement)ocImplementation4);
                            if (b3) {
                                implementation2.processMembers("tearDown", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                            }
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                }
                else {
                    implementation2.processMembers((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                }
                ocObjectType = ocObjectType.getSuperType();
            }
        }
        
        @Override
        public void visitMethod(final OCMethod ocMethod) {
            this.curMethod = ocMethod.getSymbol();
            super.visitMethod(ocMethod);
            final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocMethod, OCNullAnnotatorSink.INSTANCE, null);
            ocDataFlowAnalyzer.buildControlFlowGraph();
            for (final OCSymbol ocSymbol : ocDataFlowAnalyzer.getGraph().getLocalSymbols()) {
                try {
                    if (ocSymbol.isUnnamed()) {
                        continue;
                    }
                    ocDataFlowAnalyzer.analyzeNotReleased(ocSymbol, new OCNotReleasedVariablesChecker(ocDataFlowAnalyzer.getGraph(), ocSymbol) {
                        @Override
                        protected void handleAssignedIvar(@NotNull final Pair<OCInstanceVariableSymbol, PsiElement> pair) {
                            try {
                                if (pair == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$InitialVisitor$1", "handleAssignedIvar"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw d(ex);
                            }
                            InitialVisitor.this.myIvarsInfo.myLocalRetainedIvars.add(pair);
                        }
                        
                        private static IllegalArgumentException d(final IllegalArgumentException ex) {
                            return ex;
                        }
                    }, false);
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            this.curMethod = null;
        }
        
        @Override
        public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
            try {
                super.visitSendMessageExpression(ocSendMessageExpression);
                if (this.curMethod == null || !OCElementUtil.isReleaseCall((PsiElement)ocSendMessageExpression)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression(), false);
            if (receiverIvar != null) {
                Map<OCMethodSymbol, OCArgumentSelector> map = this.myIvarsInfo.myLocalReleases.get(receiverIvar);
                if (map == null) {
                    map = new HashMap<OCMethodSymbol, OCArgumentSelector>();
                    this.myIvarsInfo.myLocalReleases.put(receiverIvar, map);
                }
                map.put(this.curMethod, ocSendMessageExpression.getArgumentSelectors().get(0));
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
