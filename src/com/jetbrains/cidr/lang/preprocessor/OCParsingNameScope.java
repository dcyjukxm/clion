// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.Map;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TIntObjectIterator;
import java.util.Iterator;
import java.util.Collections;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.ArrayList;
import gnu.trove.TIntObjectHashMap;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;

public class OCParsingNameScope
{
    public static final int INDEX_BEFORE_FIRST = -1;
    private static final String LOCAL_SCOPE_NAME = "$LOCAL_SCOPE$";
    public static final String GLOBAL_SCOPE_NAME = "";
    private static final int LAST_COMPONENT_INDEX = 1073741823;
    @NotNull
    private final String myName;
    @NotNull
    private final THashSet<String> myProtocolNames;
    @NotNull
    private final THashMap<String, Kind> myNameKinds;
    @NotNull
    private final THashMap<String, OCParsingNameScope> myInnerScopes;
    @NotNull
    private final TIntObjectHashMap<ArrayList<String>> myTypedefIndices;
    @Nullable
    private List<OCParsingNameScope> myNamespaceUsings;
    @Nullable
    private OCParsingNameScope myParent;
    @Nullable
    private OCParsingNameScope myDelegate;
    @NotNull
    private List<Pair<String, Integer>> myTemplateTypeParameters;
    private List<Pair<String, Integer>> myTemplateValueParameters;
    
    @Nullable
    public OCParsingNameScope getDelegate() {
        return this.myDelegate;
    }
    
    public OCParsingNameScope() {
        this("", null);
    }
    
