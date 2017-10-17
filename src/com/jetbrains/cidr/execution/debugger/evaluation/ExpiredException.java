// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.openapi.util.Expirable;

public class ExpiredException extends RuntimeException
{
    public ExpiredException(final EvaluationContext evaluationContext, final Expirable expirable) {
        super("expired: " + expirable + " evaluation context: " + evaluationContext);
    }
}
