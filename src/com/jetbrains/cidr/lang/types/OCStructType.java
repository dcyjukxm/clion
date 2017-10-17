// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.Stack;
import java.util.Set;
import com.intellij.openapi.util.Pair;
import com.intellij.util.FilteringProcessor;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import java.util.HashSet;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import java.util.ArrayList;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.List;

public class OCStructType extends OCType
{
    @NotNull
    private List<OCStructSymbol> myStructs;
    @Nullable
    private String myTypedefName;
    @Nullable
    private List<OCTypeArgument> myArguments;
    
    public OCStructType() {
    }
    
    public OCStructType(@NotNull final OCStructSymbol ocStructSymbol) {
        if (ocStructSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        this(ocStructSymbol, null);
    }
    
    public OCStructType(@NotNull final List<OCStructSymbol> myStructs) {
        if (myStructs == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        this.myStructs = myStructs;
        this.myTypedefName = null;
    }
    
    public OCStructType(@NotNull final OCStructSymbol ocStructSymbol, @Nullable final String myTypedefName) {
        if (ocStructSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        this.myStructs = Collections.singletonList(ocStructSymbol);
        this.myTypedefName = myTypedefName;
    }
    
    public OCStructType(@NotNull final List<OCStructSymbol> list, @Nullable final String s, @Nullable final List<OCTypeArgument> myArguments) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        this(list, s, false, false);
        this.myArguments = myArguments;
    }
    
    public OCStructType(@NotNull final List<OCStructSymbol> list, @Nullable final String s, final boolean b, final boolean b2, @Nullable final List<OCTypeArgument> myArguments) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        this(list, s, b, b2);
        this.myArguments = myArguments;
    }
    
    public OCStructType(@NotNull final List<OCStructSymbol> myStructs, @Nullable final String myTypedefName, final boolean b, final boolean b2) {
        if (myStructs == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structs", "com/jetbrains/cidr/lang/types/OCStructType", "<init>"));
        }
        super(b, b2);
        this.myStructs = myStructs;
        this.myTypedefName = myTypedefName;
    }
    
    @NotNull
    public Collection<OCSymbol> collectMethods(@NotNull final String s, final OCResolveContext ocResolveContext) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "methodName", "com/jetbrains/cidr/lang/types/OCStructType", "collectMethods"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCFunctionSymbol;
            }
        };
        Collection results;
        try {
            this.processMembers(s, (Processor<OCSymbol>)collectProcessor, ocResolveContext);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "collectMethods"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Collection<OCSymbol>)results;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCStructType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCStructType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCStructType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCStructType ocStructType = (OCStructType)o;
        final OCStructType ocStructType2 = (OCStructType)o2;
        try {
            if (!Comparing.equal(ocStructType.myTypedefName, ocStructType2.myTypedefName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalIterable(ocStructType.myStructs, ocStructType2.myStructs)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode() * 31 + this.myStructs.get(0).hashCode();
    }
    
    public List<OCStructSymbol> getStructs() {
        return this.myStructs;
    }
    
    @Nullable
    public String getTypedefName() {
        return this.myTypedefName;
    }
    
    @NotNull
    public OCStructSymbol getSymbol() {
        OCStructSymbol ocStructSymbol;
        try {
            ocStructSymbol = this.myStructs.get(0);
            if (ocStructSymbol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocStructSymbol;
    }
    
    @Nullable
    public List<OCTypeArgument> getArguments() {
        return this.myArguments;
    }
    
    @Nullable
    public List<OCTypeArgument> getResolvedArguments(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "getResolvedArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myArguments == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<OCTypeArgument> list = new ArrayList<OCTypeArgument>();
        final Iterator<OCTypeArgument> iterator = this.myArguments.iterator();
        while (iterator.hasNext()) {
            list.add(OCSimpleTypeSubstitution.resolveTypeArgument(iterator.next(), ocResolveContext));
        }
        return list;
    }
    
    @NotNull
    public OCSymbolKind getKind() {
        OCSymbolKind kind;
        try {
            kind = this.myStructs.get(0).getKind();
            if (kind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return kind;
    }
    
    public boolean isEnumClass() {
        return this.myStructs.get(0).isEnumClass();
    }
    
    public boolean isEnum() {
        return this.myStructs.get(0).isEnum();
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitStructType(this);
    }
    
    @Nullable
    public OCDeclaratorSymbol findField(final String s) {
        final Iterator<OCStructSymbol> iterator = this.myStructs.iterator();
        while (iterator.hasNext()) {
            final OCDeclaratorSymbol field = iterator.next().findField(s);
            try {
                if (field != null) {
                    return field;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @NotNull
    public List<OCDeclaratorSymbol> getFields() {
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        this.myStructs.get(0).processFields((Processor<OCDeclaratorSymbol>)collectProcessor);
        final List list = (List)collectProcessor.getResults();
        List<Object> list2;
        try {
            Collections.sort((List<Object>)list, (ocDeclaratorSymbol, ocDeclaratorSymbol2) -> OCSymbolOffsetUtil.compare(ocDeclaratorSymbol.getComplexOffset(), ocDeclaratorSymbol2.getComplexOffset()));
            list2 = (List<Object>)list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getFields"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCDeclaratorSymbol>)list2;
    }
    
    public OCSymbol findConstructor(@NotNull final OCArgumentsList<?> list, @NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, @Nullable final Producer<Boolean> producer) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/types/OCStructType", "findConstructor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "findConstructor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Collection<OCSymbol> collectMethods = this.collectMethods(this.getSymbol().getName(), ocResolveContext);
        try {
            this.getSymbol().processConstructorsFromUsings((Processor<OCSymbol>)collectMethods::add, ocResolveContext);
            if (producer != null) {
                final Collection<OCSymbol> filter = (Collection<OCSymbol>)ContainerUtil.filter((Collection)collectMethods, (Condition)new Condition<OCSymbol>() {
                    Boolean myExplicitConstructorCall;
                    
                    public boolean value(final OCSymbol ocSymbol) {
                        if (!(ocSymbol instanceof OCFunctionSymbol) || !((OCFunctionSymbol)ocSymbol).isExplicit()) {
                            return true;
                        }
                        if (this.myExplicitConstructorCall == null) {
                            this.myExplicitConstructorCall = (Boolean)producer.produce();
                        }
                        return this.myExplicitConstructorCall;
                    }
                });
                return OCResolveOverloadsUtil.resolveConstructorOverloads(this, filter, list, null, b2, b2, false, b, b2, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Collection<OCSymbol> filter = collectMethods;
        return OCResolveOverloadsUtil.resolveConstructorOverloads(this, filter, list, null, b2, b2, false, b, b2, ocResolveContext);
    }
    
    public boolean isEmpty() {
        return this.isEmpty(new HashSet<OCStructSymbol>());
    }
    
    public boolean isEmpty(final HashSet<OCStructSymbol> set) {
        final boolean[] array = { true };
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            Label_0071: {
                try {
                    if (!set.add(ocStructSymbol)) {
                        return false;
                    }
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCSymbolKind ocSymbolKind = ocStructSymbol2.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                    if (ocSymbolKind == ocSymbolKind2) {
                        return false;
                    }
                    break Label_0071;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCSymbolKind ocSymbolKind = ocStructSymbol2.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                    if (ocSymbolKind == ocSymbolKind2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            ocStructSymbol.processFields((Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
                final OCType resolvedType = ocDeclaratorSymbol.getResolvedType();
                Label_0070: {
                    Label_0045: {
                        Label_0034: {
                            try {
                                if (!(resolvedType instanceof OCArrayType)) {
                                    break Label_0045;
                                }
                                final OCArrayType ocArrayType = (OCArrayType)resolvedType;
                                final OCArrayType ocArrayType2 = ocArrayType;
                                final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                                final OCFile ocFile = ocDeclaratorSymbol2.getContainingOCFile();
                                final HashSet<OCStructSymbol> set2 = set;
                                final boolean b = ocArrayType2.isEmpty((PsiFile)ocFile, set2);
                                if (!b) {
                                    break Label_0034;
                                }
                                return true;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final OCArrayType ocArrayType = (OCArrayType)resolvedType;
                                final OCArrayType ocArrayType2 = ocArrayType;
                                final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                                final OCFile ocFile = ocDeclaratorSymbol2.getContainingOCFile();
                                final HashSet<OCStructSymbol> set2 = set;
                                final boolean b = ocArrayType2.isEmpty((PsiFile)ocFile, set2);
                                if (!b) {
                                    array[0] = false;
                                    return true;
                                }
                                return true;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                        try {
                            if (!(resolvedType instanceof OCStructType)) {
                                break Label_0070;
                            }
                            final OCArrayType ocArrayType3 = (OCArrayType)resolvedType;
                            final OCStructType ocStructType = (OCStructType)ocArrayType3;
                            final HashSet<OCStructSymbol> set3 = set;
                            final boolean b2 = ocStructType.isEmpty(set3);
                            if (!b2) {
                                break Label_0070;
                            }
                            return true;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        final OCArrayType ocArrayType3 = (OCArrayType)resolvedType;
                        final OCStructType ocStructType = (OCStructType)ocArrayType3;
                        final HashSet<OCStructSymbol> set3 = set;
                        final boolean b2 = ocStructType.isEmpty(set3);
                        if (!b2) {
                            array[0] = false;
                            return true;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                array[0] = false;
                return true;
            }));
            set.remove(ocStructSymbol);
        }
        return array[0];
    }
    
    public boolean processFields(@Nullable final String s, final Processor<? super OCDeclaratorSymbol> processor, final OCResolveContext ocResolveContext) {
        return this.processMembers(s, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (ocSymbol instanceof OCDeclaratorSymbol) {
                    return processor.process((Object)ocSymbol);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        }), ocResolveContext);
    }
    
    public boolean isAbstract() {
        try {
            if (!this.getSymbol().processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
                Label_0024: {
                    try {
                        if (!(ocSymbol instanceof OCFunctionSymbol)) {
                            break Label_0024;
                        }
                        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final boolean b = ocFunctionSymbol2.isPureVirtual();
                        if (!b) {
                            break Label_0024;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final boolean b = ocFunctionSymbol2.isPureVirtual();
                        if (!b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }))) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isUnnamed() {
        return this.myStructs.get(0).isUnnamed();
    }
    
    public boolean isPredeclaration() {
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            try {
                if (!ocStructSymbol.isPredeclaration()) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return true;
    }
    
    public boolean isPOD(final boolean b) {
        try {
            if (!ContainerUtil.exists((Iterable)this.myStructs, ocStructSymbol -> {
                try {
                    if (!ocStructSymbol.isPOD(b)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return false;
            })) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static boolean processMembersInBaseTypes(@NotNull final OCStructSymbol p0, @Nullable final String p1, final boolean p2, final boolean p3, @NotNull final Condition<OCSymbol> p4, @NotNull final Processor<OCSymbol> p5, @NotNull final OCResolveContext p6) {
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
        //    18: ldc             "scope"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processMembersInBaseTypes"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "condition"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "processMembersInBaseTypes"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           5
        //    91: ifnonnull       134
        //    94: new             Ljava/lang/IllegalArgumentException;
        //    97: dup            
        //    98: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   100: ldc             3
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "processor"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             2
        //   120: ldc             "processMembersInBaseTypes"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload           6
        //   136: ifnonnull       179
        //   139: new             Ljava/lang/IllegalArgumentException;
        //   142: dup            
        //   143: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   145: ldc             3
        //   147: anewarray       Ljava/lang/Object;
        //   150: dup            
        //   151: ldc             0
        //   153: ldc             "context"
        //   155: aastore        
        //   156: dup            
        //   157: ldc             1
        //   159: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   161: aastore        
        //   162: dup            
        //   163: ldc             2
        //   165: ldc             "processMembersInBaseTypes"
        //   167: aastore        
        //   168: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   171: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   174: athrow         
        //   175: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newSymbolWithSubstitutionSet:()Ljava/util/Set;
        //   182: astore          7
        //   184: new             Ljava/util/Stack;
        //   187: dup            
        //   188: invokespecial   java/util/Stack.<init>:()V
        //   191: astore          8
        //   193: new             Lcom/intellij/util/FilteringProcessor;
        //   196: dup            
        //   197: aload           4
        //   199: aload           5
        //   201: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //   204: astore          9
        //   206: iload_3        
        //   207: ifne            235
        //   210: aload_1        
        //   211: ifnonnull       235
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: new             Ljava/util/HashSet;
        //   224: dup            
        //   225: invokespecial   java/util/HashSet.<init>:()V
        //   228: astore          10
        //   230: iconst_1       
        //   231: istore_3       
        //   232: goto            238
        //   235: aconst_null    
        //   236: astore          10
        //   238: aload           7
        //   240: aload_0        
        //   241: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   246: pop            
        //   247: aload           8
        //   249: new             Lcom/intellij/openapi/util/Pair;
        //   252: dup            
        //   253: aload_0        
        //   254: aload           10
        //   256: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   259: invokevirtual   java/util/Stack.add:(Ljava/lang/Object;)Z
        //   262: pop            
        //   263: aload           8
        //   265: invokevirtual   java/util/Stack.isEmpty:()Z
        //   268: ifne            362
        //   271: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   274: aload           8
        //   276: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   279: checkcast       Lcom/intellij/openapi/util/Pair;
        //   282: astore          11
        //   284: aload           11
        //   286: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   289: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   292: ifne            302
        //   295: goto            263
        //   298: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload           11
        //   304: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   307: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   310: astore          12
        //   312: iload_3        
        //   313: istore          13
        //   315: aload           12
        //   317: aload           6
        //   319: aload_0        
        //   320: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.useFor:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   323: aload_1        
        //   324: aload           9
        //   326: aload           11
        //   328: iload_2        
        //   329: iload           13
        //   331: aload_0        
        //   332: aload           7
        //   334: aload           8
        //   336: aload           5
        //   338: aload           4
        //   340: aload           6
        //   342: invokedynamic   process:(Ljava/lang/String;Lcom/intellij/util/FilteringProcessor;Lcom/intellij/openapi/util/Pair;ZZLcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/Set;Ljava/util/Stack;Lcom/intellij/util/Processor;Lcom/intellij/openapi/util/Condition;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   347: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   350: ifne            359
        //   353: iconst_0       
        //   354: ireturn        
        //   355: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   358: athrow         
        //   359: goto            263
        //   362: iconst_1       
        //   363: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;ZZLcom/intellij/openapi/util/Condition<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  89     130    130    134    Ljava/lang/IllegalArgumentException;
        //  134    175    175    179    Ljava/lang/IllegalArgumentException;
        //  206    214    217    221    Ljava/lang/IllegalArgumentException;
        //  284    298    298    302    Ljava/lang/IllegalArgumentException;
        //  315    355    355    359    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0206_1:
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
    
    public static boolean processMembersOfNamespace(@NotNull final OCNamespaceLikeSymbol p0, @Nullable final String p1, final boolean p2, final boolean p3, final Processor<OCSymbol> p4, @NotNull final OCResolveContext p5) {
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
        //    18: ldc             "namespace"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processMembersOfNamespace"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           5
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "context"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "processMembersOfNamespace"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //    92: new             Lcom/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector;
        //    95: dup            
        //    96: aload           4
        //    98: invokespecial   com/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector.<init>:(Lcom/intellij/util/Processor;)V
        //   101: astore          6
        //   103: new             Lcom/intellij/util/FilteringProcessor;
        //   106: dup            
        //   107: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.WITHOUT_QUALIFIER:Lcom/intellij/openapi/util/Condition;
        //   110: aload           6
        //   112: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //   115: astore          7
        //   117: aload_0        
        //   118: aload_1        
        //   119: aload           7
        //   121: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   126: ifne            135
        //   129: iconst_0       
        //   130: ireturn        
        //   131: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newSymbolWithSubstitutionSet:()Ljava/util/Set;
        //   138: astore          8
        //   140: new             Ljava/util/Stack;
        //   143: dup            
        //   144: invokespecial   java/util/Stack.<init>:()V
        //   147: astore          9
        //   149: aload           8
        //   151: aload_0        
        //   152: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   157: pop            
        //   158: aload           9
        //   160: new             Lcom/intellij/openapi/util/Pair;
        //   163: dup            
        //   164: aload_0        
        //   165: aconst_null    
        //   166: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   169: invokevirtual   java/util/Stack.add:(Ljava/lang/Object;)Z
        //   172: pop            
        //   173: aload_0        
        //   174: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol.getInlineNamespaces:()Ljava/util/Collection;
        //   179: astore          10
        //   181: iload_2        
        //   182: ifeq            270
        //   185: aload           10
        //   187: ifnull          270
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload           6
        //   199: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector.access$000:(Lcom/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector;)Z
        //   202: ifeq            223
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload_1        
        //   213: ifnonnull       270
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload           10
        //   225: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   230: astore          11
        //   232: aload           11
        //   234: invokeinterface java/util/Iterator.hasNext:()Z
        //   239: ifeq            270
        //   242: aload           11
        //   244: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   249: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   252: astore          12
        //   254: aload           12
        //   256: aload_1        
        //   257: iconst_1       
        //   258: iload_3        
        //   259: aload           4
        //   261: aload           5
        //   263: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersOfNamespace:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;Ljava/lang/String;ZZLcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   266: pop            
        //   267: goto            232
        //   270: iload_2        
        //   271: ifeq            804
        //   274: aload_0        
        //   275: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol.getNamespaceUsings:()Ljava/util/Collection;
        //   280: ifnonnull       302
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: aload           10
        //   292: ifnull          804
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload           6
        //   304: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector.access$000:(Lcom/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector;)Z
        //   307: ifeq            328
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: aload_1        
        //   318: ifnonnull       804
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: aload           9
        //   330: invokevirtual   java/util/Stack.isEmpty:()Z
        //   333: ifne            804
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   346: aload           9
        //   348: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   351: checkcast       Lcom/intellij/openapi/util/Pair;
        //   354: astore          11
        //   356: aload           11
        //   358: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   361: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //   364: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol.getNamespaceUsings:()Ljava/util/Collection;
        //   369: astore          12
        //   371: aload           12
        //   373: ifnull          801
        //   376: aload           12
        //   378: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   383: astore          13
        //   385: aload           13
        //   387: invokeinterface java/util/Iterator.hasNext:()Z
        //   392: ifeq            801
        //   395: aload           13
        //   397: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   402: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   405: astore          14
        //   407: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   410: aload           5
        //   412: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   415: astore          15
        //   417: ldc             2147483647
        //   419: istore          16
        //   421: aload           15
        //   423: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   426: ifeq            447
        //   429: aload           15
        //   431: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   434: iconst_0       
        //   435: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getMembersContainer:(Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //   440: goto            448
        //   443: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: aconst_null    
        //   448: astore          17
        //   450: aload           17
        //   452: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol$UsingNamespaceProvider;
        //   455: ifeq            510
        //   458: aload           17
        //   460: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol$UsingNamespaceProvider;
        //   463: aload           14
        //   465: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol$UsingNamespaceProvider.getUsingNamespaceIndex:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;)I
        //   470: istore          16
        //   472: iload           16
        //   474: aload           5
        //   476: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getCurrentUsingIndex:()I
        //   479: if_icmpge       385
        //   482: aload           5
        //   484: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isOriginallyProcessNonImported:()Z
        //   487: ifeq            510
        //   490: iload           16
        //   492: iconst_m1      
        //   493: if_icmpne       510
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: goto            385
        //   506: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   509: athrow         
        //   510: new             Lcom/jetbrains/cidr/lang/types/OCStructType$InnerProcessor;
        //   513: dup            
        //   514: aload_1        
        //   515: iload_3        
        //   516: iconst_1       
        //   517: aconst_null    
        //   518: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   521: aload           8
        //   523: aload           9
        //   525: aload           4
        //   527: invokestatic    com/intellij/openapi/util/Conditions.alwaysTrue:()Lcom/intellij/openapi/util/Condition;
        //   530: aload           5
        //   532: invokespecial   com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.<init>:(Ljava/lang/String;ZZLjava/util/Set;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Ljava/util/Set;Ljava/util/Stack;Lcom/intellij/util/Processor;Lcom/intellij/openapi/util/Condition;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   535: astore          18
        //   537: aload           5
        //   539: iload           16
        //   541: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.startResolvingNamespaceUsing:(I)V
        //   544: aload           5
        //   546: aload           14
        //   548: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   551: iconst_0       
        //   552: iload_3        
        //   553: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   556: astore          19
        //   558: aload           19
        //   560: invokeinterface java/util/List.isEmpty:()Z
        //   565: ifeq            624
        //   568: aload           5
        //   570: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isOriginallyProcessNonImported:()Z
        //   573: ifeq            624
        //   576: goto            583
        //   579: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   582: athrow         
        //   583: aload           5
        //   585: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isProcessNonImported:()Z
        //   588: ifne            624
        //   591: goto            598
        //   594: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   597: athrow         
        //   598: aload           5
        //   600: iconst_1       
        //   601: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setProcessNonImported:(Z)V
        //   604: aload           5
        //   606: aload           14
        //   608: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   611: iconst_0       
        //   612: iload_3        
        //   613: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   616: astore          19
        //   618: aload           5
        //   620: iconst_0       
        //   621: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setProcessNonImported:(Z)V
        //   624: iconst_0       
        //   625: istore          20
        //   627: aconst_null    
        //   628: astore          21
        //   630: aload           19
        //   632: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   637: astore          22
        //   639: aload           22
        //   641: invokeinterface java/util/Iterator.hasNext:()Z
        //   646: ifeq            793
        //   649: aload           22
        //   651: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   656: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   659: astore          23
        //   661: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   664: aload           5
        //   666: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   669: astore          24
        //   671: aload           24
        //   673: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   676: ifne            703
        //   679: aload           23
        //   681: aload           24
        //   683: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //   686: ifne            703
        //   689: goto            696
        //   692: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   695: athrow         
        //   696: goto            639
        //   699: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   702: athrow         
        //   703: aload           23
        //   705: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   708: ifeq            769
        //   711: aload           23
        //   713: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   716: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   719: astore          25
        //   721: iload           20
        //   723: ifeq            752
        //   726: aload           25
        //   728: aload           21
        //   730: aload           5
        //   732: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   735: ifne            752
        //   738: goto            745
        //   741: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   744: athrow         
        //   745: goto            793
        //   748: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   751: athrow         
        //   752: aload           25
        //   754: astore          21
        //   756: aload           23
        //   758: aload           14
        //   760: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   763: ifeq            769
        //   766: iconst_1       
        //   767: istore          20
        //   769: aload           18
        //   771: aload           23
        //   773: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   776: ifne            790
        //   779: aload           5
        //   781: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.stopResolvingNamespaceUsing:()V
        //   784: iconst_0       
        //   785: ireturn        
        //   786: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   789: athrow         
        //   790: goto            639
        //   793: aload           5
        //   795: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.stopResolvingNamespaceUsing:()V
        //   798: goto            385
        //   801: goto            328
        //   804: aload_0        
        //   805: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   808: ifeq            1093
        //   811: aload_0        
        //   812: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   815: astore          11
        //   817: iload_3        
        //   818: ifne            1043
        //   821: aload           11
        //   823: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   826: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   829: if_acmpne       1043
        //   832: goto            839
        //   835: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   838: athrow         
        //   839: aload           11
        //   841: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   844: astore          12
        //   846: ldc             "operator="
        //   848: aload_1        
        //   849: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   852: ifeq            1043
        //   855: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   858: dup            
        //   859: aload           11
        //   861: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)V
        //   864: astore          13
        //   866: aload           13
        //   868: aload           11
        //   870: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //   873: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   876: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   879: astore          14
        //   881: aload           11
        //   883: aload_1        
        //   884: new             Lcom/jetbrains/cidr/lang/types/OCStructType$3;
        //   887: dup            
        //   888: aload           14
        //   890: aload           12
        //   892: invokespecial   com/jetbrains/cidr/lang/types/OCStructType$3.<init>:(Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //   895: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   898: ifeq            1043
        //   901: aload           11
        //   903: ldc             "operator="
        //   905: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   908: dup            
        //   909: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   912: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   915: ifeq            1043
        //   918: goto            925
        //   921: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   924: athrow         
        //   925: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   928: dup            
        //   929: aload           13
        //   931: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   934: iconst_1       
        //   935: anewarray       Lcom/jetbrains/cidr/lang/types/OCType;
        //   938: dup            
        //   939: iconst_0       
        //   940: aload           14
        //   942: aastore        
        //   943: invokestatic    com/intellij/util/containers/ContainerUtil.list:([Ljava/lang/Object;)Ljava/util/List;
        //   946: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   949: astore          15
        //   951: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.EXPLICIT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   954: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.getMask:()I
        //   957: istore          16
        //   959: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   962: dup            
        //   963: aconst_null    
        //   964: aconst_null    
        //   965: iconst_m1      
        //   966: aconst_null    
        //   967: ldc             "<unnamed>"
        //   969: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   972: aload           14
        //   974: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   977: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   980: astore          17
        //   982: aload           4
        //   984: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   987: dup            
        //   988: aload           11
        //   990: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //   993: aload           11
        //   995: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   998: aload           11
        //  1000: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getComplexOffset:()J
        //  1003: aload           11
        //  1005: aload_1        
        //  1006: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1009: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1012: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1015: iconst_0       
        //  1016: iload           16
        //  1018: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //  1021: aload           15
        //  1023: aload           17
        //  1025: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1028: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1031: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1034: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Ljava/util/List;IILjava/util/List;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  1037: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //  1042: pop            
        //  1043: aload           6
        //  1045: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector.access$100:(Lcom/jetbrains/cidr/lang/types/OCStructType$1SomethingProcessedDetector;)Z
        //  1048: ifeq            1062
        //  1051: aload_1        
        //  1052: ifnonnull       1093
        //  1055: goto            1062
        //  1058: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1061: athrow         
        //  1062: aload           11
        //  1064: aload_1        
        //  1065: iload_3        
        //  1066: iconst_1       
        //  1067: invokestatic    com/intellij/openapi/util/Conditions.alwaysTrue:()Lcom/intellij/openapi/util/Condition;
        //  1070: aload           4
        //  1072: aload           5
        //  1074: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersInBaseTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;ZZLcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1077: ifne            1093
        //  1080: goto            1087
        //  1083: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1086: athrow         
        //  1087: iconst_0       
        //  1088: ireturn        
        //  1089: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1092: athrow         
        //  1093: iconst_1       
        //  1094: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;Ljava/lang/String;ZZLcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  117    131    131    135    Ljava/lang/IllegalArgumentException;
        //  181    190    193    197    Ljava/lang/IllegalArgumentException;
        //  185    205    208    212    Ljava/lang/IllegalArgumentException;
        //  197    216    219    223    Ljava/lang/IllegalArgumentException;
        //  270    283    286    290    Ljava/lang/IllegalArgumentException;
        //  274    295    298    302    Ljava/lang/IllegalArgumentException;
        //  290    310    313    317    Ljava/lang/IllegalArgumentException;
        //  302    321    324    328    Ljava/lang/IllegalArgumentException;
        //  317    336    339    343    Ljava/lang/IllegalArgumentException;
        //  421    443    443    447    Ljava/lang/IllegalArgumentException;
        //  482    496    499    503    Ljava/lang/IllegalArgumentException;
        //  490    506    506    510    Ljava/lang/IllegalArgumentException;
        //  558    576    579    583    Ljava/lang/IllegalArgumentException;
        //  568    591    594    598    Ljava/lang/IllegalArgumentException;
        //  671    689    692    696    Ljava/lang/IllegalArgumentException;
        //  679    699    699    703    Ljava/lang/IllegalArgumentException;
        //  721    738    741    745    Ljava/lang/IllegalArgumentException;
        //  726    748    748    752    Ljava/lang/IllegalArgumentException;
        //  769    786    786    790    Ljava/lang/IllegalArgumentException;
        //  817    832    835    839    Ljava/lang/IllegalArgumentException;
        //  881    918    921    925    Ljava/lang/IllegalArgumentException;
        //  1043   1055   1058   1062   Ljava/lang/IllegalArgumentException;
        //  1051   1080   1083   1087   Ljava/lang/IllegalArgumentException;
        //  1062   1089   1089   1093   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0197:
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
    
    public boolean processMembers(@Nullable final String s, final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            try {
                if (!processMembersOfNamespace(ocStructSymbol, s, true, false, processor, ocResolveContext)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return true;
    }
    
    public boolean processMembers(@Nullable final String s, final boolean b, final boolean b2, final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            try {
                if (!processMembersOfNamespace(ocStructSymbol, s, b, b2, processor, ocResolveContext)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return true;
    }
    
    public static boolean isSubstructOf(final OCStructSymbol ocStructSymbol, final OCStructSymbol ocStructSymbol2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "isSubstructOf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0074: {
            try {
                if (Comparing.equal((Object)ocStructSymbol, (Object)ocStructSymbol2)) {
                    break Label_0074;
                }
                final OCStructSymbol ocStructSymbol3 = ocStructSymbol2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final Object o;
                final boolean b;
                final OCStructSymbol.BaseClassProcessor baseClassProcessor = (ocSymbol, p2) -> {
                    try {
                        if (!ocSymbol.equals(o)) {
                            return b;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return b;
                };
                final boolean b2 = true;
                final boolean b3 = ocStructSymbol3.processAllBaseClasses(ocResolveContext2, baseClassProcessor, b2);
                if (!b3) {
                    break Label_0074;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCStructSymbol ocStructSymbol3 = ocStructSymbol2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final Object o2;
                final boolean b4;
                final OCStructSymbol.BaseClassProcessor baseClassProcessor = (ocSymbol2, p2) -> {
                    try {
                        if (!ocSymbol2.equals(o2)) {
                            return b4;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    return b4;
                };
                final boolean b2 = true;
                final boolean b3 = ocStructSymbol3.processAllBaseClasses(ocResolveContext2, baseClassProcessor, b2);
                if (!b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.isMagicInside(ocResolveContext, true);
    }
    
    @Override
    public boolean isSubclassOfMagic(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "isSubclassOfMagic"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.isMagicInside(ocResolveContext, false);
    }
    
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            Label_0140: {
                Label_0100: {
                    try {
                        if (!b) {
                            break Label_0100;
                        }
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = OCResolveUtil.hasNonResolvedTemplateParameters(ocStructSymbol2, ocResolveContext2);
                        if (b2) {
                            return true;
                        }
                        break Label_0100;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = OCResolveUtil.hasNonResolvedTemplateParameters(ocStructSymbol2, ocResolveContext2);
                        if (b2) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        if (!ocStructSymbol.getBaseCppClasses(ocResolveContext).isEmpty()) {
                            break Label_0140;
                        }
                        final OCStructType ocStructType = this;
                        final List<OCStructSymbol> list = ocStructType.myStructs;
                        final int n = list.size();
                        final int n2 = 1;
                        if (n <= n2) {
                            return false;
                        }
                        break Label_0140;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final OCStructType ocStructType = this;
                    final List<OCStructSymbol> list = ocStructType.myStructs;
                    final int n = list.size();
                    final int n2 = 1;
                    if (n <= n2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            OCSymbolWithQualifiedName<PsiElement> resolvedOwner = ocStructSymbol;
            boolean b3 = false;
            while (resolvedOwner instanceof OCStructSymbol) {
                final OCStructSymbol ocStructSymbol3 = (OCStructSymbol)resolvedOwner;
                if (!ocStructSymbol3.getTemplateParameters().isEmpty()) {
                    b3 = true;
                    break;
                }
                resolvedOwner = ocStructSymbol3.getResolvedOwner();
            }
            Label_0222: {
                try {
                    if (b3) {
                        break Label_0222;
                    }
                    final OCStructType ocStructType2 = this;
                    final List<OCStructSymbol> list2 = ocStructType2.myStructs;
                    final int n3 = list2.size();
                    final int n4 = 1;
                    if (n3 <= n4) {
                        return false;
                    }
                    break Label_0222;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final OCStructType ocStructType2 = this;
                    final List<OCStructSymbol> list2 = ocStructType2.myStructs;
                    final int n3 = list2.size();
                    final int n4 = 1;
                    if (n3 <= n4) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    final boolean b4;
                    if (!ocStructSymbol.processAllBaseClasses(ocResolveContext, (ocSymbol, p1) -> {
                        try {
                            if (!(ocSymbol instanceof OCTypeParameterSymbol)) {
                                return b4;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        return b4;
                    }, true)) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
        }
        return false;
    }
    
    public boolean hasSeveralSpecializations() {
        int n = 0;
        for (final OCStructSymbol ocStructSymbol : this.myStructs) {
            try {
                if (ocStructSymbol.isPredefinition()) {
                    continue;
                }
                ++n;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        try {
            if (n > 1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    public boolean isScalar() {
        try {
            if (this.getKind() == OCSymbolKind.ENUM) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isCppStructType() {
        Label_0032: {
            try {
                if (this.getKind() != OCSymbolKind.STRUCT) {
                    return false;
                }
                final OCStructType ocStructType = this;
                final OCStructSymbol ocStructSymbol = ocStructType.getSymbol();
                final OCFile ocFile = ocStructSymbol.getContainingOCFile();
                final boolean b = ocFile.isCpp();
                if (b) {
                    break Label_0032;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCStructType ocStructType = this;
                final OCStructSymbol ocStructSymbol = ocStructType.getSymbol();
                final OCFile ocFile = ocStructSymbol.getContainingOCFile();
                final boolean b = ocFile.isCpp();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isNumberCompatible(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //     5: ifne            39
        //     8: aload_0        
        //     9: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    12: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    15: if_acmpne       47
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //    29: ifne            47
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: iconst_1       
        //    40: goto            48
        //    43: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iconst_0       
        //    48: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  8      32     35     39     Ljava/lang/IllegalArgumentException;
        //  25     43     43     47     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    public boolean isIntegerCompatible(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: iload_2        
        //     3: invokespecial   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //     6: ifne            40
        //     9: aload_0        
        //    10: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    13: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    16: if_acmpne       48
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //    30: ifne            48
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: iconst_1       
        //    41: goto            49
        //    44: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iconst_0       
        //    49: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  9      33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     44     44     48     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    public String getDefaultValue(final PsiElement psiElement) {
        if (this.getKind() == OCSymbolKind.ENUM) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            Label_0099: {
                String s = null;
                Label_0064: {
                    try {
                        this.myStructs.get(0).processFields((Processor<OCDeclaratorSymbol>)findFirstProcessor);
                        if (!findFirstProcessor.isFound()) {
                            break Label_0099;
                        }
                        final CommonProcessors.FindFirstProcessor findFirstProcessor2 = findFirstProcessor;
                        final Object o = findFirstProcessor2.getFoundValue();
                        final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)o;
                        s = ocDeclaratorSymbol.getName();
                        if (s == null) {
                            break Label_0064;
                        }
                        return s;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final CommonProcessors.FindFirstProcessor findFirstProcessor2 = findFirstProcessor;
                        final Object o = findFirstProcessor2.getFoundValue();
                        final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)o;
                        s = ocDeclaratorSymbol.getName();
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getDefaultValue"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return s;
            }
        }
        else if (this.getKind() == OCSymbolKind.STRUCT) {
            final OCStructSymbol symbol = this.getSymbol();
            if (this.isCppStructType()) {
                final CommonProcessors.FindFirstProcessor findFirstProcessor3 = new CommonProcessors.FindFirstProcessor();
                final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<Object>((com.intellij.util.Processor<? super Object>)findFirstProcessor3, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { ocFunctionSymbol -> {
                        Label_0024: {
                            try {
                                if (!ocFunctionSymbol.canBeCalledWithoutArguments()) {
                                    return false;
                                }
                                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                                final OCVisibility ocVisibility = ocFunctionSymbol2.getVisibility();
                                final OCVisibility ocVisibility2 = OCVisibility.PUBLIC;
                                if (ocVisibility == ocVisibility2) {
                                    break Label_0024;
                                }
                                return false;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                                final OCVisibility ocVisibility = ocFunctionSymbol2.getVisibility();
                                final OCVisibility ocVisibility2 = OCVisibility.PUBLIC;
                                if (ocVisibility == ocVisibility2) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                        return false;
                    }, ocFunctionSymbol -> {
                        try {
                            if (ocFunctionSymbol.getVisibility() == OCVisibility.PUBLIC) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return false;
                    }, Conditions.alwaysTrue() });
                symbol.processConstructors((Processor<? super OCFunctionSymbol>)orderedProcessor);
                orderedProcessor.finish();
                final OCStructType ocStructType = (OCStructType)this.cloneWithoutCVQualifiers(symbol.getProject());
                final String typedefName = ocStructType.getTypedefName();
                final String aliasName = ocStructType.getAliasName();
                String s2 = null;
                Label_0237: {
                    try {
                        if (typedefName != null) {
                            s2 = typedefName;
                            break Label_0237;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        if (aliasName != null) {
                            s2 = aliasName;
                            break Label_0237;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    s2 = null;
                }
                final StringBuilder sb = new StringBuilder(ocStructType.getBestNameInContext(psiElement, s2));
                sb.append("(");
                if (findFirstProcessor3.isFound()) {
                    int n = 1;
                    for (final OCDeclaratorSymbol ocDeclaratorSymbol2 : ((OCFunctionSymbol)findFirstProcessor3.getFoundValue()).getParameterSymbols()) {
                        try {
                            if (ocDeclaratorSymbol2.hasInitializer()) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        try {
                            if (n == 0) {
                                sb.append(",");
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        OCType ocType = ocDeclaratorSymbol2.getResolvedType();
                        Label_0478: {
                            Label_0466: {
                                Label_0392: {
                                    try {
                                        if (!ocType.isCppStructType()) {
                                            break Label_0466;
                                        }
                                        if (!(ocType instanceof OCCppReferenceType)) {
                                            break Label_0392;
                                        }
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw a(ex7);
                                    }
                                    ocType = ((OCCppReferenceType)ocType).getRefType();
                                }
                                String s3;
                                if (ocType instanceof OCStructType) {
                                    final OCQualifiedName resolvedQualifiedName = ((OCStructType)ocType).getSymbol().getResolvedQualifiedName();
                                    OCQualifiedName qualifiedName = null;
                                    Label_0431: {
                                        try {
                                            if (resolvedQualifiedName != null) {
                                                qualifiedName = resolvedQualifiedName;
                                                break Label_0431;
                                            }
                                        }
                                        catch (IllegalArgumentException ex8) {
                                            throw a(ex8);
                                        }
                                        qualifiedName = symbol.getQualifiedName();
                                    }
                                    s3 = qualifiedName.getNameWithParent();
                                }
                                else {
                                    s3 = ocType.getName();
                                }
                                sb.append(s3).append("()");
                                break Label_0478;
                            }
                            sb.append(ocType.getDefaultValue(psiElement));
                        }
                        n = 0;
                    }
                }
                String string;
                try {
                    sb.append(")");
                    string = sb.toString();
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getDefaultValue"));
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                return string;
            }
        }
        String s4;
        try {
            s4 = "result";
            if (s4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCStructType", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        return s4;
    }
    
    @Nullable
    @Override
    public String getFormatString() {
        try {
            if (this.getKind() == OCSymbolKind.ENUM) {
                return "%d";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.getFormatString();
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        final Ref ref = new Ref((Object)0);
        this.getSymbol().processFields((Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
            Label_0024: {
                try {
                    if (ocDeclaratorSymbol.getKind() != OCSymbolKind.STRUCT_FIELD) {
                        return true;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                    final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                    if (!b) {
                        break Label_0024;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                    final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                    if (!b) {
                        ref.set((Object)((int)ref.get() + ocDeclaratorSymbol.getType().resolve(psiFile).getSizeInBytes(psiFile, ocInclusionContext)));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return true;
        }));
        return (int)ref.get();
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
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: new             Ljava/lang/IllegalStateException;
        //    21: dup            
        //    22: ldc             "@NotNull method %s.%s must not return null"
        //    24: ldc             2
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: ldc             0
        //    32: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    34: aastore        
        //    35: dup            
        //    36: ldc             1
        //    38: ldc             "doGetLeastCommonType"
        //    40: aastore        
        //    41: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    44: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    47: athrow         
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: areturn        
        //    53: aload_0        
        //    54: aload_1        
        //    55: aload_2        
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //    59: ifeq            109
        //    62: aload_0        
        //    63: dup            
        //    64: ifnonnull       108
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: new             Ljava/lang/IllegalStateException;
        //    77: dup            
        //    78: ldc             "@NotNull method %s.%s must not return null"
        //    80: ldc             2
        //    82: anewarray       Ljava/lang/Object;
        //    85: dup            
        //    86: ldc             0
        //    88: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //    90: aastore        
        //    91: dup            
        //    92: ldc             1
        //    94: ldc             "doGetLeastCommonType"
        //    96: aastore        
        //    97: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   100: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   103: athrow         
        //   104: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: areturn        
        //   109: aload_1        
        //   110: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   113: ifeq            163
        //   116: aload_0        
        //   117: dup            
        //   118: ifnonnull       162
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: new             Ljava/lang/IllegalStateException;
        //   131: dup            
        //   132: ldc             "@NotNull method %s.%s must not return null"
        //   134: ldc             2
        //   136: anewarray       Ljava/lang/Object;
        //   139: dup            
        //   140: ldc             0
        //   142: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   144: aastore        
        //   145: dup            
        //   146: ldc             1
        //   148: ldc             "doGetLeastCommonType"
        //   150: aastore        
        //   151: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   154: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   157: athrow         
        //   158: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: areturn        
        //   163: aload_0        
        //   164: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   167: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   170: if_acmpne       237
        //   173: aload_1        
        //   174: aload_2        
        //   175: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   178: ifeq            237
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   191: dup            
        //   192: ifnonnull       236
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: new             Ljava/lang/IllegalStateException;
        //   205: dup            
        //   206: ldc             "@NotNull method %s.%s must not return null"
        //   208: ldc             2
        //   210: anewarray       Ljava/lang/Object;
        //   213: dup            
        //   214: ldc             0
        //   216: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   218: aastore        
        //   219: dup            
        //   220: ldc             1
        //   222: ldc             "doGetLeastCommonType"
        //   224: aastore        
        //   225: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   228: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   231: athrow         
        //   232: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: areturn        
        //   237: aload_0        
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   241: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   244: if_acmpne       330
        //   247: aload_1        
        //   248: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   251: ifeq            330
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: aload_1        
        //   262: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   265: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   268: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   271: if_acmpne       330
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   284: dup            
        //   285: ifnonnull       329
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: new             Ljava/lang/IllegalStateException;
        //   298: dup            
        //   299: ldc             "@NotNull method %s.%s must not return null"
        //   301: ldc             2
        //   303: anewarray       Ljava/lang/Object;
        //   306: dup            
        //   307: ldc             0
        //   309: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   311: aastore        
        //   312: dup            
        //   313: ldc             1
        //   315: ldc             "doGetLeastCommonType"
        //   317: aastore        
        //   318: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   321: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   324: athrow         
        //   325: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: areturn        
        //   330: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   333: dup            
        //   334: ifnonnull       371
        //   337: new             Ljava/lang/IllegalStateException;
        //   340: dup            
        //   341: ldc             "@NotNull method %s.%s must not return null"
        //   343: ldc             2
        //   345: anewarray       Ljava/lang/Object;
        //   348: dup            
        //   349: ldc             0
        //   351: ldc             "com/jetbrains/cidr/lang/types/OCStructType"
        //   353: aastore        
        //   354: dup            
        //   355: ldc             1
        //   357: ldc             "doGetLeastCommonType"
        //   359: aastore        
        //   360: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   363: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   366: athrow         
        //   367: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   370: athrow         
        //   371: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      48     48     52     Ljava/lang/IllegalArgumentException;
        //  53     67     70     74     Ljava/lang/IllegalArgumentException;
        //  62     104    104    108    Ljava/lang/IllegalArgumentException;
        //  109    121    124    128    Ljava/lang/IllegalArgumentException;
        //  116    158    158    162    Ljava/lang/IllegalArgumentException;
        //  163    181    184    188    Ljava/lang/IllegalArgumentException;
        //  173    195    198    202    Ljava/lang/IllegalArgumentException;
        //  188    232    232    236    Ljava/lang/IllegalArgumentException;
        //  237    254    257    261    Ljava/lang/IllegalArgumentException;
        //  247    274    277    281    Ljava/lang/IllegalArgumentException;
        //  261    288    291    295    Ljava/lang/IllegalArgumentException;
        //  281    325    325    329    Ljava/lang/IllegalArgumentException;
        //  330    367    367    371    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0188:
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
    public boolean isInstanceable() {
        return true;
    }
    
    @Override
    public boolean isUnresolved(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType", "isUnresolved"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCTypeArgument ocTypeArgument : this.getSymbol().getTemplateArguments(ocResolveContext)) {
            try {
                if (!(ocTypeArgument instanceof OCType)) {
                    continue;
                }
                final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                final OCType ocType = (OCType)ocTypeArgument2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.isUnresolved(ocResolveContext2);
                if (b) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                final OCType ocType = (OCType)ocTypeArgument2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.isUnresolved(ocResolveContext2);
                if (b) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class InnerProcessor implements Processor<OCSymbol>
    {
        private String myMemberName;
        private final boolean myTypesOnly;
        private final boolean myGoTransitive;
        private final Set<String> myAlreadyProcessed;
        private final OCTypeSubstitution mySubstitution;
        private Set<OCNamespaceLikeSymbol> myProcessed;
        private Stack<Pair<OCNamespaceLikeSymbol, Set<String>>> myWorkset;
        private Processor<OCSymbol> myProcessor;
        private Processor<OCSymbol> myFilteringProcessor;
        private final Condition<OCSymbol> myCondition;
        @NotNull
        private final OCResolveContext myContext;
        
        public InnerProcessor(final String myMemberName, final boolean myTypesOnly, final boolean myGoTransitive, final Set<String> myAlreadyProcessed, final OCTypeSubstitution mySubstitution, final Set<OCNamespaceLikeSymbol> myProcessed, final Stack<Pair<OCNamespaceLikeSymbol, Set<String>>> myWorkset, final Processor<OCSymbol> myProcessor, final Condition<OCSymbol> myCondition, @NotNull final OCResolveContext myContext) {
            if (myContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor", "<init>"));
            }
            this.myMemberName = myMemberName;
            this.myTypesOnly = myTypesOnly;
            this.myGoTransitive = myGoTransitive;
            this.myAlreadyProcessed = myAlreadyProcessed;
            this.mySubstitution = mySubstitution;
            this.myProcessed = myProcessed;
            this.myWorkset = myWorkset;
            this.myProcessor = myProcessor;
            this.myFilteringProcessor = (Processor<OCSymbol>)new FilteringProcessor((Condition)myCondition, (Processor)myProcessor);
            this.myCondition = myCondition;
            this.myContext = myContext;
        }
        
        public boolean process(final OCSymbol p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //     4: ifeq            204
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myProcessed:Ljava/util/Set;
            //    11: aload_1        
            //    12: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
            //    17: ifne            204
            //    20: goto            27
            //    23: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    26: athrow         
            //    27: aload_0        
            //    28: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myProcessed:Ljava/util/Set;
            //    31: aload_1        
            //    32: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
            //    35: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //    40: pop            
            //    41: aload_1        
            //    42: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    45: ifeq            93
            //    48: goto            55
            //    51: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    54: athrow         
            //    55: aload_0        
            //    56: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //    59: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
            //    62: if_acmpeq       93
            //    65: goto            72
            //    68: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    71: athrow         
            //    72: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    75: dup            
            //    76: aload_1        
            //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //    84: iconst_0       
            //    85: aload_0        
            //    86: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //    89: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
            //    92: astore_1       
            //    93: aload_1        
            //    94: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //    97: astore_2       
            //    98: new             Lcom/intellij/openapi/util/Ref;
            //   101: dup            
            //   102: iconst_0       
            //   103: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   106: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
            //   109: astore_3       
            //   110: new             Lcom/intellij/util/FilteringProcessor;
            //   113: dup            
            //   114: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.WITHOUT_QUALIFIER:Lcom/intellij/openapi/util/Condition;
            //   117: aload_0        
            //   118: aload_3        
            //   119: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/OCStructType$InnerProcessor;Lcom/intellij/openapi/util/Ref;)Lcom/intellij/util/Processor;
            //   124: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
            //   127: astore          4
            //   129: aload_2        
            //   130: aload_0        
            //   131: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   134: aload           4
            //   136: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
            //   139: ifne            148
            //   142: iconst_0       
            //   143: ireturn        
            //   144: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   147: athrow         
            //   148: aload_3        
            //   149: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   152: checkcast       Ljava/lang/Boolean;
            //   155: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   158: ifeq            175
            //   161: aload_0        
            //   162: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myGoTransitive:Z
            //   165: ifeq            202
            //   168: goto            175
            //   171: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   174: athrow         
            //   175: aload_0        
            //   176: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myWorkset:Ljava/util/Stack;
            //   179: new             Lcom/intellij/openapi/util/Pair;
            //   182: dup            
            //   183: aload_2        
            //   184: aload_0        
            //   185: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myAlreadyProcessed:Ljava/util/Set;
            //   188: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
            //   191: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
            //   194: pop            
            //   195: goto            202
            //   198: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   201: athrow         
            //   202: iconst_1       
            //   203: ireturn        
            //   204: aload_1        
            //   205: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   208: ifeq            486
            //   211: aload_1        
            //   212: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   217: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTypedefOrAlias:()Z
            //   220: ifeq            486
            //   223: goto            230
            //   226: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   229: athrow         
            //   230: aload_1        
            //   231: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   236: astore_2       
            //   237: aload_2        
            //   238: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
            //   241: ifeq            331
            //   244: aload_0        
            //   245: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
            //   248: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
            //   251: dup            
            //   252: aconst_null    
            //   253: aconst_null    
            //   254: lconst_0       
            //   255: aload_0        
            //   256: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   259: aconst_null    
            //   260: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   263: aconst_null    
            //   264: iconst_0       
            //   265: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Lcom/intellij/openapi/util/TextRange;Z)V
            //   268: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   273: ifne            289
            //   276: goto            283
            //   279: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   282: athrow         
            //   283: iconst_0       
            //   284: ireturn        
            //   285: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   288: athrow         
            //   289: aload_0        
            //   290: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
            //   293: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   296: dup            
            //   297: aconst_null    
            //   298: aconst_null    
            //   299: iconst_0       
            //   300: aconst_null    
            //   301: aload_0        
            //   302: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   305: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   308: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
            //   311: dup            
            //   312: aload_0        
            //   313: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   316: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
            //   319: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   322: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
            //   325: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   330: ireturn        
            //   331: aload_2        
            //   332: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   335: ifeq            437
            //   338: aload_0        
            //   339: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   342: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
            //   345: if_acmpeq       425
            //   348: goto            355
            //   351: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   354: athrow         
            //   355: aload_2        
            //   356: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   359: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   362: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   367: astore_3       
            //   368: aload_3        
            //   369: invokeinterface java/util/Iterator.hasNext:()Z
            //   374: ifeq            422
            //   377: aload_3        
            //   378: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   383: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   386: astore          4
            //   388: aload_0        
            //   389: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   392: dup            
            //   393: aload           4
            //   395: aload_0        
            //   396: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   399: iconst_0       
            //   400: aload_0        
            //   401: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   404: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
            //   407: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //   410: ifne            419
            //   413: iconst_0       
            //   414: ireturn        
            //   415: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   418: athrow         
            //   419: goto            368
            //   422: goto            483
            //   425: aload_2        
            //   426: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   429: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   432: aload_0        
            //   433: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
            //   436: ireturn        
            //   437: aload_2        
            //   438: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   441: ifeq            483
            //   444: aload_0        
            //   445: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   448: aload_2        
            //   449: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   452: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   455: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.substitute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   458: aload_2        
            //   459: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   462: aload_0        
            //   463: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   466: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //   469: iconst_1       
            //   470: iconst_1       
            //   471: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
            //   474: aload_0        
            //   475: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
            //   478: ireturn        
            //   479: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   482: athrow         
            //   483: goto            671
            //   486: aload_1        
            //   487: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //   490: ifeq            601
            //   493: aload_1        
            //   494: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
            //   497: ifeq            518
            //   500: goto            507
            //   503: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   506: athrow         
            //   507: aload_1        
            //   508: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
            //   511: goto            519
            //   514: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   517: athrow         
            //   518: aconst_null    
            //   519: astore_2       
            //   520: aload_0        
            //   521: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
            //   524: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
            //   527: dup            
            //   528: aconst_null    
            //   529: aconst_null    
            //   530: lconst_0       
            //   531: aload_0        
            //   532: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   535: aload_2        
            //   536: aconst_null    
            //   537: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   540: aconst_null    
            //   541: iconst_0       
            //   542: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Lcom/intellij/openapi/util/TextRange;Z)V
            //   545: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   550: ifne            559
            //   553: iconst_0       
            //   554: ireturn        
            //   555: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   558: athrow         
            //   559: aload_0        
            //   560: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
            //   563: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   566: dup            
            //   567: aconst_null    
            //   568: aconst_null    
            //   569: iconst_0       
            //   570: aconst_null    
            //   571: aload_0        
            //   572: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   575: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   578: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
            //   581: dup            
            //   582: aload_0        
            //   583: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
            //   586: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
            //   589: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   592: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
            //   595: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   600: ireturn        
            //   601: aload_1        
            //   602: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //   605: ifeq            636
            //   608: aload_0        
            //   609: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   612: aload_1        
            //   613: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //   616: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //   619: iconst_1       
            //   620: aload_0        
            //   621: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myTypesOnly:Z
            //   624: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
            //   627: aload_0        
            //   628: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
            //   631: ireturn        
            //   632: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   635: athrow         
            //   636: aload_1        
            //   637: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol;
            //   640: ifeq            671
            //   643: aload_0        
            //   644: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   647: aload_1        
            //   648: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol;
            //   651: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol.getNamespaceReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //   654: iconst_1       
            //   655: aload_0        
            //   656: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myTypesOnly:Z
            //   659: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
            //   662: aload_0        
            //   663: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
            //   666: ireturn        
            //   667: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   670: athrow         
            //   671: iconst_1       
            //   672: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      20     23     27     Ljava/lang/IllegalArgumentException;
            //  7      48     51     55     Ljava/lang/IllegalArgumentException;
            //  27     65     68     72     Ljava/lang/IllegalArgumentException;
            //  129    144    144    148    Ljava/lang/IllegalArgumentException;
            //  148    168    171    175    Ljava/lang/IllegalArgumentException;
            //  161    195    198    202    Ljava/lang/IllegalArgumentException;
            //  204    223    226    230    Ljava/lang/IllegalArgumentException;
            //  237    276    279    283    Ljava/lang/IllegalArgumentException;
            //  244    285    285    289    Ljava/lang/IllegalArgumentException;
            //  331    348    351    355    Ljava/lang/IllegalArgumentException;
            //  388    415    415    419    Ljava/lang/IllegalArgumentException;
            //  437    479    479    483    Ljava/lang/IllegalArgumentException;
            //  486    500    503    507    Ljava/lang/IllegalArgumentException;
            //  493    514    514    518    Ljava/lang/IllegalArgumentException;
            //  520    555    555    559    Ljava/lang/IllegalArgumentException;
            //  601    632    632    636    Ljava/lang/IllegalArgumentException;
            //  636    667    667    671    Ljava/lang/IllegalArgumentException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
}
