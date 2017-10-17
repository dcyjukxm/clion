// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collection;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.List;

public class OCSymbolGroupContext
{
    public static final OCSymbolGroupContext CLASS_CONTEXT;
    public static final OCSymbolGroupContext PROTOCOL_CONTEXT;
    private static final OCSymbolGroupContext C_TYPE_CONTEXT;
    private static final OCSymbolGroupContext OBJC_TYPE_CONTEXT;
    private static final OCSymbolGroupContext CPP_TYPE_CONTEXT;
    private static final OCSymbolGroupContext OBJCPP_TYPE_CONTEXT;
    public static final OCSymbolGroupContext CONSTRUCTOR_CONTEXT;
    public static final OCSymbolGroupContext MACRO_OR_MACRO_PARAMETER_CONTEXT;
    public static final OCSymbolGroupContext MACRO_OR_UNDEF_OR_MACRO_PARAMETER_CONTEXT;
    public static final OCSymbolGroupContext MACRO_CONTEXT;
    public static final OCSymbolGroupContext STRUCT_FIELD_CONTEXT;
    public static final OCSymbolGroupContext LABEL_CONTEXT;
    private final String myName;
    private final List<OCSymbolContext> mySymbolContexts;
    
    public static OCSymbolGroupContext typeContext(final OCLanguageKind ocLanguageKind) {
        Label_0039: {
            Label_0025: {
                try {
                    if (!ocLanguageKind.isCpp()) {
                        break Label_0039;
                    }
                    final OCLanguageKind ocLanguageKind2 = ocLanguageKind;
                    final boolean b = ocLanguageKind2.isObjC();
                    if (b) {
                        break Label_0025;
                    }
                    return OCSymbolGroupContext.CPP_TYPE_CONTEXT;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCLanguageKind ocLanguageKind2 = ocLanguageKind;
                    final boolean b = ocLanguageKind2.isObjC();
                    if (b) {
                        return OCSymbolGroupContext.OBJCPP_TYPE_CONTEXT;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return OCSymbolGroupContext.CPP_TYPE_CONTEXT;
            try {
                if (ocLanguageKind.isObjC()) {
                    return OCSymbolGroupContext.OBJC_TYPE_CONTEXT;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return OCSymbolGroupContext.C_TYPE_CONTEXT;
    }
    
    public OCSymbolGroupContext(final String myName, final OCSymbolKind... array) {
        this.myName = myName;
        this.mySymbolContexts = new ArrayList<OCSymbolContext>();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.mySymbolContexts.add(new OCSymbolContext(null, array[i], null));
        }
    }
    
    public OCSymbolGroupContext(final OCSymbolKind... array) {
        this.myName = "";
        this.mySymbolContexts = new ArrayList<OCSymbolContext>();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.mySymbolContexts.add(new OCSymbolContext(null, array[i], null));
        }
    }
    
    public OCSymbolGroupContext(@NotNull final OCSymbolContext ocSymbolContext) {
        if (ocSymbolContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolContext", "com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext", "<init>"));
        }
        this.myName = ocSymbolContext.getName();
        this.mySymbolContexts = new ArrayList<OCSymbolContext>();
        this.addSymbolContext(ocSymbolContext);
    }
    
    public static OCSymbolGroupContext union(final OCSymbolGroupContext ocSymbolGroupContext, @NotNull final OCSymbolContext... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext", "union"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolGroupContext == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCSymbolGroupContext ocSymbolGroupContext2 = new OCSymbolGroupContext(new OCSymbolKind[0]);
        final Iterator<OCSymbolContext> iterator = ocSymbolGroupContext.getSymbolContexts().iterator();
        while (iterator.hasNext()) {
            ocSymbolGroupContext2.addSymbolContext(iterator.next());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            ocSymbolGroupContext2.addSymbolContext(array[i]);
        }
        return ocSymbolGroupContext2;
    }
    
    public static OCSymbolGroupContext union(final OCSymbolGroupContext ocSymbolGroupContext, final OCSymbolKind... array) {
        try {
            if (ocSymbolGroupContext == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbolGroupContext ocSymbolGroupContext2 = new OCSymbolGroupContext(new OCSymbolKind[0]);
        final Iterator<OCSymbolContext> iterator = ocSymbolGroupContext.getSymbolContexts().iterator();
        while (iterator.hasNext()) {
            ocSymbolGroupContext2.addSymbolContext(iterator.next());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            ocSymbolGroupContext2.addSymbolContext(array[i]);
        }
        return ocSymbolGroupContext2;
    }
    
    public static OCSymbolGroupContext union(final OCSymbolGroupContext ocSymbolGroupContext, final OCSymbolGroupContext ocSymbolGroupContext2) {
        final OCSymbolGroupContext ocSymbolGroupContext3 = new OCSymbolGroupContext(new OCSymbolKind[0]);
        ocSymbolGroupContext3.addSymbolContexts(ocSymbolGroupContext.getSymbolContexts());
        ocSymbolGroupContext3.addSymbolContexts(ocSymbolGroupContext2.getSymbolContexts());
        return ocSymbolGroupContext3;
    }
    
    public void addSymbolContext(final OCSymbolKind ocSymbolKind) {
        this.mySymbolContexts.add(new OCSymbolContext(null, ocSymbolKind, null));
    }
    
    public void addSymbolContext(@NotNull final OCSymbolContext ocSymbolContext) {
        try {
            if (ocSymbolContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolContext", "com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext", "addSymbolContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.mySymbolContexts.add(ocSymbolContext);
    }
    
    public void addSymbolContexts(final List<OCSymbolContext> list) {
        this.mySymbolContexts.addAll(list);
    }
    
    public List<OCSymbolContext> getSymbolContexts() {
        return this.mySymbolContexts;
    }
    
    public boolean isSuitableSymbol(final OCSymbol ocSymbol) {
        return this.isSuitableSymbolKind(ocSymbol.getKind());
    }
    
    public boolean isSuitableSymbolKind(final OCSymbolKind ocSymbolKind) {
        for (final OCSymbolContext ocSymbolContext : this.mySymbolContexts) {
            try {
                if (ocSymbolKind == ocSymbolContext.getSymbolKind()) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    public String getCannotResolveMessagePrefix() {
        try {
            if (this.mySymbolContexts.size() == 1) {
                return this.mySymbolContexts.get(0).getCannotResolveMessagePrefix();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "Can't resolve " + this.myName;
    }
    
    static {
        CLASS_CONTEXT = new OCSymbolGroupContext("class", new OCSymbolKind[] { OCSymbolKind.INTERFACE, OCSymbolKind.IMPLEMENTATION, OCSymbolKind.COMPATIBILITY_ALIAS });
        PROTOCOL_CONTEXT = new OCSymbolGroupContext("protocol", new OCSymbolKind[] { OCSymbolKind.PROTOCOL });
        C_TYPE_CONTEXT = new OCSymbolGroupContext("type", new OCSymbolKind[] { OCSymbolKind.TYPEDEF, OCSymbolKind.STRUCT, OCSymbolKind.UNION, OCSymbolKind.ENUM, OCSymbolKind.BUILTIN_SYMBOL });
        OBJC_TYPE_CONTEXT = new OCSymbolGroupContext("type", new OCSymbolKind[] { OCSymbolKind.INTERFACE, OCSymbolKind.IMPLEMENTATION, OCSymbolKind.COMPATIBILITY_ALIAS, OCSymbolKind.USING_SYMBOL_ALIAS, OCSymbolKind.TYPEDEF, OCSymbolKind.STRUCT, OCSymbolKind.UNION, OCSymbolKind.ENUM, OCSymbolKind.BUILTIN_SYMBOL, OCSymbolKind.GENERIC_PARAMETER });
        CPP_TYPE_CONTEXT = new OCSymbolGroupContext("type", new OCSymbolKind[] { OCSymbolKind.TYPEDEF, OCSymbolKind.STRUCT, OCSymbolKind.UNION, OCSymbolKind.ENUM, OCSymbolKind.TEMPLATE_TYPE_PARAMETER, OCSymbolKind.SYMBOL_USING_SYMBOL, OCSymbolKind.USING_SYMBOL_ALIAS, OCSymbolKind.BUILTIN_SYMBOL });
        OBJCPP_TYPE_CONTEXT = new OCSymbolGroupContext("type", new OCSymbolKind[] { OCSymbolKind.INTERFACE, OCSymbolKind.IMPLEMENTATION, OCSymbolKind.COMPATIBILITY_ALIAS, OCSymbolKind.USING_SYMBOL_ALIAS, OCSymbolKind.TYPEDEF, OCSymbolKind.STRUCT, OCSymbolKind.UNION, OCSymbolKind.ENUM, OCSymbolKind.TEMPLATE_TYPE_PARAMETER, OCSymbolKind.SYMBOL_USING_SYMBOL, OCSymbolKind.BUILTIN_SYMBOL, OCSymbolKind.GENERIC_PARAMETER });
        CONSTRUCTOR_CONTEXT = new OCSymbolGroupContext("constructor", new OCSymbolKind[] { OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION, OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION, OCSymbolKind.TYPEDEF, OCSymbolKind.STRUCT, OCSymbolKind.UNION, OCSymbolKind.ENUM, OCSymbolKind.TEMPLATE_TYPE_PARAMETER, OCSymbolKind.SYMBOL_USING_SYMBOL, OCSymbolKind.BUILTIN_SYMBOL });
        MACRO_OR_MACRO_PARAMETER_CONTEXT = new OCSymbolGroupContext("macro or macro parameter", new OCSymbolKind[] { OCSymbolKind.MACRO, OCSymbolKind.MACRO_PARAMETER, OCSymbolKind.BUILTIN_SYMBOL });
        MACRO_OR_UNDEF_OR_MACRO_PARAMETER_CONTEXT = new OCSymbolGroupContext("macro or macro parameter", new OCSymbolKind[] { OCSymbolKind.MACRO, OCSymbolKind.UNDEF_MACRO, OCSymbolKind.MACRO_PARAMETER });
        MACRO_CONTEXT = new OCSymbolGroupContext("macro", new OCSymbolKind[] { OCSymbolKind.MACRO, OCSymbolKind.BUILTIN_SYMBOL, OCSymbolKind.COMPATIBILITY_ALIAS });
        STRUCT_FIELD_CONTEXT = new OCSymbolGroupContext("struct field", new OCSymbolKind[] { OCSymbolKind.STRUCT_FIELD });
        LABEL_CONTEXT = new OCSymbolGroupContext(new OCSymbolContext(null, OCSymbolKind.LABEL, null));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
