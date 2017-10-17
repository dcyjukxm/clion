// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.sourceglider.scripts.Script;
import com.jetbrains.sourceglider.scripts.rml.RMLScript;
import com.jetbrains.sourceglider.visitors.Visitor;
import java.util.Iterator;
import com.jetbrains.sourceglider.ui.ThreadCallback;
import java.util.Map;
import com.jetbrains.sourceglider.relations.RelationSignature;
import com.jetbrains.sourceglider.symtable.SymbolTable;
import com.jetbrains.sourceglider.visitors.VisitorImpl;
import com.jetbrains.sourceglider.ui.UICallback;
import com.jetbrains.sourceglider.ui.console.Console;
import com.jetbrains.sourceglider.ui.EmptyUI;
import com.jetbrains.sourceglider.ui.console.EmptyConsole;
import com.jetbrains.sourceglider.ui.UIInstancesProvider;
import com.jetbrains.sourceglider.ManagerInstancesProvider;

class OCSourceGliderComponent$1 extends ManagerInstancesProvider {
    final /* synthetic */ int val$index;
    
    @Override
    protected UIInstancesProvider createUIInstancesProvider() {
        return new UIInstancesProvider(new EmptyConsole(), new EmptyUI());
    }
    
    @Override
    protected void registerVisitors() {
        final VisitorImpl visitorImpl = new VisitorImpl(this.symbolTable) {
            @Override
            public RelationSignature[] getOutputRelations() {
                return new OCContextSensitiveControlFlowBuilder(ManagerInstancesProvider.this.symbolTable, null).getOutputRelations();
            }
            
            @Override
            public void run(final Map map, final ThreadCallback threadCallback) {
                final Iterator<VisitorRunner> iterator = OCSourceGliderComponent.access$100(OCSourceGliderComponent.this).get((Object)ManagerInstancesProvider.this.val$index).iterator();
                while (iterator.hasNext()) {
                    iterator.next().run(map, threadCallback);
                }
            }
        };
        this.visitorsManager.addVisitor(visitorImpl);
        OCSourceGliderComponent.access$200(OCSourceGliderComponent.this).add(visitorImpl);
    }
    
    @Override
    protected void registerScripts() {
        this.scriptManager.addScript(RMLScript.readScript("AppCodeDFA.rml", OCSourceGliderComponent.class.getResource("AppCodeDFA.rml"), this));
    }
}