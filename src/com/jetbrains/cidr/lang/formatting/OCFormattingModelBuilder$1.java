// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import java.util.concurrent.atomic.AtomicInteger;

static final class OCFormattingModelBuilder$1 extends ThreadLocal<AtomicInteger> {
    @Override
    protected AtomicInteger initialValue() {
        return new AtomicInteger(0);
    }
}