// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementTypes;

class OCSpacingProcessor$1 extends LeftBracesProcessor<Boolean> {
    @Override
    public Boolean processNamespace() {
        return this.a(OCSpacingProcessor.this.ocSettings.NAMESPACE_BRACE_PLACEMENT, OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCSpacingProcessor.this.thisNode.getLastChildNode());
    }
    
    @Override
    public Boolean processInterfaceOrStructure() {
        return this.a(OCSpacingProcessor.this.settings.CLASS_BRACE_STYLE, OCSpacingProcessor.access$000(OCSpacingProcessor.this), (OCSpacingProcessor.access$100(OCSpacingProcessor.this) == OCElementTypes.INSTANCE_VARIABLES_LIST) ? OCSpacingProcessor.access$000(OCSpacingProcessor.this) : OCSpacingProcessor.this.thisNode.getLastChildNode());
    }
    
    @Override
    public Boolean processMethod() {
        return this.a(OCSpacingProcessor.this.ocSettings.METHOD_BRACE_PLACEMENT, OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCSpacingProcessor.access$000(OCSpacingProcessor.this));
    }
    
    @Override
    public Boolean processFunction() {
        return this.a(OCSpacingProcessor.this.ocSettings.FUNCTION_BRACE_PLACEMENT, OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCSpacingProcessor.access$000(OCSpacingProcessor.this));
    }
    
    @Override
    public Boolean processBlock() {
        return this.a(OCSpacingProcessor.this.ocSettings.BLOCK_BRACE_PLACEMENT, OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCSpacingProcessor.access$000(OCSpacingProcessor.this));
    }
    
    @Override
    public Boolean processGeneral() {
        return this.a(OCSpacingProcessor.this.settings.BRACE_STYLE, OCSpacingProcessor.access$000(OCSpacingProcessor.this), OCSpacingProcessor.access$000(OCSpacingProcessor.this));
    }
    
    private boolean a(final int n, final ASTNode astNode, final ASTNode astNode2) {
        return OCSpacingProcessor.access$200(OCSpacingProcessor.this, n, astNode, astNode2);
    }
}