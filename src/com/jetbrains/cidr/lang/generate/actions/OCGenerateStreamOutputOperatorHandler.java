// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import com.intellij.openapi.project.Project;
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
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCCCppGenerateHandlerBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0014J&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0004H\u0014J.\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018H\u0014J.\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018H\u0014J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\n \u001f*\u0004\u0018\u00010\t0\tH\u0014J\b\u0010 \u001a\u00020\tH\u0014J\b\u0010!\u001a\u00020\tH\u0014J\u0010\u0010\"\u001a\n \u001f*\u0004\u0018\u00010\t0\tH\u0014J\u0012\u0010#\u001a\u0004\u0018\u00010\u00072\u0006\u0010$\u001a\u00020%H\u0002J,\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030'H\u0014J6\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030'2\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010$\u001a\u00020%2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030'H\u0014J\b\u0010.\u001a\u00020\u000bH\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/" }, d2 = { "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorHandler;", "Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;", "Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateStreamOutputOperatorContext;", "()V", "myOStreamRefType", "Lcom/jetbrains/cidr/lang/types/OCType;", "ostreamInclude", "", "allowEmptySelection", "", "context", "checkExistingFunctions", "", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;", "project", "Lcom/intellij/openapi/project/Project;", "location", "Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;", "doPerformAction", "", "actionContext", "chosenCandidates", "", "doPerformActionInCommand", "evaluateActionContext", "parent", "element", "Lcom/intellij/psi/PsiElement;", "getActionTitle", "kotlin.jvm.PlatformType", "getAllDefinedText", "getExistingTabName", "getMembersChooserTitle", "getOStreamRefType", "file", "Lcom/intellij/psi/PsiFile;", "getReplacements", "", "Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;", "fields", "getSelectedCandidates", "editor", "Lcom/intellij/openapi/editor/Editor;", "candidates", "shouldAutoFixImports", "cidr-lang" })
public class OCGenerateStreamOutputOperatorHandler extends OCCCppGenerateHandlerBase<OCStructSymbol, OCDeclaratorSymbol, OCGenerateStreamOutputOperatorContext>
{
    private final String ostreamInclude;
    private OCType myOStreamRefType;
    
    private final OCType a(final PsiFile psiFile) {
        if (this.myOStreamRefType == null) {
            final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)PsiTreeUtil.findChildOfType((PsiElement)OCElementFactory.codeFragment("#include " + this.ostreamInclude + "\nstd::ostream& __clion_dummy_foo();", psiFile.getProject(), (PsiElement)psiFile, false, false), (Class)OCFunctionDeclaration.class);
            this.myOStreamRefType = ((ocFunctionDeclaration != null) ? ocFunctionDeclaration.getReturnType() : null);
        }
        return this.myOStreamRefType;
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext, @NotNull final List<? extends OCDeclaratorSymbol> list) {
        Intrinsics.checkParameterIsNotNull((Object)ocCaretLocation, "location");
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "actionContext");
        Intrinsics.checkParameterIsNotNull((Object)list, "fields");
        final PsiFile file = ocCaretLocation.getFile();
        Intrinsics.checkExpressionValueIsNotNull((Object)file, "location.file");
        final OCType a = this.a(file);
        if (a == null) {
            OCLog.LOG.warn("Unable to locate ostream");
            return (List<OCGenerateUtil.Replacement>)CollectionsKt.emptyList();
        }
        final OCCppReferenceType to = OCCppReferenceType.to(((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().getType().cloneWithConstModifier(ocCaretLocation.getProject()));
        String s;
        if ((s = (String)CollectionsKt.firstOrNull((Iterable)OCNameSuggester.suggestForType(((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().getType(), (PsiElement)ocCaretLocation.getFile(), CollectionsKt.emptyList()))) == null) {
            s = "rhs";
        }
        final String s2 = s;
        final OCFakeFunctionSymbolBuilder addParam = new OCFakeFunctionSymbolBuilder("operator<<").setReturnType(a).setContainer(((OCActionContext<OCSymbolWithQualifiedName, M>)ocGenerateStreamOutputOperatorContext).getParent()).setVisibility(OCVisibility.PUBLIC).setIsFriend(true).setIsOperator(true).addParam(a, "os").addParam(to, s2);
        final PsiElement locateDefinition = ((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().locateDefinition();
        if (locateDefinition == null || !(locateDefinition instanceof OCStruct)) {
            return (List<OCGenerateUtil.Replacement>)CollectionsKt.emptyList();
        }
        final StringBuilder sb = new StringBuilder();
        ((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().processBaseClasses(new OCResolveContext((PsiElement)ocCaretLocation.getFile()), (OCStructSymbol.BaseClassProcessor)new OCGenerateStreamOutputOperatorHandler$getReplacements.OCGenerateStreamOutputOperatorHandler$getReplacements$1(sb, locateDefinition, s2));
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : list) {
            sb.append(" << \"" + ((sb.length() == 0) ? "" : " ") + "" + ocDeclaratorSymbol.getName() + ": \" << " + s2 + '.' + ocDeclaratorSymbol.getName());
        }
        final List<OCGenerateUtil.Replacement> newFunctionsReplacements = OCCppDefinitionsUtil.getNewFunctionsReplacements(ocCaretLocation, (OCStructLike)locateDefinition, ocGenerateStreamOutputOperatorContext.getParent(), CollectionsKt.listOf((Object)addParam.get(ocCaretLocation.getProject())), CollectionsKt.listOf((Object)("{\n" + ((sb.length() != 0) ? ("os " + (Object)sb + ";\n") : "") + "return os;\n}")), ((OCCCppGenerateHandlerBase<P, M, OCGenerateStreamOutputOperatorContext>)this).getInlinePolicy(ocGenerateStreamOutputOperatorContext));
        Intrinsics.checkExpressionValueIsNotNull((Object)newFunctionsReplacements, "OCCppDefinitionsUtil.get\u2026inePolicy(actionContext))");
        return newFunctionsReplacements;
    }
    
    @Override
    protected boolean allowEmptySelection(@NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext) {
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "context");
        return true;
    }
    
    @NotNull
    @Override
    protected Collection<OCFunctionSymbol> checkExistingFunctions(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)ocCaretLocation, "location");
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "context");
        final ArrayList list = new(java.util.ArrayList.class);
        final List<Pair<String, OCFunctionSymbol>> list2 = OCResolveUtil.findExistingFunctions(project, CollectionsKt.listOf((Object)"operator<<"), CollectionsKt.listOf((Object[])new OCStructType[] { null, ((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().getType() }), ((OCActionContext<OCSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent(), new OCResolveContext((PsiElement)((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent().getContainingOCFile()));
        final ArrayList list3 = list;
        final ArrayList list4 = list;
        final Iterable<Pair> iterable = (Iterable<Pair>)list2;
        final ArrayList<OCFunctionSymbol> list5 = new ArrayList<OCFunctionSymbol>(CollectionsKt.collectionSizeOrDefault((Iterable)list2, 10));
        final Iterator<Pair> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list5.add((OCFunctionSymbol)iterator.next().second);
        }
        final ArrayList<OCFunctionSymbol> list6 = list5;
        final ArrayList list7 = list4;
        new ArrayList(list6);
        return (ArrayList<OCFunctionSymbol>)list7;
    }
    
    @Override
    protected void doPerformAction(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext, @NotNull final List<OCDeclaratorSymbol> list) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)ocCaretLocation, "location");
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "actionContext");
        Intrinsics.checkParameterIsNotNull((Object)list, "chosenCandidates");
        final OCStruct locateDefinition = ((OCSymbolBase<OCStruct>)((OCActionContext<OCStructSymbol, M>)ocGenerateStreamOutputOperatorContext).getParent()).locateDefinition();
        if (locateDefinition == null || !(locateDefinition instanceof OCStruct)) {
            return;
        }
        ocGenerateStreamOutputOperatorContext.setDeclarationFile(locateDefinition.getContainingOCFile());
        final Iterator iterator = PsiTreeUtil.findChildrenOfType((PsiElement)locateDefinition.getContainingOCFile(), (Class)OCIncludeDirective.class).iterator();
        while (true) {
            while (iterator.hasNext()) {
                final String referenceText = iterator.next().getReferenceText();
                if (Intrinsics.areEqual((Object)referenceText, (Object)"ostream") || Intrinsics.areEqual((Object)referenceText, (Object)"iostream") || (ApplicationManager.getApplication().isUnitTestMode() && Intrinsics.areEqual((Object)referenceText, (Object)"ostream.h"))) {
                    final boolean hasRequiredIncludes = true;
                    ocGenerateStreamOutputOperatorContext.setHasRequiredIncludes(hasRequiredIncludes);
                    super.doPerformAction(project, ocCaretLocation, ocGenerateStreamOutputOperatorContext, list);
                    return;
                }
            }
            final boolean hasRequiredIncludes = false;
            continue;
        }
    }
    
    @NotNull
    @Override
    protected String getAllDefinedText() {
        final String message = OCBundle.message("generate.stream.output.usages.all.defined", new Object[0]);
        Intrinsics.checkExpressionValueIsNotNull((Object)message, "OCBundle.message(\"genera\u2026tput.usages.all.defined\")");
        return message;
    }
    
    @NotNull
    @Override
    protected String getExistingTabName() {
        final String message = OCBundle.message("generate.stream.output.usages.existing.text", new Object[0]);
        Intrinsics.checkExpressionValueIsNotNull((Object)message, "OCBundle.message(\"genera\u2026ut.usages.existing.text\")");
        return message;
    }
    
    @Override
    protected void doPerformActionInCommand(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext, @NotNull final List<OCDeclaratorSymbol> list) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)ocCaretLocation, "location");
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "actionContext");
        Intrinsics.checkParameterIsNotNull((Object)list, "chosenCandidates");
        super.doPerformActionInCommand(project, ocCaretLocation, ocGenerateStreamOutputOperatorContext, list);
        final OCFile declarationFile = ocGenerateStreamOutputOperatorContext.getDeclarationFile();
        if (declarationFile != null && !ocGenerateStreamOutputOperatorContext.getHasRequiredIncludes()) {
            ApplicationManager.getApplication().runWriteAction((Runnable)new OCGenerateStreamOutputOperatorHandler$doPerformActionInCommand.OCGenerateStreamOutputOperatorHandler$doPerformActionInCommand$1(this, project, declarationFile));
        }
    }
    
    @Override
    protected boolean shouldAutoFixImports() {
        return false;
    }
    
    @NotNull
    @Override
    protected OCGenerateStreamOutputOperatorContext evaluateActionContext(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final PsiElement psiElement) {
        Intrinsics.checkParameterIsNotNull((Object)ocStructSymbol, "parent");
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
        return new OCGenerateStreamOutputOperatorContext(ocStructSymbol, psiElement);
    }
    
    @Override
    protected String getActionTitle() {
        return OCBundle.message("generate.stream.output.action.title", new Object[0]);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return OCBundle.message("generate.stream.output.member.chooser.title", new Object[0]);
    }
    
    @NotNull
    @Override
    protected List<OCDeclaratorSymbol> getSelectedCandidates(@NotNull final OCGenerateStreamOutputOperatorContext ocGenerateStreamOutputOperatorContext, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<? extends OCDeclaratorSymbol> list) {
        Intrinsics.checkParameterIsNotNull((Object)ocGenerateStreamOutputOperatorContext, "actionContext");
        Intrinsics.checkParameterIsNotNull((Object)psiFile, "file");
        Intrinsics.checkParameterIsNotNull((Object)list, "candidates");
        return (List<OCDeclaratorSymbol>)list;
    }
    
    public OCGenerateStreamOutputOperatorHandler() {
        this.ostreamInclude = (ApplicationManager.getApplication().isUnitTestMode() ? "\"ostream.h\"" : "<ostream>");
    }
}
