// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Consumer;
import com.intellij.openapi.application.WriteAction;

static final class OCGoogleGenerateTestHandler$2 extends WriteAction {
    final /* synthetic */ Consumer val$onCorrection;
    final /* synthetic */ CommonProcessors.FindFirstProcessor val$processor;
    final /* synthetic */ OCReferenceElement val$referenceElement;
    final /* synthetic */ String val$correctMacroName;
    final /* synthetic */ OCFile val$file;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        try {
            if (this.val$onCorrection != null) {
                this.val$onCorrection.consume(this.val$processor.getFoundValue());
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        this.val$referenceElement.setNameOfIdentifier(this.val$correctMacroName);
        FileSymbolTablesCache.getInstance(this.val$file.getProject()).scheduleReparseFile(this.val$file);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}