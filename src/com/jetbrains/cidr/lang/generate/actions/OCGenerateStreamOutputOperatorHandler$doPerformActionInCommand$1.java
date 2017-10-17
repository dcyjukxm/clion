// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCFakeFunctionSymbolBuilder;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.lang.OCLog;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCCCppGenerateHandlerBase;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "", "run" })
static final class OCGenerateStreamOutputOperatorHandler$doPerformActionInCommand$1 implements Runnable {
    @Override
    public final void run() {
        PsiDocumentManager.getInstance(this.$project).commitAllDocuments();
        OCImportSymbolFix.addImportToFile(this.$f, "#include " + OCGenerateStreamOutputOperatorHandler.access$getOstreamInclude$p(this.this$0), OCImportSymbolFix.ImportStyle.INCLUDE, this.$f.getTextRange().getEndOffset());
    }
}