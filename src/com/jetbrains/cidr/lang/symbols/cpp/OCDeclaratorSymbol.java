// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import java.util.Iterator;
import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.util.OCDocUtil;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeParameterResolveVisitor;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Arrays;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.Collections;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiNameIdentifierOwner;

public class OCDeclaratorSymbol extends OCTemplateSymbolImpl<PsiNameIdentifierOwner>
{
    private OCType myType;
    private OCSymbolKind mySymbolKind;
    private int[] myArrayLengths;
    private int myPropertiesAndAttributes;
    @Nullable
    private TextRange myScope;
    @NotNull
    private List<OCTypeParameterSymbol> myTemplateParameters;
    @Nullable
    private List<OCTypeArgument> myTemplateSpecialization;
    @Nullable
    private OCMethodSymbol myParentMethod;
    protected OCExpressionSymbol myInitializer;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCDeclaratorSymbol() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCDeclaratorSymbol(final Project p0, final VirtualFile p1, final long p2, @Nullable final OCSymbolWithQualifiedName p3, @NotNull final OCQualifiedName p4, @NotNull final List<String> p5, @NotNull final OCType p6, final OCSymbolKind p7, final int[] p8, @Nullable final OCExpressionSymbol p9, @NotNull final List<OCTypeParameterSymbol> p10, @Nullable final List<OCTypeArgument> p11, final int p12, final int p13, @Nullable final TextRange p14, @Nullable final OCVisibility p15) {
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
        //    25: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol"
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
        //    66: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol"
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
        //   101: ldc             "type"
        //   103: aastore        
        //   104: dup            
        //   105: ldc             1
        //   107: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             2
        //   113: ldc             "<init>"
        //   115: aastore        
        //   116: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   119: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   122: athrow         
        //   123: aload           12
        //   125: ifnonnull       164
        //   128: new             Ljava/lang/IllegalArgumentException;
        //   131: dup            
        //   132: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   134: ldc             3
        //   136: anewarray       Ljava/lang/Object;
        //   139: dup            
        //   140: ldc             0
        //   142: ldc             "templateParameters"
        //   144: aastore        
        //   145: dup            
        //   146: ldc             1
        //   148: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             2
        //   154: ldc             "<init>"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: aload_0        
        //   165: aload_1        
        //   166: aload_2        
        //   167: lload_3        
        //   168: aload           5
        //   170: aload           6
        //   172: aload           7
        //   174: aload           17
        //   176: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   179: aload_0        
        //   180: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   183: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   186: aload_0        
        //   187: aload           8
        //   189: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   192: aload_0        
        //   193: aload           10
        //   195: arraylength    
        //   196: ifle            204
        //   199: aload           10
        //   201: goto            207
        //   204: getstatic       com/intellij/util/ArrayUtil.EMPTY_INT_ARRAY:[I
        //   207: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myArrayLengths:[I
        //   210: aload_0        
        //   211: aload           11
        //   213: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myInitializer:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   216: aload_0        
        //   217: aload           9
        //   219: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   222: aload_0        
        //   223: iload           14
        //   225: iload           15
        //   227: ior            
        //   228: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myPropertiesAndAttributes:I
        //   231: aload_0        
        //   232: aload           12
        //   234: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myTemplateParameters:Ljava/util/List;
        //   237: aload_0        
        //   238: aload           13
        //   240: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myTemplateSpecialization:Ljava/util/List;
        //   243: aload_0        
        //   244: aload           16
        //   246: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myScope:Lcom/intellij/openapi/util/TextRange;
        //   249: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.$assertionsDisabled:Z
        //   252: ifne            298
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myScope:Lcom/intellij/openapi/util/TextRange;
        //   259: ifnull          298
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   273: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTemplateParameter:()Z
        //   276: ifeq            298
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: new             Ljava/lang/AssertionError;
        //   289: dup            
        //   290: invokespecial   java/lang/AssertionError.<init>:()V
        //   293: athrow         
        //   294: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List<Ljava/lang/String;>;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  207    262    265    269    Ljava/lang/IllegalArgumentException;
        //  255    279    282    286    Ljava/lang/IllegalArgumentException;
        //  269    294    294    298    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0269:
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
    
    public OCDeclaratorSymbol(final Project project, final VirtualFile virtualFile, final int n, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final String s, @NotNull final List<String> list, @NotNull final OCType ocType, final OCSymbolKind ocSymbolKind) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "<init>"));
        }
        if (ocType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "<init>"));
        }
        this(project, virtualFile, n, ocSymbolWithQualifiedName, OCQualifiedName.interned(s), list, ocType, ocSymbolKind);
    }
    
    public OCDeclaratorSymbol(final Project project, final VirtualFile virtualFile, final int n, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCQualifiedName ocQualifiedName, @NotNull final List<String> list, @NotNull final OCType ocType, final OCSymbolKind ocSymbolKind) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "<init>"));
        }
        if (ocType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "<init>"));
        }
        this(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, ocType, ocSymbolKind, ArrayUtil.EMPTY_INT_ARRAY, null, (List<OCTypeParameterSymbol>)Collections.emptyList(), Collections.emptyList(), 0, 0, null, null);
    }
    
    public OCDeclaratorSymbol(final OCDeclaratorSymbol ocDeclaratorSymbol, final OCTypeSubstitution ocTypeSubstitution, final OCQualifiedName ocQualifiedName, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "<init>"));
        }
        super(ocDeclaratorSymbol.getProject(), ocDeclaratorSymbol.getContainingFile(), ocDeclaratorSymbol.getOffset(), ocSymbolWithQualifiedName, ocQualifiedName, ocDeclaratorSymbol.getAttributes(), ocDeclaratorSymbol.getVisibility());
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myType = ocDeclaratorSymbol.getType();
        this.myArrayLengths = ocDeclaratorSymbol.getArrayLengths();
        this.myInitializer = ocDeclaratorSymbol.getInitializer();
        this.mySymbolKind = ocDeclaratorSymbol.getKind();
        this.myPropertiesAndAttributes = ocDeclaratorSymbol.myPropertiesAndAttributes;
        this.myScope = ocDeclaratorSymbol.getScope();
        this.mySubstitution = OCTypeSubstitution.compose(ocDeclaratorSymbol.mySubstitution, ocTypeSubstitution, ocResolveContext);
        this.myTemplateParameters = ocDeclaratorSymbol.getTemplateParameters();
        this.myTemplateSpecialization = ocDeclaratorSymbol.getTemplateSpecialization();
        if (!OCDeclaratorSymbol.$assertionsDisabled) {
            Label_0184: {
                try {
                    if (this.myScope == null) {
                        return;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = this;
                    final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol2.mySymbolKind;
                    final boolean b = ocSymbolKind.isTemplateParameter();
                    if (b) {
                        break Label_0184;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = this;
                    final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol2.mySymbolKind;
                    final boolean b = ocSymbolKind.isTemplateParameter();
                    if (b) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
        }
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "deepEqualStep"));
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
        final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)o;
        final OCDeclaratorSymbol ocDeclaratorSymbol2 = (OCDeclaratorSymbol)o2;
        try {
            if (ocDeclaratorSymbol.mySymbolKind != ocDeclaratorSymbol2.mySymbolKind) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw d(ex5);
        }
        try {
            if (ocDeclaratorSymbol.myPropertiesAndAttributes != ocDeclaratorSymbol2.myPropertiesAndAttributes) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw d(ex6);
        }
        try {
            if (!Comparing.equal((Object)ocDeclaratorSymbol.myScope, (Object)ocDeclaratorSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw d(ex7);
        }
        try {
            if (!Arrays.equals(ocDeclaratorSymbol.myArrayLengths, ocDeclaratorSymbol2.myArrayLengths)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw d(ex8);
        }
        try {
            if (!comparator.equalObjects(ocDeclaratorSymbol.myInitializer, (DeepEqual.Equality<Object>)ocDeclaratorSymbol2.myInitializer)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw d(ex9);
        }
        try {
            if (!comparator.equalObjects(ocDeclaratorSymbol.myParentMethod, (DeepEqual.Equality<Object>)ocDeclaratorSymbol2.myParentMethod)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw d(ex10);
        }
        try {
            if (!comparator.equalObjects(ocDeclaratorSymbol.mySubstitution, (DeepEqual.Equality<Object>)ocDeclaratorSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw d(ex11);
        }
        try {
            if (!comparator.equalIterable(ocDeclaratorSymbol.myTemplateParameters, ocDeclaratorSymbol2.myTemplateParameters)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex12) {
            throw d(ex12);
        }
        try {
            if (!comparator.equalIterable(ocDeclaratorSymbol.myTemplateSpecialization, ocDeclaratorSymbol2.myTemplateSpecialization)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex13) {
            throw d(ex13);
        }
        try {
            if (!comparator.equalObjects(ocDeclaratorSymbol.myType, (DeepEqual.Equality<Object>)ocDeclaratorSymbol2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex14) {
            throw d(ex14);
        }
        return true;
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
    public boolean isTopLevel() {
        Label_0021: {
            try {
                if (this.myScope != null) {
                    return false;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final OCSymbolWithQualifiedName<PsiNameIdentifierOwner> ocSymbolWithQualifiedName = ocDeclaratorSymbol.getParent();
                if (ocSymbolWithQualifiedName == null) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final OCSymbolWithQualifiedName<PsiNameIdentifierOwner> ocSymbolWithQualifiedName = ocDeclaratorSymbol.getParent();
                if (ocSymbolWithQualifiedName == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType substitute;
        try {
            substitute = this.mySubstitution.substitute(this.myType, new OCResolveContext());
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return substitute;
    }
    
    public void setType(final OCFunctionType myType) {
        this.myType = myType;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public boolean isTemplateSymbol() {
        Label_0026: {
            try {
                if (!this.myTemplateParameters.isEmpty()) {
                    break Label_0026;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final List<OCTypeArgument> list = ocDeclaratorSymbol.myTemplateSpecialization;
                if (list != null) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final List<OCTypeArgument> list = ocDeclaratorSymbol.myTemplateSpecialization;
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
    
    @NotNull
    @Override
    public List<OCTypeParameterSymbol> getTemplateParameters() {
        List<OCTypeParameterSymbol> myTemplateParameters;
        try {
            myTemplateParameters = this.myTemplateParameters;
            if (myTemplateParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getTemplateParameters"));
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
    public String getSignature() {
        final OCType ocType = this.getType().accept((OCTypeVisitor<OCType>)new OCTypeParameterResolveVisitor((PsiFile)this.getContainingOCFile()));
        String parameterSignature;
        try {
            parameterSignature = OCDocUtil.parameterSignature(ocType.getName(), this.getName());
            if (parameterSignature == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return parameterSignature;
    }
    
    @Override
    protected Class<? extends PsiNameIdentifierOwner> getPsiElementClass() {
        try {
            if (this.myParentMethod != null) {
                final Serializable psiElementClass = OCMethodSelectorPart.class;
                return (Class<? extends PsiNameIdentifierOwner>)psiElementClass;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final Serializable psiElementClass = super.getPsiElementClass();
        return (Class<? extends PsiNameIdentifierOwner>)psiElementClass;
    }
    
    public void setType(@NotNull final OCType myType) {
        try {
            if (myType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "setType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        this.myType = myType;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind mySymbolKind;
        try {
            mySymbolKind = this.mySymbolKind;
            if (mySymbolKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySymbolKind;
    }
    
    @Override
    public boolean isConst() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //     4: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)Z
        //     7: ifne            41
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConstexpr:()Z
        //    14: ifne            41
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    28: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    31: if_acmpne       49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     34     37     41     Ljava/lang/IllegalArgumentException;
        //  24     45     45     49     Ljava/lang/IllegalArgumentException;
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
    
    public boolean isConstexpr() {
        return this.hasAttribute(OCSymbolAttribute.CONSTEXPR);
    }
    
    public boolean isMutable() {
        return this.hasAttribute(OCSymbolAttribute.MUTABLE);
    }
    
    @Override
    public boolean isStatic() {
        return this.hasAttribute(OCSymbolAttribute.STATIC);
    }
    
    @Override
    public boolean isFriend() {
        return this.hasAttribute(OCSymbolAttribute.FRIEND);
    }
    
    public boolean isExtern() {
        return this.hasAttribute(OCSymbolAttribute.EXTERN);
    }
    
    public boolean isBlockModifiable() {
        return this.hasAttribute(OCSymbolAttribute.BLOCK_MODIFIABLE);
    }
    
    public boolean isPassByReference() {
        return this.hasProperty(Property.IS_PASS_BY_REF);
    }
    
    public boolean hasInitializer() {
        return this.hasProperty(Property.IS_HAS_INITIALIZER);
    }
    
    public boolean isDeclaredInLambdaCapture() {
        return this.hasProperty(Property.IS_DECLARED_IN_LAMBDA_CAPTURE);
    }
    
    public boolean hasAttribute(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
        try {
            if (ocSymbolAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attribute", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "hasAttribute"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "property", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "hasProperty"));
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
    
    @Nullable
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @Override
    public boolean isDefinition() {
        Label_0021: {
            try {
                if (!super.isDefinition()) {
                    return false;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final TextRange textRange = ocDeclaratorSymbol.myScope;
                if (textRange == null) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final TextRange textRange = ocDeclaratorSymbol.myScope;
                if (textRange == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public int[] getArrayLengths() {
        int[] myArrayLengths;
        try {
            myArrayLengths = this.myArrayLengths;
            if (myArrayLengths == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol", "getArrayLengths"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return myArrayLengths;
    }
    
    public OCExpressionSymbol getInitializer() {
        return this.myInitializer;
    }
    
    @Nullable
    public OCDeclaratorSymbol getDeclarationInParent() {
        try {
            if (this.getQualifier() == null) {
                return this;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final OCSymbolWithQualifiedName<PsiNameIdentifierOwner> resolvedOwner = this.getResolvedOwner();
        final Ref ref = new Ref((Object)null);
        try {
            if (resolvedOwner instanceof OCStructSymbol) {
                ((OCStructSymbol)resolvedOwner).processMembers(this.getName(), (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (ocSymbol instanceof OCDeclaratorSymbol) {
                            ref.set((Object)ocSymbol);
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return (OCDeclaratorSymbol)ref.get();
    }
    
    public boolean resolveIsStatic() {
        Label_0021: {
            try {
                if (this.isFriendOrStatic()) {
                    break Label_0021;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final OCQualifiedName ocQualifiedName = ocDeclaratorSymbol.getQualifier();
                if (ocQualifiedName == null) {
                    break Label_0021;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = this;
                final OCQualifiedName ocQualifiedName = ocDeclaratorSymbol.getQualifier();
                if (ocQualifiedName == null) {
                    return this.isFriendOrStatic();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        final OCDeclaratorSymbol declarationInParent = this.getDeclarationInParent();
        Label_0053: {
            try {
                if (declarationInParent == null) {
                    return false;
                }
                final OCDeclaratorSymbol ocDeclaratorSymbol2 = declarationInParent;
                final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                if (b) {
                    break Label_0053;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol2 = declarationInParent;
                final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
        }
        return false;
    }
    
    @Override
    public Icon getBaseIcon() {
        Icon icon = this.getKind().getIcon();
        Label_0037: {
            try {
                if (!this.isConst() || this.getKind() == OCSymbolKind.ENUM_CONST) {
                    break Label_0037;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            icon = OCIcons.getConstIcon(icon);
        }
        if (this.isFriendOrStatic()) {
            icon = OCIcons.getStaticIcon(icon);
        }
        return OCIcons.getVisibilityIcon(this.getVisibility(), icon);
    }
    
    public void setParentMethod(@Nullable final OCMethodSymbol myParentMethod) {
        this.myParentMethod = myParentMethod;
    }
    
    @Override
    public boolean processAssociatedSymbols(final Processor<OCSymbol> processor) {
        try {
            if (this.mySymbolKind != OCSymbolKind.PARAMETER) {
                return super.processAssociatedSymbols(processor);
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        if (this.myParentMethod != null) {
            final OCMethodSymbol associatedSymbol = this.myParentMethod.getAssociatedSymbol();
            try {
                if (associatedSymbol == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            int n = 0;
            for (final OCMethodSymbol.SelectorPartSymbol selectorPartSymbol : this.myParentMethod.getSelectors()) {
                try {
                    if (this.equals(selectorPartSymbol.getParameter())) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
                ++n;
            }
            try {
                if (n >= this.myParentMethod.getSelectors().size()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
            return processor.process((Object)associatedSymbol.getSelectors().get(n).getParameter());
        }
        return !(this.myParent instanceof OCFunctionSymbol) || this.myParent.processAssociatedSymbols(p2 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //     4: ifeq            66
            //     7: iload_0        
            //     8: iconst_m1      
            //     9: if_icmpeq       66
            //    12: goto            19
            //    15: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    18: athrow         
            //    19: iload_0        
            //    20: aload_2        
            //    21: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    24: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
            //    27: invokeinterface java/util/List.size:()I
            //    32: if_icmpge       66
            //    35: goto            42
            //    38: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    41: athrow         
            //    42: aload_1        
            //    43: aload_2        
            //    44: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    47: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
            //    50: iload_0        
            //    51: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //    56: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //    61: ireturn        
            //    62: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    65: athrow         
            //    66: iconst_1       
            //    67: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      12     15     19     Ljava/lang/IllegalArgumentException;
            //  7      35     38     42     Ljava/lang/IllegalArgumentException;
            //  19     62     62     66     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0019:
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
        });
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     7: if_acmpne       129
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myParentMethod:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    14: ifnull          77
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    28: astore_3       
        //    29: aload_1        
        //    30: aload_0        
        //    31: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    36: ifeq            75
        //    39: aload_3        
        //    40: ifnull          67
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_1        
        //    51: aload_3        
        //    52: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    57: ifeq            75
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: iconst_1       
        //    68: goto            76
        //    71: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: iconst_0       
        //    76: ireturn        
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    81: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    84: ifeq            129
        //    87: aload_1        
        //    88: aload_0        
        //    89: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    94: ifeq            127
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_0        
        //   105: aload_1        
        //   106: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.processAssociatedSymbols:(Lcom/intellij/util/Processor;)Z
        //   109: ifeq            127
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: iconst_1       
        //   120: goto            128
        //   123: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: iconst_0       
        //   128: ireturn        
        //   129: aload_0        
        //   130: aload_1        
        //   131: aload_2        
        //   132: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbolImpl.processSameSymbols:(Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiFile;)Z
        //   135: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/intellij/psi/PsiFile;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  29     43     46     50     Ljava/lang/IllegalArgumentException;
        //  39     60     63     67     Ljava/lang/IllegalArgumentException;
        //  50     71     71     75     Ljava/lang/IllegalArgumentException;
        //  77     97     100    104    Ljava/lang/IllegalArgumentException;
        //  87     112    115    119    Ljava/lang/IllegalArgumentException;
        //  104    123    123    127    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCDeclaratorSymbol.class.desiredAssertionStatus()) {
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
        IS_PASS_BY_REF, 
        IS_HAS_INITIALIZER, 
        IS_DECLARED_IN_LAMBDA_CAPTURE;
        
        public static final int DEFAULT = 0;
        
        public int getMask() {
            assert this.ordinal() < 8;
            return 1 << 31 - this.ordinal();
        }
    }
}
