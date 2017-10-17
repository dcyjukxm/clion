// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import java.io.File;
import com.intellij.util.CommonProcessors;

static final class MinGW$1 extends CommonProcessors.FindFirstProcessor<File> {
    protected boolean accept(final File file) {
        return MinGW.access$000().matcher(file.getName()).matches();
    }
}