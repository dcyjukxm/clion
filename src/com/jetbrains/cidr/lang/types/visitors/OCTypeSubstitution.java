// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.HashMap;
import gnu.trove.THashSet;
import gnu.trove.TObjectHashingStrategy;
import java.util.Map;
import com.intellij.util.Processor;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.io.Serializable;

public abstract class OCTypeSubstitution implements Serializable, DeepEqual.Equality
{
    public static final OCSimpleTypeSubstitution ID;
    
    public abstract OCType substitute(@NotNull final OCType p0, final boolean p1, @NotNull final OCResolveContext p2);
    
    public abstract OCTypeArgument getSubstitutionFor(@NotNull final OCTypeParameterSymbol p0);
    
    public abstract Collection<OCTypeArgument> getSubstitutedTypes();
    
    public abstract boolean processSubstitutions(final Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>> p0);
    
    public abstract boolean hasSubstitutionForName(@NotNull final String p0);
    
    public OCType substitute(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.substitute(ocType, false, ocResolveContext);
    }
    
    public static OCTypeSubstitution compose(final OCTypeSubstitution ocTypeSubstitution, final OCTypeSubstitution ocTypeSubstitution2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "compose"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return compose(ocTypeSubstitution, ocTypeSubstitution2, false, ocResolveContext);
    }
    
