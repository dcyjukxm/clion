// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import org.jetbrains.annotations.Nullable;
import com.intellij.lang.Commenter;

public class CMakeCommenter implements Commenter
{
    @Nullable
    public String getLineCommentPrefix() {
        return "#";
    }
    
    @Nullable
    public String getBlockCommentPrefix() {
        return "#[[";
    }
    
    @Nullable
    public String getBlockCommentSuffix() {
        return "]]";
    }
    
    @Nullable
    public String getCommentedBlockCommentPrefix() {
        return "#[=[";
    }
    
    @Nullable
    public String getCommentedBlockCommentSuffix() {
        return "]=]";
    }
}
