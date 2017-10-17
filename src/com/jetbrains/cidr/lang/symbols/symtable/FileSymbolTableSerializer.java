// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.newvfs.persistent.PersistentFS;
import com.jetbrains.cidr.lang.CustomHeaderProvider;
import com.intellij.openapi.vfs.VirtualFileWithId;
import com.intellij.psi.tree.IElementType;
import java.util.HashMap;
import java.util.Map;
import com.intellij.psi.tree.TokenSet;
import java.io.OutputStream;
import com.esotericsoftware.kryo.io.FastOutput;
import java.io.DataOutputStream;
import org.jetbrains.annotations.Nullable;
import java.io.Closeable;
import com.intellij.openapi.util.io.StreamUtil;
import java.io.InputStream;
import com.esotericsoftware.kryo.io.FastInput;
import org.jetbrains.annotations.NotNull;
import java.io.DataInputStream;
import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.lang.util.OCImmutableList;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import com.jetbrains.cidr.lang.util.OCNumber;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.EnumMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.jetbrains.cidr.lang.types.OCNullability;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.expression.OCVariadicPackExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.CTypeId;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.jetbrains.cidr.lang.psi.impl.OCBlockExpressionImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCAliasUsingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUndefMacroSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCUnknownExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCQualifiedExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCommaExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCInitializerListExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLiteralExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCNewExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCastExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCArrayIndexExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCConditionalExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCPostfixExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCPrefixExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCUnaryExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCBinaryExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCDelegatingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCLocalFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbolImpl;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCLabelSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.esotericsoftware.kryo.io.Output;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import gnu.trove.TObjectIntHashMap;
import com.esotericsoftware.kryo.Serializer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class FileSymbolTableSerializer extends OCSerializer
{
    private static final int CHUNK_SIZE = 65536;
    private VirtualFile myCurrentFile;
    private Project myProject;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public FileSymbolTableSerializer(final Project myProject) {
        this.myProject = myProject;
    }
    
    @Override
    protected void registerSerializers() {
        final Kryo kryo = this.getKryo();
        kryo.register((Class)FileSymbolTablesPack.class, (Serializer)new FileSymbolTablesPack.FileSymbolsPackSerializer(kryo));
        kryo.register((Class)TObjectIntHashMap.class, (Serializer)new OCJavaSerializer());
        kryo.register((Class)ContextSignature.class, (Serializer)new FieldSerializer<ContextSignature>(kryo, ContextSignature.class) {
            protected ContextSignature create(final Kryo kryo, final Input input, final Class<ContextSignature> clazz) {
                return new ContextSignature();
            }
        });
        kryo.register((Class)FileSymbolTable.class, (Serializer)new ProjectAndFileOwnerSerializer<FileSymbolTable>(FileSymbolTable.class) {
            @Override
            public FileSymbolTable createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new FileSymbolTable();
            }
        });
        kryo.register((Class)OCQualifiedName.class, (Serializer)new Serializer<OCQualifiedName>(true) {
            public void write(final Kryo kryo, final Output output, final OCQualifiedName ocQualifiedName) {
                kryo.writeClassAndObject(output, (Object)ocQualifiedName.getQualifier());
                kryo.writeObjectOrNull(output, (Object)ocQualifiedName.getName(), (Class)String.class);
            }
            
            public OCQualifiedName read(final Kryo kryo, final Input input, final Class<OCQualifiedName> clazz) {
                return OCQualifiedName.interned((OCQualifiedName)kryo.readClassAndObject(input), (String)kryo.readObjectOrNull(input, (Class)String.class));
            }
        });
        kryo.register((Class)OCQualifiedNameWithArguments.class, (Serializer)new FieldSerializer<OCQualifiedNameWithArguments>(kryo, OCQualifiedNameWithArguments.class) {
            protected OCQualifiedNameWithArguments create(final Kryo kryo, final Input input, final Class<OCQualifiedNameWithArguments> clazz) {
                return new OCQualifiedNameWithArguments();
            }
        });
        kryo.register((Class)OCImplementationSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCImplementationSymbol>(OCImplementationSymbol.class) {
            @Override
            public OCImplementationSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCImplementationSymbol();
            }
        });
        kryo.register((Class)OCInterfaceSymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCInterfaceSymbolImpl>(OCInterfaceSymbolImpl.class) {
            @Override
            public OCInterfaceSymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCInterfaceSymbolImpl();
            }
        });
        kryo.register((Class)OCProtocolSymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCProtocolSymbolImpl>(OCProtocolSymbolImpl.class) {
            @Override
            public OCProtocolSymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCProtocolSymbolImpl();
            }
        });
        kryo.register((Class)OCLabelSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCLabelSymbol>(OCLabelSymbol.class) {
            @Override
            public OCLabelSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCLabelSymbol();
            }
        });
        kryo.register((Class)OCMacroParameterSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCMacroParameterSymbol>(OCMacroParameterSymbol.class) {
            @Override
            public OCMacroParameterSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCMacroParameterSymbol();
            }
        });
        kryo.register((Class)OCMacroSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCMacroSymbol>(OCMacroSymbol.class) {
            @Override
            public OCMacroSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCMacroSymbol();
            }
        });
        kryo.register((Class)OCInstanceVariableSymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCInstanceVariableSymbolImpl>(OCInstanceVariableSymbolImpl.class) {
            @Override
            public OCInstanceVariableSymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCInstanceVariableSymbolImpl();
            }
        });
        kryo.register((Class)OCMethodSymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCMethodSymbolImpl>(OCMethodSymbolImpl.class) {
            @Override
            public OCMethodSymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCMethodSymbolImpl();
            }
        });
        kryo.register((Class)OCPropertySymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCPropertySymbolImpl>(OCPropertySymbolImpl.class) {
            @Override
            public OCPropertySymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCPropertySymbolImpl();
            }
        });
        kryo.register((Class)OCSynthesizeSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCSynthesizeSymbol>(OCSynthesizeSymbol.class) {
            @Override
            public OCSynthesizeSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCSynthesizeSymbol();
            }
        });
        kryo.register((Class)OCDeclaratorSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCDeclaratorSymbol>(OCDeclaratorSymbol.class) {
            @Override
            public OCDeclaratorSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCDeclaratorSymbol();
            }
        });
        kryo.register((Class)OCFunctionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCFunctionSymbol>(OCFunctionSymbol.class) {
            @Override
            public OCFunctionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCFunctionSymbol();
            }
        });
        kryo.register((Class)OCLocalFunctionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCLocalFunctionSymbol>(OCLocalFunctionSymbol.class) {
            @Override
            public OCLocalFunctionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCLocalFunctionSymbol();
            }
        });
        kryo.register((Class)OCNamespaceSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCNamespaceSymbol>(OCNamespaceSymbol.class) {
            @Override
            public OCNamespaceSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCNamespaceSymbol();
            }
        });
        kryo.register((Class)OCNamespaceAliasSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCNamespaceAliasSymbol>(OCNamespaceAliasSymbol.class) {
            @Override
            public OCNamespaceAliasSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCNamespaceAliasSymbol();
            }
        });
        kryo.register((Class)OCStructSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCStructSymbol>(OCStructSymbol.class) {
            @Override
            public OCStructSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCStructSymbol();
            }
        });
        kryo.register((Class)OCDelegatingSymbol.class, (Serializer)new Serializer() {
            public void write(final Kryo kryo, final Output output, final Object o) {
                final OCSymbol rawDelegate = ((OCDelegatingSymbol)o).getRawDelegate();
                kryo.writeClass(output, (Class)rawDelegate.getClass());
                kryo.writeObject(output, (Object)rawDelegate);
            }
            
            public Object read(final Kryo kryo, final Input input, final Class clazz) {
                return new OCDelegatingSymbol((OCSymbol<PsiElement>)kryo.readObject(input, kryo.readClass(input).getType()));
            }
        });
        kryo.register((Class)OCTypeParameterTypeSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCTypeParameterTypeSymbol>(OCTypeParameterTypeSymbol.class) {
            @Override
            public OCTypeParameterTypeSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCTypeParameterTypeSymbol();
            }
        });
        kryo.register((Class)OCTypeParameterValueSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCTypeParameterValueSymbol>(OCTypeParameterValueSymbol.class) {
            @Override
            public OCTypeParameterValueSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCTypeParameterValueSymbol();
            }
        });
        kryo.register((Class)OCBinaryExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCBinaryExpressionSymbol>(OCBinaryExpressionSymbol.class) {
            @Override
            public OCBinaryExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCBinaryExpressionSymbol();
            }
        });
        kryo.register((Class)OCUnaryExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCUnaryExpressionSymbol>(OCUnaryExpressionSymbol.class) {
            @Override
            public OCUnaryExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCUnaryExpressionSymbol();
            }
        });
        kryo.register((Class)OCPrefixExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCPrefixExpressionSymbol>(OCPrefixExpressionSymbol.class) {
            @Override
            public OCPrefixExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCPrefixExpressionSymbol();
            }
        });
        kryo.register((Class)OCPostfixExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCPostfixExpressionSymbol>(OCPostfixExpressionSymbol.class) {
            @Override
            public OCPostfixExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCPostfixExpressionSymbol();
            }
        });
        kryo.register((Class)OCConditionalExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCConditionalExpressionSymbol>(OCConditionalExpressionSymbol.class) {
            @Override
            public OCConditionalExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCConditionalExpressionSymbol();
            }
        });
        kryo.register((Class)OCCallExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCCallExpressionSymbol>(OCCallExpressionSymbol.class) {
            @Override
            public OCCallExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCCallExpressionSymbol();
            }
        });
        kryo.register((Class)OCArrayIndexExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCArrayIndexExpressionSymbol>(OCArrayIndexExpressionSymbol.class) {
            @Override
            public OCArrayIndexExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCArrayIndexExpressionSymbol();
            }
        });
        kryo.register((Class)OCCastExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCCastExpressionSymbol>(OCCastExpressionSymbol.class) {
            @Override
            public OCCastExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCCastExpressionSymbol();
            }
        });
        kryo.register((Class)OCNewExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCNewExpressionSymbol>(OCNewExpressionSymbol.class) {
            @Override
            public OCNewExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCNewExpressionSymbol();
            }
        });
        kryo.register((Class)OCSizeofExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCSizeofExpressionSymbol>(OCSizeofExpressionSymbol.class) {
            @Override
            public OCSizeofExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCSizeofExpressionSymbol();
            }
        });
        kryo.register((Class)OCLiteralExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCLiteralExpressionSymbol>(OCLiteralExpressionSymbol.class) {
            @Override
            public OCLiteralExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCLiteralExpressionSymbol();
            }
        });
        kryo.register((Class)OCInitializerListExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCInitializerListExpressionSymbol>(OCInitializerListExpressionSymbol.class) {
            @Override
            public OCInitializerListExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCInitializerListExpressionSymbol();
            }
        });
        kryo.register((Class)OCCommaExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCCommaExpressionSymbol>(OCCommaExpressionSymbol.class) {
            @Override
            public OCCommaExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCCommaExpressionSymbol();
            }
        });
        kryo.register((Class)OCLambdaExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCLambdaExpressionSymbol>(OCLambdaExpressionSymbol.class) {
            @Override
            public OCLambdaExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCLambdaExpressionSymbol();
            }
        });
        kryo.register((Class)OCReferenceExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCReferenceExpressionSymbol>(OCReferenceExpressionSymbol.class) {
            @Override
            public OCReferenceExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCReferenceExpressionSymbol();
            }
        });
        kryo.register((Class)OCQualifiedExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCQualifiedExpressionSymbol>(OCQualifiedExpressionSymbol.class) {
            @Override
            public OCQualifiedExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCQualifiedExpressionSymbol();
            }
        });
        kryo.register((Class)OCUnknownExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCUnknownExpressionSymbol>(OCUnknownExpressionSymbol.class) {
            @Override
            public OCUnknownExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCUnknownExpressionSymbol();
            }
        });
        kryo.register((Class)OCUndefMacroSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCUndefMacroSymbol>(OCUndefMacroSymbol.class) {
            @Override
            public OCUndefMacroSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCUndefMacroSymbol();
            }
        });
        kryo.register((Class)OCUsingSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCUsingSymbol>(OCUsingSymbol.class) {
            @Override
            public OCUsingSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCUsingSymbol();
            }
        });
        kryo.register((Class)OCAliasUsingSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCAliasUsingSymbol>(OCAliasUsingSymbol.class) {
            @Override
            public OCAliasUsingSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCAliasUsingSymbol();
            }
        });
        kryo.register((Class)OCCompatibilityAliasSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCCompatibilityAliasSymbol>(OCCompatibilityAliasSymbol.class) {
            @Override
            public OCCompatibilityAliasSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCCompatibilityAliasSymbol();
            }
        });
        kryo.register((Class)OCBlockExpressionImpl.OCBlockSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCBlockExpressionImpl.OCBlockSymbol>(OCBlockExpressionImpl.OCBlockSymbol.class) {
            @Override
            public OCBlockExpressionImpl.OCBlockSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCBlockExpressionImpl.OCBlockSymbol();
            }
        });
        kryo.register((Class)OCThisSelfSuperSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCThisSelfSuperSymbol>(OCThisSelfSuperSymbol.class) {
            @Override
            public OCThisSelfSuperSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCThisSelfSuperSymbol();
            }
        });
        kryo.register((Class)OCPunctuatorElementType.class, (Serializer)new Serializer<OCPunctuatorElementType>() {
            public void write(final Kryo kryo, final Output output, final OCPunctuatorElementType ocPunctuatorElementType) {
                output.writeString(ocPunctuatorElementType.toString());
            }
            
            public OCPunctuatorElementType read(final Kryo kryo, final Input input, final Class<OCPunctuatorElementType> clazz) {
                return OCTokenTypes.ourNameToTokenType.get(input.readString());
            }
        });
        kryo.register((Class)OCElementType.class, (Serializer)new Serializer<OCElementType>() {
            public void write(final Kryo kryo, final Output output, final OCElementType ocElementType) {
                output.writeString(ocElementType.toString());
            }
            
            public OCElementType read(final Kryo kryo, final Input input, final Class<OCElementType> clazz) {
                return OCTokenTypes.ourNameToTokenType.get(input.readString());
            }
        });
        kryo.register((Class)OCKeywordElementType.class, (Serializer)new Serializer<OCKeywordElementType>() {
            public void write(final Kryo kryo, final Output output, final OCKeywordElementType ocKeywordElementType) {
                output.writeString(ocKeywordElementType.toString());
            }
            
            public OCKeywordElementType read(final Kryo kryo, final Input input, final Class<OCKeywordElementType> clazz) {
                return OCTokenTypes.ourNameToTokenType.get(input.readString());
            }
        });
        kryo.register((Class)OCMethodSymbolImpl.SelectorPartSymbolImpl.class, (Serializer)new FieldSerializer<OCMethodSymbolImpl.SelectorPartSymbolImpl>(kryo, OCMethodSymbolImpl.SelectorPartSymbolImpl.class) {
            protected OCMethodSymbolImpl.SelectorPartSymbolImpl create(final Kryo kryo, final Input input, final Class<OCMethodSymbolImpl.SelectorPartSymbolImpl> clazz) {
                return new OCMethodSymbolImpl.SelectorPartSymbolImpl();
            }
        });
        kryo.register((Class)OCSymbolReference.SymbolFilter.class, (Serializer)new FieldSerializer(kryo, (Class)OCSymbolReference.SymbolFilter.class));
        kryo.register((Class)OCSymbolReference.TrueSymbolFilter.class, (Serializer)new FieldSerializer<OCSymbolReference.TrueSymbolFilter>(kryo, OCSymbolReference.TrueSymbolFilter.class) {
            protected OCSymbolReference.TrueSymbolFilter create(final Kryo kryo, final Input input, final Class<OCSymbolReference.TrueSymbolFilter> clazz) {
                return new OCSymbolReference.TrueSymbolFilter();
            }
            
            public OCSymbolReference.TrueSymbolFilter read(final Kryo kryo, final Input input, final Class<OCSymbolReference.TrueSymbolFilter> clazz) {
                return OCSymbolReference.SymbolFilter.NONE;
            }
        });
        kryo.register((Class)OCSymbolReference.SymbolKindFilter.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCSymbolReference.SymbolKindFilter.class));
        kryo.register((Class)OCSymbolReference.GlobalReference.class, (Serializer)new FieldSerializer<OCSymbolReference.GlobalReference>(kryo, OCSymbolReference.GlobalReference.class) {
            protected OCSymbolReference.GlobalReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.GlobalReference> clazz) {
                return new OCSymbolReference.GlobalReference();
            }
        });
        kryo.register((Class)OCSymbolReference.BaseClauseReference.class, (Serializer)new FieldSerializer<OCSymbolReference.BaseClauseReference>(kryo, OCSymbolReference.BaseClauseReference.class) {
            protected OCSymbolReference.BaseClauseReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.BaseClauseReference> clazz) {
                return new OCSymbolReference.BaseClauseReference();
            }
        });
        kryo.register((Class)OCSymbolReference.TemplateParamsReference.class, (Serializer)new FieldSerializer<OCSymbolReference.TemplateParamsReference>(kryo, OCSymbolReference.TemplateParamsReference.class) {
            protected OCSymbolReference.TemplateParamsReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.TemplateParamsReference> clazz) {
                return new OCSymbolReference.TemplateParamsReference();
            }
        });
        kryo.register((Class)OCSymbolReference.LambdaLocalReference.class, (Serializer)new FieldSerializer<OCSymbolReference.LambdaLocalReference>(kryo, OCSymbolReference.LambdaLocalReference.class) {
            protected OCSymbolReference.LambdaLocalReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.LambdaLocalReference> clazz) {
                return new OCSymbolReference.LambdaLocalReference();
            }
        });
        kryo.register((Class)OCSymbolReference.LocalReference.class, (Serializer)new NeverSerializer(kryo, OCSymbolReference.LocalReference.class));
        kryo.register((Class)PsiElement.class, (Serializer)new NeverSerializer(kryo, PsiElement.class));
        kryo.register((Class)OCExpressionTypeArgument.class, (Serializer)new FieldSerializer<OCExpressionTypeArgument>(kryo, OCExpressionTypeArgument.class) {
            protected OCExpressionTypeArgument create(final Kryo kryo, final Input input, final Class<OCExpressionTypeArgument> clazz) {
                return new OCExpressionTypeArgument();
            }
        });
        kryo.register((Class)OCCppReferenceType.class, (Serializer)new FieldSerializer<OCCppReferenceType>(kryo, OCCppReferenceType.class) {
            protected OCCppReferenceType create(final Kryo kryo, final Input input, final Class<OCCppReferenceType> clazz) {
                return new OCCppReferenceType();
            }
        });
        kryo.register((Class)OCEllipsisType.class, (Serializer)new SingletonSerializer<Object>(OCEllipsisType.instance()));
        kryo.register((Class)OCFunctionType.class, (Serializer)new FieldSerializer<OCFunctionType>(kryo, OCFunctionType.class) {
            protected OCFunctionType create(final Kryo kryo, final Input input, final Class<OCFunctionType> clazz) {
                return new OCFunctionType();
            }
        });
        kryo.register((Class)OCMagicType.class, (Serializer)new FieldSerializer<OCMagicType>(kryo, OCMagicType.class) {
            protected OCMagicType create(final Kryo kryo, final Input input, final Class<OCMagicType> clazz) {
                return new OCMagicType();
            }
        });
        kryo.register((Class)OCUnknownType.class, (Serializer)new SingletonSerializer<Object>(OCUnknownType.INSTANCE));
        kryo.register((Class)OCIntType.class, (Serializer)new FieldSerializer<OCIntType>(kryo, OCIntType.class) {
            protected OCIntType create(final Kryo kryo, final Input input, final Class<OCIntType> clazz) {
                return new OCIntType();
            }
        });
        kryo.register((Class)CTypeId.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)CTypeId.class));
        kryo.register((Class)OCRealType.class, (Serializer)new FieldSerializer<OCRealType>(kryo, OCRealType.class) {
            protected OCRealType create(final Kryo kryo, final Input input, final Class<OCRealType> clazz) {
                return new OCRealType();
            }
        });
        kryo.register((Class)OCObjectType.class, (Serializer)new FieldSerializer<OCObjectType>(kryo, OCObjectType.class) {
            protected OCObjectType create(final Kryo kryo, final Input input, final Class<OCObjectType> clazz) {
                return new OCObjectType();
            }
        });
        kryo.register((Class)OCIdType.class, (Serializer)new FieldSerializer<OCIdType>(kryo, OCIdType.class) {
            protected OCIdType create(final Kryo kryo, final Input input, final Class<OCIdType> clazz) {
                return new OCIdType();
            }
        });
        kryo.register((Class)OCIdType.IDInterfaceSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCIdType.IDInterfaceSymbol>(OCIdType.IDInterfaceSymbol.class) {
            @Override
            public OCIdType.IDInterfaceSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCIdType.IDInterfaceSymbol();
            }
        });
        kryo.register((Class)OCPointerType.class, (Serializer)new FieldSerializer<OCPointerType>(kryo, OCPointerType.class) {
            protected OCPointerType create(final Kryo kryo, final Input input, final Class<OCPointerType> clazz) {
                return new OCPointerType();
            }
        });
        kryo.register((Class)OCBlockPointerType.class, (Serializer)new FieldSerializer<OCBlockPointerType>(kryo, OCBlockPointerType.class) {
            protected OCBlockPointerType create(final Kryo kryo, final Input input, final Class<OCBlockPointerType> clazz) {
                return new OCBlockPointerType();
            }
        });
        kryo.register((Class)OCReferenceType.class, (Serializer)new FieldSerializer<OCReferenceType>(kryo, OCReferenceType.class) {
            protected OCReferenceType create(final Kryo kryo, final Input input, final Class<OCReferenceType> clazz) {
                return new OCReferenceType();
            }
        });
        kryo.register((Class)OCStructType.class, (Serializer)new FieldSerializer<OCStructType>(kryo, OCStructType.class) {
            protected OCStructType create(final Kryo kryo, final Input input, final Class<OCStructType> clazz) {
                return new OCStructType();
            }
        });
        kryo.register((Class)OCVoidType.class, (Serializer)new Serializer<OCVoidType>() {
            public void write(final Kryo kryo, final Output output, final OCVoidType ocVoidType) {
                output.writeBoolean(ocVoidType.isConst());
                output.writeBoolean(ocVoidType.isVolatile());
            }
            
            public OCVoidType read(final Kryo kryo, final Input input, final Class<OCVoidType> clazz) {
                return OCVoidType.instance(input.readBoolean(), input.readBoolean());
            }
        });
        kryo.register((Class)OCAutoType.class, (Serializer)new FieldSerializer<OCAutoType>(kryo, OCAutoType.class) {
            public OCAutoType create(final Kryo kryo, final Input input, final Class<OCAutoType> clazz) {
                return new OCAutoType();
            }
        });
        kryo.register((Class)OCArrayType.class, (Serializer)new FieldSerializer<OCArrayType>(kryo, OCArrayType.class) {
            protected OCArrayType create(final Kryo kryo, final Input input, final Class<OCArrayType> clazz) {
                return new OCArrayType();
            }
        });
        kryo.register((Class)OCExpansionPackType.class, (Serializer)new FieldSerializer<OCExpansionPackType>(kryo, OCExpansionPackType.class) {
            protected OCExpansionPackType create(final Kryo kryo, final Input input, final Class<OCExpansionPackType> clazz) {
                return new OCExpansionPackType();
            }
        });
        kryo.register((Class)OCVariadicType.class, (Serializer)new FieldSerializer<OCVariadicType>(kryo, OCVariadicType.class) {
            protected OCVariadicType create(final Kryo kryo, final Input input, final Class<OCVariadicType> clazz) {
                return new OCVariadicType();
            }
        });
        kryo.register((Class)OCVariadicPackExpressionSymbol.class, (Serializer)new ProjectAndFileOwnerSerializer<OCVariadicPackExpressionSymbol>(OCVariadicPackExpressionSymbol.class) {
            @Override
            public OCVariadicPackExpressionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCVariadicPackExpressionSymbol();
            }
        });
        kryo.register((Class)OCSimpleTypeSubstitution.class, (Serializer)new FieldSerializer<OCSimpleTypeSubstitution>(kryo, OCSimpleTypeSubstitution.class) {
            protected OCSimpleTypeSubstitution create(final Kryo kryo, final Input input, final Class<OCSimpleTypeSubstitution> clazz) {
                return new OCSimpleTypeSubstitution();
            }
            
            public OCSimpleTypeSubstitution read(final Kryo kryo, final Input input, final Class<OCSimpleTypeSubstitution> clazz) {
                if (input.readBoolean()) {
                    return OCTypeSubstitution.ID;
                }
                return (OCSimpleTypeSubstitution)super.read(kryo, input, (Class)clazz);
            }
            
            public void write(final Kryo kryo, final Output output, final OCSimpleTypeSubstitution ocSimpleTypeSubstitution) {
                final boolean b = ocSimpleTypeSubstitution == OCTypeSubstitution.ID;
                output.writeBoolean(b);
                if (!b) {
                    super.write(kryo, output, (Object)ocSimpleTypeSubstitution);
                }
            }
        });
        kryo.register((Class)ARCAttribute.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)ARCAttribute.class));
        kryo.register((Class)OCSymbolKind.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCSymbolKind.class));
        kryo.register((Class)OCNullability.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCNullability.class));
        kryo.register((Class)OCIncludeSymbol.class, (Serializer)new OCIncludeSerializer(OCIncludeSymbol.class) {
            @Override
            public OCIncludeSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCIncludeSymbol();
            }
        });
        kryo.register((Class)OCIncludeSymbol.IncludePath.class, (Serializer)new FieldSerializer<OCIncludeSymbol.IncludePath>(kryo, OCIncludeSymbol.IncludePath.class) {
            protected OCIncludeSymbol.IncludePath create(final Kryo kryo, final Input input, final Class<OCIncludeSymbol.IncludePath> clazz) {
                return new OCIncludeSymbol.IncludePath();
            }
        });
        kryo.register((Class)OCIncludeSymbol.IncludePath.EMPTY.getClass(), (Serializer)new SingletonSerializer<Object>(OCIncludeSymbol.IncludePath.EMPTY));
        kryo.register((Class)EnumMap.class, (Serializer)new OCJavaSerializer());
        kryo.register((Class)OCPropertySymbol.PropertyAttribute.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCPropertySymbol.PropertyAttribute.class));
        kryo.register((Class)OCVisibility.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCVisibility.class));
        kryo.register((Class)TextRange.class, (Serializer)new Serializer<TextRange>() {
            public void write(final Kryo kryo, final Output output, final TextRange textRange) {
                output.writeInt(textRange.getStartOffset(), true);
                output.writeInt(textRange.getEndOffset(), true);
            }
            
            public TextRange read(final Kryo kryo, final Input input, final Class<TextRange> clazz) {
                return new TextRange(input.readInt(true), input.readInt(true));
            }
        });
        kryo.register((Class)OCNumber.class, (Serializer)new Serializer<OCNumber>() {
            public void write(final Kryo kryo, final Output output, final OCNumber ocNumber) {
                output.writeByte(ocNumber.getSizeInBytes());
                output.writeBoolean(ocNumber.isSigned());
                final byte[] byteArray = ocNumber.toByteArray();
                output.writeByte(byteArray.length);
                if (byteArray.length > 0) {
                    output.write(byteArray);
                }
            }
            
            public OCNumber read(final Kryo kryo, final Input input, final Class<OCNumber> clazz) {
                final byte byte1 = input.readByte();
                final boolean boolean1 = input.readBoolean();
                final byte[] array = new byte[input.readByte()];
                input.readBytes(array);
                return new OCNumber(array, byte1, boolean1);
            }
        });
        kryo.register((Class)FileSymbolTablesCache.MetaData.class, (Serializer)new FieldSerializer<FileSymbolTablesCache.MetaData>(kryo, FileSymbolTablesCache.MetaData.class) {
            protected FileSymbolTablesCache.MetaData create(final Kryo kryo, final Input input, final Class<FileSymbolTablesCache.MetaData> clazz) {
                return new FileSymbolTablesCache.MetaData();
            }
        });
        kryo.register((Class)OCGenericParameterSymbol.Covariance.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)OCGenericParameterSymbol.Covariance.class));
        kryo.register((Class)OCGenericParameterSymbolImpl.class, (Serializer)new ProjectAndFileOwnerSerializer<OCGenericParameterSymbolImpl>(OCGenericParameterSymbolImpl.class) {
            @Override
            public OCGenericParameterSymbolImpl createInstance(final Kryo kryo, final Input input, final Class clazz) {
                return new OCGenericParameterSymbolImpl();
            }
        });
        kryo.register((Class)OCImmutableList.class, (Serializer)new FieldSerializer<OCImmutableList>(kryo, OCImmutableList.class) {
            public void write(final Kryo kryo, final Output output, final OCImmutableList list) {
                final boolean b = list.size() > 0;
                output.writeBoolean(b);
                if (b) {
                    super.write(kryo, output, (Object)list);
                }
            }
            
            public OCImmutableList read(final Kryo kryo, final Input input, final Class<OCImmutableList> clazz) {
                return (OCImmutableList)(input.readBoolean() ? super.read(kryo, input, (Class)clazz) : OCImmutableList.emptyList());
            }
            
            protected OCImmutableList create(final Kryo kryo, final Input input, final Class<OCImmutableList> clazz) {
                return new OCImmutableList((Collection<T>)Collections.emptyList());
            }
        });
        this.a();
    }
    
    private void a() {
        final SerializerProvider[] array = (SerializerProvider[])SerializerProvider.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].registerSerializers(this);
        }
    }
    
    @Nullable
    public FileSymbolTablesPack readSymbolTables(@NotNull final DataInputStream dataInputStream, @NotNull final VirtualFile myCurrentFile) {
        try {
            if (dataInputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dis", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "readSymbolTables"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        try {
            if (myCurrentFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "readSymbolTables"));
            }
        }
        catch (Exception ex2) {
            throw b(ex2);
        }
        this.myCurrentFile = myCurrentFile;
        final FastInput fastInput = new FastInput((InputStream)dataInputStream, 65536);
        try {
            return (FileSymbolTablesPack)this.getKryo().readObject((Input)fastInput, (Class)FileSymbolTablesPack.class);
        }
        catch (Exception ex3) {
            this.LOG.error((Throwable)ex3);
            return null;
        }
        finally {
            StreamUtil.closeStream((Closeable)fastInput);
        }
    }
    
    public void writeSymbolTables(@NotNull final DataOutputStream dataOutputStream, @NotNull final FileSymbolTablesPack fileSymbolTablesPack) {
        try {
            if (dataOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dos", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "writeSymbolTables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (fileSymbolTablesPack == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pack", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "writeSymbolTables"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.a(dataOutputStream, fileSymbolTablesPack);
    }
    
    private void a(final DataOutputStream dataOutputStream, final Object o) {
        final FastOutput fastOutput = new FastOutput((OutputStream)dataOutputStream, 65536);
        try {
            this.getKryo().writeObject((Output)fastOutput, o);
            ((Output)fastOutput).flush();
        }
        catch (Exception ex) {
            this.LOG.error((Throwable)ex);
        }
        finally {
            StreamUtil.closeStream((Closeable)fastOutput);
        }
    }
    
    @Nullable
    public FileSymbolTablesCache.MetaData readMetaData(@NotNull final DataInputStream dataInputStream) {
        try {
            if (dataInputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dis", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "readMetaData"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        final FastInput fastInput = new FastInput((InputStream)dataInputStream, 65536);
        try {
            return (FileSymbolTablesCache.MetaData)this.getKryo().readObject((Input)fastInput, (Class)FileSymbolTablesCache.MetaData.class);
        }
        catch (Exception ex2) {
            this.LOG.error((Throwable)ex2);
            return null;
        }
        finally {
            StreamUtil.closeStream((Closeable)fastInput);
        }
    }
    
    public void writeMetaData(@NotNull final DataOutputStream dataOutputStream, @NotNull final FileSymbolTablesCache.MetaData metaData) {
        try {
            if (dataOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dos", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "writeMetaData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (metaData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "metaData", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer", "writeMetaData"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.a(dataOutputStream, metaData);
    }
    
    public static Map<String, OCElementType> getNameToElementTypeMap(final TokenSet set) {
        final HashMap<String, OCElementType> hashMap = new HashMap<String, OCElementType>();
        for (final IElementType elementType : set.getTypes()) {
            hashMap.put(elementType.toString(), (OCElementType)elementType);
        }
        Label_0081: {
            try {
                if (FileSymbolTableSerializer.$assertionsDisabled) {
                    return hashMap;
                }
                final HashMap<String, OCElementType> hashMap2 = hashMap;
                final int n = hashMap2.size();
                final TokenSet set2 = set;
                final IElementType[] array = set2.getTypes();
                final int n2 = array.length;
                if (n != n2) {
                    break Label_0081;
                }
                return hashMap;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final HashMap<String, OCElementType> hashMap2 = hashMap;
                final int n = hashMap2.size();
                final TokenSet set2 = set;
                final IElementType[] array = set2.getTypes();
                final int n2 = array.length;
                if (n != n2) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return hashMap;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!FileSymbolTableSerializer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public abstract class ProjectAndFileOwnerSerializer<T extends ProjectAndVirtualFileOwner> extends FieldSerializer<T>
    {
        public ProjectAndFileOwnerSerializer(final Class<T> clazz) {
            super(FileSymbolTableSerializer.this.getKryo(), (Class)clazz);
        }
        
        public void write(final Kryo kryo, final Output output, final T t) {
            output.writeBoolean(t.getProject() != null);
            output.writeBoolean(t.getContainingFile() != null);
            super.write(kryo, output, (Object)t);
        }
        
        public final T create(final Kryo kryo, final Input input, final Class clazz) {
            final boolean boolean1 = input.readBoolean();
            final boolean boolean2 = input.readBoolean();
            final ProjectAndVirtualFileOwner instance = this.createInstance(kryo, input, clazz);
            instance.init(boolean1 ? FileSymbolTableSerializer.this.myProject : null, boolean2 ? FileSymbolTableSerializer.this.myCurrentFile : null);
            return (T)instance;
        }
        
        public abstract T createInstance(final Kryo p0, final Input p1, final Class p2);
    }
    
    public abstract class OCIncludeSerializer<T extends OCIncludeSymbol> extends ProjectAndFileOwnerSerializer<T>
    {
        public OCIncludeSerializer(final Class<T> clazz) {
            super(clazz);
        }
        
        @Override
        public abstract T createInstance(final Kryo p0, final Input p1, final Class p2);
        
        @Override
        public void write(final Kryo kryo, final Output output, final T t) {
            super.write(kryo, output, t);
            final VirtualFile targetFile = t.getTargetFile();
            int id;
            String s;
            if (targetFile != null && targetFile.isValid()) {
                if (targetFile instanceof VirtualFileWithId) {
                    id = ((VirtualFileWithId)targetFile).getId();
                    s = targetFile.getPath();
                }
                else {
                    id = -1;
                    s = CustomHeaderProvider.provideSerializationPathForFile(targetFile);
                    if (s == null) {
                        FileSymbolTableSerializer.this.LOG.error("only LocalVirtualFiles are supported: " + targetFile.getClass() + ": " + targetFile);
                    }
                }
            }
            else {
                id = -1;
                s = null;
            }
            output.writeInt(id, true);
            output.writeString(s);
        }
        
        public T read(final Kryo kryo, final Input input, final Class<T> clazz) {
            final OCIncludeSymbol ocIncludeSymbol = (OCIncludeSymbol)super.read(kryo, input, (Class)clazz);
            final int int1 = input.readInt(true);
            final String string = input.readString();
            VirtualFile virtualFile = null;
            if (int1 != -1) {
                virtualFile = PersistentFS.getInstance().findFileById(int1);
            }
            if (string != null && (virtualFile == null || !FileUtil.pathsEqual(string, virtualFile.getPath()))) {
                virtualFile = LocalFileSystem.getInstance().findFileByPath(string);
                if (virtualFile == null) {
                    virtualFile = CustomHeaderProvider.getCustomHeaderFile(string, FileSymbolTableSerializer.this.myProject, FileSymbolTableSerializer.this.myCurrentFile);
                }
            }
            ocIncludeSymbol.updateTargetFile(virtualFile);
            return (T)ocIncludeSymbol;
        }
    }
}
