// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import javax.swing.Icon;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCElement;

public class OCFunctionSymbol extends OCTemplateSymbolImpl<OCElement>
{
    public static final String ATTRIBUTE_IMPLICIT_BRIDGING = "ImplicitBridging";
    private OCFunctionType myType;
    @NotNull
    private List<OCTypeParameterSymbol> myTemplateParameters;
    @Nullable
    private List<OCTypeArgument> myTemplateSpecialization;
    @NotNull
    private List<OCDeclaratorSymbol> myParameterSymbols;
    private OCSymbolKind myKind;
    private int myPropertiesAndAttributes;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    
    public OCFunctionSymbol() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCFunctionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final List<OCTypeParameterSymbol> myTemplateParameters, @Nullable final List<OCTypeArgument> myTemplateSpecialization, final int n2, final int n3, @NotNull final List<String> list, @NotNull final OCFunctionType myType, @NotNull final List<OCDeclaratorSymbol> myParameterSymbols, final OCSymbolKind myKind, @Nullable final OCVisibility ocVisibility) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        if (myTemplateParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateParameters", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        if (myParameterSymbols == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterSymbols", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, ocVisibility);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myType = myType;
        this.myTemplateParameters = myTemplateParameters;
        this.myTemplateSpecialization = myTemplateSpecialization;
        this.myParameterSymbols = myParameterSymbols;
        this.myKind = myKind;
        this.myPropertiesAndAttributes = (n2 | n3);
    }
    
    public OCFunctionSymbol(final OCFunctionSymbol ocFunctionSymbol) {
        this(ocFunctionSymbol, ocFunctionSymbol.getParent());
    }
    
    public OCFunctionSymbol(final OCFunctionSymbol ocFunctionSymbol, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        super(ocFunctionSymbol.getProject(), ocFunctionSymbol.getContainingFile(), ocFunctionSymbol.getOffset(), ocSymbolWithQualifiedName, ocFunctionSymbol.getQualifiedName(), ocFunctionSymbol.getAttributes(), ocFunctionSymbol.getVisibility());
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myType = ocFunctionSymbol.myType;
        this.myTemplateParameters = ocFunctionSymbol.myTemplateParameters;
        this.myTemplateSpecialization = ocFunctionSymbol.myTemplateSpecialization;
        this.myParameterSymbols = ocFunctionSymbol.myParameterSymbols;
        this.myKind = ocFunctionSymbol.myKind;
        this.myPropertiesAndAttributes = ocFunctionSymbol.myPropertiesAndAttributes;
        this.mySubstitution = ocFunctionSymbol.mySubstitution;
    }
    
    public OCFunctionSymbol(final OCFunctionSymbol ocFunctionSymbol, final OCTypeSubstitution ocTypeSubstitution, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "<init>"));
        }
        this(ocFunctionSymbol, ocSymbolWithQualifiedName);
        this.mySubstitution = OCTypeSubstitution.compose(ocFunctionSymbol.mySubstitution, ocTypeSubstitution, ocResolveContext);
    }
    
    @Override
    public boolean isConst() {
        return this.myType.isConst();
    }
    
    public boolean isVolatile() {
        return this.myType.isVolatile();
    }
    
    public boolean isConstexpr() {
        return this.hasAttribute(OCSymbolAttribute.CONSTEXPR);
    }
    
    public boolean isVirtual() {
        return this.hasAttribute(OCSymbolAttribute.VIRTUAL);
    }
    
    @Override
    public boolean isStatic() {
        return this.hasAttribute(OCSymbolAttribute.STATIC);
    }
    
    public boolean isExplicit() {
        return this.hasAttribute(OCSymbolAttribute.EXPLICIT);
    }
    
    public boolean isDefault() {
        return this.hasAttribute(OCSymbolAttribute.DEFAULT);
    }
    
    public boolean isDelete() {
        return this.hasAttribute(OCSymbolAttribute.DELETE);
    }
    
    @Override
    public boolean isFriend() {
        return this.hasAttribute(OCSymbolAttribute.FRIEND);
    }
    
    public boolean isFinal() {
        return this.hasAttribute(OCSymbolAttribute.FINAL);
    }
    
    public boolean isOverride() {
        return this.hasAttribute(OCSymbolAttribute.OVERRIDE);
    }
    
    public boolean isInline() {
        return this.hasAttribute(OCSymbolAttribute.INLINE);
    }
    
    public boolean isExtern() {
        return this.hasAttribute(OCSymbolAttribute.EXTERN);
    }
    
    public boolean isPureVirtual() {
        return this.hasProperty(Property.IS_PURE_VIRTUAL);
    }
    
    public boolean isCppConversionOperator() {
        return this.hasProperty(Property.IS_CONVERSION_OPERATOR);
    }
    
    @Override
    public boolean isTemplateSymbol() {
        return this.hasProperty(Property.IS_TEMPLATE);
    }
    
    public boolean isCppOperator() {
        return this.hasProperty(Property.IS_OPERATOR);
    }
    
    public boolean isUDL() {
        return this.hasProperty(Property.IS_UDL);
    }
    
    public boolean isVararg() {
        return this.myType.isVararg();
    }
    
    public boolean hasAttribute(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
        try {
            if (ocSymbolAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attr", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "hasAttribute"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "property", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "hasProperty"));
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
    
    public boolean isCppMemberOperator(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isCppMemberOperator"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //    48: ifeq            92
        //    51: aload_0        
        //    52: aload_1        
        //    53: iconst_0       
        //    54: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    57: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    60: ifeq            92
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_0        
        //    71: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //    74: ifne            92
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: iconst_1       
        //    85: goto            93
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: iconst_0       
        //    93: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     77     80     84     Ljava/lang/IllegalArgumentException;
        //  70     88     88     92     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    public boolean isCppNonMemberOperator(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "isCppNonMemberOperator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Label_0066: {
            try {
                if (!this.isCppOperator()) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocFunctionSymbol.isCppMemberOperator(ocResolveContext2);
                if (!b) {
                    break Label_0066;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocFunctionSymbol.isCppMemberOperator(ocResolveContext2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return false;
    }
    
    public void setType(final OCFunctionType myType) {
        this.myType = myType;
    }
    
    @Nullable
    public OCFunctionDeclaration locateFunctionDefinition() {
        final OCElement ocElement = this.locateDefinition();
        Object parent = null;
        Label_0026: {
            try {
                if (ocElement != null) {
                    parent = ocElement.getParent();
                    break Label_0026;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            parent = null;
        }
        final Object o = parent;
        try {
            if (o instanceof OCFunctionDeclaration) {
                return (OCFunctionDeclaration)o;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return null;
    }
    
    @NotNull
    @Override
    public List<OCTypeParameterSymbol> getTemplateParameters() {
        List<OCTypeParameterSymbol> myTemplateParameters;
        try {
            myTemplateParameters = this.myTemplateParameters;
            if (myTemplateParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getTemplateParameters"));
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
    
    public boolean isCppConstructor() {
        Label_0024: {
            try {
                if (!this.getKind().isConstructorOrDestructor()) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol = this;
                final boolean b = ocFunctionSymbol.isCppDestructor();
                if (!b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = this;
                final boolean b = ocFunctionSymbol.isCppDestructor();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    public boolean isCppDestructor() {
        return this.getName().startsWith("~");
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "deepEqualStep"));
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
        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)o;
        final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)o2;
        try {
            if (ocFunctionSymbol.myPropertiesAndAttributes != ocFunctionSymbol2.myPropertiesAndAttributes) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw d(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocFunctionSymbol.myKind, (Object)ocFunctionSymbol2.myKind)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw d(ex6);
        }
        try {
            if (!comparator.equalIterable(ocFunctionSymbol.myParameterSymbols, ocFunctionSymbol2.myParameterSymbols)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw d(ex7);
        }
        try {
            if (!comparator.equalObjects(ocFunctionSymbol.mySubstitution, (DeepEqual.Equality<Object>)ocFunctionSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw d(ex8);
        }
        try {
            if (!comparator.equalIterable(ocFunctionSymbol.myTemplateParameters, ocFunctionSymbol2.myTemplateParameters)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw d(ex9);
        }
        try {
            if (!comparator.equalIterable(ocFunctionSymbol.myTemplateSpecialization, ocFunctionSymbol2.myTemplateSpecialization)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw d(ex10);
        }
        try {
            if (!comparator.equalObjects(ocFunctionSymbol.myType, (DeepEqual.Equality<Object>)ocFunctionSymbol2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw d(ex11);
        }
        return true;
    }
    
    @Nullable
    public OCFunctionSymbol getDeclarationInParent() {
        try {
            if (this.isPredeclaration()) {
                return this;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final OCSymbolWithQualifiedName<OCElement> resolvedOwner = this.getResolvedOwner();
        final Ref ref = new Ref((Object)null);
        if (resolvedOwner instanceof OCStructSymbol) {
            ((OCStructSymbol)resolvedOwner).processMembers(this.getName(), (Processor<OCSymbol>)(ocSymbol -> {
                if (ocSymbol instanceof OCFunctionSymbol) {
                    final OCType resolve = ocSymbol.getType().resolve((PsiFile)this.getContainingOCFile());
                    try {
                        if (!new OCTypeEqualityVisitor(resolve, true, new OCResolveContext()).equal(ocType)) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    ref.set((Object)ocSymbol);
                    return false;
                }
                return true;
            }));
        }
        return (OCFunctionSymbol)ref.get();
    }
    
    public boolean resolveIsStatic() {
        Label_0021: {
            try {
                if (this.isFriendOrStatic()) {
                    break Label_0021;
                }
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCQualifiedName ocQualifiedName = ocFunctionSymbol.getQualifier();
                if (ocQualifiedName == null) {
                    break Label_0021;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCQualifiedName ocQualifiedName = ocFunctionSymbol.getQualifier();
                if (ocQualifiedName == null) {
                    return this.isFriendOrStatic();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        final OCFunctionSymbol declarationInParent = this.getDeclarationInParent();
        Label_0053: {
            try {
                if (declarationInParent == null) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol2 = declarationInParent;
                final boolean b = ocFunctionSymbol2.isFriendOrStatic();
                if (b) {
                    break Label_0053;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol2 = declarationInParent;
                final boolean b = ocFunctionSymbol2.isFriendOrStatic();
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
    public void updateOffset(final int n, final int n2, final int n3) {
        super.updateOffset(n, n2, n3);
        final Iterator<OCTypeParameterSymbol> iterator = this.myTemplateParameters.iterator();
        while (iterator.hasNext()) {
            ((OCSymbol)iterator.next()).updateOffset(n, n2, n3);
        }
        final Iterator<OCDeclaratorSymbol> iterator2 = this.myParameterSymbols.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().updateOffset(n, n2, n3);
        }
    }
    
    @Override
    public void compact() {
        super.compact();
        final Iterator<OCTypeParameterSymbol> iterator = this.myTemplateParameters.iterator();
        while (iterator.hasNext()) {
            ((OCSymbol)iterator.next()).compact();
        }
        final Iterator<OCDeclaratorSymbol> iterator2 = this.myParameterSymbols.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().compact();
        }
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind myKind;
        try {
            myKind = this.myKind;
            if (myKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return myKind;
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //     7: ifeq            60
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
        //    14: ifne            60
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    28: ifne            60
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //    42: ifne            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_1       
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: iconst_0       
        //    61: istore_1       
        //    62: aload_0        
        //    63: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //    66: aload_0        
        //    67: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPureVirtual:()Z
        //    70: iload_1        
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    75: invokestatic    com/jetbrains/cidr/lang/OCIcons.getFunctionIcon:(ZZZLcom/jetbrains/cidr/lang/symbols/OCVisibility;)Ljavax/swing/Icon;
        //    78: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     56     56     60     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public Icon computeFullIconNow(@Nullable final OCElement ocElement) {
        final Icon computeFullIconNow = super.computeFullIconNow(ocElement);
        try {
            if (computeFullIconNow == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (OCTestFrameworks.isTestMethodOrFunction(this)) {
                return OCIcons.getTestIcon(computeFullIconNow);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return computeFullIconNow;
    }
    
    @Override
    public boolean isGlobal() {
        return true;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySubstitution;
    }
    
    @NotNull
    public OCFunctionSymbol clearSubstitution() {
        final OCFunctionSymbol ocFunctionSymbol = new OCFunctionSymbol(this);
        OCFunctionSymbol ocFunctionSymbol2;
        try {
            ocFunctionSymbol.mySubstitution = OCTypeSubstitution.ID;
            ocFunctionSymbol2 = ocFunctionSymbol;
            if (ocFunctionSymbol2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "clearSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocFunctionSymbol2;
    }
    
    public int getFunctionAttributes() {
        return 0xFFFFFF & this.myPropertiesAndAttributes;
    }
    
    public int getFunctionProperties() {
        return 0xFF000000 & this.myPropertiesAndAttributes;
    }
    
    @NotNull
    @Override
    public OCFunctionType getType() {
        OCFunctionType ocFunctionType;
        try {
            ocFunctionType = (OCFunctionType)this.mySubstitution.substitute((OCType)this.myType, new OCResolveContext());
            if (ocFunctionType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocFunctionType;
    }
    
    @NotNull
    public OCFunctionType getTypeWithoutSubstitution() {
        OCFunctionType myType;
        try {
            myType = this.myType;
            if (myType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getTypeWithoutSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return myType;
    }
    
    @NotNull
    public List<OCDeclaratorSymbol> getParameterSymbols() {
        List<OCDeclaratorSymbol> substitute;
        try {
            substitute = this.mySubstitution.substitute(this.myParameterSymbols, new OCResolveContext());
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getParameterSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return substitute;
    }
    
    @NotNull
    public List<OCDeclaratorSymbol> getParameterSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getParameterSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final ArrayList<OCDeclaratorSymbol> list = new ArrayList<OCDeclaratorSymbol>();
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : this.mySubstitution.substitute(this.myParameterSymbols, ocResolveContext)) {
            final OCType terminalType = ocDeclaratorSymbol.getType().getTerminalType();
            if (terminalType instanceof OCVariadicType) {
                final int size = OCArgumentsList.expandVariadicTypes((List<OCTypeArgument>)Collections.singletonList((T)terminalType), ocResolveContext).size();
                int i = 0;
                try {
                    while (i < size) {
                        list.add(ocDeclaratorSymbol);
                        ++i;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
            else {
                list.add(ocDeclaratorSymbol);
            }
        }
        ArrayList<OCDeclaratorSymbol> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getParameterSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        return list2;
    }
    
    public int getNonInitializedParametersCount(@Nullable final OCResolveContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.myName:Ljava/lang/String;
        //     4: ldc             "operator++"
        //     6: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //     9: ifne            31
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.myName:Ljava/lang/String;
        //    16: ldc             "operator--"
        //    18: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    21: ifeq            92
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: ifnull          86
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_1        
        //    43: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    46: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    49: ifeq            86
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_1        
        //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    63: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    66: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //    71: ldc             "operator"
        //    73: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    76: ifne            92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_0       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.myType:Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    96: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //    99: ifeq            108
        //   102: iconst_0       
        //   103: ireturn        
        //   104: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_0       
        //   109: istore_2       
        //   110: aload_0        
        //   111: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   114: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   119: astore_3       
        //   120: aload_3        
        //   121: invokeinterface java/util/Iterator.hasNext:()Z
        //   126: ifeq            257
        //   129: aload_3        
        //   130: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   135: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   138: astore          4
        //   140: aload           4
        //   142: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   145: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   148: astore          5
        //   150: aload           4
        //   152: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.hasInitializer:()Z
        //   155: ifne            257
        //   158: aload           5
        //   160: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   163: ifeq            180
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: goto            257
        //   176: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload           5
        //   182: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   185: ifeq            251
        //   188: aload_1        
        //   189: ifnull          254
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload           5
        //   201: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   204: aload_1        
        //   205: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.expandVariadicTypes:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   208: astore          6
        //   210: aload           6
        //   212: invokeinterface java/util/List.size:()I
        //   217: iconst_1       
        //   218: if_icmpne       238
        //   221: aload           6
        //   223: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   226: aload           5
        //   228: if_acmpeq       248
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: iload_2        
        //   239: aload           6
        //   241: invokeinterface java/util/List.size:()I
        //   246: iadd           
        //   247: istore_2       
        //   248: goto            254
        //   251: iinc            2, 1
        //   254: goto            120
        //   257: iload_2        
        //   258: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  12     35     38     42     Ljava/lang/IllegalArgumentException;
        //  31     52     55     59     Ljava/lang/IllegalArgumentException;
        //  42     79     82     86     Ljava/lang/IllegalArgumentException;
        //  59     88     88     92     Ljava/lang/IllegalArgumentException;
        //  92     104    104    108    Ljava/lang/IllegalArgumentException;
        //  150    166    169    173    Ljava/lang/IllegalArgumentException;
        //  158    176    176    180    Ljava/lang/IllegalArgumentException;
        //  180    192    195    199    Ljava/lang/IllegalArgumentException;
        //  210    231    234    238    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    public OCType getEffectiveType() {
        return this.mySubstitution.substitute(this.myType.getReturnType(), new OCResolveContext());
    }
    
    @NotNull
    @Override
    public OCType getEffectiveResolvedType() {
        final OCType resolvedType = this.getResolvedType();
        OCType returnType;
        if (resolvedType instanceof OCFunctionType) {
            returnType = ((OCFunctionType)resolvedType).getReturnType();
        }
        else {
            returnType = resolvedType;
        }
        OCType substitute;
        try {
            substitute = this.mySubstitution.substitute(returnType, new OCResolveContext());
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getEffectiveResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return substitute;
    }
    
    public boolean canBeCalledWithoutArguments() {
        try {
            if (this.getNonInitializedParametersCount(null) == 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    public boolean isMainFunction() {
        Label_0026: {
            try {
                if (!"main".equals(this.myName)) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol.getResolvedOwner();
                if (ocSymbolWithQualifiedName == null) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = this;
                final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = ocFunctionSymbol.getResolvedOwner();
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
    
    @Override
    public String getKindUppercase() {
        try {
            if (this.isCppDestructor()) {
                return "Destructor";
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return super.getKindUppercase();
    }
    
    @NotNull
    @Override
    public String getSignature() {
        final StringBuilder sb = new StringBuilder();
        final String name = this.getType().getReturnType().getName();
        try {
            sb.append(name);
            if (!name.endsWith("*")) {
                sb.append(" ");
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        String string;
        try {
            sb.append(this.getName());
            sb.append(this.getParametersSignature());
            this.getType().getCVQualifiers().appendCVQualifiers(sb);
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getSignature"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return string;
    }
    
    @NotNull
    public String getParametersSignature() {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        int n = 1;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : this.getParameterSymbols()) {
            try {
                if (n == 0) {
                    sb.append(", ");
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            sb.append(ocDeclaratorSymbol.getSignature());
            n = 0;
        }
        String string;
        try {
            sb.append(')');
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getParametersSignature"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return string;
    }
    
    public String getNiceAttributes() {
        final ArrayList<String> list = new ArrayList<String>();
        for (final OCSymbolAttribute ocSymbolAttribute : OCSymbolAttribute.values()) {
            try {
                if (this.hasAttribute(ocSymbolAttribute)) {
                    list.add(ocSymbolAttribute.name().toLowerCase());
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
        }
        for (final Property property : Property.values()) {
            try {
                if (this.hasProperty(property)) {
                    list.add(property.name().substring(3));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return "\u300a" + StringUtil.join((Collection)list, " ") + "\u300b";
    }
    
    @Override
    public String toString() {
        return this.getKind() + this.getNiceAttributes() + "[" + this.getName() + "]@" + this.getOffset();
    }
    
    @NotNull
    @Override
    public String getPresentableText() {
        String signatureWithoutParamNames;
        try {
            signatureWithoutParamNames = this.getSignatureWithoutParamNames();
            if (signatureWithoutParamNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getPresentableText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return signatureWithoutParamNames;
    }
    
    @NotNull
    public String getSignatureWithoutParamNames() {
        String signatureWithoutParamNames;
        try {
            signatureWithoutParamNames = this.getSignatureWithoutParamNames(false, true);
            if (signatureWithoutParamNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getSignatureWithoutParamNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return signatureWithoutParamNames;
    }
    
    @NotNull
    public String getSignatureWithoutParamNames(final boolean b, final boolean b2) {
        final StringBuilder sb = new StringBuilder();
        String string = null;
        Label_0099: {
            Label_0070: {
                Label_0028: {
                    StringBuilder sb2;
                    try {
                        sb2 = sb;
                        if (b) {
                            final String s = this.getNameWithParent();
                            break Label_0028;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    final String s = this.getName();
                    try {
                        sb2.append(s);
                        sb.append(this.getParamsSignatureWithoutNames());
                        this.getType().getCVQualifiers().appendCVQualifiers(sb);
                        if (!b2) {
                            break Label_0099;
                        }
                        final OCFunctionSymbol ocFunctionSymbol = this;
                        final boolean b3 = ocFunctionSymbol.isCppConstructor();
                        if (!b3) {
                            break Label_0070;
                        }
                        break Label_0099;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw d(ex2);
                    }
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol = this;
                    final boolean b3 = ocFunctionSymbol.isCppConstructor();
                    if (!b3) {
                        sb.append(" : ");
                        sb.append(this.getType().getReturnType().getName());
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
            try {
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getSignatureWithoutParamNames"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
        }
        return string;
    }
    
    @NotNull
    public String getParamsSignatureWithoutNames() {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        int n = 1;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : this.getParameterSymbols()) {
            try {
                if (n == 0) {
                    sb.append(", ");
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            sb.append(ocDeclaratorSymbol.getType().getName());
            n = 0;
        }
        String string;
        try {
            sb.append(")");
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol", "getParamsSignatureWithoutNames"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return string;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Property
    {
        IS_TEMPLATE, 
        IS_OPERATOR, 
        IS_PURE_VIRTUAL, 
        IS_CONVERSION_OPERATOR, 
        IS_UDL;
        
        public static final int DEFAULT = 0;
        
        public int getMask() {
            assert this.ordinal() < 8;
            return 1 << 31 - this.ordinal();
        }
    }
}
