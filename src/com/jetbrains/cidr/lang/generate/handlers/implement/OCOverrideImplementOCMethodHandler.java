// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;

public class OCOverrideImplementOCMethodHandler extends OCImplementOCMethodsHandler
{
    @Override
    protected String getNoMembersMessage(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocOverrideImplementActionContext.getParentNameUppercase() + " has no methods to override/implement";
    }
    
    @Override
    protected boolean defaultShowOptional() {
        return true;
    }
    
    @Override
    protected boolean defaultShowSynthesizedAccessors() {
        return true;
    }
    
    @NotNull
    @Override
    protected OCOverrideImplementActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCOverrideImplementActionContext ocOverrideImplementActionContext;
        try {
            ocOverrideImplementActionContext = new OCOverrideImplementActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement) {
                @NotNull
                @Override
                public Collection<OCMethodSymbol> getMemberCandidates() {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: new             Ljava/util/HashSet;
                    //     3: dup            
                    //     4: invokespecial   java/util/HashSet.<init>:()V
                    //     7: astore_1       
                    //     8: new             Ljava/util/ArrayList;
                    //    11: dup            
                    //    12: invokespecial   java/util/ArrayList.<init>:()V
                    //    15: astore_2       
                    //    16: aload_0        
                    //    17: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
                    //    20: astore_3       
                    //    21: aload_3        
                    //    22: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
                    //    25: astore          4
                    //    27: aload           4
                    //    29: ifnull          61
                    //    32: aload_3        
                    //    33: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                    //    36: ifnull          61
                    //    39: goto            46
                    //    42: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //    45: athrow         
                    //    46: aload           4
                    //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                    //    51: ifnonnull       110
                    //    54: goto            61
                    //    57: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //    60: athrow         
                    //    61: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
                    //    64: dup            
                    //    65: ifnonnull       109
                    //    68: goto            75
                    //    71: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //    74: athrow         
                    //    75: new             Ljava/lang/IllegalStateException;
                    //    78: dup            
                    //    79: ldc             "@NotNull method %s.%s must not return null"
                    //    81: ldc             2
                    //    83: anewarray       Ljava/lang/Object;
                    //    86: dup            
                    //    87: ldc             0
                    //    89: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1"
                    //    91: aastore        
                    //    92: dup            
                    //    93: ldc             1
                    //    95: ldc             "getMemberCandidates"
                    //    97: aastore        
                    //    98: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   101: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   104: athrow         
                    //   105: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //   108: athrow         
                    //   109: areturn        
                    //   110: aload_3        
                    //   111: aload_0        
                    //   112: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.getInterfaceSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                    //   115: aconst_null    
                    //   116: aload_0        
                    //   117: aload_1        
                    //   118: aload_2        
                    //   119: invokedynamic   process:(Lcom/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1;Ljava/util/Set;Ljava/util/List;)Lcom/intellij/util/Processor;
                    //   124: aload_0        
                    //   125: getfield        com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.val$element:Lcom/intellij/psi/PsiElement;
                    //   128: iconst_0       
                    //   129: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processInterfaceMethods:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/lang/String;Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiElement;Z)Z
                    //   132: pop            
                    //   133: aload           4
                    //   135: aconst_null    
                    //   136: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
                    //   138: aload_1        
                    //   139: aload_2        
                    //   140: invokedynamic   process:(Ljava/util/Set;Ljava/util/List;)Lcom/intellij/util/Processor;
                    //   145: iconst_1       
                    //   146: iconst_0       
                    //   147: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;ZZ)Z
                    //   150: pop            
                    //   151: aload_2        
                    //   152: dup            
                    //   153: ifnonnull       190
                    //   156: new             Ljava/lang/IllegalStateException;
                    //   159: dup            
                    //   160: ldc             "@NotNull method %s.%s must not return null"
                    //   162: ldc             2
                    //   164: anewarray       Ljava/lang/Object;
                    //   167: dup            
                    //   168: ldc             0
                    //   170: ldc             "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1"
                    //   172: aastore        
                    //   173: dup            
                    //   174: ldc             1
                    //   176: ldc             "getMemberCandidates"
                    //   178: aastore        
                    //   179: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //   182: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //   185: athrow         
                    //   186: invokestatic    com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler$1.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //   189: athrow         
                    //   190: areturn        
                    //    Signature:
                    //  ()Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                             
                    //  -----  -----  -----  -----  ---------------------------------
                    //  27     39     42     46     Ljava/lang/IllegalStateException;
                    //  32     54     57     61     Ljava/lang/IllegalStateException;
                    //  46     68     71     75     Ljava/lang/IllegalStateException;
                    //  61     105    105    109    Ljava/lang/IllegalStateException;
                    //  110    186    186    190    Ljava/lang/IllegalStateException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
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
                
                private static IllegalStateException b(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementOCMethodHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocOverrideImplementActionContext;
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Choose Methods to Override/Implement";
    }
    
    @Override
    protected boolean selectAllCandidates() {
        return false;
    }
    
    @Override
    protected String generateMethodText(final OCMethodSymbol ocMethodSymbol, final PsiElement psiElement, final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        String s = null;
        Label_0019: {
            try {
                if (ocOverrideImplementActionContext.isAbstract(ocMethodSymbol)) {
                    s = "OC Implemented Method Body.m";
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            s = "OC Overridden Method Body.m";
        }
        final String s2 = s;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final OCPropertySymbol generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
        HashMap<String, String> hashMap2 = null;
        String s3 = null;
        String s4 = null;
        Label_0074: {
            Label_0063: {
                try {
                    if (generatedFromProperty == null) {
                        return OCCallableUtil.methodFromTemplate(ocMethodSymbol, s2, psiElement, hashMap);
                    }
                    hashMap2 = hashMap;
                    s3 = "IVAR_IS_AVAILABLE";
                    final OCOverrideImplementActionContext ocOverrideImplementActionContext2 = ocOverrideImplementActionContext;
                    final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                    final boolean b = ocOverrideImplementActionContext2.isIvarAvailable(ocPropertySymbol);
                    if (b) {
                        break Label_0063;
                    }
                    break Label_0063;
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                try {
                    hashMap2 = hashMap;
                    s3 = "IVAR_IS_AVAILABLE";
                    final OCOverrideImplementActionContext ocOverrideImplementActionContext2 = ocOverrideImplementActionContext;
                    final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                    final boolean b = ocOverrideImplementActionContext2.isIvarAvailable(ocPropertySymbol);
                    if (b) {
                        s4 = "true";
                        break Label_0074;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
            s4 = "false";
        }
        hashMap2.put(s3, s4);
        return OCCallableUtil.methodFromTemplate(ocMethodSymbol, s2, psiElement, hashMap);
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
