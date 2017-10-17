// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;

public interface OCAttributesList extends OCElement
{
    List<OCAttribute> getAttributes();
    
    List<String> getAttributeValues();
    
    boolean isMicrosoftAttributes();
}
