// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import java.util.Collections;
import java.util.Collection;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ByteString;
import java.util.List;
import com.google.protobuf.Descriptors;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class ModulesLoaded_Broadcast extends GeneratedMessage implements ModulesLoaded_BroadcastOrBuilder
{
    private static final ModulesLoaded_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ModulesLoaded_Broadcast> PARSER;
    public static final int MODULES_FIELD_NUMBER = 1;
    private LazyStringList modules_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ModulesLoaded_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ModulesLoaded_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ModulesLoaded_Broadcast getDefaultInstance() {
        return ModulesLoaded_Broadcast.defaultInstance;
    }
    
    public ModulesLoaded_Broadcast getDefaultInstanceForType() {
        return ModulesLoaded_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ModulesLoaded_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.a();
        int n = 0;
        final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
        try {
            int i = 0;
            while (i == 0) {
                final int tag = codedInputStream.readTag();
                switch (tag) {
                    case 0: {
                        i = 1;
                        continue;
                    }
                    default: {
                        if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                            i = 1;
                            continue;
                        }
                        continue;
                    }
                    case 10: {
                        if ((n & 0x1) != 0x1) {
                            this.modules_ = (LazyStringList)new LazyStringArrayList();
                            n |= 0x1;
                        }
                        this.modules_.add(codedInputStream.readBytes());
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex) {
            throw ex.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex2) {
            throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            try {
                if ((n & 0x1) == 0x1) {
                    this.modules_ = (LazyStringList)new UnmodifiableLazyStringList(this.modules_);
                }
            }
            catch (InvalidProtocolBufferException ex3) {
                throw a((IOException)ex3);
            }
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$4700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$4800().ensureFieldAccessorsInitialized((Class)ModulesLoaded_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<ModulesLoaded_Broadcast> getParserForType() {
        return ModulesLoaded_Broadcast.PARSER;
    }
    
    public List<String> getModulesList() {
        return (List<String>)this.modules_;
    }
    
    public int getModulesCount() {
        return this.modules_.size();
    }
    
    public String getModules(final int n) {
        return (String)this.modules_.get(n);
    }
    
    public ByteString getModulesBytes(final int n) {
        return this.modules_.getByteString(n);
    }
    
    private void a() {
        this.modules_ = LazyStringArrayList.EMPTY;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        this.getSerializedSize();
        int i = 0;
        try {
            while (i < this.modules_.size()) {
                codedOutputStream.writeBytes(1, this.modules_.getByteString(i));
                ++i;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        final int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.modules_.size(); ++i) {
            n2 += CodedOutputStream.computeBytesSizeNoTag(this.modules_.getByteString(i));
        }
        return this.memoizedSerializedSize = n + n2 + 1 * this.getModulesList().size() + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(array);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ModulesLoaded_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ModulesLoaded_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static ModulesLoaded_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ModulesLoaded_Broadcast modulesLoaded_Broadcast) {
        return newBuilder().mergeFrom(modulesLoaded_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ModulesLoaded_Broadcast.PARSER = (Parser<ModulesLoaded_Broadcast>)new AbstractParser<ModulesLoaded_Broadcast>() {
            public ModulesLoaded_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ModulesLoaded_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ModulesLoaded_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ModulesLoaded_BroadcastOrBuilder
    {
        private int bitField0_;
        private LazyStringList modules_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$4700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$4800().ensureFieldAccessorsInitialized((Class)ModulesLoaded_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.modules_ = LazyStringArrayList.EMPTY;
            this.c();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.modules_ = LazyStringArrayList.EMPTY;
            this.c();
        }
        
        private void c() {
            if (ModulesLoaded_Broadcast.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.modules_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Broadcasts.access$4700();
        }
        
        public ModulesLoaded_Broadcast getDefaultInstanceForType() {
            return ModulesLoaded_Broadcast.getDefaultInstance();
        }
        
        public ModulesLoaded_Broadcast build() {
            final ModulesLoaded_Broadcast buildPartial = this.buildPartial();
            try {
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return buildPartial;
        }
        
        public ModulesLoaded_Broadcast buildPartial() {
            final ModulesLoaded_Broadcast modulesLoaded_Broadcast = new ModulesLoaded_Broadcast((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            try {
                if ((this.bitField0_ & 0x1) == 0x1) {
                    this.modules_ = (LazyStringList)new UnmodifiableLazyStringList(this.modules_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            modulesLoaded_Broadcast.modules_ = this.modules_;
            this.onBuilt();
            return modulesLoaded_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ModulesLoaded_Broadcast) {
                    return this.mergeFrom((ModulesLoaded_Broadcast)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ModulesLoaded_Broadcast modulesLoaded_Broadcast) {
            try {
                if (modulesLoaded_Broadcast == ModulesLoaded_Broadcast.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            Label_0092: {
                Label_0088: {
                    Label_0044: {
                        try {
                            if (modulesLoaded_Broadcast.modules_.isEmpty()) {
                                break Label_0092;
                            }
                            final Builder builder = this;
                            final LazyStringList list = builder.modules_;
                            final boolean b = list.isEmpty();
                            if (b) {
                                break Label_0044;
                            }
                            break Label_0044;
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final Builder builder = this;
                            final LazyStringList list = builder.modules_;
                            final boolean b = list.isEmpty();
                            if (b) {
                                this.modules_ = modulesLoaded_Broadcast.modules_;
                                this.bitField0_ &= 0xFFFFFFFE;
                                break Label_0088;
                            }
                        }
                        catch (NullPointerException ex3) {
                            throw b(ex3);
                        }
                    }
                    this.b();
                    this.modules_.addAll((Collection)modulesLoaded_Broadcast.modules_);
                }
                this.onChanged();
            }
            this.mergeUnknownFields(modulesLoaded_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ModulesLoaded_Broadcast modulesLoaded_Broadcast = null;
            try {
                modulesLoaded_Broadcast = (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                modulesLoaded_Broadcast = (ModulesLoaded_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (modulesLoaded_Broadcast != null) {
                        this.mergeFrom(modulesLoaded_Broadcast);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        private void b() {
            try {
                if ((this.bitField0_ & 0x1) != 0x1) {
                    this.modules_ = (LazyStringList)new LazyStringArrayList(this.modules_);
                    this.bitField0_ |= 0x1;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        public List<String> getModulesList() {
            return Collections.unmodifiableList((List<? extends String>)this.modules_);
        }
        
        public int getModulesCount() {
            return this.modules_.size();
        }
        
        public String getModules(final int n) {
            return (String)this.modules_.get(n);
        }
        
        public ByteString getModulesBytes(final int n) {
            return this.modules_.getByteString(n);
        }
        
        public Builder setModules(final int n, final String s) {
            try {
                if (s == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.b();
            this.modules_.set(n, (Object)s);
            this.onChanged();
            return this;
        }
        
        public Builder addModules(final String s) {
            try {
                if (s == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.b();
            this.modules_.add((Object)s);
            this.onChanged();
            return this;
        }
        
        public Builder addAllModules(final Iterable<String> iterable) {
            this.b();
            GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.modules_);
            this.onChanged();
            return this;
        }
        
        public Builder clearModules() {
            this.modules_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFFFE;
            this.onChanged();
            return this;
        }
        
        public Builder addModulesBytes(final ByteString byteString) {
            try {
                if (byteString == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.b();
            this.modules_.add(byteString);
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
