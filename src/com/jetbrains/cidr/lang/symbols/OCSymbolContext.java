// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;

public class OCSymbolContext
{
    private OCExpectedTypeUtil.Expectable myExpectable;
    private OCSymbolKind mySymbolKind;
    private OCSymbol myParent;
    
    public OCSymbolContext(@Nullable final OCExpectedTypeUtil.Expectable myExpectable, final OCSymbolKind mySymbolKind, final OCSymbol myParent) {
        this.myExpectable = myExpectable;
        this.mySymbolKind = mySymbolKind;
        this.myParent = myParent;
    }
    
    public OCSymbolContext(final OCSymbolKind ocSymbolKind) {
        this(null, ocSymbolKind, null);
    }
    
    public String getName() {
        return this.mySymbolKind.getNameLowercase();
    }
    
    @Nullable
    public OCSymbol getParent() {
        return this.myParent;
    }
    
    public OCExpectedTypeUtil.Expectable getExpectable() {
        return this.myExpectable;
    }
    
    @Nullable
    public OCType getExpectedType() {
        return (this.myExpectable != null) ? this.myExpectable.getExpectedType() : null;
    }
    
    public OCSymbolKind getSymbolKind() {
        return this.mySymbolKind;
    }
    
    public String getCannotResolveMessagePrefix() {
        final String name = this.getName();
        if (this.myParent != null) {
            return this.myParent.getNameWithKindUppercase() + " doesn't have " + (StringUtil.isVowel(name.charAt(0)) ? "an " : "a ") + name;
        }
        return "Can't resolve " + name;
    }
    
    public static class StructMemberContext extends OCSymbolContext
    {
        private final boolean myStatic;
        
        public StructMemberContext(@Nullable final OCExpectedTypeUtil.Expectable expectable, final OCSymbolKind ocSymbolKind, final boolean myStatic, final OCSymbol ocSymbol) {
            super(expectable, ocSymbolKind, ocSymbol);
            this.myStatic = myStatic;
        }
        
        public boolean isStatic() {
            return this.myStatic;
        }
        
        @Override
        public String getName() {
            final String name = super.getName();
            return this.myStatic ? ("static " + name) : name;
        }
    }
}
