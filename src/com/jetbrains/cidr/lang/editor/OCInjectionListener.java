// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.messages.Topic;

public interface OCInjectionListener
{
    public static final Topic<OCInjectionListener> INJECTION_TOPIC = Topic.create("did inject objective-c", (Class)OCInjectionListener.class);
    
    void didInject(@NotNull final OCFile p0, final PsiLanguageInjectionHost p1);
}
