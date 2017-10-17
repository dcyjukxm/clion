// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.Nullable;

public static class Converter
{
    private Feature myFeature;
    private String myReceiverType;
    private String myMessageName;
    private String myIntentionName;
    private String myReplacement;
    private String myReplacementLoop;
    private boolean myRequireNilTermination;
    private SpecialFlag mySpecialFlag;
    
    private Converter(final Feature myFeature, final String myReceiverType, final String myMessageName, final String myIntentionName, final String myReplacement) {
        this.myFeature = myFeature;
        this.myReceiverType = myReceiverType;
        this.myMessageName = myMessageName;
        this.myIntentionName = myIntentionName;
        this.myReplacement = myReplacement;
    }
    
    private Converter(final Feature feature, final String s, final String s2, final String s3, final String s4, @Nullable final String myReplacementLoop, final boolean myRequireNilTermination, @Nullable final SpecialFlag mySpecialFlag) {
        this(feature, s, s2, s3, s4);
        this.myReplacementLoop = myReplacementLoop;
        this.myRequireNilTermination = myRequireNilTermination;
        this.mySpecialFlag = mySpecialFlag;
    }
    
    public Feature getFeature() {
        return this.myFeature;
    }
    
    public String getMessageName() {
        return this.myMessageName;
    }
    
    public String getIntentionName() {
        return this.myIntentionName;
    }
    
    public String getReplacement() {
        return this.myReplacement;
    }
    
    public String getReplacementLoop() {
        return this.myReplacementLoop;
    }
    
    public String getReceiverType() {
        return this.myReceiverType;
    }
    
    public boolean isRequireNilTermination() {
        return this.myRequireNilTermination;
    }
    
    public SpecialFlag getSpecialFlag() {
        return this.mySpecialFlag;
    }
}
