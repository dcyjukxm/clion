// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.quickfixes.OCImplementAllMethodsIntentionAction;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.quickfixes.OCCreateInterfaceIntentionAction;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import java.util.HashSet;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.quickfixes.OCImplementInterfaceIntentionAction;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.psi.OCSuperClassRef;
import com.jetbrains.cidr.lang.quickfixes.OCClearElementIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCCopyElementIntentionAction;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import org.jetbrains.annotations.NotNull;

public class OCImplementationChecker extends OCAnnotatorSinkWrapper
{
    public static final int NON_IMPLEMENTED_METHODS_STRING_MAX_LENGTH = 70;
    public static final int DECLARED_IN_CLASSES_STRING_MAX_LENGTH = 30;
    @NotNull
    private final OCDeclaratorChecker myDeclaratorChecker;
    @NotNull
    private final OCOperatorsChecker myOperatorsChecker;
    
    public OCImplementationChecker(@NotNull final OCAnnotatorSink ocAnnotatorSink, @NotNull final OCDeclaratorChecker myDeclaratorChecker, @NotNull final OCOperatorsChecker myOperatorsChecker) {
        if (ocAnnotatorSink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "impl", "com/jetbrains/cidr/lang/daemon/OCImplementationChecker", "<init>"));
        }
        if (myDeclaratorChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checker", "com/jetbrains/cidr/lang/daemon/OCImplementationChecker", "<init>"));
        }
        if (myOperatorsChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operatorsChecker", "com/jetbrains/cidr/lang/daemon/OCImplementationChecker", "<init>"));
        }
        super(ocAnnotatorSink);
        this.myDeclaratorChecker = myDeclaratorChecker;
        this.myOperatorsChecker = myOperatorsChecker;
    }
    
    private void a(final OCImplementation ocImplementation, final OCInterface ocInterface) {
        try {
            if (ocInterface == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCSuperClassRef superClassRef = ocImplementation.getSuperClassRef();
        final OCSuperClassRef superClassRef2 = ocInterface.getSuperClassRef();
        try {
            if (superClassRef.getFirstChild() == null || OCElementUtil.areElementsEquivalent((PsiElement)superClassRef, (PsiElement)superClassRef2, true)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)superClassRef, "err_conflicting_super_class", "Implementation superclass is inconsistent with interface superclass");
        this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCCopyElementIntentionAction((PsiElement)superClassRef2, (PsiElement)superClassRef, "Copy superclass from interface"));
        this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCCopyElementIntentionAction((PsiElement)superClassRef, (PsiElement)superClassRef2, "Copy superclass to interface"));
        this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCClearElementIntentionAction((PsiElement)superClassRef, "Remove superclass"));
    }
    
    public void checkMethod(final OCMethod p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelectors:()Ljava/util/List;
        //     6: astore_2       
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/daemon/OCImplementationChecker.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
        //    17: aload_1        
        //    18: aload_2        
        //    19: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkDuplicates:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/util/List;)Z
        //    22: pop            
        //    23: aload_1        
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    29: aload_1        
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    38: astore_3       
        //    39: aload_1        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    48: astore          4
        //    50: aload_3        
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //    54: ifne            82
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/lang/daemon/OCImplementationChecker.myDeclaratorChecker:Lcom/jetbrains/cidr/lang/daemon/OCDeclaratorChecker;
        //    61: aload_3        
        //    62: aload_1        
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    68: aload           4
        //    70: iconst_1       
        //    71: invokevirtual   com/jetbrains/cidr/lang/daemon/OCDeclaratorChecker.checkInstanceable:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Z
        //    74: pop            
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload           4
        //    84: ifnonnull       92
        //    87: return         
        //    88: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload           4
        //    94: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    99: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   102: ifne            125
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/daemon/OCImplementationChecker.myOperatorsChecker:Lcom/jetbrains/cidr/lang/daemon/OCOperatorsChecker;
        //   109: aload_2        
        //   110: aload           4
        //   112: ldc             "Declaring"
        //   114: invokevirtual   com/jetbrains/cidr/lang/daemon/OCOperatorsChecker.checkReadonlyAccess:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;)Z
        //   117: pop            
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload           4
        //   127: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isDefinition:()Z
        //   132: ifeq            438
        //   135: aload_1        
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   141: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //   144: ifeq            438
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           4
        //   156: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   161: astore          5
        //   163: aload           5
        //   165: ifnull          182
        //   168: aload           5
        //   170: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   175: goto            183
        //   178: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aconst_null    
        //   183: astore          6
        //   185: aload           6
        //   187: ifnull          438
        //   190: aload           6
        //   192: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   197: ifne            438
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload           6
        //   209: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   212: aload           6
        //   214: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   219: aload_1        
        //   220: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   225: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.NONATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   228: if_acmpeq       438
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: aload           6
        //   240: new             Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker$1;
        //   243: dup            
        //   244: aload_0        
        //   245: invokespecial   com/jetbrains/cidr/lang/daemon/OCImplementationChecker$1.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker;)V
        //   248: iconst_0       
        //   249: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processAccessorMethods:(Lcom/intellij/util/Processor;Z)Z
        //   254: ifeq            438
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: new             Ljava/lang/StringBuilder;
        //   267: dup            
        //   268: invokespecial   java/lang/StringBuilder.<init>:()V
        //   271: ldc             "Writable atomic "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: aload           6
        //   278: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: ldc             " can't have a"
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: aload           4
        //   293: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
        //   298: ifeq            317
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: ldc             " defined getter and a synthesized setter"
        //   310: goto            319
        //   313: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: ldc             " synthesized getter and a defined setter"
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   325: astore          7
        //   327: aload_0        
        //   328: aload_2        
        //   329: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$AccessorsWereOverridden;.class
        //   331: ldc             "warn_atomic_property_rule"
        //   333: aload           7
        //   335: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   338: astore          8
        //   340: aload_0        
        //   341: aload           8
        //   343: new             Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker$2;
        //   346: dup            
        //   347: aload_0        
        //   348: aload           6
        //   350: aload           4
        //   352: invokespecial   com/jetbrains/cidr/lang/daemon/OCImplementationChecker$2.<init>:(Lcom/jetbrains/cidr/lang/daemon/OCImplementationChecker;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)V
        //   355: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   358: aload_0        
        //   359: aload           8
        //   361: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction;
        //   364: dup            
        //   365: aload           6
        //   367: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   370: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.NONATOMIC:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   373: aconst_null    
        //   374: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;)V
        //   377: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   380: aload_0        
        //   381: aload           8
        //   383: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   386: dup            
        //   387: aload_1        
        //   388: new             Ljava/lang/StringBuilder;
        //   391: dup            
        //   392: invokespecial   java/lang/StringBuilder.<init>:()V
        //   395: ldc             "Remove user-defined "
        //   397: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   400: aload           4
        //   402: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
        //   407: ifeq            419
        //   410: ldc             "getter"
        //   412: goto            421
        //   415: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: ldc             "setter"
        //   421: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   424: ldc             " method"
        //   426: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   429: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   432: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   435: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFixes:(Ljava/util/List;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   438: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  50     75     78     82     Ljava/lang/IllegalArgumentException;
        //  82     88     88     92     Ljava/lang/IllegalArgumentException;
        //  92     118    121    125    Ljava/lang/IllegalArgumentException;
        //  125    147    150    154    Ljava/lang/IllegalArgumentException;
        //  163    178    178    182    Ljava/lang/IllegalArgumentException;
        //  185    200    203    207    Ljava/lang/IllegalArgumentException;
        //  190    231    234    238    Ljava/lang/IllegalArgumentException;
        //  207    257    260    264    Ljava/lang/IllegalArgumentException;
        //  238    301    304    308    Ljava/lang/IllegalArgumentException;
        //  264    313    313    317    Ljava/lang/IllegalArgumentException;
        //  340    415    415    419    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0207:
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
    
    private void a(final OCClassDeclaration<? extends OCClassSymbol> ocClassDeclaration) {
        final OCInstanceVariablesList instanceVariablesList = ocClassDeclaration.getInstanceVariablesList();
        final String category = ocClassDeclaration.getCategory();
        Label_0378: {
            Label_0311: {
                Label_0283: {
                    Label_0210: {
                        Label_0143: {
                            Label_0100: {
                                Label_0078: {
                                    try {
                                        if (category == null) {
                                            break Label_0143;
                                        }
                                        if (ocClassDeclaration.getSuperClassRef().isEmpty()) {
                                            break Label_0078;
                                        }
                                    }
                                    catch (IllegalArgumentException ex) {
                                        throw b(ex);
                                    }
                                    this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocClassDeclaration.getSuperClassRef(), OCInspections.ConstructionIsNotAllowed.class, "err_expected_unqualified_id", "Category can't have superclass"), (IntentionAction)new OCClearElementIntentionAction((PsiElement)ocClassDeclaration.getSuperClassRef(), "Remove superclass reference"));
                                    try {
                                        if (instanceVariablesList.isEmpty()) {
                                            break Label_0143;
                                        }
                                        final boolean b = OCCompilerFeatures.supportsIvarsInCategories();
                                        if (b) {
                                            break Label_0100;
                                        }
                                        break Label_0100;
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw b(ex2);
                                    }
                                }
                                try {
                                    final boolean b = OCCompilerFeatures.supportsIvarsInCategories();
                                    if (b) {
                                        if (category.isEmpty()) {
                                            break Label_0143;
                                        }
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw b(ex3);
                                }
                            }
                            this.registerQuickFix(this.addErrorAnnotation((PsiElement)instanceVariablesList, OCInspections.ConstructionIsNotAllowed.class, "err_misplaced_ivar", "Category can't have instance variables"), (IntentionAction)new OCClearElementIntentionAction((PsiElement)instanceVariablesList, "Remove instance variable list"));
                            try {
                                if (!(ocClassDeclaration instanceof OCProtocol)) {
                                    break Label_0311;
                                }
                                if (ocClassDeclaration.getSuperClassRef().isEmpty()) {
                                    break Label_0210;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                        }
                        this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocClassDeclaration.getSuperClassRef(), OCInspections.ConstructionIsNotAllowed.class, "err_expected_unqualified_id", "Protocol can't have superclass"), (IntentionAction)new OCClearElementIntentionAction((PsiElement)ocClassDeclaration.getSuperClassRef(), "Remove superclass reference"));
                        try {
                            if (instanceVariablesList.isEmpty() || instanceVariablesList.getDeclarations().size() <= 0) {
                                break Label_0283;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                    }
                    this.registerQuickFix(this.addErrorAnnotation((PsiElement)instanceVariablesList.getDeclarations().get(0), OCInspections.ConstructionIsNotAllowed.class, "err_expected_unqualified_id", "Protocol can't have instance variables"), (IntentionAction)new OCClearElementIntentionAction((PsiElement)instanceVariablesList, "Remove instance variable list"));
                    try {
                        if (category != null) {
                            this.addErrorAnnotation(ocClassDeclaration.getNameIdentifier(), OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Protocol can't have category");
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                try {
                    if (!(ocClassDeclaration instanceof OCImplementation) || ocClassDeclaration.getProtocolList().isEmpty()) {
                        break Label_0378;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            this.registerQuickFix(this.addErrorAnnotation((PsiElement)ocClassDeclaration.getProtocolList(), OCInspections.ConstructionIsNotAllowed.class, "err_objc_unexpected_attr", "Implementation can't have protocols"), (IntentionAction)new OCClearElementIntentionAction((PsiElement)ocClassDeclaration.getProtocolList(), "Remove protocols list"));
        }
        for (final OCMethod ocMethod : ocClassDeclaration.getMethods()) {
            if (ocMethod.getBody() != null) {
                Annotation annotation = null;
                if (ocClassDeclaration instanceof OCInterface) {
                    annotation = this.addErrorAnnotation((PsiElement)ocMethod.getBody(), OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Interface can't have method implementations");
                }
                if (ocClassDeclaration instanceof OCProtocol) {
                    annotation = this.addErrorAnnotation((PsiElement)ocMethod.getBody(), OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Protocol can't have method implementations");
                }
                try {
                    if (annotation == null) {
                        continue;
                    }
                    this.registerQuickFix(annotation, (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocMethod.getBody(), "Remove method body"));
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            else {
                try {
                    if (!(ocClassDeclaration instanceof OCImplementation)) {
                        continue;
                    }
                    this.addErrorAnnotation(ocMethod.getNavigationElement(), null, "err_expected_method_body", "Implementation of this method is expected");
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
            }
        }
    }
    
    public void checkSynthesize(final OCSynthesizeProperty p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: iconst_1       
        //     2: anewarray       Ljava/lang/Class;
        //     5: dup            
        //     6: iconst_0       
        //     7: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //     9: aastore        
        //    10: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //    16: astore_2       
        //    17: aload_2        
        //    18: ifnonnull       59
        //    21: aload_0        
        //    22: aload_1        
        //    23: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getParent:()Lcom/intellij/psi/PsiElement;
        //    28: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //    30: ldc             "CIDR"
        //    32: ldc             "'@synthesize'/'@dynamic' is outside of a class implementation"
        //    34: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //    37: astore_3       
        //    38: aload_0        
        //    39: aload_3        
        //    40: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //    43: dup            
        //    44: aload_1        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getParent:()Lcom/intellij/psi/PsiElement;
        //    50: ldc             "Remove '@synthesize'/'@dynamic' statement"
        //    52: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //    55: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //    58: return         
        //    59: aload_2        
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCImplementation.getCategory:()Ljava/lang/String;
        //    65: ifnull          126
        //    68: aload_1        
        //    69: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.isSynthesize:()Z
        //    74: ifeq            126
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: aload_1        
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getParent:()Lcom/intellij/psi/PsiElement;
        //    91: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //    93: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //    96: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getSynthesizeCategoryDecl:()Ljava/lang/String;
        //    99: ldc             "'@synthesize' is not allowed in category's implementation"
        //   101: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   104: astore_3       
        //   105: aload_0        
        //   106: aload_3        
        //   107: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //   110: dup            
        //   111: aload_1        
        //   112: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getParent:()Lcom/intellij/psi/PsiElement;
        //   117: ldc             "Remove '@synthesize' statement"
        //   119: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   122: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   125: return         
        //   126: aload_1        
        //   127: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   132: astore_3       
        //   133: aload_3        
        //   134: ifnonnull       142
        //   137: return         
        //   138: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload_2        
        //   143: invokeinterface com/jetbrains/cidr/lang/psi/OCImplementation.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   148: astore          4
        //   150: aload_3        
        //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   156: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   159: astore          5
        //   161: aload_1        
        //   162: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   167: ifnull          183
        //   170: aload_1        
        //   171: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   176: goto            184
        //   179: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_3        
        //   184: astore          6
        //   186: aload           4
        //   188: ifnull          215
        //   191: aload           6
        //   193: ifnull          215
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload           5
        //   205: ifnonnull       220
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: return         
        //   216: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload           4
        //   222: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   227: astore          4
        //   229: aload_1        
        //   230: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   235: aload           5
        //   237: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   242: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markImportNeeded:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   245: aload           6
        //   247: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   250: dup            
        //   251: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   254: dup            
        //   255: aconst_null    
        //   256: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   259: aload           4
        //   261: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   264: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   267: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   272: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   275: astore          7
        //   277: aload           7
        //   279: ifnonnull       287
        //   282: return         
        //   283: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload           7
        //   289: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
        //   294: ifnull          329
        //   297: aload_1        
        //   298: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.isSynthesize:()Z
        //   303: ifeq            329
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload_0        
        //   314: aload_1        
        //   315: aload           5
        //   317: aload           6
        //   319: invokespecial   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;)V
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: aload           7
        //   331: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
        //   336: ifnonnull       358
        //   339: aload_1        
        //   340: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   345: aload           7
        //   347: aload_1        
        //   348: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.markSymbolAsUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_0        
        //   359: aload_1        
        //   360: aload           5
        //   362: aload_3        
        //   363: invokespecial   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;Lcom/intellij/psi/PsiElement;)V
        //   366: aload_0        
        //   367: aload_1        
        //   368: aload           7
        //   370: aload           6
        //   372: invokespecial   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;Lcom/intellij/psi/PsiElement;)V
        //   375: aload           5
        //   377: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   382: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   385: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   390: astore          8
        //   392: aload           8
        //   394: ifnull          462
        //   397: aload           8
        //   399: invokevirtual   java/lang/String.isEmpty:()Z
        //   402: ifne            462
        //   405: goto            412
        //   408: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   411: athrow         
        //   412: aload_0        
        //   413: aload_3        
        //   414: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ConstructionIsNotAllowed;.class
        //   416: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   419: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getCategoryProperty:()Ljava/lang/String;
        //   422: ldc             "Can't synthesize property declared in category"
        //   424: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   427: astore          9
        //   429: aload           4
        //   431: ifnull          462
        //   434: aload_0        
        //   435: aload           9
        //   437: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction;
        //   440: dup            
        //   441: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   444: aload_1        
        //   445: aload           4
        //   447: aload           5
        //   449: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   452: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload           4
        //   464: ifnull          580
        //   467: aload           7
        //   469: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   474: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   477: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   482: aload           4
        //   484: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   489: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   492: ifne            580
        //   495: goto            502
        //   498: invokestatic    com/jetbrains/cidr/lang/daemon/OCImplementationChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: new             Ljava/lang/StringBuilder;
        //   505: dup            
        //   506: invokespecial   java/lang/StringBuilder.<init>:()V
        //   509: aload           7
        //   511: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: ldc             " must be declared in class '"
        //   521: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   524: aload_2        
        //   525: invokeinterface com/jetbrains/cidr/lang/psi/OCImplementation.getCanonicalName:()Ljava/lang/String;
        //   530: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: ldc             "'"
        //   535: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   538: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   541: astore          9
        //   543: aload_0        
        //   544: aload           6
        //   546: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getInstance:()Lcom/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder;
        //   549: invokevirtual   com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.getIvarInSuperclassUse:()Ljava/lang/String;
        //   552: aload           9
        //   554: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   557: astore          10
        //   559: aload_0        
        //   560: aload           10
        //   562: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction;
        //   565: dup            
        //   566: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   569: aload_1        
        //   570: aload           4
        //   572: aload           7
        //   574: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMoveDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   577: invokevirtual   com/jetbrains/cidr/lang/daemon/OCImplementationChecker.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   580: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  59     77     80     84     Ljava/lang/IllegalArgumentException;
        //  133    138    138    142    Ljava/lang/IllegalArgumentException;
        //  161    179    179    183    Ljava/lang/IllegalArgumentException;
        //  186    196    199    203    Ljava/lang/IllegalArgumentException;
        //  191    208    211    215    Ljava/lang/IllegalArgumentException;
        //  203    216    216    220    Ljava/lang/IllegalArgumentException;
        //  277    283    283    287    Ljava/lang/IllegalArgumentException;
        //  287    306    309    313    Ljava/lang/IllegalArgumentException;
        //  297    322    325    329    Ljava/lang/IllegalArgumentException;
        //  329    351    354    358    Ljava/lang/IllegalArgumentException;
        //  392    405    408    412    Ljava/lang/IllegalArgumentException;
        //  429    455    458    462    Ljava/lang/IllegalArgumentException;
        //  462    495    498    502    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0203:
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
    
    private void a(final OCSynthesizeProperty ocSynthesizeProperty, final OCPropertySymbol ocPropertySymbol, final OCReferenceElement ocReferenceElement) {
        final OCAnnotatorHelper[] annotatorHelpers = OCAnnotator.getAnnotatorHelpers();
        for (int length = annotatorHelpers.length, i = 0; i < length; ++i) {
            annotatorHelpers[i].checkDynamicIVar(this, ocSynthesizeProperty, ocPropertySymbol, ocReferenceElement);
        }
    }
    
    private void a(final OCSynthesizeProperty ocSynthesizeProperty, final OCMemberSymbol ocMemberSymbol, final PsiElement psiElement) {
        final Processor processor = ocSynthesizeSymbol -> {
            final PsiElement locateDefinition = ocSynthesizeSymbol.locateDefinition();
            try {
                if (locateDefinition == null || locateDefinition.getTextOffset() >= n) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            String s = null;
            final OCPropertySymbol associatedProperty = ocSynthesizeSymbol.getAssociatedProperty();
            final OCInstanceVariableSymbol ivarSymbol = ocSynthesizeSymbol.getIvarSymbol();
            String s2 = null;
            Label_0184: {
                Label_0116: {
                    try {
                        if (!(ocMemberSymbol instanceof OCInstanceVariableSymbol) || associatedProperty == null) {
                            break Label_0116;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    s = ocMemberSymbol.getNameWithKindUppercase() + " was already used for synthesizing of " + associatedProperty.getNameWithKindLowercase();
                    s2 = OCClangMessageFinder.getInstance().getDuplicateIvarUse();
                    break Label_0184;
                    try {
                        if (!(ocMemberSymbol instanceof OCPropertySymbol) || ivarSymbol == null) {
                            break Label_0184;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                s = "Accessors of " + ocMemberSymbol.getNameWithKindLowercase() + " were already synthesized with " + ivarSymbol.getNameWithKindLowercase();
                s2 = OCClangMessageFinder.getInstance().getPropertyImplemented();
                try {
                    if (s != null) {
                        this.addErrorAnnotation(psiElement, OCInspections.DuplicateDeclarations.class, s2, s);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return false;
        };
        try {
            if (ocMemberSymbol instanceof OCPropertySymbol) {
                ((OCPropertySymbol)ocMemberSymbol).processSynthesizes((Processor<? super OCSynthesizeSymbol>)processor);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocMemberSymbol instanceof OCInstanceVariableSymbol) {
                ((OCInstanceVariableSymbol)ocMemberSymbol).processSynthesizes((Processor<OCSynthesizeSymbol>)processor);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    public void checkInterfaceDeclaration(final OCInterface ocInterface) {
        this.a((OCClassDeclaration<? extends OCClassSymbol>)ocInterface);
        final OCInterfaceSymbol symbol = ocInterface.getSymbol();
        Label_0037: {
            try {
                if (symbol == null) {
                    return;
                }
                final OCInterfaceSymbol ocInterfaceSymbol = symbol;
                final boolean b = ocInterfaceSymbol.isPredeclaration();
                if (b) {
                    return;
                }
                break Label_0037;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCInterfaceSymbol ocInterfaceSymbol = symbol;
                final boolean b = ocInterfaceSymbol.isPredeclaration();
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (this.a(ocInterface)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        this.a(ocInterface, symbol, symbol.getResolvedType());
        final OCImplementationSymbol implementation = symbol.getImplementation();
        Label_0203: {
            Label_0121: {
                try {
                    if (implementation == null) {
                        break Label_0121;
                    }
                    if ("CoreDataGeneratedAccessors".equals(symbol.getCategoryName())) {
                        break Label_0203;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                symbol.processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)new OCCheckImplementedMethodsProcessor(this, implementation, null, false));
                break Label_0203;
                try {
                    if (!OCCodeInsightUtil.isValid((PsiElement)ocInterface) || ocInterface.getCategory() != null) {
                        break Label_0203;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            this.registerQuickFix(this.addWarningAnnotation(ocInterface.getNameIdentifier(), OCInspections.InterfaceHasNoImplementation.class, "CIDR", "Interface '" + ocInterface.getCanonicalName() + "' doesn't have an implementation"), (IntentionAction)new OCImplementInterfaceIntentionAction(symbol));
        }
        if ("".equals(ocInterface.getCategory())) {
            final OCInterfaceSymbol mainInterface = symbol.getMainInterface();
            Label_0256: {
                try {
                    if (mainInterface == null) {
                        return;
                    }
                    final OCInterfaceSymbol ocInterfaceSymbol2 = symbol;
                    final VirtualFile virtualFile = ocInterfaceSymbol2.getContainingFile();
                    final OCInterfaceSymbol ocInterfaceSymbol3 = mainInterface;
                    final VirtualFile virtualFile2 = ocInterfaceSymbol3.getContainingFile();
                    final boolean b2 = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
                    if (b2) {
                        break Label_0256;
                    }
                    return;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final OCInterfaceSymbol ocInterfaceSymbol2 = symbol;
                    final VirtualFile virtualFile = ocInterfaceSymbol2.getContainingFile();
                    final OCInterfaceSymbol ocInterfaceSymbol3 = mainInterface;
                    final VirtualFile virtualFile2 = ocInterfaceSymbol3.getContainingFile();
                    final boolean b2 = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
                    if (b2) {
                        this.addWarningAnnotation(ocInterface.getNameIdentifier(), OCInspections.PrivateCategoryShouldBeNearImplementation.class, "CIDR", "Private category should not be declared in the same file with the public interface");
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
        }
    }
    
    private boolean a(final OCInterface ocInterface) {
        OCInterfaceSymbol symbol = ocInterface.getSymbol();
        final HashSet<OCInterfaceSymbol> set = new HashSet<OCInterfaceSymbol>();
        while (true) {
            Label_0036: {
                try {
                    if (symbol == null) {
                        break;
                    }
                    final HashSet<OCInterfaceSymbol> set2 = set;
                    final OCInterfaceSymbol ocInterfaceSymbol = symbol;
                    final boolean b = set2.add(ocInterfaceSymbol);
                    if (!b) {
                        break Label_0036;
                    }
                    break Label_0036;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final HashSet<OCInterfaceSymbol> set2 = set;
                    final OCInterfaceSymbol ocInterfaceSymbol = symbol;
                    final boolean b = set2.add(ocInterfaceSymbol);
                    if (!b) {
                        this.addErrorAnnotation(ocInterface.getNameIdentifier(), "err_recursive_superclass", "There's a loop in inheritance hierarchy");
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            final OCType resolve = symbol.getSuperType().resolve((PsiFile)ocInterface.getContainingOCFile());
            OCInterfaceSymbol interface1 = null;
            Label_0102: {
                try {
                    if (resolve instanceof OCObjectType) {
                        interface1 = ((OCObjectType)resolve).getInterface();
                        break Label_0102;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                interface1 = null;
            }
            symbol = interface1;
        }
        return false;
    }
    
    public void checkInterfaceImplementation(final OCImplementation ocImplementation) {
        final OCImplementationSymbol symbol = ocImplementation.getSymbol();
        try {
            if (symbol == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCInterfaceSymbol interface1 = symbol.getInterface();
        this.a(ocImplementation);
        final OCType resolve = symbol.getType().resolve(ocImplementation.getContainingFile());
        Label_0180: {
            try {
                this.a(ocImplementation, symbol, resolve);
                if (interface1 != null) {
                    break Label_0180;
                }
                if (ocImplementation.getCategory() != null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final Annotation addWarningAnnotation = this.addWarningAnnotation(ocImplementation.getNameIdentifier(), OCInspections.ImplementationHasNoInterface.class, "err_undef_interface", "Implementation of '" + ocImplementation.getCanonicalName() + "' doesn't have an interface declaration");
            final OCInterfaceSymbol interface2 = symbol.getInterface(true, symbol.getCategoryName());
            try {
                if (interface2 != null) {
                    this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCImportSymbolFix(ocImplementation.getNameIdentifier(), interface2));
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            this.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCCreateInterfaceIntentionAction(symbol, ocImplementation.getNameIdentifier()));
            return;
            try {
                if ("".equals(ocImplementation.getCategory())) {
                    this.addErrorAnnotation(ocImplementation.getNameIdentifier(), OCInspections.ConstructionIsNotAllowed.class, "err_expected_unqualified_id", "Private category can't have an implementation");
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        final OCInterface ocInterface = ((OCSymbol<OCInterface>)interface1).locateDefinition();
        try {
            OCFileSymbols.markImportNeeded(ocImplementation.getContainingOCFile(), interface1);
            if (ocInterface != null) {
                this.a(ocImplementation, ocInterface);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!(resolve instanceof OCObjectType) || !Comparing.equal(interface1.getCategoryName(), symbol.getCategoryName())) {
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        final OCObjectType ocObjectType = (OCObjectType)resolve;
        final OCCheckImplementedMethodsProcessor ocCheckImplementedMethodsProcessor = new OCCheckImplementedMethodsProcessor(this, symbol, ((OCObjectType)resolve).getSuperType(), true);
        try {
            ocObjectType.processInterfaceMethods(interface1, null, (Processor<OCMethodSymbol>)ocCheckImplementedMethodsProcessor, (PsiElement)ocImplementation, false);
            if (ocCheckImplementedMethodsProcessor.wasUnimplementedProperty() || ocCheckImplementedMethodsProcessor.getUnimplementedMethods().length() <= 0) {
                return;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        this.registerQuickFix(this.addWarningAnnotation(ocImplementation.getNameIdentifier(), OCInspections.NotImplementedMethods.class, OCClangMessageFinder.getInstance().getMethodNotImplemented(), "Class doesn't implement method(s) " + ocCheckImplementedMethodsProcessor.getUnimplementedMethods() + " declared in the " + ocCheckImplementedMethodsProcessor.getDeclaredInClasses()), (IntentionAction)new OCImplementAllMethodsIntentionAction(symbol));
    }
    
    private void a(final OCClassDeclaration ocClassDeclaration, final OCClassSymbol ocClassSymbol, final OCType ocType) {
        Label_0023: {
            try {
                if (ocClassSymbol.getCategoryName() == null) {
                    return;
                }
                final OCType ocType2 = ocType;
                final boolean b = ocType2 instanceof OCObjectType;
                if (b) {
                    break Label_0023;
                }
                break Label_0023;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType2 = ocType;
                final boolean b = ocType2 instanceof OCObjectType;
                if (b) {
                    OCFileSymbols.markImportNeeded(ocClassDeclaration.getContainingOCFile(), ((OCObjectType)ocType).getInterface());
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        this.registerQuickFix(this.addErrorAnnotation(ocClassDeclaration.getNameIdentifier(), OCInspections.CannotResolve.class, "CIDR", "Can't resolve interface '" + ocClassSymbol.getName() + "'"), (IntentionAction)new OCImportSymbolFix(ocClassDeclaration.getNameIdentifier(), OCSymbolBase.findSymbolDefinition(ocClassSymbol.getName(), OCSymbolKind.INTERFACE, ocClassDeclaration.getProject(), ocClassSymbol.getContainingFile(), (com.intellij.openapi.util.Condition<OCInterfaceSymbol>)(ocInterfaceSymbol -> {
            try {
                if (ocInterfaceSymbol.getCategoryName() == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        })), true, false));
    }
    
    public void checkProtocolDeclaration(final OCProtocol ocProtocol) {
        this.a(ocProtocol);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
