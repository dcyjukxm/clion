// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;

class BuilderDriverBase$2$1 implements Iterator<T> {
    int idx = 0;
    final /* synthetic */ BuilderDriverBase$2 this$1;
    
    @Override
    public boolean hasNext() {
        Label_0034: {
            try {
                if (Iterable.this.val$childrenRef.isNull()) {
                    return false;
                }
                final Iterator<T> iterator = this;
                final int n = iterator.idx;
                final Iterator<T> iterator2 = this;
                final Iterable<T> iterable = iterator2.this$1;
                final int n2 = iterable.val$count;
                if (n < n2) {
                    break Label_0034;
                }
                return false;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final Iterator<T> iterator = this;
                final int n = iterator.idx;
                final Iterator<T> iterator2 = this;
                final Iterable<T> iterable = iterator2.this$1;
                final int n2 = iterable.val$count;
                if (n < n2) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    public T next() {
        ProgressManager.checkCanceled();
        return (T)((Object[])Iterable.this.val$childrenRef.get())[this.idx++];
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}