// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.formatting.Block;
import java.util.List;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Alignment;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Wrap;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class OCMacroBlock extends OCSimpleBlock
{
    @NotNull
    ASTNode myFirstInjection;
    @NotNull
    ASTNode myLastInjection;
    @NotNull
    ASTNode myIndentAnchor;
    @NotNull
    ASTNode myMacroCall;
    
    public OCMacroBlock(@Nullable final Wrap wrap, @Nullable final Alignment alignment, @Nullable final Indent indent, @NotNull final OCCodeBlock ocCodeBlock, @NotNull final List<Block> list, @NotNull final ASTNode myMacroCall, @NotNull final ASTNode myFirstInjection, @NotNull final ASTNode myLastInjection, @NotNull final ASTNode myIndentAnchor) {
        if (ocCodeBlock == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlock", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "blocks", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        if (myMacroCall == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macroCall", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        if (myFirstInjection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "firstInjection", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        if (myLastInjection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lastInjection", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        if (myIndentAnchor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indentAnchor", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "<init>"));
        }
        super(wrap, alignment, indent, ocCodeBlock, list);
        this.myMacroCall = myMacroCall;
        this.myFirstInjection = myFirstInjection;
        this.myLastInjection = myLastInjection;
        this.myIndentAnchor = myIndentAnchor;
    }
    
    @NotNull
    public ASTNode getMacroCall() {
        ASTNode myMacroCall;
        try {
            myMacroCall = this.myMacroCall;
            if (myMacroCall == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "getMacroCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myMacroCall;
    }
    
    @NotNull
    public ASTNode getFirstInjection() {
        ASTNode myFirstInjection;
        try {
            myFirstInjection = this.myFirstInjection;
            if (myFirstInjection == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "getFirstInjection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myFirstInjection;
    }
    
    @NotNull
    public ASTNode getLastInjection() {
        ASTNode myLastInjection;
        try {
            myLastInjection = this.myLastInjection;
            if (myLastInjection == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "getLastInjection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myLastInjection;
    }
    
    @NotNull
    public ASTNode getIndentAnchor() {
        ASTNode myIndentAnchor;
        try {
            myIndentAnchor = this.myIndentAnchor;
            if (myIndentAnchor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMacroBlock", "getIndentAnchor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myIndentAnchor;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
