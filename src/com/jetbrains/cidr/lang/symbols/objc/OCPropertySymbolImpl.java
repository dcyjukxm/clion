// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.EnumMap;
import org.jetbrains.annotations.Nullable;
import java.util.EnumSet;
import com.jetbrains.cidr.lang.types.OCType;

public class OCPropertySymbolImpl extends OCMemberSymbolImpl implements OCPropertySymbol
{
    private OCType myType;
    @Nullable
    private EnumSet<PropertyAttribute> myAttributesSet;
    private EnumMap<PropertyAttribute, String> myStringAttributes;
    private boolean myOptional;
    private OCTypeSubstitution mySubstitution;
    
    public OCPropertySymbolImpl() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCPropertySymbolImpl(final Project project, final VirtualFile virtualFile, final int n, final String s, @NotNull final List<String> list, final OCClassSymbol ocClassSymbol, @NotNull final OCType myType, final boolean myOptional, @Nullable final EnumSet<PropertyAttribute> set, final EnumMap<PropertyAttribute, String> myStringAttributes) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list, ocClassSymbol);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myType = myType;
        this.myOptional = myOptional;
        this.myStringAttributes = myStringAttributes;
        EnumSet<PropertyAttribute> myAttributesSet = null;
        Label_0145: {
            if (set != null) {
                try {
                    if (!set.isEmpty()) {
                        myAttributesSet = set;
                        break Label_0145;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
            }
            myAttributesSet = null;
        }
        this.myAttributesSet = myAttributesSet;
    }
    
    public OCPropertySymbolImpl(@NotNull final OCPropertySymbolImpl ocPropertySymbolImpl, @NotNull final OCTypeSubstitution ocTypeSubstitution, final OCResolveContext ocResolveContext) {
        if (ocPropertySymbolImpl == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "<init>"));
        }
        if (ocTypeSubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "<init>"));
        }
        this(ocPropertySymbolImpl.getProject(), ocPropertySymbolImpl.getContainingFile(), ocPropertySymbolImpl.getOffset(), ocPropertySymbolImpl.getName(), ocPropertySymbolImpl.getAttributes(), ocPropertySymbolImpl.getParent(), ocPropertySymbolImpl.myType, ocPropertySymbolImpl.myOptional, ocPropertySymbolImpl.myAttributesSet, ocPropertySymbolImpl.myStringAttributes);
        this.mySubstitution = OCTypeSubstitution.compose(ocPropertySymbolImpl.mySubstitution, ocTypeSubstitution, ocResolveContext);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCPropertySymbolImpl ocPropertySymbolImpl = (OCPropertySymbolImpl)o;
        final OCPropertySymbolImpl ocPropertySymbolImpl2 = (OCPropertySymbolImpl)o2;
        try {
            if (!Comparing.equal((Object)ocPropertySymbolImpl.myAttributesSet, (Object)ocPropertySymbolImpl2.myAttributesSet)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocPropertySymbolImpl.myStringAttributes, (Object)ocPropertySymbolImpl2.myStringAttributes)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        try {
            if (!comparator.equalObjects(ocPropertySymbolImpl.mySubstitution, (DeepEqual.Equality<Object>)ocPropertySymbolImpl2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        return comparator.equalObjects(ocPropertySymbolImpl.myType, (DeepEqual.Equality<Object>)ocPropertySymbolImpl2.myType);
    }
    
    public static PropertySemantics getDefaultSemanticsForType(final OCType ocType, final PsiElement psiElement) {
        Label_0060: {
            Label_0034: {
                Label_0026: {
                    try {
                        if ("NSString *".equals(ocType.getName())) {
                            break Label_0026;
                        }
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCBlockPointerType;
                        if (b) {
                            break Label_0026;
                        }
                        break Label_0034;
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCBlockPointerType;
                        if (b) {
                            return PropertySemantics.COPY;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
                try {
                    if (!ocType.isPointerToObjectCompatible()) {
                        return PropertySemantics.ASSIGN;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final boolean b2 = OCCompilerFeatures.isArcEnabled(psiFile);
                    if (b2) {
                        break Label_0060;
                    }
                    return PropertySemantics.RETAIN;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiFile psiFile = psiElement2.getContainingFile();
                final boolean b2 = OCCompilerFeatures.isArcEnabled(psiFile);
                if (b2) {
                    return PropertySemantics.STRONG;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return PropertySemantics.RETAIN;
    }
    
    @Nullable
    public static PropertyAttribute parseAttribute(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "parseAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        for (final PropertyAttribute propertyAttribute : PropertyAttribute.values()) {
            try {
                if (s.equals(propertyAttribute.getTokenName())) {
                    return propertyAttribute;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType substitute;
        try {
            substitute = this.mySubstitution.substitute(this.myType, new OCResolveContext());
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return substitute;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind property;
        try {
            property = OCSymbolKind.PROPERTY;
            if (property == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return property;
    }
    
    @Override
    public boolean hasAttribute(final PropertyAttribute p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.myAttributesSet:Ljava/util/EnumSet;
        //     4: ifnull          25
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.myAttributesSet:Ljava/util/EnumSet;
        //    11: aload_1        
        //    12: invokevirtual   java/util/EnumSet.contains:(Ljava/lang/Object;)Z
        //    15: ifne            43
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.myStringAttributes:Ljava/util/EnumMap;
        //    29: aload_1        
        //    30: invokevirtual   java/util/EnumMap.containsKey:(Ljava/lang/Object;)Z
        //    33: ifeq            51
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  7      36     39     43     Ljava/lang/IllegalArgumentException;
        //  25     47     47     51     Ljava/lang/IllegalArgumentException;
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
    public String getAttributeValue(final PropertyAttribute propertyAttribute) {
        return this.myStringAttributes.get(propertyAttribute);
    }
    
    @Override
    public PropertyAttribute getAttributeOfGroup(final PropertyAttribute p0, final OCType p1, final PsiElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.myAttributesSet:Ljava/util/EnumSet;
        //     4: ifnull          60
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.myAttributesSet:Ljava/util/EnumSet;
        //    11: invokevirtual   java/util/EnumSet.iterator:()Ljava/util/Iterator;
        //    14: astore          4
        //    16: aload           4
        //    18: invokeinterface java/util/Iterator.hasNext:()Z
        //    23: ifeq            60
        //    26: aload           4
        //    28: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    33: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    36: astore          5
        //    38: aload           5
        //    40: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //    43: aload_1        
        //    44: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //    47: if_icmpne       57
        //    50: aload           5
        //    52: areturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: goto            16
        //    60: aload_1        
        //    61: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //    64: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    67: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getGroup:()I
        //    70: if_icmpne       118
        //    73: aload_2        
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //    77: ifeq            114
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload_3        
        //    88: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    93: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    96: ifeq            114
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   109: areturn        
        //   110: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   117: areturn        
        //   118: aload_1        
        //   119: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  38     53     53     57     Ljava/lang/IllegalArgumentException;
        //  60     80     83     87     Ljava/lang/IllegalArgumentException;
        //  73     99     102    106    Ljava/lang/IllegalArgumentException;
        //  87     110    110    114    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        return OCIcons.getPropertyIcon(this.isStatic(), this.isOptional());
    }
    
    @Override
    public boolean isStatic() {
        return this.hasAttribute(PropertyAttribute.CLASS);
    }
    
    @Override
    public boolean isOptional() {
        return this.myOptional;
    }
    
    @Override
    public boolean isReadonly() {
        return this.hasAttribute(PropertyAttribute.READONLY);
    }
    
    @Override
    public boolean isRetained() {
        Label_0027: {
            try {
                if (this.hasAttribute(PropertyAttribute.RETAIN)) {
                    break Label_0027;
                }
                final OCPropertySymbolImpl ocPropertySymbolImpl = this;
                final PropertyAttribute propertyAttribute = PropertyAttribute.COPY;
                final boolean b = ocPropertySymbolImpl.hasAttribute(propertyAttribute);
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCPropertySymbolImpl ocPropertySymbolImpl = this;
                final PropertyAttribute propertyAttribute = PropertyAttribute.COPY;
                final boolean b = ocPropertySymbolImpl.hasAttribute(propertyAttribute);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public String getGetterName() {
        final String attributeValue = this.getAttributeValue(PropertyAttribute.GETTER);
        String s = null;
        Label_0024: {
            try {
                if (attributeValue != null) {
                    final String myName;
                    s = (myName = attributeValue);
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String myName;
            s = (myName = this.myName);
            try {
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "getGetterName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    @Override
    public String getSetterName() {
        final String attributeValue = this.getAttributeValue(PropertyAttribute.SETTER);
        String s = null;
        Label_0045: {
            try {
                if (attributeValue != null) {
                    final String s2;
                    s = (s2 = attributeValue + (char)58);
                    break Label_0045;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String s2;
            s = (s2 = OCNameSuggester.getObjCSetterFromGetter(this.myName));
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "getSetterName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public boolean processAccessorMethods(final Processor<? super OCMethodSymbol> processor, final boolean b) {
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((Processor<? super T>)processor, (Condition<T>[])new Condition[] { ocMethodSymbol -> ocMethodSymbol.isGetter(), Conditions.alwaysTrue() });
        this.getParent().processMembers((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMethodSymbol -> {
            Label_0021: {
                try {
                    if (ocMethodSymbol.getGeneratedFromProperty() != this) {
                        return true;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0021;
                    }
                    break Label_0021;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        return OCSearchUtil.processMembersHierarchy(ocMethodSymbol, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                            try {
                                if (ocMethodSymbol.getGeneratedFromProperty() == null) {
                                    return orderedProcessor.process(ocMethodSymbol);
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw c(ex);
                            }
                            return true;
                        }), false, true);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            final OCMethodSymbol associatedSymbol = ocMethodSymbol.getAssociatedSymbol();
            try {
                if (associatedSymbol != null) {
                    return orderedProcessor.process(associatedSymbol);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            return true;
        }));
        return orderedProcessor.finish();
    }
    
    @Override
    public boolean processSynthesizes(final Processor<? super OCSynthesizeSymbol> processor) {
        final OCImplementationSymbol implementation = this.getParent().getImplementation();
        try {
            if (implementation == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return implementation.processMembers(OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCSynthesizeSymbol>)(ocSynthesizeSymbol -> {
            Label_0031: {
                try {
                    if (!this.getName().equals(ocSynthesizeSymbol.getName())) {
                        break Label_0031;
                    }
                    final Processor processor2 = processor;
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = processor2.process((Object)ocSynthesizeSymbol2);
                    if (b) {
                        break Label_0031;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final Processor processor2 = processor;
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = processor2.process((Object)ocSynthesizeSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return false;
        }));
    }
    
    @Nullable
    @Override
    public OCInstanceVariableSymbol getAssociatedIvar() {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        try {
            this.processSynthesizes((Processor<? super OCSynthesizeSymbol>)findFirstProcessor);
            if (findFirstProcessor.isFound()) {
                return ((OCSynthesizeSymbol)findFirstProcessor.getFoundValue()).getIvarSymbol();
            }
            if (!OCCompilerFeatures.supportsAutosynthesis((PsiFile)this.getContainingOCFile())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final String clang4ImplicitIvarName = OCNameSuggester.getClang4ImplicitIvarName(this.myName);
        final CommonProcessors.FindFirstProcessor findFirstProcessor2 = new CommonProcessors.FindFirstProcessor();
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<Object>((com.intellij.util.Processor<? super Object>)findFirstProcessor2, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { ocInstanceVariableSymbol -> {
                try {
                    if (!ocInstanceVariableSymbol.isClang4ImplicitIvar()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                return false;
            }, Conditions.alwaysTrue() });
        this.myParent.processMembersInAllCategories(clang4ImplicitIvarName, (Class<? extends OCMemberSymbol>)OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)orderedProcessor, false);
        orderedProcessor.finish();
        return (OCInstanceVariableSymbol)findFirstProcessor2.getFoundValue();
    }
    
    @Override
    public boolean hasAllAccessorsImplemented(final boolean b) {
        final OCImplementationSymbol implementation = this.getParent().getImplementation();
        try {
            if (implementation == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final String getterName = this.getGetterName();
        final String setterName = this.getSetterName();
        try {
            if (!implementation.processMembers(null, (Processor<OCMemberSymbol>)new Processor<OCMemberSymbol>() {
                boolean wasGetter;
                boolean wasSetter;
                
                public boolean process(final OCMemberSymbol ocMemberSymbol) {
                    if (b && ocMemberSymbol instanceof OCSynthesizeSymbol && OCPropertySymbolImpl.this.myName.equals(ocMemberSymbol.getName())) {
                        return false;
                    }
                    if (ocMemberSymbol instanceof OCMethodSymbol) {
                        if (getterName.equals(ocMemberSymbol.getName()) && ((OCMethodSymbol)ocMemberSymbol).isGetter()) {
                            this.wasGetter = true;
                        }
                        if (setterName.equals(ocMemberSymbol.getName()) && ((OCMethodSymbol)ocMemberSymbol).isSetter()) {
                            this.wasSetter = true;
                        }
                        return !this.wasGetter || (!OCPropertySymbolImpl.this.isReadonly() && !this.wasSetter);
                    }
                    return true;
                }
            })) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCPropertySymbol getAssociatedPropertyInPrivateCategory() {
        try {
            if ("".equals(this.getParent().getCategoryName())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        this.getParent().processMembers("", this.getName(), (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, true);
        return (OCPropertySymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    @Override
    public OCPropertySymbol getAssociatedPropertyInMainInterface() {
        try {
            if (this.getParent().getCategoryName() == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CommonProcessors.FindFirstProcessor<OCPropertySymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCPropertySymbol>() {
            protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
                return ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().getCategoryName() == null;
            }
        };
        final OCInterfaceSymbol mainInterface = this.getParent().getMainInterface();
        try {
            if (mainInterface != null) {
                mainInterface.processMembers(this.getName(), (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return (OCPropertySymbol)findFirstProcessor.getFoundValue();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
