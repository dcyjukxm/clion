// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import java.util.List;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.util.containers.ContainerUtil;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementCppActionContext;
import com.jetbrains.cidr.lang.OCBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;

public class OCOverrideImplementCppFunctionsHandler extends OCOverrideImplementCppFunctionsHandlerBase
{
    private static final OCBooleanOption SHOW_NON_VIRTUAL_FUNCTIONS;
    
    protected boolean defaultShowNonVirtualOption(final PsiFile psiFile, @Nullable final Editor editor) {
        return true;
    }
    
    @Nullable
    @Override
    public String getActionName() {
        return OCBundle.message("override.implement.cpp.action.name", new Object[0]);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return OCBundle.message("override.implement.cpp.action.memberChooserTitle", new Object[0]);
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext) {
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return OCBundle.message("override.implement.cpp.action.noMember", ocOverrideImplementCppActionContext.getParentNameUppercase());
    }
    
    @NotNull
    @Override
    protected String getNoParentsMessage() {
        String message;
        try {
            message = OCBundle.message("override.implement.cpp.action.noParent", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "getNoParentsMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return message;
    }
    
    @Override
    protected OCOverrideImplementCppActionContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCOverrideImplementCppActionContext(ocStructSymbol, psiElement, false);
    }
    
    @Override
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<OCFunctionSymbol> collection, final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, final int n) {
        return super.getChooserNodes(ocMemberChooser, ContainerUtil.filter((Collection)collection, p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //     4: astore_2       
            //     5: aload_0        
            //     6: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //     9: ifeq            37
            //    12: aload_2        
            //    13: ifnull          37
            //    16: goto            23
            //    19: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    22: athrow         
            //    23: aload_2        
            //    24: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isWritable:()Z
            //    27: ifne            51
            //    30: goto            37
            //    33: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    36: athrow         
            //    37: aload_1        
            //    38: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.resolveIsVirtual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Z
            //    41: ifeq            59
            //    44: goto            51
            //    47: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    50: athrow         
            //    51: iconst_1       
            //    52: goto            60
            //    55: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    58: athrow         
            //    59: iconst_0       
            //    60: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  5      16     19     23     Ljava/lang/IllegalArgumentException;
            //  12     30     33     37     Ljava/lang/IllegalArgumentException;
            //  23     44     47     51     Ljava/lang/IllegalArgumentException;
            //  37     55     55     59     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
        }), ocOverrideImplementCppActionContext, n);
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCOverrideImplementCppFunctionsHandler.SHOW_NON_VIRTUAL_FUNCTIONS, (Object)this.defaultShowNonVirtualOption(psiFile, editor)));
        super.loadOptions(psiFile, editor, ocOverrideImplementCppActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<OCFunctionSymbol> collection, final OCOption ocOption, final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext) {
        try {
            if (OCOverrideImplementCppFunctionsHandler.SHOW_NON_VIRTUAL_FUNCTIONS.equals(ocOption)) {
                ocMemberChooser.resetElements(this.getChooserNodes(ocMemberChooser, collection, ocOverrideImplementCppActionContext, 0));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @Override
    protected void doPerformAction(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, @NotNull final List<OCFunctionSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        super.doPerformAction(project, ocCaretLocation, ocOverrideImplementCppActionContext, ContainerUtil.map((Collection)list, ocFunctionSymbol -> {
            try {
                if (ocOverrideImplementCppActionContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler", "lambda$doPerformAction$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            Label_0075: {
                try {
                    if (ocFunctionSymbol.isVirtual()) {
                        return ocFunctionSymbol;
                    }
                    final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext2 = ocOverrideImplementCppActionContext;
                    final OCStructSymbol ocStructSymbol = ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext2).getParent();
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                    final boolean b = ocStructSymbol2.equals(ocSymbolWithQualifiedName);
                    if (!b) {
                        break Label_0075;
                    }
                    return ocFunctionSymbol;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext2 = ocOverrideImplementCppActionContext;
                    final OCStructSymbol ocStructSymbol = ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext2).getParent();
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                    final boolean b = ocStructSymbol2.equals(ocSymbolWithQualifiedName);
                    if (!b) {
                        return new OCFunctionSymbol(ocFunctionSymbol) {
                            @Override
                            public boolean isVirtual() {
                                return true;
                            }
                        };
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            return ocFunctionSymbol;
        }));
        new WriteCommandAction(project, new PsiFile[] { ocCaretLocation.getFile() }) {
            protected void run(@NotNull final Result p0) throws Throwable {
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
                //    18: ldc             "result"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "run"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
                //    43: athrow         
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$chosenCandidates:Ljava/util/List;
                //    48: invokedynamic   compare:()Ljava/util/Comparator;
                //    53: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
                //    56: aload_0        
                //    57: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$chosenCandidates:Ljava/util/List;
                //    60: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
                //    65: astore_2       
                //    66: aload_2        
                //    67: invokeinterface java/util/Iterator.hasNext:()Z
                //    72: ifeq            195
                //    75: aload_2        
                //    76: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                //    81: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    84: astore_3       
                //    85: new             Lcom/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix;
                //    88: dup            
                //    89: aload_3        
                //    90: iconst_0       
                //    91: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)V
                //    94: astore          4
                //    96: aload_0        
                //    97: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
                //   100: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
                //   103: astore          5
                //   105: aload_0        
                //   106: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
                //   109: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getEditorFile:()Lcom/intellij/psi/PsiFile;
                //   112: astore          6
                //   114: aload_0        
                //   115: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$actionContext:Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;
                //   118: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
                //   121: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //   124: aload_3        
                //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                //   128: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.equals:(Ljava/lang/Object;)Z
                //   131: ifne            192
                //   134: aload_3        
                //   135: iconst_0       
                //   136: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.findFirstVirtual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //   139: ifnonnull       192
                //   142: goto            149
                //   145: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
                //   148: athrow         
                //   149: aload           4
                //   151: aload_0        
                //   152: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$project:Lcom/intellij/openapi/project/Project;
                //   155: aload           5
                //   157: aload           6
                //   159: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
                //   162: ifeq            192
                //   165: goto            172
                //   168: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
                //   171: athrow         
                //   172: aload           4
                //   174: aload_0        
                //   175: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.val$project:Lcom/intellij/openapi/project/Project;
                //   178: aload           5
                //   180: aload           6
                //   182: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
                //   185: goto            192
                //   188: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandler$2.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
                //   191: athrow         
                //   192: goto            66
                //   195: return         
                //    Exceptions:
                //  throws java.lang.Throwable
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      40     40     44     Ljava/lang/Throwable;
                //  114    142    145    149    Ljava/lang/Throwable;
                //  134    165    168    172    Ljava/lang/Throwable;
                //  149    185    188    192    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0149:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    static {
        SHOW_NON_VIRTUAL_FUNCTIONS = new OCBooleanOption(OCBundle.message("override.implement.cpp.action.showNonVirtual", new Object[0]));
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