    public static OCTypeSubstitution compose(final OCTypeSubstitution ocTypeSubstitution, final OCTypeSubstitution ocTypeSubstitution2, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "compose"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTypeSubstitution == OCTypeSubstitution.ID) {
                return ocTypeSubstitution2;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocTypeSubstitution2 == OCTypeSubstitution.ID) {
                return ocTypeSubstitution;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ocTypeSubstitution.equals(ocTypeSubstitution2)) {
                return ocTypeSubstitution;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final TObjectHashingStrategy<OCSimpleTypeSubstitution> tObjectHashingStrategy = (TObjectHashingStrategy<OCSimpleTypeSubstitution>)new TObjectHashingStrategy<OCSimpleTypeSubstitution>() {
            public int computeHashCode(final OCSimpleTypeSubstitution ocSimpleTypeSubstitution) {
                return ocSimpleTypeSubstitution.hashCode();
            }
            
            public boolean equals(final OCSimpleTypeSubstitution ocSimpleTypeSubstitution, final OCSimpleTypeSubstitution ocSimpleTypeSubstitution2) {
                return ocSimpleTypeSubstitution.equals(ocSimpleTypeSubstitution2);
            }
        };
        final THashSet set = new THashSet((TObjectHashingStrategy)tObjectHashingStrategy);
        final THashSet set2 = new THashSet((TObjectHashingStrategy)tObjectHashingStrategy);
        final HashMap hashMap = new HashMap();
        Label_0206: {
            Label_0165: {
                try {
                    if (ocTypeSubstitution instanceof OCMultiTypeSubstitution) {
                        ((Set<OCSimpleTypeSubstitution>)set).addAll(((OCMultiTypeSubstitution)ocTypeSubstitution).getSubstitutions());
                        break Label_0165;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                ((Set<OCSimpleTypeSubstitution>)set).add((OCSimpleTypeSubstitution)ocTypeSubstitution);
                try {
                    if (ocTypeSubstitution2 instanceof OCMultiTypeSubstitution) {
                        ((Set<OCSimpleTypeSubstitution>)set2).addAll(((OCMultiTypeSubstitution)ocTypeSubstitution2).getSubstitutions());
                        break Label_0206;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            ((Set<OCSimpleTypeSubstitution>)set2).add((OCSimpleTypeSubstitution)ocTypeSubstitution2);
        }
        for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution : set) {
            final Iterator<OCTypeParameterSymbol> iterator2 = (Iterator<OCTypeParameterSymbol>)ocSimpleTypeSubstitution.getTypeParameters().iterator();
            while (iterator2.hasNext()) {
                ((Map<OCTypeParameterSymbol, OCSimpleTypeSubstitution>)hashMap).put(iterator2.next(), ocSimpleTypeSubstitution);
            }
        }
        for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution2 : set2) {
            Label_0541: {
                try {
                    if (((Set)set).contains(ocSimpleTypeSubstitution2)) {
                        continue;
                    }
                    if (!b) {
                        break Label_0541;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                ((Set<OCSimpleTypeSubstitution>)set).add(ocSimpleTypeSubstitution2);
                for (final OCTypeParameterSymbol ocTypeParameterSymbol : ocSimpleTypeSubstitution2.getTypeParameters()) {
                    final OCSimpleTypeSubstitution ocSimpleTypeSubstitution3 = ((Map<OCTypeParameterSymbol, OCSimpleTypeSubstitution>)hashMap).get(ocTypeParameterSymbol);
                    if (ocSimpleTypeSubstitution3 != null) {
                        final Map<OCTypeParameterSymbol, OCTypeArgument> substitutions = ocSimpleTypeSubstitution3.getSubstitutions();
                        ((Set)set).remove(ocSimpleTypeSubstitution3);
                        if (substitutions.size() <= 1) {
                            continue;
                        }
                        ((Map<OCTypeParameterSymbol, OCSimpleTypeSubstitution>)hashMap).remove(ocTypeParameterSymbol);
                        final HashMap hashMap2 = new HashMap((Map)substitutions);
                        ((Map<OCTypeParameterSymbol, OCTypeArgument>)hashMap2).remove(ocTypeParameterSymbol);
                        final OCSimpleTypeSubstitution ocSimpleTypeSubstitution4 = new OCSimpleTypeSubstitution((Map<OCTypeParameterSymbol, OCTypeArgument>)hashMap2);
                        ((Set<OCSimpleTypeSubstitution>)set).add(ocSimpleTypeSubstitution4);
                        final Iterator<OCTypeParameterSymbol> iterator5 = (Iterator<OCTypeParameterSymbol>)ocSimpleTypeSubstitution4.getTypeParameters().iterator();
                        while (iterator5.hasNext()) {
                            ((Map<OCTypeParameterSymbol, OCSimpleTypeSubstitution>)hashMap).put(iterator5.next(), ocSimpleTypeSubstitution4);
                        }
                    }
                }
                continue;
                try {
                    if (ContainerUtil.intersects((Collection)ocSimpleTypeSubstitution2.getTypeParameters(), (Collection)((Map<OCTypeParameterSymbol, OCSimpleTypeSubstitution>)hashMap).keySet())) {
                        continue;
                    }
                    ((Set<OCSimpleTypeSubstitution>)set).add(ocSimpleTypeSubstitution2);
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
        }
        return new OCMultiTypeSubstitution(new ArrayList<OCSimpleTypeSubstitution>((Collection<? extends OCSimpleTypeSubstitution>)set));
    }
    
    public boolean dependsOn(@Nullable final OCSymbolReference p0, @NotNull final OCResolveContext p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "dependsOn"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //    48: ifeq            111
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //    55: astore_3       
        //    56: aload_2        
        //    57: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.dontUseSymbolContextsInDepends:()Z
        //    60: ifne            82
        //    63: aload_0        
        //    64: aload_3        
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference.getSymbolContext:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    68: aload_2        
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.dependsOn:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    72: ifne            101
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_0        
        //    83: aload_3        
        //    84: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    87: aload_2        
        //    88: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.dependsOn:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    91: ifeq            109
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: iconst_1       
        //   102: goto            110
        //   105: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: iconst_0       
        //   110: ireturn        
        //   111: iconst_1       
        //   112: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     75     78     82     Ljava/lang/IllegalArgumentException;
        //  63     94     97     101    Ljava/lang/IllegalArgumentException;
        //  82     105    105    109    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
    
    public boolean dependsOn(@NotNull final OCQualifiedName ocQualifiedName, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (ocQualifiedName instanceof OCQualifiedNameWithArguments) {
            for (final OCTypeArgument ocTypeArgument : ((OCQualifiedNameWithArguments)ocQualifiedName).getArguments()) {
                try {
                    if (this.dependsOn(ocTypeArgument, ocResolveContext)) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        final String name = ocQualifiedName.getName();
        Label_0208: {
            Label_0182: {
                try {
                    if (name == null) {
                        break Label_0182;
                    }
                    final OCTypeSubstitution ocTypeSubstitution = this;
                    final String s = name;
                    final Processor processor = entry -> {
                        try {
                            if (!s.equals(entry.getKey().getName())) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return false;
                    };
                    final boolean b = ocTypeSubstitution.processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)processor);
                    if (!b) {
                        return true;
                    }
                    break Label_0182;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCTypeSubstitution ocTypeSubstitution = this;
                    final String s = name;
                    final Processor processor = entry -> {
                        try {
                            if (!s.equals(entry.getKey().getName())) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return false;
                    };
                    final boolean b = ocTypeSubstitution.processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)processor);
                    if (!b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    if (ocQualifiedName.getQualifier() == null) {
                        return false;
                    }
                    final OCTypeSubstitution ocTypeSubstitution2 = this;
                    final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                    final OCQualifiedName ocQualifiedName3 = ocQualifiedName2.getQualifier();
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b2 = ocTypeSubstitution2.dependsOn(ocQualifiedName3, ocResolveContext2);
                    if (b2) {
                        break Label_0208;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final OCTypeSubstitution ocTypeSubstitution2 = this;
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                final OCQualifiedName ocQualifiedName3 = ocQualifiedName2.getQualifier();
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b2 = ocTypeSubstitution2.dependsOn(ocQualifiedName3, ocResolveContext2);
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return false;
    }
    
    public boolean dependsOn(@Nullable final OCTypeArgument ocTypeArgument, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTypeArgument instanceof OCExpressionTypeArgument) {
                return this.dependsOn(((OCExpressionTypeArgument)ocTypeArgument).getSymbol(), ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocTypeArgument instanceof OCType) {
                return ((OCType)ocTypeArgument).accept((OCTypeVisitor<Boolean>)new OCBooleanTypeVisitor() {
                    @Override
                    public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
                        return OCTypeSubstitution.this.dependsOn(ocReferenceType.getReference(ocResolveContext), ocResolveContext) || ocReferenceType.getSubstitution().dependsOn(OCTypeSubstitution.this, ocResolveContext);
                    }
                    
                    @Override
                    public Boolean visitStructType(final OCStructType ocStructType) {
                        return OCTypeSubstitution.this.dependsOn(ocStructType.getSymbol(), ocResolveContext);
                    }
                    
                    @Override
                    public Boolean visitObjectType(final OCObjectType ocObjectType) {
                        return OCTypeSubstitution.this.dependsOn(ocObjectType.getClassSymbol(), ocResolveContext);
                    }
                    
                    @Override
                    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                        return OCTypeSubstitution.this.dependsOn((OCSymbol)ocTypeParameterType.getSymbol(), ocResolveContext);
                    }
                    
                    @Override
                    public Boolean visitAutoType(final OCAutoType ocAutoType) {
                        return OCTypeSubstitution.this.dependsOn(ocAutoType.getExpressionSymbol(), ocResolveContext) || OCTypeSubstitution.this.dependsOn(ocAutoType.getSubstitution(), ocResolveContext);
                    }
                });
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    public boolean dependsOn(@Nullable final OCSymbol p0, @NotNull final OCResolveContext p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "dependsOn"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //    48: if_acmpeq       81
        //    51: aload_1        
        //    52: ifnull          81
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_1        
        //    63: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    68: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    71: if_acmpne       87
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: ireturn        
        //    83: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload_1        
        //    88: astore_3       
        //    89: aload_1        
        //    90: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    93: ifeq            136
        //    96: aload_1        
        //    97: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   100: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution$3;
        //   103: dup            
        //   104: aload_0        
        //   105: aload_2        
        //   106: aload_2        
        //   107: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution$3.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   110: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.evaluate:(Lcom/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator;)Ljava/lang/Object;
        //   113: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   116: if_acmpne       134
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_1       
        //   127: goto            135
        //   130: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_0       
        //   135: ireturn        
        //   136: aload_1        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   140: ifeq            195
        //   143: aload_0        
        //   144: aload_1        
        //   145: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   148: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   151: ifnonnull       185
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_0        
        //   162: aload_1        
        //   163: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   166: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getDefaultValue:()Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   171: aload_2        
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.dependsOn:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   175: ifeq            193
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iconst_1       
        //   186: goto            194
        //   189: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: iconst_0       
        //   194: ireturn        
        //   195: aload_3        
        //   196: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   199: ifeq            337
        //   202: aload_3        
        //   203: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //   206: ifeq            216
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_3        
        //   217: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   220: ifeq            324
        //   223: aload_3        
        //   224: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   227: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   232: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   237: astore          4
        //   239: aload           4
        //   241: invokeinterface java/util/Iterator.hasNext:()Z
        //   246: ifeq            324
        //   249: aload           4
        //   251: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   256: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   259: astore          5
        //   261: aload_3        
        //   262: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //   265: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   270: aload           5
        //   272: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   275: astore          6
        //   277: aload           6
        //   279: ifnonnull       298
        //   282: aload_0        
        //   283: aload           5
        //   285: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   288: ifnonnull       315
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload_0        
        //   299: aload           6
        //   301: aload_2        
        //   302: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.dependsOn:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   305: ifeq            321
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: iconst_1       
        //   316: ireturn        
        //   317: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: goto            239
        //   324: aload_3        
        //   325: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   328: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   333: astore_3       
        //   334: goto            195
        //   337: iconst_0       
        //   338: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  51     74     77     81     Ljava/lang/IllegalArgumentException;
        //  62     83     83     87     Ljava/lang/IllegalArgumentException;
        //  89     119    122    126    Ljava/lang/IllegalArgumentException;
        //  96     130    130    134    Ljava/lang/IllegalArgumentException;
        //  136    154    157    161    Ljava/lang/IllegalArgumentException;
        //  143    178    181    185    Ljava/lang/IllegalArgumentException;
        //  161    189    189    193    Ljava/lang/IllegalArgumentException;
        //  195    209    212    216    Ljava/lang/IllegalArgumentException;
        //  277    291    294    298    Ljava/lang/IllegalArgumentException;
        //  282    308    311    315    Ljava/lang/IllegalArgumentException;
        //  298    317    317    321    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    
    public boolean dependsOn(final Collection<Object> collection, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final Object next : collection) {
            try {
                if (this.dependsOn(next, ocResolveContext)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean dependsOn(@Nullable final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o instanceof OCSymbolReference) {
                return this.dependsOn((OCSymbolReference)o, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o instanceof OCQualifiedName) {
                return this.dependsOn((OCQualifiedName)o, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (o instanceof OCTypeArgument) {
                return this.dependsOn((OCTypeArgument)o, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (o instanceof OCSymbol) {
                return this.dependsOn((OCSymbol)o, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (o instanceof Collection) {
                return this.dependsOn((Collection<Object>)o, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (o instanceof OCSimpleTypeSubstitution) {
                return this.dependsOn(((OCSimpleTypeSubstitution)o).getSubstitutedTypes(), ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (o != null) {
                throw new IllegalArgumentException(o.getClass().getName());
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return false;
    }
    
    protected boolean dependsOn(final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "dependsOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCTypeArgument ocTypeArgument : ocTypeSubstitution.getSubstitutedTypes()) {
            try {
                if (this.dependsOn(ocTypeArgument, ocResolveContext)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public OCTypeSubstitution getMinimalDependentSubstitution(final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "getMinimalDependentSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.dependsOn(o, ocResolveContext)) {
                return this;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return OCTypeSubstitution.ID;
    }
    
    public <T extends OCSymbol> T substitute(final T t, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.substitute(t, null, false, ocResolveContext);
    }
    
    public <T extends OCSymbol> T substitute(final T p0, @Nullable final OCSymbolWithQualifiedName p1, final boolean p2, @NotNull final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "substitute"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_1        
        //    46: ifnull          80
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //    53: if_acmpeq       80
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: aload_1        
        //    65: aload           4
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.dependsOn:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    70: ifne            86
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_1        
        //    81: areturn        
        //    82: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_0        
        //    87: aload_1        
        //    88: aload           4
        //    90: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getMinimalDependentSubstitution:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    93: astore          5
        //    95: aload_1        
        //    96: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //    99: ifeq            122
        //   102: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   105: dup            
        //   106: aload_1        
        //   107: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   110: aload           5
        //   112: aload           4
        //   114: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   117: areturn        
        //   118: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_1        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   126: ifeq            181
        //   129: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   132: dup            
        //   133: aload_1        
        //   134: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   137: aload           5
        //   139: aload_2        
        //   140: ifnull          158
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_2        
        //   151: goto            174
        //   154: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: aload_1        
        //   160: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   166: aload           4
        //   168: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   171: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   174: iload_3        
        //   175: aload           4
        //   177: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   180: areturn        
        //   181: aload_1        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   185: ifeq            239
        //   188: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   191: dup            
        //   192: aload_1        
        //   193: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   196: aload           5
        //   198: aload_2        
        //   199: ifnull          217
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_2        
        //   210: goto            233
        //   213: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: aload_0        
        //   218: aload_1        
        //   219: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   222: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   225: aload           4
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   230: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   233: aload           4
        //   235: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   238: areturn        
        //   239: aload_1        
        //   240: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   243: ifeq            304
        //   246: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   249: dup            
        //   250: aload_1        
        //   251: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   254: aload           5
        //   256: aload_1        
        //   257: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   260: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   263: aload_2        
        //   264: ifnull          282
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aload_2        
        //   275: goto            298
        //   278: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: aload_0        
        //   283: aload_1        
        //   284: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   287: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   290: aload           4
        //   292: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   295: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   298: aload           4
        //   300: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   303: areturn        
        //   304: aload_1        
        //   305: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   308: ifeq            331
        //   311: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   314: dup            
        //   315: aload_1        
        //   316: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   319: aload           5
        //   321: aload           4
        //   323: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   326: areturn        
        //   327: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload_1        
        //   332: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;
        //   335: ifeq            358
        //   338: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;
        //   341: dup            
        //   342: aload_1        
        //   343: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;
        //   346: aload           5
        //   348: aload           4
        //   350: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   353: areturn        
        //   354: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_1        
        //   359: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;
        //   362: ifeq            386
        //   365: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;
        //   368: dup            
        //   369: aload_1        
        //   370: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;
        //   373: aload           5
        //   375: iload_3        
        //   376: aload           4
        //   378: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   381: areturn        
        //   382: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: aload_1        
        //   387: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl;
        //   390: ifeq            413
        //   393: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl;
        //   396: dup            
        //   397: aload_1        
        //   398: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl;
        //   401: aload           5
        //   403: aload           4
        //   405: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   408: areturn        
        //   409: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   412: athrow         
        //   413: aload_1        
        //   414: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl;
        //   417: ifeq            440
        //   420: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl;
        //   423: dup            
        //   424: aload_1        
        //   425: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl;
        //   428: aload           5
        //   430: aload           4
        //   432: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   435: areturn        
        //   436: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload_1        
        //   441: areturn        
        //    Signature:
        //  <T::Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>(TT;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     73     76     80     Ljava/lang/IllegalArgumentException;
        //  63     82     82     86     Ljava/lang/IllegalArgumentException;
        //  95     118    118    122    Ljava/lang/IllegalArgumentException;
        //  122    143    146    150    Ljava/lang/IllegalArgumentException;
        //  129    154    154    158    Ljava/lang/IllegalArgumentException;
        //  181    202    205    209    Ljava/lang/IllegalArgumentException;
        //  188    213    213    217    Ljava/lang/IllegalArgumentException;
        //  239    267    270    274    Ljava/lang/IllegalArgumentException;
        //  246    278    278    282    Ljava/lang/IllegalArgumentException;
        //  304    327    327    331    Ljava/lang/IllegalArgumentException;
        //  331    354    354    358    Ljava/lang/IllegalArgumentException;
        //  358    382    382    386    Ljava/lang/IllegalArgumentException;
        //  386    409    409    413    Ljava/lang/IllegalArgumentException;
        //  413    436    436    440    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    public <T extends OCSymbol> List<T> substitute(final List<T> list, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0098: {
            List<T> list2 = null;
            Label_0063: {
                try {
                    if (this != OCTypeSubstitution.ID) {
                        break Label_0098;
                    }
                    list2 = list;
                    if (list2 == null) {
                        break Label_0063;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list2 = list;
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return list2;
        }
        final ArrayList<T> list3 = new ArrayList<T>();
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            list3.add(this.substitute(iterator.next(), ocResolveContext));
        }
        ArrayList<T> list4;
        try {
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return list4;
    }
    
    @Override
    public boolean equals(final Object o) {
        return DeepEqual.equalObjects(this, o);
    }
    
    @NotNull
    protected static OCReferenceType substituteReferenceType(@NotNull final OCReferenceType constVolatile, final OCTypeSubstitution ocTypeSubstitution, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (constVolatile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substituteReferenceType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substituteReferenceType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCReferenceTypeBuilder ocReferenceTypeBuilder = new OCReferenceTypeBuilder(constVolatile.getReference(ocResolveContext));
        OCReferenceType build;
        try {
            ocReferenceTypeBuilder.setConstVolatile(constVolatile);
            ocReferenceTypeBuilder.setProtocolNames(constVolatile.getProtocolNames());
            ocReferenceTypeBuilder.setSubstitution(compose(constVolatile.getSubstitution(), ocTypeSubstitution, b, ocResolveContext));
            ocReferenceTypeBuilder.setFunctionParameterType(constVolatile.isFunctionParameterType());
            build = ocReferenceTypeBuilder.build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution", "substituteReferenceType"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return build;
    }
    
    static {
        ID = new OCSimpleTypeSubstitution(OCTypeUtils.newTypeParameterMap());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    protected static class TypeSubstituteVisitor extends OCNonPrimitiveTypeCloneVisitor
    {
        private OCTypeSubstitution mySubstitution;
        @NotNull
        private OCResolveContext myContext;
        private boolean myOverwriteSubstitution;
        
        TypeSubstituteVisitor(final OCTypeSubstitution mySubstitution, final boolean myOverwriteSubstitution, @NotNull final OCResolveContext myContext) {
            if (myContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution$TypeSubstituteVisitor", "<init>"));
            }
            this.mySubstitution = mySubstitution;
            this.myOverwriteSubstitution = myOverwriteSubstitution;
            this.myContext = myContext;
        }
        
        @Override
        public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
            return OCTypeSubstitution.substituteReferenceType(ocReferenceType, this.mySubstitution, this.myOverwriteSubstitution, this.myContext);
        }
        
        @Override
        public OCType visitAutoType(final OCAutoType ocAutoType) {
            return new OCAutoType(ocAutoType, this.mySubstitution, this.myContext);
        }
        
        @Override
        public OCType visitStructType(final OCStructType ocStructType) {
            return new OCStructType(ContainerUtil.map((Collection)ocStructType.getStructs(), ocStructSymbol -> this.mySubstitution.substitute(ocStructSymbol, this.myContext)), ocStructType.getTypedefName(), ocStructType.isConst(), ocStructType.isVolatile(), ocStructType.getArguments());
        }
    }
}
