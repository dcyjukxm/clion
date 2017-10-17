// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.intellij.util.Function;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Map;

public class OCSimpleTypeSubstitution extends OCTypeSubstitution
{
    @NotNull
    private Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutions;
    
    public OCSimpleTypeSubstitution() {
    }
    
    OCSimpleTypeSubstitution(@NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutions) {
        if (mySubstitutions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutions", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "<init>"));
        }
        this.mySubstitutions = mySubstitutions;
    }
    
    @NotNull
    public Map<OCTypeParameterSymbol, OCTypeArgument> getSubstitutions() {
        Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutions;
        try {
            mySubstitutions = this.mySubstitutions;
            if (mySubstitutions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "getSubstitutions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySubstitutions;
    }
    
    public static OCSimpleTypeSubstitution create(@NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> map) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutions", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (map.isEmpty()) {
                return OCSimpleTypeSubstitution.ID;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return new OCSimpleTypeSubstitution((Map<OCTypeParameterSymbol, OCTypeArgument>)new HashMap((Map)map));
    }
    
    @Override
    public boolean hasSubstitutionForName(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "hasSubstitutionForName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final OCTypeParameterSymbol ocTypeParameterSymbol : this.getTypeParameters()) {
            try {
                if (s.equals(ocTypeParameterSymbol.getName())) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public Set<OCTypeParameterSymbol> getTypeParameters() {
        return (Set<OCTypeParameterSymbol>)this.mySubstitutions.keySet();
    }
    
    @Override
    public Collection<OCTypeArgument> getSubstitutedTypes() {
        return this.mySubstitutions.values();
    }
    
    @Override
    public boolean processSubstitutions(final Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>> processor) {
        return ContainerUtil.process((Iterable)this.mySubstitutions.entrySet(), (Processor)processor);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return comparator.equalMaps(((OCSimpleTypeSubstitution)o).mySubstitutions, ((OCSimpleTypeSubstitution)o2).mySubstitutions);
    }
    
    @Override
    public OCType substitute(@NotNull final OCType ocType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (this != OCSimpleTypeSubstitution.ID) {
                return ocType.transformType(new TypeSubstituteVisitor(this, b, ocResolveContext) {
                    @Override
                    public OCType visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                        final OCTypeArgument ocTypeArgument = OCSimpleTypeSubstitution.this.mySubstitutions.get(ocTypeParameterType.getSymbol());
                        if (ocTypeArgument == null) {
                            return ocTypeParameterType;
                        }
                        if (ocTypeArgument instanceof OCType) {
                            OCReferenceType substituteReferenceType = (OCReferenceType)ocTypeArgument;
                            if (ocTypeArgument instanceof OCReferenceType) {
                                substituteReferenceType = OCTypeSubstitution.substituteReferenceType((OCReferenceType)ocTypeArgument, OCSimpleTypeSubstitution.this, b, ocResolveContext);
                            }
                            return substituteReferenceType.cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), ocResolveContext.getProject());
                        }
                        return new OCMagicType(ocTypeArgument.getNameForPresentation(OCType.Presentation.FULL, ocResolveContext, true, 0));
                    }
                });
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return ocType;
    }
    
    public static List<OCTemplateSymbol> resolveTemplateSpecialization(@NotNull final List<OCTemplateSymbol> p0, final List<OCTypeArgument> p1, @NotNull final OCResolveContext p2) {
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
        //    18: ldc             "symbols"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveTemplateSpecialization"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "resolver"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "resolveTemplateSpecialization"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface java/util/List.isEmpty:()Z
        //    94: ifeq            103
        //    97: aload_0        
        //    98: areturn        
        //    99: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: new             Ljava/util/ArrayList;
        //   106: dup            
        //   107: invokespecial   java/util/ArrayList.<init>:()V
        //   110: astore_3       
        //   111: new             Ljava/util/ArrayList;
        //   114: dup            
        //   115: invokespecial   java/util/ArrayList.<init>:()V
        //   118: astore          4
        //   120: new             Ljava/util/ArrayList;
        //   123: dup            
        //   124: invokespecial   java/util/ArrayList.<init>:()V
        //   127: astore          5
        //   129: new             Ljava/util/ArrayList;
        //   132: dup            
        //   133: invokespecial   java/util/ArrayList.<init>:()V
        //   136: astore          6
        //   138: aload_0        
        //   139: aload_3        
        //   140: aload           4
        //   142: aload           5
        //   144: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.divideSymbols:(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V
        //   147: aload_1        
        //   148: aload_2        
        //   149: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.expandVariadicTypes:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   152: astore_1       
        //   153: aload_1        
        //   154: aload_2        
        //   155: invokedynamic   fun:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/intellij/util/Function;
        //   160: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   163: astore          7
        //   165: aload           7
        //   167: aload_2        
        //   168: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   171: istore          8
        //   173: aload_0        
        //   174: invokeinterface java/util/List.size:()I
        //   179: iconst_1       
        //   180: if_icmpne       282
        //   183: aload_0        
        //   184: iconst_0       
        //   185: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   190: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   193: astore          9
        //   195: new             Lcom/intellij/openapi/util/Ref;
        //   198: dup            
        //   199: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   202: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   205: astore          10
        //   207: aload           9
        //   209: aload           5
        //   211: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   214: ifeq            226
        //   217: aload           9
        //   219: goto            227
        //   222: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aconst_null    
        //   227: aconst_null    
        //   228: aload           7
        //   230: aload_2        
        //   231: aload           10
        //   233: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   236: astore          11
        //   238: aload           5
        //   240: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   243: ifne            264
        //   246: aload           10
        //   248: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   251: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   254: if_acmpeq       279
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload           6
        //   266: aload           11
        //   268: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   271: pop            
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: aload           6
        //   281: areturn        
        //   282: aload           5
        //   284: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   287: ifne            645
        //   290: aconst_null    
        //   291: astore          9
        //   293: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   296: astore          10
        //   298: aload           5
        //   300: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   303: astore          11
        //   305: aload           11
        //   307: invokeinterface java/util/Iterator.hasNext:()Z
        //   312: ifeq            598
        //   315: aload           11
        //   317: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   322: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   325: astore          12
        //   327: new             Lcom/intellij/openapi/util/Ref;
        //   330: dup            
        //   331: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   334: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   337: astore          13
        //   339: aconst_null    
        //   340: astore          14
        //   342: aload           4
        //   344: invokevirtual   java/util/ArrayList.size:()I
        //   347: iconst_1       
        //   348: if_icmpne       365
        //   351: aload           4
        //   353: iconst_0       
        //   354: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   357: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   360: astore          14
        //   362: goto            383
        //   365: aload_3        
        //   366: invokevirtual   java/util/ArrayList.size:()I
        //   369: iconst_1       
        //   370: if_icmpne       383
        //   373: aload_3        
        //   374: iconst_0       
        //   375: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   378: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   381: astore          14
        //   383: aload           12
        //   385: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   388: ifeq            409
        //   391: aload           14
        //   393: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   396: ifne            409
        //   399: goto            406
        //   402: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   405: athrow         
        //   406: aconst_null    
        //   407: astore          14
        //   409: aload           12
        //   411: aload           14
        //   413: aconst_null    
        //   414: aload           7
        //   416: aload_2        
        //   417: aload           13
        //   419: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   422: astore          15
        //   424: iload           8
        //   426: ifne            569
        //   429: aload           12
        //   431: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   434: ifeq            536
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: aload           13
        //   446: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   449: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   452: aload           10
        //   454: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isBetter:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Z
        //   457: ifeq            487
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: aload           6
        //   469: invokevirtual   java/util/ArrayList.clear:()V
        //   472: aload           6
        //   474: aload           12
        //   476: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   479: pop            
        //   480: goto            536
        //   483: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload           10
        //   489: aload           13
        //   491: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   494: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   497: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isBetter:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Z
        //   500: ifne            536
        //   503: aload           13
        //   505: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   508: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   511: if_acmpeq       536
        //   514: goto            521
        //   517: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   520: athrow         
        //   521: aload           6
        //   523: aload           12
        //   525: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   528: pop            
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   535: athrow         
        //   536: aload           13
        //   538: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   541: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   544: aload           10
        //   546: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isBetter:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Z
        //   549: ifeq            595
        //   552: aload           15
        //   554: astore          9
        //   556: aload           13
        //   558: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   561: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   564: astore          10
        //   566: goto            595
        //   569: aload           13
        //   571: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   574: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   577: if_acmpeq       595
        //   580: aload           6
        //   582: aload           15
        //   584: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   587: pop            
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: goto            305
        //   598: iload           8
        //   600: ifne            645
        //   603: aload           9
        //   605: ifnull          645
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: aload           9
        //   617: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   620: ifne            645
        //   623: goto            630
        //   626: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: aload           6
        //   632: aload           9
        //   634: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   637: pop            
        //   638: goto            645
        //   641: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   644: athrow         
        //   645: aload           6
        //   647: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   650: ifne            695
        //   653: iload           8
        //   655: ifne            695
        //   658: goto            665
        //   661: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   664: athrow         
        //   665: aload_2        
        //   666: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isIncompleteMode:()Z
        //   669: ifeq            800
        //   672: goto            679
        //   675: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   678: athrow         
        //   679: aload_1        
        //   680: invokeinterface java/util/List.isEmpty:()Z
        //   685: ifeq            800
        //   688: goto            695
        //   691: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   694: athrow         
        //   695: aload           4
        //   697: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   700: astore          9
        //   702: aload           9
        //   704: invokeinterface java/util/Iterator.hasNext:()Z
        //   709: ifeq            800
        //   712: aload           9
        //   714: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   719: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   722: astore          10
        //   724: new             Lcom/intellij/openapi/util/Ref;
        //   727: dup            
        //   728: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   731: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   734: astore          11
        //   736: aload           10
        //   738: aload           10
        //   740: aconst_null    
        //   741: aload           7
        //   743: aload_2        
        //   744: aload           11
        //   746: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   749: astore          12
        //   751: aload           11
        //   753: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   756: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   759: if_acmpeq       797
        //   762: iload           8
        //   764: ifeq            789
        //   767: goto            774
        //   770: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   773: athrow         
        //   774: aload           6
        //   776: iconst_0       
        //   777: aload           12
        //   779: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
        //   782: goto            797
        //   785: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   788: athrow         
        //   789: aload           6
        //   791: aload           12
        //   793: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   796: pop            
        //   797: goto            702
        //   800: aload           6
        //   802: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   805: ifeq            969
        //   808: aload_3        
        //   809: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   812: astore          9
        //   814: aload           9
        //   816: invokeinterface java/util/Iterator.hasNext:()Z
        //   821: ifeq            969
        //   824: aload           9
        //   826: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   831: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   834: astore          10
        //   836: new             Lcom/intellij/openapi/util/Ref;
        //   839: dup            
        //   840: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   843: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   846: astore          11
        //   848: aload           10
        //   850: aload           10
        //   852: aconst_null    
        //   853: aload           7
        //   855: aload_2        
        //   856: aload           11
        //   858: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   861: astore          12
        //   863: aload           11
        //   865: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   868: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   871: if_acmpeq       966
        //   874: aload           4
        //   876: invokevirtual   java/util/ArrayList.size:()I
        //   879: iconst_1       
        //   880: if_icmpne       958
        //   883: goto            890
        //   886: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: new             Lcom/intellij/openapi/util/Ref;
        //   893: dup            
        //   894: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   897: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   900: astore          11
        //   902: aload           4
        //   904: iconst_0       
        //   905: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   908: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   911: aload           4
        //   913: iconst_0       
        //   914: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   917: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   920: aload           12
        //   922: aload           7
        //   924: aload_2        
        //   925: aload           11
        //   927: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   930: astore          12
        //   932: aload           11
        //   934: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   937: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   940: if_acmpeq       966
        //   943: aload           6
        //   945: aload           12
        //   947: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   950: pop            
        //   951: goto            966
        //   954: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   957: athrow         
        //   958: aload           6
        //   960: aload           12
        //   962: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   965: pop            
        //   966: goto            814
        //   969: aload           6
        //   971: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     99     99     103    Ljava/lang/IllegalArgumentException;
        //  207    222    222    226    Ljava/lang/IllegalArgumentException;
        //  238    257    260    264    Ljava/lang/IllegalArgumentException;
        //  246    272    275    279    Ljava/lang/IllegalArgumentException;
        //  383    399    402    406    Ljava/lang/IllegalArgumentException;
        //  424    437    440    444    Ljava/lang/IllegalArgumentException;
        //  429    460    463    467    Ljava/lang/IllegalArgumentException;
        //  444    483    483    487    Ljava/lang/IllegalArgumentException;
        //  487    514    517    521    Ljava/lang/IllegalArgumentException;
        //  503    529    532    536    Ljava/lang/IllegalArgumentException;
        //  569    588    591    595    Ljava/lang/IllegalArgumentException;
        //  598    608    611    615    Ljava/lang/IllegalArgumentException;
        //  603    623    626    630    Ljava/lang/IllegalArgumentException;
        //  615    638    641    645    Ljava/lang/IllegalArgumentException;
        //  645    658    661    665    Ljava/lang/IllegalArgumentException;
        //  653    672    675    679    Ljava/lang/IllegalArgumentException;
        //  665    688    691    695    Ljava/lang/IllegalArgumentException;
        //  751    767    770    774    Ljava/lang/IllegalArgumentException;
        //  762    785    785    789    Ljava/lang/IllegalArgumentException;
        //  863    883    886    890    Ljava/lang/IllegalArgumentException;
        //  932    954    954    958    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0444:
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
    
    protected static void divideSymbols(@NotNull final List<OCTemplateSymbol> list, @NotNull final List<OCTemplateSymbol> list2, @NotNull final List<OCTemplateSymbol> list3, @NotNull final List<OCTemplateSymbol> list4) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "divideSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "predefinitions", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "divideSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nonSpecialized", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "divideSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "specialized", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "divideSymbols"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        for (final OCTemplateSymbol ocTemplateSymbol : list) {
            Label_0247: {
                Label_0231: {
                    try {
                        if (!(ocTemplateSymbol instanceof OCStructSymbol)) {
                            break Label_0247;
                        }
                        final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                        final boolean b = ocTemplateSymbol2.isPredeclaration();
                        if (b) {
                            break Label_0231;
                        }
                        break Label_0247;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final OCTemplateSymbol ocTemplateSymbol2 = ocTemplateSymbol;
                        final boolean b = ocTemplateSymbol2.isPredeclaration();
                        if (b) {
                            list2.add(ocTemplateSymbol);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                try {
                    if (ocTemplateSymbol.getTemplateSpecialization() != null) {
                        list4.add(ocTemplateSymbol);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            list3.add(ocTemplateSymbol);
        }
    }
    
    private static boolean a(@NotNull final List<OCTypeArgument> p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "arguments"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isDependentType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "resolver"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isDependentType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_0       
        //    89: istore_2       
        //    90: aload_0        
        //    91: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    96: astore_3       
        //    97: aload_3        
        //    98: invokeinterface java/util/Iterator.hasNext:()Z
        //   103: ifeq            285
        //   106: aload_3        
        //   107: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   112: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   115: astore          4
        //   117: aload           4
        //   119: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   122: ifeq            135
        //   125: aload           4
        //   127: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   130: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   133: astore          4
        //   135: aload           4
        //   137: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   140: ifeq            172
        //   143: aload           4
        //   145: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   148: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   151: invokeinterface java/util/List.size:()I
        //   156: iconst_1       
        //   157: if_icmple       282
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: iconst_1       
        //   168: istore_2       
        //   169: goto            282
        //   172: aload           4
        //   174: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   177: ifne            253
        //   180: aload           4
        //   182: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   185: ifeq            216
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload           4
        //   197: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   203: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   206: ifne            253
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload           4
        //   218: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   221: ifeq            282
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           4
        //   233: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   239: aload_1        
        //   240: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   243: ifnonnull       282
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: iconst_1       
        //   254: istore_2       
        //   255: aload           4
        //   257: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   260: ifeq            282
        //   263: aload_1        
        //   264: aload           4
        //   266: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   269: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   272: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.addTypeDependency:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)V
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: goto            97
        //   285: iload_2        
        //   286: ireturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  135    160    163    167    Ljava/lang/IllegalArgumentException;
        //  172    188    191    195    Ljava/lang/IllegalArgumentException;
        //  180    209    212    216    Ljava/lang/IllegalArgumentException;
        //  195    224    227    231    Ljava/lang/IllegalArgumentException;
        //  216    246    249    253    Ljava/lang/IllegalArgumentException;
        //  255    275    278    282    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0195:
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
    public OCTypeArgument getSubstitutionFor(@NotNull final OCTypeParameterSymbol ocTypeParameterSymbol) {
        try {
            if (ocTypeParameterSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "param", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "getSubstitutionFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.mySubstitutions.get(ocTypeParameterSymbol);
    }
    
    private static OCTemplateSymbol a(@NotNull final OCTemplateSymbol<?> p0, @Nullable final OCTemplateSymbol<?> p1, @Nullable final OCTemplateSymbol<?> p2, @NotNull final List<OCTypeArgument> p3, @NotNull final OCResolveContext p4, @NotNull final Ref<OCTypeUnificationVisitor.UnificationResult> p5) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "applyArgumentsToTemplateSymbol"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "resolvedArguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "applyArgumentsToTemplateSymbol"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "resolver"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "applyArgumentsToTemplateSymbol"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           5
        //   135: ifnonnull       178
        //   138: new             Ljava/lang/IllegalArgumentException;
        //   141: dup            
        //   142: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   144: ldc             3
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "specializationCount"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "applyArgumentsToTemplateSymbol"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypeParameterMap:()Ljava/util/Map;
        //   181: astore          6
        //   183: aload_0        
        //   184: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateSpecialization:()Ljava/util/List;
        //   189: astore          7
        //   191: aload_3        
        //   192: invokeinterface java/util/List.size:()I
        //   197: istore          8
        //   199: iconst_0       
        //   200: istore          9
        //   202: iload           8
        //   204: ifle            230
        //   207: aload_3        
        //   208: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   211: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   214: ifeq            230
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: iinc            8, -1
        //   227: iconst_1       
        //   228: istore          9
        //   230: aload           7
        //   232: ifnull          254
        //   235: aload           7
        //   237: invokeinterface java/util/List.size:()I
        //   242: iload           8
        //   244: if_icmpge       334
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_0        
        //   255: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isVariadicTemplate:()Z
        //   260: ifne            334
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aload_1        
        //   271: ifnull          320
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload_1        
        //   282: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   287: invokeinterface java/util/List.size:()I
        //   292: iload           8
        //   294: if_icmplt       320
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload_1        
        //   305: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isVariadicTemplate:()Z
        //   310: ifeq            334
        //   313: goto            320
        //   316: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: aload           5
        //   322: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   325: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   328: aload_0        
        //   329: areturn        
        //   330: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aload           7
        //   336: ifnull          388
        //   339: aload           4
        //   341: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.clearSubstitution:()Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   344: astore          10
        //   346: aload           10
        //   348: iconst_1       
        //   349: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setDontExpandVariadics:(Z)V
        //   352: aload           7
        //   354: aload_3        
        //   355: aload           10
        //   357: aload           6
        //   359: invokedynamic   fun:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;)Lcom/intellij/util/Function;
        //   364: iconst_0       
        //   365: aload           6
        //   367: aload           4
        //   369: aload           5
        //   371: invokedynamic   process:(Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;
        //   376: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.processArguments:(Ljava/util/List;Ljava/util/List;Lcom/intellij/util/Function;ZLcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;)Z
        //   379: ifne            388
        //   382: aload_0        
        //   383: areturn        
        //   384: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: aload_1        
        //   389: ifnull          1114
        //   392: aload_1        
        //   393: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   398: astore          10
        //   400: iconst_0       
        //   401: istore          11
        //   403: iload           11
        //   405: aload           10
        //   407: invokeinterface java/util/List.size:()I
        //   412: if_icmpge       833
        //   415: aload           10
        //   417: iload           11
        //   419: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   424: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   427: astore          12
        //   429: aload           12
        //   431: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getDefaultValue:()Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   436: astore          13
        //   438: new             Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   441: dup            
        //   442: aload           12
        //   444: invokespecial   com/jetbrains/cidr/lang/types/OCTypeParameterType.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)V
        //   447: astore          14
        //   449: aload           7
        //   451: ifnull          514
        //   454: iload           11
        //   456: aload           7
        //   458: invokeinterface java/util/List.size:()I
        //   463: if_icmplt       514
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: aload           7
        //   475: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   478: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   481: ifne            514
        //   484: goto            491
        //   487: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   490: athrow         
        //   491: aload           13
        //   493: ifnull          514
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload           4
        //   505: aload           6
        //   507: aload           13
        //   509: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.resolveTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   512: astore          14
        //   514: aload           12
        //   516: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   521: ifeq            531
        //   524: goto            833
        //   527: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   530: athrow         
        //   531: iload           11
        //   533: iload           8
        //   535: if_icmpge       561
        //   538: aload           4
        //   540: aload           6
        //   542: aload_3        
        //   543: iload           11
        //   545: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   550: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   553: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.resolveTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   556: astore          15
        //   558: goto            796
        //   561: aload_2        
        //   562: ifnull          638
        //   565: aload_2        
        //   566: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   571: astore          16
        //   573: aload           16
        //   575: invokeinterface java/util/List.size:()I
        //   580: iload           11
        //   582: if_icmple       613
        //   585: aload_2        
        //   586: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   591: aload           16
        //   593: iload           11
        //   595: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   600: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   603: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   606: goto            614
        //   609: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aconst_null    
        //   614: astore          15
        //   616: aload           15
        //   618: ifnonnull       635
        //   621: aload           5
        //   623: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   626: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   629: aload_0        
        //   630: areturn        
        //   631: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   634: athrow         
        //   635: goto            796
        //   638: aload           13
        //   640: ifnonnull       683
        //   643: aload_1        
        //   644: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   647: ifeq            833
        //   650: goto            657
        //   653: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   656: athrow         
        //   657: iload           9
        //   659: ifne            833
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: aload           5
        //   671: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   674: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   677: aload_0        
        //   678: areturn        
        //   679: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   682: athrow         
        //   683: aload           4
        //   685: aload           6
        //   687: aload           13
        //   689: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.resolveTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   692: astore          15
        //   694: aload           7
        //   696: ifnull          796
        //   699: iload           11
        //   701: aload           7
        //   703: invokeinterface java/util/List.size:()I
        //   708: if_icmpge       796
        //   711: goto            718
        //   714: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   717: athrow         
        //   718: aload           4
        //   720: aload           6
        //   722: aload           7
        //   724: iload           11
        //   726: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   731: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   734: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.resolveTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   737: astore          16
        //   739: aload           16
        //   741: aload           15
        //   743: aload           6
        //   745: aload           4
        //   747: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   750: astore          17
        //   752: aload           5
        //   754: aload           5
        //   756: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   759: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   762: aload           17
        //   764: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   767: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   770: aload           16
        //   772: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   775: ifeq            796
        //   778: aload           5
        //   780: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   783: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   786: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.incNumOfNonSpecializedArgs:()V
        //   789: goto            796
        //   792: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   795: athrow         
        //   796: aload           14
        //   798: aload           15
        //   800: aload           6
        //   802: aload           4
        //   804: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   807: astore          16
        //   809: aload           5
        //   811: aload           5
        //   813: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   816: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   819: aload           16
        //   821: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   824: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   827: iinc            11, 1
        //   830: goto            403
        //   833: aload_1        
        //   834: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isVariadicTemplate:()Z
        //   839: ifeq            1114
        //   842: aload           10
        //   844: invokeinterface java/util/List.size:()I
        //   849: iconst_1       
        //   850: isub           
        //   851: istore          11
        //   853: iload           11
        //   855: ifle            897
        //   858: aload           10
        //   860: iload           11
        //   862: iconst_1       
        //   863: isub           
        //   864: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   869: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   872: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   877: ifeq            897
        //   880: goto            887
        //   883: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   886: athrow         
        //   887: iinc            11, -1
        //   890: goto            853
        //   893: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   896: athrow         
        //   897: aload           10
        //   899: iload           11
        //   901: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   906: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   909: astore          12
        //   911: aload_0        
        //   912: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   915: ifne            942
        //   918: aload           6
        //   920: aload           12
        //   922: new             Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   925: dup            
        //   926: invokespecial   com/jetbrains/cidr/lang/types/OCExpansionPackType.<init>:()V
        //   929: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   934: pop            
        //   935: goto            942
        //   938: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   941: athrow         
        //   942: new             Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   945: dup            
        //   946: aload           12
        //   948: invokespecial   com/jetbrains/cidr/lang/types/OCTypeParameterType.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)V
        //   951: astore          13
        //   953: iload           11
        //   955: istore          14
        //   957: iload           14
        //   959: aload_3        
        //   960: invokeinterface java/util/List.size:()I
        //   965: if_icmpge       1025
        //   968: aload           4
        //   970: aload           6
        //   972: aload_3        
        //   973: iload           14
        //   975: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   980: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   983: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.resolveTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   986: astore          15
        //   988: aload           13
        //   990: aload           15
        //   992: aload           6
        //   994: aload           4
        //   996: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   999: astore          16
        //  1001: aload           5
        //  1003: aload           5
        //  1005: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //  1008: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //  1011: aload           16
        //  1013: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //  1016: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //  1019: iinc            14, 1
        //  1022: goto            957
        //  1025: aload           7
        //  1027: ifnull          1114
        //  1030: aload           7
        //  1032: invokeinterface java/util/List.size:()I
        //  1037: istore          14
        //  1039: aload           7
        //  1041: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //  1044: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //  1047: astore          15
        //  1049: aload           15
        //  1051: ifnull          1081
        //  1054: aload           15
        //  1056: invokeinterface com/jetbrains/cidr/lang/types/OCTypeArgument.isVariadic:()Z
        //  1061: ifeq            1081
        //  1064: goto            1071
        //  1067: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1070: athrow         
        //  1071: iinc            14, -1
        //  1074: goto            1081
        //  1077: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1080: athrow         
        //  1081: iload           8
        //  1083: iload           14
        //  1085: if_icmpge       1114
        //  1088: iload           9
        //  1090: ifne            1114
        //  1093: goto            1100
        //  1096: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1099: athrow         
        //  1100: aload           5
        //  1102: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //  1105: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //  1108: aload_0        
        //  1109: areturn        
        //  1110: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1113: athrow         
        //  1114: aload           4
        //  1116: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //  1119: aload_3        
        //  1120: aload           4
        //  1122: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getMinimalDependentSubstitution:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //  1125: astore          10
        //  1127: aload           6
        //  1129: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.create:(Ljava/util/Map;)Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //  1132: aload           10
        //  1134: iconst_0       
        //  1135: aload           4
        //  1137: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.compose:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //  1140: aload_0        
        //  1141: aconst_null    
        //  1142: iconst_1       
        //  1143: aload           4
        //  1145: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1148: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1151: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol<*>;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol<*>;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol<*>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/openapi/util/Ref<Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;>;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    174    174    178    Ljava/lang/IllegalArgumentException;
        //  202    217    220    224    Ljava/lang/IllegalArgumentException;
        //  230    247    250    254    Ljava/lang/IllegalArgumentException;
        //  235    263    266    270    Ljava/lang/IllegalArgumentException;
        //  254    274    277    281    Ljava/lang/IllegalArgumentException;
        //  270    297    300    304    Ljava/lang/IllegalArgumentException;
        //  281    313    316    320    Ljava/lang/IllegalArgumentException;
        //  304    330    330    334    Ljava/lang/IllegalArgumentException;
        //  346    384    384    388    Ljava/lang/IllegalArgumentException;
        //  449    466    469    473    Ljava/lang/IllegalArgumentException;
        //  454    484    487    491    Ljava/lang/IllegalArgumentException;
        //  473    496    499    503    Ljava/lang/IllegalArgumentException;
        //  514    527    527    531    Ljava/lang/IllegalArgumentException;
        //  573    609    609    613    Ljava/lang/IllegalArgumentException;
        //  616    631    631    635    Ljava/lang/IllegalArgumentException;
        //  638    650    653    657    Ljava/lang/IllegalArgumentException;
        //  643    662    665    669    Ljava/lang/IllegalArgumentException;
        //  657    679    679    683    Ljava/lang/IllegalArgumentException;
        //  694    711    714    718    Ljava/lang/IllegalArgumentException;
        //  752    789    792    796    Ljava/lang/IllegalArgumentException;
        //  853    880    883    887    Ljava/lang/IllegalArgumentException;
        //  858    893    893    897    Ljava/lang/IllegalArgumentException;
        //  911    935    938    942    Ljava/lang/IllegalArgumentException;
        //  1049   1064   1067   1071   Ljava/lang/IllegalArgumentException;
        //  1054   1074   1077   1081   Ljava/lang/IllegalArgumentException;
        //  1081   1093   1096   1100   Ljava/lang/IllegalArgumentException;
        //  1088   1110   1110   1114   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0254:
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
    
    private static boolean a(@NotNull final OCTypeArgument ocTypeArgument, @NotNull final OCTypeArgument ocTypeArgument2, @NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> map, @NotNull OCResolveContext substitute) {
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "specializationParam", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "incompatibleArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocTypeArgument2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "incompatibleArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutionMap", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "incompatibleArgument"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (substitute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "incompatibleArgument"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        substitute = substitute.substitute(create(map));
        try {
            if (!(ocTypeArgument instanceof OCReferenceType) || !(ocTypeArgument2 instanceof OCStructType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final OCReferenceType ocReferenceType = (OCReferenceType)ocTypeArgument;
        final OCStructType ocStructType = (OCStructType)ocTypeArgument2;
        final List<OCSymbol> resolveToSymbols = ocReferenceType.getReference(substitute).resolveToSymbols(true, true, false, substitute);
        boolean b = false;
        Label_0252: {
            try {
                if (ContainerUtil.findInstance((Iterable)resolveToSymbols, (Class)OCStructSymbol.class) != null) {
                    b = true;
                    break Label_0252;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            b = false;
        }
        final boolean b2 = b;
        try {
            if (!b2) {
                return false;
            }
            final List<OCSymbol> list = resolveToSymbols;
            final OCStructType ocStructType2 = ocStructType;
            final OCStructSymbol ocStructSymbol = ocStructType2.getSymbol();
            final boolean b3 = list.contains(ocStructSymbol);
            if (!b3) {
                return true;
            }
            return false;
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            final List<OCSymbol> list = resolveToSymbols;
            final OCStructType ocStructType2 = ocStructType;
            final OCStructSymbol ocStructSymbol = ocStructType2.getSymbol();
            final boolean b3 = list.contains(ocStructSymbol);
            if (!b3) {
                return true;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        return false;
    }
    
    @NotNull
    protected static OCTypeArgument resolveTypeArgument(@NotNull final OCResolveContext ocResolveContext, final Map<OCTypeParameterSymbol, OCTypeArgument> map, @NotNull final OCTypeArgument ocTypeArgument) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "resolveTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "resolveTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCTypeArgument resolveTypeArgument;
        try {
            resolveTypeArgument = resolveTypeArgument(ocTypeArgument, ocResolveContext.useFor(ocTypeArgument, create(map)));
            if (resolveTypeArgument == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "resolveTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return resolveTypeArgument;
    }
    
    @NotNull
    public static OCTypeArgument resolveTypeArgument(@NotNull final OCTypeArgument p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "argument"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveTypeArgument"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "resolveTypeArgument"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    92: ifeq            315
        //    95: aconst_null    
        //    96: astore_2       
        //    97: aload_1        
        //    98: aload_0        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   102: aload_1        
        //   103: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   106: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/util/List;
        //   109: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   114: astore_3       
        //   115: aload_3        
        //   116: invokeinterface java/util/Iterator.hasNext:()Z
        //   121: ifeq            244
        //   124: aload_3        
        //   125: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   130: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   133: astore          4
        //   135: aload           4
        //   137: aload_1        
        //   138: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Object;
        //   141: astore          5
        //   143: aload           5
        //   145: ifnonnull       153
        //   148: aconst_null    
        //   149: astore_2       
        //   150: goto            244
        //   153: aload_2        
        //   154: ifnull          238
        //   157: aload           5
        //   159: aload_2        
        //   160: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   163: ifne            238
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: new             Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   176: dup            
        //   177: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCUnknownExpressionSymbol;
        //   180: dup            
        //   181: aconst_null    
        //   182: aconst_null    
        //   183: lconst_0       
        //   184: ldc             ""
        //   186: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCUnknownExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;)V
        //   189: invokespecial   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.<init>:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   192: dup            
        //   193: ifnonnull       237
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: new             Ljava/lang/IllegalStateException;
        //   206: dup            
        //   207: ldc             "@NotNull method %s.%s must not return null"
        //   209: ldc             2
        //   211: anewarray       Ljava/lang/Object;
        //   214: dup            
        //   215: ldc             0
        //   217: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   219: aastore        
        //   220: dup            
        //   221: ldc             1
        //   223: ldc             "resolveTypeArgument"
        //   225: aastore        
        //   226: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   229: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   232: athrow         
        //   233: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: areturn        
        //   238: aload           5
        //   240: astore_2       
        //   241: goto            115
        //   244: aload_2        
        //   245: ifnull          315
        //   248: new             Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   251: dup            
        //   252: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol;
        //   255: dup            
        //   256: aload_2        
        //   257: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   260: aload_2        
        //   261: aconst_null    
        //   262: aconst_null    
        //   263: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol.<init>:(Ljava/lang/String;Ljava/lang/Object;Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   266: invokespecial   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.<init>:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   269: dup            
        //   270: ifnonnull       314
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: new             Ljava/lang/IllegalStateException;
        //   283: dup            
        //   284: ldc             "@NotNull method %s.%s must not return null"
        //   286: ldc             2
        //   288: anewarray       Ljava/lang/Object;
        //   291: dup            
        //   292: ldc             0
        //   294: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   296: aastore        
        //   297: dup            
        //   298: ldc             1
        //   300: ldc             "resolveTypeArgument"
        //   302: aastore        
        //   303: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   306: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   309: athrow         
        //   310: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: areturn        
        //   315: aload_0        
        //   316: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   319: ifeq            341
        //   322: aload_0        
        //   323: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   326: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
        //   329: dup            
        //   330: aload_1        
        //   331: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   334: invokevirtual   com/jetbrains/cidr/lang/types/OCType.transformType:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   337: astore_0       
        //   338: goto            413
        //   341: aload_0        
        //   342: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   345: ifeq            413
        //   348: aload_0        
        //   349: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   352: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   355: astore_2       
        //   356: aload_2        
        //   357: aload_1        
        //   358: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.evaluateToTypeArgument:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   361: astore_3       
        //   362: aload_3        
        //   363: ifnull          413
        //   366: aload_3        
        //   367: dup            
        //   368: ifnonnull       412
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: new             Ljava/lang/IllegalStateException;
        //   381: dup            
        //   382: ldc             "@NotNull method %s.%s must not return null"
        //   384: ldc             2
        //   386: anewarray       Ljava/lang/Object;
        //   389: dup            
        //   390: ldc             0
        //   392: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   394: aastore        
        //   395: dup            
        //   396: ldc             1
        //   398: ldc             "resolveTypeArgument"
        //   400: aastore        
        //   401: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   404: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   407: athrow         
        //   408: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   411: athrow         
        //   412: areturn        
        //   413: aload_0        
        //   414: dup            
        //   415: ifnonnull       452
        //   418: new             Ljava/lang/IllegalStateException;
        //   421: dup            
        //   422: ldc             "@NotNull method %s.%s must not return null"
        //   424: ldc             2
        //   426: anewarray       Ljava/lang/Object;
        //   429: dup            
        //   430: ldc             0
        //   432: ldc             "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution"
        //   434: aastore        
        //   435: dup            
        //   436: ldc             1
        //   438: ldc             "resolveTypeArgument"
        //   440: aastore        
        //   441: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   444: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   447: athrow         
        //   448: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  153    166    169    173    Ljava/lang/IllegalArgumentException;
        //  157    196    199    203    Ljava/lang/IllegalArgumentException;
        //  173    233    233    237    Ljava/lang/IllegalArgumentException;
        //  244    273    276    280    Ljava/lang/IllegalArgumentException;
        //  248    310    310    314    Ljava/lang/IllegalArgumentException;
        //  362    371    374    378    Ljava/lang/IllegalArgumentException;
        //  366    408    408    412    Ljava/lang/IllegalArgumentException;
        //  413    448    448    452    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
    
    public static OCTypeUnificationVisitor.UnificationResult unify(@NotNull final OCTypeArgument ocTypeArgument, @NotNull final OCTypeArgument ocTypeArgument2, @NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> map, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameter", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocTypeArgument2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutionMap", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return unify(ocTypeArgument, ocTypeArgument2, null, map, null, false, false, ocResolveContext);
    }
    
    public static OCTypeUnificationVisitor.UnificationResult unify(@NotNull final OCTypeArgument ocTypeArgument, @NotNull final OCTypeArgument ocTypeArgument2, @Nullable final OCTypeOwner ocTypeOwner, @NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> map, @Nullable final Set<OCTypeParameterSymbol> set, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameter", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocTypeArgument2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutionMap", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "unify"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return new OCTypeUnificationVisitor(b, true, b2, true, false, ocTypeArgument2, ocTypeOwner, map, set, ocResolveContext).unify(ocTypeArgument, ocTypeArgument2);
    }
    
    @NotNull
    String substList() {
        final Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutions = this.mySubstitutions;
        final Function function = entry -> {
            final String string = entry.getKey().toString();
            final String substring = string.substring(string.indexOf(91));
            final OCTypeArgument ocTypeArgument = (OCTypeArgument)entry.getValue();
            String s;
            if (ocTypeArgument instanceof OCTypeParameterType) {
                final String string2 = ((OCTypeParameterType)ocTypeArgument).getSymbol().toString();
                s = "<param>" + string2.substring(string2.indexOf(91));
            }
            else if (ocTypeArgument instanceof OCExpressionTypeArgument) {
                final String string3 = ((OCExpressionTypeArgument)ocTypeArgument).getSymbol().toString();
                s = "<value>" + string3.substring(string3.indexOf(91));
            }
            else {
                s = ocTypeArgument.toString();
            }
            return substring + " -> " + s;
        };
        String join;
        try {
            join = StringUtil.join((Collection)ContainerUtil.map((Collection)mySubstitutions.entrySet(), function), ", ");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution", "substList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return join;
    }
    
    @Override
    public String toString() {
        return "{" + this.substList() + "}";
    }
    
    @Override
    public int hashCode() {
        return this.mySubstitutions.hashCode();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
