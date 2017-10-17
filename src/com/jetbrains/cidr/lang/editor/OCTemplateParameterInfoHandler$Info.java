// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;

public static class Info
{
    @NotNull
    private final OCTemplateArgumentList myList;
    @NotNull
    private final List<OCTypeParameterSymbol> myParameters;
    
    public Info(@NotNull final OCTemplateArgumentList myList, @NotNull final List<OCTypeParameterSymbol> myParameters) {
        if (myList == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "<init>"));
        }
        if (myParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "<init>"));
        }
        this.myList = myList;
        this.myParameters = myParameters;
    }
    
    @NotNull
    public OCTemplateArgumentList getList() {
        OCTemplateArgumentList myList;
        try {
            myList = this.myList;
            if (myList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "getList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myList;
    }
    
    @NotNull
    public List<OCTypeParameterSymbol> getParameters() {
        List<OCTypeParameterSymbol> myParameters;
        try {
            myParameters = this.myParameters;
            if (myParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myParameters;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
