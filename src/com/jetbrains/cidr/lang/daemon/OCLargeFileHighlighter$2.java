// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.ui.InputValidator;

class OCLargeFileHighlighter$2 implements InputValidator {
    public boolean checkInput(final String s) {
        return StringUtil.parseInt(s, -1) > 0;
    }
    
    public boolean canClose(final String s) {
        return true;
    }
}