// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.util.OCStringLiteralUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import java.util.List;

private static class SubstitutionResult
{
    private StringBuilder myString;
    private List<Pair<Integer, TextRange>> mySubstitutions;
    
    private SubstitutionResult() {
        this.myString = new StringBuilder();
        this.mySubstitutions = new ArrayList<Pair<Integer, TextRange>>();
    }
    
    public String getString() {
        return this.myString.toString();
    }
    
    private void a(final String s) {
        this.myString.append(s);
    }
    
    private void a(final int n, final int n2) {
        this.mySubstitutions.add((Pair<Integer, TextRange>)new Pair((Object)n, (Object)new TextRange(this.myString.length(), this.myString.length() + n2)));
    }
    
    public List<Pair<Integer, TextRange>> getSubstitutions() {
        return this.mySubstitutions;
    }
    
    public void removeLastComma() {
        int length;
        for (length = this.myString.length() - 1; length >= 0 && this.myString.charAt(length) == ' '; --length) {}
        if (length >= 0 && this.myString.charAt(length) == ',') {
            this.myString.setLength(length);
        }
    }
    
    public void onHashOperator() {
        int length;
        int n;
        for (n = (length = this.myString.length()); length > 0 && Character.isJavaIdentifierPart(this.myString.charAt(length - 1)); --length) {}
        if (length != n && OCStringLiteralUtil.isStringLiteralPrefix(this.myString.substring(length, n))) {
            this.myString.append(" ");
        }
    }
}
