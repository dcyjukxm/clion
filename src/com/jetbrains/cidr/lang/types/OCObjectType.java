// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.HashSet;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Iterator;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;

public class OCObjectType extends OCType
{
    @Nullable
    private OCInterfaceSymbol myInterface;
    @Nullable
    private OCImplementationSymbol myImplementation;
    private List<OCProtocolSymbol> myAugmentedProtocols;
    private List<OCProtocolSymbol> myAllProtocols;
    private List<OCInterfaceSymbol> myCategoryInterfaces;
    private List<OCImplementationSymbol> myCategoryImplementations;
    @Nullable
    private OCObjectType mySuperType;
    private boolean myIsKindof;
    
    public OCObjectType() {
        this.myIsKindof = false;
    }
    
    public OCObjectType(@Nullable final OCInterfaceSymbol myInterface, @Nullable final OCImplementationSymbol myImplementation, final List<OCProtocolSymbol> myAllProtocols, final List<OCProtocolSymbol> myAugmentedProtocols, final List<OCInterfaceSymbol> myCategoryInterfaces, final List<OCImplementationSymbol> myCategoryImplementations, @Nullable final OCObjectType mySuperType, final boolean b, final boolean b2, final boolean myIsKindof) {
        super(b, b2);
        this.myIsKindof = false;
        this.myInterface = myInterface;
        this.myImplementation = myImplementation;
        this.myAllProtocols = myAllProtocols;
        this.myAugmentedProtocols = myAugmentedProtocols;
        this.myCategoryInterfaces = myCategoryInterfaces;
        this.myCategoryImplementations = myCategoryImplementations;
        this.mySuperType = mySuperType;
        this.myIsKindof = myIsKindof;
    }
    
    public OCObjectType(@Nullable final OCInterfaceSymbol ocInterfaceSymbol, final List<OCProtocolSymbol> list, final List<OCProtocolSymbol> list2, final boolean b, final boolean b2) {
        this(ocInterfaceSymbol, null, list, list2, Collections.emptyList(), Collections.emptyList(), null, b, b2, false);
    }
    
