// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.util.PathUtil;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.util.Processor;

class 1ImportProcessor implements Processor<OCAutoImportHelper.ImportSpecification>
{
    public OCAutoImportHelper.ImportSpecification best;
    public Delimiters bestDelimiters;
    public boolean exactMatch;
    public boolean renameOnly;
    
    1ImportProcessor() {
        this.best = null;
        this.bestDelimiters = null;
        this.exactMatch = false;
        this.renameOnly = false;
    }
    
    public boolean process(final OCAutoImportHelper.ImportSpecification importSpecification) {
        assert !this.exactMatch && !this.renameOnly;
        final Delimiters delimiters = FileReference.this.this$1.this$0.getDelimiters();
        if (importSpecification.areDelimitersAllowed(delimiters)) {
            if (!OCIncludeDirectiveImpl.access$000(importSpecification.getImportPath(), FileReference.this.this$1.this$0.getReferenceText())) {
                this.exactMatch = true;
            }
            else if (!OCIncludeDirectiveImpl.access$000(PathUtil.getParentPath(importSpecification.getImportPath()), PathUtil.getParentPath(FileReference.this.this$1.this$0.getReferenceText()))) {
                this.renameOnly = true;
            }
            if (this.exactMatch || this.renameOnly) {
                this.best = importSpecification;
                this.bestDelimiters = delimiters;
                return false;
            }
        }
        final Delimiters bestDelimiters = importSpecification.areDelimitersAllowed(delimiters) ? delimiters : importSpecification.getPreferredDelimiters();
        if (this.best == null || (this.bestDelimiters != delimiters && bestDelimiters == delimiters)) {
            this.best = importSpecification;
            this.bestDelimiters = bestDelimiters;
        }
        return true;
    }
}
