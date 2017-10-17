// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.io.IOException;
import java.io.File;
import com.intellij.execution.ui.ConsoleView;

class CMakeOutputConsole$5 implements Runnable {
    boolean finished = false;
    final /* synthetic */ boolean val$canceled;
    final /* synthetic */ ConsoleView val$each;
    final /* synthetic */ File val$generationDir;
    
    @Override
    public void run() {
        try {
            if (this.finished) {
                return;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            this.finished = true;
            if (this.val$canceled) {
                this.val$each.print("\n" + CPPBundle.message("cmake.console.reloadingCanceled", new Object[0]) + "\n", ConsoleViewContentType.SYSTEM_OUTPUT);
                return;
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        final File access$500 = CMakeOutputConsole.access$500(this.val$generationDir);
        try {
            FileUtil.writeToFile(access$500, CMakeOutputConsole.getFlushedConsoleText(this.val$each));
        }
        catch (IOException ex3) {
            CPPLog.LOG.warn("Can't write CMake log: " + ex3);
        }
        this.val$each.print("\n" + CPPBundle.message("cmake.console.reloadingFinished", new Object[0]) + "\n", ConsoleViewContentType.SYSTEM_OUTPUT);
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}