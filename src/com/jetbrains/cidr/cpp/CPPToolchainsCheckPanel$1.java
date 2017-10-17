// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.util.regex.Pattern;
import com.intellij.util.ExceptionUtil;

class CPPToolchainsCheckPanel$1 implements Runnable {
    String error;
    final /* synthetic */ Exception val$e;
    final /* synthetic */ StringBuilder val$output;
    
    @Override
    public void run() {
        if (this.error == null) {
            final StringBuilder sb = new StringBuilder();
            final String message = ExceptionUtil.getMessage((Throwable)this.val$e);
            if (message != null) {
                sb.append(message).append("\n\n");
            }
            if (Pattern.compile(".+directory \\W/cygdrive/.+ does not exist.*", 32).matcher(this.val$output).matches()) {
                sb.append("Selected CMake might be incompatible with Cygwin environment.\nIn order to run on Cygwin, CMake needs to be specially compiled.\n\n");
            }
            if (!(this.val$e instanceof CMakeException)) {
                sb.append(ExceptionUtil.getThrowableText((Throwable)this.val$e)).append("\n\n");
            }
            sb.append((CharSequence)this.val$output);
            this.error = sb.toString();
        }
        OutputMessages.showMessageDialog(this.error);
    }
}