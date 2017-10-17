// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

public abstract static class LeftBracesProcessor<T>
{
    public T processNamespace() {
        return this.processGeneral();
    }
    
    public T processInterfaceOrStructure() {
        return this.processGeneral();
    }
    
    public T processMethod() {
        return this.processGeneral();
    }
    
    public T processFunction() {
        return this.processGeneral();
    }
    
    public T processBlock() {
        return this.processFunction();
    }
    
    public T processIfStatement() {
        return this.processGeneral();
    }
    
    public T processForOrForEachStatement() {
        return this.processGeneral();
    }
    
    public T processWhileStatement() {
        return this.processGeneral();
    }
    
    public T processDoWhileStatement() {
        return this.processGeneral();
    }
    
    public T processSwitchStatement() {
        return this.processGeneral();
    }
    
    public T processTryStatement() {
        return this.processGeneral();
    }
    
    public T processCatchStatement() {
        return this.processGeneral();
    }
    
    public T processFinallyStatement() {
        return this.processGeneral();
    }
    
    public T processSynchronizedStatement() {
        return this.processGeneral();
    }
    
    public T processAutoreleasePoolStatement() {
        return this.processGeneral();
    }
    
    public T processGeneral() {
        return null;
    }
}
