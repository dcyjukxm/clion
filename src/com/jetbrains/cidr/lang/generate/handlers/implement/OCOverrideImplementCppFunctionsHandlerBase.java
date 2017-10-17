// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.OCIcons;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import java.util.Collections;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCCCppGenerateHandlerBase;

public abstract class OCOverrideImplementCppFunctionsHandlerBase extends OCCCppGenerateHandlerBase<OCStructSymbol, OCFunctionSymbol, OCOverrideImplementCppActionContext>
{
    private static final OCBooleanOption INSERT_OVERRIDE;
    
    @Override
    protected String getActionTitle() {
        return OCBundle.message("override.implement.cpp.action.title", new Object[0]);
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Label_0149: {
            List<Pair<OCOption, Object>> list2 = null;
            Pair pair = null;
            OCBooleanOption ocBooleanOption = null;
            boolean b = false;
            Label_0137: {
                Label_0136: {
                    Label_0117: {
                        try {
                            if (!OCCompilerFeatures.supportsOverrideControl(psiFile)) {
                                break Label_0149;
                            }
                            list2 = list;
                            pair = new(com.intellij.openapi.util.Pair.class);
                            ocBooleanOption = OCOverrideImplementCppFunctionsHandlerBase.INSERT_OVERRIDE;
                            final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                            if (ocCodeStyleSettings2 != null) {
                                break Label_0117;
                            }
                            break Label_0117;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        try {
                            list2 = list;
                            pair = new(com.intellij.openapi.util.Pair.class);
                            ocBooleanOption = OCOverrideImplementCppFunctionsHandlerBase.INSERT_OVERRIDE;
                            final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                            if (ocCodeStyleSettings2 != null) {
                                if (!ocCodeStyleSettings.INSERT_OVERRIDE) {
                                    break Label_0136;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                    b = true;
                    break Label_0137;
                }
                b = false;
            }
            new Pair((Object)ocBooleanOption, (Object)b);
            list2.add((Pair<OCOption, Object>)pair);
        }
        super.loadOptions(psiFile, editor, ocOverrideImplementCppActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (map.containsKey(OCOverrideImplementCppFunctionsHandlerBase.INSERT_OVERRIDE)) {
                ocCodeStyleSettings.INSERT_OVERRIDE = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCOverrideImplementCppFunctionsHandlerBase.INSERT_OVERRIDE);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        super.saveOptions(psiFile, ocCodeStyleSettings, map);
    }
    
    @Override
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<OCFunctionSymbol> collection, final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, final int n) {
        return (OCMemberChooserObject[])ContainerUtil.map2Array((Collection)collection, (Class)OCMemberChooserObject.class, ocFunctionSymbol -> {
            boolean b2 = false;
            Label_0037: {
                Label_0028: {
                    try {
                        if (ocFunctionSymbol.isPureVirtual()) {
                            break Label_0028;
                        }
                        final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext2 = ocOverrideImplementCppActionContext;
                        final OCStructSymbol ocStructSymbol = ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext2).getParent();
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                        final boolean b = Comparing.equal((Object)ocStructSymbol, (Object)ocSymbolWithQualifiedName);
                        if (b) {
                            break Label_0028;
                        }
                        break Label_0028;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext2 = ocOverrideImplementCppActionContext;
                        final OCStructSymbol ocStructSymbol = ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext2).getParent();
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol2.getParent();
                        final boolean b = Comparing.equal((Object)ocStructSymbol, (Object)ocSymbolWithQualifiedName);
                        if (b) {
                            b2 = true;
                            break Label_0037;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                b2 = false;
            }
            final boolean b3 = b2;
            boolean friendOrStatic;
            boolean b4;
            try {
                friendOrStatic = ocFunctionSymbol.isFriendOrStatic();
                b4 = b3;
                if (!ocFunctionSymbol.isVirtual()) {
                    final boolean b5 = true;
                    return new OCMemberChooserObject(ocFunctionSymbol, OCIcons.getFunctionIcon(friendOrStatic, b4, b5, ocFunctionSymbol.getVisibility()), map);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            final boolean b5 = false;
            return new OCMemberChooserObject(ocFunctionSymbol, OCIcons.getFunctionIcon(friendOrStatic, b4, b5, ocFunctionSymbol.getVisibility()), map);
        });
    }
    
    @NotNull
    @Override
    protected String getFeatureId() {
        String s;
        try {
            s = "codeassists.overrideimplement";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "getFeatureId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    protected void doPerformAction(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCOverrideImplementCppActionContext ocOverrideImplementCppActionContext, @NotNull final List<OCFunctionSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocOverrideImplementCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "doPerformAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final IllegalArgumentException ex5;
        final IllegalArgumentException ex7;
        final OCVisibility ocVisibility;
        final OCVisibility ocVisibility2;
        final int n;
        final int n2;
        Collections.sort((List<Object>)list, (ocFunctionSymbol, ocFunctionSymbol2) -> {
            try {
                if (ocOverrideImplementCppActionContext == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "lambda$doPerformAction$1"));
                    throw ex5;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            try {
                if (ocCaretLocation == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase", "lambda$doPerformAction$1"));
                    throw ex7;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            a(ocFunctionSymbol, ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext).getParent(), ocCaretLocation.getFile());
            a(ocFunctionSymbol2, ((OCActionContext<OCStructSymbol, M>)ocOverrideImplementCppActionContext).getParent(), ocCaretLocation.getFile());
            if (ocVisibility == ocVisibility2) {
                FileUtil.comparePaths(ocFunctionSymbol.getContainingFile().getPath(), ocFunctionSymbol2.getContainingFile().getPath());
                try {
                    if (n != 0) {
                        return n2;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
                n2 = ocFunctionSymbol.getOffset() - ocFunctionSymbol2.getOffset();
                return n2;
            }
            else {
                return ocVisibility.compareTo(ocVisibility2);
            }
        });
        super.doPerformAction(project, ocCaretLocation, ocOverrideImplementCppActionContext, list);
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final OCOverrideImplementCppActionContext p1, @NotNull final List<OCFunctionSymbol> p2) {
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
        //    18: ldc             "location"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "actionContext"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "functions"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getReplacements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   136: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   142: astore          4
        //   144: aload           4
        //   146: ifnull          164
        //   149: aload           4
        //   151: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   154: ifne            213
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   167: dup            
        //   168: ifnonnull       212
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: new             Ljava/lang/IllegalStateException;
        //   181: dup            
        //   182: ldc             "@NotNull method %s.%s must not return null"
        //   184: ldc             2
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: ldc             0
        //   192: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase"
        //   194: aastore        
        //   195: dup            
        //   196: ldc             1
        //   198: ldc             "getReplacements"
        //   200: aastore        
        //   201: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   204: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   207: athrow         
        //   208: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: areturn        
        //   213: aload           4
        //   215: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   218: astore          5
        //   220: new             Ljava/util/ArrayList;
        //   223: dup            
        //   224: aload_3        
        //   225: invokeinterface java/util/List.size:()I
        //   230: invokespecial   java/util/ArrayList.<init>:(I)V
        //   233: astore          6
        //   235: new             Ljava/util/ArrayList;
        //   238: dup            
        //   239: aload_3        
        //   240: invokeinterface java/util/List.size:()I
        //   245: invokespecial   java/util/ArrayList.<init>:(I)V
        //   248: astore          7
        //   250: aload_1        
        //   251: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getProject:()Lcom/intellij/openapi/project/Project;
        //   254: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   257: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   259: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   262: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   265: astore          8
        //   267: aload_0        
        //   268: aload_2        
        //   269: getstatic       com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.INSERT_OVERRIDE:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   272: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   275: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   278: if_acmpne       289
        //   281: iconst_1       
        //   282: goto            290
        //   285: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: iconst_0       
        //   290: istore          9
        //   292: iload           9
        //   294: ifeq            312
        //   297: aload           8
        //   299: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INSERT_VIRTUAL_WITH_OVERRIDE:Z
        //   302: ifeq            320
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: iconst_1       
        //   313: goto            321
        //   316: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: iconst_0       
        //   321: istore          10
        //   323: aload_3        
        //   324: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   329: astore          11
        //   331: aload           11
        //   333: invokeinterface java/util/Iterator.hasNext:()Z
        //   338: ifeq            441
        //   341: aload           11
        //   343: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   348: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   351: astore          12
        //   353: aload           12
        //   355: aload_2        
        //   356: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   359: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   362: aload           5
        //   364: aload           12
        //   366: aload_2        
        //   367: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   370: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   373: aload           5
        //   375: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   380: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   383: iload           9
        //   385: iload           10
        //   387: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.createOverridingFunction:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;ZZ)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   390: astore          13
        //   392: aload           6
        //   394: aload           13
        //   396: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   401: pop            
        //   402: aload           7
        //   404: aload           13
        //   406: aload           12
        //   408: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPureVirtual:()Z
        //   411: ifne            423
        //   414: aload           12
        //   416: goto            424
        //   419: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   422: athrow         
        //   423: aconst_null    
        //   424: aload           5
        //   426: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.getCorrectContextToCalculateNames:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   429: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.defaultFunctionBody:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   432: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   437: pop            
        //   438: goto            331
        //   441: aload_1        
        //   442: aload           5
        //   444: aload_2        
        //   445: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   448: aload           6
        //   450: aload           7
        //   452: aload_0        
        //   453: aload_2        
        //   454: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.getInlinePolicy:(Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //   457: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getNewFunctionsReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/psi/OCStructLike;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //   460: dup            
        //   461: ifnonnull       498
        //   464: new             Ljava/lang/IllegalStateException;
        //   467: dup            
        //   468: ldc             "@NotNull method %s.%s must not return null"
        //   470: ldc             2
        //   472: anewarray       Ljava/lang/Object;
        //   475: dup            
        //   476: ldc             0
        //   478: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase"
        //   480: aastore        
        //   481: dup            
        //   482: ldc             1
        //   484: ldc             "getReplacements"
        //   486: aastore        
        //   487: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   490: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   493: athrow         
        //   494: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementCppFunctionsHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  144    157    160    164    Ljava/lang/IllegalArgumentException;
        //  149    171    174    178    Ljava/lang/IllegalArgumentException;
        //  164    208    208    212    Ljava/lang/IllegalArgumentException;
        //  267    285    285    289    Ljava/lang/IllegalArgumentException;
        //  292    305    308    312    Ljava/lang/IllegalArgumentException;
        //  297    316    316    320    Ljava/lang/IllegalArgumentException;
        //  392    419    419    423    Ljava/lang/IllegalArgumentException;
        //  441    494    494    498    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
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
    
    private static OCVisibility a(final OCFunctionSymbol ocFunctionSymbol, final OCStructSymbol ocStructSymbol, final PsiFile psiFile) {
        final OCVisibility visibility = ocFunctionSymbol.getVisibility();
        try {
            if (visibility != null) {
                final OCVisibility private1 = visibility;
                return OCVisibility.max(private1, OCVisibility.getMaxInheritanceVisibility((OCStructSymbol)ocFunctionSymbol.getParent(), ocStructSymbol, psiFile));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCVisibility private1 = OCVisibility.PRIVATE;
        return OCVisibility.max(private1, OCVisibility.getMaxInheritanceVisibility((OCStructSymbol)ocFunctionSymbol.getParent(), ocStructSymbol, psiFile));
    }
    
    static {
        INSERT_OVERRIDE = new OCBooleanOption(OCBundle.message("override.implement.cpp.action.insertOverride", new Object[0]));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
