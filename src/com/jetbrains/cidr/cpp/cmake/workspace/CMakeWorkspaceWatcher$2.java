// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

class CMakeWorkspaceWatcher$2 implements CMakeWorkspaceListener {
    final /* synthetic */ AtomicBoolean val$isGenerationRunning;
    final /* synthetic */ AtomicInteger val$isGenerationRefreshRunning;
    final /* synthetic */ AtomicLong val$lastGenerationTimestamp;
    
    @Override
    public void generationStarted() {
        this.val$isGenerationRunning.set(true);
        this.val$isGenerationRefreshRunning.incrementAndGet();
        this.val$lastGenerationTimestamp.set(System.currentTimeMillis());
    }
    
    @Override
    public void generationFinished() {
        this.val$isGenerationRunning.set(false);
        this.val$lastGenerationTimestamp.set(System.currentTimeMillis());
    }
    
    @Override
    public void filesRefreshedAfterGeneration() {
        this.val$isGenerationRefreshRunning.decrementAndGet();
    }
}