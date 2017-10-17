// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.Collections;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import javax.swing.JComponent;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;

public class OCGenerateInitWithHandler extends OCObjCClassTextActionHandlerBase<OCInstanceVariableSymbol, OCGenerateMethodActionContext>
{
    private static final OCBooleanOption RETAIN_OBJECTS;
    private static final OCBooleanOption USE_SETTERS;
    private static final OCBooleanOption GENERATE_CLASS_CONSTRUCTOR;
    
    @Override
    protected String getActionTitle() {
        return "Generate -initWith";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Members to be Initialized";
    }
    
    protected boolean defaultRetainObjects(@Nullable final OCCodeStyleSettings ocCodeStyleSettings) {
        Label_0018: {
            try {
                if (ocCodeStyleSettings == null) {
                    break Label_0018;
                }
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR;
                if (b) {
                    break Label_0018;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    protected boolean defaultUseSetters(@Nullable final OCCodeStyleSettings ocCodeStyleSettings) {
        Label_0018: {
            try {
                if (ocCodeStyleSettings == null) {
                    return false;
                }
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.USE_SETTERS_IN_CONSTRUCTOR;
                if (b) {
                    break Label_0018;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.USE_SETTERS_IN_CONSTRUCTOR;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    protected boolean defaultGenerateClassConstructor(@Nullable final OCCodeStyleSettings ocCodeStyleSettings) {
        Label_0018: {
            try {
                if (ocCodeStyleSettings == null) {
                    return false;
                }
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.GENERATE_CLASS_CONSTRUCTOR;
                if (b) {
                    break Label_0018;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.GENERATE_CLASS_CONSTRUCTOR;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (OCCompilerFeatures.isArcDisabled(psiFile)) {
                list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateInitWithHandler.RETAIN_OBJECTS, (Object)this.defaultRetainObjects(ocCodeStyleSettings)));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateInitWithHandler.USE_SETTERS, (Object)this.defaultUseSetters(ocCodeStyleSettings)));
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateInitWithHandler.GENERATE_CLASS_CONSTRUCTOR, (Object)this.defaultGenerateClassConstructor(ocCodeStyleSettings)));
        super.loadOptions(psiFile, editor, (C)ocGenerateMethodActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (OCCompilerFeatures.isArcDisabled(psiFile)) {
                ocCodeStyleSettings.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateInitWithHandler.RETAIN_OBJECTS);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        ocCodeStyleSettings.USE_SETTERS_IN_CONSTRUCTOR = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateInitWithHandler.USE_SETTERS);
        ocCodeStyleSettings.GENERATE_CLASS_CONSTRUCTOR = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateInitWithHandler.GENERATE_CLASS_CONSTRUCTOR);
        super.saveOptions(psiFile, ocCodeStyleSettings, map);
    }
    
    private static boolean a(final PsiFile psiFile) {
        return OCCompilerFeatures.supportsInstancetype(psiFile);
    }
    
    @NotNull
    @Override
    protected OCGenerateMethodActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        final OCObjectType resolvedType = ocClassSymbol.getResolvedType(true);
        try {
            if (resolvedType != null) {
                resolvedType.processMembers("init", OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        OCGenerateMethodActionContext ocGenerateMethodActionContext;
        try {
            ocGenerateMethodActionContext = new OCGenerateMethodActionContext(ocClassSymbol, Collections.singletonList(findFirstProcessor.getFoundValue()), resolvedType, psiElement);
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return ocGenerateMethodActionContext;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @NotNull final List<OCInstanceVariableSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivars", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        if (!list.isEmpty()) {
            final OCClassSymbol methodParent = ((OCSymbolWithParent<T, OCClassSymbol>)list.get(0)).getParent();
            final StringBuilder sb = new StringBuilder();
            final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(OCElementFactory.methodFromSignature(a(ocGenerateMethodActionContext.getBaseMethod(), list, ((OCClassActionHandlerBase<P, M, C>)this).getOption((C)ocGenerateMethodActionContext, (OCOption<Boolean, JComponent>)OCGenerateInitWithHandler.USE_SETTERS), psiFile), (PsiElement)psiFile, false, false), (PsiElement)methodParent.getContainingOCFile());
            String string = null;
            Label_0355: {
                try {
                    handler.setChangeUsages(false);
                    handler.getGeneratedInfo().setMethodParent(methodParent);
                    handler.getGeneratedInfo().runOnSuccess(() -> sb.append(handler.getNewSignature()));
                    handler.setTitle(this.getActionTitle());
                    handler.setRefactorButtonText("Generate");
                    handler.invoke();
                    if (sb.length() > 0) {
                        string = sb.toString();
                        break Label_0355;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
                string = null;
            }
            ocGenerateMethodActionContext.setMethodSignature(string);
            ocGenerateMethodActionContext.setParameters(handler.getParameters());
        }
        super.performAction(project, editor, psiFile, ocGenerateMethodActionContext, list);
    }
    
    protected boolean allowEmptySelection(final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        return true;
    }
    
    private static String a(final OCMethodSymbol p0, final List<OCInstanceVariableSymbol> p1, final boolean p2, final PsiFile p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore          4
        //     9: aload           4
        //    11: aload_0        
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //    17: ifeq            29
        //    20: bipush          43
        //    22: goto            31
        //    25: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: bipush          45
        //    31: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    34: pop            
        //    35: aload_3        
        //    36: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.a:(Lcom/intellij/psi/PsiFile;)Z
        //    39: ifeq            57
        //    42: aload           4
        //    44: ldc             "(instancetype)"
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: pop            
        //    50: goto            83
        //    53: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload           4
        //    59: bipush          40
        //    61: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    64: aload_0        
        //    65: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    70: aload_3        
        //    71: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    77: bipush          41
        //    79: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    82: pop            
        //    83: iconst_1       
        //    84: istore          5
        //    86: aload_1        
        //    87: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    92: astore          6
        //    94: aload           6
        //    96: invokeinterface java/util/Iterator.hasNext:()Z
        //   101: ifeq            292
        //   104: aload           6
        //   106: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   111: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   114: astore          7
        //   116: aload           7
        //   118: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   123: astore          8
        //   125: aload           8
        //   127: ifnull          156
        //   130: aload           8
        //   132: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   137: ifeq            156
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload           8
        //   149: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedPropertyInPrivateCategory:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   154: astore          8
        //   156: iload           5
        //   158: ifeq            188
        //   161: aload           4
        //   163: ldc             "initWith"
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload           7
        //   170: iconst_1       
        //   171: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getNonCollidingName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Ljava/lang/String;
        //   174: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: pop            
        //   181: goto            205
        //   184: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload           4
        //   190: bipush          32
        //   192: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   195: aload           7
        //   197: iconst_1       
        //   198: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getNonCollidingName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Ljava/lang/String;
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: pop            
        //   205: iconst_0       
        //   206: istore          5
        //   208: iload_2        
        //   209: ifeq            249
        //   212: aload           8
        //   214: ifnull          249
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload           8
        //   226: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   231: ifne            249
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: iconst_1       
        //   242: goto            250
        //   245: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: iconst_0       
        //   250: istore          9
        //   252: aload           4
        //   254: ldc             ":("
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload           7
        //   261: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   266: aload_3        
        //   267: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: bipush          41
        //   275: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   278: aload           7
        //   280: iload           9
        //   282: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getNonCollidingName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)Ljava/lang/String;
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: pop            
        //   289: goto            94
        //   292: aload           4
        //   294: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   297: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;ZLcom/intellij/psi/PsiFile;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  9      25     25     29     Ljava/lang/IllegalArgumentException;
        //  31     53     53     57     Ljava/lang/IllegalArgumentException;
        //  125    140    143    147    Ljava/lang/IllegalArgumentException;
        //  156    184    184    188    Ljava/lang/IllegalArgumentException;
        //  208    217    220    224    Ljava/lang/IllegalArgumentException;
        //  212    234    237    241    Ljava/lang/IllegalArgumentException;
        //  224    245    245    249    Ljava/lang/IllegalArgumentException;
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
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement p0, @Nullable final PsiElement p1, @NotNull final List<OCInstanceVariableSymbol> p2, @NotNull final OCGenerateMethodActionContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getInsertText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "ivars"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getInsertText"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "getInsertText"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           4
        //   135: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getMethodSignature:()Ljava/lang/String;
        //   138: astore          5
        //   140: aload_3        
        //   141: invokeinterface java/util/List.isEmpty:()Z
        //   146: ifeq            260
        //   149: aload_1        
        //   150: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   153: ifeq            219
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload           4
        //   165: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getBaseMethod:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   168: aconst_null    
        //   169: aload_1        
        //   170: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.methodText:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   173: dup            
        //   174: ifnonnull       218
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: new             Ljava/lang/IllegalStateException;
        //   187: dup            
        //   188: ldc             "@NotNull method %s.%s must not return null"
        //   190: ldc             2
        //   192: anewarray       Ljava/lang/Object;
        //   195: dup            
        //   196: ldc             0
        //   198: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   200: aastore        
        //   201: dup            
        //   202: ldc             1
        //   204: ldc             "getInsertText"
        //   206: aastore        
        //   207: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   210: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   213: athrow         
        //   214: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: areturn        
        //   219: ldc             ""
        //   221: dup            
        //   222: ifnonnull       259
        //   225: new             Ljava/lang/IllegalStateException;
        //   228: dup            
        //   229: ldc             "@NotNull method %s.%s must not return null"
        //   231: ldc             2
        //   233: anewarray       Ljava/lang/Object;
        //   236: dup            
        //   237: ldc             0
        //   239: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   241: aastore        
        //   242: dup            
        //   243: ldc             1
        //   245: ldc             "getInsertText"
        //   247: aastore        
        //   248: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   251: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   254: athrow         
        //   255: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: areturn        
        //   260: aload           5
        //   262: ifnull          621
        //   265: aload_0        
        //   266: aload           4
        //   268: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.USE_SETTERS:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   271: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   274: checkcast       Ljava/lang/Boolean;
        //   277: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   280: istore          6
        //   282: aload_1        
        //   283: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   288: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   291: ifeq            327
        //   294: aload_0        
        //   295: aload           4
        //   297: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.RETAIN_OBJECTS:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   300: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   303: checkcast       Ljava/lang/Boolean;
        //   306: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   309: ifeq            327
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: iconst_1       
        //   320: goto            328
        //   323: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: iconst_0       
        //   328: istore          7
        //   330: aload_0        
        //   331: aload           4
        //   333: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.GENERATE_CLASS_CONSTRUCTOR:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   336: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   339: checkcast       Ljava/lang/Boolean;
        //   342: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   345: istore          8
        //   347: aload           5
        //   349: iconst_1       
        //   350: aload           4
        //   352: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   355: aload_1        
        //   356: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   361: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.getObjectMethodSignature:(Ljava/lang/String;ZLcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/intellij/psi/PsiFile;)Ljava/lang/String;
        //   364: astore          9
        //   366: aload_1        
        //   367: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   370: ifeq            518
        //   373: aload_1        
        //   374: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   379: astore          10
        //   381: aload_3        
        //   382: aload           4
        //   384: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getParameters:()Ljava/util/List;
        //   387: iconst_0       
        //   388: aload           10
        //   390: iload           6
        //   392: iload           7
        //   394: aload           4
        //   396: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getNonReleasedIvars:()Ljava/util/List;
        //   399: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.getInitializerText:(Ljava/util/List;Ljava/util/List;ILcom/intellij/psi/PsiFile;ZZLjava/util/List;)Ljava/lang/String;
        //   402: astore          11
        //   404: new             Ljava/lang/StringBuilder;
        //   407: dup            
        //   408: invokespecial   java/lang/StringBuilder.<init>:()V
        //   411: astore          12
        //   413: aload           12
        //   415: aload           4
        //   417: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext.getBaseMethod:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   420: aload           5
        //   422: aload           11
        //   424: aload_1        
        //   425: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.methodWithSignature:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   428: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   431: pop            
        //   432: iload           8
        //   434: ifeq            474
        //   437: aload           9
        //   439: ifnull          474
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_1        
        //   450: checkcast       Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   453: aload           4
        //   455: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCImplementation;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext;)Ljava/lang/String;
        //   458: astore          13
        //   460: aload           12
        //   462: aload           9
        //   464: aload           13
        //   466: aload_1        
        //   467: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.methodText:(Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   470: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: pop            
        //   474: aload           12
        //   476: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   479: dup            
        //   480: ifnonnull       517
        //   483: new             Ljava/lang/IllegalStateException;
        //   486: dup            
        //   487: ldc             "@NotNull method %s.%s must not return null"
        //   489: ldc             2
        //   491: anewarray       Ljava/lang/Object;
        //   494: dup            
        //   495: ldc             0
        //   497: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   499: aastore        
        //   500: dup            
        //   501: ldc             1
        //   503: ldc             "getInsertText"
        //   505: aastore        
        //   506: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   509: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   512: athrow         
        //   513: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   516: athrow         
        //   517: areturn        
        //   518: new             Ljava/lang/StringBuilder;
        //   521: dup            
        //   522: invokespecial   java/lang/StringBuilder.<init>:()V
        //   525: astore          10
        //   527: aload           10
        //   529: aload           5
        //   531: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: ldc             ";\n"
        //   536: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   539: pop            
        //   540: iload           8
        //   542: ifeq            577
        //   545: aload           9
        //   547: ifnull          577
        //   550: goto            557
        //   553: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   556: athrow         
        //   557: aload           10
        //   559: aload           9
        //   561: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   564: ldc             ";\n"
        //   566: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   569: pop            
        //   570: goto            577
        //   573: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   576: athrow         
        //   577: aload           10
        //   579: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   582: dup            
        //   583: ifnonnull       620
        //   586: new             Ljava/lang/IllegalStateException;
        //   589: dup            
        //   590: ldc             "@NotNull method %s.%s must not return null"
        //   592: ldc             2
        //   594: anewarray       Ljava/lang/Object;
        //   597: dup            
        //   598: ldc             0
        //   600: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   602: aastore        
        //   603: dup            
        //   604: ldc             1
        //   606: ldc             "getInsertText"
        //   608: aastore        
        //   609: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   612: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   615: athrow         
        //   616: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   619: athrow         
        //   620: areturn        
        //   621: ldc             ""
        //   623: dup            
        //   624: ifnonnull       661
        //   627: new             Ljava/lang/IllegalStateException;
        //   630: dup            
        //   631: ldc             "@NotNull method %s.%s must not return null"
        //   633: ldc             2
        //   635: anewarray       Ljava/lang/Object;
        //   638: dup            
        //   639: ldc             0
        //   641: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //   643: aastore        
        //   644: dup            
        //   645: ldc             1
        //   647: ldc             "getInsertText"
        //   649: aastore        
        //   650: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   653: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   656: athrow         
        //   657: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   660: athrow         
        //   661: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateMethodActionContext;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  140    156    159    163    Ljava/lang/IllegalArgumentException;
        //  149    177    180    184    Ljava/lang/IllegalArgumentException;
        //  163    214    214    218    Ljava/lang/IllegalArgumentException;
        //  219    255    255    259    Ljava/lang/IllegalArgumentException;
        //  282    312    315    319    Ljava/lang/IllegalArgumentException;
        //  294    323    323    327    Ljava/lang/IllegalArgumentException;
        //  413    442    445    449    Ljava/lang/IllegalArgumentException;
        //  474    513    513    517    Ljava/lang/IllegalArgumentException;
        //  527    550    553    557    Ljava/lang/IllegalArgumentException;
        //  545    570    573    577    Ljava/lang/IllegalArgumentException;
        //  577    616    616    620    Ljava/lang/IllegalArgumentException;
        //  621    657    657    661    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    public static String getObjectMethodSignature(final String s, final boolean b, final OCObjectType ocObjectType, final PsiFile psiFile) {
        final int index = s.indexOf("initWith");
        int index2 = 0;
        Label_0035: {
            try {
                if (index != -1) {
                    index2 = index + "initWith".length();
                    break Label_0035;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            index2 = s.indexOf(58);
        }
        final int n = index2;
        final Collection<String> suggestForType = OCNameSuggester.suggestForType(ocObjectType, null, "");
        String s2 = null;
        Label_0080: {
            try {
                if (suggestForType.isEmpty()) {
                    s2 = "object";
                    break Label_0080;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            s2 = suggestForType.iterator().next();
        }
        final String s3 = s2;
        String s4 = null;
        Label_0116: {
            Label_0114: {
                Label_0100: {
                    try {
                        if (!b) {
                            break Label_0114;
                        }
                        final PsiFile psiFile2 = psiFile;
                        final boolean b2 = a(psiFile2);
                        if (b2) {
                            break Label_0100;
                        }
                        break Label_0100;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                    try {
                        final PsiFile psiFile2 = psiFile;
                        final boolean b2 = a(psiFile2);
                        if (b2) {
                            s4 = "+(instancetype)";
                            break Label_0116;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw c(ex4);
                    }
                }
                s4 = "+(id)";
                break Label_0116;
            }
            s4 = "";
        }
        final String s5 = s4;
        try {
            if (n != -1) {
                return s5 + s3 + "With" + s.substring(n);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        return null;
    }
    
    private static String a(final OCImplementation ocImplementation, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        final StringBuilder sb = new StringBuilder("return ");
        try {
            if (OCCompilerFeatures.isArcDisabled(ocImplementation.getContainingFile())) {
                sb.append("[");
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        sb.append("[[self alloc] ");
        for (final OCParameterInfo ocParameterInfo : ocGenerateMethodActionContext.getParameters()) {
            sb.append(ocParameterInfo.getSelector()).append(":").append(ocParameterInfo.getName()).append(' ');
        }
        try {
            if (OCCompilerFeatures.isArcDisabled(ocImplementation.getContainingFile())) {
                sb.append("] autorelease");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        sb.append("];");
        return sb.toString();
    }
    
    public static String getInitializerText(final List<OCInstanceVariableSymbol> p0, final List<OCParameterInfo> p1, final int p2, @NotNull final PsiFile p3, final boolean p4, final boolean p5, final List<OCInstanceVariableSymbol> p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "implementationFile"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getInitializerText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore          7
        //    53: aload_3        
        //    54: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.startInspection:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //    57: astore          8
        //    59: aload_1        
        //    60: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    65: astore          9
        //    67: aload           9
        //    69: invokeinterface java/util/Iterator.hasNext:()Z
        //    74: ifeq            572
        //    77: aload           9
        //    79: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    84: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    87: astore          10
        //    89: aload           10
        //    91: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //    94: iload_2        
        //    95: isub           
        //    96: istore          11
        //    98: iload           11
        //   100: iflt            67
        //   103: iload           11
        //   105: aload_0        
        //   106: invokeinterface java/util/List.size:()I
        //   111: if_icmplt       121
        //   114: goto            67
        //   117: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_0        
        //   122: iload           11
        //   124: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   129: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   132: astore          12
        //   134: aload           12
        //   136: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   141: astore          13
        //   143: aload           12
        //   145: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   150: astore          14
        //   152: aload           13
        //   154: ifnull          201
        //   157: aload           13
        //   159: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   164: ifeq            201
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload           13
        //   176: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedPropertyInPrivateCategory:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   181: astore          15
        //   183: aload           15
        //   185: ifnull          197
        //   188: aload           15
        //   190: goto            199
        //   193: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload           13
        //   199: astore          13
        //   201: aload           7
        //   203: invokevirtual   java/lang/StringBuilder.length:()I
        //   206: ifle            224
        //   209: aload           7
        //   211: ldc             "\n"
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: pop            
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: iload           4
        //   226: ifeq            304
        //   229: aload           13
        //   231: ifnull          304
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: aload           13
        //   243: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   248: ifne            304
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aload           7
        //   260: ldc             "self."
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: aload           13
        //   267: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getName:()Ljava/lang/String;
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: bipush          61
        //   277: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   280: pop            
        //   281: aload           7
        //   283: aload           10
        //   285: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: ldc             ";"
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: pop            
        //   297: goto            569
        //   300: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: iload           5
        //   306: ifeq            464
        //   309: aload           14
        //   311: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   314: ifeq            464
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: aload_3        
        //   325: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   328: ifeq            464
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload           7
        //   340: aload           12
        //   342: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   347: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: bipush          61
        //   352: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   355: pop            
        //   356: aload           7
        //   358: ldc             "["
        //   360: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: aload           10
        //   365: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   368: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: pop            
        //   372: aload           14
        //   374: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToString:()Z
        //   377: ifeq            402
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aload           7
        //   389: ldc             " copy];"
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   394: pop            
        //   395: goto            410
        //   398: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: aload           7
        //   404: ldc             " retain];"
        //   406: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   409: pop            
        //   410: aload_3        
        //   411: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   414: ifeq            569
        //   417: aload           8
        //   419: ifnull          569
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: aload           12
        //   431: aload_3        
        //   432: aload           8
        //   434: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.isIvarReleased:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Z
        //   437: ifne            569
        //   440: goto            447
        //   443: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: aload           6
        //   449: aload           12
        //   451: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   456: pop            
        //   457: goto            569
        //   460: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: aload           13
        //   466: ifnull          535
        //   469: aload           13
        //   471: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   474: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   479: ifeq            535
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload           7
        //   491: aload           12
        //   493: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: bipush          61
        //   503: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   506: pop            
        //   507: aload           7
        //   509: ldc             "["
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: aload           10
        //   516: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   519: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: ldc             " copy];"
        //   524: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   527: pop            
        //   528: goto            569
        //   531: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   534: athrow         
        //   535: aload           7
        //   537: aload           12
        //   539: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   544: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   547: bipush          61
        //   549: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   552: pop            
        //   553: aload           7
        //   555: aload           10
        //   557: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   560: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   563: ldc             ";"
        //   565: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   568: pop            
        //   569: goto            67
        //   572: aload           7
        //   574: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   577: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;>;ILcom/intellij/psi/PsiFile;ZZLjava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  103    117    117    121    Ljava/lang/IllegalArgumentException;
        //  152    167    170    174    Ljava/lang/IllegalArgumentException;
        //  183    193    193    197    Ljava/lang/IllegalArgumentException;
        //  201    217    220    224    Ljava/lang/IllegalArgumentException;
        //  224    234    237    241    Ljava/lang/IllegalArgumentException;
        //  229    251    254    258    Ljava/lang/IllegalArgumentException;
        //  241    300    300    304    Ljava/lang/IllegalArgumentException;
        //  304    317    320    324    Ljava/lang/IllegalArgumentException;
        //  309    331    334    338    Ljava/lang/IllegalArgumentException;
        //  324    380    383    387    Ljava/lang/IllegalArgumentException;
        //  338    398    398    402    Ljava/lang/IllegalArgumentException;
        //  410    422    425    429    Ljava/lang/IllegalArgumentException;
        //  417    440    443    447    Ljava/lang/IllegalArgumentException;
        //  429    460    460    464    Ljava/lang/IllegalArgumentException;
        //  464    482    485    489    Ljava/lang/IllegalArgumentException;
        //  469    531    531    535    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0241:
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
    protected void performAction(@NotNull final Project project, @NotNull final PsiElement psiElement, final int n, final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        Label_0218: {
            try {
                super.performAction(project, psiElement, n, psiElement2, list, ocGenerateMethodActionContext);
                if (!(psiElement instanceof OCImplementation)) {
                    break Label_0218;
                }
                final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                final List<OCInstanceVariableSymbol> list2 = ocGenerateMethodActionContext2.getNonReleasedIvars();
                final boolean b = list2.isEmpty();
                if (!b) {
                    break Label_0218;
                }
                break Label_0218;
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            try {
                final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                final List<OCInstanceVariableSymbol> list2 = ocGenerateMethodActionContext2.getNonReleasedIvars();
                final boolean b = list2.isEmpty();
                if (!b) {
                    new OCReleaseVariablesIntentionAction(ocGenerateMethodActionContext.getNonReleasedIvars()).invoke(project, null, psiElement.getContainingFile());
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
        }
        if (psiElement instanceof OCImplementation) {
            for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
                try {
                    if (((OCSymbolWithParent<T, OCClassSymbol>)ocInstanceVariableSymbol).getParent().isSameCategory(ocGenerateMethodActionContext.getInterfaceSymbol())) {
                        continue;
                    }
                    new OCImportSymbolFix(psiElement, ((OCSymbolWithParent<T, OCClassSymbol>)ocInstanceVariableSymbol).getParent(), false).fixFirstItem(project, psiElement.getContainingFile());
                }
                catch (IllegalArgumentException ex7) {
                    throw c(ex7);
                }
            }
        }
    }
    
    @Override
    protected boolean shouldSelectResult(@NotNull final OCBlockStatement ocBlockStatement) {
        try {
            if (ocBlockStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateInitWithHandler", "shouldSelectResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return true;
    }
    
    @Override
    protected int getInsertPosition(final PsiElement psiElement, final int n, final PsiElement psiElement2, final List<OCInstanceVariableSymbol> list, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        Label_0025: {
            try {
                if (psiElement2 == null) {
                    break Label_0025;
                }
                final int n2 = n;
                final PsiElement psiElement3 = psiElement;
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement3;
                final boolean b = true;
                final int n3 = ocClassDeclaration.getMethodsStartOffset(b);
                if (n2 < n3) {
                    break Label_0025;
                }
                return ((OCClassDeclaration)psiElement).getMethodsInsertPosition(true, psiElement2, n);
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final int n2 = n;
                final PsiElement psiElement3 = psiElement;
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement3;
                final boolean b = true;
                final int n3 = ocClassDeclaration.getMethodsStartOffset(b);
                if (n2 < n3) {
                    return OCDeclarationKind.InitMethod.getChildrenEndOffset(psiElement);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return ((OCClassDeclaration)psiElement).getMethodsInsertPosition(true, psiElement2, n);
    }
    
    static {
        RETAIN_OBJECTS = new OCBooleanOption("Retain object parameters");
        USE_SETTERS = new OCBooleanOption("Use property setters");
        GENERATE_CLASS_CONSTRUCTOR = new OCBooleanOption("Generate \"+objectWith...\"");
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
