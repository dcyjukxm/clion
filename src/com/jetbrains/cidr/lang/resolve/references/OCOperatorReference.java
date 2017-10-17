// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.types.OCPointerType;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCOperatorReference extends OCPolyVariantReferenceImpl<OCSymbol> implements OCStatefulReference
{
    private final OCElement myElement;
    private final String mySign;
    private final OperatorPlacement myPlacement;
    protected final List<? extends OCTypeOwner> myArguments;
    private final List<OCType> myParamTypes;
    private final PsiElement mySignElement;
    private static Key<MyResolver>[] RESOLVER_KEYS;
    
    public OCOperatorReference(@NotNull final OCElement ocElement, @NotNull final String s, @NotNull final OperatorPlacement operatorPlacement, @Nullable final PsiElement psiElement, final OCExpression... array) {
        if (ocElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        if (operatorPlacement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "placement", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        this(ocElement, s, operatorPlacement, psiElement, Arrays.asList(array), null);
    }
    
    public OCOperatorReference(@NotNull final OCElement myElement, @NotNull final String mySign, @NotNull final OperatorPlacement myPlacement, @Nullable final PsiElement mySignElement, @Nullable final List<? extends OCTypeOwner> myArguments, @Nullable final List<OCType> myParamTypes) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        if (mySign == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        if (myPlacement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "placement", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "<init>"));
        }
        this.myElement = myElement;
        this.mySign = mySign;
        this.myPlacement = myPlacement;
        this.myArguments = myArguments;
        this.myParamTypes = myParamTypes;
        this.mySignElement = mySignElement;
    }
    
    public PsiElement getElement() {
        return (PsiElement)this.myElement;
    }
    
    public TextRange getRangeInElement() {
        try {
            if (this.mySignElement != null) {
                return OCElementUtil.getRangeInParent(this.mySignElement);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return TextRange.EMPTY_RANGE;
    }
    
    @NotNull
    @Override
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    @Override
    public boolean isValid() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.myElement:Lcom/jetbrains/cidr/lang/psi/OCElement;
        //     4: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.isValid:()Z
        //     9: ifeq            89
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.mySignElement:Lcom/intellij/psi/PsiElement;
        //    16: ifnull          45
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.mySignElement:Lcom/intellij/psi/PsiElement;
        //    30: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    35: ifeq            89
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.myArguments:Ljava/util/List;
        //    49: ifnull          81
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    58: athrow         
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.myArguments:Ljava/util/List;
        //    63: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //    68: invokestatic    com/intellij/util/containers/ContainerUtil.and:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
        //    71: ifeq            89
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    80: athrow         
        //    81: iconst_1       
        //    82: goto            90
        //    85: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    88: athrow         
        //    89: iconst_0       
        //    90: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      19     22     26     Lcom/intellij/util/IncorrectOperationException;
        //  12     38     41     45     Lcom/intellij/util/IncorrectOperationException;
        //  26     52     55     59     Lcom/intellij/util/IncorrectOperationException;
        //  45     74     77     81     Lcom/intellij/util/IncorrectOperationException;
        //  59     85     85     89     Lcom/intellij/util/IncorrectOperationException;
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
    
    public boolean isSoft() {
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCOperatorReference ocOperatorReference = this;
                final Class<? extends OCOperatorReference> clazz = ocOperatorReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCOperatorReference ocOperatorReference = this;
                final Class<? extends OCOperatorReference> clazz = ocOperatorReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        final OCOperatorReference ocOperatorReference2 = (OCOperatorReference)o;
        Label_0175: {
            Label_0168: {
                Label_0140: {
                    Label_0127: {
                        Label_0120: {
                            Label_0092: {
                                Label_0079: {
                                    Label_0072: {
                                        try {
                                            if (this.myElement == null) {
                                                break Label_0079;
                                            }
                                            final OCOperatorReference ocOperatorReference3 = this;
                                            final OCElement ocElement = ocOperatorReference3.myElement;
                                            final OCOperatorReference ocOperatorReference4 = ocOperatorReference2;
                                            final OCElement ocElement2 = ocOperatorReference4.myElement;
                                            final boolean b = ocElement.equals(ocElement2);
                                            if (!b) {
                                                break Label_0072;
                                            }
                                            break Label_0092;
                                        }
                                        catch (IncorrectOperationException ex4) {
                                            throw a(ex4);
                                        }
                                        try {
                                            final OCOperatorReference ocOperatorReference3 = this;
                                            final OCElement ocElement = ocOperatorReference3.myElement;
                                            final OCOperatorReference ocOperatorReference4 = ocOperatorReference2;
                                            final OCElement ocElement2 = ocOperatorReference4.myElement;
                                            final boolean b = ocElement.equals(ocElement2);
                                            if (!b) {
                                                return false;
                                            }
                                            break Label_0092;
                                        }
                                        catch (IncorrectOperationException ex5) {
                                            throw a(ex5);
                                        }
                                    }
                                    try {
                                        if (ocOperatorReference2.myElement != null) {
                                            return false;
                                        }
                                    }
                                    catch (IncorrectOperationException ex6) {
                                        throw a(ex6);
                                    }
                                }
                                try {
                                    if (this.mySignElement == null) {
                                        break Label_0127;
                                    }
                                    final OCOperatorReference ocOperatorReference5 = this;
                                    final PsiElement psiElement = ocOperatorReference5.mySignElement;
                                    final OCOperatorReference ocOperatorReference6 = ocOperatorReference2;
                                    final PsiElement psiElement2 = ocOperatorReference6.mySignElement;
                                    final boolean b2 = psiElement.equals(psiElement2);
                                    if (!b2) {
                                        break Label_0120;
                                    }
                                    break Label_0140;
                                }
                                catch (IncorrectOperationException ex7) {
                                    throw a(ex7);
                                }
                            }
                            try {
                                final OCOperatorReference ocOperatorReference5 = this;
                                final PsiElement psiElement = ocOperatorReference5.mySignElement;
                                final OCOperatorReference ocOperatorReference6 = ocOperatorReference2;
                                final PsiElement psiElement2 = ocOperatorReference6.mySignElement;
                                final boolean b2 = psiElement.equals(psiElement2);
                                if (!b2) {
                                    return false;
                                }
                                break Label_0140;
                            }
                            catch (IncorrectOperationException ex8) {
                                throw a(ex8);
                            }
                        }
                        try {
                            if (ocOperatorReference2.mySignElement != null) {
                                return false;
                            }
                        }
                        catch (IncorrectOperationException ex9) {
                            throw a(ex9);
                        }
                    }
                    try {
                        if (this.mySign == null) {
                            break Label_0175;
                        }
                        final OCOperatorReference ocOperatorReference7 = this;
                        final String s = ocOperatorReference7.mySign;
                        final OCOperatorReference ocOperatorReference8 = ocOperatorReference2;
                        final String s2 = ocOperatorReference8.mySign;
                        final boolean b3 = s.equals(s2);
                        if (!b3) {
                            break Label_0168;
                        }
                        return true;
                    }
                    catch (IncorrectOperationException ex10) {
                        throw a(ex10);
                    }
                }
                try {
                    final OCOperatorReference ocOperatorReference7 = this;
                    final String s = ocOperatorReference7.mySign;
                    final OCOperatorReference ocOperatorReference8 = ocOperatorReference2;
                    final String s2 = ocOperatorReference8.mySign;
                    final boolean b3 = s.equals(s2);
                    if (!b3) {
                        return false;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex11) {
                    throw a(ex11);
                }
            }
            try {
                if (ocOperatorReference2.mySign != null) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex12) {
                throw a(ex12);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.myElement != null) {
                    hashCode = this.myElement.hashCode();
                    break Label_0022;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2 = 0;
        int hashCode2 = 0;
        Label_0049: {
            try {
                n2 = 31 * n;
                if (this.mySign != null) {
                    hashCode2 = this.mySign.hashCode();
                    break Label_0049;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        int n4;
        try {
            n4 = 31 * n3;
            if (this.mySignElement != null) {
                final int hashCode3 = this.mySignElement.hashCode();
                return n4 + hashCode3;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final int hashCode3 = 0;
        return n4 + hashCode3;
    }
    
    @Nullable
    public List<? extends OCTypeOwner> getArgumentExpressions() {
        return this.myArguments;
    }
    
    @NotNull
    public String getCanonicalText() {
        String mySign = null;
        Label_0062: {
            String s = null;
            Label_0027: {
                try {
                    if (this.mySignElement == null) {
                        break Label_0062;
                    }
                    final OCOperatorReference ocOperatorReference = this;
                    final PsiElement psiElement = ocOperatorReference.mySignElement;
                    s = psiElement.getText();
                    if (s == null) {
                        break Label_0027;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCOperatorReference ocOperatorReference = this;
                    final PsiElement psiElement = ocOperatorReference.mySignElement;
                    s = psiElement.getText();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "getCanonicalText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                mySign = this.mySign;
                if (mySign == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return mySign;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        try {
            if (this.mySignElement != null) {
                OCElementUtil.replaceWithIdentifier(this.mySignElement, s, this.getElement());
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getElement();
    }
    
    @Override
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.handleElementRename(ocSymbol.getName());
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        try {
            if (symbol != null) {
                return this.bindToSymbol(symbol);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return psiElement;
    }
    
    @Nullable
    public static OCFunctionSymbol resolveOperator(@NotNull final String s, @NotNull final OperatorPlacement operatorPlacement, @NotNull final List<OCType> list, @Nullable final List<? extends OCTypeOwner> list2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (operatorPlacement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "placement", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCSymbol ocSymbol = (OCSymbol)ContainerUtil.getFirstItem((List)new MyResolver(true, true, ocResolveContext).resolve(new OCOperatorReference((OCElement)ocResolveContext.getElement(), s, operatorPlacement, null, list2, list), ocResolveContext).first);
        try {
            if (ocSymbol instanceof OCFunctionSymbol) {
                return (OCFunctionSymbol)ocSymbol;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Nullable
    public static OCFunctionSymbol resolveOperator(@NotNull final String s, @NotNull final OperatorPlacement operatorPlacement, @NotNull final OCElement ocElement, @NotNull final OCStructType ocStructType) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (operatorPlacement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "placement", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (ocStructType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "struct", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveOperator"));
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocElement);
        final OCSymbol ocSymbol = (OCSymbol)ContainerUtil.getFirstItem(new MyResolver(true, true, ocResolveContext).a(s, operatorPlacement, Collections.singletonList(ocStructType), null, ocResolveContext, null));
        try {
            if (ocSymbol instanceof OCFunctionSymbol) {
                return (OCFunctionSymbol)ocSymbol;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Nullable
    public static OCFunctionSymbol resolveDerefOperator(@NotNull final OCElement ocElement, @NotNull final OCStructType ocStructType) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveDerefOperator"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocStructType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "struct", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveDerefOperator"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return resolveOperator(OCTokenTypes.DEREF.getName(), OperatorPlacement.INFIX, ocElement, ocStructType);
    }
    
    @NotNull
    private List<OCSymbol> a(@NotNull final MyResolver resolver) {
        try {
            if (resolver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Pair pair = null;
        final ResolveCache instance = ResolveCache.getInstance(this.myElement.getProject());
        if (instance != null) {
            pair = instance.resolveWithCaching(this, (ResolveCache.AbstractResolver<OCOperatorReference, Pair>)resolver, false, false);
        }
        List<OCSymbol> list = null;
        Label_0096: {
            try {
                if (pair != null) {
                    final List<OCSymbol> emptyList;
                    list = (emptyList = (List<OCSymbol>)pair.getFirst());
                    break Label_0096;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            List<OCSymbol> emptyList;
            list = (emptyList = Collections.emptyList());
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return (List<OCSymbol>)list;
    }
    
    @NotNull
    public Collection<OCSymbol> resolveToSymbols(final boolean b) {
        List<OCSymbol> a;
        try {
            a = this.a(MyResolver.getInstance(b));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    public Collection<OCSymbol> resolveToSymbols(final boolean b, final boolean b2) {
        final Pair<List<OCSymbol>, Boolean> resolve = new MyResolver(b, b2).resolve(this, false);
        Collection<Object> collection = null;
        Label_0037: {
            try {
                if (resolve != null) {
                    final Collection<Object> emptyList;
                    collection = (emptyList = (Collection<Object>)resolve.getFirst());
                    break Label_0037;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            Collection<Object> emptyList;
            collection = (emptyList = Collections.emptyList());
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return (Collection<OCSymbol>)collection;
    }
    
    public boolean hasMagicOperands() {
        final Pair pair = ResolveCache.getInstance(this.myElement.getProject()).resolveWithCaching(this, (ResolveCache.AbstractResolver<OCOperatorReference, Pair>)MyResolver.getInstance(true), false, false);
        try {
            if (pair != null) {
                return (boolean)pair.getSecond();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols() {
        ArrayList list;
        try {
            list = new ArrayList<OCSymbol>(this.resolveToSymbols(true));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<OCSymbol>)list;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols(true, ocResolveContext);
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    public List<OCSymbol> resolveToSymbols(final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Key<MyResolver>[] resolver_KEYS = null;
        int n = 0;
        Label_0060: {
            try {
                resolver_KEYS = OCOperatorReference.RESOLVER_KEYS;
                if (b) {
                    n = 1;
                    break Label_0060;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            n = 0;
        }
        final Key<MyResolver> key = resolver_KEYS[n];
        MyResolver myResolver;
        synchronized (ocResolveContext) {
            myResolver = (MyResolver)ocResolveContext.getUserData((Key)key);
            if (myResolver == null) {
                ocResolveContext.putUserData((Key)key, (Object)(myResolver = new MyResolver(b, false, ocResolveContext)));
            }
        }
        List<OCSymbol> a;
        try {
            a = this.a(myResolver);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return a;
    }
    
    static {
        OCOperatorReference.RESOLVER_KEYS = (Key<MyResolver>[])new Key[] { Key.create("RESOLVER_KEY1"), Key.create("RESOLVER_KEY2") };
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    public enum OperatorPlacement
    {
        PREFIX, 
        INFIX, 
        POSTFIX;
    }
    
    private static class MyResolver implements ResolveCache.AbstractResolver<OCOperatorReference, Pair<List<OCSymbol>, Boolean>>
    {
        private boolean myResolveOverloads;
        private boolean myFilterAmbigs;
        private OCResolveContext myContext;
        private static final MyResolver[] INSTANCES;
        private static final Pair<List<OCSymbol>, Boolean> EMPTY;
        private static final Pair<List<OCSymbol>, Boolean> EMPTY_MAGIC;
        
        public static MyResolver getInstance(final boolean b) {
            try {
                if (b) {
                    return MyResolver.INSTANCES[1];
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return MyResolver.INSTANCES[0];
        }
        
        private MyResolver(final boolean myResolveOverloads, final boolean myFilterAmbigs, @NotNull final OCResolveContext myContext) {
            if (myContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "<init>"));
            }
            this.myResolveOverloads = myResolveOverloads;
            this.myFilterAmbigs = myFilterAmbigs;
            this.myContext = myContext;
        }
        
        private MyResolver(final boolean myResolveOverloads, final boolean myFilterAmbigs) {
            this.myResolveOverloads = myResolveOverloads;
            this.myFilterAmbigs = myFilterAmbigs;
        }
        
        @Override
        public Pair<List<OCSymbol>, Boolean> resolve(@NotNull final OCOperatorReference ocOperatorReference, final boolean b) {
            try {
                if (ocOperatorReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (this.myContext != null) {
                    final OCResolveContext myContext = this.myContext;
                    return this.resolve(ocOperatorReference, myContext);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCResolveContext myContext = new OCResolveContext(ocOperatorReference.getElement());
            return this.resolve(ocOperatorReference, myContext);
        }
        
        public Pair<List<OCSymbol>, Boolean> resolve(@NotNull final OCOperatorReference ocOperatorReference, @NotNull final OCResolveContext ocResolveContext) {
            try {
                if (ocOperatorReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocOperatorReference.myArguments != null) {
                    return this.b(ocOperatorReference.mySign, ocOperatorReference.myPlacement, ocOperatorReference.myParamTypes, ocOperatorReference.myArguments, ocResolveContext, ocOperatorReference.getElement());
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return (Pair<List<OCSymbol>, Boolean>)Pair.create((Object)this.a(ocOperatorReference.mySign, ocOperatorReference.myPlacement, ocOperatorReference.myParamTypes, null, ocResolveContext, ocOperatorReference.getElement()), (Object)false);
        }
        
        @NotNull
        private Pair<List<OCSymbol>, Boolean> b(@NotNull final String p0, @NotNull final OperatorPlacement p1, @Nullable final List<OCType> p2, @NotNull final List<? extends OCTypeOwner> p3, @NotNull final OCResolveContext p4, @Nullable final PsiElement p5) {
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
            //    18: ldc             "sign"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "resolveOperator"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
            //    62: ldc             "placement"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "resolveOperator"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
            //   107: ldc             "arguments"
            //   109: aastore        
            //   110: dup            
            //   111: ldc             1
            //   113: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   115: aastore        
            //   116: dup            
            //   117: ldc             2
            //   119: ldc             "resolveOperator"
            //   121: aastore        
            //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   128: athrow         
            //   129: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
            //   152: ldc             "context"
            //   154: aastore        
            //   155: dup            
            //   156: ldc             1
            //   158: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   160: aastore        
            //   161: dup            
            //   162: ldc             2
            //   164: ldc             "resolveOperator"
            //   166: aastore        
            //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   173: athrow         
            //   174: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   177: athrow         
            //   178: aload_1        
            //   179: invokestatic    com/jetbrains/cidr/lang/psi/OCUDLiteralExpression.isUDLOperator:(Ljava/lang/String;)Z
            //   182: ifeq            243
            //   185: aload_0        
            //   186: aload_1        
            //   187: aload_2        
            //   188: aload           4
            //   190: aload           5
            //   192: aload           6
            //   194: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Pair;
            //   197: dup            
            //   198: ifnonnull       242
            //   201: goto            208
            //   204: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   207: athrow         
            //   208: new             Ljava/lang/IllegalStateException;
            //   211: dup            
            //   212: ldc             "@NotNull method %s.%s must not return null"
            //   214: ldc             2
            //   216: anewarray       Ljava/lang/Object;
            //   219: dup            
            //   220: ldc             0
            //   222: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   224: aastore        
            //   225: dup            
            //   226: ldc             1
            //   228: ldc             "resolveOperator"
            //   230: aastore        
            //   231: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   234: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   237: athrow         
            //   238: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   241: athrow         
            //   242: areturn        
            //   243: new             Ljava/util/ArrayList;
            //   246: dup            
            //   247: invokespecial   java/util/ArrayList.<init>:()V
            //   250: astore          7
            //   252: new             Ljava/util/ArrayList;
            //   255: dup            
            //   256: invokespecial   java/util/ArrayList.<init>:()V
            //   259: astore          8
            //   261: iconst_0       
            //   262: istore          9
            //   264: iconst_0       
            //   265: istore          10
            //   267: iconst_0       
            //   268: istore          11
            //   270: aload           4
            //   272: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   277: astore          12
            //   279: aload           12
            //   281: invokeinterface java/util/Iterator.hasNext:()Z
            //   286: ifeq            330
            //   289: aload           12
            //   291: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   296: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
            //   299: astore          13
            //   301: aload           13
            //   303: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
            //   306: ifne            327
            //   309: aload           13
            //   311: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol;
            //   314: ifne            327
            //   317: goto            324
            //   320: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   323: athrow         
            //   324: iconst_1       
            //   325: istore          10
            //   327: goto            279
            //   330: iload           10
            //   332: ifne            384
            //   335: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY:Lcom/intellij/openapi/util/Pair;
            //   338: dup            
            //   339: ifnonnull       383
            //   342: goto            349
            //   345: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   348: athrow         
            //   349: new             Ljava/lang/IllegalStateException;
            //   352: dup            
            //   353: ldc             "@NotNull method %s.%s must not return null"
            //   355: ldc             2
            //   357: anewarray       Ljava/lang/Object;
            //   360: dup            
            //   361: ldc             0
            //   363: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   365: aastore        
            //   366: dup            
            //   367: ldc             1
            //   369: ldc             "resolveOperator"
            //   371: aastore        
            //   372: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   375: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   378: athrow         
            //   379: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   382: athrow         
            //   383: areturn        
            //   384: iconst_0       
            //   385: istore          12
            //   387: iload           12
            //   389: aload           4
            //   391: invokeinterface java/util/List.size:()I
            //   396: if_icmpge       653
            //   399: aload           4
            //   401: iload           12
            //   403: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   408: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
            //   411: astore          13
            //   413: aload_3        
            //   414: ifnull          458
            //   417: iload           12
            //   419: aload_3        
            //   420: invokeinterface java/util/List.size:()I
            //   425: if_icmpge       458
            //   428: goto            435
            //   431: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   434: athrow         
            //   435: aload_3        
            //   436: iload           12
            //   438: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   443: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   446: aload           5
            //   448: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   451: goto            467
            //   454: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   457: athrow         
            //   458: aload           13
            //   460: aload           5
            //   462: invokeinterface com/jetbrains/cidr/lang/types/OCTypeOwner.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   467: astore          14
            //   469: aload           8
            //   471: aload           13
            //   473: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   478: pop            
            //   479: aload           14
            //   481: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   484: ifeq            502
            //   487: aload           14
            //   489: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   492: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   495: goto            504
            //   498: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   501: athrow         
            //   502: aload           14
            //   504: astore          15
            //   506: aload           15
            //   508: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
            //   511: ifeq            517
            //   514: iconst_1       
            //   515: istore          11
            //   517: aload           15
            //   519: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   522: ifeq            552
            //   525: aload           15
            //   527: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   530: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   533: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   536: if_acmpeq       552
            //   539: goto            546
            //   542: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   545: athrow         
            //   546: iconst_1       
            //   547: istore          9
            //   549: goto            637
            //   552: iload           12
            //   554: ifne            637
            //   557: aload_1        
            //   558: ldc             "()"
            //   560: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   563: ifeq            637
            //   566: goto            573
            //   569: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   572: athrow         
            //   573: iload           11
            //   575: ifeq            595
            //   578: goto            585
            //   581: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   584: athrow         
            //   585: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY_MAGIC:Lcom/intellij/openapi/util/Pair;
            //   588: goto            598
            //   591: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   594: athrow         
            //   595: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY:Lcom/intellij/openapi/util/Pair;
            //   598: dup            
            //   599: ifnonnull       636
            //   602: new             Ljava/lang/IllegalStateException;
            //   605: dup            
            //   606: ldc             "@NotNull method %s.%s must not return null"
            //   608: ldc             2
            //   610: anewarray       Ljava/lang/Object;
            //   613: dup            
            //   614: ldc             0
            //   616: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   618: aastore        
            //   619: dup            
            //   620: ldc             1
            //   622: ldc             "resolveOperator"
            //   624: aastore        
            //   625: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   628: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   631: athrow         
            //   632: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   635: athrow         
            //   636: areturn        
            //   637: aload           7
            //   639: aload           14
            //   641: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   646: pop            
            //   647: iinc            12, 1
            //   650: goto            387
            //   653: iload           9
            //   655: ifeq            743
            //   658: aload_0        
            //   659: aload_1        
            //   660: aload_2        
            //   661: aload           7
            //   663: aload           8
            //   665: aload           5
            //   667: aload           6
            //   669: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/psi/PsiElement;)Ljava/util/List;
            //   672: astore          12
            //   674: aload           12
            //   676: invokeinterface java/util/List.isEmpty:()Z
            //   681: ifne            701
            //   684: aload           12
            //   686: iload           11
            //   688: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   691: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
            //   694: goto            704
            //   697: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   700: athrow         
            //   701: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY:Lcom/intellij/openapi/util/Pair;
            //   704: dup            
            //   705: ifnonnull       742
            //   708: new             Ljava/lang/IllegalStateException;
            //   711: dup            
            //   712: ldc             "@NotNull method %s.%s must not return null"
            //   714: ldc             2
            //   716: anewarray       Ljava/lang/Object;
            //   719: dup            
            //   720: ldc             0
            //   722: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   724: aastore        
            //   725: dup            
            //   726: ldc             1
            //   728: ldc             "resolveOperator"
            //   730: aastore        
            //   731: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   734: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   737: athrow         
            //   738: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   741: athrow         
            //   742: areturn        
            //   743: iload           11
            //   745: ifeq            758
            //   748: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY_MAGIC:Lcom/intellij/openapi/util/Pair;
            //   751: goto            761
            //   754: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   757: athrow         
            //   758: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.EMPTY:Lcom/intellij/openapi/util/Pair;
            //   761: dup            
            //   762: ifnonnull       799
            //   765: new             Ljava/lang/IllegalStateException;
            //   768: dup            
            //   769: ldc             "@NotNull method %s.%s must not return null"
            //   771: ldc             2
            //   773: anewarray       Ljava/lang/Object;
            //   776: dup            
            //   777: ldc             0
            //   779: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   781: aastore        
            //   782: dup            
            //   783: ldc             1
            //   785: ldc             "resolveOperator"
            //   787: aastore        
            //   788: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   791: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   794: athrow         
            //   795: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   798: athrow         
            //   799: areturn        
            //    Signature:
            //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCType;>;Ljava/util/List<+Lcom/jetbrains/cidr/lang/types/OCTypeOwner;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Pair<Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Ljava/lang/Boolean;>;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  88     129    129    133    Ljava/lang/IllegalArgumentException;
            //  133    174    174    178    Ljava/lang/IllegalArgumentException;
            //  178    201    204    208    Ljava/lang/IllegalArgumentException;
            //  185    238    238    242    Ljava/lang/IllegalArgumentException;
            //  301    317    320    324    Ljava/lang/IllegalArgumentException;
            //  330    342    345    349    Ljava/lang/IllegalArgumentException;
            //  335    379    379    383    Ljava/lang/IllegalArgumentException;
            //  413    428    431    435    Ljava/lang/IllegalArgumentException;
            //  417    454    454    458    Ljava/lang/IllegalArgumentException;
            //  469    498    498    502    Ljava/lang/IllegalArgumentException;
            //  517    539    542    546    Ljava/lang/IllegalArgumentException;
            //  552    566    569    573    Ljava/lang/IllegalArgumentException;
            //  557    578    581    585    Ljava/lang/IllegalArgumentException;
            //  573    591    591    595    Ljava/lang/IllegalArgumentException;
            //  598    632    632    636    Ljava/lang/IllegalArgumentException;
            //  674    697    697    701    Ljava/lang/IllegalArgumentException;
            //  704    738    738    742    Ljava/lang/IllegalArgumentException;
            //  743    754    754    758    Ljava/lang/IllegalArgumentException;
            //  761    795    795    799    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0573:
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
        
        @NotNull
        private Pair<List<OCSymbol>, Boolean> a(@NotNull final String s, @NotNull final OperatorPlacement operatorPlacement, @NotNull final List<? extends OCTypeOwner> list, @NotNull final OCResolveContext ocResolveContext, @Nullable final PsiElement psiElement) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (operatorPlacement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "placement", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final ArrayList<OCPointerType> list2 = new ArrayList<OCPointerType>();
            final ArrayList<OCLiteralExpression> list3 = new ArrayList<OCLiteralExpression>();
            Pair<List<OCSymbol>, Boolean> empty = null;
            Label_0731: {
                try {
                    if (list.size() != 1 || !(list.get(0) instanceof OCLiteralExpression)) {
                        break Label_0731;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                final OCLiteralExpression ocLiteralExpression = (OCLiteralExpression)list.get(0);
                final IElementType elementType = OCElementUtil.getElementType(ocLiteralExpression.getFirstChild());
                OCType ocType;
                boolean b;
                if (elementType == OCTokenTypes.INTEGER_LITERAL) {
                    ocType = OCIntType.ULONGLONG;
                    b = true;
                }
                else if (elementType == OCTokenTypes.FLOAT_LITERAL) {
                    ocType = OCRealType.LONG_DOUBLE;
                    b = true;
                }
                else {
                    ocType = ocLiteralExpression.getType(ocResolveContext);
                    b = false;
                }
                try {
                    list3.add(ocLiteralExpression);
                    list2.add((OCIntType)ocType);
                    if (ocLiteralExpression.isStringLiteral()) {
                        list2.add((OCPointerType)OCIntType.SIZE_T);
                        list3.add(null);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                Object o = this.a(s, operatorPlacement, (List<OCType>)list2, (List<OCTypeOwner>)list3, ocResolveContext, psiElement);
                Label_0439: {
                    Pair pair = null;
                    Label_0404: {
                        try {
                            if (((List)o).isEmpty()) {
                                break Label_0439;
                            }
                            final Object o2 = o;
                            final boolean b2 = false;
                            final Boolean b3 = b2;
                            pair = Pair.create(o2, (Object)b3);
                            if (pair == null) {
                                break Label_0404;
                            }
                            return (Pair<List<OCSymbol>, Boolean>)pair;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        try {
                            final Object o2 = o;
                            final boolean b2 = false;
                            final Boolean b3 = b2;
                            pair = Pair.create(o2, (Object)b3);
                            if (pair == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                    }
                    return (Pair<List<OCSymbol>, Boolean>)pair;
                }
                if (b) {
                    list3.clear();
                    list2.clear();
                    list2.add(OCPointerType.to(OCIntType.CHAR_CONST));
                    list3.add(null);
                    final List<OCSymbol> a = this.a(s, operatorPlacement, (List<OCType>)list2, (List<OCTypeOwner>)list3, ocResolveContext, psiElement);
                    list2.clear();
                    final List<OCSymbol> a2 = this.a(s, operatorPlacement, (List<OCType>)list2, null, ocResolveContext, psiElement);
                    Pair pair2 = null;
                    Label_0696: {
                        Label_0666: {
                            Label_0587: {
                                Label_0553: {
                                    try {
                                        if (a.isEmpty() || !a2.isEmpty()) {
                                            break Label_0553;
                                        }
                                    }
                                    catch (IllegalArgumentException ex9) {
                                        throw a(ex9);
                                    }
                                    o = a;
                                    break Label_0666;
                                    try {
                                        if (!a.isEmpty() || a2.isEmpty()) {
                                            break Label_0587;
                                        }
                                    }
                                    catch (IllegalArgumentException ex10) {
                                        throw a(ex10);
                                    }
                                }
                                o = a2;
                                break Label_0666;
                                try {
                                    if (a.isEmpty() || a2.isEmpty()) {
                                        break Label_0666;
                                    }
                                }
                                catch (IllegalArgumentException ex11) {
                                    throw a(ex11);
                                }
                            }
                            final ArrayList list4 = new ArrayList(2);
                            list4.addAll(a);
                            list4.addAll(a2);
                            o = Collections.singletonList(new OCResolveOverloadsUtil.OCFunctionGroupSymbol(ContainerUtil.map((Collection)list4, ocSymbol -> (OCFunctionSymbol)ocSymbol), null, OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause.Ambiguous));
                            try {
                                if (((List)o).isEmpty()) {
                                    break Label_0731;
                                }
                                final Object o3 = o;
                                final boolean b4 = false;
                                final Boolean b5 = b4;
                                pair2 = Pair.create(o3, (Object)b5);
                                if (pair2 == null) {
                                    break Label_0696;
                                }
                                return (Pair<List<OCSymbol>, Boolean>)pair2;
                            }
                            catch (IllegalArgumentException ex12) {
                                throw a(ex12);
                            }
                        }
                        try {
                            final Object o3 = o;
                            final boolean b4 = false;
                            final Boolean b5 = b4;
                            pair2 = Pair.create(o3, (Object)b5);
                            if (pair2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                            }
                        }
                        catch (IllegalArgumentException ex13) {
                            throw a(ex13);
                        }
                    }
                    return (Pair<List<OCSymbol>, Boolean>)pair2;
                }
                try {
                    empty = MyResolver.EMPTY;
                    if (empty == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver", "resolveUDLOperator"));
                    }
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
            return empty;
        }
        
        @NotNull
        private List<OCSymbol> a(@NotNull final String p0, @NotNull final OperatorPlacement p1, @NotNull final List<OCType> p2, @Nullable final List<OCTypeOwner> p3, @NotNull final OCResolveContext p4, @Nullable final PsiElement p5) {
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
            //    18: ldc             "sign"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "doResolveOperator"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
            //    62: ldc             "placement"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "doResolveOperator"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_3        
            //    89: ifnonnull       132
            //    92: new             Ljava/lang/IllegalArgumentException;
            //    95: dup            
            //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    98: ldc             3
            //   100: anewarray       Ljava/lang/Object;
            //   103: dup            
            //   104: ldc             0
            //   106: ldc             "argTypes"
            //   108: aastore        
            //   109: dup            
            //   110: ldc             1
            //   112: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   114: aastore        
            //   115: dup            
            //   116: ldc             2
            //   118: ldc             "doResolveOperator"
            //   120: aastore        
            //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   127: athrow         
            //   128: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   131: athrow         
            //   132: aload           5
            //   134: ifnonnull       177
            //   137: new             Ljava/lang/IllegalArgumentException;
            //   140: dup            
            //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //   143: ldc             3
            //   145: anewarray       Ljava/lang/Object;
            //   148: dup            
            //   149: ldc             0
            //   151: ldc             "context"
            //   153: aastore        
            //   154: dup            
            //   155: ldc             1
            //   157: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   159: aastore        
            //   160: dup            
            //   161: ldc             2
            //   163: ldc             "doResolveOperator"
            //   165: aastore        
            //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   172: athrow         
            //   173: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   176: athrow         
            //   177: new             Ljava/lang/StringBuilder;
            //   180: dup            
            //   181: invokespecial   java/lang/StringBuilder.<init>:()V
            //   184: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
            //   187: invokevirtual   com/jetbrains/cidr/lang/parser/OCKeywordElementType.getName:()Ljava/lang/String;
            //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   193: aload_1        
            //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   197: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   200: astore          7
            //   202: aload_3        
            //   203: invokeinterface java/util/List.isEmpty:()Z
            //   208: ifeq            219
            //   211: aconst_null    
            //   212: goto            229
            //   215: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   218: athrow         
            //   219: aload_3        
            //   220: iconst_0       
            //   221: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   226: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   229: astore          8
            //   231: aload           4
            //   233: ifnull          253
            //   236: aload           4
            //   238: invokeinterface java/util/List.isEmpty:()Z
            //   243: ifeq            261
            //   246: goto            253
            //   249: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   252: athrow         
            //   253: aconst_null    
            //   254: goto            277
            //   257: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   260: athrow         
            //   261: aload           4
            //   263: iconst_0       
            //   264: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   269: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
            //   272: aload           5
            //   274: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
            //   277: astore          9
            //   279: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
            //   282: dup            
            //   283: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:()V
            //   286: astore          10
            //   288: aload           8
            //   290: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   293: ifeq            306
            //   296: aload           8
            //   298: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   301: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   304: astore          8
            //   306: aload           8
            //   308: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   311: ifeq            338
            //   314: aload           8
            //   316: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   319: aload           7
            //   321: aconst_null    
            //   322: aload           5
            //   324: iconst_1       
            //   325: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getResolvedMembers:(Lcom/jetbrains/cidr/lang/types/OCStructType;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Ljava/util/List;
            //   328: astore          11
            //   330: aload           11
            //   332: aload           10
            //   334: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
            //   337: pop            
            //   338: aload           6
            //   340: ifnull          365
            //   343: aload           7
            //   345: aload           6
            //   347: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
            //   350: aload           10
            //   352: aload           5
            //   354: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference.processPossibleSymbols:(Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   357: pop            
            //   358: goto            365
            //   361: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   364: athrow         
            //   365: aload_3        
            //   366: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   371: astore          11
            //   373: aload           11
            //   375: invokeinterface java/util/Iterator.hasNext:()Z
            //   380: ifeq            441
            //   383: aload           11
            //   385: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   390: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   393: astore          12
            //   395: aload           12
            //   397: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   400: astore          13
            //   402: aload           13
            //   404: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   407: ifeq            438
            //   410: aload           13
            //   412: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   415: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   418: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   421: astore          14
            //   423: aload           7
            //   425: aload           14
            //   427: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getGlobalReference:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
            //   430: aload           10
            //   432: aload           5
            //   434: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference.processPossibleSymbols:(Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   437: pop            
            //   438: goto            373
            //   441: aload           10
            //   443: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
            //   446: astore          11
            //   448: aload           4
            //   450: ifnull          494
            //   453: aload           4
            //   455: iconst_0       
            //   456: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   461: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //   464: ifeq            494
            //   467: goto            474
            //   470: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   473: athrow         
            //   474: aload           10
            //   476: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
            //   479: aload_3        
            //   480: aload           4
            //   482: aload           7
            //   484: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   487: aload           5
            //   489: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCArgumentDepLookupAccumulator.doArgDepLookup:(Ljava/util/Collection;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/Collection;
            //   492: astore          11
            //   494: aload_0        
            //   495: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.myResolveOverloads:Z
            //   498: ifeq            840
            //   501: aload_1        
            //   502: invokestatic    com/jetbrains/cidr/lang/psi/OCUDLiteralExpression.isUDLOperator:(Ljava/lang/String;)Z
            //   505: ifeq            706
            //   508: goto            515
            //   511: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   514: athrow         
            //   515: aload           11
            //   517: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
            //   522: astore          12
            //   524: aload           12
            //   526: invokeinterface java/util/Iterator.hasNext:()Z
            //   531: ifeq            703
            //   534: aload           12
            //   536: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   541: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   544: astore          13
            //   546: aload           13
            //   548: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   551: ifeq            700
            //   554: aload           13
            //   556: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   559: astore          14
            //   561: aload           14
            //   563: aload_2        
            //   564: aload           5
            //   566: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
            //   569: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.getParameterTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Ljava/util/List;
            //   572: astore          15
            //   574: aload_3        
            //   575: invokeinterface java/util/List.size:()I
            //   580: aload           15
            //   582: invokeinterface java/util/List.size:()I
            //   587: if_icmpne       700
            //   590: aload_3        
            //   591: invokeinterface java/util/List.size:()I
            //   596: ifeq            649
            //   599: goto            606
            //   602: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   605: athrow         
            //   606: aload           15
            //   608: iconst_0       
            //   609: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   614: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   617: aload_3        
            //   618: iconst_0       
            //   619: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   624: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   627: getstatic       com/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger.INSTANCE:Lcom/jetbrains/cidr/lang/types/visitors/OCArrayToPointerChanger;
            //   630: invokevirtual   com/jetbrains/cidr/lang/types/OCType.transformType:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   633: iconst_0       
            //   634: aload           5
            //   636: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   639: ifeq            700
            //   642: goto            649
            //   645: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   648: athrow         
            //   649: aload           13
            //   651: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
            //   654: dup            
            //   655: ifnonnull       699
            //   658: goto            665
            //   661: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   664: athrow         
            //   665: new             Ljava/lang/IllegalStateException;
            //   668: dup            
            //   669: ldc             "@NotNull method %s.%s must not return null"
            //   671: ldc             2
            //   673: anewarray       Ljava/lang/Object;
            //   676: dup            
            //   677: ldc             0
            //   679: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   681: aastore        
            //   682: dup            
            //   683: ldc             1
            //   685: ldc             "doResolveOperator"
            //   687: aastore        
            //   688: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   691: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   694: athrow         
            //   695: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   698: athrow         
            //   699: areturn        
            //   700: goto            524
            //   703: goto            798
            //   706: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
            //   709: dup            
            //   710: aload_3        
            //   711: aload           4
            //   713: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
            //   716: astore          12
            //   718: aload           11
            //   720: aload           12
            //   722: aload           8
            //   724: aload           9
            //   726: aload_2        
            //   727: iconst_1       
            //   728: iconst_1       
            //   729: aload_0        
            //   730: getfield        com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.myFilterAmbigs:Z
            //   733: iconst_1       
            //   734: iconst_0       
            //   735: aload           5
            //   737: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.resolveOverloads:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   740: astore          13
            //   742: aload           13
            //   744: ifnull          798
            //   747: aload           13
            //   749: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
            //   752: dup            
            //   753: ifnonnull       797
            //   756: goto            763
            //   759: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   762: athrow         
            //   763: new             Ljava/lang/IllegalStateException;
            //   766: dup            
            //   767: ldc             "@NotNull method %s.%s must not return null"
            //   769: ldc             2
            //   771: anewarray       Ljava/lang/Object;
            //   774: dup            
            //   775: ldc             0
            //   777: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   779: aastore        
            //   780: dup            
            //   781: ldc             1
            //   783: ldc             "doResolveOperator"
            //   785: aastore        
            //   786: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   789: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   792: athrow         
            //   793: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   796: athrow         
            //   797: areturn        
            //   798: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   801: dup            
            //   802: ifnonnull       839
            //   805: new             Ljava/lang/IllegalStateException;
            //   808: dup            
            //   809: ldc             "@NotNull method %s.%s must not return null"
            //   811: ldc             2
            //   813: anewarray       Ljava/lang/Object;
            //   816: dup            
            //   817: ldc             0
            //   819: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   821: aastore        
            //   822: dup            
            //   823: ldc             1
            //   825: ldc             "doResolveOperator"
            //   827: aastore        
            //   828: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   831: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   834: athrow         
            //   835: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   838: athrow         
            //   839: areturn        
            //   840: aload           10
            //   842: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
            //   845: checkcast       Ljava/util/List;
            //   848: dup            
            //   849: ifnonnull       886
            //   852: new             Ljava/lang/IllegalStateException;
            //   855: dup            
            //   856: ldc             "@NotNull method %s.%s must not return null"
            //   858: ldc             2
            //   860: anewarray       Ljava/lang/Object;
            //   863: dup            
            //   864: ldc             0
            //   866: ldc             "com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver"
            //   868: aastore        
            //   869: dup            
            //   870: ldc             1
            //   872: ldc             "doResolveOperator"
            //   874: aastore        
            //   875: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   878: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   881: athrow         
            //   882: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$MyResolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   885: athrow         
            //   886: areturn        
            //    Signature:
            //  (Ljava/lang/String;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCType;>;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeOwner;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/intellij/psi/PsiElement;)Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  88     128    128    132    Ljava/lang/IllegalArgumentException;
            //  132    173    173    177    Ljava/lang/IllegalArgumentException;
            //  202    215    215    219    Ljava/lang/IllegalArgumentException;
            //  231    246    249    253    Ljava/lang/IllegalArgumentException;
            //  236    257    257    261    Ljava/lang/IllegalArgumentException;
            //  338    358    361    365    Ljava/lang/IllegalArgumentException;
            //  448    467    470    474    Ljava/lang/IllegalArgumentException;
            //  494    508    511    515    Ljava/lang/IllegalArgumentException;
            //  574    599    602    606    Ljava/lang/IllegalArgumentException;
            //  590    642    645    649    Ljava/lang/IllegalArgumentException;
            //  606    658    661    665    Ljava/lang/IllegalArgumentException;
            //  649    695    695    699    Ljava/lang/IllegalArgumentException;
            //  742    756    759    763    Ljava/lang/IllegalArgumentException;
            //  747    793    793    797    Ljava/lang/IllegalArgumentException;
            //  798    835    835    839    Ljava/lang/IllegalArgumentException;
            //  840    882    882    886    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0606:
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
        
        static {
            INSTANCES = new MyResolver[] { new MyResolver(false, false), new MyResolver(true, false) };
            EMPTY = new Pair((Object)Collections.emptyList(), (Object)false);
            EMPTY_MAGIC = new Pair((Object)Collections.emptyList(), (Object)true);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
