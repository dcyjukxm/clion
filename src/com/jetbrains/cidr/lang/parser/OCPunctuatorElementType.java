// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import gnu.trove.THashMap;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

public class OCPunctuatorElementType extends OCElementType
{
    private static final Map<String, OCPunctuatorElementType> ourAlternativeKeywords;
    
    public OCPunctuatorElementType(final String s, @Nullable final String s2) {
        super("OCPunctuator:", s);
        if (s2 != null) {
            OCPunctuatorElementType.ourAlternativeKeywords.put(s2, this);
        }
    }
    
    public OCPunctuatorElementType(final String s) {
        this(s, null);
    }
    
    public static OCPunctuatorElementType findByKeyword(final String s) {
        return OCPunctuatorElementType.ourAlternativeKeywords.get(s);
    }
    
    static {
        ourAlternativeKeywords = (Map)new THashMap();
    }
}