    @NotNull
    public OCParsingNameScope copy() {
        OCParsingNameScope delegatingCopy;
        try {
            delegatingCopy = this.delegatingCopy();
            if (delegatingCopy == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "copy"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return delegatingCopy;
    }
    
    @NotNull
    public OCParsingNameScope delegatingCopy() {
        final OCParsingNameScope parent = this.getParent();
        String name = null;
        OCParsingNameScope delegatingCopy = null;
        Label_0026: {
            try {
                name = this.getName();
                if (parent == null) {
                    delegatingCopy = null;
                    break Label_0026;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            delegatingCopy = parent.delegatingCopy();
        }
        final OCParsingNameScope scope = this.newScope(name, delegatingCopy);
        OCParsingNameScope ocParsingNameScope;
        try {
            scope.myDelegate = this;
            ocParsingNameScope = scope;
            if (ocParsingNameScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "delegatingCopy"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocParsingNameScope;
    }
    
    @NotNull
    protected OCParsingNameScope newScope(@NotNull final String s, @Nullable final OCParsingNameScope ocParsingNameScope) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "newScope"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCParsingNameScope ocParsingNameScope2;
        try {
            ocParsingNameScope2 = new OCParsingNameScope(s, ocParsingNameScope);
            if (ocParsingNameScope2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "newScope"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocParsingNameScope2;
    }
    
    private String a() {
        try {
            if (this.getName() == "") {
                return "::";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.getName() == "$LOCAL_SCOPE$") {
                return "<local>";
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return this.getName();
    }
    
    protected OCParsingNameScope(@NotNull final String myName, @Nullable final OCParsingNameScope myParent) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "<init>"));
        }
        this.myTypedefIndices = (TIntObjectHashMap<ArrayList<String>>)new TIntObjectHashMap();
        this.myTemplateTypeParameters = new ArrayList<Pair<String, Integer>>();
        this.myTemplateValueParameters = new ArrayList<Pair<String, Integer>>();
        this.myName = myName;
        this.myProtocolNames = (THashSet<String>)new THashSet();
        this.myNameKinds = (THashMap<String, Kind>)new THashMap();
        this.myInnerScopes = (THashMap<String, OCParsingNameScope>)new THashMap();
        if ((this.myParent = myParent) != null) {
            myParent.myInnerScopes.put((Object)myName, (Object)this);
            if (myParent.myDelegate != null) {
                final OCParsingNameScope myDelegate = (OCParsingNameScope)myParent.myDelegate.myInnerScopes.get((Object)myName);
                try {
                    if (myDelegate != null) {
                        this.myDelegate = myDelegate;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
            }
        }
    }
    
    public static Kind getTypeKind(final boolean b) {
        try {
            if (b) {
                return Kind.TEMPLATE_TYPE;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return Kind.SIMPLE_TYPE;
    }
    
    public static boolean isTemplate(@Nullable final Kind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TEMPLATE_TYPE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //     4: if_acmpeq       35
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TEMPLATE_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    11: if_acmpeq       35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.UNKNOWN:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    25: if_acmpne       43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     39     39     43     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isType(@Nullable final Kind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.SIMPLE_TYPE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //     4: if_acmpeq       63
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TEMPLATE_TYPE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    11: if_acmpeq       63
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.OBJC_INTERFACE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    25: if_acmpeq       63
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TYPE_AND_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    39: if_acmpeq       63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.UNKNOWN:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    53: if_acmpne       71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     56     59     63     Ljava/lang/IllegalStateException;
        //  49     67     67     71     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isCppType(@Nullable final Kind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.SIMPLE_TYPE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //     4: if_acmpeq       49
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TEMPLATE_TYPE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    11: if_acmpeq       49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TYPE_AND_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    25: if_acmpeq       49
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.UNKNOWN:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    39: if_acmpne       57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     53     53     57     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isValue(@Nullable final Kind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.SIMPLE_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //     4: if_acmpeq       49
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TEMPLATE_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    11: if_acmpeq       49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.TYPE_AND_VALUE:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    25: if_acmpeq       49
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getstatic       com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind.UNKNOWN:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //    39: if_acmpne       57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     53     53     57     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Nullable
    public OCParsingNameScope getParent() {
        return this.myParent;
    }
    
    public OCParsingNameScope dropAndGetParent() {
        try {
            if (this.myParent != null) {
                this.myParent.myInnerScopes.remove((Object)this.myName);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.myParent;
    }
    
    @Nullable
    public Kind getKind(final String s) {
        return this.getKind(Collections.singletonList(s));
    }
    
    @Nullable
    public Kind getKind(final List<String> list) {
        final ResolveContext resolveContext = new ResolveContext();
        Label_0044: {
            try {
                if (list.size() <= 1) {
                    return this.b(list, 0, resolveContext);
                }
                final List<String> list2 = list;
                final int n = 0;
                final String s = list2.get(n);
                final String s2 = s;
                final String s3 = "";
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0044;
                }
                return this.b(list, 0, resolveContext);
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final List<String> list2 = list;
                final int n = 0;
                final String s = list2.get(n);
                final String s2 = s;
                final String s3 = "";
                final boolean b = s2.equals(s3);
                if (b) {
                    return this.a(list, resolveContext);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return this.b(list, 0, resolveContext);
    }
    
    @Nullable
    private Kind a(@NotNull final String s, @Nullable ResolveContext resolveContext) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "getKindTerminal"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Kind kind = null;
        OCParsingNameScope myDelegate = this;
        while (true) {
            try {
                if (kind != null || myDelegate == null) {
                    break;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            kind = (Kind)myDelegate.myNameKinds.get((Object)s);
            Label_0203: {
                Label_0117: {
                    Label_0097: {
                        try {
                            if (kind != null) {
                                break Label_0203;
                            }
                            final OCParsingNameScope ocParsingNameScope = myDelegate;
                            final List<OCParsingNameScope> list = ocParsingNameScope.myNamespaceUsings;
                            if (list != null) {
                                break Label_0097;
                            }
                            break Label_0203;
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCParsingNameScope ocParsingNameScope = myDelegate;
                            final List<OCParsingNameScope> list = ocParsingNameScope.myNamespaceUsings;
                            if (list == null) {
                                break Label_0203;
                            }
                            if (resolveContext != null) {
                                break Label_0117;
                            }
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                    }
                    resolveContext = new ResolveContext();
                }
                if (resolveContext.shouldCheckAtOffset(myDelegate, 1073741823)) {
                    final Iterator<OCParsingNameScope> iterator = myDelegate.myNamespaceUsings.iterator();
                    while (iterator.hasNext()) {
                        final Kind a = iterator.next().a(s, resolveContext);
                        try {
                            if (!isType(a)) {
                                if (!isValue(a)) {
                                    continue;
                                }
                            }
                        }
                        catch (IllegalStateException ex5) {
                            throw a(ex5);
                        }
                        Label_0194: {
                            break Label_0194;
                            continue;
                        }
                        kind = a;
                        break;
                    }
                }
            }
            myDelegate = myDelegate.myDelegate;
        }
        return kind;
    }
    
    public boolean isProtocol(@Nullable final String s) {
        return this.isProtocol(s, true);
    }
    
    protected boolean isProtocol(@Nullable final String p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myProtocolNames:Lgnu/trove/THashSet;
        //     4: aload_1        
        //     5: invokevirtual   gnu/trove/THashSet.contains:(Ljava/lang/Object;)Z
        //     8: ifne            103
        //    11: iload_2        
        //    12: ifeq            55
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    21: athrow         
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myDelegate:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    26: ifnull          55
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    35: athrow         
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myDelegate:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    40: aload_1        
        //    41: iconst_1       
        //    42: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.isProtocol:(Ljava/lang/String;Z)Z
        //    45: ifne            103
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    54: athrow         
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myParent:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    59: ifnull          111
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myParent:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    73: aload_1        
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myDelegate:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    78: ifnonnull       96
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: iconst_1       
        //    89: goto            97
        //    92: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: iconst_0       
        //    97: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.isProtocol:(Ljava/lang/String;Z)Z
        //   100: ifeq            111
        //   103: iconst_1       
        //   104: goto            112
        //   107: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   110: athrow         
        //   111: iconst_0       
        //   112: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      15     18     22     Ljava/lang/IllegalStateException;
        //  11     29     32     36     Ljava/lang/IllegalStateException;
        //  22     48     51     55     Ljava/lang/IllegalStateException;
        //  36     62     65     69     Ljava/lang/IllegalStateException;
        //  55     81     84     88     Ljava/lang/IllegalStateException;
        //  69     92     92     96     Ljava/lang/IllegalStateException;
        //  97     107    107    111    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Nullable
    protected Kind getKind(final List<String> list, final int n, final ResolveContext resolveContext) {
        if (list.size() == 1) {
            for (final Pair<String, Integer> pair : this.myTemplateValueParameters) {
                try {
                    if (((String)pair.first).equals(list.get(0))) {
                        return Kind.SIMPLE_VALUE;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
            }
            for (final Pair<String, Integer> pair2 : this.myTemplateTypeParameters) {
                try {
                    if (((String)pair2.first).equals(list.get(0))) {
                        return Kind.SIMPLE_TYPE;
                    }
                    continue;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        Kind kind = null;
        final String s = list.get(n);
        if (list.size() == n + 1) {
            kind = this.a(s, resolveContext);
        }
        else {
            OCParsingNameScope myDelegate = this;
            while (true) {
                try {
                    if (kind != null || myDelegate == null) {
                        break;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                final OCParsingNameScope ocParsingNameScope = (OCParsingNameScope)myDelegate.myInnerScopes.get((Object)s);
                if (ocParsingNameScope != null) {
                    kind = ocParsingNameScope.getKind(list, n + 1, resolveContext);
                    if (kind == null) {
                        kind = Kind.UNKNOWN;
                    }
                }
                Label_0376: {
                    Label_0261: {
                        try {
                            if (kind != null) {
                                break Label_0376;
                            }
                            final OCParsingNameScope ocParsingNameScope2 = myDelegate;
                            final List<OCParsingNameScope> list2 = ocParsingNameScope2.myNamespaceUsings;
                            if (list2 != null) {
                                break Label_0261;
                            }
                            break Label_0376;
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final OCParsingNameScope ocParsingNameScope2 = myDelegate;
                            final List<OCParsingNameScope> list2 = ocParsingNameScope2.myNamespaceUsings;
                            if (list2 == null) {
                                break Label_0376;
                            }
                            if (!resolveContext.shouldCheckAtOffset(myDelegate, n)) {
                                break Label_0376;
                            }
                        }
                        catch (IllegalStateException ex5) {
                            throw a(ex5);
                        }
                    }
                    boolean b = false;
                    final Iterator<OCParsingNameScope> iterator3 = myDelegate.myNamespaceUsings.iterator();
                    while (iterator3.hasNext()) {
                        kind = iterator3.next().b(list, n, resolveContext);
                        if (kind == Kind.NON_TYPE) {
                            b = true;
                        }
                        else {
                            try {
                                if (kind != null) {
                                    break;
                                }
                                continue;
                            }
                            catch (IllegalStateException ex6) {
                                throw a(ex6);
                            }
                        }
                    }
                    try {
                        if (kind != null || !b) {
                            break Label_0376;
                        }
                    }
                    catch (IllegalStateException ex7) {
                        throw a(ex7);
                    }
                    kind = Kind.NON_TYPE;
                }
                myDelegate = myDelegate.myDelegate;
            }
        }
        return kind;
    }
    
    @Nullable
    private Kind b(final List<String> list, final int n, final ResolveContext resolveContext) {
        final Kind kind = this.getKind(list, n, resolveContext);
        Label_0028: {
            try {
                if (this.myParent == null) {
                    return kind;
                }
                final Kind kind2 = kind;
                if (kind2 == null) {
                    break Label_0028;
                }
                break Label_0028;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final Kind kind2 = kind;
                if (kind2 == null) {
                    return this.myParent.b(list, n, resolveContext);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        if (kind == Kind.NON_TYPE) {
            final Kind b = this.myParent.b(list, n, resolveContext);
            try {
                if (b != null) {
                    return b;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            return Kind.NON_TYPE;
        }
        return kind;
    }
    
    @Nullable
    private Kind a(final List<String> list, final ResolveContext resolveContext) {
        try {
            if (this.myParent != null) {
                return this.myParent.a(list, resolveContext);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (list.size() > 1) {
                return this.getKind(list, 1, resolveContext);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return Kind.TEMPLATE_TYPE;
    }
    
    public void undefineFromIndex(final int n) {
        final TIntObjectIterator iterator = this.myTypedefIndices.iterator();
        while (iterator.hasNext()) {
            iterator.advance();
            if (iterator.key() >= n) {
                final Iterator iterator2 = ((ArrayList)iterator.value()).iterator();
                while (iterator2.hasNext()) {
                    this.myNameKinds.remove((Object)iterator2.next());
                }
                iterator.remove();
            }
        }
    }
    
    private void a(final String s, final int n) {
        ArrayList<String> list = (ArrayList<String>)this.myTypedefIndices.get(n);
        if (list == null) {
            list = new ArrayList<String>();
            this.myTypedefIndices.put(n, (Object)list);
        }
        list.add(s);
    }
    
    @Nullable
    public Kind defineType(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.defineType(s, b, false, -1);
    }
    
    @Nullable
    public Kind defineType(@NotNull final String s, final boolean b, final boolean b2, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Kind kind = null;
        Label_0061: {
            try {
                if (b) {
                    kind = Kind.TEMPLATE_TYPE;
                    break Label_0061;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            kind = Kind.SIMPLE_TYPE;
        }
        Kind type_AND_VALUE = kind;
        final Kind a = this.a(s, null);
        if (isValue(a)) {
            type_AND_VALUE = Kind.TYPE_AND_VALUE;
        }
        else {
            try {
                if (a == Kind.TEMPLATE_TYPE) {
                    return Kind.TEMPLATE_TYPE;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            Label_0122: {
                try {
                    if (!b2) {
                        return this.a(s, n, type_AND_VALUE);
                    }
                    final OCParsingNameScope ocParsingNameScope = this;
                    final String s2 = s;
                    final Kind kind2 = ocParsingNameScope.getKind(s2);
                    if (kind2 != null) {
                        break Label_0122;
                    }
                    return this.a(s, n, type_AND_VALUE);
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCParsingNameScope ocParsingNameScope = this;
                    final String s2 = s;
                    final Kind kind2 = ocParsingNameScope.getKind(s2);
                    if (kind2 != null) {
                        return this.getKind(s);
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
        }
        return this.a(s, n, type_AND_VALUE);
    }
    
    @Nullable
    public Kind defineInterface(final String s) {
        return this.defineInterface(s, -1);
    }
    
    @Nullable
    public Kind defineInterface(final String s, final int n) {
        Kind kind = Kind.OBJC_INTERFACE;
        if (isValue(this.a(s, null))) {
            kind = Kind.TYPE_AND_VALUE;
        }
        return this.a(s, n, kind);
    }
    
    public void defineProtocol(final String s) {
        this.defineProtocol(s, -1);
    }
    
    public void defineProtocol(final String s, final int n) {
        this.myProtocolNames.add((Object)s);
        this.a(s, n);
    }
    
    public Kind defineValue(final String s, final boolean b) {
        return this.defineValue(s, b, -1);
    }
    
    public Kind defineValue(final String s, final boolean b, final int n) {
        Kind kind = null;
        Label_0017: {
            try {
                if (b) {
                    kind = Kind.TEMPLATE_VALUE;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            kind = Kind.SIMPLE_VALUE;
        }
        Kind type_AND_VALUE = kind;
        final Kind a = this.a(s, null);
        if (isType(a)) {
            type_AND_VALUE = Kind.TYPE_AND_VALUE;
        }
        else {
            try {
                if (a == Kind.TEMPLATE_VALUE) {
                    return Kind.TEMPLATE_VALUE;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return this.a(s, n, type_AND_VALUE);
    }
    
    private Kind a(final String s, final int n, final Kind kind) {
        this.a(s, n);
        return (Kind)this.myNameKinds.put((Object)s, (Object)kind);
    }
    
    public OCParsingNameScope defineNamespace(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineNamespace"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCParsingNameScope scope = (OCParsingNameScope)this.myInnerScopes.get((Object)s);
        try {
            if (scope != null) {
                if (scope.getParent() == this) {
                    return scope;
                }
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        scope = this.newScope(s, this);
        return scope;
    }
    
    public OCParsingNameScope defineLocalScope() {
        return this.newScope("$LOCAL_SCOPE$", this);
    }
    
    @NotNull
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @Override
    public String toString() {
        return "OCParsingNameScope{" + this.a() + "}";
    }
    
    public void defineNamespaceAlias(final String s, final String s2) {
        this.defineNamespaceAlias(s, Collections.singletonList(s2));
    }
    
    public void defineNamespaceAlias(final String s, final List<String> list) {
        final OCParsingNameScope a = this.a(list);
        try {
            if (a != null) {
                this.myInnerScopes.put((Object)s, (Object)a);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    public void defineNamespaceUsing(@NotNull final List<String> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "qualifiedName"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "defineNamespaceUsing"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myNamespaceUsings:Ljava/util/List;
        //    48: ifnonnull       69
        //    51: aload_0        
        //    52: new             Ljava/util/ArrayList;
        //    55: dup            
        //    56: invokespecial   java/util/ArrayList.<init>:()V
        //    59: putfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myNamespaceUsings:Ljava/util/List;
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: aload_0        
        //    70: aload_1        
        //    71: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //    74: astore_2       
        //    75: aload_2        
        //    76: ifnull          129
        //    79: aload_2        
        //    80: aload_0        
        //    81: if_acmpeq       129
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myNamespaceUsings:Ljava/util/List;
        //    95: aload_2        
        //    96: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   101: ifne            129
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.myNamespaceUsings:Ljava/util/List;
        //   115: aload_2        
        //   116: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   121: pop            
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   128: athrow         
        //   129: return         
        //    Signature:
        //  (Ljava/util/List<Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     62     65     69     Ljava/lang/IllegalStateException;
        //  75     84     87     91     Ljava/lang/IllegalStateException;
        //  79     104    107    111    Ljava/lang/IllegalStateException;
        //  91     122    125    129    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0091:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void defineNamespaceUsing(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineNamespaceUsing"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.defineNamespaceUsing(Collections.singletonList(s));
    }
    
    public void defineSymbolUsing(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineSymbolUsing"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.defineSymbolUsing(Collections.singletonList(s));
    }
    
    public void defineSymbolUsing(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "defineSymbolUsing"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Kind kind = this.getKind(list);
        final boolean type = isType(kind);
        Label_0078: {
            try {
                if (type) {
                    break Label_0078;
                }
                final Kind kind2 = kind;
                final boolean b = isValue(kind2);
                if (!b) {
                    return;
                }
                break Label_0078;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final Kind kind2 = kind;
                final boolean b = isValue(kind2);
                if (!b) {
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final String s = list.get(list.size() - 1);
        try {
            this.a(s, Integer.MAX_VALUE, kind);
            if (type) {
                this.defineNamespaceAlias(s, list);
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
    }
    
    @NotNull
    public List<Pair<String, Integer>> getTemplateValueParameters() {
        List<Pair<String, Integer>> myTemplateValueParameters;
        try {
            myTemplateValueParameters = this.myTemplateValueParameters;
            if (myTemplateValueParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "getTemplateValueParameters"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myTemplateValueParameters;
    }
    
    @NotNull
    public List<Pair<String, Integer>> getTemplateTypeParameters() {
        List<Pair<String, Integer>> myTemplateTypeParameters;
        try {
            myTemplateTypeParameters = this.myTemplateTypeParameters;
            if (myTemplateTypeParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "getTemplateTypeParameters"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myTemplateTypeParameters;
    }
    
    public void addTemplateValueParameter(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "addTemplateValueParameter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myTemplateValueParameters.add((Pair<String, Integer>)Pair.create((Object)s, (Object)n));
    }
    
    public void addTemplateTypeParameter(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "addTemplateTypeParameter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myTemplateTypeParameters.add((Pair<String, Integer>)Pair.create((Object)s, (Object)n));
    }
    
    public void clearTemplateParameters() {
        this.myTemplateValueParameters.clear();
        this.myTemplateTypeParameters.clear();
    }
    
    @Nullable
    private OCParsingNameScope a(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope", "resolveNamespace"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final ResolveContext resolveContext = new ResolveContext();
        try {
            if (list.size() <= 0 || !list.get(0).equals("")) {
                return this.a(list, 0, resolveContext);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCParsingNameScope myParent;
        for (myParent = this; myParent.myParent != null; myParent = myParent.myParent) {}
        return myParent.a(list, 1, resolveContext);
        ocParsingNameScope = this.a(list, 0, resolveContext);
        return ocParsingNameScope;
    }
    
    @Nullable
    private OCParsingNameScope a(final List<String> list, final int n, final ResolveContext resolveContext) {
        try {
            if (n == list.size()) {
                return this;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCParsingNameScope ocParsingNameScope = null;
        final String s = list.get(n);
        OCParsingNameScope myDelegate = this;
        while (true) {
            try {
                if (ocParsingNameScope != null || myDelegate == null) {
                    break;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final OCParsingNameScope ocParsingNameScope2 = (OCParsingNameScope)myDelegate.myInnerScopes.get((Object)s);
            if (ocParsingNameScope2 != null) {
                ocParsingNameScope = ocParsingNameScope2.a(list, n + 1, resolveContext);
            }
            Label_0179: {
                Label_0103: {
                    try {
                        if (ocParsingNameScope != null) {
                            break Label_0179;
                        }
                        final OCParsingNameScope ocParsingNameScope3 = myDelegate;
                        final List<OCParsingNameScope> list2 = ocParsingNameScope3.myNamespaceUsings;
                        if (list2 != null) {
                            break Label_0103;
                        }
                        break Label_0179;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCParsingNameScope ocParsingNameScope3 = myDelegate;
                        final List<OCParsingNameScope> list2 = ocParsingNameScope3.myNamespaceUsings;
                        if (list2 == null) {
                            break Label_0179;
                        }
                        if (!resolveContext.shouldCheckAtOffset(myDelegate, n)) {
                            break Label_0179;
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                final Iterator<OCParsingNameScope> iterator = myDelegate.myNamespaceUsings.iterator();
                while (iterator.hasNext()) {
                    ocParsingNameScope = iterator.next().a(list, n, resolveContext);
                    try {
                        if (ocParsingNameScope != null) {
                            break;
                        }
                        continue;
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                }
            }
            myDelegate = myDelegate.myDelegate;
        }
        try {
            if (ocParsingNameScope != null || this.myParent == null) {
                return ocParsingNameScope;
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        ocParsingNameScope = this.myParent.a(list, n, resolveContext);
        return ocParsingNameScope;
    }
    
    public DeepEqual.Equality<OCParsingNameScope> equality() {
        return new DeepEquality();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum Kind
    {
        UNKNOWN, 
        SIMPLE_TYPE, 
        TEMPLATE_TYPE, 
        SIMPLE_VALUE, 
        TEMPLATE_VALUE, 
        OBJC_INTERFACE, 
        TYPE_AND_VALUE, 
        NON_TYPE;
    }
    
    private static class ResolveContext
    {
        private THashMap<OCParsingNameScope, List<Integer>> myCurrentOffsets;
        
        public boolean shouldCheckAtOffset(@NotNull final OCParsingNameScope ocParsingNameScope, final int n) {
            try {
                if (ocParsingNameScope == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$ResolveContext", "shouldCheckAtOffset"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            List<Integer> list;
            if (this.myCurrentOffsets == null) {
                this.myCurrentOffsets = (THashMap<OCParsingNameScope, List<Integer>>)new THashMap();
                list = null;
            }
            else {
                list = (List<Integer>)this.myCurrentOffsets.get((Object)ocParsingNameScope);
            }
            if (list != null) {
                try {
                    if (!list.contains(n)) {
                        list.add(n);
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return false;
            }
            this.myCurrentOffsets.put((Object)ocParsingNameScope, (Object)new SmartList((Object)n));
            return true;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class DeepEquality implements DeepEqual.Equality<OCParsingNameScope>
    {
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCParsingNameScope ocParsingNameScope, @NotNull final OCParsingNameScope ocParsingNameScope2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocParsingNameScope == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocParsingNameScope2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$DeepEquality", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!ocParsingNameScope.myName.equals(ocParsingNameScope2.myName)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!ocParsingNameScope.myNameKinds.equals((Object)ocParsingNameScope2.myNameKinds)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (!ocParsingNameScope.myProtocolNames.equals((Object)ocParsingNameScope2.myProtocolNames)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (!ocParsingNameScope.myTypedefIndices.equals((Object)ocParsingNameScope2.myTypedefIndices)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                if (!comparator.equalIterable(ocParsingNameScope.myNamespaceUsings, ocParsingNameScope2.myNamespaceUsings)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                if (!comparator.equalObjects(ocParsingNameScope.myParent, ocParsingNameScope2.myParent)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            try {
                if (!comparator.equalMaps((Map)ocParsingNameScope.myInnerScopes, (Map)ocParsingNameScope2.myInnerScopes)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            return true;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
