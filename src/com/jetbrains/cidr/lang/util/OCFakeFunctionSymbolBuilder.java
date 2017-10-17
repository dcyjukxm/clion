// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;

public class OCFakeFunctionSymbolBuilder
{
    @NotNull
    private final String myName;
    @NotNull
    private OCType myReturnType;
    @Nullable
    private OCSymbolWithQualifiedName myContainer;
    @NotNull
    private List<Pair<OCType, String>> myParams;
    private boolean myIsConst;
    private boolean myIsCtorOrDtor;
    private int myProperties;
    private int myAttributes;
    @NotNull
    private OCVisibility myVisibility;
    
    public OCFakeFunctionSymbolBuilder(@NotNull final String myName) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "<init>"));
        }
        this.myReturnType = OCVoidType.instance();
        this.myContainer = null;
        this.myParams = new ArrayList<Pair<OCType, String>>();
        this.myIsConst = false;
        this.myIsCtorOrDtor = false;
        this.myProperties = 0;
        this.myAttributes = 0;
        this.myVisibility = OCVisibility.NULL;
        this.myName = myName;
    }
    
    public OCFakeFunctionSymbolBuilder setReturnType(@NotNull final OCType myReturnType) {
        try {
            if (myReturnType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "setReturnType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myReturnType = myReturnType;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setContainer(@Nullable final OCSymbolWithQualifiedName myContainer) {
        this.myContainer = myContainer;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder addParam(@NotNull final OCType ocType, @NotNull final String s) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "addParam"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "addParam"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myParams.add((Pair<OCType, String>)Pair.create((Object)ocType, (Object)s));
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setVisibility(@NotNull final OCVisibility myVisibility) {
        try {
            if (myVisibility == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visibility", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "setVisibility"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myVisibility = myVisibility;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsStatic(final boolean b) {
        int myAttributes = 0;
        Label_0036: {
            try {
                if (b) {
                    myAttributes = (this.myAttributes | OCSymbolAttribute.STATIC.getMask());
                    break Label_0036;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myAttributes = (this.myAttributes & ~OCSymbolAttribute.STATIC.getMask());
        }
        this.myAttributes = myAttributes;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsConst(final boolean myIsConst) {
        this.myIsConst = myIsConst;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsCtorOrDtor(final boolean myIsCtorOrDtor) {
        this.myIsCtorOrDtor = myIsCtorOrDtor;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsVirtual(final boolean b) {
        int myAttributes = 0;
        Label_0036: {
            try {
                if (b) {
                    myAttributes = (this.myAttributes | OCSymbolAttribute.VIRTUAL.getMask());
                    break Label_0036;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myAttributes = (this.myAttributes & ~OCSymbolAttribute.VIRTUAL.getMask());
        }
        this.myAttributes = myAttributes;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsFriend(final boolean b) {
        int myAttributes = 0;
        Label_0036: {
            try {
                if (b) {
                    myAttributes = (this.myAttributes | OCSymbolAttribute.FRIEND.getMask());
                    break Label_0036;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myAttributes = (this.myAttributes & ~OCSymbolAttribute.FRIEND.getMask());
        }
        this.myAttributes = myAttributes;
        return this;
    }
    
    public OCFakeFunctionSymbolBuilder setIsOperator(final boolean b) {
        int myProperties = 0;
        Label_0036: {
            try {
                if (b) {
                    myProperties = (this.myProperties | OCFunctionSymbol.Property.IS_OPERATOR.getMask());
                    break Label_0036;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myProperties = (this.myProperties & ~OCFunctionSymbol.Property.IS_OPERATOR.getMask());
        }
        this.myProperties = myProperties;
        return this;
    }
    
    @NotNull
    public OCFunctionSymbol get(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "get"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List map = ContainerUtil.map((Collection)this.myParams, pair -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "lambda$get$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return new OCDeclaratorSymbol(project, null, 4294967296L, null, OCQualifiedName.interned((String)pair.second), Collections.emptyList(), (OCType)pair.first, OCSymbolKind.PARAMETER, ArrayUtil.EMPTY_INT_ARRAY, null, (List<OCTypeParameterSymbol>)Collections.emptyList(), Collections.emptyList(), 0, 0, null, null);
        });
        final OCFunctionType ocFunctionType = new OCFunctionType(this.myReturnType, ContainerUtil.map((Collection)this.myParams, pair -> (OCType)pair.first), ContainerUtil.map((Collection)this.myParams, pair -> (String)pair.second), this.myIsConst, false, false, false);
        VirtualFile virtualFile = null;
        long n = 0L;
        OCSymbolWithQualifiedName myContainer = null;
        OCQualifiedName interned = null;
        List<OCTypeParameterSymbol> emptyList = null;
        List<OCTypeArgument> emptyList2 = null;
        int myProperties = 0;
        int myAttributes = 0;
        List<String> emptyList3 = null;
        OCFunctionType ocFunctionType2 = null;
        List<OCDeclaratorSymbol> list = null;
        OCSymbolKind ocSymbolKind = null;
        Label_0168: {
            try {
                virtualFile = null;
                n = 4294967296L;
                myContainer = this.myContainer;
                interned = OCQualifiedName.interned(this.myName);
                emptyList = Collections.emptyList();
                emptyList2 = Collections.emptyList();
                myProperties = this.myProperties;
                myAttributes = this.myAttributes;
                emptyList3 = Collections.emptyList();
                ocFunctionType2 = ocFunctionType;
                list = (List<OCDeclaratorSymbol>)map;
                if (this.myIsCtorOrDtor) {
                    ocSymbolKind = OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION;
                    break Label_0168;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocSymbolKind = OCSymbolKind.FUNCTION_DECLARATION;
        }
        final OCFunctionSymbol ocFunctionSymbol = new OCFunctionSymbol(project, virtualFile, n, myContainer, interned, (List<OCTypeParameterSymbol>)emptyList, emptyList2, myProperties, myAttributes, emptyList3, ocFunctionType2, list, ocSymbolKind, this.myVisibility);
        if (ocFunctionSymbol == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder", "get"));
        }
        return ocFunctionSymbol;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
