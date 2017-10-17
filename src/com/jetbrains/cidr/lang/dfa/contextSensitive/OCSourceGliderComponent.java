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
import com.jetbrains.sourceglider.ui.UICallback;
import com.jetbrains.sourceglider.ui.console.Console;
import com.jetbrains.sourceglider.ui.EmptyUI;
import com.jetbrains.sourceglider.ui.console.EmptyConsole;
import com.jetbrains.sourceglider.ui.UIInstancesProvider;
import com.intellij.openapi.application.ApplicationManager;
import java.util.ArrayList;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.sourceglider.visitors.VisitorImpl;
import com.jetbrains.sourceglider.ManagerInstancesProvider;
import java.util.List;
import com.intellij.openapi.components.ApplicationComponent;

public class OCSourceGliderComponent implements ApplicationComponent
{
    private static final String SCRIPT_PATH = "AppCodeDFA.rml";
    private static final boolean LOG_TO_CONSOLE = false;
    private static final int PROVIDERS_CNT = 2;
    private List<ManagerInstancesProvider> myProviders;
    private List<VisitorImpl> myVisitors;
    private MultiMap<Integer, VisitorRunner> myVisitorRunners;
    
    public OCSourceGliderComponent() {
        this.myProviders = new ArrayList<ManagerInstancesProvider>();
        this.myVisitors = new ArrayList<VisitorImpl>();
        this.myVisitorRunners = (MultiMap<Integer, VisitorRunner>)new MultiMap();
    }
    
    public static OCSourceGliderComponent getInstance() {
        return (OCSourceGliderComponent)ApplicationManager.getApplication().getComponent((Class)OCSourceGliderComponent.class);
    }
    
    public ManagerInstancesProvider getProvider(final int n) {
        return this.myProviders.get(n);
    }
    
    public void addVisitorRunner(final int n, final VisitorRunner visitorRunner) {
        this.myVisitorRunners.putValue((Object)n, (Object)visitorRunner);
    }
    
    public void clearVisitorRunners() {
        this.myVisitorRunners.clear();
    }
    
    public VisitorImpl getVisitor(final int n) {
        return this.myVisitors.get(n);
    }
    
    public void initComponent() {
        for (int i = 0; i < 2; ++i) {
            final ManagerInstancesProvider managerInstancesProvider = new ManagerInstancesProvider() {
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
                            final Iterator<VisitorRunner> iterator = OCSourceGliderComponent.this.myVisitorRunners.get((Object)i).iterator();
                            while (iterator.hasNext()) {
                                iterator.next().run(map, threadCallback);
                            }
                        }
                    };
                    this.visitorsManager.addVisitor(visitorImpl);
                    OCSourceGliderComponent.this.myVisitors.add(visitorImpl);
                }
                
                @Override
                protected void registerScripts() {
                    this.scriptManager.addScript(RMLScript.readScript("AppCodeDFA.rml", OCSourceGliderComponent.class.getResource("AppCodeDFA.rml"), this));
                }
            };
            managerInstancesProvider.startInit();
            managerInstancesProvider.getSymbolTable().setAllowDomainsResize(true);
            this.myProviders.add(managerInstancesProvider);
        }
    }
    
    @FunctionalInterface
    public interface VisitorRunner
    {
        void run(final Map p0, final ThreadCallback p1);
    }
}
