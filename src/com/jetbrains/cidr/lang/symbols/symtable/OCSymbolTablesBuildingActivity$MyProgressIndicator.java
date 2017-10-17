// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.ide.util.DelegatingProgressIndicator;

private static class MyProgressIndicator extends DelegatingProgressIndicator
{
    private double myFromFraction;
    private double myToFraction;
    private String lastActivity;
    private long activityStarted;
    
    public MyProgressIndicator(@NotNull final ProgressIndicator progressIndicator) {
        if (progressIndicator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator", "<init>"));
        }
        super(progressIndicator);
        this.myToFraction = 1.0;
    }
    
    public void startTiming(final String lastActivity) {
        this.activityStarted = System.currentTimeMillis();
        this.lastActivity = lastActivity;
    }
    
    public void setText(final String text) {
        super.setText(text);
    }
    
    public void logTiming() {
        OCLog.LOG.info(this.lastActivity + " finished in " + (System.currentTimeMillis() - this.activityStarted) / 1000L + " s.");
    }
    
    public void setInterval(final double myFromFraction, final double myToFraction) {
        this.myFromFraction = myFromFraction;
        this.myToFraction = myToFraction;
    }
    
    public void setFraction(final double n) {
        super.setFraction(this.myFromFraction + (this.myToFraction - this.myFromFraction) * n);
    }
}
