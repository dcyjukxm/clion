// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;

class OCSpacingProcessor$2 extends LeftBracesProcessor<Boolean> {
    @Override
    public Boolean processNamespace() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.ocSettings.SPACE_BEFORE_NAMESPACE_LBRACE);
    }
    
    @Override
    public Boolean processInterfaceOrStructure() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_CLASS_LBRACE);
    }
    
    @Override
    public Boolean processMethod() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_METHOD_LBRACE);
    }
    
    @Override
    public Boolean processFunction() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_METHOD_LBRACE);
    }
    
    @Override
    public Boolean processBlock() {
        return this.processFunction();
    }
    
    @Override
    public Boolean processIfStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCFormatterUtil.isPrecededBy(OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCTokenTypes.ELSE_KEYWORD) ? OCSpacingProcessor.this.settings.SPACE_BEFORE_ELSE_LBRACE : OCSpacingProcessor.this.settings.SPACE_BEFORE_IF_LBRACE);
    }
    
    @Override
    public Boolean processForOrForEachStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_FOR_LBRACE);
    }
    
    @Override
    public Boolean processWhileStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_WHILE_LBRACE);
    }
    
    @Override
    public Boolean processDoWhileStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_DO_LBRACE);
    }
    
    @Override
    public Boolean processSwitchStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_SWITCH_LBRACE);
    }
    
    @Override
    public Boolean processTryStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_TRY_LBRACE);
    }
    
    @Override
    public Boolean processCatchStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_CATCH_LBRACE);
    }
    
    @Override
    public Boolean processFinallyStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_FINALLY_LBRACE);
    }
    
    @Override
    public Boolean processSynchronizedStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.settings.SPACE_BEFORE_SYNCHRONIZED_LBRACE);
    }
    
    @Override
    public Boolean processAutoreleasePoolStatement() {
        return OCSpacingProcessor.access$300(OCSpacingProcessor.this, OCSpacingProcessor.this.ocSettings.SPACE_BEFORE_AUTORELEASE_POOL_LBRACE);
    }
}