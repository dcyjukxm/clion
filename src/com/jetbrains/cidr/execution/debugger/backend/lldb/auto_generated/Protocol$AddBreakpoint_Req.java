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

public static final class AddBreakpoint_Req extends GeneratedMessage implements AddBreakpoint_ReqOrBuilder
{
    private static final AddBreakpoint_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<AddBreakpoint_Req> PARSER;
    private int bitField0_;
    public static final int FILE_FIELD_NUMBER = 2;
    private Object file_;
    public static final int LINE_FIELD_NUMBER = 3;
    private int line_;
    public static final int CONDITION_FIELD_NUMBER = 4;
    private Object condition_;
    public static final int SYMBOL_PATTERN_FIELD_NUMBER = 5;
    private Object symbolPattern_;
    public static final int MODULE_NAME_FIELD_NUMBER = 6;
    private Object moduleName_;
    public static final int THREAD_ID_FIELD_NUMBER = 7;
    private int threadId_;
    public static final int REGEXP_FIELD_NUMBER = 8;
    private boolean regexp_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private AddBreakpoint_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private AddBreakpoint_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static AddBreakpoint_Req getDefaultInstance() {
        return AddBreakpoint_Req.defaultInstance;
    }
    
    public AddBreakpoint_Req getDefaultInstanceForType() {
        return AddBreakpoint_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private AddBreakpoint_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18: {
                        this.bitField0_ |= 0x1;
                        this.file_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.line_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 34: {
                        this.bitField0_ |= 0x4;
                        this.condition_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 42: {
                        this.bitField0_ |= 0x8;
                        this.symbolPattern_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 50: {
                        this.bitField0_ |= 0x10;
                        this.moduleName_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 56: {
                        this.bitField0_ |= 0x20;
                        this.threadId_ = codedInputStream.readUInt32();
                        continue;
                    }
                    case 64: {
                        this.bitField0_ |= 0x40;
                        this.regexp_ = codedInputStream.readBool();
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
        return Protocol.access$19500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$19600().ensureFieldAccessorsInitialized((Class)AddBreakpoint_Req.class, (Class)Builder.class);
    }
    
    public Parser<AddBreakpoint_Req> getParserForType() {
        return AddBreakpoint_Req.PARSER;
    }
    
    public boolean hasFile() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getFile() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (String)file_;
        }
        final ByteString byteString = (ByteString)file_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.file_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getFileBytes() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
        }
        return (ByteString)file_;
    }
    
    public boolean hasLine() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getLine() {
        return this.line_;
    }
    
    public boolean hasCondition() {
        return (this.bitField0_ & 0x4) == 0x4;
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
    
    public boolean hasSymbolPattern() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public String getSymbolPattern() {
        final Object symbolPattern_ = this.symbolPattern_;
        if (symbolPattern_ instanceof String) {
            return (String)symbolPattern_;
        }
        final ByteString byteString = (ByteString)symbolPattern_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.symbolPattern_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getSymbolPatternBytes() {
        final Object symbolPattern_ = this.symbolPattern_;
        if (symbolPattern_ instanceof String) {
            return (ByteString)(this.symbolPattern_ = ByteString.copyFromUtf8((String)symbolPattern_));
        }
        return (ByteString)symbolPattern_;
    }
    
    public boolean hasModuleName() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public String getModuleName() {
        final Object moduleName_ = this.moduleName_;
        if (moduleName_ instanceof String) {
            return (String)moduleName_;
        }
        final ByteString byteString = (ByteString)moduleName_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.moduleName_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getModuleNameBytes() {
        final Object moduleName_ = this.moduleName_;
        if (moduleName_ instanceof String) {
            return (ByteString)(this.moduleName_ = ByteString.copyFromUtf8((String)moduleName_));
        }
        return (ByteString)moduleName_;
    }
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x20) == 0x20;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public boolean hasRegexp() {
        return (this.bitField0_ & 0x40) == 0x40;
    }
    
    public boolean getRegexp() {
        return this.regexp_;
    }
    
    private void a() {
        this.file_ = "";
        this.line_ = 0;
        this.condition_ = "";
        this.symbolPattern_ = "";
        this.moduleName_ = "";
        this.threadId_ = 0;
        this.regexp_ = false;
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
        try {
            this.getSerializedSize();
            if ((this.bitField0_ & 0x1) == 0x1) {
                codedOutputStream.writeBytes(2, this.getFileBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.line_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(4, this.getConditionBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBytes(5, this.getSymbolPatternBytes());
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeBytes(6, this.getModuleNameBytes());
            }
        }
        catch (IOException ex5) {
            throw a(ex5);
        }
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream.writeUInt32(7, this.threadId_);
            }
        }
        catch (IOException ex6) {
            throw a(ex6);
        }
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream.writeBool(8, this.regexp_);
            }
        }
        catch (IOException ex7) {
            throw a(ex7);
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
            n += CodedOutputStream.computeBytesSize(2, this.getFileBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.line_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(4, this.getConditionBytes());
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBytesSize(5, this.getSymbolPatternBytes());
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeBytesSize(6, this.getModuleNameBytes());
        }
        if ((this.bitField0_ & 0x20) == 0x20) {
            n += CodedOutputStream.computeUInt32Size(7, this.threadId_);
        }
        if ((this.bitField0_ & 0x40) == 0x40) {
            n += CodedOutputStream.computeBoolSize(8, this.regexp_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static AddBreakpoint_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(byteString);
    }
    
    public static AddBreakpoint_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static AddBreakpoint_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(array);
    }
    
    public static AddBreakpoint_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static AddBreakpoint_Req parseFrom(final InputStream inputStream) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(inputStream);
    }
    
    public static AddBreakpoint_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static AddBreakpoint_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static AddBreakpoint_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static AddBreakpoint_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static AddBreakpoint_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final AddBreakpoint_Req addBreakpoint_Req) {
        return newBuilder().mergeFrom(addBreakpoint_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        AddBreakpoint_Req.PARSER = (Parser<AddBreakpoint_Req>)new AbstractParser<AddBreakpoint_Req>() {
            public AddBreakpoint_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AddBreakpoint_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new AddBreakpoint_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements AddBreakpoint_ReqOrBuilder
    {
        private int bitField0_;
        private Object file_;
        private int line_;
        private Object condition_;
        private Object symbolPattern_;
        private Object moduleName_;
        private int threadId_;
        private boolean regexp_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$19500();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$19600().ensureFieldAccessorsInitialized((Class)AddBreakpoint_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.file_ = "";
            this.condition_ = "";
            this.symbolPattern_ = "";
            this.moduleName_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.file_ = "";
            this.condition_ = "";
            this.symbolPattern_ = "";
            this.moduleName_ = "";
            this.b();
        }
        
        private void b() {
            if (AddBreakpoint_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.file_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.line_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.condition_ = "";
            this.bitField0_ &= 0xFFFFFFFB;
            this.symbolPattern_ = "";
            this.bitField0_ &= 0xFFFFFFF7;
            this.moduleName_ = "";
            this.bitField0_ &= 0xFFFFFFEF;
            this.threadId_ = 0;
            this.bitField0_ &= 0xFFFFFFDF;
            this.regexp_ = false;
            this.bitField0_ &= 0xFFFFFFBF;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$19500();
        }
        
        public AddBreakpoint_Req getDefaultInstanceForType() {
            return AddBreakpoint_Req.getDefaultInstance();
        }
        
        public AddBreakpoint_Req build() {
            final AddBreakpoint_Req buildPartial = this.buildPartial();
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
        
        public AddBreakpoint_Req buildPartial() {
            final AddBreakpoint_Req addBreakpoint_Req = new AddBreakpoint_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            addBreakpoint_Req.file_ = this.file_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            addBreakpoint_Req.line_ = this.line_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            addBreakpoint_Req.condition_ = this.condition_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            addBreakpoint_Req.symbolPattern_ = this.symbolPattern_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            addBreakpoint_Req.moduleName_ = this.moduleName_;
            if ((bitField0_ & 0x20) == 0x20) {
                n |= 0x20;
            }
            addBreakpoint_Req.threadId_ = this.threadId_;
            if ((bitField0_ & 0x40) == 0x40) {
                n |= 0x40;
            }
            addBreakpoint_Req.regexp_ = this.regexp_;
            addBreakpoint_Req.bitField0_ = n;
            this.onBuilt();
            return addBreakpoint_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof AddBreakpoint_Req) {
                    return this.mergeFrom((AddBreakpoint_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final AddBreakpoint_Req addBreakpoint_Req) {
            try {
                if (addBreakpoint_Req == AddBreakpoint_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (addBreakpoint_Req.hasFile()) {
                    this.bitField0_ |= 0x1;
                    this.file_ = addBreakpoint_Req.file_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (addBreakpoint_Req.hasLine()) {
                    this.setLine(addBreakpoint_Req.getLine());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (addBreakpoint_Req.hasCondition()) {
                    this.bitField0_ |= 0x4;
                    this.condition_ = addBreakpoint_Req.condition_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (addBreakpoint_Req.hasSymbolPattern()) {
                    this.bitField0_ |= 0x8;
                    this.symbolPattern_ = addBreakpoint_Req.symbolPattern_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (addBreakpoint_Req.hasModuleName()) {
                    this.bitField0_ |= 0x10;
                    this.moduleName_ = addBreakpoint_Req.moduleName_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            try {
                if (addBreakpoint_Req.hasThreadId()) {
                    this.setThreadId(addBreakpoint_Req.getThreadId());
                }
            }
            catch (NullPointerException ex7) {
                throw b(ex7);
            }
            try {
                if (addBreakpoint_Req.hasRegexp()) {
                    this.setRegexp(addBreakpoint_Req.getRegexp());
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            this.mergeUnknownFields(addBreakpoint_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AddBreakpoint_Req addBreakpoint_Req = null;
            try {
                addBreakpoint_Req = (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                addBreakpoint_Req = (AddBreakpoint_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (addBreakpoint_Req != null) {
                        this.mergeFrom(addBreakpoint_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasFile() {
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
        
        public String getFile() {
            final Object file_ = this.file_;
            if (!(file_ instanceof String)) {
                return (String)(this.file_ = ((ByteString)file_).toStringUtf8());
            }
            return (String)file_;
        }
        
        public ByteString getFileBytes() {
            final Object file_ = this.file_;
            if (file_ instanceof String) {
                return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
            }
            return (ByteString)file_;
        }
        
        public Builder setFile(final String file_) {
            try {
                if (file_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.file_ = file_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFile() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.file_ = AddBreakpoint_Req.getDefaultInstance().getFile();
            this.onChanged();
            return this;
        }
        
        public Builder setFileBytes(final ByteString file_) {
            try {
                if (file_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.file_ = file_;
            this.onChanged();
            return this;
        }
        
        public boolean hasLine() {
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
        
        public int getLine() {
            return this.line_;
        }
        
        public Builder setLine(final int line_) {
            this.bitField0_ |= 0x2;
            this.line_ = line_;
            this.onChanged();
            return this;
        }
        
        public Builder clearLine() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.line_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasCondition() {
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
            this.bitField0_ |= 0x4;
            this.condition_ = condition_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCondition() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.condition_ = AddBreakpoint_Req.getDefaultInstance().getCondition();
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
            this.bitField0_ |= 0x4;
            this.condition_ = condition_;
            this.onChanged();
            return this;
        }
        
        public boolean hasSymbolPattern() {
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
        
        public String getSymbolPattern() {
            final Object symbolPattern_ = this.symbolPattern_;
            if (!(symbolPattern_ instanceof String)) {
                return (String)(this.symbolPattern_ = ((ByteString)symbolPattern_).toStringUtf8());
            }
            return (String)symbolPattern_;
        }
        
        public ByteString getSymbolPatternBytes() {
            final Object symbolPattern_ = this.symbolPattern_;
            if (symbolPattern_ instanceof String) {
                return (ByteString)(this.symbolPattern_ = ByteString.copyFromUtf8((String)symbolPattern_));
            }
            return (ByteString)symbolPattern_;
        }
        
        public Builder setSymbolPattern(final String symbolPattern_) {
            try {
                if (symbolPattern_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.symbolPattern_ = symbolPattern_;
            this.onChanged();
            return this;
        }
        
        public Builder clearSymbolPattern() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.symbolPattern_ = AddBreakpoint_Req.getDefaultInstance().getSymbolPattern();
            this.onChanged();
            return this;
        }
        
        public Builder setSymbolPatternBytes(final ByteString symbolPattern_) {
            try {
                if (symbolPattern_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.symbolPattern_ = symbolPattern_;
            this.onChanged();
            return this;
        }
        
        public boolean hasModuleName() {
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
        
        public String getModuleName() {
            final Object moduleName_ = this.moduleName_;
            if (!(moduleName_ instanceof String)) {
                return (String)(this.moduleName_ = ((ByteString)moduleName_).toStringUtf8());
            }
            return (String)moduleName_;
        }
        
        public ByteString getModuleNameBytes() {
            final Object moduleName_ = this.moduleName_;
            if (moduleName_ instanceof String) {
                return (ByteString)(this.moduleName_ = ByteString.copyFromUtf8((String)moduleName_));
            }
            return (ByteString)moduleName_;
        }
        
        public Builder setModuleName(final String moduleName_) {
            try {
                if (moduleName_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.moduleName_ = moduleName_;
            this.onChanged();
            return this;
        }
        
        public Builder clearModuleName() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.moduleName_ = AddBreakpoint_Req.getDefaultInstance().getModuleName();
            this.onChanged();
            return this;
        }
        
        public Builder setModuleNameBytes(final ByteString moduleName_) {
            try {
                if (moduleName_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.moduleName_ = moduleName_;
            this.onChanged();
            return this;
        }
        
        public boolean hasThreadId() {
            try {
                if ((this.bitField0_ & 0x20) == 0x20) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getThreadId() {
            return this.threadId_;
        }
        
        public Builder setThreadId(final int threadId_) {
            this.bitField0_ |= 0x20;
            this.threadId_ = threadId_;
            this.onChanged();
            return this;
        }
        
        public Builder clearThreadId() {
            this.bitField0_ &= 0xFFFFFFDF;
            this.threadId_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasRegexp() {
            try {
                if ((this.bitField0_ & 0x40) == 0x40) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public boolean getRegexp() {
            return this.regexp_;
        }
        
        public Builder setRegexp(final boolean regexp_) {
            this.bitField0_ |= 0x40;
            this.regexp_ = regexp_;
            this.onChanged();
            return this;
        }
        
        public Builder clearRegexp() {
            this.bitField0_ &= 0xFFFFFFBF;
            this.regexp_ = false;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
