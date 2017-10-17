// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.lang.OCBundle;

public class OCGenerateDefinitionsOutsideIntentionAction extends OCGenerateDefinitionsIntentionActionBase
{
    @Nls
    @NotNull
    public String getText() {
        String message;
        try {
            message = OCBundle.message("generate.definitions.intention.outsideText", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCGenerateDefinitionsOutsideIntentionAction", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return message;
    }
    
    @Override
    protected boolean isInline() {
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
