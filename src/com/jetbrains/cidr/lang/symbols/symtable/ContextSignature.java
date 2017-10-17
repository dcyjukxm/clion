// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Comparing;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.List;
import java.io.Serializable;

public class ContextSignature implements Serializable
{
    public static final int NO_EXACT_DEFINITION_MARKER = Integer.MAX_VALUE;
    public final List<String> myDefined;
    public final int[] myDefinedHashes;
    public final List<String> myNotDefined;
    public int myDistinctiveDefinedCount;
    public int myDistinctiveNotDefinedCount;
    @Nullable
    public final OCLanguageKind myLanguageKind;
    
    public ContextSignature(@Nullable final OCLanguageKind myLanguageKind, final Map<String, Integer> map, final Set<String> set) {
        this.myDistinctiveDefinedCount = 0;
        this.myDistinctiveNotDefinedCount = 0;
        this.myLanguageKind = myLanguageKind;
        final int size = map.size();
        this.myDefined = new ArrayList<String>(size);
        this.myDefinedHashes = new int[size];
        int n = 0;
        for (final Map.Entry<String, Integer> entry : map.entrySet()) {
            this.myDefined.add(entry.getKey());
            final Integer n2 = entry.getValue();
            int[] myDefinedHashes = null;
            int n3 = 0;
            int a = 0;
            Label_0143: {
                try {
                    myDefinedHashes = this.myDefinedHashes;
                    n3 = n;
                    if (n2 == null) {
                        a = Integer.MAX_VALUE;
                        break Label_0143;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                a = a(n2);
            }
            myDefinedHashes[n3] = a;
            ++n;
        }
        this.myNotDefined = new ArrayList<String>(set);
    }
    
    public ContextSignature() {
        this(null, Collections.emptyMap(), Collections.emptySet());
    }
    
    public boolean isCompatible(@NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ctx", "com/jetbrains/cidr/lang/symbols/symtable/ContextSignature", "isCompatible"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocInclusionContext.getLanguageKind() != this.myLanguageKind) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0371: {
            synchronized (this) {
                int n = 0;
                while (true) {
                    Label_0120: {
                        Label_0114: {
                            try {
                                if (n >= this.myDistinctiveDefinedCount) {
                                    break Label_0120;
                                }
                                if (checkDefined(ocInclusionContext, this.myDefined.get(n), this.myDefinedHashes[n])) {
                                    break Label_0114;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            return false;
                        }
                        ++n;
                        continue;
                    }
                    int n2 = 0;
                    while (true) {
                        Label_0169: {
                            Label_0163: {
                                try {
                                    if (n2 >= this.myDistinctiveNotDefinedCount) {
                                        break Label_0169;
                                    }
                                    if (!ocInclusionContext.isDefined(this.myNotDefined.get(n2))) {
                                        break Label_0163;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                                return false;
                            }
                            ++n2;
                            continue;
                        }
                        final int size = this.myDefined.size();
                        int myDistinctiveDefinedCount = this.myDistinctiveDefinedCount;
                        while (true) {
                            Label_0272: {
                                Label_0226: {
                                    Label_0266: {
                                        try {
                                            if (myDistinctiveDefinedCount >= size) {
                                                break Label_0272;
                                            }
                                            if (checkDefined(ocInclusionContext, this.myDefined.get(myDistinctiveDefinedCount), this.myDefinedHashes[myDistinctiveDefinedCount])) {
                                                break Label_0266;
                                            }
                                        }
                                        catch (IllegalArgumentException ex5) {
                                            throw a(ex5);
                                        }
                                        break Label_0226;
                                    }
                                    ++myDistinctiveDefinedCount;
                                    continue;
                                }
                                Collections.swap(this.myDefined, this.myDistinctiveDefinedCount, myDistinctiveDefinedCount);
                                ArrayUtil.swap(this.myDefinedHashes, this.myDistinctiveDefinedCount, myDistinctiveDefinedCount);
                                ++this.myDistinctiveDefinedCount;
                                return false;
                            }
                            final int size2 = this.myNotDefined.size();
                            int myDistinctiveNotDefinedCount = this.myDistinctiveNotDefinedCount;
                            while (true) {
                                Label_0359: {
                                    Label_0326: {
                                        Label_0353: {
                                            try {
                                                if (myDistinctiveNotDefinedCount >= size2) {
                                                    break Label_0359;
                                                }
                                                if (!ocInclusionContext.isDefined(this.myNotDefined.get(myDistinctiveNotDefinedCount))) {
                                                    break Label_0353;
                                                }
                                            }
                                            catch (IllegalArgumentException ex6) {
                                                throw a(ex6);
                                            }
                                            break Label_0326;
                                        }
                                        ++myDistinctiveNotDefinedCount;
                                        continue;
                                    }
                                    Collections.swap(this.myNotDefined, this.myDistinctiveNotDefinedCount, myDistinctiveNotDefinedCount);
                                    ++this.myDistinctiveNotDefinedCount;
                                    return false;
                                }
                                break Label_0371;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean checkDefined(@NotNull final OCInclusionContext p0, final String p1, final int p2) {
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
        //    18: ldc             "ctx"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/ContextSignature"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkDefined"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iload_2        
        //    45: ldc             2147483647
        //    47: if_icmpeq       58
        //    50: iconst_1       
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_0       
        //    59: istore_3       
        //    60: aload_0        
        //    61: aload_1        
        //    62: iload_3        
        //    63: ifeq            76
        //    66: getstatic       com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart.EXACT_DEFINITION:Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;
        //    69: goto            79
        //    72: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: getstatic       com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart.HAS_DEFINITION:Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;
        //    79: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getDefinition:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //    84: astore          4
        //    86: aload           4
        //    88: ifnull          99
        //    91: iconst_1       
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_0       
        //   100: istore          5
        //   102: iload           5
        //   104: ifeq            148
        //   107: iload_3        
        //   108: ifeq            148
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iload_2        
        //   119: aload           4
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getSubstitutionHash:()I
        //   124: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(I)I
        //   127: if_icmpne       145
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: iconst_1       
        //   138: goto            146
        //   141: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: iconst_0       
        //   146: istore          5
        //   148: iload           5
        //   150: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     54     54     58     Ljava/lang/IllegalArgumentException;
        //  60     72     72     76     Ljava/lang/IllegalArgumentException;
        //  86     95     95     99     Ljava/lang/IllegalArgumentException;
        //  102    111    114    118    Ljava/lang/IllegalArgumentException;
        //  107    130    133    137    Ljava/lang/IllegalArgumentException;
        //  118    141    141    145    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
    
    private static int a(final int n) {
        try {
            if (n == Integer.MAX_VALUE) {
                return n - 1;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return n;
    }
    
    public boolean sameSignature(@NotNull final ContextSignature contextSignature) {
        try {
            if (contextSignature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "other", "com/jetbrains/cidr/lang/symbols/symtable/ContextSignature", "sameSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!Comparing.equal((Object)this.myLanguageKind, (Object)contextSignature.myLanguageKind)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HashSet hashSet = ContainerUtil.newHashSet((Iterable)this.myNotDefined);
        final HashSet hashSet2 = ContainerUtil.newHashSet((Iterable)contextSignature.myNotDefined);
        try {
            if (!Comparing.equal((Object)hashSet, (Object)hashSet2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Map hashMap = ContainerUtil.newHashMap((List)this.myDefined, (List)a(this.myDefinedHashes));
        final Map hashMap2 = ContainerUtil.newHashMap((List)contextSignature.myDefined, (List)a(contextSignature.myDefinedHashes));
        try {
            if (!Comparing.equal((Object)hashMap, (Object)hashMap2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    @NotNull
    private static ArrayList<Integer> a(final int[] array) {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        ArrayList<Integer> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/ContextSignature", "toIntList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return list2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
