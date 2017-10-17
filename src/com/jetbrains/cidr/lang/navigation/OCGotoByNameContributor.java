// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import java.util.List;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import java.util.Arrays;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import java.util.HashSet;
import com.intellij.navigation.NavigationItem;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCMostlySingularMultiUniqueMap;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.navigation.GotoClassContributor;

public class OCGotoByNameContributor implements GotoClassContributor
{
    private Condition<OCSymbol> myFilter;
    
    public OCGotoByNameContributor(final Condition<OCSymbol> myFilter, final boolean b) {
        this.myFilter = myFilter;
    }
    
    @NotNull
    public String[] getNames(final Project project, final boolean b) {
        final Set<String> allSymbolNames = OCGlobalProjectSymbolsCache.getAllSymbolNames(project);
        final OCMostlySingularMultiUniqueMap<String, OCClassSymbol> allCategories = OCGlobalProjectSymbolsCache.getAllCategories(project);
        final ArrayList list = new ArrayList(allSymbolNames.size() + allCategories.size());
        String[] array;
        try {
            list.addAll((Collection)allSymbolNames);
            allCategories.processAllValues((com.intellij.util.Processor<? super OCClassSymbol>)(ocClassSymbol -> {
                list.add(ocClassSymbol.getName() + "+" + ocClassSymbol.getCategoryName());
                return true;
            }));
            array = (String[])list.toArray((Object[])new String[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor", "getNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @NotNull
    public NavigationItem[] getItemsByName(final String s, final String s2, final Project project, final boolean b) {
        OCSymbol[] symbolsByName;
        try {
            symbolsByName = this.getSymbolsByName(s, project, b, (Condition<OCSymbol>)(ocSymbol -> ocSymbol instanceof OCImplementationSymbol), false);
            if (symbolsByName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor", "getItemsByName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (NavigationItem[])symbolsByName;
    }
    
    public OCSymbol[] getSymbolsByName(final String s, final Project project, final boolean b, final Condition<OCSymbol> condition, final boolean b2) {
        final ArrayList list = new ArrayList();
        final Processor processor = p3 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload           4
            //     2: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //     5: ifeq            14
            //     8: iconst_1       
            //     9: ireturn        
            //    10: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    13: athrow         
            //    14: aload           4
            //    16: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    21: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
            //    24: ifeq            50
            //    27: aload           4
            //    29: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    34: ifeq            50
            //    37: goto            44
            //    40: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    43: athrow         
            //    44: iconst_1       
            //    45: ireturn        
            //    46: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    49: athrow         
            //    50: aload           4
            //    52: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCForeignSymbol;
            //    55: ifeq            64
            //    58: iconst_1       
            //    59: ireturn        
            //    60: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    63: athrow         
            //    64: aload           4
            //    66: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSynthetic:()Z
            //    71: ifeq            80
            //    74: iconst_1       
            //    75: ireturn        
            //    76: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    79: athrow         
            //    80: aload           4
            //    82: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    85: ifeq            117
            //    88: aload_1        
            //    89: aload           4
            //    91: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getPresentableName:()Ljava/lang/String;
            //    96: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
            //   101: ifeq            117
            //   104: goto            111
            //   107: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   110: athrow         
            //   111: iconst_1       
            //   112: ireturn        
            //   113: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   116: athrow         
            //   117: aload           4
            //   119: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //   124: astore          5
            //   126: aload           5
            //   128: ifnull          213
            //   131: aload_0        
            //   132: getfield        com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.myFilter:Lcom/intellij/openapi/util/Condition;
            //   135: aload           4
            //   137: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
            //   142: ifeq            213
            //   145: goto            152
            //   148: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   151: athrow         
            //   152: aload_2        
            //   153: aload           5
            //   155: invokevirtual   com/intellij/psi/search/GlobalSearchScope.contains:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
            //   158: ifeq            213
            //   161: goto            168
            //   164: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   167: athrow         
            //   168: aload_3        
            //   169: aload           4
            //   171: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   176: pop            
            //   177: aload           4
            //   179: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   182: ifeq            213
            //   185: goto            192
            //   188: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   191: athrow         
            //   192: aload_1        
            //   193: aload           4
            //   195: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getPresentableName:()Ljava/lang/String;
            //   200: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //   205: pop            
            //   206: goto            213
            //   209: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   212: athrow         
            //   213: iconst_1       
            //   214: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  0      10     10     14     Ljava/lang/IllegalStateException;
            //  14     37     40     44     Ljava/lang/IllegalStateException;
            //  27     46     46     50     Ljava/lang/IllegalStateException;
            //  50     60     60     64     Ljava/lang/IllegalStateException;
            //  64     76     76     80     Ljava/lang/IllegalStateException;
            //  80     104    107    111    Ljava/lang/IllegalStateException;
            //  88     113    113    117    Ljava/lang/IllegalStateException;
            //  126    145    148    152    Ljava/lang/IllegalStateException;
            //  131    161    164    168    Ljava/lang/IllegalStateException;
            //  152    185    188    192    Ljava/lang/IllegalStateException;
            //  168    206    209    213    Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0152:
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
        };
        Processor processor2 = null;
        Condition[] array = null;
        int n = 0;
        Condition condition2 = null;
        Label_0074: {
            try {
                processor2 = processor;
                array = new Condition[] { condition, null };
                n = 1;
                if (b2) {
                    condition2 = Conditions.alwaysFalse();
                    break Label_0074;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            condition2 = Conditions.alwaysTrue();
        }
        array[n] = condition2;
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<OCSymbol>((com.intellij.util.Processor<? super OCSymbol>)processor2, (com.intellij.openapi.util.Condition<OCSymbol>[])array);
        final int index = s.indexOf(43);
        if (index != -1) {
            OCGlobalProjectSymbolsCache.processSymbolsByCategory(project, (Processor<OCSymbol>)(p2 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_2        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                //     4: ifeq            42
                //     7: aload_2        
                //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                //    13: aload_0        
                //    14: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //    17: ifeq            42
                //    20: goto            27
                //    23: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    26: athrow         
                //    27: aload_1        
                //    28: aload_2        
                //    29: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.process:(Ljava/lang/Object;)Z
                //    32: ifeq            50
                //    35: goto            42
                //    38: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    41: athrow         
                //    42: iconst_1       
                //    43: goto            51
                //    46: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoByNameContributor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    49: athrow         
                //    50: iconst_0       
                //    51: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      20     23     27     Ljava/lang/IllegalStateException;
                //  7      35     38     42     Ljava/lang/IllegalStateException;
                //  27     46     46     50     Ljava/lang/IllegalStateException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
            }), s.substring(index + 1).trim());
        }
        else {
            OCGlobalProjectSymbolsCache.processByQualifiedName(project, (Processor<OCSymbol>)orderedProcessor, s);
            OCGlobalProjectSymbolsCache.processSymbolsByCategory(project, (Processor<OCSymbol>)orderedProcessor, s);
        }
        orderedProcessor.finish();
        final OCSymbol[] array2 = (OCSymbol[])list.toArray(new OCSymbol[list.size()]);
        Arrays.sort(array2);
        return array2;
    }
    
    @Nullable
    public String getQualifiedName(NavigationItem symbol) {
        if (symbol instanceof OCSymbolHolderVirtualPsiElement) {
            symbol = ((OCSymbolHolderBase<NavigationItem>)symbol).getSymbol();
        }
        Label_0061: {
            try {
                if (!(symbol instanceof OCSymbolWithQualifiedName) || ((OCSymbolWithQualifiedName)symbol).getQualifier() != null) {
                    break Label_0061;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)symbol).getResolvedQualifiedName();
            try {
                if (resolvedQualifiedName != null) {
                    return resolvedQualifiedName.getCanonicalName(true);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                if (symbol instanceof OCSymbol) {
                    return ((OCSymbol)symbol).getPresentableName();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public String getQualifiedNameSeparator() {
        return "::";
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
