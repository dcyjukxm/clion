// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.PsiBuilder;
import com.intellij.util.containers.ContainerUtil;
import java.util.Set;

private class ClassParsingScope
{
    private boolean myHasGenericParametersForSure;
    private boolean myHasProtocolListForSure;
    private Set<String> myGenericParameterNames;
    
    private ClassParsingScope() {
        this.myHasGenericParametersForSure = false;
        this.myHasProtocolListForSure = false;
        this.myGenericParameterNames = (Set<String>)ContainerUtil.newHashSet();
    }
    
    public boolean parseClass() {
        final PsiBuilder.Marker access$300 = OCParsing.access$300(OCParsing.this);
        OCParsing.access$400(OCParsing.this);
        if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.EXTERN_KEYWORD) {
            OCParsing.access$100(OCParsing.this);
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.STRING_LITERAL) {
                OCParsing.access$100(OCParsing.this);
            }
            OCParsing.access$400(OCParsing.this);
        }
        final boolean b = OCParsing.access$500(OCParsing.this) == OCTokenTypes.INTERFACE_KEYWORD;
        final boolean b2 = OCParsing.access$500(OCParsing.this) == OCTokenTypes.IMPLEMENTATION_KEYWORD;
        final boolean b3 = OCParsing.access$500(OCParsing.this) == OCTokenTypes.PROTOCOL_KEYWORD;
        if (!b && !b2 && !b3) {
            OCParsing.access$600(OCParsing.this, access$300);
            return false;
        }
        OCParsing.access$100(OCParsing.this);
        OCParsing.access$700(OCParsing.this, "class/category/protocol name expected", false, !b3, b3, false);
        if (b && OCParsing.this.detectAngleBracketedExpressionType() == AngleBracketedExpressionType.GENERIC_PARAMETER_LIST) {
            this.a(null);
        }
        PsiBuilder.Marker marker = OCParsing.access$300(OCParsing.this);
        if (b3 && (OCParsing.access$500(OCParsing.this) == OCTokenTypes.COMMA || OCParsing.access$500(OCParsing.this) == OCTokenTypes.SEMICOLON)) {
            OCParsing.access$600(OCParsing.this, access$300);
            this.a();
            return true;
        }
        boolean b4 = !this.myHasGenericParametersForSure;
        if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.LPAR) {
            final PsiBuilder.Marker access$301 = OCParsing.access$300(OCParsing.this);
            OCParsing.access$100(OCParsing.this);
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.IDENTIFIER) {
                OCParsing.access$100(OCParsing.this);
            }
            OCParsing.access$800(OCParsing.this, OCTokenTypes.RPAR, "')' expected");
            access$301.done((IElementType)OCElementTypes.CATEGORY_NAME);
            marker.drop();
            marker = OCParsing.access$300(OCParsing.this);
            b4 = false;
        }
        final Ref ref = new Ref();
        PsiBuilder.Marker marker2 = this.b(marker);
        do {
            marker2 = this.a(marker2, (Ref<Boolean>)ref);
        } while (ref.get() == Boolean.TRUE);
        if (b4 && b && ref.get() == Boolean.FALSE) {
            marker2 = this.a(marker2);
        }
        OCParsing.access$900(OCParsing.this, marker2).drop();
        while (!OCParsing.access$1000(OCParsing.this) && OCParsing.access$500(OCParsing.this) != OCTokenTypes.END_KEYWORD && (!OCTokenTypes.OBJC_CLASS_KEYWORDS.contains(OCParsing.access$500(OCParsing.this)) || OCParsing.access$500(OCParsing.this) == OCParsing.access$1100())) {
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.OPTIONAL_KEYWORD || OCParsing.access$500(OCParsing.this) == OCTokenTypes.REQUIRED_KEYWORD) {
                if (!b3) {
                    OCParsing.access$1200(OCParsing.this, "'@optional/@required' is allowed in protocol declarations only");
                }
                OCParsing.access$100(OCParsing.this);
            }
            else if (OCParsing.access$500(OCParsing.this) == OCParsing.access$1100()) {
                this.a();
            }
            else if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.MINUS || OCParsing.access$500(OCParsing.this) == OCTokenTypes.PLUS) {
                OCParsing.access$1300(OCParsing.this);
            }
            else if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.PROPERTY_KEYWORD) {
                OCParsing.access$1400(OCParsing.this);
            }
            else if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.SYNTHESIZE_KEYWORD || OCParsing.access$500(OCParsing.this) == OCTokenTypes.DYNAMIC_KEYWORD) {
                OCParsing.access$1500(OCParsing.this);
            }
            else if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.SEMICOLON) {
                OCParsing.access$100(OCParsing.this);
            }
            else {
                if (OCParsing.access$1600(OCParsing.this, DeclarationContext.FILE) != DeclarationParsingResult.FAIL) {
                    continue;
                }
                OCParsing.access$1800(OCParsing.this, "Method or declaration expected", OCParsing.access$1700(OCParsing.this), OCElementTypes.UNKNOWN_CPP_CODE);
            }
        }
        if (b2) {
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.END_KEYWORD) {
                OCParsing.access$100(OCParsing.this);
            }
        }
        else {
            OCParsing.access$800(OCParsing.this, OCTokenTypes.END_KEYWORD, "@end missing");
        }
        OCParsing.access$1900(OCParsing.this, access$300, b ? OCElementTypes.INTERFACE : (b2 ? OCElementTypes.IMPLEMENTATION : OCElementTypes.PROTOCOL));
        return true;
    }
    
    private void a() {
        final PsiBuilder.Marker access$300 = OCParsing.access$300(OCParsing.this);
        final boolean b = OCParsing.access$500(OCParsing.this) == OCTokenTypes.PROTOCOL_KEYWORD;
        OCParsing.access$100(OCParsing.this);
        while (true) {
            final PsiBuilder.Marker access$301 = OCParsing.access$300(OCParsing.this);
            OCParsing.access$700(OCParsing.this, "Expecting class name", false, !b, b, false);
            this.a(null);
            OCParsing.access$1900(OCParsing.this, access$301, OCElementTypes.CLASS_PREDEF);
            if (OCParsing.access$500(OCParsing.this) != OCTokenTypes.COMMA) {
                break;
            }
            OCParsing.access$100(OCParsing.this);
        }
        OCParsing.access$800(OCParsing.this, OCTokenTypes.SEMICOLON, "Missing ';'");
        OCParsing.access$1900(OCParsing.this, access$300, OCElementTypes.CLASS_PREDEF_LIST);
    }
    
    private PsiBuilder.Marker a(@Nullable final PsiBuilder.Marker marker) {
        if (OCParsing.access$500(OCParsing.this) != OCTokenTypes.LT) {
            return marker;
        }
        final PsiBuilder.Marker access$300 = OCParsing.access$300(OCParsing.this);
        OCParsing.access$100(OCParsing.this);
        while (true) {
            final PsiBuilder.Marker access$301 = OCParsing.access$300(OCParsing.this);
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.COVARIANT_KEYWORD || OCParsing.access$500(OCParsing.this) == OCTokenTypes.CONTRAVARIANT_KEYWORD) {
                OCParsing.access$100(OCParsing.this);
            }
            this.myGenericParameterNames.add(OCParsing.access$700(OCParsing.this, "Expected type parameter name", false, true, false, false));
            OCParsing.access$2000(OCParsing.this);
            OCParsing.access$1900(OCParsing.this, access$301, OCElementTypes.GENERIC_PARAMETER);
            if (OCParsing.access$500(OCParsing.this) != OCTokenTypes.COMMA) {
                break;
            }
            OCParsing.access$100(OCParsing.this);
        }
        OCParsing.access$2100(OCParsing.this);
        OCParsing.access$1900(OCParsing.this, access$300, OCElementTypes.GENERIC_PARAMETERS_LIST);
        this.myHasGenericParametersForSure = true;
        if (marker != null) {
            marker.drop();
            return OCParsing.access$300(OCParsing.this);
        }
        return null;
    }
    
    private PsiBuilder.Marker a(final PsiBuilder.Marker marker, final Ref<Boolean> ref) {
        if (OCParsing.access$500(OCParsing.this) != OCTokenTypes.LT) {
            ref.set((Object)null);
            if (!this.myHasProtocolListForSure) {
                this.myHasProtocolListForSure = true;
                marker.precede().doneBefore((IElementType)OCElementTypes.PROTOCOL_LIST, marker);
            }
            return marker;
        }
        final PsiBuilder.Marker access$300 = OCParsing.access$300(OCParsing.this);
        OCParsing.access$100(OCParsing.this);
        while (true) {
            final PsiBuilder.Marker access$301 = OCParsing.access$300(OCParsing.this);
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.COVARIANT_KEYWORD || OCParsing.access$500(OCParsing.this) == OCTokenTypes.CONTRAVARIANT_KEYWORD) {
                access$301.drop();
                OCParsing.access$600(OCParsing.this, access$300);
                ref.set((Object)false);
                if (!this.myHasProtocolListForSure) {
                    this.myHasProtocolListForSure = true;
                    marker.precede().doneBefore((IElementType)OCElementTypes.PROTOCOL_LIST, marker);
                }
                return marker;
            }
            OCParsing.access$800(OCParsing.this, OCTokenTypes.IDENTIFIER, "protocol name expected");
            OCParsing.access$1900(OCParsing.this, access$301, OCElementTypes.REFERENCE_ELEMENT);
            if (OCParsing.access$500(OCParsing.this) != OCTokenTypes.COMMA) {
                this.myHasProtocolListForSure = true;
                OCParsing.access$800(OCParsing.this, OCTokenTypes.GT, "Missing '>'");
                OCParsing.access$1900(OCParsing.this, access$300, OCElementTypes.PROTOCOL_LIST);
                marker.drop();
                ref.set((Object)true);
                return OCParsing.access$300(OCParsing.this);
            }
            OCParsing.access$100(OCParsing.this);
        }
    }
    
    private PsiBuilder.Marker b(final PsiBuilder.Marker marker) {
        if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.COLON) {
            final PsiBuilder.Marker access$300 = OCParsing.access$300(OCParsing.this);
            OCParsing.access$100(OCParsing.this);
            final PsiBuilder.Marker access$301 = OCParsing.access$300(OCParsing.this);
            OCParsing.access$800(OCParsing.this, OCTokenTypes.IDENTIFIER, "super class name expected");
            final PsiBuilder.Marker access$302 = OCParsing.access$300(OCParsing.this);
            if (OCParsing.access$500(OCParsing.this) == OCTokenTypes.LT) {
                final boolean b = OCParsing.access$2200(OCParsing.this) > 1;
                if (b || OCParsing.this.detectAngleBracketedExpressionType() != AngleBracketedExpressionType.PROTOCOL_LIST) {
                    OCParsing.access$2300(OCParsing.this, b);
                }
                access$302.drop();
            }
            else {
                OCParsing.access$600(OCParsing.this, access$302);
            }
            OCParsing.access$1900(OCParsing.this, access$301, OCElementTypes.REFERENCE_ELEMENT);
            OCParsing.access$1900(OCParsing.this, access$300, OCElementTypes.SUPER_CLASS_REF);
            marker.drop();
            return OCParsing.access$300(OCParsing.this);
        }
        marker.precede().doneBefore((IElementType)OCElementTypes.SUPER_CLASS_REF, marker);
        return marker;
    }
}
