// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import java.util.Iterator;
import com.jetbrains.sourceglider.ui.ThreadCallback;
import java.util.Map;
import com.jetbrains.sourceglider.relations.RelationSignature;
import com.jetbrains.sourceglider.symtable.SymbolTable;
import com.jetbrains.sourceglider.visitors.VisitorImpl;

class OCSourceGliderComponent$1$1 extends VisitorImpl {
    @Override
    public RelationSignature[] getOutputRelations() {
        return new OCContextSensitiveControlFlowBuilder(OCSourceGliderComponent$1.access$000(ManagerInstancesProvider.this), null).getOutputRelations();
    }
    
    @Override
    public void run(final Map map, final ThreadCallback threadCallback) {
        final Iterator<VisitorRunner> iterator = OCSourceGliderComponent.access$100(ManagerInstancesProvider.this.this$0).get((Object)ManagerInstancesProvider.this.val$index).iterator();
        while (iterator.hasNext()) {
            iterator.next().run(map, threadCallback);
        }
    }
}