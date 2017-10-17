// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class AddWatchpoint_Req extends GeneratedMessage implements AddWatchpoint_ReqOrBuilder
{
    private static final AddWatchpoint_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<AddWatchpoint_Req> PARSER;
    private int bitField0_;
    public static final int VALUE_ID_FIELD_NUMBER = 2;
    private int valueId_;
    public static final int TO_RESOLVE_LOCATION_FIELD_NUMBER = 3;
    private int toResolveLocation_;
    public static final int READ_FIELD_NUMBER = 4;
    private int read_;
    public static final int WRITE_FIELD_NUMBER = 5;
    private int write_;
    public static final int CONDITION_FIELD_NUMBER = 6;
    private Object condition_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private AddWatchpoint_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private AddWatchpoint_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static AddWatchpoint_Req getDefaultInstance() {
        return AddWatchpoint_Req.defaultInstance;
    }
    
    public AddWatchpoint_Req getDefaultInstanceForType() {
        return AddWatchpoint_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private AddWatchpoint_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.a();
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
                    case 16: {
                        this.bitField0_ |= 0x1;
                        this.valueId_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.toResolveLocation_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.read_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x8;
                        this.write_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 50: {
                        this.bitField0_ |= 0x10;
                        this.condition_ = codedInputStream.readBytes();
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
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$31400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$31500().ensureFieldAccessorsInitialized((Class)AddWatchpoint_Req.class, (Class)Builder.class);
    }
    
    public Parser<AddWatchpoint_Req> getParserForType() {
        return AddWatchpoint_Req.PARSER;
    }
    
    public boolean hasValueId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getValueId() {
        return this.valueId_;
    }
    
    public boolean hasToResolveLocation() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getToResolveLocation() {
        return this.toResolveLocation_;
    }
    
    public boolean hasRead() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getRead() {
        return this.read_;
    }
    
    public boolean hasWrite() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public int getWrite() {
        return this.write_;
    }
    
    public boolean hasCondition() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public String getCondition() {
        final Object condition_ = this.condition_;
        if (condition_ instanceof String) {
            return (String)condition_;
        }
        final ByteString byteString = (ByteString)condition_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.condition_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getConditionBytes() {
        final Object condition_ = this.condition_;
        if (condition_ instanceof String) {
            return (ByteString)(this.condition_ = ByteString.copyFromUtf8((String)condition_));
        }
        return (ByteString)condition_;
    }
    
    private void a() {
        this.valueId_ = 0;
        this.toResolveLocation_ = 0;
        this.read_ = 0;
        this.write_ = 0;
        this.condition_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasValueId()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasToResolveLocation()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasRead()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasWrite()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        try {
            this.getSerializedSize();
            if ((this.bitField0_ & 0x1) == 0x1) {
                codedOutputStream.writeInt32(2, this.valueId_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.toResolveLocation_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(4, this.read_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeInt32(5, this.write_);
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeBytes(6, this.getConditionBytes());
            }
        }
        catch (IOException ex5) {
            throw a(ex5);
        }
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int n = 0;
        if ((this.bitField0_ & 0x1) == 0x1) {
            n += CodedOutputStream.computeInt32Size(2, this.valueId_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.toResolveLocation_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.read_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeInt32Size(5, this.write_);
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeBytesSize(6, this.getConditionBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static AddWatchpoint_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(byteString);
    }
    
    public static AddWatchpoint_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static AddWatchpoint_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(array);
    }
    
    public static AddWatchpoint_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static AddWatchpoint_Req parseFrom(final InputStream inputStream) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(inputStream);
    }
    
    public static AddWatchpoint_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static AddWatchpoint_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static AddWatchpoint_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static AddWatchpoint_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static AddWatchpoint_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final AddWatchpoint_Req addWatchpoint_Req) {
        return newBuilder().mergeFrom(addWatchpoint_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        AddWatchpoint_Req.PARSER = (Parser<AddWatchpoint_Req>)new AbstractParser<AddWatchpoint_Req>() {
            public AddWatchpoint_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AddWatchpoint_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new AddWatchpoint_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements AddWatchpoint_ReqOrBuilder
    {
        private int bitField0_;
        private int valueId_;
        private int toResolveLocation_;
        private int read_;
        private int write_;
        private Object condition_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$31400();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$31500().ensureFieldAccessorsInitialized((Class)AddWatchpoint_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.condition_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.condition_ = "";
            this.b();
        }
        
        private void b() {
            if (AddWatchpoint_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.valueId_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.toResolveLocation_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.read_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            this.write_ = 0;
            this.bitField0_ &= 0xFFFFFFF7;
            this.condition_ = "";
            this.bitField0_ &= 0xFFFFFFEF;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$31400();
        }
        
        public AddWatchpoint_Req getDefaultInstanceForType() {
            return AddWatchpoint_Req.getDefaultInstance();
        }
        
        public AddWatchpoint_Req build() {
            final AddWatchpoint_Req buildPartial = this.buildPartial();
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
        
        public AddWatchpoint_Req buildPartial() {
            final AddWatchpoint_Req addWatchpoint_Req = new AddWatchpoint_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            addWatchpoint_Req.valueId_ = this.valueId_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            addWatchpoint_Req.toResolveLocation_ = this.toResolveLocation_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            addWatchpoint_Req.read_ = this.read_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            addWatchpoint_Req.write_ = this.write_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            addWatchpoint_Req.condition_ = this.condition_;
            addWatchpoint_Req.bitField0_ = n;
            this.onBuilt();
            return addWatchpoint_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof AddWatchpoint_Req) {
                    return this.mergeFrom((AddWatchpoint_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final AddWatchpoint_Req addWatchpoint_Req) {
            try {
                if (addWatchpoint_Req == AddWatchpoint_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (addWatchpoint_Req.hasValueId()) {
                    this.setValueId(addWatchpoint_Req.getValueId());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (addWatchpoint_Req.hasToResolveLocation()) {
                    this.setToResolveLocation(addWatchpoint_Req.getToResolveLocation());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (addWatchpoint_Req.hasRead()) {
                    this.setRead(addWatchpoint_Req.getRead());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (addWatchpoint_Req.hasWrite()) {
                    this.setWrite(addWatchpoint_Req.getWrite());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (addWatchpoint_Req.hasCondition()) {
                    this.bitField0_ |= 0x10;
                    this.condition_ = addWatchpoint_Req.condition_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            this.mergeUnknownFields(addWatchpoint_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasValueId()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasToResolveLocation()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasRead()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (!this.hasWrite()) {
                    return false;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AddWatchpoint_Req addWatchpoint_Req = null;
            try {
                addWatchpoint_Req = (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                addWatchpoint_Req = (AddWatchpoint_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (addWatchpoint_Req != null) {
                        this.mergeFrom(addWatchpoint_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasValueId() {
            try {
                if ((this.bitField0_ & 0x1) == 0x1) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getValueId() {
            return this.valueId_;
        }
        
        public Builder setValueId(final int valueId_) {
            this.bitField0_ |= 0x1;
            this.valueId_ = valueId_;
            this.onChanged();
            return this;
        }
        
        public Builder clearValueId() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.valueId_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasToResolveLocation() {
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getToResolveLocation() {
            return this.toResolveLocation_;
        }
        
        public Builder setToResolveLocation(final int toResolveLocation_) {
            this.bitField0_ |= 0x2;
            this.toResolveLocation_ = toResolveLocation_;
            this.onChanged();
            return this;
        }
        
        public Builder clearToResolveLocation() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.toResolveLocation_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasRead() {
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getRead() {
            return this.read_;
        }
        
        public Builder setRead(final int read_) {
            this.bitField0_ |= 0x4;
            this.read_ = read_;
            this.onChanged();
            return this;
        }
        
        public Builder clearRead() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.read_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasWrite() {
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getWrite() {
            return this.write_;
        }
        
        public Builder setWrite(final int write_) {
            this.bitField0_ |= 0x8;
            this.write_ = write_;
            this.onChanged();
            return this;
        }
        
        public Builder clearWrite() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.write_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasCondition() {
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public String getCondition() {
            final Object condition_ = this.condition_;
            if (!(condition_ instanceof String)) {
                return (String)(this.condition_ = ((ByteString)condition_).toStringUtf8());
            }
            return (String)condition_;
        }
        
        public ByteString getConditionBytes() {
            final Object condition_ = this.condition_;
            if (condition_ instanceof String) {
                return (ByteString)(this.condition_ = ByteString.copyFromUtf8((String)condition_));
            }
            return (ByteString)condition_;
        }
        
        public Builder setCondition(final String condition_) {
            try {
                if (condition_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.condition_ = condition_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCondition() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.condition_ = AddWatchpoint_Req.getDefaultInstance().getCondition();
            this.onChanged();
            return this;
        }
        
        public Builder setConditionBytes(final ByteString condition_) {
            try {
                if (condition_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.condition_ = condition_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
