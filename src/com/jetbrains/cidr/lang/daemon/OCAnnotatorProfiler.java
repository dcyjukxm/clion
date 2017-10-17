// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.lang.annotation.AnnotationHolder;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.HashMap;
import com.intellij.psi.PsiElement;
import java.util.Map;
import com.intellij.lang.annotation.Annotator;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public abstract class OCAnnotatorProfiler extends OCVisitor implements Annotator
{
    private Map<Class<? extends PsiElement>, Long> stats;
    
    public OCAnnotatorProfiler() {
        this.stats = (Map<Class<? extends PsiElement>, Long>)new HashMap();
    }
    
    public abstract void doAnnotate(@NotNull final PsiElement p0, @NotNull final AnnotationHolder p1);
    
    public void annotate(@NotNull final PsiElement psiElement, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/daemon/OCAnnotatorProfiler", "annotate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/OCAnnotatorProfiler", "annotate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.doAnnotate(psiElement, annotationHolder);
        final long n = System.currentTimeMillis() - currentTimeMillis;
        Long n2 = this.stats.get(psiElement.getClass());
        if (n2 == null) {
            n2 = new Long(0L);
        }
        this.stats.put(psiElement.getClass(), n2 + n);
    }
    
    public void printStats() {
        final ArrayList list = new ArrayList<Class<?>>(this.stats.keySet());
        Collections.sort((List<Object>)list, (clazz, clazz2) -> -this.stats.get(clazz).compareTo(Long.valueOf(this.stats.get(clazz2))));
        long n = 0L;
        final Iterator<Class<?>> iterator = list.iterator();
        while (iterator.hasNext()) {
            n += this.stats.get(iterator.next());
        }
        synchronized (OCAnnotatorProfiler.class) {
            System.out.println("\n" + this.getClass().getSimpleName() + ": Total time: " + n);
            int n2 = 0;
            while (true) {
                try {
                    if (n2 >= 7 || n2 >= list.size()) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final long longValue = this.stats.get(list.get(n2));
                System.out.println("  " + list.get(n2).getSimpleName() + ": " + longValue + " (" + (int)(Object)new Double(longValue * 100.0 / n) + "%)");
                ++n2;
            }
        }
    }
    
    @Override
    public void visitOCFile(final OCFile ocFile) {
        this.printStats();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
