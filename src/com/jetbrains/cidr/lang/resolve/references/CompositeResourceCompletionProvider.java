// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import java.util.List;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.diagnostic.Logger;
import java.util.Iterator;
import com.jetbrains.cidr.lang.OCLog;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Function;

public class CompositeResourceCompletionProvider extends OCResourceCompletionProvider
{
    private static final Function<OCResourceCompletionProvider, String> CLASS_NAME;
    private static final Function<OCResourceCompletionProvider, String> SELECTOR;
    @NotNull
    private final OCResourceCompletionProvider[] myProviders;
    
    public CompositeResourceCompletionProvider(@NotNull final OCResourceCompletionProvider... myProviders) {
        if (myProviders == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/resolve/references/CompositeResourceCompletionProvider", "<init>"));
        }
        super(b(myProviders), a(myProviders));
        this.myProviders = myProviders;
    }
    
    private static String a(@NotNull final OCResourceCompletionProvider[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/resolve/references/CompositeResourceCompletionProvider", "argumentSelector"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return assertSameValue(Arrays.asList(array), CompositeResourceCompletionProvider.SELECTOR);
    }
    
    private static String b(@NotNull final OCResourceCompletionProvider[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/resolve/references/CompositeResourceCompletionProvider", "className"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return assertSameValue(Arrays.asList(array), CompositeResourceCompletionProvider.CLASS_NAME);
    }
    
    public static <T, V> V assertSameValue(@NotNull final Iterable<T> iterable, final Function<T, V> function) {
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/jetbrains/cidr/lang/resolve/references/CompositeResourceCompletionProvider", "assertSameValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCLog.LOG.assertTrue(iterable.iterator().hasNext());
        Object o = null;
        final Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            final Object fun = function.fun((Object)iterator.next());
            Logger log = null;
            boolean b2 = false;
            Label_0130: {
                Label_0121: {
                    try {
                        log = OCLog.LOG;
                        if (o == null) {
                            break Label_0121;
                        }
                        final Object o2 = o;
                        final Object o3 = fun;
                        final boolean b = o2.equals(o3);
                        if (b) {
                            break Label_0121;
                        }
                        break Label_0121;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final Object o2 = o;
                        final Object o3 = fun;
                        final boolean b = o2.equals(o3);
                        if (b) {
                            b2 = true;
                            break Label_0130;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                b2 = false;
            }
            log.assertTrue(b2);
            o = fun;
        }
        return (V)o;
    }
    
    @Nullable
    @Override
    protected OCResourceReference createReference(final OCExpression ocExpression, @Nullable final PsiElement psiElement) {
        final ArrayList<OCResourceReference> list = new ArrayList<OCResourceReference>();
        final OCResourceCompletionProvider[] myProviders = this.myProviders;
        for (int length = myProviders.length, i = 0; i < length; ++i) {
            list.add(myProviders[i].createReference(ocExpression, psiElement));
        }
        return new OCCompositeResourceReference(list);
    }
    
    static {
        CLASS_NAME = (ocResourceCompletionProvider -> ocResourceCompletionProvider.getClassName());
        SELECTOR = (ocResourceCompletionProvider -> ocResourceCompletionProvider.getArgumentSelector());
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
