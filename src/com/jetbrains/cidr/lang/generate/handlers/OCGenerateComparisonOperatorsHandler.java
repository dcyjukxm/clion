// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import java.util.Map;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.util.OCFakeFunctionSymbolBuilder;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.util.EnumSet;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.OCGenerateComparisonOperatorsContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCGenerateComparisonOperatorsHandler extends OCCCppGenerateHandlerBase<OCStructSymbol, OCDeclaratorSymbol, OCGenerateComparisonOperatorsContext>
{
    private static final OCBooleanOption ADDITIONAL_EQ_OPTION;
    private static final OCBooleanOption ADDITIONAL_REL_OPTION;
    private static final OCBooleanOption AS_MEMBER_OPTION;
    private static final OCBooleanOption USE_STD_TIE_OPTION;
    private final EnumSet<Type> myTypes;
    
    public OCGenerateComparisonOperatorsHandler(@NotNull final EnumSet<Type> myTypes) {
        if (myTypes == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "<init>"));
        }
        this.myTypes = myTypes;
    }
    
    @NotNull
    @Override
    protected Collection<OCFunctionSymbol> checkExistingFunctions(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "checkExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "checkExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "checkExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final ArrayList<String> a = this.a(ocGenerateComparisonOperatorsContext);
        final OCStructType type = ((OCActionContext<OCStructSymbol, M>)ocGenerateComparisonOperatorsContext).getParent().getType();
        final List<Pair<String, OCFunctionSymbol>> existingFunctions = OCResolveUtil.findExistingFunctions(project, a, ContainerUtil.list((Object[])new OCType[] { type, type }), ((OCActionContext<OCSymbol, M>)ocGenerateComparisonOperatorsContext).getParent(), new OCResolveContext((PsiElement)((OCActionContext<OCStructSymbol, M>)ocGenerateComparisonOperatorsContext).getParent().getContainingOCFile()));
        List<? super Object> list;
        try {
            ocGenerateComparisonOperatorsContext.setExistingOperators((List<String>)existingFunctions.stream().map(pair -> (String)pair.getFirst()).distinct().collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()));
            ocGenerateComparisonOperatorsContext.setOperatorsToGenerate(a);
            list = existingFunctions.stream().map(pair2 -> (OCFunctionSymbol)pair2.getSecond()).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "checkExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return (Collection<OCFunctionSymbol>)list;
    }
    
    @NotNull
    @Override
    protected String getSomeDefinedText() {
        String message;
        try {
            message = OCBundle.message("generate.comparison.operators.usages.some.defined", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getSomeDefinedText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return message;
    }
    
    @NotNull
    @Override
    protected String getAllDefinedText() {
        String message;
        try {
            message = OCBundle.message("generate.comparison.operators.usages.all.defined", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getAllDefinedText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return message;
    }
    
    @NotNull
    @Override
    protected String getExistingTabName() {
        String message;
        try {
            message = OCBundle.message("generate.comparison.operators.usages.existing.text", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getExistingTabName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return message;
    }
    
    @NotNull
    private ArrayList<String> a(@NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getOperatorsToGenerate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list4 = null;
        Label_0182: {
            Label_0154: {
                Label_0110: {
                    Label_0096: {
                        try {
                            if (!this.myTypes.contains(Type.EQUALITY)) {
                                break Label_0110;
                            }
                            final ArrayList<String> list2 = list;
                            final String s = "operator==";
                            list2.add(s);
                            final OCGenerateComparisonOperatorsHandler ocGenerateComparisonOperatorsHandler = this;
                            final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext2 = ocGenerateComparisonOperatorsContext;
                            final OCBooleanOption ocBooleanOption = OCGenerateComparisonOperatorsHandler.ADDITIONAL_EQ_OPTION;
                            final Boolean b = ((OCClassActionHandlerBase<OCMembersContainer, OCSymbolWithParent, OCGenerateComparisonOperatorsContext>)ocGenerateComparisonOperatorsHandler).getOption(ocGenerateComparisonOperatorsContext2, (OCOption<Boolean, JComponent>)ocBooleanOption);
                            final Boolean b2 = b;
                            final boolean b3 = b2;
                            if (b3) {
                                break Label_0096;
                            }
                            break Label_0110;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final ArrayList<String> list2 = list;
                            final String s = "operator==";
                            list2.add(s);
                            final OCGenerateComparisonOperatorsHandler ocGenerateComparisonOperatorsHandler = this;
                            final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext2 = ocGenerateComparisonOperatorsContext;
                            final OCBooleanOption ocBooleanOption = OCGenerateComparisonOperatorsHandler.ADDITIONAL_EQ_OPTION;
                            final Boolean b = ((OCClassActionHandlerBase<OCMembersContainer, OCSymbolWithParent, OCGenerateComparisonOperatorsContext>)ocGenerateComparisonOperatorsHandler).getOption(ocGenerateComparisonOperatorsContext2, (OCOption<Boolean, JComponent>)ocBooleanOption);
                            final Boolean b2 = b;
                            final boolean b3 = b2;
                            if (b3) {
                                list.add("operator!=");
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    try {
                        if (!this.myTypes.contains(Type.RELATIONAL)) {
                            break Label_0182;
                        }
                        final ArrayList<String> list3 = list;
                        final String s2 = "operator<";
                        list3.add(s2);
                        final OCGenerateComparisonOperatorsHandler ocGenerateComparisonOperatorsHandler2 = this;
                        final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext3 = ocGenerateComparisonOperatorsContext;
                        final OCBooleanOption ocBooleanOption2 = OCGenerateComparisonOperatorsHandler.ADDITIONAL_REL_OPTION;
                        final Boolean b4 = ((OCClassActionHandlerBase<OCMembersContainer, OCSymbolWithParent, OCGenerateComparisonOperatorsContext>)ocGenerateComparisonOperatorsHandler2).getOption(ocGenerateComparisonOperatorsContext3, (OCOption<Boolean, JComponent>)ocBooleanOption2);
                        final Boolean b5 = b4;
                        final boolean b6 = b5;
                        if (b6) {
                            break Label_0154;
                        }
                        break Label_0182;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    final ArrayList<String> list3 = list;
                    final String s2 = "operator<";
                    list3.add(s2);
                    final OCGenerateComparisonOperatorsHandler ocGenerateComparisonOperatorsHandler2 = this;
                    final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext3 = ocGenerateComparisonOperatorsContext;
                    final OCBooleanOption ocBooleanOption2 = OCGenerateComparisonOperatorsHandler.ADDITIONAL_REL_OPTION;
                    final Boolean b4 = ((OCClassActionHandlerBase<OCMembersContainer, OCSymbolWithParent, OCGenerateComparisonOperatorsContext>)ocGenerateComparisonOperatorsHandler2).getOption(ocGenerateComparisonOperatorsContext3, (OCOption<Boolean, JComponent>)ocBooleanOption2);
                    final Boolean b5 = b4;
                    final boolean b6 = b5;
                    if (b6) {
                        list.add("operator>");
                        list.add("operator<=");
                        list.add("operator>=");
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            try {
                list4 = list;
                if (list4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getOperatorsToGenerate"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        return list4;
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final OCGenerateComparisonOperatorsContext p1, @NotNull final List<OCDeclaratorSymbol> p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "fields"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getReplacements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.getOperatorsToGenerate:()Ljava/util/List;
        //   136: astore          4
        //   138: aload_2        
        //   139: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.isReplaceExisting:()Z
        //   142: ifeq            154
        //   145: aload           4
        //   147: goto            183
        //   150: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           4
        //   156: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   161: aload_2        
        //   162: invokedynamic   test:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/util/function/Predicate;
        //   167: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   172: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   175: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   180: checkcast       Ljava/util/List;
        //   183: astore          5
        //   185: aload           5
        //   187: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   192: aload_1        
        //   193: aload_2        
        //   194: invokedynamic   apply:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/util/function/Function;
        //   199: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   204: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   207: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   212: checkcast       Ljava/util/List;
        //   215: astore          6
        //   217: aload           5
        //   219: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   224: aload_0        
        //   225: aload_2        
        //   226: aload_3        
        //   227: invokedynamic   apply:(Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List;)Ljava/util/function/Function;
        //   232: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   237: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   240: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   245: checkcast       Ljava/util/List;
        //   248: astore          7
        //   250: aload_2        
        //   251: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   254: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   257: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   260: astore          8
        //   262: aload           8
        //   264: ifnull          282
        //   267: aload           8
        //   269: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   272: ifne            331
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   285: dup            
        //   286: ifnonnull       330
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: new             Ljava/lang/IllegalStateException;
        //   299: dup            
        //   300: ldc             "@NotNull method %s.%s must not return null"
        //   302: ldc             2
        //   304: anewarray       Ljava/lang/Object;
        //   307: dup            
        //   308: ldc             0
        //   310: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   312: aastore        
        //   313: dup            
        //   314: ldc             1
        //   316: ldc             "getReplacements"
        //   318: aastore        
        //   319: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   322: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   325: athrow         
        //   326: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: areturn        
        //   331: aload_1        
        //   332: aload           8
        //   334: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   337: aload_2        
        //   338: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   341: aload           6
        //   343: aload           7
        //   345: aload_0        
        //   346: aload_2        
        //   347: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getInlinePolicy:(Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //   350: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getNewFunctionsReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/psi/OCStructLike;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //   353: astore          9
        //   355: aload           9
        //   357: dup            
        //   358: ifnonnull       395
        //   361: new             Ljava/lang/IllegalStateException;
        //   364: dup            
        //   365: ldc             "@NotNull method %s.%s must not return null"
        //   367: ldc             2
        //   369: anewarray       Ljava/lang/Object;
        //   372: dup            
        //   373: ldc             0
        //   375: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   377: aastore        
        //   378: dup            
        //   379: ldc             1
        //   381: ldc             "getReplacements"
        //   383: aastore        
        //   384: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   387: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   390: athrow         
        //   391: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  138    150    150    154    Ljava/lang/IllegalArgumentException;
        //  262    275    278    282    Ljava/lang/IllegalArgumentException;
        //  267    289    292    296    Ljava/lang/IllegalArgumentException;
        //  282    326    326    330    Ljava/lang/IllegalArgumentException;
        //  355    391    391    395    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0282:
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
    private String a(@NotNull final String p0, @NotNull final OCGenerateComparisonOperatorsContext p1, @NotNull final List<OCDeclaratorSymbol> p2) {
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
        //    18: ldc             "operator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getOperatorBody"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getOperatorBody"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "fields"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getOperatorBody"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: astore          4
        //   135: iconst_m1      
        //   136: istore          5
        //   138: aload           4
        //   140: invokevirtual   java/lang/String.hashCode:()I
        //   143: lookupswitch {
        //          4363232: 223
        //          4364069: 271
        //          4364100: 200
        //          4364131: 287
        //          1662708760: 239
        //          1662708762: 255
        //          default: 300
        //        }
        //   200: aload           4
        //   202: ldc             "operator=="
        //   204: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   207: ifeq            300
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iconst_0       
        //   218: istore          5
        //   220: goto            300
        //   223: aload           4
        //   225: ldc             "operator!="
        //   227: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   230: ifeq            300
        //   233: iconst_1       
        //   234: istore          5
        //   236: goto            300
        //   239: aload           4
        //   241: ldc             "operator<"
        //   243: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   246: ifeq            300
        //   249: iconst_2       
        //   250: istore          5
        //   252: goto            300
        //   255: aload           4
        //   257: ldc             "operator>"
        //   259: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   262: ifeq            300
        //   265: iconst_3       
        //   266: istore          5
        //   268: goto            300
        //   271: aload           4
        //   273: ldc             "operator<="
        //   275: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   278: ifeq            300
        //   281: iconst_4       
        //   282: istore          5
        //   284: goto            300
        //   287: aload           4
        //   289: ldc             "operator>="
        //   291: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   294: ifeq            300
        //   297: iconst_5       
        //   298: istore          5
        //   300: iload           5
        //   302: tableswitch {
        //                0: 340
        //                1: 392
        //                2: 453
        //                3: 498
        //                4: 559
        //                5: 620
        //          default: 681
        //        }
        //   340: aload_0        
        //   341: aload_3        
        //   342: aload_2        
        //   343: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   346: dup            
        //   347: ifnonnull       391
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: new             Ljava/lang/IllegalStateException;
        //   360: dup            
        //   361: ldc             "@NotNull method %s.%s must not return null"
        //   363: ldc             2
        //   365: anewarray       Ljava/lang/Object;
        //   368: dup            
        //   369: ldc             0
        //   371: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   373: aastore        
        //   374: dup            
        //   375: ldc             1
        //   377: ldc             "getOperatorBody"
        //   379: aastore        
        //   380: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   383: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   386: athrow         
        //   387: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: areturn        
        //   392: ldc             "{\n return !(%s == %s);\n}"
        //   394: iconst_2       
        //   395: anewarray       Ljava/lang/Object;
        //   398: dup            
        //   399: iconst_0       
        //   400: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getRhsName:()Ljava/lang/String;
        //   403: aastore        
        //   404: dup            
        //   405: iconst_1       
        //   406: aload_2        
        //   407: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getLhsNameOrThis:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   410: aastore        
        //   411: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   414: dup            
        //   415: ifnonnull       452
        //   418: new             Ljava/lang/IllegalStateException;
        //   421: dup            
        //   422: ldc             "@NotNull method %s.%s must not return null"
        //   424: ldc             2
        //   426: anewarray       Ljava/lang/Object;
        //   429: dup            
        //   430: ldc             0
        //   432: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   434: aastore        
        //   435: dup            
        //   436: ldc             1
        //   438: ldc             "getOperatorBody"
        //   440: aastore        
        //   441: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   444: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   447: athrow         
        //   448: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: areturn        
        //   453: aload_0        
        //   454: aload_3        
        //   455: aload_2        
        //   456: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   459: dup            
        //   460: ifnonnull       497
        //   463: new             Ljava/lang/IllegalStateException;
        //   466: dup            
        //   467: ldc             "@NotNull method %s.%s must not return null"
        //   469: ldc             2
        //   471: anewarray       Ljava/lang/Object;
        //   474: dup            
        //   475: ldc             0
        //   477: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   479: aastore        
        //   480: dup            
        //   481: ldc             1
        //   483: ldc             "getOperatorBody"
        //   485: aastore        
        //   486: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   489: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   492: athrow         
        //   493: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: areturn        
        //   498: ldc             "{\n return %s < %s;\n}"
        //   500: iconst_2       
        //   501: anewarray       Ljava/lang/Object;
        //   504: dup            
        //   505: iconst_0       
        //   506: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getRhsName:()Ljava/lang/String;
        //   509: aastore        
        //   510: dup            
        //   511: iconst_1       
        //   512: aload_2        
        //   513: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getLhsNameOrThis:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   516: aastore        
        //   517: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   520: dup            
        //   521: ifnonnull       558
        //   524: new             Ljava/lang/IllegalStateException;
        //   527: dup            
        //   528: ldc             "@NotNull method %s.%s must not return null"
        //   530: ldc             2
        //   532: anewarray       Ljava/lang/Object;
        //   535: dup            
        //   536: ldc             0
        //   538: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   540: aastore        
        //   541: dup            
        //   542: ldc             1
        //   544: ldc             "getOperatorBody"
        //   546: aastore        
        //   547: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   550: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   553: athrow         
        //   554: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: areturn        
        //   559: ldc             "{\n return !(%s < %s);\n}"
        //   561: iconst_2       
        //   562: anewarray       Ljava/lang/Object;
        //   565: dup            
        //   566: iconst_0       
        //   567: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getRhsName:()Ljava/lang/String;
        //   570: aastore        
        //   571: dup            
        //   572: iconst_1       
        //   573: aload_2        
        //   574: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getLhsNameOrThis:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   577: aastore        
        //   578: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   581: dup            
        //   582: ifnonnull       619
        //   585: new             Ljava/lang/IllegalStateException;
        //   588: dup            
        //   589: ldc             "@NotNull method %s.%s must not return null"
        //   591: ldc             2
        //   593: anewarray       Ljava/lang/Object;
        //   596: dup            
        //   597: ldc             0
        //   599: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   601: aastore        
        //   602: dup            
        //   603: ldc             1
        //   605: ldc             "getOperatorBody"
        //   607: aastore        
        //   608: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   611: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   614: athrow         
        //   615: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   618: athrow         
        //   619: areturn        
        //   620: ldc             "{\n return !(%s < %s);\n}"
        //   622: iconst_2       
        //   623: anewarray       Ljava/lang/Object;
        //   626: dup            
        //   627: iconst_0       
        //   628: aload_2        
        //   629: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getLhsNameOrThis:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //   632: aastore        
        //   633: dup            
        //   634: iconst_1       
        //   635: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getRhsName:()Ljava/lang/String;
        //   638: aastore        
        //   639: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   642: dup            
        //   643: ifnonnull       680
        //   646: new             Ljava/lang/IllegalStateException;
        //   649: dup            
        //   650: ldc             "@NotNull method %s.%s must not return null"
        //   652: ldc             2
        //   654: anewarray       Ljava/lang/Object;
        //   657: dup            
        //   658: ldc             0
        //   660: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   662: aastore        
        //   663: dup            
        //   664: ldc             1
        //   666: ldc             "getOperatorBody"
        //   668: aastore        
        //   669: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   672: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   675: athrow         
        //   676: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   679: athrow         
        //   680: areturn        
        //   681: new             Ljava/lang/IllegalArgumentException;
        //   684: dup            
        //   685: new             Ljava/lang/StringBuilder;
        //   688: dup            
        //   689: invokespecial   java/lang/StringBuilder.<init>:()V
        //   692: aload_1        
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: ldc             " should be a comparison operator"
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   704: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   707: athrow         
        //    Signature:
        //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  138    210    213    217    Ljava/lang/IllegalArgumentException;
        //  300    350    353    357    Ljava/lang/IllegalArgumentException;
        //  340    387    387    391    Ljava/lang/IllegalArgumentException;
        //  392    448    448    452    Ljava/lang/IllegalArgumentException;
        //  453    493    493    497    Ljava/lang/IllegalArgumentException;
        //  498    554    554    558    Ljava/lang/IllegalArgumentException;
        //  559    615    615    619    Ljava/lang/IllegalArgumentException;
        //  620    676    676    680    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0340:
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
    private static OCFunctionSymbol a(@NotNull final Project project, @NotNull final String s, @NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "createBinaryOperatorSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "createBinaryOperatorSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "createBinaryOperatorSymbol"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final boolean shouldBeMember = shouldBeMember(ocGenerateComparisonOperatorsContext);
        final OCCppReferenceType to = OCCppReferenceType.to(((OCActionContext<OCStructSymbol, M>)ocGenerateComparisonOperatorsContext).getParent().getType().cloneWithConstModifier(project));
        OCFakeFunctionSymbolBuilder setIsConst = null;
        boolean isFriend = false;
        Label_0203: {
            try {
                setIsConst = new OCFakeFunctionSymbolBuilder(s).setReturnType(OCIntType.BOOL_NATIVE).setContainer(((OCActionContext<OCSymbolWithQualifiedName, M>)ocGenerateComparisonOperatorsContext).getParent()).setVisibility(OCVisibility.PUBLIC).setIsConst(shouldBeMember);
                if (!shouldBeMember) {
                    isFriend = true;
                    break Label_0203;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            isFriend = false;
        }
        final OCFakeFunctionSymbolBuilder setIsOperator = setIsConst.setIsFriend(isFriend).setIsOperator(true);
        try {
            if (!shouldBeMember) {
                setIsOperator.addParam(to, getLhsName());
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        OCFunctionSymbol value;
        try {
            setIsOperator.addParam(to, getRhsName());
            value = setIsOperator.get(project);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "createBinaryOperatorSymbol"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return value;
    }
    
    @Contract(pure = true)
    @NotNull
    public static String getRhsName() {
        String s;
        try {
            s = "rhs";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getRhsName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Contract(pure = true)
    @NotNull
    public static String getLhsName() {
        String s;
        try {
            s = "lhs";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getLhsName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    public static String getLhsNameOrThis(@NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getLhsNameOrThis"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String s = null;
        Label_0063: {
            try {
                if (shouldBeMember(ocGenerateComparisonOperatorsContext)) {
                    final String lhsName;
                    s = (lhsName = "*this");
                    break Label_0063;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            String lhsName;
            s = (lhsName = getLhsName());
            try {
                if (lhsName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getLhsNameOrThis"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return s;
    }
    
    public static boolean shouldBeMember(@NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "shouldBeMember"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCClassActionHandlerBase.getOption(ocGenerateComparisonOperatorsContext.getOptionValues(), (OCOption<Boolean, JComponent>)OCGenerateComparisonOperatorsHandler.AS_MEMBER_OPTION);
    }
    
    private static void a(@NotNull final List<OCDeclaratorSymbol> p0, @NotNull final OCGenerateComparisonOperatorsContext p1, @NotNull final List<String> p2, @NotNull final List<String> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "fields"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "fillLeftsAndRights"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "fillLeftsAndRights"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "lefts"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "fillLeftsAndRights"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "rights"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "fillLeftsAndRights"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload_1        
        //   177: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   180: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   183: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   186: astore          4
        //   188: aload           4
        //   190: ifnull          208
        //   193: aload           4
        //   195: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   198: ifne            213
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: return         
        //   209: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload_1        
        //   214: invokevirtual   com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   217: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   220: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   223: dup            
        //   224: aload           4
        //   226: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   229: aload           4
        //   231: aload_2        
        //   232: aload_1        
        //   233: aload_3        
        //   234: invokedynamic   process:(Lcom/intellij/psi/PsiElement;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   239: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   242: pop            
        //   243: aload_0        
        //   244: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   249: astore          5
        //   251: aload           5
        //   253: invokeinterface java/util/Iterator.hasNext:()Z
        //   258: ifeq            377
        //   261: aload           5
        //   263: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   268: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   271: astore          6
        //   273: aload_2        
        //   274: new             Ljava/lang/StringBuilder;
        //   277: dup            
        //   278: invokespecial   java/lang/StringBuilder.<init>:()V
        //   281: aload_1        
        //   282: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.shouldBeMember:(Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Z
        //   285: ifeq            297
        //   288: ldc             ""
        //   290: goto            318
        //   293: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: new             Ljava/lang/StringBuilder;
        //   300: dup            
        //   301: invokespecial   java/lang/StringBuilder.<init>:()V
        //   304: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getLhsName:()Ljava/lang/String;
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: ldc             "."
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: aload           6
        //   323: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   329: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   332: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   337: pop            
        //   338: aload_3        
        //   339: new             Ljava/lang/StringBuilder;
        //   342: dup            
        //   343: invokespecial   java/lang/StringBuilder.<init>:()V
        //   346: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getRhsName:()Ljava/lang/String;
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: ldc             "."
        //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   357: aload           6
        //   359: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   362: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   365: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   368: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   373: pop            
        //   374: goto            251
        //   377: return         
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List<Ljava/lang/String;>;Ljava/util/List<Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    172    172    176    Ljava/lang/IllegalArgumentException;
        //  188    201    204    208    Ljava/lang/IllegalArgumentException;
        //  193    209    209    213    Ljava/lang/IllegalArgumentException;
        //  273    293    293    297    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    private String b(@NotNull final List<OCDeclaratorSymbol> list, @NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fields", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getEqOperatorBody"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getEqOperatorBody"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        final ArrayList<String> list3 = new ArrayList<String>();
        String s = null;
        Label_0198: {
            Label_0147: {
                try {
                    a(list, ocGenerateComparisonOperatorsContext, list2, list3);
                    if (!((OCClassActionHandlerBase<P, M, OCGenerateComparisonOperatorsContext>)this).getOption(ocGenerateComparisonOperatorsContext, (OCOption<Boolean, JComponent>)OCGenerateComparisonOperatorsHandler.USE_STD_TIE_OPTION)) {
                        break Label_0147;
                    }
                    final ArrayList<String> list4 = list2;
                    final int n = list4.size();
                    final int n2 = 1;
                    if (n > n2) {
                        break Label_0147;
                    }
                    break Label_0147;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final ArrayList<String> list4 = list2;
                    final int n = list4.size();
                    final int n2 = 1;
                    if (n > n2) {
                        s = String.format("std::tie(%s) == std::tie(%s)", StringUtil.join((Collection)list2, ", "), StringUtil.join((Collection)list3, ", "));
                        break Label_0198;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            s = StringUtil.join(ContainerUtil.zip((Iterable)list2, (Iterable)list3), pair -> (String)pair.first + " == " + (String)pair.second, " && \n");
        }
        final String s2 = s;
        String format;
        try {
            format = String.format("{\nreturn %s;\n}", s2);
            if (format == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getEqOperatorBody"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return format;
    }
    
    @NotNull
    private String a(@NotNull final List<OCDeclaratorSymbol> p0, @NotNull final OCGenerateComparisonOperatorsContext p1) {
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
        //    18: ldc             "fields"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLessOperatorBody"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getLessOperatorBody"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Ljava/util/ArrayList;
        //    91: dup            
        //    92: invokespecial   java/util/ArrayList.<init>:()V
        //    95: astore_3       
        //    96: new             Ljava/util/ArrayList;
        //    99: dup            
        //   100: invokespecial   java/util/ArrayList.<init>:()V
        //   103: astore          4
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_3        
        //   108: aload           4
        //   110: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;Ljava/util/List;Ljava/util/List;)V
        //   113: aload_0        
        //   114: aload_2        
        //   115: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.USE_STD_TIE_OPTION:Lcom/jetbrains/cidr/lang/settings/OCBooleanOption;
        //   118: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.getOption:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;Lcom/jetbrains/cidr/lang/settings/OCOption;)Ljava/lang/Object;
        //   121: checkcast       Ljava/lang/Boolean;
        //   124: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   127: ifeq            221
        //   130: aload_3        
        //   131: invokeinterface java/util/List.size:()I
        //   136: iconst_1       
        //   137: if_icmple       221
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: ldc             "{\nreturn std::tie(%s) < std::tie(%s);\n}"
        //   149: iconst_2       
        //   150: anewarray       Ljava/lang/Object;
        //   153: dup            
        //   154: iconst_0       
        //   155: aload_3        
        //   156: ldc             ", "
        //   158: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   161: aastore        
        //   162: dup            
        //   163: iconst_1       
        //   164: aload           4
        //   166: ldc             ", "
        //   168: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   171: aastore        
        //   172: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   175: dup            
        //   176: ifnonnull       220
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: new             Ljava/lang/IllegalStateException;
        //   189: dup            
        //   190: ldc             "@NotNull method %s.%s must not return null"
        //   192: ldc             2
        //   194: anewarray       Ljava/lang/Object;
        //   197: dup            
        //   198: ldc             0
        //   200: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   202: aastore        
        //   203: dup            
        //   204: ldc             1
        //   206: ldc             "getLessOperatorBody"
        //   208: aastore        
        //   209: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   212: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   215: athrow         
        //   216: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: areturn        
        //   221: new             Ljava/lang/StringBuilder;
        //   224: dup            
        //   225: ldc             "{\n"
        //   227: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   230: astore          5
        //   232: iconst_0       
        //   233: istore          6
        //   235: aload_3        
        //   236: aload           4
        //   238: invokestatic    com/intellij/util/containers/ContainerUtil.zip:(Ljava/lang/Iterable;Ljava/lang/Iterable;)Ljava/lang/Iterable;
        //   241: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   246: astore          7
        //   248: aload           7
        //   250: invokeinterface java/util/Iterator.hasNext:()Z
        //   255: ifeq            374
        //   258: aload           7
        //   260: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   265: checkcast       Lcom/intellij/openapi/util/Pair;
        //   268: astore          8
        //   270: iload           6
        //   272: aload_3        
        //   273: invokeinterface java/util/List.size:()I
        //   278: iconst_1       
        //   279: isub           
        //   280: if_icmpeq       337
        //   283: aload           5
        //   285: ldc             "if (%s < %s)\nreturn true;\nif (%s < %s)\nreturn false;\n"
        //   287: iconst_4       
        //   288: anewarray       Ljava/lang/Object;
        //   291: dup            
        //   292: iconst_0       
        //   293: aload           8
        //   295: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   298: aastore        
        //   299: dup            
        //   300: iconst_1       
        //   301: aload           8
        //   303: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   306: aastore        
        //   307: dup            
        //   308: iconst_2       
        //   309: aload           8
        //   311: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   314: aastore        
        //   315: dup            
        //   316: iconst_3       
        //   317: aload           8
        //   319: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   322: aastore        
        //   323: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   329: pop            
        //   330: goto            368
        //   333: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload           5
        //   339: ldc             "return %s < %s;\n"
        //   341: iconst_2       
        //   342: anewarray       Ljava/lang/Object;
        //   345: dup            
        //   346: iconst_0       
        //   347: aload           8
        //   349: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   352: aastore        
        //   353: dup            
        //   354: iconst_1       
        //   355: aload           8
        //   357: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   360: aastore        
        //   361: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: pop            
        //   368: iinc            6, 1
        //   371: goto            248
        //   374: aload           5
        //   376: ldc             "}"
        //   378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: pop            
        //   382: aload           5
        //   384: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   387: dup            
        //   388: ifnonnull       425
        //   391: new             Ljava/lang/IllegalStateException;
        //   394: dup            
        //   395: ldc             "@NotNull method %s.%s must not return null"
        //   397: ldc             2
        //   399: anewarray       Ljava/lang/Object;
        //   402: dup            
        //   403: ldc             0
        //   405: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler"
        //   407: aastore        
        //   408: dup            
        //   409: ldc             1
        //   411: ldc             "getLessOperatorBody"
        //   413: aastore        
        //   414: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   417: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   420: athrow         
        //   421: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;Lcom/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  105    140    143    147    Ljava/lang/IllegalArgumentException;
        //  130    179    182    186    Ljava/lang/IllegalArgumentException;
        //  147    216    216    220    Ljava/lang/IllegalArgumentException;
        //  270    333    333    337    Ljava/lang/IllegalArgumentException;
        //  374    421    421    425    Ljava/lang/IllegalArgumentException;
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
    protected String getActionTitle() {
        try {
            if (this.myTypes.equals(EnumSet.of(Type.EQUALITY))) {
                return OCBundle.message("generate.comparison.operators.action.title.eq", new Object[0]);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myTypes.equals(EnumSet.of(Type.RELATIONAL))) {
                return OCBundle.message("generate.comparison.operators.action.title.rel", new Object[0]);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return OCBundle.message("generate.comparison.operators.action.title", new Object[0]);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return OCBundle.message("generate.comparison.operators.member.chooser.title", new Object[0]);
    }
    
    @NotNull
    @Override
    protected OCGenerateComparisonOperatorsContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext;
        try {
            ocGenerateComparisonOperatorsContext = new OCGenerateComparisonOperatorsContext(ocStructSymbol, psiElement);
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocGenerateComparisonOperatorsContext;
    }
    
    @NotNull
    @Override
    protected Collection<OCDeclaratorSymbol> getSelectedCandidates(@NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<OCDeclaratorSymbol> list) {
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return list;
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (map.containsKey(OCGenerateComparisonOperatorsHandler.AS_MEMBER_OPTION)) {
                ocCodeStyleSettings.GENERATE_OPERATORS_AS_MEMBERS = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateComparisonOperatorsHandler.AS_MEMBER_OPTION);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (map.containsKey(OCGenerateComparisonOperatorsHandler.USE_STD_TIE_OPTION)) {
                ocCodeStyleSettings.GENERATE_COMPARISON_OPERATORS_USE_STD_TIE = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateComparisonOperatorsHandler.USE_STD_TIE_OPTION);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateComparisonOperatorsContext ocGenerateComparisonOperatorsContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateComparisonOperatorsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateComparisonOperatorsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCBooleanOption as_MEMBER_OPTION = null;
        boolean b3 = false;
        Label_0255: {
            Label_0254: {
                Label_0221: {
                    List<Pair<OCOption, Object>> list3 = null;
                    Pair pair2 = null;
                    OCBooleanOption ocBooleanOption2 = null;
                    boolean b2 = false;
                    Label_0209: {
                        Label_0208: {
                            Label_0189: {
                                Label_0155: {
                                    List<Pair<OCOption, Object>> list2 = null;
                                    Pair pair = null;
                                    OCBooleanOption ocBooleanOption = null;
                                    boolean b = false;
                                    Label_0143: {
                                        Label_0142: {
                                            Label_0123: {
                                                try {
                                                    if (!this.myTypes.contains(Type.EQUALITY)) {
                                                        break Label_0155;
                                                    }
                                                    list2 = list;
                                                    pair = new(com.intellij.openapi.util.Pair.class);
                                                    ocBooleanOption = OCGenerateComparisonOperatorsHandler.ADDITIONAL_EQ_OPTION;
                                                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                                                    if (ocCodeStyleSettings2 != null) {
                                                        break Label_0123;
                                                    }
                                                    break Label_0123;
                                                }
                                                catch (IllegalArgumentException ex3) {
                                                    throw b(ex3);
                                                }
                                                try {
                                                    list2 = list;
                                                    pair = new(com.intellij.openapi.util.Pair.class);
                                                    ocBooleanOption = OCGenerateComparisonOperatorsHandler.ADDITIONAL_EQ_OPTION;
                                                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                                                    if (ocCodeStyleSettings2 != null) {
                                                        if (!ocCodeStyleSettings.GENERATE_ADDITIONAL_EQ_OPERATORS) {
                                                            break Label_0142;
                                                        }
                                                    }
                                                }
                                                catch (IllegalArgumentException ex4) {
                                                    throw b(ex4);
                                                }
                                            }
                                            b = true;
                                            break Label_0143;
                                        }
                                        b = false;
                                    }
                                    new Pair((Object)ocBooleanOption, (Object)b);
                                    list2.add((Pair<OCOption, Object>)pair);
                                    try {
                                        if (!this.myTypes.contains(Type.RELATIONAL)) {
                                            break Label_0221;
                                        }
                                        list3 = list;
                                        pair2 = new(com.intellij.openapi.util.Pair.class);
                                        ocBooleanOption2 = OCGenerateComparisonOperatorsHandler.ADDITIONAL_REL_OPTION;
                                        final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                                        if (ocCodeStyleSettings3 != null) {
                                            break Label_0189;
                                        }
                                        break Label_0189;
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw b(ex5);
                                    }
                                }
                                try {
                                    list3 = list;
                                    pair2 = new(com.intellij.openapi.util.Pair.class);
                                    ocBooleanOption2 = OCGenerateComparisonOperatorsHandler.ADDITIONAL_REL_OPTION;
                                    final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                                    if (ocCodeStyleSettings3 != null) {
                                        if (!ocCodeStyleSettings.GENERATE_ADDITIONAL_REL_OPERATORS) {
                                            break Label_0208;
                                        }
                                    }
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw b(ex6);
                                }
                            }
                            b2 = true;
                            break Label_0209;
                        }
                        b2 = false;
                    }
                    new Pair((Object)ocBooleanOption2, (Object)b2);
                    list3.add((Pair<OCOption, Object>)pair2);
                    try {
                        as_MEMBER_OPTION = OCGenerateComparisonOperatorsHandler.AS_MEMBER_OPTION;
                        if (ocCodeStyleSettings != null) {
                            if (!ocCodeStyleSettings.GENERATE_OPERATORS_AS_MEMBERS) {
                                break Label_0254;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                b3 = true;
                break Label_0255;
            }
            b3 = false;
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)as_MEMBER_OPTION, (Object)b3));
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateComparisonOperatorsHandler.USE_STD_TIE_OPTION, (Object)(ocCodeStyleSettings != null && ocCodeStyleSettings.GENERATE_COMPARISON_OPERATORS_USE_STD_TIE)));
        super.loadOptions(psiFile, editor, ocGenerateComparisonOperatorsContext, ocCodeStyleSettings, list);
    }
    
    static {
        ADDITIONAL_EQ_OPTION = new OCBooleanOption(OCBundle.message("generate.comparison.operators.additional.eq.option", new Object[0]));
        ADDITIONAL_REL_OPTION = new OCBooleanOption(OCBundle.message("generate.comparison.operators.additional.rel.option", new Object[0]));
        AS_MEMBER_OPTION = new OCBooleanOption(OCBundle.message("generate.comparison.operators.as.member.option", new Object[0]));
        USE_STD_TIE_OPTION = new OCBooleanOption(OCBundle.message("generate.comparison.operators.use.std.tie", new Object[0]));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Type
    {
        EQUALITY, 
        RELATIONAL;
    }
}
