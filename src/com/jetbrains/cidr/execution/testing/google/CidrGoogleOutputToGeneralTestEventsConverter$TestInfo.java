// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

static class TestInfo
{
    private String instantiation;
    private String suite;
    private String name;
    private String param;
    private String type;
    private boolean isTyped;
    private boolean isValueParameterized;
    
    public String instantiationLocation() {
        String s = this.suite;
        if (this.instantiation != null) {
            s = this.instantiation + "/" + s;
        }
        return s;
    }
    
    public String testLocation() {
        String s = this.instantiationLocation() + "." + this.name;
        if (this.param != null) {
            s = s + "=" + this.param;
        }
        if (this.isTyped) {
            s += "?typed";
        }
        return s;
    }
}
