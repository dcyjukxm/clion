// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.sourceglider.atttributes.Attribute;
import com.jetbrains.sourceglider.relations.RelationSignature;

@FunctionalInterface
public interface TuplesConsumer
{
    void addTuple(final RelationSignature p0, final Attribute... p1);
}