    public OCObjectType(@Nullable final OCInterfaceSymbol ocInterfaceSymbol, @Nullable final OCObjectType ocObjectType) {
        this(ocInterfaceSymbol, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), ocObjectType, false, false, false);
    }
    
    @Nullable
    public OCClassSymbol getClassSymbol() {
        try {
            if (this.myInterface != null) {
                final Object o = this.myInterface;
                return (OCClassSymbol)o;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Object o = this.myImplementation;
        return (OCClassSymbol)o;
    }
    
    @Nullable
    public OCInterfaceSymbol getInterface() {
        return this.myInterface;
    }
    
    @Nullable
    public OCImplementationSymbol getImplementation() {
        return this.myImplementation;
    }
    
    public List<OCInterfaceSymbol> getCategoryInterfaces() {
        return this.myCategoryInterfaces;
    }
    
    public List<OCImplementationSymbol> getCategoryImplementations() {
        return this.myCategoryImplementations;
    }
    
    @NotNull
    public List<OCProtocolSymbol> getAllProtocols() {
        List<OCProtocolSymbol> myAllProtocols;
        try {
            myAllProtocols = this.myAllProtocols;
            if (myAllProtocols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCObjectType", "getAllProtocols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myAllProtocols;
    }
    
    public List<OCProtocolSymbol> getAugmentedProtocols() {
        return this.myAugmentedProtocols;
    }
    
    @Nullable
    public OCObjectType getSuperType() {
        return this.mySuperType;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitObjectType(this);
    }
    
    @Nullable
    public <T extends OCMemberSymbol> T findMember(final String s, final Class<T> clazz) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        this.processMembers(s, clazz, (com.intellij.util.Processor<? super T>)findFirstProcessor);
        return (T)findFirstProcessor.getFoundValue();
    }
    
    public <T extends OCMemberSymbol> boolean processMembers(final Class<T> clazz, final Processor<? super T> processor) {
        return this.processMembers(null, clazz, processor);
    }
    
    public <T extends OCMemberSymbol> boolean processMembers(@Nullable final String s, final Class<T> clazz, final Processor<? super T> processor) {
        return this.processMembers(this.myInterface, s, clazz, processor, true, true);
    }
    
    public <T extends OCMemberSymbol> boolean processMembers(@Nullable final String s, final Class<T> clazz, final Processor<? super T> processor, final boolean b, final boolean b2) {
        return this.processMembers(this.myInterface, s, clazz, processor, b, b2);
    }
    
    public <T extends OCMemberSymbol> boolean processMembers(final OCClassSymbol p0, @Nullable final String p1, final Class<T> p2, final Processor<? super T> p3, final boolean p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload           5
        //     2: ifeq            43
        //     5: aload_1        
        //     6: ifnull          43
        //     9: goto            16
        //    12: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    15: athrow         
        //    16: aload_2        
        //    17: aload_3        
        //    18: aload_1        
        //    19: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    22: aload           4
        //    24: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //    27: ifne            43
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    36: athrow         
        //    37: iconst_0       
        //    38: ireturn        
        //    39: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: iload           6
        //    45: ifeq            92
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    52: ifnull          92
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aload_2        
        //    63: aload_3        
        //    64: aload_0        
        //    65: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    68: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    71: aload           4
        //    73: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //    76: ifne            92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: iconst_0       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    91: athrow         
        //    92: iload           5
        //    94: ifeq            195
        //    97: aload_1        
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   102: if_acmpne       139
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   111: athrow         
        //   112: aload_2        
        //   113: aload_3        
        //   114: aload_0        
        //   115: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myCategoryInterfaces:Ljava/util/List;
        //   118: aload           4
        //   120: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   123: ifne            139
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   132: athrow         
        //   133: iconst_0       
        //   134: ireturn        
        //   135: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   138: athrow         
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   143: ifnull          175
        //   146: aload_0        
        //   147: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   150: aload_2        
        //   151: aload_3        
        //   152: aload           4
        //   154: iconst_1       
        //   155: iconst_0       
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;ZZ)Z
        //   159: ifne            175
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   168: athrow         
        //   169: iconst_0       
        //   170: ireturn        
        //   171: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   174: athrow         
        //   175: aload_2        
        //   176: aload_3        
        //   177: aload_0        
        //   178: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myAllProtocols:Ljava/util/List;
        //   181: aload           4
        //   183: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   186: ifne            195
        //   189: iconst_0       
        //   190: ireturn        
        //   191: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   194: athrow         
        //   195: iload           6
        //   197: ifeq            278
        //   200: aload_1        
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   205: if_acmpne       242
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   214: athrow         
        //   215: aload_2        
        //   216: aload_3        
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myCategoryImplementations:Ljava/util/List;
        //   221: aload           4
        //   223: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   226: ifne            242
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   235: athrow         
        //   236: iconst_0       
        //   237: ireturn        
        //   238: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   241: athrow         
        //   242: aload_0        
        //   243: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   246: ifnull          278
        //   249: aload_0        
        //   250: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   253: aload_2        
        //   254: aload_3        
        //   255: aload           4
        //   257: iconst_0       
        //   258: iconst_1       
        //   259: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;ZZ)Z
        //   262: ifne            278
        //   265: goto            272
        //   268: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   271: athrow         
        //   272: iconst_0       
        //   273: ireturn        
        //   274: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   277: athrow         
        //   278: iconst_1       
        //   279: ireturn        
        //    Signature:
        //  <T::Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;>(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/lang/String;Ljava/lang/Class<TT;>;Lcom/intellij/util/Processor<-TT;>;ZZ)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      9      12     16     Ljava/lang/IllegalStateException;
        //  5      30     33     37     Ljava/lang/IllegalStateException;
        //  16     39     39     43     Ljava/lang/IllegalStateException;
        //  43     55     58     62     Ljava/lang/IllegalStateException;
        //  48     79     82     86     Ljava/lang/IllegalStateException;
        //  62     88     88     92     Ljava/lang/IllegalStateException;
        //  92     105    108    112    Ljava/lang/IllegalStateException;
        //  97     126    129    133    Ljava/lang/IllegalStateException;
        //  112    135    135    139    Ljava/lang/IllegalStateException;
        //  139    162    165    169    Ljava/lang/IllegalStateException;
        //  146    171    171    175    Ljava/lang/IllegalStateException;
        //  175    191    191    195    Ljava/lang/IllegalStateException;
        //  195    208    211    215    Ljava/lang/IllegalStateException;
        //  200    229    232    236    Ljava/lang/IllegalStateException;
        //  215    238    238    242    Ljava/lang/IllegalStateException;
        //  242    265    268    272    Ljava/lang/IllegalStateException;
        //  249    274    274    278    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    private static <T extends OCMemberSymbol> boolean a(@Nullable final String s, final Class<T> clazz, final List<? extends OCClassSymbol> list, final Processor<? super T> processor) {
        for (final OCClassSymbol ocClassSymbol : list) {
            try {
                if (!ocClassSymbol.processMembers(s, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)processor)) {
                    return false;
                }
                continue;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return true;
    }
    
    public boolean processInterfaceMethods(final OCClassSymbol ocClassSymbol, @Nullable final String s, final Processor<OCMethodSymbol> processor, final PsiElement psiElement, final boolean b) {
        final HashSet<String> set = new HashSet<String>();
        final LinkedHashSet<OCProtocolSymbol> set2 = new LinkedHashSet<OCProtocolSymbol>();
        OCObjectType ocObjectType = this.mySuperType;
        try {
            if (ocClassSymbol == null) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        while (ocObjectType != null) {
            for (final OCProtocolSymbol ocProtocolSymbol : ocObjectType.getAllProtocols()) {
                set.add(ocProtocolSymbol.getName());
                set2.add(ocProtocolSymbol);
            }
            ocObjectType = ocObjectType.getSuperType();
        }
        try {
            if (!ocClassSymbol.processAllMethods(s, processor, set, psiElement)) {
                return false;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocClassSymbol.getCategoryName() != null) {
                return true;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        for (final OCInterfaceSymbol ocInterfaceSymbol : this.myCategoryInterfaces) {
            try {
                if (!"".equals(ocInterfaceSymbol.getCategoryName())) {
                    continue;
                }
                final OCInterfaceSymbol ocInterfaceSymbol2 = ocInterfaceSymbol;
                final String s2 = s;
                final Processor<OCMethodSymbol> processor2 = processor;
                final HashSet<String> set3 = set;
                final PsiElement psiElement2 = psiElement;
                final boolean b2 = ocInterfaceSymbol2.processAllMethods(s2, processor2, set3, psiElement2);
                if (!b2) {
                    return false;
                }
                continue;
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                final OCInterfaceSymbol ocInterfaceSymbol2 = ocInterfaceSymbol;
                final String s2 = s;
                final Processor<OCMethodSymbol> processor2 = processor;
                final HashSet<String> set3 = set;
                final PsiElement psiElement2 = psiElement;
                final boolean b2 = ocInterfaceSymbol2.processAllMethods(s2, processor2, set3, psiElement2);
                if (!b2) {
                    return false;
                }
                continue;
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        if (b) {
            for (final OCProtocolSymbol ocProtocolSymbol2 : set2) {
                try {
                    if (!ocProtocolSymbol2.processAllMethods(s, processor, set, psiElement)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
        }
        return true;
    }
    
    @NotNull
    public String getClassName() {
        String symbolName;
        try {
            symbolName = OCSymbolBase.getSymbolName(this.getClassSymbol());
            if (symbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCObjectType", "getClassName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return symbolName;
    }
    
    @Override
    public int hashCode() {
        final int baseHashCode = this.baseHashCode();
        int n = 0;
        int hashCode = 0;
        Label_0031: {
            try {
                n = 31 * baseHashCode;
                if (this.myInterface == null) {
                    hashCode = 0;
                    break Label_0031;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            hashCode = this.myInterface.hashCode();
        }
        final int n2 = n + hashCode;
        int n3;
        try {
            n3 = 31 * n2;
            if (this.myImplementation == null) {
                final int hashCode2 = 0;
                return 31 * (31 * (n3 + hashCode2) + this.myAllProtocols.hashCode()) + Boolean.valueOf(this.myIsKindof).hashCode();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = this.myImplementation.hashCode();
        return 31 * (31 * (n3 + hashCode2) + this.myAllProtocols.hashCode()) + Boolean.valueOf(this.myIsKindof).hashCode();
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCObjectType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCObjectType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCObjectType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final OCObjectType ocObjectType = (OCObjectType)o;
        final OCObjectType ocObjectType2 = (OCObjectType)o2;
        try {
            if (ocObjectType.isKindof() != ocObjectType2.isKindof()) {
                return false;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalIterable(ocObjectType.myAllProtocols, ocObjectType2.myAllProtocols)) {
                return false;
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        try {
            if (!comparator.equalIterable(ocObjectType.myAugmentedProtocols, ocObjectType2.myAugmentedProtocols)) {
                return false;
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        try {
            if (!comparator.equalIterable(ocObjectType.myCategoryImplementations, ocObjectType2.myCategoryImplementations)) {
                return false;
            }
        }
        catch (IllegalStateException ex8) {
            throw a(ex8);
        }
        try {
            if (!comparator.equalIterable(ocObjectType.myCategoryInterfaces, ocObjectType2.myCategoryInterfaces)) {
                return false;
            }
        }
        catch (IllegalStateException ex9) {
            throw a(ex9);
        }
        try {
            if (!comparator.equalObjects(ocObjectType.myImplementation, (DeepEqual.Equality<Object>)ocObjectType2.myImplementation)) {
                return false;
            }
        }
        catch (IllegalStateException ex10) {
            throw a(ex10);
        }
        try {
            if (!comparator.equalObjects(ocObjectType.myInterface, (DeepEqual.Equality<Object>)ocObjectType2.myInterface)) {
                return false;
            }
        }
        catch (IllegalStateException ex11) {
            throw a(ex11);
        }
        try {
            if (!comparator.equalObjects(ocObjectType.mySuperType, (DeepEqual.Equality<Object>)ocObjectType2.mySuperType)) {
                return false;
            }
        }
        catch (IllegalStateException ex12) {
            throw a(ex12);
        }
        return true;
    }
    
    @NotNull
    @Override
    protected OCType doGetLeastCommonType(final OCType p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       53
        //     4: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //     7: dup            
        //     8: ifnonnull       52
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    17: athrow         
        //    18: new             Ljava/lang/IllegalStateException;
        //    21: dup            
        //    22: ldc             "@NotNull method %s.%s must not return null"
        //    24: ldc             2
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: ldc             0
        //    32: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //    34: aastore        
        //    35: dup            
        //    36: ldc             1
        //    38: ldc             "doGetLeastCommonType"
        //    40: aastore        
        //    41: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    44: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    47: athrow         
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: areturn        
        //    53: aload_1        
        //    54: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    57: ifeq            552
        //    60: aload_0        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //    64: ifeq            140
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: aload_0        
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //    78: invokeinterface java/util/List.isEmpty:()Z
        //    83: ifeq            140
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: aload_1        
        //    94: dup            
        //    95: ifnonnull       139
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   104: athrow         
        //   105: new             Ljava/lang/IllegalStateException;
        //   108: dup            
        //   109: ldc             "@NotNull method %s.%s must not return null"
        //   111: ldc             2
        //   113: anewarray       Ljava/lang/Object;
        //   116: dup            
        //   117: ldc             0
        //   119: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   121: aastore        
        //   122: dup            
        //   123: ldc             1
        //   125: ldc             "doGetLeastCommonType"
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   138: athrow         
        //   139: areturn        
        //   140: aload_1        
        //   141: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   144: ifeq            216
        //   147: aload_1        
        //   148: checkcast       Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   151: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.getAllProtocols:()Ljava/util/List;
        //   154: invokeinterface java/util/List.isEmpty:()Z
        //   159: ifeq            216
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   168: athrow         
        //   169: aload_0        
        //   170: dup            
        //   171: ifnonnull       215
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   180: athrow         
        //   181: new             Ljava/lang/IllegalStateException;
        //   184: dup            
        //   185: ldc             "@NotNull method %s.%s must not return null"
        //   187: ldc             2
        //   189: anewarray       Ljava/lang/Object;
        //   192: dup            
        //   193: ldc             0
        //   195: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   197: aastore        
        //   198: dup            
        //   199: ldc             1
        //   201: ldc             "doGetLeastCommonType"
        //   203: aastore        
        //   204: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   207: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   210: athrow         
        //   211: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   214: athrow         
        //   215: areturn        
        //   216: aload_0        
        //   217: aload_1        
        //   218: aload_2        
        //   219: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   222: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   225: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   228: if_acmpne       278
        //   231: aload_0        
        //   232: dup            
        //   233: ifnonnull       277
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   242: athrow         
        //   243: new             Ljava/lang/IllegalStateException;
        //   246: dup            
        //   247: ldc             "@NotNull method %s.%s must not return null"
        //   249: ldc             2
        //   251: anewarray       Ljava/lang/Object;
        //   254: dup            
        //   255: ldc             0
        //   257: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   259: aastore        
        //   260: dup            
        //   261: ldc             1
        //   263: ldc             "doGetLeastCommonType"
        //   265: aastore        
        //   266: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   269: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   272: athrow         
        //   273: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   276: athrow         
        //   277: areturn        
        //   278: aload_1        
        //   279: aload_0        
        //   280: aload_2        
        //   281: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   284: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   287: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   290: if_acmpne       340
        //   293: aload_1        
        //   294: dup            
        //   295: ifnonnull       339
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   304: athrow         
        //   305: new             Ljava/lang/IllegalStateException;
        //   308: dup            
        //   309: ldc             "@NotNull method %s.%s must not return null"
        //   311: ldc             2
        //   313: anewarray       Ljava/lang/Object;
        //   316: dup            
        //   317: ldc             0
        //   319: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   321: aastore        
        //   322: dup            
        //   323: ldc             1
        //   325: ldc             "doGetLeastCommonType"
        //   327: aastore        
        //   328: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   331: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   334: athrow         
        //   335: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   338: athrow         
        //   339: areturn        
        //   340: aload_0        
        //   341: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   344: ifnull          462
        //   347: aload_0        
        //   348: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   351: aload_1        
        //   352: aload_2        
        //   353: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getLeastCommonType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   356: astore_3       
        //   357: aload_3        
        //   358: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   361: ifeq            422
        //   364: aload_0        
        //   365: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myAllProtocols:Ljava/util/List;
        //   368: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   373: astore          4
        //   375: aload           4
        //   377: invokeinterface java/util/Iterator.hasNext:()Z
        //   382: ifeq            422
        //   385: aload           4
        //   387: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   392: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //   395: astore          5
        //   397: aload_1        
        //   398: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   401: aload           5
        //   403: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.implementsProtocol:(Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;)Z
        //   406: ifeq            419
        //   409: aload_3        
        //   410: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   413: aload           5
        //   415: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.augmentWithProtocol:(Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;)Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   418: astore_3       
        //   419: goto            375
        //   422: aload_3        
        //   423: dup            
        //   424: ifnonnull       461
        //   427: new             Ljava/lang/IllegalStateException;
        //   430: dup            
        //   431: ldc             "@NotNull method %s.%s must not return null"
        //   433: ldc             2
        //   435: anewarray       Ljava/lang/Object;
        //   438: dup            
        //   439: ldc             0
        //   441: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   443: aastore        
        //   444: dup            
        //   445: ldc             1
        //   447: ldc             "doGetLeastCommonType"
        //   449: aastore        
        //   450: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   453: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   456: athrow         
        //   457: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   460: athrow         
        //   461: areturn        
        //   462: aload_1        
        //   463: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   466: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   469: astore_3       
        //   470: aload_3        
        //   471: ifnull          487
        //   474: aload_3        
        //   475: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //   480: goto            488
        //   483: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   486: athrow         
        //   487: aconst_null    
        //   488: astore          4
        //   490: aload           4
        //   492: ifnull          510
        //   495: aload           4
        //   497: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   500: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   503: goto            513
        //   506: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   509: athrow         
        //   510: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   513: dup            
        //   514: ifnonnull       551
        //   517: new             Ljava/lang/IllegalStateException;
        //   520: dup            
        //   521: ldc             "@NotNull method %s.%s must not return null"
        //   523: ldc             2
        //   525: anewarray       Ljava/lang/Object;
        //   528: dup            
        //   529: ldc             0
        //   531: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   533: aastore        
        //   534: dup            
        //   535: ldc             1
        //   537: ldc             "doGetLeastCommonType"
        //   539: aastore        
        //   540: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   543: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   546: athrow         
        //   547: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   550: athrow         
        //   551: areturn        
        //   552: aload_1        
        //   553: aload_2        
        //   554: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   557: ifeq            612
        //   560: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   563: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   566: dup            
        //   567: ifnonnull       611
        //   570: goto            577
        //   573: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   576: athrow         
        //   577: new             Ljava/lang/IllegalStateException;
        //   580: dup            
        //   581: ldc             "@NotNull method %s.%s must not return null"
        //   583: ldc             2
        //   585: anewarray       Ljava/lang/Object;
        //   588: dup            
        //   589: ldc             0
        //   591: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   593: aastore        
        //   594: dup            
        //   595: ldc             1
        //   597: ldc             "doGetLeastCommonType"
        //   599: aastore        
        //   600: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   603: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   606: athrow         
        //   607: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   610: athrow         
        //   611: areturn        
        //   612: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   615: dup            
        //   616: ifnonnull       653
        //   619: new             Ljava/lang/IllegalStateException;
        //   622: dup            
        //   623: ldc             "@NotNull method %s.%s must not return null"
        //   625: ldc             2
        //   627: anewarray       Ljava/lang/Object;
        //   630: dup            
        //   631: ldc             0
        //   633: ldc             "com/jetbrains/cidr/lang/types/OCObjectType"
        //   635: aastore        
        //   636: dup            
        //   637: ldc             1
        //   639: ldc             "doGetLeastCommonType"
        //   641: aastore        
        //   642: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   645: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   648: athrow         
        //   649: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   652: athrow         
        //   653: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     14     18     Ljava/lang/IllegalStateException;
        //  4      48     48     52     Ljava/lang/IllegalStateException;
        //  53     67     70     74     Ljava/lang/IllegalStateException;
        //  60     86     89     93     Ljava/lang/IllegalStateException;
        //  74     98     101    105    Ljava/lang/IllegalStateException;
        //  93     135    135    139    Ljava/lang/IllegalStateException;
        //  140    162    165    169    Ljava/lang/IllegalStateException;
        //  147    174    177    181    Ljava/lang/IllegalStateException;
        //  169    211    211    215    Ljava/lang/IllegalStateException;
        //  216    236    239    243    Ljava/lang/IllegalStateException;
        //  231    273    273    277    Ljava/lang/IllegalStateException;
        //  278    298    301    305    Ljava/lang/IllegalStateException;
        //  293    335    335    339    Ljava/lang/IllegalStateException;
        //  422    457    457    461    Ljava/lang/IllegalStateException;
        //  470    483    483    487    Ljava/lang/IllegalStateException;
        //  490    506    506    510    Ljava/lang/IllegalStateException;
        //  513    547    547    551    Ljava/lang/IllegalStateException;
        //  552    570    573    577    Ljava/lang/IllegalStateException;
        //  560    607    607    611    Ljava/lang/IllegalStateException;
        //  612    649    649    653    Ljava/lang/IllegalStateException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isAncestorOf(@Nullable OCObjectType mySuperType) {
        try {
            if (this instanceof OCIdType) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCClassSymbol classSymbol = this.getClassSymbol();
        try {
            if (classSymbol == null) {
                return false;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        while (true) {
            Label_0046: {
                try {
                    if (mySuperType == null) {
                        break;
                    }
                    final OCObjectType ocObjectType = mySuperType;
                    final OCClassSymbol ocClassSymbol = ocObjectType.getClassSymbol();
                    if (ocClassSymbol != null) {
                        break Label_0046;
                    }
                    break Label_0046;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCObjectType ocObjectType = mySuperType;
                    final OCClassSymbol ocClassSymbol = ocObjectType.getClassSymbol();
                    if (ocClassSymbol != null) {
                        if (classSymbol.isSameClass(mySuperType.getClassSymbol())) {
                            break;
                        }
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            mySuperType = mySuperType.mySuperType;
        }
        try {
            if (mySuperType != null) {
                return true;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    public boolean implementsProtocol(@Nullable final OCProtocolSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCObjectType.myAllProtocols:Ljava/util/List;
        //     4: aload_1        
        //     5: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //    10: ifne            45
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    17: ifnull          53
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/types/OCObjectType.mySuperType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    31: aload_1        
        //    32: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.implementsProtocol:(Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;)Z
        //    35: ifeq            53
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    44: athrow         
        //    45: iconst_1       
        //    46: goto            54
        //    49: invokestatic    com/jetbrains/cidr/lang/types/OCObjectType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    52: athrow         
        //    53: iconst_0       
        //    54: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     23     27     Ljava/lang/IllegalStateException;
        //  13     38     41     45     Ljava/lang/IllegalStateException;
        //  27     49     49     53     Ljava/lang/IllegalStateException;
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
    }
    
    public OCObjectType augmentWithProtocol(@NotNull final OCProtocolSymbol ocProtocolSymbol) {
        try {
            if (ocProtocolSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolSymbol", "com/jetbrains/cidr/lang/types/OCObjectType", "augmentWithProtocol"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.implementsProtocol(ocProtocolSymbol)) {
                return this;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCObjectType ocObjectType = (OCObjectType)this.getShallowCopy();
        ocObjectType.myAugmentedProtocols = a(ocObjectType.myAugmentedProtocols, ocProtocolSymbol);
        ocObjectType.myAllProtocols = a(ocObjectType.myAllProtocols, ocProtocolSymbol);
        return ocObjectType;
    }
    
    @NotNull
    private static List<OCProtocolSymbol> a(@NotNull final List<OCProtocolSymbol> list, @NotNull final OCProtocolSymbol ocProtocolSymbol) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocols", "com/jetbrains/cidr/lang/types/OCObjectType", "cloneWithAddedProtocol"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocProtocolSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolSymbol", "com/jetbrains/cidr/lang/types/OCObjectType", "cloneWithAddedProtocol"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final ArrayList<Object> list2 = new ArrayList<Object>(list.size() + 1);
        ArrayList<Object> list3;
        try {
            list2.addAll(list);
            list2.add(ocProtocolSymbol);
            list3 = list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCObjectType", "cloneWithAddedProtocol"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (List<OCProtocolSymbol>)list3;
    }
    
    @Override
    public boolean isScalar() {
        return true;
    }
    
    public boolean isKindof() {
        return this.myIsKindof;
    }
    
    @Override
    public String getFormatString() {
        return "%@";
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
