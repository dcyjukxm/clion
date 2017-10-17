// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import java.util.regex.Pattern;

public class OCClangMessageDescriptor
{
    public static final String EMPTY_CATEGORY = "Empty Category";
    public static final String EMPTY_GROUP = "Empty Group";
    private String myID;
    private OCClangMessageKind myKind;
    private String myCategory;
    private String myGroup;
    private Pattern myPattern;
    private int myWordsCnt;
    
    OCClangMessageDescriptor(final String myID, final OCClangMessageKind myKind, final String myCategory, final String myGroup, final Pattern myPattern, final int myWordsCnt) {
        this.myID = myID;
        this.myKind = myKind;
        this.myCategory = myCategory;
        this.myGroup = myGroup;
        this.myPattern = myPattern;
        this.myWordsCnt = myWordsCnt;
    }
    
    public String getID() {
        return this.myID;
    }
    
    public OCClangMessageKind getKind() {
        return this.myKind;
    }
    
    public String getCategory() {
        return this.myCategory;
    }
    
    public String getGroup() {
        return this.myGroup;
    }
    
    @Nullable
    public OCSuppressionGroup getSuppressionGroup() {
        return (this.myGroup != null && !this.myGroup.equals("Empty Group")) ? new OCSuppressionGroup("clang", "-W" + this.myGroup) : null;
    }
    
    public Pattern getPattern() {
        return this.myPattern;
    }
    
    public int getWordsCnt() {
        return this.myWordsCnt;
    }
    
    public enum OCClangMessageKind
    {
        Warning, 
        Error, 
        ExtWarn, 
        Extension, 
        Note, 
        Remark;
    }
}
