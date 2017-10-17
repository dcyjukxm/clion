// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import java.util.Stack;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeResolveVisitor;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.HashMap;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Pair;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;

public class OCStructSymbol extends OCNamespaceSymbol implements OCTemplateSymbol<PsiElement>
{
    private OCSymbolKind mySymbolKind;
    @NotNull
    private Collection<Pair<OCType, OCVisibility>> myBaseCppClasses;
    @NotNull
    private List<OCTypeParameterSymbol> myTemplateParameters;
    @Nullable
    private List<OCTypeArgument> myTemplateSpecialization;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    @Nullable
    private TextRange myScope;
    private int myPropertiesAndAttributes;
    
    public OCStructSymbol() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCStructSymbol(@Nullable final Project p0, @Nullable final VirtualFile p1, final long p2, @Nullable final OCSymbolWithQualifiedName p3, @NotNull final OCQualifiedName p4, @NotNull final List<String> p5, @NotNull final OCSymbolKind p6, @NotNull final List<Pair<OCType, OCVisibility>> p7, @NotNull final List<OCTypeParameterSymbol> p8, @Nullable final List<OCTypeArgument> p9, @Nullable final List<OCSymbol> p10, @Nullable final MostlySingularMultiMap<String, OCSymbol> p11, final TextRange p12, @Nullable final OCVisibility p13, final int p14, final int p15) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           6
        //     2: ifnonnull       41
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "name"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "<init>"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: aload           7
        //    43: ifnonnull       82
        //    46: new             Ljava/lang/IllegalArgumentException;
        //    49: dup            
        //    50: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    52: ldc             3
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: ldc             0
        //    60: ldc             "attributes"
        //    62: aastore        
        //    63: dup            
        //    64: ldc             1
        //    66: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //    68: aastore        
        //    69: dup            
        //    70: ldc             2
        //    72: ldc             "<init>"
        //    74: aastore        
        //    75: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    78: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    81: athrow         
        //    82: aload           8
        //    84: ifnonnull       123
        //    87: new             Ljava/lang/IllegalArgumentException;
        //    90: dup            
        //    91: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    93: ldc             3
        //    95: anewarray       Ljava/lang/Object;
        //    98: dup            
        //    99: ldc             0
        //   101: ldc             "kind"
        //   103: aastore        
        //   104: dup            
        //   105: ldc             1
        //   107: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             2
        //   113: ldc             "<init>"
        //   115: aastore        
        //   116: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   119: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   122: athrow         
        //   123: aload           9
        //   125: ifnonnull       164
        //   128: new             Ljava/lang/IllegalArgumentException;
        //   131: dup            
        //   132: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   134: ldc             3
        //   136: anewarray       Ljava/lang/Object;
        //   139: dup            
        //   140: ldc             0
        //   142: ldc             "baseClasses"
        //   144: aastore        
        //   145: dup            
        //   146: ldc             1
        //   148: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             2
        //   154: ldc             "<init>"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: aload           10
        //   166: ifnonnull       205
        //   169: new             Ljava/lang/IllegalArgumentException;
        //   172: dup            
        //   173: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   175: ldc             3
        //   177: anewarray       Ljava/lang/Object;
        //   180: dup            
        //   181: ldc             0
        //   183: ldc             "templateParameters"
        //   185: aastore        
        //   186: dup            
        //   187: ldc             1
        //   189: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //   191: aastore        
        //   192: dup            
        //   193: ldc             2
        //   195: ldc             "<init>"
        //   197: aastore        
        //   198: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   201: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   204: athrow         
        //   205: aload_0        
        //   206: aload_1        
        //   207: aload_2        
        //   208: lload_3        
        //   209: aload           5
        //   211: aload           6
        //   213: aload           7
        //   215: aload           12
        //   217: aload           13
        //   219: aconst_null    
        //   220: iconst_0       
        //   221: aload           15
        //   223: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Ljava/util/List;Lcom/intellij/util/containers/MostlySingularMultiMap;Ljava/util/List;ZLcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   226: aload_0        
        //   227: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   230: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   233: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.$assertionsDisabled:Z
        //   236: ifne            289
        //   239: aload           8
        //   241: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   244: if_acmpeq       289
        //   247: aload           8
        //   249: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   252: if_acmpeq       289
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: aload           8
        //   264: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   267: if_acmpeq       289
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: new             Ljava/lang/AssertionError;
        //   280: dup            
        //   281: invokespecial   java/lang/AssertionError.<init>:()V
        //   284: athrow         
        //   285: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload_0        
        //   290: aload           8
        //   292: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   295: aload_0        
        //   296: aload           9
        //   298: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myBaseCppClasses:Ljava/util/Collection;
        //   301: aload_0        
        //   302: aload           10
        //   304: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myTemplateParameters:Ljava/util/List;
        //   307: aload_0        
        //   308: aload           11
        //   310: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myTemplateSpecialization:Ljava/util/List;
        //   313: aload_0        
        //   314: aload           14
        //   316: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myScope:Lcom/intellij/openapi/util/TextRange;
        //   319: aload_0        
        //   320: iload           16
        //   322: iload           17
        //   324: ior            
        //   325: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myPropertiesAndAttributes:I
        //   328: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List<Ljava/lang/String;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/util/List<Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;>;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/intellij/util/containers/MostlySingularMultiMap<Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;II)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  239    255    258    262    Ljava/lang/IllegalArgumentException;
        //  247    270    273    277    Ljava/lang/IllegalArgumentException;
        //  262    285    285    289    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0262:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public OCStructSymbol(final OCStructSymbol ocStructSymbol, final OCTypeSubstitution ocTypeSubstitution, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "<init>"));
        }
        this(ocStructSymbol, ocTypeSubstitution, ocStructSymbol.getParent(), b, ocResolveContext);
    }
    
    public OCStructSymbol(final OCStructSymbol ocStructSymbol, final OCTypeSubstitution ocTypeSubstitution, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "<init>"));
        }
        super(ocStructSymbol.myProject, ocStructSymbol.myFile, ocStructSymbol.myComplexOffset, ocSymbolWithQualifiedName, ocStructSymbol.getQualifiedName(), ocStructSymbol.getAttributes(), ocStructSymbol.getMembersList(), ocStructSymbol.getMembers(), null, false, ocStructSymbol.myVisibility);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.mySymbolKind = ocStructSymbol.getKind();
        this.myBaseCppClasses = ocStructSymbol.myBaseCppClasses;
        this.myTemplateParameters = ocStructSymbol.myTemplateParameters;
        this.myTemplateSpecialization = ocStructSymbol.myTemplateSpecialization;
        this.mySubstitution = OCTypeSubstitution.compose(ocStructSymbol.mySubstitution, ocTypeSubstitution, b, ocResolveContext);
        this.myScope = ocStructSymbol.getScope();
        this.myPropertiesAndAttributes = ocStructSymbol.myPropertiesAndAttributes;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        final OCStructSymbol ocStructSymbol = (OCStructSymbol)o;
        final OCStructSymbol ocStructSymbol2 = (OCStructSymbol)o2;
        try {
            if (ocStructSymbol.myPropertiesAndAttributes != ocStructSymbol2.myPropertiesAndAttributes) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw d(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocStructSymbol.myScope, (Object)ocStructSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw d(ex6);
        }
        try {
            if (!Comparing.equal((Object)ocStructSymbol.mySymbolKind, (Object)ocStructSymbol2.mySymbolKind)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw d(ex7);
        }
        try {
            if (!comparator.equalIterable(ocStructSymbol.myBaseCppClasses, ocStructSymbol2.myBaseCppClasses)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw d(ex8);
        }
        try {
            if (!comparator.equalObjects(ocStructSymbol.mySubstitution, (DeepEqual.Equality<Object>)ocStructSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw d(ex9);
        }
        try {
            if (!comparator.equalIterable(ocStructSymbol.myTemplateParameters, ocStructSymbol2.myTemplateParameters)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw d(ex10);
        }
        try {
            if (!comparator.equalIterable(ocStructSymbol.myTemplateSpecialization, ocStructSymbol2.myTemplateSpecialization)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw d(ex11);
        }
        return true;
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
        super.updateOffset(n, n2, n3);
        final Iterator<OCTypeParameterSymbol> iterator = this.myTemplateParameters.iterator();
        while (iterator.hasNext()) {
            ((OCSymbol)iterator.next()).updateOffset(n, n2, n3);
        }
    }
    
    @Override
    public void compact() {
        super.compact();
        final Iterator<OCTypeParameterSymbol> iterator = this.myTemplateParameters.iterator();
        while (iterator.hasNext()) {
            ((OCSymbol)iterator.next()).compact();
        }
    }
    
    @Override
    public boolean isFriend() {
        return this.hasAttribute(OCSymbolAttribute.FRIEND);
    }
    
    public boolean isFinal() {
        return this.hasAttribute(OCSymbolAttribute.FINAL);
    }
    
    public boolean isEnumClass() {
        return this.hasProperty(Property.IS_ENUM_CLASS);
    }
    
    public boolean isEnum() {
        try {
            if (this.getKind() == OCSymbolKind.ENUM) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public boolean isTemplateSymbol() {
        Label_0026: {
            try {
                if (this.myTemplateParameters.size() != 0) {
                    break Label_0026;
                }
                final OCStructSymbol ocStructSymbol = this;
                final List<OCTypeArgument> list = ocStructSymbol.myTemplateSpecialization;
                if (list != null) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol = this;
                final List<OCTypeArgument> list = ocStructSymbol.myTemplateSpecialization;
                if (list != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    public boolean isInnerClass() {
        OCSymbolWithQualifiedName ocSymbolWithQualifiedName = this.myParent;
        while (true) {
            Label_0029: {
                try {
                    if (ocSymbolWithQualifiedName == null) {
                        break;
                    }
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                    final boolean b = ocSymbolWithQualifiedName2 instanceof OCStructSymbol;
                    if (b) {
                        return true;
                    }
                    break Label_0029;
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                try {
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                    final boolean b = ocSymbolWithQualifiedName2 instanceof OCStructSymbol;
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
            ocSymbolWithQualifiedName = ocSymbolWithQualifiedName.getParent();
        }
        return false;
    }
    
    public boolean hasAttribute(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
        try {
            if (ocSymbolAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attr", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "hasAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if ((this.myPropertiesAndAttributes & ocSymbolAttribute.getMask()) != 0x0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return false;
    }
    
    public boolean hasProperty(@NotNull final Property property) {
        try {
            if (property == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prop", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "hasProperty"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if ((this.myPropertiesAndAttributes & property.getMask()) != 0x0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return false;
    }
    
    @NotNull
    @Override
    public List<OCTypeParameterSymbol> getTemplateParameters() {
        List<OCTypeParameterSymbol> myTemplateParameters;
        try {
            myTemplateParameters = this.myTemplateParameters;
            if (myTemplateParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getTemplateParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return myTemplateParameters;
    }
    
    @Nullable
    @Override
    public List<OCTypeArgument> getTemplateSpecialization() {
        return this.myTemplateSpecialization;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public boolean isVariadicTemplate() {
        return OCTemplateSymbolImpl.isVariadicTemplate(this);
    }
    
    @Override
    public boolean isSpecialization() {
        try {
            if (this.getTemplateSpecialization() != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public boolean isExplicitInstantiation() {
        Label_0026: {
            try {
                if (!this.isTemplateSymbol()) {
                    return false;
                }
                final OCStructSymbol ocStructSymbol = this;
                final List<OCTypeParameterSymbol> list = ocStructSymbol.getTemplateParameters();
                final boolean b = list.isEmpty();
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol = this;
                final List<OCTypeParameterSymbol> list = ocStructSymbol.getTemplateParameters();
                final boolean b = list.isEmpty();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    @Override
    public int getRequiredTemplateArgumentsCnt() {
        return OCTemplateSymbolImpl.getRequiredTemplateArgumentsCnt(this);
    }
    
    @Override
    public boolean isGlobal() {
        try {
            if (this.myScope == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public boolean isPredeclaration() {
        try {
            if (this.getMembers() == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public String getKindUppercase() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     9: astore_2       
        //    10: aload_2        
        //    11: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    14: if_acmpne       51
        //    17: aload_1        
        //    18: ifnull          51
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: aload_1        
        //    29: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    34: ifeq            51
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: ldc             "Class"
        //    46: areturn        
        //    47: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_0        
        //    52: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.getKindUppercase:()Ljava/lang/String;
        //    55: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     21     24     28     Ljava/lang/IllegalArgumentException;
        //  17     37     40     44     Ljava/lang/IllegalArgumentException;
        //  28     47     47     51     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    public boolean processMembers(@Nullable final String s, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Processor processor2;
        if (this.mySubstitution != OCTypeSubstitution.ID) {
            processor2 = (ocSymbol -> {
                try {
                    if (processor == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "lambda$processMembers$0"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                return processor.process(this.mySubstitution.substitute((Object)ocSymbol, this, false, new OCResolveContext()));
            });
        }
        else {
            processor2 = processor;
        }
        return super.processMembers(s, (Processor<OCSymbol>)processor2);
    }
    
    public boolean processFields(@NotNull final Processor<OCDeclaratorSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processFields"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return this.processMembers(null, (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)processor, OCDeclaratorSymbol.class));
    }
    
    public boolean processFunctions(@Nullable final String s, @NotNull final Processor<OCFunctionSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processFunctions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return this.processMembers(s, (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)processor, OCFunctionSymbol.class));
    }
    
    @Nullable
    public OCDeclaratorSymbol findField(final String s) {
        final MostlySingularMultiMap<String, OCSymbol> members = this.getMembers();
        try {
            if (members == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol.getDelegate() instanceof OCDeclaratorSymbol;
            }
        };
        members.processForKey((Object)s, (Processor)findFirstProcessor);
        final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)findFirstProcessor.getFoundValue();
        try {
            if (ocDeclaratorSymbol != null) {
                return (OCDeclaratorSymbol)ocDeclaratorSymbol.getDelegate();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind mySymbolKind;
        try {
            mySymbolKind = this.mySymbolKind;
            if (mySymbolKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySymbolKind;
    }
    
    @NotNull
    @Override
    public OCStructType getType() {
        OCStructType ocStructType;
        try {
            ocStructType = new OCStructType(this);
            if (ocStructType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocStructType;
    }
    
    @Nullable
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @NotNull
    @Override
    public String getPresentableName() {
        String name;
        try {
            name = this.getType().getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return name;
    }
    
    @Override
    public Icon computeFullIconNow(@Nullable final PsiElement psiElement) {
        final Icon computeFullIconNow = super.computeFullIconNow(psiElement);
        try {
            if (computeFullIconNow == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (OCTestFrameworks.isTestClassOrStruct(this)) {
                return OCIcons.getTestIcon(computeFullIconNow);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return computeFullIconNow;
    }
    
    public boolean processAllMembersWithName(final String s, final Processor<OCSymbol> processor) {
        try {
            if (!this.isGlobal()) {
                return this.processMembers(s, (Processor<OCSymbol>)(ocSymbol -> processor.process((Object)ocSymbol)));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(this.myProject, (Processor<OCSymbol>)(ocSymbol2 -> {
            try {
                if (!((OCSymbolWithQualifiedName)ocSymbol2).getClass().equals(OCFunctionSymbol.class)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            final OCSymbolWithQualifiedName resolvedOwner = ((OCSymbolWithQualifiedName)ocSymbol2).getResolvedOwner();
            try {
                if (resolvedOwner == null) {
                    return true;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = resolvedOwner;
                final OCSymbol ocSymbol3 = ocSymbol;
                final boolean b = ocSymbolWithQualifiedName.equals(ocSymbol3);
                if (!b) {
                    return true;
                }
                return processor.process((Object)ocSymbol2);
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = resolvedOwner;
                final OCSymbol ocSymbol3 = ocSymbol;
                final boolean b = ocSymbolWithQualifiedName.equals(ocSymbol3);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
            return processor.process((Object)ocSymbol2);
        }), s);
    }
    
    public boolean processConstructors(final Processor<? super OCFunctionSymbol> processor) {
        return this.a(processor, false, false, null);
    }
    
    public boolean processConstructors(final Processor<? super OCFunctionSymbol> processor, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processConstructors"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return this.a(processor, false, b, ocResolveContext);
    }
    
    public boolean processConstructors(final Processor<? super OCFunctionSymbol> processor, final boolean b) {
        return this.a(processor, b, false, null);
    }
    
    private boolean a(final Processor<? super OCFunctionSymbol> p0, final boolean p1, final boolean p2, @Nullable final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //     6: astore          5
        //     8: iload_3        
        //     9: ifeq            48
        //    12: aload           4
        //    14: ifnull          48
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: aload           5
        //    27: aload           4
        //    29: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processConstructorsFromUsings:(Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    32: ifne            48
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_0       
        //    43: ireturn        
        //    44: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iload_2        
        //    49: ifeq            67
        //    52: aload_0        
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myName:Ljava/lang/String;
        //    57: aload           5
        //    59: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllMembersWithName:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //    62: ireturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.myName:Ljava/lang/String;
        //    72: aload           5
        //    74: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //    77: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<-Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  8      17     20     24     Ljava/lang/IllegalArgumentException;
        //  12     35     38     42     Ljava/lang/IllegalArgumentException;
        //  24     44     44     48     Ljava/lang/IllegalArgumentException;
        //  48     63     63     67     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public boolean processConstructorsFromUsings(@NotNull final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processConstructorsFromUsings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processConstructorsFromUsings"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return this.processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "lambda$processConstructorsFromUsings$5"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "lambda$processConstructorsFromUsings$5"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            if (ocSymbol instanceof OCUsingSymbol) {
                final OCSymbolReference symbolReference = ((OCUsingSymbol)ocSymbol).getSymbolReference();
                final OCQualifiedName qualifiedName = symbolReference.getQualifiedName();
                final OCQualifiedName qualifier = qualifiedName.getQualifier();
                try {
                    if (qualifier == null || !qualifier.getNameWithParent().equals(qualifiedName.getName())) {
                        return symbolReference.processPossibleSymbols((Processor<OCSymbol>)(p1 -> {
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
                            //    18: ldc             "processor"
                            //    20: aastore        
                            //    21: dup            
                            //    22: ldc             1
                            //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
                            //    26: aastore        
                            //    27: dup            
                            //    28: ldc             2
                            //    30: ldc             "lambda$null$4"
                            //    32: aastore        
                            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                            //    39: athrow         
                            //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                            //    43: athrow         
                            //    44: aload_1        
                            //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                            //    48: ifeq            85
                            //    51: aload_1        
                            //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                            //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
                            //    58: ifeq            85
                            //    61: goto            68
                            //    64: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                            //    67: athrow         
                            //    68: aload_0        
                            //    69: aload_1        
                            //    70: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                            //    75: ifeq            93
                            //    78: goto            85
                            //    81: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                            //    84: athrow         
                            //    85: iconst_1       
                            //    86: goto            94
                            //    89: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                            //    92: athrow         
                            //    93: iconst_0       
                            //    94: ireturn        
                            //    Exceptions:
                            //  Try           Handler
                            //  Start  End    Start  End    Type                                
                            //  -----  -----  -----  -----  ------------------------------------
                            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                            //  44     61     64     68     Ljava/lang/IllegalArgumentException;
                            //  51     78     81     85     Ljava/lang/IllegalArgumentException;
                            //  68     89     89     93     Ljava/lang/IllegalArgumentException;
                            // 
                            // The error that occurred was:
                            // 
                            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
                        }), ocResolveContext.substitute(((OCUsingSymbol)ocSymbol).getSubstitution()));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
                for (final OCSymbol ocSymbol2 : symbolReference.getSymbolReferenceToQualifier().resolveToSymbols(true, true, true, ocResolveContext.substitute(this.getSubstitution()))) {
                    try {
                        if (!(ocSymbol2 instanceof OCStructSymbol)) {
                            continue;
                        }
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol2;
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final Processor processor2 = processor;
                        final boolean b = true;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = ocStructSymbol2.processConstructors((Processor<? super OCFunctionSymbol>)processor2, b, ocResolveContext2);
                        if (!b2) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw d(ex4);
                    }
                    try {
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol2;
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final Processor processor2 = processor;
                        final boolean b = true;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b2 = ocStructSymbol2.processConstructors((Processor<? super OCFunctionSymbol>)processor2, b, ocResolveContext2);
                        if (!b2) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw d(ex5);
                    }
                }
                return symbolReference.processPossibleSymbols((Processor<OCSymbol>)(p1 -> {
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
                    //    18: ldc             "processor"
                    //    20: aastore        
                    //    21: dup            
                    //    22: ldc             1
                    //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
                    //    26: aastore        
                    //    27: dup            
                    //    28: ldc             2
                    //    30: ldc             "lambda$null$4"
                    //    32: aastore        
                    //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                    //    39: athrow         
                    //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    43: athrow         
                    //    44: aload_1        
                    //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                    //    48: ifeq            85
                    //    51: aload_1        
                    //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                    //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
                    //    58: ifeq            85
                    //    61: goto            68
                    //    64: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    67: athrow         
                    //    68: aload_0        
                    //    69: aload_1        
                    //    70: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                    //    75: ifeq            93
                    //    78: goto            85
                    //    81: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    84: athrow         
                    //    85: iconst_1       
                    //    86: goto            94
                    //    89: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    92: athrow         
                    //    93: iconst_0       
                    //    94: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                    //  44     61     64     68     Ljava/lang/IllegalArgumentException;
                    //  51     78     81     85     Ljava/lang/IllegalArgumentException;
                    //  68     89     89     93     Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
                }), ocResolveContext.substitute(((OCUsingSymbol)ocSymbol).getSubstitution()));
            }
            return true;
        }));
    }
    
    public boolean processDestructors(final Processor<OCFunctionSymbol> processor, final boolean b) {
        final Processor processor2 = ocSymbol -> {
            Label_0024: {
                try {
                    if (!(ocSymbol instanceof OCFunctionSymbol)) {
                        return true;
                    }
                    final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = ocFunctionSymbol2.isCppDestructor();
                    if (b) {
                        break Label_0024;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = ocFunctionSymbol2.isCppDestructor();
                    if (b) {
                        return processor.process((Object)ocSymbol);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
            return true;
        };
        try {
            if (b) {
                return this.processAllMembersWithName("~" + this.myName, (Processor<OCSymbol>)processor2);
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return this.processMembers("~" + this.myName, (Processor<OCSymbol>)processor2);
    }
    
    public boolean hasDefaultConstructor() {
        final OCStructSymbol.1MyProcessor 1MyProcessor = new OCStructSymbol.1MyProcessor();
        this.processConstructors((Processor<? super OCFunctionSymbol>)1MyProcessor);
        return 1MyProcessor.hasDefaultConstructor();
    }
    
    public boolean hasDeclaredConstructor() {
        try {
            if (!this.processConstructors((Processor<? super OCFunctionSymbol>)new CommonProcessors.FindFirstProcessor())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Nullable
    public OCFunctionSymbol getDefaultConstructor() {
        final CommonProcessors.FindFirstProcessor<OCFunctionSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCFunctionSymbol>() {
            protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
                return ocFunctionSymbol.canBeCalledWithoutArguments();
            }
        };
        this.processConstructors((Processor<? super OCFunctionSymbol>)findFirstProcessor);
        return (OCFunctionSymbol)findFirstProcessor.getFoundValue();
    }
    
    public boolean hasTrivialDestructor(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "hasTrivialDestructor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return this.a(new HashMap<OCStructSymbol, Optional<Boolean>>(), ocResolveContext);
    }
    
    private boolean a(@NotNull final HashMap<OCStructSymbol, Optional<Boolean>> p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "processingSymbols"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "hasTrivialDestructor"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "hasTrivialDestructor"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: aload_0        
        //    90: invokevirtual   java/util/HashMap.containsKey:(Ljava/lang/Object;)Z
        //    93: ifeq            120
        //    96: aload_1        
        //    97: aload_0        
        //    98: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   101: checkcast       Ljava/util/Optional;
        //   104: astore_3       
        //   105: aload_3        
        //   106: iconst_0       
        //   107: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   110: invokevirtual   java/util/Optional.orElse:(Ljava/lang/Object;)Ljava/lang/Object;
        //   113: checkcast       Ljava/lang/Boolean;
        //   116: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   119: ireturn        
        //   120: aload_0        
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   124: astore_3       
        //   125: aload_0        
        //   126: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   129: astore          4
        //   131: aload           4
        //   133: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   136: if_acmpeq       160
        //   139: aload           4
        //   141: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   144: if_acmpeq       160
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: iconst_1       
        //   155: ireturn        
        //   156: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: invokedynamic   process:()Lcom/intellij/util/Processor;
        //   165: astore          5
        //   167: aload_1        
        //   168: aload_2        
        //   169: invokedynamic   process:(Ljava/util/HashMap;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   174: astore          6
        //   176: aload_2        
        //   177: aload_1        
        //   178: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/HashMap;)Lcom/intellij/util/Processor;
        //   183: astore          7
        //   185: aload_1        
        //   186: aload_0        
        //   187: invokestatic    java/util/Optional.empty:()Ljava/util/Optional;
        //   190: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   193: pop            
        //   194: aload_0        
        //   195: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredeclaration:()Z
        //   198: ifne            274
        //   201: aload_3        
        //   202: aload_2        
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   206: ifne            274
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_0        
        //   217: aload           5
        //   219: iconst_0       
        //   220: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processDestructors:(Lcom/intellij/util/Processor;Z)Z
        //   223: ifeq            274
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload_0        
        //   234: aload_2        
        //   235: aload           6
        //   237: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   240: ifeq            274
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: aload_0        
        //   251: aload           7
        //   253: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processFields:(Lcom/intellij/util/Processor;)Z
        //   256: ifeq            274
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: iconst_1       
        //   267: goto            275
        //   270: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: iconst_0       
        //   275: istore          8
        //   277: aload_1        
        //   278: aload_0        
        //   279: iload           8
        //   281: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   284: invokestatic    java/util/Optional.of:(Ljava/lang/Object;)Ljava/util/Optional;
        //   287: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   290: pop            
        //   291: iload           8
        //   293: ireturn        
        //    Signature:
        //  (Ljava/util/HashMap<Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/Optional<Ljava/lang/Boolean;>;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  131    147    150    154    Ljava/lang/IllegalArgumentException;
        //  139    156    156    160    Ljava/lang/IllegalArgumentException;
        //  185    209    212    216    Ljava/lang/IllegalArgumentException;
        //  201    226    229    233    Ljava/lang/IllegalArgumentException;
        //  216    243    246    250    Ljava/lang/IllegalArgumentException;
        //  233    259    262    266    Ljava/lang/IllegalArgumentException;
        //  250    270    270    274    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0216:
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
    
    public boolean hasBaseClasses() {
        try {
            if (!this.myBaseCppClasses.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    public boolean isPOD(final boolean b) {
        Label_0033: {
            try {
                if (this.mySymbolKind == OCSymbolKind.STRUCT) {
                    break Label_0033;
                }
                final OCStructSymbol ocStructSymbol = this;
                final OCSymbolKind ocSymbolKind = ocStructSymbol.mySymbolKind;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                if (ocSymbolKind != ocSymbolKind2) {
                    return false;
                }
                break Label_0033;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol = this;
                final OCSymbolKind ocSymbolKind = ocStructSymbol.mySymbolKind;
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                if (ocSymbolKind != ocSymbolKind2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                if (this.hasBaseClasses()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return a(this, new HashSet<OCSymbol>(), b, this.getContainingOCFile());
    }
    
    public boolean isPOD() {
        return this.isPOD(true);
    }
    
    private static boolean a(final OCStructSymbol ocStructSymbol, final Set<OCSymbol> set, final boolean b, final OCFile ocFile) {
        final boolean[] array = { true };
        ocStructSymbol.processMembers(null, (Processor<OCSymbol>)(p4 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload           4
            //     3: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //     8: ifne            17
            //    11: iconst_1       
            //    12: ireturn        
            //    13: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    16: athrow         
            //    17: aload           4
            //    19: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    22: ifeq            67
            //    25: iload_1        
            //    26: ifeq            220
            //    29: goto            36
            //    32: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    35: athrow         
            //    36: aload           4
            //    38: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    41: aload_0        
            //    42: iconst_1       
            //    43: aload_2        
            //    44: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/Set;ZLcom/jetbrains/cidr/lang/psi/OCFile;)Z
            //    47: ifne            220
            //    50: goto            57
            //    53: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    56: athrow         
            //    57: aload_3        
            //    58: iconst_0       
            //    59: iconst_0       
            //    60: bastore        
            //    61: iconst_0       
            //    62: ireturn        
            //    63: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    66: athrow         
            //    67: aload           4
            //    69: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //    72: ifeq            220
            //    75: aload           4
            //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //    80: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
            //    83: astore          5
            //    85: aload           5
            //    87: ifnull          130
            //    90: aload           5
            //    92: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
            //    95: if_acmpeq       130
            //    98: goto            105
            //   101: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   104: athrow         
            //   105: aload           5
            //   107: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
            //   110: if_acmpeq       130
            //   113: goto            120
            //   116: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   119: athrow         
            //   120: aload_3        
            //   121: iconst_0       
            //   122: iconst_0       
            //   123: bastore        
            //   124: iconst_0       
            //   125: ireturn        
            //   126: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   129: athrow         
            //   130: aload           4
            //   132: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   137: aload_2        
            //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   141: astore          6
            //   143: iload_1        
            //   144: ifeq            220
            //   147: aload           6
            //   149: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   152: ifeq            220
            //   155: goto            162
            //   158: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   161: athrow         
            //   162: aload           4
            //   164: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   169: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   172: if_acmpeq       220
            //   175: goto            182
            //   178: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   181: athrow         
            //   182: aload           6
            //   184: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   187: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   190: aload_0        
            //   191: aload_2        
            //   192: invokedynamic   value:(Ljava/util/Set;Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/openapi/util/Condition;
            //   197: invokestatic    com/intellij/util/containers/ContainerUtil.exists:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
            //   200: ifeq            220
            //   203: goto            210
            //   206: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   209: athrow         
            //   210: aload_3        
            //   211: iconst_0       
            //   212: iconst_0       
            //   213: bastore        
            //   214: iconst_0       
            //   215: ireturn        
            //   216: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   219: athrow         
            //   220: iconst_1       
            //   221: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      13     13     17     Ljava/lang/IllegalArgumentException;
            //  17     29     32     36     Ljava/lang/IllegalArgumentException;
            //  25     50     53     57     Ljava/lang/IllegalArgumentException;
            //  36     63     63     67     Ljava/lang/IllegalArgumentException;
            //  85     98     101    105    Ljava/lang/IllegalArgumentException;
            //  90     113    116    120    Ljava/lang/IllegalArgumentException;
            //  105    126    126    130    Ljava/lang/IllegalArgumentException;
            //  143    155    158    162    Ljava/lang/IllegalArgumentException;
            //  147    175    178    182    Ljava/lang/IllegalArgumentException;
            //  162    203    206    210    Ljava/lang/IllegalArgumentException;
            //  182    216    216    220    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
        }));
        return array[0];
    }
    
    public boolean hasMemberFunctions() {
        try {
            if (!this.processFunctions(null, (Processor<OCFunctionSymbol>)new CommonProcessors.FindFirstProcessor())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @NotNull
    public Collection<OCType> getBaseCppClasses(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClasses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Collection<OCType> baseCppClasses;
        try {
            baseCppClasses = this.getBaseCppClasses(new OCResolveContext(psiElement));
            if (baseCppClasses == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClasses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return baseCppClasses;
    }
    
    @NotNull
    public Collection<OCType> getBaseCppClasses(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClasses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        List map;
        try {
            map = ContainerUtil.map((Collection)this.myBaseCppClasses, pair -> {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "lambda$getBaseCppClasses$12"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                return this.mySubstitution.substitute((OCType)pair.first, ocResolveContext);
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClasses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return (Collection<OCType>)map;
    }
    
    @NotNull
    public Collection<Pair<OCType, OCVisibility>> getBaseCppClassesWithVisibility(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClassesWithVisibility"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        List map;
        try {
            map = ContainerUtil.map((Collection)this.myBaseCppClasses, pair -> {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "lambda$getBaseCppClassesWithVisibility$13"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                return new Pair((Object)this.mySubstitution.substitute((OCType)pair.first, ocResolveContext), pair.second);
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getBaseCppClassesWithVisibility"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return (Collection<Pair<OCType, OCVisibility>>)map;
    }
    
    public boolean processBaseClasses(@NotNull final OCResolveContext ocResolveContext, final BaseClassProcessor baseClassProcessor) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processBaseClasses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        for (final Pair<OCType, OCVisibility> pair : this.myBaseCppClasses) {
            final OCType substitute = this.mySubstitution.substitute((OCType)pair.first, ocResolveContext);
            final OCType ocType = substitute.accept((OCTypeVisitor<OCType>)new OCTypeResolveVisitor(ocResolveContext));
            Label_0129: {
                try {
                    if (ocType.isUnknown()) {
                        final OCType ocType2 = substitute;
                        break Label_0129;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                final OCType ocType2 = ocType;
                try {
                    if (!a(ocType2, (OCVisibility)pair.second, baseClassProcessor, ocResolveContext)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
        }
        return true;
    }
    
    private static boolean a(final OCType ocType, final OCVisibility ocVisibility, final BaseClassProcessor baseClassProcessor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "processBaseClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        ProgressManager.checkCanceled();
        if (ocType instanceof OCStructType) {
            for (final OCStructSymbol ocStructSymbol : ((OCStructType)ocType).getStructs()) {
                try {
                    if (!baseClassProcessor.process(ocStructSymbol, ocVisibility)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
        }
        else if (ocType instanceof OCReferenceType) {
            for (final OCSymbol ocSymbol : ocResolveContext.resolveToSymbols(((OCReferenceType)ocType).getReference(ocResolveContext), true, true)) {
                try {
                    ProgressManager.checkCanceled();
                    if (!baseClassProcessor.process(ocSymbol, ocVisibility)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
        }
        else if (ocType instanceof OCExpansionPackType) {
            for (final OCTypeArgument ocTypeArgument : ((OCExpansionPackType)ocType).getExpansions()) {
                try {
                    if (!(ocTypeArgument instanceof OCType)) {
                        continue;
                    }
                    final OCType ocType2 = (OCType)ocTypeArgument;
                    final OCType ocType3 = ocType2;
                    final OCVisibility ocVisibility2 = ocVisibility;
                    final BaseClassProcessor baseClassProcessor2 = baseClassProcessor;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = a(ocType3, ocVisibility2, baseClassProcessor2, ocResolveContext2);
                    if (!b) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw d(ex4);
                }
                try {
                    final OCType ocType2 = (OCType)ocTypeArgument;
                    final OCType ocType3 = ocType2;
                    final OCVisibility ocVisibility2 = ocVisibility;
                    final BaseClassProcessor baseClassProcessor2 = baseClassProcessor;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = a(ocType3, ocVisibility2, baseClassProcessor2, ocResolveContext2);
                    if (!b) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw d(ex5);
                }
            }
        }
        return true;
    }
    
    public boolean processAllBaseClasses(final BaseClassProcessor baseClassProcessor) {
        return this.processAllBaseClasses(baseClassProcessor, null);
    }
    
    public boolean processAllBaseClasses(final BaseClassProcessor baseClassProcessor, @Nullable final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            final OCFile containingOCFile = this.getContainingOCFile();
            Label_0036: {
                try {
                    if (containingOCFile == null) {
                        break Label_0036;
                    }
                    final OCStructSymbol ocStructSymbol = this;
                    final BaseClassProcessor baseClassProcessor2 = baseClassProcessor;
                    final PsiElement psiElement = (PsiElement)containingOCFile;
                    final OCResolveContext ocResolveContext2 = new OCResolveContext(psiElement);
                    final boolean b = ocStructSymbol.processAllBaseClasses(baseClassProcessor2, ocResolveContext2);
                    if (b) {
                        break Label_0036;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                try {
                    final OCStructSymbol ocStructSymbol = this;
                    final BaseClassProcessor baseClassProcessor2 = baseClassProcessor;
                    final PsiElement psiElement = (PsiElement)containingOCFile;
                    final OCResolveContext ocResolveContext2 = new OCResolveContext(psiElement);
                    final boolean b = ocStructSymbol.processAllBaseClasses(baseClassProcessor2, ocResolveContext2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
            return false;
        }
        return this.processAllBaseClasses(ocResolveContext, baseClassProcessor, true);
    }
    
    public boolean processAllBaseClasses(@NotNull final OCResolveContext p0, final BaseClassProcessor p1, final boolean p2) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processAllBaseClasses"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newSymbolWithSubstitutionSet:()Ljava/util/Set;
        //    47: astore          4
        //    49: new             Ljava/util/Stack;
        //    52: dup            
        //    53: invokespecial   java/util/Stack.<init>:()V
        //    56: astore          5
        //    58: new             Ljava/util/Stack;
        //    61: dup            
        //    62: invokespecial   java/util/Stack.<init>:()V
        //    65: astore          6
        //    67: aload           5
        //    69: aload_0        
        //    70: invokevirtual   java/util/Stack.add:(Ljava/lang/Object;)Z
        //    73: pop            
        //    74: aload           6
        //    76: aconst_null    
        //    77: invokevirtual   java/util/Stack.add:(Ljava/lang/Object;)Z
        //    80: pop            
        //    81: aload           5
        //    83: invokevirtual   java/util/Stack.isEmpty:()Z
        //    86: ifne            169
        //    89: aload           5
        //    91: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    97: astore          7
        //    99: aload           6
        //   101: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   104: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   107: astore          8
        //   109: aload           4
        //   111: aload           7
        //   113: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   118: ifne            166
        //   121: aload           4
        //   123: aload           7
        //   125: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   130: pop            
        //   131: aload           7
        //   133: aload_1        
        //   134: aload           8
        //   136: iload_3        
        //   137: aload_2        
        //   138: aload           5
        //   140: aload           6
        //   142: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;ZLcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;Ljava/util/Stack;Ljava/util/Stack;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   147: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   150: ifne            166
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: iconst_0       
        //   161: ireturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: goto            81
        //   169: iconst_1       
        //   170: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  109    153    156    160    Ljava/lang/IllegalArgumentException;
        //  121    162    162    166    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    public boolean isAncestor(final OCStructSymbol ocStructSymbol) {
        return this.isAncestor(ocStructSymbol, null);
    }
    
    public boolean isAncestor(final OCStructSymbol ocStructSymbol, @Nullable final OCResolveContext ocResolveContext) {
        Label_0025_1: {
            try {
                if (this.resolvedNamesEqual(ocStructSymbol)) {
                    break Label_0025_1;
                }
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName2;
                OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName;
                final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName3;
                final boolean b;
                final boolean b2;
                final BaseClassProcessor baseClassProcessor = (ocSymbol, p1) -> {
                    try {
                        if (ocSymbol instanceof OCStructSymbol) {
                            ocSymbolWithQualifiedName = ocSymbolWithQualifiedName2;
                            ocSymbolWithQualifiedName3.resolvedNamesEqual(ocSymbolWithQualifiedName);
                            if (!b) {
                                break Label_0025_1;
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            break Label_0025_1;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    try {
                        ocSymbolWithQualifiedName = ocSymbolWithQualifiedName2;
                        ocSymbolWithQualifiedName3.resolvedNamesEqual(ocSymbolWithQualifiedName);
                        if (!b) {
                            Label_0025_1: {
                                return b2;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw d(ex2);
                    }
                    return b2;
                };
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b3 = ocStructSymbol2.processAllBaseClasses(baseClassProcessor, ocResolveContext2);
                if (!b3) {
                    break Label_0025_1;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
            try {
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName5;
                OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName4;
                final OCSymbolWithQualifiedName<PsiElement> ocSymbolWithQualifiedName6;
                final boolean b4;
                final boolean b5;
                final BaseClassProcessor baseClassProcessor = (ocSymbol2, p1) -> {
                    try {
                        if (ocSymbol2 instanceof OCStructSymbol) {
                            ocSymbolWithQualifiedName4 = ocSymbolWithQualifiedName5;
                            ocSymbolWithQualifiedName6.resolvedNamesEqual(ocSymbolWithQualifiedName4);
                            if (!b4) {
                                break Label_0025_1;
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            break Label_0025_1;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw d(ex4);
                    }
                    try {
                        ocSymbolWithQualifiedName4 = ocSymbolWithQualifiedName5;
                        ocSymbolWithQualifiedName6.resolvedNamesEqual(ocSymbolWithQualifiedName4);
                        if (!b4) {
                            return b5;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw d(ex5);
                    }
                    return b5;
                };
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b3 = ocStructSymbol2.processAllBaseClasses(baseClassProcessor, ocResolveContext2);
                if (!b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw d(ex6);
            }
        }
        return false;
    }
    
    @NotNull
    public List<OCTypeArgument> getTemplateArguments(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getTemplateArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final ArrayList<OCExpansionPackType> list = (ArrayList<OCExpansionPackType>)new ArrayList<OCTypeParameterType>();
        final List<OCTypeArgument> templateSpecialization = this.getTemplateSpecialization();
        if (templateSpecialization != null) {
            for (final OCTypeArgument ocTypeArgument : templateSpecialization) {
                if (ocTypeArgument instanceof OCType) {
                    final OCType transformType = ((OCTypeParameterType)ocTypeArgument).transformType(new OCTypeResolveVisitor(ocResolveContext.substituteFirst(this.getSubstitution()), false));
                    Label_0155: {
                        try {
                            if (!(transformType instanceof OCStructType)) {
                                break Label_0155;
                            }
                            final OCType ocType = transformType;
                            final OCStructType ocStructType = (OCStructType)ocType;
                            final OCStructSymbol ocStructSymbol = ocStructType.getSymbol();
                            final OCStructSymbol ocStructSymbol2 = this;
                            final boolean b = ocStructSymbol.equals(ocStructSymbol2);
                            if (!b) {
                                break Label_0155;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw d(ex2);
                        }
                        try {
                            final OCType ocType = transformType;
                            final OCStructType ocStructType = (OCStructType)ocType;
                            final OCStructSymbol ocStructSymbol = ocStructType.getSymbol();
                            final OCStructSymbol ocStructSymbol2 = this;
                            final boolean b = ocStructSymbol.equals(ocStructSymbol2);
                            if (b) {
                                continue;
                            }
                            list.add((OCTypeParameterType)transformType);
                        }
                        catch (IllegalArgumentException ex3) {
                            throw d(ex3);
                        }
                    }
                }
                else {
                    list.add((OCTypeParameterType)ocTypeArgument);
                }
            }
        }
        else {
            for (final OCTypeParameterSymbol ocTypeParameterSymbol : this.getTemplateParameters()) {
                final OCTypeArgument substitution = this.getSubstitution().getSubstitutionFor(ocTypeParameterSymbol);
                try {
                    if (substitution instanceof OCExpansionPackType) {
                        list.addAll((Collection<?>)((OCExpansionPackType)substitution).getExpansions());
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw d(ex4);
                }
                try {
                    if (substitution != null) {
                        list.add((OCTypeParameterType)substitution);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw d(ex5);
                }
                list.add(new OCTypeParameterType(ocTypeParameterSymbol));
            }
        }
        ArrayList<OCExpansionPackType> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol", "getTemplateArguments"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw d(ex6);
        }
        return (List<OCTypeArgument>)list2;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCStructSymbol.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Property
    {
        IS_ENUM_CLASS;
        
        public static final int DEFAULT = 0;
        
        public int getMask() {
            assert this.ordinal() < 8;
            return 1 << 31 - this.ordinal();
        }
    }
    
    @FunctionalInterface
    public interface BaseClassProcessor
    {
        boolean process(final OCSymbol p0, final OCVisibility p1);
    }
}
