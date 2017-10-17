// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.move.OCCopyMoveProcessor;
import java.util.ArrayList;

private class Completer
{
    private final ArrayList<Runnable> myRefactorings;
    private volatile int myCounter;
    private final OCCopyMoveProcessor.Helper<M, R, G, P> myHelper;
    private final M myManipulator;
    
    public Completer(final OCCopyMoveProcessor.Helper<M, R, G, P> myHelper, final M myManipulator) {
        this.myRefactorings = new ArrayList<Runnable>();
        this.myCounter = 0;
        this.myHelper = myHelper;
        this.myManipulator = myManipulator;
    }
    
    void addTask(final Runnable runnable) {
        this.myRefactorings.add(runnable);
        ++this.myCounter;
    }
    
    boolean isEmpty() {
        return this.myCounter == 0;
    }
    
    void executeTasks() {
        final Iterator<Runnable> iterator = this.myRefactorings.iterator();
        while (iterator.hasNext()) {
            iterator.next().run();
        }
    }
    
    void oneTaskExecuted() {
        --this.myCounter;
        if (this.myCounter == 0) {
            this.myHelper.completeManipulation(this.myManipulator);
        }
    }
}
