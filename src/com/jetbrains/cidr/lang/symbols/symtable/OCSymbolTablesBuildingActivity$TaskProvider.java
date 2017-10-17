// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.util.Consumer;
import com.intellij.util.Producer;

public interface TaskProvider<T>
{
    Producer<T> getItemProvider();
    
    Consumer<T> getWorker();
}
