// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import java.util.ArrayList;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.intellij.util.containers.MultiMap;

public static class Scope
{
    private MultiMap<String, Pair<TextRange, OCPragma.Mode>> settings;
    private int ownSettingsCnt;
    private TextRange push;
    private TextRange pop;
    private List<Scope> children;
    
    public Scope() {
        this.settings = (MultiMap<String, Pair<TextRange, OCPragma.Mode>>)new MultiMap();
        this.children = new ArrayList<Scope>();
    }
    
    public boolean isTopLevel() {
        return this.pop == null;
    }
    
    public TextRange getPush() {
        return this.push;
    }
    
    public TextRange getPop() {
        return this.pop;
    }
    
    public MultiMap<String, Pair<TextRange, OCPragma.Mode>> getSettings() {
        return this.settings;
    }
    
    public int getOwnSettingsCnt() {
        return this.ownSettingsCnt;
    }
}
