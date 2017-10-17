// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Ref;
import com.intellij.util.containers.HashMap;
import com.intellij.util.containers.HashSet;
import com.intellij.psi.search.SingleTargetRequestResultProcessor;
import com.intellij.psi.PsiElement;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import java.util.Set;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;

class 1MacroCallProcessor implements Processor<PsiReference>
{
    Set<OCMacroSymbol> badMacros;
    Set<OCMacroSymbol> goodMacros;
    Map<OCMacroSymbol, PsiElement> badMacroReferences;
    SingleTargetRequestResultProcessor resultProcessor;
    final /* synthetic */ PsiElement val$target;
    final /* synthetic */ Processor val$consumer;
    final /* synthetic */ boolean val$searchForRenameConflicts;
    
    1MacroCallProcessor(final PsiElement val$target, final Processor val$consumer, final boolean val$searchForRenameConflicts) {
        this.val$target = val$target;
        this.val$consumer = val$consumer;
        this.val$searchForRenameConflicts = val$searchForRenameConflicts;
        this.badMacros = (Set<OCMacroSymbol>)new HashSet();
        this.goodMacros = (Set<OCMacroSymbol>)new HashSet();
        this.badMacroReferences = (Map<OCMacroSymbol, PsiElement>)new HashMap();
        this.resultProcessor = new SingleTargetRequestResultProcessor(this.val$target);
    }
    
    public boolean process(final PsiReference p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    13: astore_3       
        //    14: aload_3        
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    18: ifeq            159
        //    21: aload_3        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //    30: astore          4
        //    32: iconst_0       
        //    33: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    36: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //    39: astore          5
        //    41: aload_3        
        //    42: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.val$target:Lcom/intellij/psi/PsiElement;
        //    49: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch.access$000:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    52: aload_0        
        //    53: aload_3        
        //    54: aload           5
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.val$consumer:Lcom/intellij/util/Processor;
        //    60: invokedynamic   process:(Lcom/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Ref;Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //    65: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.processExpansionDependenciesWithIdent:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //    70: istore          6
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.val$searchForRenameConflicts:Z
        //    76: ifeq            156
        //    79: aload           4
        //    81: ifnull          156
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload           5
        //    93: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    96: checkcast       Ljava/lang/Boolean;
        //    99: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   102: ifeq            131
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_0        
        //   113: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.goodMacros:Ljava/util/Set;
        //   116: aload           4
        //   118: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   123: pop            
        //   124: goto            156
        //   127: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.badMacros:Ljava/util/Set;
        //   135: aload           4
        //   137: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   142: pop            
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1MacroCallProcessor.badMacroReferences:Ljava/util/Map;
        //   147: aload           4
        //   149: aload_2        
        //   150: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   155: pop            
        //   156: iload           6
        //   158: ireturn        
        //   159: iconst_1       
        //   160: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  72     84     87     91     Ljava/lang/IllegalArgumentException;
        //  79     105    108    112    Ljava/lang/IllegalArgumentException;
        //  91     127    127    131    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0091:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
