// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.intellij.ide.hierarchy.HierarchyProvider;
import com.intellij.lang.LanguageExtension;

public class LanguageIncludeHierarchy extends LanguageExtension<HierarchyProvider>
{
    public static final LanguageIncludeHierarchy INSTANCE;
    
    public LanguageIncludeHierarchy() {
        super("cidr.lang.includeHierarchyProvider");
    }
    
    static {
        INSTANCE = new LanguageIncludeHierarchy();
    }
}
