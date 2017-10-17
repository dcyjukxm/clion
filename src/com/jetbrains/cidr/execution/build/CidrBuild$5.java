// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.intellij.openapi.util.io.FileUtil;
import java.io.IOException;
import com.jetbrains.cidr.lang.OCLog;
import java.io.File;
import java.io.FileWriter;
import com.intellij.openapi.Disposable;

static final class CidrBuild$5 implements Disposable {
    final /* synthetic */ FileWriter val$finalWriter;
    final /* synthetic */ File val$finalFile;
    
    public void dispose() {
        if (this.val$finalWriter != null) {
            try {
                this.val$finalWriter.close();
            }
            catch (IOException ex) {
                OCLog.LOG.warn((Throwable)ex);
            }
        }
        try {
            if (this.val$finalFile != null) {
                FileUtil.delete(this.val$finalFile);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}