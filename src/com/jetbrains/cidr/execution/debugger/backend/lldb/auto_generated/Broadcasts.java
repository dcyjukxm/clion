// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Descriptors;

public final class Broadcasts
{
    private static Descriptors.Descriptor internal_static_ProcessInterrupted_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ProcessInterrupted_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ProcessRunning_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ProcessRunning_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ProcessExited_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ProcessExited_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_TargetProcessOutput_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_TargetProcessOutput_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_LogMessage_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_LogMessage_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ModulesLoaded_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ModulesLoaded_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ChangePrompt_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ChangePrompt_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ReadyForCommands_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ReadyForCommands_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_CommandsInterpreter_Broadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_CommandsInterpreter_Broadcast_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_CompositeBroadcast_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_CompositeBroadcast_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;
    
    public static void registerAllExtensions(final ExtensionRegistry extensionRegistry) {
    }
    
    public static Descriptors.FileDescriptor getDescriptor() {
        return Broadcasts.descriptor;
    }
    
    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[] { "\n\u0010broadcasts.proto\u001a\u000bmodel.proto\"V\n\u001cProcessInterrupted_Broadcast\u0012\u001b\n\u0006thread\u0018\u0001 \u0002(\u000b2\u000b.LLDBThread\u0012\u0019\n\u0005frame\u0018\u0002 \u0002(\u000b2\n.LLDBFrame\"\u001a\n\u0018ProcessRunning_Broadcast\"F\n\u0017ProcessExited_Broadcast\u0012\u0011\n\texit_code\u0018\u0001 \u0002(\u0005\u0012\u0018\n\u0010exit_description\u0018\u0002 \u0001(\t\"O\n\u001dTargetProcessOutput_Broadcast\u0012\f\n\u0004text\u0018\u0001 \u0002(\t\u0012 \n\u000boutput_type\u0018\u0002 \u0002(\u000e2\u000b.OutputType\"F\n\u0014LogMessage_Broadcast\u0012\u001d\n\tverbosity\u0018\u0001 \u0002(\u000e2\n.Verbosity\u0012\u000f\n\u0007message\u0018\u0002 \u0002(\t\"*\n\u0017ModulesLoaded_Broadcast\u0012", "\u000f\n\u0007modules\u0018\u0001 \u0003(\t\"(\n\u0016ChangePrompt_Broadcast\u0012\u000e\n\u0006prompt\u0018\u0001 \u0002(\t\"+\n\u001aReadyForCommands_Broadcast\u0012\r\n\u0005ready\u0018\u0001 \u0002(\u0005\"0\n\u001dCommandsInterpreter_Broadcast\u0012\u000f\n\u0007message\u0018\u0001 \u0002(\t\"\u00f8\u0003\n\u0012CompositeBroadcast\u0012:\n\u0013process_interrupted\u0018\u0001 \u0001(\u000b2\u001d.ProcessInterrupted_Broadcast\u00122\n\u000fprocess_running\u0018\u0002 \u0001(\u000b2\u0019.ProcessRunning_Broadcast\u00120\n\u000eprocess_exited\u0018\u0003 \u0001(\u000b2\u0018.ProcessExited_Broadcast\u0012*\n\u000blog_message\u0018\u0004 \u0001(\u000b2\u0015.LogMessage_Broadcast\u0012.\n\rchange_prompt\u0018", "\u0005 \u0001(\u000b2\u0017.ChangePrompt_Broadcast\u00126\n\u0011ready_for_command\u0018\u0006 \u0001(\u000b2\u001b.ReadyForCommands_Broadcast\u0012;\n\u0013interpreter_message\u0018\u0007 \u0001(\u000b2\u001e.CommandsInterpreter_Broadcast\u0012=\n\u0015target_process_output\u0018\b \u0001(\u000b2\u001e.TargetProcessOutput_Broadcast\u00120\n\u000emodules_loaded\u0018\t \u0001(\u000b2\u0018.ModulesLoaded_Broadcast*M\n\tVerbosity\u0012\u0012\n\u000eVerbosityDebug\u0010\u0001\u0012\u0012\n\u000eVerbosityError\u0010\u0002\u0012\u0018\n\u0014VerbosityDisplayText\u0010\u0003BC\nAcom.jetbrains.cidr.execution.debugger.backend.lldb.auto_g", "enerated" }, new Descriptors.FileDescriptor[] { Model.getDescriptor() }, (Descriptors.FileDescriptor.InternalDescriptorAssigner)new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(final Descriptors.FileDescriptor fileDescriptor) {
                Broadcasts.descriptor = fileDescriptor;
                Broadcasts.internal_static_ProcessInterrupted_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(0);
                Broadcasts.internal_static_ProcessInterrupted_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ProcessInterrupted_Broadcast_descriptor, new String[] { "Thread", "Frame" });
                Broadcasts.internal_static_ProcessRunning_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(1);
                Broadcasts.internal_static_ProcessRunning_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ProcessRunning_Broadcast_descriptor, new String[0]);
                Broadcasts.internal_static_ProcessExited_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(2);
                Broadcasts.internal_static_ProcessExited_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ProcessExited_Broadcast_descriptor, new String[] { "ExitCode", "ExitDescription" });
                Broadcasts.internal_static_TargetProcessOutput_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(3);
                Broadcasts.internal_static_TargetProcessOutput_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_TargetProcessOutput_Broadcast_descriptor, new String[] { "Text", "OutputType" });
                Broadcasts.internal_static_LogMessage_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(4);
                Broadcasts.internal_static_LogMessage_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_LogMessage_Broadcast_descriptor, new String[] { "Verbosity", "Message" });
                Broadcasts.internal_static_ModulesLoaded_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(5);
                Broadcasts.internal_static_ModulesLoaded_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ModulesLoaded_Broadcast_descriptor, new String[] { "Modules" });
                Broadcasts.internal_static_ChangePrompt_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(6);
                Broadcasts.internal_static_ChangePrompt_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ChangePrompt_Broadcast_descriptor, new String[] { "Prompt" });
                Broadcasts.internal_static_ReadyForCommands_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(7);
                Broadcasts.internal_static_ReadyForCommands_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_ReadyForCommands_Broadcast_descriptor, new String[] { "Ready" });
                Broadcasts.internal_static_CommandsInterpreter_Broadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(8);
                Broadcasts.internal_static_CommandsInterpreter_Broadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_CommandsInterpreter_Broadcast_descriptor, new String[] { "Message" });
                Broadcasts.internal_static_CompositeBroadcast_descriptor = Broadcasts.getDescriptor().getMessageTypes().get(9);
                Broadcasts.internal_static_CompositeBroadcast_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Broadcasts.internal_static_CompositeBroadcast_descriptor, new String[] { "ProcessInterrupted", "ProcessRunning", "ProcessExited", "LogMessage", "ChangePrompt", "ReadyForCommand", "InterpreterMessage", "TargetProcessOutput", "ModulesLoaded" });
                return null;
            }
        });
    }
    
    public enum Verbosity implements ProtocolMessageEnum
    {
        VerbosityDebug(0, 1), 
        VerbosityError(1, 2), 
        VerbosityDisplayText(2, 3);
        
        public static final int VerbosityDebug_VALUE = 1;
        public static final int VerbosityError_VALUE = 2;
        public static final int VerbosityDisplayText_VALUE = 3;
        private static Internal.EnumLiteMap<Verbosity> internalValueMap;
        private static final Verbosity[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static Verbosity valueOf(final int n) {
            try {
                switch (n) {
                    case 1: {
                        return Verbosity.VerbosityDebug;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        return Verbosity.VerbosityDisplayText;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return Verbosity.VerbosityError;
        }
        
        public static Internal.EnumLiteMap<Verbosity> internalGetValueMap() {
            return Verbosity.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Broadcasts.getDescriptor().getEnumTypes().get(0);
        }
        
        public static Verbosity valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return Verbosity.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private Verbosity(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            Verbosity.internalValueMap = (Internal.EnumLiteMap<Verbosity>)new Internal.EnumLiteMap<Verbosity>() {
                public Verbosity findValueByNumber(final int n) {
                    return Verbosity.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static final class ProcessInterrupted_Broadcast extends GeneratedMessage implements ProcessInterrupted_BroadcastOrBuilder
    {
        private static final ProcessInterrupted_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ProcessInterrupted_Broadcast> PARSER;
        private int bitField0_;
        public static final int THREAD_FIELD_NUMBER = 1;
        private Model.LLDBThread thread_;
        public static final int FRAME_FIELD_NUMBER = 2;
        private Model.LLDBFrame frame_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ProcessInterrupted_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ProcessInterrupted_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ProcessInterrupted_Broadcast getDefaultInstance() {
            return ProcessInterrupted_Broadcast.defaultInstance;
        }
        
        public ProcessInterrupted_Broadcast getDefaultInstanceForType() {
            return ProcessInterrupted_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ProcessInterrupted_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 10: {
                            Model.LLDBThread.Builder builder2 = null;
                            if ((this.bitField0_ & 0x1) == 0x1) {
                                builder2 = this.thread_.toBuilder();
                            }
                            try {
                                this.thread_ = (Model.LLDBThread)codedInputStream.readMessage((Parser)Model.LLDBThread.PARSER, extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.thread_);
                                    this.thread_ = builder2.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x1;
                            continue;
                        }
                        case 18: {
                            Model.LLDBFrame.Builder builder3 = null;
                            if ((this.bitField0_ & 0x2) == 0x2) {
                                builder3 = this.frame_.toBuilder();
                            }
                            try {
                                this.frame_ = (Model.LLDBFrame)codedInputStream.readMessage((Parser)Model.LLDBFrame.PARSER, extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.frame_);
                                    this.frame_ = builder3.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex2) {
                                throw a((IOException)ex2);
                            }
                            this.bitField0_ |= 0x2;
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex3) {
                throw ex3.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex4) {
                throw new InvalidProtocolBufferException(ex4.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.internal_static_ProcessInterrupted_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ProcessInterrupted_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessInterrupted_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<ProcessInterrupted_Broadcast> getParserForType() {
            return ProcessInterrupted_Broadcast.PARSER;
        }
        
        public boolean hasThread() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public Model.LLDBThread getThread() {
            return this.thread_;
        }
        
        public Model.LLDBThreadOrBuilder getThreadOrBuilder() {
            return this.thread_;
        }
        
        public boolean hasFrame() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public Model.LLDBFrame getFrame() {
            return this.frame_;
        }
        
        public Model.LLDBFrameOrBuilder getFrameOrBuilder() {
            return this.frame_;
        }
        
        private void a() {
            this.thread_ = Model.LLDBThread.getDefaultInstance();
            this.frame_ = Model.LLDBFrame.getDefaultInstance();
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasThread()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasFrame()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getThread().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getFrame().isInitialized()) {
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
                    codedOutputStream.writeMessage(1, (MessageLite)this.thread_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeMessage(2, (MessageLite)this.frame_);
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
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
                n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.thread_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.frame_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(array);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessInterrupted_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ProcessInterrupted_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static ProcessInterrupted_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ProcessInterrupted_Broadcast processInterrupted_Broadcast) {
            return newBuilder().mergeFrom(processInterrupted_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ProcessInterrupted_Broadcast.PARSER = (Parser<ProcessInterrupted_Broadcast>)new AbstractParser<ProcessInterrupted_Broadcast>() {
                public ProcessInterrupted_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ProcessInterrupted_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ProcessInterrupted_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessInterrupted_BroadcastOrBuilder
        {
            private int bitField0_;
            private Model.LLDBThread thread_;
            private SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> threadBuilder_;
            private Model.LLDBFrame frame_;
            private SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder> frameBuilder_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_ProcessInterrupted_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ProcessInterrupted_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessInterrupted_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.thread_ = Model.LLDBThread.getDefaultInstance();
                this.frame_ = Model.LLDBFrame.getDefaultInstance();
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.thread_ = Model.LLDBThread.getDefaultInstance();
                this.frame_ = Model.LLDBFrame.getDefaultInstance();
                this.a();
            }
            
            private void a() {
                try {
                    if (ProcessInterrupted_Broadcast.alwaysUseFieldBuilders) {
                        this.d();
                        this.c();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                Label_0074: {
                    Label_0034: {
                        try {
                            super.clear();
                            if (this.threadBuilder_ == null) {
                                this.thread_ = Model.LLDBThread.getDefaultInstance();
                                break Label_0034;
                            }
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        this.threadBuilder_.clear();
                        try {
                            this.bitField0_ &= 0xFFFFFFFE;
                            if (this.frameBuilder_ == null) {
                                this.frame_ = Model.LLDBFrame.getDefaultInstance();
                                break Label_0074;
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.frameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_ProcessInterrupted_Broadcast_descriptor;
            }
            
            public ProcessInterrupted_Broadcast getDefaultInstanceForType() {
                return ProcessInterrupted_Broadcast.getDefaultInstance();
            }
            
            public ProcessInterrupted_Broadcast build() {
                final ProcessInterrupted_Broadcast buildPartial = this.buildPartial();
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
            
            public ProcessInterrupted_Broadcast buildPartial() {
                final ProcessInterrupted_Broadcast processInterrupted_Broadcast = new ProcessInterrupted_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                Label_0066: {
                    try {
                        if (this.threadBuilder_ == null) {
                            processInterrupted_Broadcast.thread_ = this.thread_;
                            break Label_0066;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    processInterrupted_Broadcast.thread_ = (Model.LLDBThread)this.threadBuilder_.build();
                }
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                Label_0115: {
                    try {
                        if (this.frameBuilder_ == null) {
                            processInterrupted_Broadcast.frame_ = this.frame_;
                            break Label_0115;
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                    processInterrupted_Broadcast.frame_ = (Model.LLDBFrame)this.frameBuilder_.build();
                }
                processInterrupted_Broadcast.bitField0_ = n;
                this.onBuilt();
                return processInterrupted_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof ProcessInterrupted_Broadcast) {
                        return this.mergeFrom((ProcessInterrupted_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ProcessInterrupted_Broadcast processInterrupted_Broadcast) {
                try {
                    if (processInterrupted_Broadcast == ProcessInterrupted_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (processInterrupted_Broadcast.hasThread()) {
                        this.mergeThread(processInterrupted_Broadcast.getThread());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (processInterrupted_Broadcast.hasFrame()) {
                        this.mergeFrame(processInterrupted_Broadcast.getFrame());
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                this.mergeUnknownFields(processInterrupted_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasThread()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasFrame()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (!this.getThread().isInitialized()) {
                        return false;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (!this.getFrame().isInitialized()) {
                        return false;
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ProcessInterrupted_Broadcast processInterrupted_Broadcast = null;
                try {
                    processInterrupted_Broadcast = (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    processInterrupted_Broadcast = (ProcessInterrupted_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (processInterrupted_Broadcast != null) {
                            this.mergeFrom(processInterrupted_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasThread() {
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
            
            public Model.LLDBThread getThread() {
                try {
                    if (this.threadBuilder_ == null) {
                        return this.thread_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (Model.LLDBThread)this.threadBuilder_.getMessage();
            }
            
            public Builder setThread(final Model.LLDBThread lldbThread) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.threadBuilder_ != null) {
                                    break Label_0042;
                                }
                                final Model.LLDBThread lldbThread2 = lldbThread;
                                if (lldbThread2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final Model.LLDBThread lldbThread2 = lldbThread;
                                if (lldbThread2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.thread_ = lldbThread;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.threadBuilder_.setMessage((GeneratedMessage)lldbThread);
                }
                this.bitField0_ |= 0x1;
                return this;
            }
            
            public Builder setThread(final Model.LLDBThread.Builder builder) {
                Label_0038: {
                    try {
                        if (this.threadBuilder_ == null) {
                            this.thread_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.threadBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x1;
                return this;
            }
            
            public Builder mergeThread(final Model.LLDBThread p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.threadBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       78
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    11: iconst_1       
                //    12: iand           
                //    13: iconst_1       
                //    14: if_icmpne       66
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    23: athrow         
                //    24: aload_0        
                //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    31: if_acmpeq       66
                //    34: goto            41
                //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    40: athrow         
                //    41: aload_0        
                //    42: aload_0        
                //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder;
                //    49: aload_1        
                //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder;
                //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    59: goto            71
                //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    65: athrow         
                //    66: aload_0        
                //    67: aload_1        
                //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
                //    71: aload_0        
                //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.onChanged:()V
                //    75: goto            87
                //    78: aload_0        
                //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.threadBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    82: aload_1        
                //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    86: pop            
                //    87: aload_0        
                //    88: dup            
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    92: iconst_1       
                //    93: ior            
                //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    97: aload_0        
                //    98: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      17     20     24     Ljava/lang/NullPointerException;
                //  7      34     37     41     Ljava/lang/NullPointerException;
                //  24     62     62     66     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearThread() {
                Label_0033: {
                    try {
                        if (this.threadBuilder_ == null) {
                            this.thread_ = Model.LLDBThread.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.threadBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }
            
            public Model.LLDBThread.Builder getThreadBuilder() {
                this.bitField0_ |= 0x1;
                this.onChanged();
                return (Model.LLDBThread.Builder)this.d().getBuilder();
            }
            
            public Model.LLDBThreadOrBuilder getThreadOrBuilder() {
                try {
                    if (this.threadBuilder_ != null) {
                        return (Model.LLDBThreadOrBuilder)this.threadBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.thread_;
            }
            
            private SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> d() {
                try {
                    if (this.threadBuilder_ == null) {
                        this.threadBuilder_ = (SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.thread_, this.getParentForChildren(), this.isClean());
                        this.thread_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.threadBuilder_;
            }
            
            public boolean hasFrame() {
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
            
            public Model.LLDBFrame getFrame() {
                try {
                    if (this.frameBuilder_ == null) {
                        return this.frame_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (Model.LLDBFrame)this.frameBuilder_.getMessage();
            }
            
            public Builder setFrame(final Model.LLDBFrame lldbFrame) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.frameBuilder_ != null) {
                                    break Label_0042;
                                }
                                final Model.LLDBFrame lldbFrame2 = lldbFrame;
                                if (lldbFrame2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final Model.LLDBFrame lldbFrame2 = lldbFrame;
                                if (lldbFrame2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.frame_ = lldbFrame;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.frameBuilder_.setMessage((GeneratedMessage)lldbFrame);
                }
                this.bitField0_ |= 0x2;
                return this;
            }
            
            public Builder setFrame(final Model.LLDBFrame.Builder builder) {
                Label_0038: {
                    try {
                        if (this.frameBuilder_ == null) {
                            this.frame_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.frameBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x2;
                return this;
            }
            
            public Builder mergeFrame(final Model.LLDBFrame p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       78
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    11: iconst_2       
                //    12: iand           
                //    13: iconst_2       
                //    14: if_icmpne       66
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    23: athrow         
                //    24: aload_0        
                //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    31: if_acmpeq       66
                //    34: goto            41
                //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    40: athrow         
                //    41: aload_0        
                //    42: aload_0        
                //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder;
                //    49: aload_1        
                //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder;
                //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    59: goto            71
                //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    65: athrow         
                //    66: aload_0        
                //    67: aload_1        
                //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
                //    71: aload_0        
                //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.onChanged:()V
                //    75: goto            87
                //    78: aload_0        
                //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    82: aload_1        
                //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    86: pop            
                //    87: aload_0        
                //    88: dup            
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    92: iconst_2       
                //    93: ior            
                //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
                //    97: aload_0        
                //    98: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      17     20     24     Ljava/lang/NullPointerException;
                //  7      34     37     41     Ljava/lang/NullPointerException;
                //  24     62     62     66     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearFrame() {
                Label_0033: {
                    try {
                        if (this.frameBuilder_ == null) {
                            this.frame_ = Model.LLDBFrame.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.frameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Model.LLDBFrame.Builder getFrameBuilder() {
                this.bitField0_ |= 0x2;
                this.onChanged();
                return (Model.LLDBFrame.Builder)this.c().getBuilder();
            }
            
            public Model.LLDBFrameOrBuilder getFrameOrBuilder() {
                try {
                    if (this.frameBuilder_ != null) {
                        return (Model.LLDBFrameOrBuilder)this.frameBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.frame_;
            }
            
            private SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder> c() {
                try {
                    if (this.frameBuilder_ == null) {
                        this.frameBuilder_ = (SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.frame_, this.getParentForChildren(), this.isClean());
                        this.frame_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.frameBuilder_;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class ProcessRunning_Broadcast extends GeneratedMessage implements ProcessRunning_BroadcastOrBuilder
    {
        private static final ProcessRunning_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ProcessRunning_Broadcast> PARSER;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ProcessRunning_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ProcessRunning_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ProcessRunning_Broadcast getDefaultInstance() {
            return ProcessRunning_Broadcast.defaultInstance;
        }
        
        public ProcessRunning_Broadcast getDefaultInstanceForType() {
            return ProcessRunning_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ProcessRunning_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
            return Broadcasts.internal_static_ProcessRunning_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ProcessRunning_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessRunning_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<ProcessRunning_Broadcast> getParserForType() {
            return ProcessRunning_Broadcast.PARSER;
        }
        
        private void a() {
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
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            return this.memoizedSerializedSize = 0 + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ProcessRunning_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(array);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessRunning_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ProcessRunning_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static ProcessRunning_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ProcessRunning_Broadcast processRunning_Broadcast) {
            return newBuilder().mergeFrom(processRunning_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ProcessRunning_Broadcast.PARSER = (Parser<ProcessRunning_Broadcast>)new AbstractParser<ProcessRunning_Broadcast>() {
                public ProcessRunning_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ProcessRunning_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ProcessRunning_Broadcast(true)).a();
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessRunning_BroadcastOrBuilder
        {
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_ProcessRunning_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ProcessRunning_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessRunning_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.a();
            }
            
            private void a() {
                if (ProcessRunning_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_ProcessRunning_Broadcast_descriptor;
            }
            
            public ProcessRunning_Broadcast getDefaultInstanceForType() {
                return ProcessRunning_Broadcast.getDefaultInstance();
            }
            
            public ProcessRunning_Broadcast build() {
                final ProcessRunning_Broadcast buildPartial = this.buildPartial();
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
                return buildPartial;
            }
            
            public ProcessRunning_Broadcast buildPartial() {
                final ProcessRunning_Broadcast processRunning_Broadcast = new ProcessRunning_Broadcast((GeneratedMessage.Builder)this);
                this.onBuilt();
                return processRunning_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                if (message instanceof ProcessRunning_Broadcast) {
                    return this.mergeFrom((ProcessRunning_Broadcast)message);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ProcessRunning_Broadcast processRunning_Broadcast) {
                if (processRunning_Broadcast == ProcessRunning_Broadcast.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(processRunning_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ProcessRunning_Broadcast processRunning_Broadcast = null;
                try {
                    processRunning_Broadcast = (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    processRunning_Broadcast = (ProcessRunning_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (processRunning_Broadcast != null) {
                            this.mergeFrom(processRunning_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b(ex2);
                    }
                }
                return this;
            }
            
            private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
                return ex;
            }
        }
    }
    
    public static final class ProcessExited_Broadcast extends GeneratedMessage implements ProcessExited_BroadcastOrBuilder
    {
        private static final ProcessExited_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ProcessExited_Broadcast> PARSER;
        private int bitField0_;
        public static final int EXIT_CODE_FIELD_NUMBER = 1;
        private int exitCode_;
        public static final int EXIT_DESCRIPTION_FIELD_NUMBER = 2;
        private Object exitDescription_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ProcessExited_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ProcessExited_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ProcessExited_Broadcast getDefaultInstance() {
            return ProcessExited_Broadcast.defaultInstance;
        }
        
        public ProcessExited_Broadcast getDefaultInstanceForType() {
            return ProcessExited_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ProcessExited_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 8: {
                            this.bitField0_ |= 0x1;
                            this.exitCode_ = codedInputStream.readInt32();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.exitDescription_ = codedInputStream.readBytes();
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
            return Broadcasts.internal_static_ProcessExited_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ProcessExited_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessExited_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<ProcessExited_Broadcast> getParserForType() {
            return ProcessExited_Broadcast.PARSER;
        }
        
        public boolean hasExitCode() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getExitCode() {
            return this.exitCode_;
        }
        
        public boolean hasExitDescription() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getExitDescription() {
            final Object exitDescription_ = this.exitDescription_;
            if (exitDescription_ instanceof String) {
                return (String)exitDescription_;
            }
            final ByteString byteString = (ByteString)exitDescription_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.exitDescription_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getExitDescriptionBytes() {
            final Object exitDescription_ = this.exitDescription_;
            if (exitDescription_ instanceof String) {
                return (ByteString)(this.exitDescription_ = ByteString.copyFromUtf8((String)exitDescription_));
            }
            return (ByteString)exitDescription_;
        }
        
        private void a() {
            this.exitCode_ = 0;
            this.exitDescription_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasExitCode()) {
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
                    codedOutputStream.writeInt32(1, this.exitCode_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getExitDescriptionBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
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
                n += CodedOutputStream.computeInt32Size(1, this.exitCode_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getExitDescriptionBytes());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ProcessExited_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static ProcessExited_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ProcessExited_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(array);
        }
        
        public static ProcessExited_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ProcessExited_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static ProcessExited_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessExited_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ProcessExited_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ProcessExited_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static ProcessExited_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ProcessExited_Broadcast processExited_Broadcast) {
            return newBuilder().mergeFrom(processExited_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ProcessExited_Broadcast.PARSER = (Parser<ProcessExited_Broadcast>)new AbstractParser<ProcessExited_Broadcast>() {
                public ProcessExited_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ProcessExited_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ProcessExited_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessExited_BroadcastOrBuilder
        {
            private int bitField0_;
            private int exitCode_;
            private Object exitDescription_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_ProcessExited_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ProcessExited_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ProcessExited_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.exitDescription_ = "";
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.exitDescription_ = "";
                this.a();
            }
            
            private void a() {
                if (ProcessExited_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.exitCode_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                this.exitDescription_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_ProcessExited_Broadcast_descriptor;
            }
            
            public ProcessExited_Broadcast getDefaultInstanceForType() {
                return ProcessExited_Broadcast.getDefaultInstance();
            }
            
            public ProcessExited_Broadcast build() {
                final ProcessExited_Broadcast buildPartial = this.buildPartial();
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
            
            public ProcessExited_Broadcast buildPartial() {
                final ProcessExited_Broadcast processExited_Broadcast = new ProcessExited_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                processExited_Broadcast.exitCode_ = this.exitCode_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                processExited_Broadcast.exitDescription_ = this.exitDescription_;
                processExited_Broadcast.bitField0_ = n;
                this.onBuilt();
                return processExited_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof ProcessExited_Broadcast) {
                        return this.mergeFrom((ProcessExited_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ProcessExited_Broadcast processExited_Broadcast) {
                try {
                    if (processExited_Broadcast == ProcessExited_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (processExited_Broadcast.hasExitCode()) {
                        this.setExitCode(processExited_Broadcast.getExitCode());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (processExited_Broadcast.hasExitDescription()) {
                        this.bitField0_ |= 0x2;
                        this.exitDescription_ = processExited_Broadcast.exitDescription_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                this.mergeUnknownFields(processExited_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasExitCode()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ProcessExited_Broadcast processExited_Broadcast = null;
                try {
                    processExited_Broadcast = (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    processExited_Broadcast = (ProcessExited_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (processExited_Broadcast != null) {
                            this.mergeFrom(processExited_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasExitCode() {
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
            
            public int getExitCode() {
                return this.exitCode_;
            }
            
            public Builder setExitCode(final int exitCode_) {
                this.bitField0_ |= 0x1;
                this.exitCode_ = exitCode_;
                this.onChanged();
                return this;
            }
            
            public Builder clearExitCode() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.exitCode_ = 0;
                this.onChanged();
                return this;
            }
            
            public boolean hasExitDescription() {
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
            
            public String getExitDescription() {
                final Object exitDescription_ = this.exitDescription_;
                if (!(exitDescription_ instanceof String)) {
                    return (String)(this.exitDescription_ = ((ByteString)exitDescription_).toStringUtf8());
                }
                return (String)exitDescription_;
            }
            
            public ByteString getExitDescriptionBytes() {
                final Object exitDescription_ = this.exitDescription_;
                if (exitDescription_ instanceof String) {
                    return (ByteString)(this.exitDescription_ = ByteString.copyFromUtf8((String)exitDescription_));
                }
                return (ByteString)exitDescription_;
            }
            
            public Builder setExitDescription(final String exitDescription_) {
                try {
                    if (exitDescription_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.exitDescription_ = exitDescription_;
                this.onChanged();
                return this;
            }
            
            public Builder clearExitDescription() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.exitDescription_ = ProcessExited_Broadcast.getDefaultInstance().getExitDescription();
                this.onChanged();
                return this;
            }
            
            public Builder setExitDescriptionBytes(final ByteString exitDescription_) {
                try {
                    if (exitDescription_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.exitDescription_ = exitDescription_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class TargetProcessOutput_Broadcast extends GeneratedMessage implements TargetProcessOutput_BroadcastOrBuilder
    {
        private static final TargetProcessOutput_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<TargetProcessOutput_Broadcast> PARSER;
        private int bitField0_;
        public static final int TEXT_FIELD_NUMBER = 1;
        private Object text_;
        public static final int OUTPUT_TYPE_FIELD_NUMBER = 2;
        private Model.OutputType outputType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private TargetProcessOutput_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private TargetProcessOutput_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static TargetProcessOutput_Broadcast getDefaultInstance() {
            return TargetProcessOutput_Broadcast.defaultInstance;
        }
        
        public TargetProcessOutput_Broadcast getDefaultInstanceForType() {
            return TargetProcessOutput_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private TargetProcessOutput_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 10: {
                            this.bitField0_ |= 0x1;
                            this.text_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 16: {
                            final int enum1 = codedInputStream.readEnum();
                            final Model.OutputType value = Model.OutputType.valueOf(enum1);
                            try {
                                if (value == null) {
                                    builder.mergeVarintField(2, enum1);
                                    continue;
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x2;
                            this.outputType_ = value;
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.internal_static_TargetProcessOutput_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_TargetProcessOutput_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)TargetProcessOutput_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<TargetProcessOutput_Broadcast> getParserForType() {
            return TargetProcessOutput_Broadcast.PARSER;
        }
        
        public boolean hasText() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getText() {
            final Object text_ = this.text_;
            if (text_ instanceof String) {
                return (String)text_;
            }
            final ByteString byteString = (ByteString)text_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.text_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getTextBytes() {
            final Object text_ = this.text_;
            if (text_ instanceof String) {
                return (ByteString)(this.text_ = ByteString.copyFromUtf8((String)text_));
            }
            return (ByteString)text_;
        }
        
        public boolean hasOutputType() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public Model.OutputType getOutputType() {
            return this.outputType_;
        }
        
        private void a() {
            this.text_ = "";
            this.outputType_ = Model.OutputType.OutputTypeStdout;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasText()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasOutputType()) {
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
                    codedOutputStream.writeBytes(1, this.getTextBytes());
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeEnum(2, this.outputType_.getNumber());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
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
                n += CodedOutputStream.computeBytesSize(1, this.getTextBytes());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeEnumSize(2, this.outputType_.getNumber());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(array);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static TargetProcessOutput_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static TargetProcessOutput_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static TargetProcessOutput_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
            return newBuilder().mergeFrom(targetProcessOutput_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            TargetProcessOutput_Broadcast.PARSER = (Parser<TargetProcessOutput_Broadcast>)new AbstractParser<TargetProcessOutput_Broadcast>() {
                public TargetProcessOutput_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new TargetProcessOutput_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new TargetProcessOutput_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements TargetProcessOutput_BroadcastOrBuilder
        {
            private int bitField0_;
            private Object text_;
            private Model.OutputType outputType_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_TargetProcessOutput_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_TargetProcessOutput_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)TargetProcessOutput_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.text_ = "";
                this.outputType_ = Model.OutputType.OutputTypeStdout;
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.text_ = "";
                this.outputType_ = Model.OutputType.OutputTypeStdout;
                this.a();
            }
            
            private void a() {
                if (TargetProcessOutput_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.text_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.outputType_ = Model.OutputType.OutputTypeStdout;
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_TargetProcessOutput_Broadcast_descriptor;
            }
            
            public TargetProcessOutput_Broadcast getDefaultInstanceForType() {
                return TargetProcessOutput_Broadcast.getDefaultInstance();
            }
            
            public TargetProcessOutput_Broadcast build() {
                final TargetProcessOutput_Broadcast buildPartial = this.buildPartial();
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
            
            public TargetProcessOutput_Broadcast buildPartial() {
                final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = new TargetProcessOutput_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                targetProcessOutput_Broadcast.text_ = this.text_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                targetProcessOutput_Broadcast.outputType_ = this.outputType_;
                targetProcessOutput_Broadcast.bitField0_ = n;
                this.onBuilt();
                return targetProcessOutput_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof TargetProcessOutput_Broadcast) {
                        return this.mergeFrom((TargetProcessOutput_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
                try {
                    if (targetProcessOutput_Broadcast == TargetProcessOutput_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (targetProcessOutput_Broadcast.hasText()) {
                        this.bitField0_ |= 0x1;
                        this.text_ = targetProcessOutput_Broadcast.text_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (targetProcessOutput_Broadcast.hasOutputType()) {
                        this.setOutputType(targetProcessOutput_Broadcast.getOutputType());
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                this.mergeUnknownFields(targetProcessOutput_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasText()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasOutputType()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = null;
                try {
                    targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (targetProcessOutput_Broadcast != null) {
                            this.mergeFrom(targetProcessOutput_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasText() {
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
            
            public String getText() {
                final Object text_ = this.text_;
                if (!(text_ instanceof String)) {
                    return (String)(this.text_ = ((ByteString)text_).toStringUtf8());
                }
                return (String)text_;
            }
            
            public ByteString getTextBytes() {
                final Object text_ = this.text_;
                if (text_ instanceof String) {
                    return (ByteString)(this.text_ = ByteString.copyFromUtf8((String)text_));
                }
                return (ByteString)text_;
            }
            
            public Builder setText(final String text_) {
                try {
                    if (text_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.text_ = text_;
                this.onChanged();
                return this;
            }
            
            public Builder clearText() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.text_ = TargetProcessOutput_Broadcast.getDefaultInstance().getText();
                this.onChanged();
                return this;
            }
            
            public Builder setTextBytes(final ByteString text_) {
                try {
                    if (text_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.text_ = text_;
                this.onChanged();
                return this;
            }
            
            public boolean hasOutputType() {
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
            
            public Model.OutputType getOutputType() {
                return this.outputType_;
            }
            
            public Builder setOutputType(final Model.OutputType outputType_) {
                try {
                    if (outputType_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.outputType_ = outputType_;
                this.onChanged();
                return this;
            }
            
            public Builder clearOutputType() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.outputType_ = Model.OutputType.OutputTypeStdout;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class LogMessage_Broadcast extends GeneratedMessage implements LogMessage_BroadcastOrBuilder
    {
        private static final LogMessage_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<LogMessage_Broadcast> PARSER;
        private int bitField0_;
        public static final int VERBOSITY_FIELD_NUMBER = 1;
        private Verbosity verbosity_;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        private Object message_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private LogMessage_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private LogMessage_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static LogMessage_Broadcast getDefaultInstance() {
            return LogMessage_Broadcast.defaultInstance;
        }
        
        public LogMessage_Broadcast getDefaultInstanceForType() {
            return LogMessage_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private LogMessage_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 8: {
                            final int enum1 = codedInputStream.readEnum();
                            final Verbosity value = Verbosity.valueOf(enum1);
                            try {
                                if (value == null) {
                                    builder.mergeVarintField(1, enum1);
                                    continue;
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x1;
                            this.verbosity_ = value;
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.message_ = codedInputStream.readBytes();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.internal_static_LogMessage_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_LogMessage_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LogMessage_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<LogMessage_Broadcast> getParserForType() {
            return LogMessage_Broadcast.PARSER;
        }
        
        public boolean hasVerbosity() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public Verbosity getVerbosity() {
            return this.verbosity_;
        }
        
        public boolean hasMessage() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getMessage() {
            final Object message_ = this.message_;
            if (message_ instanceof String) {
                return (String)message_;
            }
            final ByteString byteString = (ByteString)message_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.message_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getMessageBytes() {
            final Object message_ = this.message_;
            if (message_ instanceof String) {
                return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
            }
            return (ByteString)message_;
        }
        
        private void a() {
            this.verbosity_ = Verbosity.VerbosityDebug;
            this.message_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasVerbosity()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasMessage()) {
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
                    codedOutputStream.writeEnum(1, this.verbosity_.getNumber());
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getMessageBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
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
                n += CodedOutputStream.computeEnumSize(1, this.verbosity_.getNumber());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getMessageBytes());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static LogMessage_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static LogMessage_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static LogMessage_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(array);
        }
        
        public static LogMessage_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static LogMessage_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static LogMessage_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static LogMessage_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static LogMessage_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static LogMessage_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static LogMessage_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final LogMessage_Broadcast logMessage_Broadcast) {
            return newBuilder().mergeFrom(logMessage_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            LogMessage_Broadcast.PARSER = (Parser<LogMessage_Broadcast>)new AbstractParser<LogMessage_Broadcast>() {
                public LogMessage_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new LogMessage_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new LogMessage_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LogMessage_BroadcastOrBuilder
        {
            private int bitField0_;
            private Verbosity verbosity_;
            private Object message_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_LogMessage_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_LogMessage_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LogMessage_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.verbosity_ = Verbosity.VerbosityDebug;
                this.message_ = "";
                this.b();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.verbosity_ = Verbosity.VerbosityDebug;
                this.message_ = "";
                this.b();
            }
            
            private void b() {
                if (LogMessage_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder a() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.verbosity_ = Verbosity.VerbosityDebug;
                this.bitField0_ &= 0xFFFFFFFE;
                this.message_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Builder clone() {
                return a().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_LogMessage_Broadcast_descriptor;
            }
            
            public LogMessage_Broadcast getDefaultInstanceForType() {
                return LogMessage_Broadcast.getDefaultInstance();
            }
            
            public LogMessage_Broadcast build() {
                final LogMessage_Broadcast buildPartial = this.buildPartial();
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
            
            public LogMessage_Broadcast buildPartial() {
                final LogMessage_Broadcast logMessage_Broadcast = new LogMessage_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                logMessage_Broadcast.verbosity_ = this.verbosity_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                logMessage_Broadcast.message_ = this.message_;
                logMessage_Broadcast.bitField0_ = n;
                this.onBuilt();
                return logMessage_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof LogMessage_Broadcast) {
                        return this.mergeFrom((LogMessage_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final LogMessage_Broadcast logMessage_Broadcast) {
                try {
                    if (logMessage_Broadcast == LogMessage_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (logMessage_Broadcast.hasVerbosity()) {
                        this.setVerbosity(logMessage_Broadcast.getVerbosity());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (logMessage_Broadcast.hasMessage()) {
                        this.bitField0_ |= 0x2;
                        this.message_ = logMessage_Broadcast.message_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                this.mergeUnknownFields(logMessage_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasVerbosity()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasMessage()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LogMessage_Broadcast logMessage_Broadcast = null;
                try {
                    logMessage_Broadcast = (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    logMessage_Broadcast = (LogMessage_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (logMessage_Broadcast != null) {
                            this.mergeFrom(logMessage_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasVerbosity() {
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
            
            public Verbosity getVerbosity() {
                return this.verbosity_;
            }
            
            public Builder setVerbosity(final Verbosity verbosity_) {
                try {
                    if (verbosity_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.verbosity_ = verbosity_;
                this.onChanged();
                return this;
            }
            
            public Builder clearVerbosity() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.verbosity_ = Verbosity.VerbosityDebug;
                this.onChanged();
                return this;
            }
            
            public boolean hasMessage() {
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
            
            public String getMessage() {
                final Object message_ = this.message_;
                if (!(message_ instanceof String)) {
                    return (String)(this.message_ = ((ByteString)message_).toStringUtf8());
                }
                return (String)message_;
            }
            
            public ByteString getMessageBytes() {
                final Object message_ = this.message_;
                if (message_ instanceof String) {
                    return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
                }
                return (ByteString)message_;
            }
            
            public Builder setMessage(final String message_) {
                try {
                    if (message_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.message_ = message_;
                this.onChanged();
                return this;
            }
            
            public Builder clearMessage() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.message_ = LogMessage_Broadcast.getDefaultInstance().getMessage();
                this.onChanged();
                return this;
            }
            
            public Builder setMessageBytes(final ByteString message_) {
                try {
                    if (message_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.message_ = message_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
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
            return Broadcasts.internal_static_ModulesLoaded_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ModulesLoaded_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ModulesLoaded_Broadcast.class, (Class)Builder.class);
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
                return Broadcasts.internal_static_ModulesLoaded_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ModulesLoaded_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ModulesLoaded_Broadcast.class, (Class)Builder.class);
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
                return Broadcasts.internal_static_ModulesLoaded_Broadcast_descriptor;
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
    
    public static final class ChangePrompt_Broadcast extends GeneratedMessage implements ChangePrompt_BroadcastOrBuilder
    {
        private static final ChangePrompt_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ChangePrompt_Broadcast> PARSER;
        private int bitField0_;
        public static final int PROMPT_FIELD_NUMBER = 1;
        private Object prompt_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ChangePrompt_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ChangePrompt_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ChangePrompt_Broadcast getDefaultInstance() {
            return ChangePrompt_Broadcast.defaultInstance;
        }
        
        public ChangePrompt_Broadcast getDefaultInstanceForType() {
            return ChangePrompt_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ChangePrompt_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 10: {
                            this.bitField0_ |= 0x1;
                            this.prompt_ = codedInputStream.readBytes();
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
            return Broadcasts.internal_static_ChangePrompt_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ChangePrompt_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ChangePrompt_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<ChangePrompt_Broadcast> getParserForType() {
            return ChangePrompt_Broadcast.PARSER;
        }
        
        public boolean hasPrompt() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getPrompt() {
            final Object prompt_ = this.prompt_;
            if (prompt_ instanceof String) {
                return (String)prompt_;
            }
            final ByteString byteString = (ByteString)prompt_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.prompt_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getPromptBytes() {
            final Object prompt_ = this.prompt_;
            if (prompt_ instanceof String) {
                return (ByteString)(this.prompt_ = ByteString.copyFromUtf8((String)prompt_));
            }
            return (ByteString)prompt_;
        }
        
        private void a() {
            this.prompt_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasPrompt()) {
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
                    codedOutputStream.writeBytes(1, this.getPromptBytes());
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
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeBytesSize(1, this.getPromptBytes());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ChangePrompt_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(array);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ChangePrompt_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ChangePrompt_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static ChangePrompt_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ChangePrompt_Broadcast changePrompt_Broadcast) {
            return newBuilder().mergeFrom(changePrompt_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ChangePrompt_Broadcast.PARSER = (Parser<ChangePrompt_Broadcast>)new AbstractParser<ChangePrompt_Broadcast>() {
                public ChangePrompt_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ChangePrompt_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ChangePrompt_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ChangePrompt_BroadcastOrBuilder
        {
            private int bitField0_;
            private Object prompt_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_ChangePrompt_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ChangePrompt_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ChangePrompt_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.prompt_ = "";
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.prompt_ = "";
                this.a();
            }
            
            private void a() {
                if (ChangePrompt_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.prompt_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_ChangePrompt_Broadcast_descriptor;
            }
            
            public ChangePrompt_Broadcast getDefaultInstanceForType() {
                return ChangePrompt_Broadcast.getDefaultInstance();
            }
            
            public ChangePrompt_Broadcast build() {
                final ChangePrompt_Broadcast buildPartial = this.buildPartial();
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
            
            public ChangePrompt_Broadcast buildPartial() {
                final ChangePrompt_Broadcast changePrompt_Broadcast = new ChangePrompt_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                changePrompt_Broadcast.prompt_ = this.prompt_;
                changePrompt_Broadcast.bitField0_ = n;
                this.onBuilt();
                return changePrompt_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof ChangePrompt_Broadcast) {
                        return this.mergeFrom((ChangePrompt_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ChangePrompt_Broadcast changePrompt_Broadcast) {
                try {
                    if (changePrompt_Broadcast == ChangePrompt_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (changePrompt_Broadcast.hasPrompt()) {
                        this.bitField0_ |= 0x1;
                        this.prompt_ = changePrompt_Broadcast.prompt_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                this.mergeUnknownFields(changePrompt_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasPrompt()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ChangePrompt_Broadcast changePrompt_Broadcast = null;
                try {
                    changePrompt_Broadcast = (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    changePrompt_Broadcast = (ChangePrompt_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (changePrompt_Broadcast != null) {
                            this.mergeFrom(changePrompt_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasPrompt() {
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
            
            public String getPrompt() {
                final Object prompt_ = this.prompt_;
                if (!(prompt_ instanceof String)) {
                    return (String)(this.prompt_ = ((ByteString)prompt_).toStringUtf8());
                }
                return (String)prompt_;
            }
            
            public ByteString getPromptBytes() {
                final Object prompt_ = this.prompt_;
                if (prompt_ instanceof String) {
                    return (ByteString)(this.prompt_ = ByteString.copyFromUtf8((String)prompt_));
                }
                return (ByteString)prompt_;
            }
            
            public Builder setPrompt(final String prompt_) {
                try {
                    if (prompt_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.prompt_ = prompt_;
                this.onChanged();
                return this;
            }
            
            public Builder clearPrompt() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.prompt_ = ChangePrompt_Broadcast.getDefaultInstance().getPrompt();
                this.onChanged();
                return this;
            }
            
            public Builder setPromptBytes(final ByteString prompt_) {
                try {
                    if (prompt_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.prompt_ = prompt_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class ReadyForCommands_Broadcast extends GeneratedMessage implements ReadyForCommands_BroadcastOrBuilder
    {
        private static final ReadyForCommands_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ReadyForCommands_Broadcast> PARSER;
        private int bitField0_;
        public static final int READY_FIELD_NUMBER = 1;
        private int ready_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ReadyForCommands_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ReadyForCommands_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ReadyForCommands_Broadcast getDefaultInstance() {
            return ReadyForCommands_Broadcast.defaultInstance;
        }
        
        public ReadyForCommands_Broadcast getDefaultInstanceForType() {
            return ReadyForCommands_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ReadyForCommands_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 8: {
                            this.bitField0_ |= 0x1;
                            this.ready_ = codedInputStream.readInt32();
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
            return Broadcasts.internal_static_ReadyForCommands_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_ReadyForCommands_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ReadyForCommands_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<ReadyForCommands_Broadcast> getParserForType() {
            return ReadyForCommands_Broadcast.PARSER;
        }
        
        public boolean hasReady() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getReady() {
            return this.ready_;
        }
        
        private void a() {
            this.ready_ = 0;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasReady()) {
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
                    codedOutputStream.writeInt32(1, this.ready_);
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
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeInt32Size(1, this.ready_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(array);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ReadyForCommands_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ReadyForCommands_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static ReadyForCommands_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
            return newBuilder().mergeFrom(readyForCommands_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ReadyForCommands_Broadcast.PARSER = (Parser<ReadyForCommands_Broadcast>)new AbstractParser<ReadyForCommands_Broadcast>() {
                public ReadyForCommands_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ReadyForCommands_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ReadyForCommands_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ReadyForCommands_BroadcastOrBuilder
        {
            private int bitField0_;
            private int ready_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_ReadyForCommands_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_ReadyForCommands_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ReadyForCommands_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.a();
            }
            
            private void a() {
                if (ReadyForCommands_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.ready_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_ReadyForCommands_Broadcast_descriptor;
            }
            
            public ReadyForCommands_Broadcast getDefaultInstanceForType() {
                return ReadyForCommands_Broadcast.getDefaultInstance();
            }
            
            public ReadyForCommands_Broadcast build() {
                final ReadyForCommands_Broadcast buildPartial = this.buildPartial();
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
                return buildPartial;
            }
            
            public ReadyForCommands_Broadcast buildPartial() {
                final ReadyForCommands_Broadcast readyForCommands_Broadcast = new ReadyForCommands_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                readyForCommands_Broadcast.ready_ = this.ready_;
                readyForCommands_Broadcast.bitField0_ = n;
                this.onBuilt();
                return readyForCommands_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                if (message instanceof ReadyForCommands_Broadcast) {
                    return this.mergeFrom((ReadyForCommands_Broadcast)message);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
                if (readyForCommands_Broadcast == ReadyForCommands_Broadcast.getDefaultInstance()) {
                    return this;
                }
                if (readyForCommands_Broadcast.hasReady()) {
                    this.setReady(readyForCommands_Broadcast.getReady());
                }
                this.mergeUnknownFields(readyForCommands_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                return this.hasReady();
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ReadyForCommands_Broadcast readyForCommands_Broadcast = null;
                try {
                    readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (readyForCommands_Broadcast != null) {
                            this.mergeFrom(readyForCommands_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b(ex2);
                    }
                }
                return this;
            }
            
            public boolean hasReady() {
                return (this.bitField0_ & 0x1) == 0x1;
            }
            
            public int getReady() {
                return this.ready_;
            }
            
            public Builder setReady(final int ready_) {
                this.bitField0_ |= 0x1;
                this.ready_ = ready_;
                this.onChanged();
                return this;
            }
            
            public Builder clearReady() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.ready_ = 0;
                this.onChanged();
                return this;
            }
            
            private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
                return ex;
            }
        }
    }
    
    public static final class CommandsInterpreter_Broadcast extends GeneratedMessage implements CommandsInterpreter_BroadcastOrBuilder
    {
        private static final CommandsInterpreter_Broadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CommandsInterpreter_Broadcast> PARSER;
        private int bitField0_;
        public static final int MESSAGE_FIELD_NUMBER = 1;
        private Object message_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private CommandsInterpreter_Broadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private CommandsInterpreter_Broadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static CommandsInterpreter_Broadcast getDefaultInstance() {
            return CommandsInterpreter_Broadcast.defaultInstance;
        }
        
        public CommandsInterpreter_Broadcast getDefaultInstanceForType() {
            return CommandsInterpreter_Broadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private CommandsInterpreter_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 10: {
                            this.bitField0_ |= 0x1;
                            this.message_ = codedInputStream.readBytes();
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
            return Broadcasts.internal_static_CommandsInterpreter_Broadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_CommandsInterpreter_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CommandsInterpreter_Broadcast.class, (Class)Builder.class);
        }
        
        public Parser<CommandsInterpreter_Broadcast> getParserForType() {
            return CommandsInterpreter_Broadcast.PARSER;
        }
        
        public boolean hasMessage() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getMessage() {
            final Object message_ = this.message_;
            if (message_ instanceof String) {
                return (String)message_;
            }
            final ByteString byteString = (ByteString)message_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.message_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getMessageBytes() {
            final Object message_ = this.message_;
            if (message_ instanceof String) {
                return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
            }
            return (ByteString)message_;
        }
        
        private void a() {
            this.message_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasMessage()) {
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
                    codedOutputStream.writeBytes(1, this.getMessageBytes());
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
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeBytesSize(1, this.getMessageBytes());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(byteString);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(array);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final InputStream inputStream) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(inputStream);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static CommandsInterpreter_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static CommandsInterpreter_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static CommandsInterpreter_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast) {
            return newBuilder().mergeFrom(commandsInterpreter_Broadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            CommandsInterpreter_Broadcast.PARSER = (Parser<CommandsInterpreter_Broadcast>)new AbstractParser<CommandsInterpreter_Broadcast>() {
                public CommandsInterpreter_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CommandsInterpreter_Broadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new CommandsInterpreter_Broadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommandsInterpreter_BroadcastOrBuilder
        {
            private int bitField0_;
            private Object message_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_CommandsInterpreter_Broadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_CommandsInterpreter_Broadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CommandsInterpreter_Broadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.message_ = "";
                this.b();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.message_ = "";
                this.b();
            }
            
            private void b() {
                if (CommandsInterpreter_Broadcast.alwaysUseFieldBuilders) {}
            }
            
            private static Builder a() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.message_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }
            
            public Builder clone() {
                return a().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_CommandsInterpreter_Broadcast_descriptor;
            }
            
            public CommandsInterpreter_Broadcast getDefaultInstanceForType() {
                return CommandsInterpreter_Broadcast.getDefaultInstance();
            }
            
            public CommandsInterpreter_Broadcast build() {
                final CommandsInterpreter_Broadcast buildPartial = this.buildPartial();
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
            
            public CommandsInterpreter_Broadcast buildPartial() {
                final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = new CommandsInterpreter_Broadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                commandsInterpreter_Broadcast.message_ = this.message_;
                commandsInterpreter_Broadcast.bitField0_ = n;
                this.onBuilt();
                return commandsInterpreter_Broadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof CommandsInterpreter_Broadcast) {
                        return this.mergeFrom((CommandsInterpreter_Broadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast) {
                try {
                    if (commandsInterpreter_Broadcast == CommandsInterpreter_Broadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (commandsInterpreter_Broadcast.hasMessage()) {
                        this.bitField0_ |= 0x1;
                        this.message_ = commandsInterpreter_Broadcast.message_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                this.mergeUnknownFields(commandsInterpreter_Broadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasMessage()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = null;
                try {
                    commandsInterpreter_Broadcast = (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    commandsInterpreter_Broadcast = (CommandsInterpreter_Broadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (commandsInterpreter_Broadcast != null) {
                            this.mergeFrom(commandsInterpreter_Broadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasMessage() {
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
            
            public String getMessage() {
                final Object message_ = this.message_;
                if (!(message_ instanceof String)) {
                    return (String)(this.message_ = ((ByteString)message_).toStringUtf8());
                }
                return (String)message_;
            }
            
            public ByteString getMessageBytes() {
                final Object message_ = this.message_;
                if (message_ instanceof String) {
                    return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
                }
                return (ByteString)message_;
            }
            
            public Builder setMessage(final String message_) {
                try {
                    if (message_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.message_ = message_;
                this.onChanged();
                return this;
            }
            
            public Builder clearMessage() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.message_ = CommandsInterpreter_Broadcast.getDefaultInstance().getMessage();
                this.onChanged();
                return this;
            }
            
            public Builder setMessageBytes(final ByteString message_) {
                try {
                    if (message_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.message_ = message_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class CompositeBroadcast extends GeneratedMessage implements CompositeBroadcastOrBuilder
    {
        private static final CompositeBroadcast defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CompositeBroadcast> PARSER;
        private int bitField0_;
        public static final int PROCESS_INTERRUPTED_FIELD_NUMBER = 1;
        private ProcessInterrupted_Broadcast processInterrupted_;
        public static final int PROCESS_RUNNING_FIELD_NUMBER = 2;
        private ProcessRunning_Broadcast processRunning_;
        public static final int PROCESS_EXITED_FIELD_NUMBER = 3;
        private ProcessExited_Broadcast processExited_;
        public static final int LOG_MESSAGE_FIELD_NUMBER = 4;
        private LogMessage_Broadcast logMessage_;
        public static final int CHANGE_PROMPT_FIELD_NUMBER = 5;
        private ChangePrompt_Broadcast changePrompt_;
        public static final int READY_FOR_COMMAND_FIELD_NUMBER = 6;
        private ReadyForCommands_Broadcast readyForCommand_;
        public static final int INTERPRETER_MESSAGE_FIELD_NUMBER = 7;
        private CommandsInterpreter_Broadcast interpreterMessage_;
        public static final int TARGET_PROCESS_OUTPUT_FIELD_NUMBER = 8;
        private TargetProcessOutput_Broadcast targetProcessOutput_;
        public static final int MODULES_LOADED_FIELD_NUMBER = 9;
        private ModulesLoaded_Broadcast modulesLoaded_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private CompositeBroadcast(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private CompositeBroadcast(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static CompositeBroadcast getDefaultInstance() {
            return CompositeBroadcast.defaultInstance;
        }
        
        public CompositeBroadcast getDefaultInstanceForType() {
            return CompositeBroadcast.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private CompositeBroadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        case 10: {
                            ProcessInterrupted_Broadcast.Builder builder2 = null;
                            if ((this.bitField0_ & 0x1) == 0x1) {
                                builder2 = this.processInterrupted_.toBuilder();
                            }
                            try {
                                this.processInterrupted_ = (ProcessInterrupted_Broadcast)codedInputStream.readMessage((Parser)ProcessInterrupted_Broadcast.PARSER, extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.processInterrupted_);
                                    this.processInterrupted_ = builder2.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x1;
                            continue;
                        }
                        case 18: {
                            ProcessRunning_Broadcast.Builder builder3 = null;
                            if ((this.bitField0_ & 0x2) == 0x2) {
                                builder3 = this.processRunning_.toBuilder();
                            }
                            try {
                                this.processRunning_ = (ProcessRunning_Broadcast)codedInputStream.readMessage((Parser)ProcessRunning_Broadcast.PARSER, extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.processRunning_);
                                    this.processRunning_ = builder3.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex2) {
                                throw a((IOException)ex2);
                            }
                            this.bitField0_ |= 0x2;
                            continue;
                        }
                        case 26: {
                            ProcessExited_Broadcast.Builder builder4 = null;
                            if ((this.bitField0_ & 0x4) == 0x4) {
                                builder4 = this.processExited_.toBuilder();
                            }
                            try {
                                this.processExited_ = (ProcessExited_Broadcast)codedInputStream.readMessage((Parser)ProcessExited_Broadcast.PARSER, extensionRegistryLite);
                                if (builder4 != null) {
                                    builder4.mergeFrom(this.processExited_);
                                    this.processExited_ = builder4.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex3) {
                                throw a((IOException)ex3);
                            }
                            this.bitField0_ |= 0x4;
                            continue;
                        }
                        case 34: {
                            LogMessage_Broadcast.Builder builder5 = null;
                            if ((this.bitField0_ & 0x8) == 0x8) {
                                builder5 = this.logMessage_.toBuilder();
                            }
                            try {
                                this.logMessage_ = (LogMessage_Broadcast)codedInputStream.readMessage((Parser)LogMessage_Broadcast.PARSER, extensionRegistryLite);
                                if (builder5 != null) {
                                    builder5.mergeFrom(this.logMessage_);
                                    this.logMessage_ = builder5.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex4) {
                                throw a((IOException)ex4);
                            }
                            this.bitField0_ |= 0x8;
                            continue;
                        }
                        case 42: {
                            ChangePrompt_Broadcast.Builder builder6 = null;
                            if ((this.bitField0_ & 0x10) == 0x10) {
                                builder6 = this.changePrompt_.toBuilder();
                            }
                            try {
                                this.changePrompt_ = (ChangePrompt_Broadcast)codedInputStream.readMessage((Parser)ChangePrompt_Broadcast.PARSER, extensionRegistryLite);
                                if (builder6 != null) {
                                    builder6.mergeFrom(this.changePrompt_);
                                    this.changePrompt_ = builder6.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex5) {
                                throw a((IOException)ex5);
                            }
                            this.bitField0_ |= 0x10;
                            continue;
                        }
                        case 50: {
                            ReadyForCommands_Broadcast.Builder builder7 = null;
                            if ((this.bitField0_ & 0x20) == 0x20) {
                                builder7 = this.readyForCommand_.toBuilder();
                            }
                            try {
                                this.readyForCommand_ = (ReadyForCommands_Broadcast)codedInputStream.readMessage((Parser)ReadyForCommands_Broadcast.PARSER, extensionRegistryLite);
                                if (builder7 != null) {
                                    builder7.mergeFrom(this.readyForCommand_);
                                    this.readyForCommand_ = builder7.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex6) {
                                throw a((IOException)ex6);
                            }
                            this.bitField0_ |= 0x20;
                            continue;
                        }
                        case 58: {
                            CommandsInterpreter_Broadcast.Builder builder8 = null;
                            if ((this.bitField0_ & 0x40) == 0x40) {
                                builder8 = this.interpreterMessage_.toBuilder();
                            }
                            try {
                                this.interpreterMessage_ = (CommandsInterpreter_Broadcast)codedInputStream.readMessage((Parser)CommandsInterpreter_Broadcast.PARSER, extensionRegistryLite);
                                if (builder8 != null) {
                                    builder8.mergeFrom(this.interpreterMessage_);
                                    this.interpreterMessage_ = builder8.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex7) {
                                throw a((IOException)ex7);
                            }
                            this.bitField0_ |= 0x40;
                            continue;
                        }
                        case 66: {
                            TargetProcessOutput_Broadcast.Builder builder9 = null;
                            if ((this.bitField0_ & 0x80) == 0x80) {
                                builder9 = this.targetProcessOutput_.toBuilder();
                            }
                            try {
                                this.targetProcessOutput_ = (TargetProcessOutput_Broadcast)codedInputStream.readMessage((Parser)TargetProcessOutput_Broadcast.PARSER, extensionRegistryLite);
                                if (builder9 != null) {
                                    builder9.mergeFrom(this.targetProcessOutput_);
                                    this.targetProcessOutput_ = builder9.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex8) {
                                throw a((IOException)ex8);
                            }
                            this.bitField0_ |= 0x80;
                            continue;
                        }
                        case 74: {
                            ModulesLoaded_Broadcast.Builder builder10 = null;
                            if ((this.bitField0_ & 0x100) == 0x100) {
                                builder10 = this.modulesLoaded_.toBuilder();
                            }
                            try {
                                this.modulesLoaded_ = (ModulesLoaded_Broadcast)codedInputStream.readMessage((Parser)ModulesLoaded_Broadcast.PARSER, extensionRegistryLite);
                                if (builder10 != null) {
                                    builder10.mergeFrom(this.modulesLoaded_);
                                    this.modulesLoaded_ = builder10.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex9) {
                                throw a((IOException)ex9);
                            }
                            this.bitField0_ |= 0x100;
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex10) {
                throw ex10.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex11) {
                throw new InvalidProtocolBufferException(ex11.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.internal_static_CompositeBroadcast_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.internal_static_CompositeBroadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CompositeBroadcast.class, (Class)Builder.class);
        }
        
        public Parser<CompositeBroadcast> getParserForType() {
            return CompositeBroadcast.PARSER;
        }
        
        public boolean hasProcessInterrupted() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public ProcessInterrupted_Broadcast getProcessInterrupted() {
            return this.processInterrupted_;
        }
        
        public ProcessInterrupted_BroadcastOrBuilder getProcessInterruptedOrBuilder() {
            return this.processInterrupted_;
        }
        
        public boolean hasProcessRunning() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public ProcessRunning_Broadcast getProcessRunning() {
            return this.processRunning_;
        }
        
        public ProcessRunning_BroadcastOrBuilder getProcessRunningOrBuilder() {
            return this.processRunning_;
        }
        
        public boolean hasProcessExited() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public ProcessExited_Broadcast getProcessExited() {
            return this.processExited_;
        }
        
        public ProcessExited_BroadcastOrBuilder getProcessExitedOrBuilder() {
            return this.processExited_;
        }
        
        public boolean hasLogMessage() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public LogMessage_Broadcast getLogMessage() {
            return this.logMessage_;
        }
        
        public LogMessage_BroadcastOrBuilder getLogMessageOrBuilder() {
            return this.logMessage_;
        }
        
        public boolean hasChangePrompt() {
            return (this.bitField0_ & 0x10) == 0x10;
        }
        
        public ChangePrompt_Broadcast getChangePrompt() {
            return this.changePrompt_;
        }
        
        public ChangePrompt_BroadcastOrBuilder getChangePromptOrBuilder() {
            return this.changePrompt_;
        }
        
        public boolean hasReadyForCommand() {
            return (this.bitField0_ & 0x20) == 0x20;
        }
        
        public ReadyForCommands_Broadcast getReadyForCommand() {
            return this.readyForCommand_;
        }
        
        public ReadyForCommands_BroadcastOrBuilder getReadyForCommandOrBuilder() {
            return this.readyForCommand_;
        }
        
        public boolean hasInterpreterMessage() {
            return (this.bitField0_ & 0x40) == 0x40;
        }
        
        public CommandsInterpreter_Broadcast getInterpreterMessage() {
            return this.interpreterMessage_;
        }
        
        public CommandsInterpreter_BroadcastOrBuilder getInterpreterMessageOrBuilder() {
            return this.interpreterMessage_;
        }
        
        public boolean hasTargetProcessOutput() {
            return (this.bitField0_ & 0x80) == 0x80;
        }
        
        public TargetProcessOutput_Broadcast getTargetProcessOutput() {
            return this.targetProcessOutput_;
        }
        
        public TargetProcessOutput_BroadcastOrBuilder getTargetProcessOutputOrBuilder() {
            return this.targetProcessOutput_;
        }
        
        public boolean hasModulesLoaded() {
            return (this.bitField0_ & 0x100) == 0x100;
        }
        
        public ModulesLoaded_Broadcast getModulesLoaded() {
            return this.modulesLoaded_;
        }
        
        public ModulesLoaded_BroadcastOrBuilder getModulesLoadedOrBuilder() {
            return this.modulesLoaded_;
        }
        
        private void a() {
            this.processInterrupted_ = ProcessInterrupted_Broadcast.getDefaultInstance();
            this.processRunning_ = ProcessRunning_Broadcast.getDefaultInstance();
            this.processExited_ = ProcessExited_Broadcast.getDefaultInstance();
            this.logMessage_ = LogMessage_Broadcast.getDefaultInstance();
            this.changePrompt_ = ChangePrompt_Broadcast.getDefaultInstance();
            this.readyForCommand_ = ReadyForCommands_Broadcast.getDefaultInstance();
            this.interpreterMessage_ = CommandsInterpreter_Broadcast.getDefaultInstance();
            this.targetProcessOutput_ = TargetProcessOutput_Broadcast.getDefaultInstance();
            this.modulesLoaded_ = ModulesLoaded_Broadcast.getDefaultInstance();
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (this.hasProcessInterrupted() && !this.getProcessInterrupted().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasProcessExited() && !this.getProcessExited().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLogMessage() && !this.getLogMessage().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasChangePrompt() && !this.getChangePrompt().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasReadyForCommand() && !this.getReadyForCommand().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasInterpreterMessage() && !this.getInterpreterMessage().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasTargetProcessOutput() && !this.getTargetProcessOutput().isInitialized()) {
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
                    codedOutputStream.writeMessage(1, (MessageLite)this.processInterrupted_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeMessage(2, (MessageLite)this.processRunning_);
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeMessage(3, (MessageLite)this.processExited_);
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeMessage(4, (MessageLite)this.logMessage_);
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    codedOutputStream.writeMessage(5, (MessageLite)this.changePrompt_);
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
            try {
                if ((this.bitField0_ & 0x20) == 0x20) {
                    codedOutputStream.writeMessage(6, (MessageLite)this.readyForCommand_);
                }
            }
            catch (IOException ex6) {
                throw a(ex6);
            }
            try {
                if ((this.bitField0_ & 0x40) == 0x40) {
                    codedOutputStream.writeMessage(7, (MessageLite)this.interpreterMessage_);
                }
            }
            catch (IOException ex7) {
                throw a(ex7);
            }
            try {
                if ((this.bitField0_ & 0x80) == 0x80) {
                    codedOutputStream.writeMessage(8, (MessageLite)this.targetProcessOutput_);
                }
            }
            catch (IOException ex8) {
                throw a(ex8);
            }
            try {
                if ((this.bitField0_ & 0x100) == 0x100) {
                    codedOutputStream.writeMessage(9, (MessageLite)this.modulesLoaded_);
                }
            }
            catch (IOException ex9) {
                throw a(ex9);
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
                n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.processInterrupted_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.processRunning_);
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeMessageSize(3, (MessageLite)this.processExited_);
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeMessageSize(4, (MessageLite)this.logMessage_);
            }
            if ((this.bitField0_ & 0x10) == 0x10) {
                n += CodedOutputStream.computeMessageSize(5, (MessageLite)this.changePrompt_);
            }
            if ((this.bitField0_ & 0x20) == 0x20) {
                n += CodedOutputStream.computeMessageSize(6, (MessageLite)this.readyForCommand_);
            }
            if ((this.bitField0_ & 0x40) == 0x40) {
                n += CodedOutputStream.computeMessageSize(7, (MessageLite)this.interpreterMessage_);
            }
            if ((this.bitField0_ & 0x80) == 0x80) {
                n += CodedOutputStream.computeMessageSize(8, (MessageLite)this.targetProcessOutput_);
            }
            if ((this.bitField0_ & 0x100) == 0x100) {
                n += CodedOutputStream.computeMessageSize(9, (MessageLite)this.modulesLoaded_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static CompositeBroadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(byteString);
        }
        
        public static CompositeBroadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static CompositeBroadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(array);
        }
        
        public static CompositeBroadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static CompositeBroadcast parseFrom(final InputStream inputStream) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(inputStream);
        }
        
        public static CompositeBroadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static CompositeBroadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static CompositeBroadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static CompositeBroadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(codedInputStream);
        }
        
        public static CompositeBroadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeBroadcast)CompositeBroadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return g();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final CompositeBroadcast compositeBroadcast) {
            return newBuilder().mergeFrom(compositeBroadcast);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            CompositeBroadcast.PARSER = (Parser<CompositeBroadcast>)new AbstractParser<CompositeBroadcast>() {
                public CompositeBroadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CompositeBroadcast(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new CompositeBroadcast(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements CompositeBroadcastOrBuilder
        {
            private int bitField0_;
            private ProcessInterrupted_Broadcast processInterrupted_;
            private SingleFieldBuilder<ProcessInterrupted_Broadcast, ProcessInterrupted_Broadcast.Builder, ProcessInterrupted_BroadcastOrBuilder> processInterruptedBuilder_;
            private ProcessRunning_Broadcast processRunning_;
            private SingleFieldBuilder<ProcessRunning_Broadcast, ProcessRunning_Broadcast.Builder, ProcessRunning_BroadcastOrBuilder> processRunningBuilder_;
            private ProcessExited_Broadcast processExited_;
            private SingleFieldBuilder<ProcessExited_Broadcast, ProcessExited_Broadcast.Builder, ProcessExited_BroadcastOrBuilder> processExitedBuilder_;
            private LogMessage_Broadcast logMessage_;
            private SingleFieldBuilder<LogMessage_Broadcast, LogMessage_Broadcast.Builder, LogMessage_BroadcastOrBuilder> logMessageBuilder_;
            private ChangePrompt_Broadcast changePrompt_;
            private SingleFieldBuilder<ChangePrompt_Broadcast, ChangePrompt_Broadcast.Builder, ChangePrompt_BroadcastOrBuilder> changePromptBuilder_;
            private ReadyForCommands_Broadcast readyForCommand_;
            private SingleFieldBuilder<ReadyForCommands_Broadcast, ReadyForCommands_Broadcast.Builder, ReadyForCommands_BroadcastOrBuilder> readyForCommandBuilder_;
            private CommandsInterpreter_Broadcast interpreterMessage_;
            private SingleFieldBuilder<CommandsInterpreter_Broadcast, CommandsInterpreter_Broadcast.Builder, CommandsInterpreter_BroadcastOrBuilder> interpreterMessageBuilder_;
            private TargetProcessOutput_Broadcast targetProcessOutput_;
            private SingleFieldBuilder<TargetProcessOutput_Broadcast, TargetProcessOutput_Broadcast.Builder, TargetProcessOutput_BroadcastOrBuilder> targetProcessOutputBuilder_;
            private ModulesLoaded_Broadcast modulesLoaded_;
            private SingleFieldBuilder<ModulesLoaded_Broadcast, ModulesLoaded_Broadcast.Builder, ModulesLoaded_BroadcastOrBuilder> modulesLoadedBuilder_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Broadcasts.internal_static_CompositeBroadcast_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Broadcasts.internal_static_CompositeBroadcast_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CompositeBroadcast.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.processInterrupted_ = ProcessInterrupted_Broadcast.getDefaultInstance();
                this.processRunning_ = ProcessRunning_Broadcast.getDefaultInstance();
                this.processExited_ = ProcessExited_Broadcast.getDefaultInstance();
                this.logMessage_ = LogMessage_Broadcast.getDefaultInstance();
                this.changePrompt_ = ChangePrompt_Broadcast.getDefaultInstance();
                this.readyForCommand_ = ReadyForCommands_Broadcast.getDefaultInstance();
                this.interpreterMessage_ = CommandsInterpreter_Broadcast.getDefaultInstance();
                this.targetProcessOutput_ = TargetProcessOutput_Broadcast.getDefaultInstance();
                this.modulesLoaded_ = ModulesLoaded_Broadcast.getDefaultInstance();
                this.f();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.processInterrupted_ = ProcessInterrupted_Broadcast.getDefaultInstance();
                this.processRunning_ = ProcessRunning_Broadcast.getDefaultInstance();
                this.processExited_ = ProcessExited_Broadcast.getDefaultInstance();
                this.logMessage_ = LogMessage_Broadcast.getDefaultInstance();
                this.changePrompt_ = ChangePrompt_Broadcast.getDefaultInstance();
                this.readyForCommand_ = ReadyForCommands_Broadcast.getDefaultInstance();
                this.interpreterMessage_ = CommandsInterpreter_Broadcast.getDefaultInstance();
                this.targetProcessOutput_ = TargetProcessOutput_Broadcast.getDefaultInstance();
                this.modulesLoaded_ = ModulesLoaded_Broadcast.getDefaultInstance();
                this.f();
            }
            
            private void f() {
                try {
                    if (CompositeBroadcast.alwaysUseFieldBuilders) {
                        this.i();
                        this.c();
                        this.b();
                        this.a();
                        this.j();
                        this.d();
                        this.e();
                        this.k();
                        this.h();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            private static Builder g() {
                return new Builder();
            }
            
            public Builder clear() {
                Label_0355: {
                    Label_0314: {
                        Label_0274: {
                            Label_0234: {
                                Label_0194: {
                                    Label_0154: {
                                        Label_0114: {
                                            Label_0074: {
                                                Label_0034: {
                                                    try {
                                                        super.clear();
                                                        if (this.processInterruptedBuilder_ == null) {
                                                            this.processInterrupted_ = ProcessInterrupted_Broadcast.getDefaultInstance();
                                                            break Label_0034;
                                                        }
                                                    }
                                                    catch (NullPointerException ex) {
                                                        throw b(ex);
                                                    }
                                                    this.processInterruptedBuilder_.clear();
                                                    try {
                                                        this.bitField0_ &= 0xFFFFFFFE;
                                                        if (this.processRunningBuilder_ == null) {
                                                            this.processRunning_ = ProcessRunning_Broadcast.getDefaultInstance();
                                                            break Label_0074;
                                                        }
                                                    }
                                                    catch (NullPointerException ex2) {
                                                        throw b(ex2);
                                                    }
                                                }
                                                this.processRunningBuilder_.clear();
                                                try {
                                                    this.bitField0_ &= 0xFFFFFFFD;
                                                    if (this.processExitedBuilder_ == null) {
                                                        this.processExited_ = ProcessExited_Broadcast.getDefaultInstance();
                                                        break Label_0114;
                                                    }
                                                }
                                                catch (NullPointerException ex3) {
                                                    throw b(ex3);
                                                }
                                            }
                                            this.processExitedBuilder_.clear();
                                            try {
                                                this.bitField0_ &= 0xFFFFFFFB;
                                                if (this.logMessageBuilder_ == null) {
                                                    this.logMessage_ = LogMessage_Broadcast.getDefaultInstance();
                                                    break Label_0154;
                                                }
                                            }
                                            catch (NullPointerException ex4) {
                                                throw b(ex4);
                                            }
                                        }
                                        this.logMessageBuilder_.clear();
                                        try {
                                            this.bitField0_ &= 0xFFFFFFF7;
                                            if (this.changePromptBuilder_ == null) {
                                                this.changePrompt_ = ChangePrompt_Broadcast.getDefaultInstance();
                                                break Label_0194;
                                            }
                                        }
                                        catch (NullPointerException ex5) {
                                            throw b(ex5);
                                        }
                                    }
                                    this.changePromptBuilder_.clear();
                                    try {
                                        this.bitField0_ &= 0xFFFFFFEF;
                                        if (this.readyForCommandBuilder_ == null) {
                                            this.readyForCommand_ = ReadyForCommands_Broadcast.getDefaultInstance();
                                            break Label_0234;
                                        }
                                    }
                                    catch (NullPointerException ex6) {
                                        throw b(ex6);
                                    }
                                }
                                this.readyForCommandBuilder_.clear();
                                try {
                                    this.bitField0_ &= 0xFFFFFFDF;
                                    if (this.interpreterMessageBuilder_ == null) {
                                        this.interpreterMessage_ = CommandsInterpreter_Broadcast.getDefaultInstance();
                                        break Label_0274;
                                    }
                                }
                                catch (NullPointerException ex7) {
                                    throw b(ex7);
                                }
                            }
                            this.interpreterMessageBuilder_.clear();
                            try {
                                this.bitField0_ &= 0xFFFFFFBF;
                                if (this.targetProcessOutputBuilder_ == null) {
                                    this.targetProcessOutput_ = TargetProcessOutput_Broadcast.getDefaultInstance();
                                    break Label_0314;
                                }
                            }
                            catch (NullPointerException ex8) {
                                throw b(ex8);
                            }
                        }
                        this.targetProcessOutputBuilder_.clear();
                        try {
                            this.bitField0_ &= 0xFFFFFF7F;
                            if (this.modulesLoadedBuilder_ == null) {
                                this.modulesLoaded_ = ModulesLoaded_Broadcast.getDefaultInstance();
                                break Label_0355;
                            }
                        }
                        catch (NullPointerException ex9) {
                            throw b(ex9);
                        }
                    }
                    this.modulesLoadedBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }
            
            public Builder clone() {
                return g().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Broadcasts.internal_static_CompositeBroadcast_descriptor;
            }
            
            public CompositeBroadcast getDefaultInstanceForType() {
                return CompositeBroadcast.getDefaultInstance();
            }
            
            public CompositeBroadcast build() {
                final CompositeBroadcast buildPartial = this.buildPartial();
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
            
            public CompositeBroadcast buildPartial() {
                final CompositeBroadcast compositeBroadcast = new CompositeBroadcast((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                Label_0066: {
                    try {
                        if (this.processInterruptedBuilder_ == null) {
                            compositeBroadcast.processInterrupted_ = this.processInterrupted_;
                            break Label_0066;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    compositeBroadcast.processInterrupted_ = (ProcessInterrupted_Broadcast)this.processInterruptedBuilder_.build();
                }
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                Label_0115: {
                    try {
                        if (this.processRunningBuilder_ == null) {
                            compositeBroadcast.processRunning_ = this.processRunning_;
                            break Label_0115;
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                    compositeBroadcast.processRunning_ = (ProcessRunning_Broadcast)this.processRunningBuilder_.build();
                }
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                Label_0164: {
                    try {
                        if (this.processExitedBuilder_ == null) {
                            compositeBroadcast.processExited_ = this.processExited_;
                            break Label_0164;
                        }
                    }
                    catch (NullPointerException ex3) {
                        throw b(ex3);
                    }
                    compositeBroadcast.processExited_ = (ProcessExited_Broadcast)this.processExitedBuilder_.build();
                }
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                Label_0216: {
                    try {
                        if (this.logMessageBuilder_ == null) {
                            compositeBroadcast.logMessage_ = this.logMessage_;
                            break Label_0216;
                        }
                    }
                    catch (NullPointerException ex4) {
                        throw b(ex4);
                    }
                    compositeBroadcast.logMessage_ = (LogMessage_Broadcast)this.logMessageBuilder_.build();
                }
                if ((bitField0_ & 0x10) == 0x10) {
                    n |= 0x10;
                }
                Label_0268: {
                    try {
                        if (this.changePromptBuilder_ == null) {
                            compositeBroadcast.changePrompt_ = this.changePrompt_;
                            break Label_0268;
                        }
                    }
                    catch (NullPointerException ex5) {
                        throw b(ex5);
                    }
                    compositeBroadcast.changePrompt_ = (ChangePrompt_Broadcast)this.changePromptBuilder_.build();
                }
                if ((bitField0_ & 0x20) == 0x20) {
                    n |= 0x20;
                }
                Label_0320: {
                    try {
                        if (this.readyForCommandBuilder_ == null) {
                            compositeBroadcast.readyForCommand_ = this.readyForCommand_;
                            break Label_0320;
                        }
                    }
                    catch (NullPointerException ex6) {
                        throw b(ex6);
                    }
                    compositeBroadcast.readyForCommand_ = (ReadyForCommands_Broadcast)this.readyForCommandBuilder_.build();
                }
                if ((bitField0_ & 0x40) == 0x40) {
                    n |= 0x40;
                }
                Label_0372: {
                    try {
                        if (this.interpreterMessageBuilder_ == null) {
                            compositeBroadcast.interpreterMessage_ = this.interpreterMessage_;
                            break Label_0372;
                        }
                    }
                    catch (NullPointerException ex7) {
                        throw b(ex7);
                    }
                    compositeBroadcast.interpreterMessage_ = (CommandsInterpreter_Broadcast)this.interpreterMessageBuilder_.build();
                }
                if ((bitField0_ & 0x80) == 0x80) {
                    n |= 0x80;
                }
                Label_0427: {
                    try {
                        if (this.targetProcessOutputBuilder_ == null) {
                            compositeBroadcast.targetProcessOutput_ = this.targetProcessOutput_;
                            break Label_0427;
                        }
                    }
                    catch (NullPointerException ex8) {
                        throw b(ex8);
                    }
                    compositeBroadcast.targetProcessOutput_ = (TargetProcessOutput_Broadcast)this.targetProcessOutputBuilder_.build();
                }
                if ((bitField0_ & 0x100) == 0x100) {
                    n |= 0x100;
                }
                Label_0482: {
                    try {
                        if (this.modulesLoadedBuilder_ == null) {
                            compositeBroadcast.modulesLoaded_ = this.modulesLoaded_;
                            break Label_0482;
                        }
                    }
                    catch (NullPointerException ex9) {
                        throw b(ex9);
                    }
                    compositeBroadcast.modulesLoaded_ = (ModulesLoaded_Broadcast)this.modulesLoadedBuilder_.build();
                }
                compositeBroadcast.bitField0_ = n;
                this.onBuilt();
                return compositeBroadcast;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof CompositeBroadcast) {
                        return this.mergeFrom((CompositeBroadcast)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final CompositeBroadcast compositeBroadcast) {
                try {
                    if (compositeBroadcast == CompositeBroadcast.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (compositeBroadcast.hasProcessInterrupted()) {
                        this.mergeProcessInterrupted(compositeBroadcast.getProcessInterrupted());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (compositeBroadcast.hasProcessRunning()) {
                        this.mergeProcessRunning(compositeBroadcast.getProcessRunning());
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (compositeBroadcast.hasProcessExited()) {
                        this.mergeProcessExited(compositeBroadcast.getProcessExited());
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                try {
                    if (compositeBroadcast.hasLogMessage()) {
                        this.mergeLogMessage(compositeBroadcast.getLogMessage());
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                try {
                    if (compositeBroadcast.hasChangePrompt()) {
                        this.mergeChangePrompt(compositeBroadcast.getChangePrompt());
                    }
                }
                catch (NullPointerException ex6) {
                    throw b(ex6);
                }
                try {
                    if (compositeBroadcast.hasReadyForCommand()) {
                        this.mergeReadyForCommand(compositeBroadcast.getReadyForCommand());
                    }
                }
                catch (NullPointerException ex7) {
                    throw b(ex7);
                }
                try {
                    if (compositeBroadcast.hasInterpreterMessage()) {
                        this.mergeInterpreterMessage(compositeBroadcast.getInterpreterMessage());
                    }
                }
                catch (NullPointerException ex8) {
                    throw b(ex8);
                }
                try {
                    if (compositeBroadcast.hasTargetProcessOutput()) {
                        this.mergeTargetProcessOutput(compositeBroadcast.getTargetProcessOutput());
                    }
                }
                catch (NullPointerException ex9) {
                    throw b(ex9);
                }
                try {
                    if (compositeBroadcast.hasModulesLoaded()) {
                        this.mergeModulesLoaded(compositeBroadcast.getModulesLoaded());
                    }
                }
                catch (NullPointerException ex10) {
                    throw b(ex10);
                }
                this.mergeUnknownFields(compositeBroadcast.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                Label_0180: {
                    Label_0150: {
                        Label_0120: {
                            Label_0090: {
                                Label_0060: {
                                    Label_0030: {
                                        try {
                                            if (!this.hasProcessInterrupted()) {
                                                break Label_0030;
                                            }
                                            final Builder builder = this;
                                            final ProcessInterrupted_Broadcast processInterrupted_Broadcast = builder.getProcessInterrupted();
                                            final boolean b = processInterrupted_Broadcast.isInitialized();
                                            if (!b) {
                                                return false;
                                            }
                                            break Label_0030;
                                        }
                                        catch (NullPointerException ex) {
                                            throw b(ex);
                                        }
                                        try {
                                            final Builder builder = this;
                                            final ProcessInterrupted_Broadcast processInterrupted_Broadcast = builder.getProcessInterrupted();
                                            final boolean b = processInterrupted_Broadcast.isInitialized();
                                            if (!b) {
                                                return false;
                                            }
                                        }
                                        catch (NullPointerException ex2) {
                                            throw b(ex2);
                                        }
                                        try {
                                            if (!this.hasProcessExited()) {
                                                break Label_0060;
                                            }
                                            final Builder builder2 = this;
                                            final ProcessExited_Broadcast processExited_Broadcast = builder2.getProcessExited();
                                            final boolean b2 = processExited_Broadcast.isInitialized();
                                            if (!b2) {
                                                return false;
                                            }
                                            break Label_0060;
                                        }
                                        catch (NullPointerException ex3) {
                                            throw b(ex3);
                                        }
                                    }
                                    try {
                                        final Builder builder2 = this;
                                        final ProcessExited_Broadcast processExited_Broadcast = builder2.getProcessExited();
                                        final boolean b2 = processExited_Broadcast.isInitialized();
                                        if (!b2) {
                                            return false;
                                        }
                                    }
                                    catch (NullPointerException ex4) {
                                        throw b(ex4);
                                    }
                                    try {
                                        if (!this.hasLogMessage()) {
                                            break Label_0090;
                                        }
                                        final Builder builder3 = this;
                                        final LogMessage_Broadcast logMessage_Broadcast = builder3.getLogMessage();
                                        final boolean b3 = logMessage_Broadcast.isInitialized();
                                        if (!b3) {
                                            return false;
                                        }
                                        break Label_0090;
                                    }
                                    catch (NullPointerException ex5) {
                                        throw b(ex5);
                                    }
                                }
                                try {
                                    final Builder builder3 = this;
                                    final LogMessage_Broadcast logMessage_Broadcast = builder3.getLogMessage();
                                    final boolean b3 = logMessage_Broadcast.isInitialized();
                                    if (!b3) {
                                        return false;
                                    }
                                }
                                catch (NullPointerException ex6) {
                                    throw b(ex6);
                                }
                                try {
                                    if (!this.hasChangePrompt()) {
                                        break Label_0120;
                                    }
                                    final Builder builder4 = this;
                                    final ChangePrompt_Broadcast changePrompt_Broadcast = builder4.getChangePrompt();
                                    final boolean b4 = changePrompt_Broadcast.isInitialized();
                                    if (!b4) {
                                        return false;
                                    }
                                    break Label_0120;
                                }
                                catch (NullPointerException ex7) {
                                    throw b(ex7);
                                }
                            }
                            try {
                                final Builder builder4 = this;
                                final ChangePrompt_Broadcast changePrompt_Broadcast = builder4.getChangePrompt();
                                final boolean b4 = changePrompt_Broadcast.isInitialized();
                                if (!b4) {
                                    return false;
                                }
                            }
                            catch (NullPointerException ex8) {
                                throw b(ex8);
                            }
                            try {
                                if (!this.hasReadyForCommand()) {
                                    break Label_0150;
                                }
                                final Builder builder5 = this;
                                final ReadyForCommands_Broadcast readyForCommands_Broadcast = builder5.getReadyForCommand();
                                final boolean b5 = readyForCommands_Broadcast.isInitialized();
                                if (!b5) {
                                    return false;
                                }
                                break Label_0150;
                            }
                            catch (NullPointerException ex9) {
                                throw b(ex9);
                            }
                        }
                        try {
                            final Builder builder5 = this;
                            final ReadyForCommands_Broadcast readyForCommands_Broadcast = builder5.getReadyForCommand();
                            final boolean b5 = readyForCommands_Broadcast.isInitialized();
                            if (!b5) {
                                return false;
                            }
                        }
                        catch (NullPointerException ex10) {
                            throw b(ex10);
                        }
                        try {
                            if (!this.hasInterpreterMessage()) {
                                break Label_0180;
                            }
                            final Builder builder6 = this;
                            final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = builder6.getInterpreterMessage();
                            final boolean b6 = commandsInterpreter_Broadcast.isInitialized();
                            if (!b6) {
                                return false;
                            }
                            break Label_0180;
                        }
                        catch (NullPointerException ex11) {
                            throw b(ex11);
                        }
                    }
                    try {
                        final Builder builder6 = this;
                        final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = builder6.getInterpreterMessage();
                        final boolean b6 = commandsInterpreter_Broadcast.isInitialized();
                        if (!b6) {
                            return false;
                        }
                    }
                    catch (NullPointerException ex12) {
                        throw b(ex12);
                    }
                    try {
                        if (!this.hasTargetProcessOutput()) {
                            return true;
                        }
                        final Builder builder7 = this;
                        final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = builder7.getTargetProcessOutput();
                        final boolean b7 = targetProcessOutput_Broadcast.isInitialized();
                        if (!b7) {
                            return false;
                        }
                        return true;
                    }
                    catch (NullPointerException ex13) {
                        throw b(ex13);
                    }
                }
                try {
                    final Builder builder7 = this;
                    final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = builder7.getTargetProcessOutput();
                    final boolean b7 = targetProcessOutput_Broadcast.isInitialized();
                    if (!b7) {
                        return false;
                    }
                }
                catch (NullPointerException ex14) {
                    throw b(ex14);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CompositeBroadcast compositeBroadcast = null;
                try {
                    compositeBroadcast = (CompositeBroadcast)CompositeBroadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    compositeBroadcast = (CompositeBroadcast)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (compositeBroadcast != null) {
                            this.mergeFrom(compositeBroadcast);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasProcessInterrupted() {
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
            
            public ProcessInterrupted_Broadcast getProcessInterrupted() {
                try {
                    if (this.processInterruptedBuilder_ == null) {
                        return this.processInterrupted_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ProcessInterrupted_Broadcast)this.processInterruptedBuilder_.getMessage();
            }
            
            public Builder setProcessInterrupted(final ProcessInterrupted_Broadcast processInterrupted_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.processInterruptedBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ProcessInterrupted_Broadcast processInterrupted_Broadcast2 = processInterrupted_Broadcast;
                                if (processInterrupted_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ProcessInterrupted_Broadcast processInterrupted_Broadcast2 = processInterrupted_Broadcast;
                                if (processInterrupted_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.processInterrupted_ = processInterrupted_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.processInterruptedBuilder_.setMessage((GeneratedMessage)processInterrupted_Broadcast);
                }
                this.bitField0_ |= 0x1;
                return this;
            }
            
            public Builder setProcessInterrupted(final ProcessInterrupted_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.processInterruptedBuilder_ == null) {
                            this.processInterrupted_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processInterruptedBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x1;
                return this;
            }
            
            public Builder mergeProcessInterrupted(final ProcessInterrupted_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterruptedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       78
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: iconst_1       
                //    12: iand           
                //    13: iconst_1       
                //    14: if_icmpne       66
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    23: athrow         
                //    24: aload_0        
                //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterrupted_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    31: if_acmpeq       66
                //    34: goto            41
                //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    40: athrow         
                //    41: aload_0        
                //    42: aload_0        
                //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterrupted_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder;
                //    49: aload_1        
                //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder;
                //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterrupted_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    59: goto            71
                //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    65: athrow         
                //    66: aload_0        
                //    67: aload_1        
                //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterrupted_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast;
                //    71: aload_0        
                //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    75: goto            87
                //    78: aload_0        
                //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processInterruptedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    82: aload_1        
                //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    86: pop            
                //    87: aload_0        
                //    88: dup            
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    92: iconst_1       
                //    93: ior            
                //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    97: aload_0        
                //    98: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      17     20     24     Ljava/lang/NullPointerException;
                //  7      34     37     41     Ljava/lang/NullPointerException;
                //  24     62     62     66     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearProcessInterrupted() {
                Label_0033: {
                    try {
                        if (this.processInterruptedBuilder_ == null) {
                            this.processInterrupted_ = ProcessInterrupted_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processInterruptedBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }
            
            public ProcessInterrupted_Broadcast.Builder getProcessInterruptedBuilder() {
                this.bitField0_ |= 0x1;
                this.onChanged();
                return (ProcessInterrupted_Broadcast.Builder)this.i().getBuilder();
            }
            
            public ProcessInterrupted_BroadcastOrBuilder getProcessInterruptedOrBuilder() {
                try {
                    if (this.processInterruptedBuilder_ != null) {
                        return (ProcessInterrupted_BroadcastOrBuilder)this.processInterruptedBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processInterrupted_;
            }
            
            private SingleFieldBuilder<ProcessInterrupted_Broadcast, ProcessInterrupted_Broadcast.Builder, ProcessInterrupted_BroadcastOrBuilder> i() {
                try {
                    if (this.processInterruptedBuilder_ == null) {
                        this.processInterruptedBuilder_ = (SingleFieldBuilder<ProcessInterrupted_Broadcast, ProcessInterrupted_Broadcast.Builder, ProcessInterrupted_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.processInterrupted_, this.getParentForChildren(), this.isClean());
                        this.processInterrupted_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processInterruptedBuilder_;
            }
            
            public boolean hasProcessRunning() {
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
            
            public ProcessRunning_Broadcast getProcessRunning() {
                try {
                    if (this.processRunningBuilder_ == null) {
                        return this.processRunning_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ProcessRunning_Broadcast)this.processRunningBuilder_.getMessage();
            }
            
            public Builder setProcessRunning(final ProcessRunning_Broadcast processRunning_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.processRunningBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ProcessRunning_Broadcast processRunning_Broadcast2 = processRunning_Broadcast;
                                if (processRunning_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ProcessRunning_Broadcast processRunning_Broadcast2 = processRunning_Broadcast;
                                if (processRunning_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.processRunning_ = processRunning_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.processRunningBuilder_.setMessage((GeneratedMessage)processRunning_Broadcast);
                }
                this.bitField0_ |= 0x2;
                return this;
            }
            
            public Builder setProcessRunning(final ProcessRunning_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.processRunningBuilder_ == null) {
                            this.processRunning_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processRunningBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x2;
                return this;
            }
            
            public Builder mergeProcessRunning(final ProcessRunning_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunningBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       78
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: iconst_2       
                //    12: iand           
                //    13: iconst_2       
                //    14: if_icmpne       66
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    23: athrow         
                //    24: aload_0        
                //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunning_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    31: if_acmpeq       66
                //    34: goto            41
                //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    40: athrow         
                //    41: aload_0        
                //    42: aload_0        
                //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunning_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast$Builder;
                //    49: aload_1        
                //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast$Builder;
                //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunning_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    59: goto            71
                //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    65: athrow         
                //    66: aload_0        
                //    67: aload_1        
                //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunning_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessRunning_Broadcast;
                //    71: aload_0        
                //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    75: goto            87
                //    78: aload_0        
                //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processRunningBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    82: aload_1        
                //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    86: pop            
                //    87: aload_0        
                //    88: dup            
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    92: iconst_2       
                //    93: ior            
                //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    97: aload_0        
                //    98: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      17     20     24     Ljava/lang/NullPointerException;
                //  7      34     37     41     Ljava/lang/NullPointerException;
                //  24     62     62     66     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearProcessRunning() {
                Label_0033: {
                    try {
                        if (this.processRunningBuilder_ == null) {
                            this.processRunning_ = ProcessRunning_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processRunningBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public ProcessRunning_Broadcast.Builder getProcessRunningBuilder() {
                this.bitField0_ |= 0x2;
                this.onChanged();
                return (ProcessRunning_Broadcast.Builder)this.c().getBuilder();
            }
            
            public ProcessRunning_BroadcastOrBuilder getProcessRunningOrBuilder() {
                try {
                    if (this.processRunningBuilder_ != null) {
                        return (ProcessRunning_BroadcastOrBuilder)this.processRunningBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processRunning_;
            }
            
            private SingleFieldBuilder<ProcessRunning_Broadcast, ProcessRunning_Broadcast.Builder, ProcessRunning_BroadcastOrBuilder> c() {
                try {
                    if (this.processRunningBuilder_ == null) {
                        this.processRunningBuilder_ = (SingleFieldBuilder<ProcessRunning_Broadcast, ProcessRunning_Broadcast.Builder, ProcessRunning_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.processRunning_, this.getParentForChildren(), this.isClean());
                        this.processRunning_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processRunningBuilder_;
            }
            
            public boolean hasProcessExited() {
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
            
            public ProcessExited_Broadcast getProcessExited() {
                try {
                    if (this.processExitedBuilder_ == null) {
                        return this.processExited_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ProcessExited_Broadcast)this.processExitedBuilder_.getMessage();
            }
            
            public Builder setProcessExited(final ProcessExited_Broadcast processExited_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.processExitedBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ProcessExited_Broadcast processExited_Broadcast2 = processExited_Broadcast;
                                if (processExited_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ProcessExited_Broadcast processExited_Broadcast2 = processExited_Broadcast;
                                if (processExited_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.processExited_ = processExited_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.processExitedBuilder_.setMessage((GeneratedMessage)processExited_Broadcast);
                }
                this.bitField0_ |= 0x4;
                return this;
            }
            
            public Builder setProcessExited(final ProcessExited_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.processExitedBuilder_ == null) {
                            this.processExited_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processExitedBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x4;
                return this;
            }
            
            public Builder mergeProcessExited(final ProcessExited_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExitedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       78
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: iconst_4       
                //    12: iand           
                //    13: iconst_4       
                //    14: if_icmpne       66
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    23: athrow         
                //    24: aload_0        
                //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExited_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    31: if_acmpeq       66
                //    34: goto            41
                //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    40: athrow         
                //    41: aload_0        
                //    42: aload_0        
                //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExited_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast$Builder;
                //    49: aload_1        
                //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast$Builder;
                //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExited_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    59: goto            71
                //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    65: athrow         
                //    66: aload_0        
                //    67: aload_1        
                //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExited_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessExited_Broadcast;
                //    71: aload_0        
                //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    75: goto            87
                //    78: aload_0        
                //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.processExitedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    82: aload_1        
                //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    86: pop            
                //    87: aload_0        
                //    88: dup            
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    92: iconst_4       
                //    93: ior            
                //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    97: aload_0        
                //    98: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      17     20     24     Ljava/lang/NullPointerException;
                //  7      34     37     41     Ljava/lang/NullPointerException;
                //  24     62     62     66     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearProcessExited() {
                Label_0033: {
                    try {
                        if (this.processExitedBuilder_ == null) {
                            this.processExited_ = ProcessExited_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.processExitedBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }
            
            public ProcessExited_Broadcast.Builder getProcessExitedBuilder() {
                this.bitField0_ |= 0x4;
                this.onChanged();
                return (ProcessExited_Broadcast.Builder)this.b().getBuilder();
            }
            
            public ProcessExited_BroadcastOrBuilder getProcessExitedOrBuilder() {
                try {
                    if (this.processExitedBuilder_ != null) {
                        return (ProcessExited_BroadcastOrBuilder)this.processExitedBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processExited_;
            }
            
            private SingleFieldBuilder<ProcessExited_Broadcast, ProcessExited_Broadcast.Builder, ProcessExited_BroadcastOrBuilder> b() {
                try {
                    if (this.processExitedBuilder_ == null) {
                        this.processExitedBuilder_ = (SingleFieldBuilder<ProcessExited_Broadcast, ProcessExited_Broadcast.Builder, ProcessExited_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.processExited_, this.getParentForChildren(), this.isClean());
                        this.processExited_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.processExitedBuilder_;
            }
            
            public boolean hasLogMessage() {
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
            
            public LogMessage_Broadcast getLogMessage() {
                try {
                    if (this.logMessageBuilder_ == null) {
                        return this.logMessage_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (LogMessage_Broadcast)this.logMessageBuilder_.getMessage();
            }
            
            public Builder setLogMessage(final LogMessage_Broadcast logMessage_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.logMessageBuilder_ != null) {
                                    break Label_0042;
                                }
                                final LogMessage_Broadcast logMessage_Broadcast2 = logMessage_Broadcast;
                                if (logMessage_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final LogMessage_Broadcast logMessage_Broadcast2 = logMessage_Broadcast;
                                if (logMessage_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.logMessage_ = logMessage_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.logMessageBuilder_.setMessage((GeneratedMessage)logMessage_Broadcast);
                }
                this.bitField0_ |= 0x8;
                return this;
            }
            
            public Builder setLogMessage(final LogMessage_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.logMessageBuilder_ == null) {
                            this.logMessage_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.logMessageBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x8;
                return this;
            }
            
            public Builder mergeLogMessage(final LogMessage_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessageBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       80
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: bipush          8
                //    13: iand           
                //    14: bipush          8
                //    16: if_icmpne       68
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    33: if_acmpeq       68
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    42: athrow         
                //    43: aload_0        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast$Builder;
                //    51: aload_1        
                //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast$Builder;
                //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    61: goto            73
                //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    67: athrow         
                //    68: aload_0        
                //    69: aload_1        
                //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$LogMessage_Broadcast;
                //    73: aload_0        
                //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    77: goto            89
                //    80: aload_0        
                //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.logMessageBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    84: aload_1        
                //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    88: pop            
                //    89: aload_0        
                //    90: dup            
                //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    94: bipush          8
                //    96: ior            
                //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   100: aload_0        
                //   101: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      19     22     26     Ljava/lang/NullPointerException;
                //  7      36     39     43     Ljava/lang/NullPointerException;
                //  26     64     64     68     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearLogMessage() {
                Label_0033: {
                    try {
                        if (this.logMessageBuilder_ == null) {
                            this.logMessage_ = LogMessage_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.logMessageBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }
            
            public LogMessage_Broadcast.Builder getLogMessageBuilder() {
                this.bitField0_ |= 0x8;
                this.onChanged();
                return (LogMessage_Broadcast.Builder)this.a().getBuilder();
            }
            
            public LogMessage_BroadcastOrBuilder getLogMessageOrBuilder() {
                try {
                    if (this.logMessageBuilder_ != null) {
                        return (LogMessage_BroadcastOrBuilder)this.logMessageBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.logMessage_;
            }
            
            private SingleFieldBuilder<LogMessage_Broadcast, LogMessage_Broadcast.Builder, LogMessage_BroadcastOrBuilder> a() {
                try {
                    if (this.logMessageBuilder_ == null) {
                        this.logMessageBuilder_ = (SingleFieldBuilder<LogMessage_Broadcast, LogMessage_Broadcast.Builder, LogMessage_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.logMessage_, this.getParentForChildren(), this.isClean());
                        this.logMessage_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.logMessageBuilder_;
            }
            
            public boolean hasChangePrompt() {
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
            
            public ChangePrompt_Broadcast getChangePrompt() {
                try {
                    if (this.changePromptBuilder_ == null) {
                        return this.changePrompt_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ChangePrompt_Broadcast)this.changePromptBuilder_.getMessage();
            }
            
            public Builder setChangePrompt(final ChangePrompt_Broadcast changePrompt_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.changePromptBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ChangePrompt_Broadcast changePrompt_Broadcast2 = changePrompt_Broadcast;
                                if (changePrompt_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ChangePrompt_Broadcast changePrompt_Broadcast2 = changePrompt_Broadcast;
                                if (changePrompt_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.changePrompt_ = changePrompt_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.changePromptBuilder_.setMessage((GeneratedMessage)changePrompt_Broadcast);
                }
                this.bitField0_ |= 0x10;
                return this;
            }
            
            public Builder setChangePrompt(final ChangePrompt_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.changePromptBuilder_ == null) {
                            this.changePrompt_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.changePromptBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x10;
                return this;
            }
            
            public Builder mergeChangePrompt(final ChangePrompt_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePromptBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       80
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: bipush          16
                //    13: iand           
                //    14: bipush          16
                //    16: if_icmpne       68
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePrompt_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    33: if_acmpeq       68
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    42: athrow         
                //    43: aload_0        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePrompt_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast$Builder;
                //    51: aload_1        
                //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast$Builder;
                //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePrompt_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    61: goto            73
                //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    67: athrow         
                //    68: aload_0        
                //    69: aload_1        
                //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePrompt_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ChangePrompt_Broadcast;
                //    73: aload_0        
                //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    77: goto            89
                //    80: aload_0        
                //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.changePromptBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    84: aload_1        
                //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    88: pop            
                //    89: aload_0        
                //    90: dup            
                //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    94: bipush          16
                //    96: ior            
                //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   100: aload_0        
                //   101: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      19     22     26     Ljava/lang/NullPointerException;
                //  7      36     39     43     Ljava/lang/NullPointerException;
                //  26     64     64     68     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearChangePrompt() {
                Label_0033: {
                    try {
                        if (this.changePromptBuilder_ == null) {
                            this.changePrompt_ = ChangePrompt_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.changePromptBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }
            
            public ChangePrompt_Broadcast.Builder getChangePromptBuilder() {
                this.bitField0_ |= 0x10;
                this.onChanged();
                return (ChangePrompt_Broadcast.Builder)this.j().getBuilder();
            }
            
            public ChangePrompt_BroadcastOrBuilder getChangePromptOrBuilder() {
                try {
                    if (this.changePromptBuilder_ != null) {
                        return (ChangePrompt_BroadcastOrBuilder)this.changePromptBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.changePrompt_;
            }
            
            private SingleFieldBuilder<ChangePrompt_Broadcast, ChangePrompt_Broadcast.Builder, ChangePrompt_BroadcastOrBuilder> j() {
                try {
                    if (this.changePromptBuilder_ == null) {
                        this.changePromptBuilder_ = (SingleFieldBuilder<ChangePrompt_Broadcast, ChangePrompt_Broadcast.Builder, ChangePrompt_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.changePrompt_, this.getParentForChildren(), this.isClean());
                        this.changePrompt_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.changePromptBuilder_;
            }
            
            public boolean hasReadyForCommand() {
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
            
            public ReadyForCommands_Broadcast getReadyForCommand() {
                try {
                    if (this.readyForCommandBuilder_ == null) {
                        return this.readyForCommand_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ReadyForCommands_Broadcast)this.readyForCommandBuilder_.getMessage();
            }
            
            public Builder setReadyForCommand(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.readyForCommandBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ReadyForCommands_Broadcast readyForCommands_Broadcast2 = readyForCommands_Broadcast;
                                if (readyForCommands_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ReadyForCommands_Broadcast readyForCommands_Broadcast2 = readyForCommands_Broadcast;
                                if (readyForCommands_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.readyForCommand_ = readyForCommands_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.readyForCommandBuilder_.setMessage((GeneratedMessage)readyForCommands_Broadcast);
                }
                this.bitField0_ |= 0x20;
                return this;
            }
            
            public Builder setReadyForCommand(final ReadyForCommands_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.readyForCommandBuilder_ == null) {
                            this.readyForCommand_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.readyForCommandBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x20;
                return this;
            }
            
            public Builder mergeReadyForCommand(final ReadyForCommands_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       80
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: bipush          32
                //    13: iand           
                //    14: bipush          32
                //    16: if_icmpne       68
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    33: if_acmpeq       68
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    42: athrow         
                //    43: aload_0        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast$Builder;
                //    51: aload_1        
                //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast$Builder;
                //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    61: goto            73
                //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    67: athrow         
                //    68: aload_0        
                //    69: aload_1        
                //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ReadyForCommands_Broadcast;
                //    73: aload_0        
                //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    77: goto            89
                //    80: aload_0        
                //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.readyForCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    84: aload_1        
                //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    88: pop            
                //    89: aload_0        
                //    90: dup            
                //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    94: bipush          32
                //    96: ior            
                //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   100: aload_0        
                //   101: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      19     22     26     Ljava/lang/NullPointerException;
                //  7      36     39     43     Ljava/lang/NullPointerException;
                //  26     64     64     68     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearReadyForCommand() {
                Label_0033: {
                    try {
                        if (this.readyForCommandBuilder_ == null) {
                            this.readyForCommand_ = ReadyForCommands_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.readyForCommandBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                return this;
            }
            
            public ReadyForCommands_Broadcast.Builder getReadyForCommandBuilder() {
                this.bitField0_ |= 0x20;
                this.onChanged();
                return (ReadyForCommands_Broadcast.Builder)this.d().getBuilder();
            }
            
            public ReadyForCommands_BroadcastOrBuilder getReadyForCommandOrBuilder() {
                try {
                    if (this.readyForCommandBuilder_ != null) {
                        return (ReadyForCommands_BroadcastOrBuilder)this.readyForCommandBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.readyForCommand_;
            }
            
            private SingleFieldBuilder<ReadyForCommands_Broadcast, ReadyForCommands_Broadcast.Builder, ReadyForCommands_BroadcastOrBuilder> d() {
                try {
                    if (this.readyForCommandBuilder_ == null) {
                        this.readyForCommandBuilder_ = (SingleFieldBuilder<ReadyForCommands_Broadcast, ReadyForCommands_Broadcast.Builder, ReadyForCommands_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.readyForCommand_, this.getParentForChildren(), this.isClean());
                        this.readyForCommand_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.readyForCommandBuilder_;
            }
            
            public boolean hasInterpreterMessage() {
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
            
            public CommandsInterpreter_Broadcast getInterpreterMessage() {
                try {
                    if (this.interpreterMessageBuilder_ == null) {
                        return this.interpreterMessage_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (CommandsInterpreter_Broadcast)this.interpreterMessageBuilder_.getMessage();
            }
            
            public Builder setInterpreterMessage(final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.interpreterMessageBuilder_ != null) {
                                    break Label_0042;
                                }
                                final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast2 = commandsInterpreter_Broadcast;
                                if (commandsInterpreter_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast2 = commandsInterpreter_Broadcast;
                                if (commandsInterpreter_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.interpreterMessage_ = commandsInterpreter_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.interpreterMessageBuilder_.setMessage((GeneratedMessage)commandsInterpreter_Broadcast);
                }
                this.bitField0_ |= 0x40;
                return this;
            }
            
            public Builder setInterpreterMessage(final CommandsInterpreter_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.interpreterMessageBuilder_ == null) {
                            this.interpreterMessage_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.interpreterMessageBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x40;
                return this;
            }
            
            public Builder mergeInterpreterMessage(final CommandsInterpreter_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessageBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       80
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: bipush          64
                //    13: iand           
                //    14: bipush          64
                //    16: if_icmpne       68
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    33: if_acmpeq       68
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    42: athrow         
                //    43: aload_0        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast$Builder;
                //    51: aload_1        
                //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast$Builder;
                //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    61: goto            73
                //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    67: athrow         
                //    68: aload_0        
                //    69: aload_1        
                //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessage_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CommandsInterpreter_Broadcast;
                //    73: aload_0        
                //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    77: goto            89
                //    80: aload_0        
                //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.interpreterMessageBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    84: aload_1        
                //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    88: pop            
                //    89: aload_0        
                //    90: dup            
                //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    94: bipush          64
                //    96: ior            
                //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   100: aload_0        
                //   101: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      19     22     26     Ljava/lang/NullPointerException;
                //  7      36     39     43     Ljava/lang/NullPointerException;
                //  26     64     64     68     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearInterpreterMessage() {
                Label_0033: {
                    try {
                        if (this.interpreterMessageBuilder_ == null) {
                            this.interpreterMessage_ = CommandsInterpreter_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.interpreterMessageBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }
            
            public CommandsInterpreter_Broadcast.Builder getInterpreterMessageBuilder() {
                this.bitField0_ |= 0x40;
                this.onChanged();
                return (CommandsInterpreter_Broadcast.Builder)this.e().getBuilder();
            }
            
            public CommandsInterpreter_BroadcastOrBuilder getInterpreterMessageOrBuilder() {
                try {
                    if (this.interpreterMessageBuilder_ != null) {
                        return (CommandsInterpreter_BroadcastOrBuilder)this.interpreterMessageBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.interpreterMessage_;
            }
            
            private SingleFieldBuilder<CommandsInterpreter_Broadcast, CommandsInterpreter_Broadcast.Builder, CommandsInterpreter_BroadcastOrBuilder> e() {
                try {
                    if (this.interpreterMessageBuilder_ == null) {
                        this.interpreterMessageBuilder_ = (SingleFieldBuilder<CommandsInterpreter_Broadcast, CommandsInterpreter_Broadcast.Builder, CommandsInterpreter_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.interpreterMessage_, this.getParentForChildren(), this.isClean());
                        this.interpreterMessage_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.interpreterMessageBuilder_;
            }
            
            public boolean hasTargetProcessOutput() {
                try {
                    if ((this.bitField0_ & 0x80) == 0x80) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public TargetProcessOutput_Broadcast getTargetProcessOutput() {
                try {
                    if (this.targetProcessOutputBuilder_ == null) {
                        return this.targetProcessOutput_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (TargetProcessOutput_Broadcast)this.targetProcessOutputBuilder_.getMessage();
            }
            
            public Builder setTargetProcessOutput(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.targetProcessOutputBuilder_ != null) {
                                    break Label_0042;
                                }
                                final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast2 = targetProcessOutput_Broadcast;
                                if (targetProcessOutput_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast2 = targetProcessOutput_Broadcast;
                                if (targetProcessOutput_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.targetProcessOutput_ = targetProcessOutput_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.targetProcessOutputBuilder_.setMessage((GeneratedMessage)targetProcessOutput_Broadcast);
                }
                this.bitField0_ |= 0x80;
                return this;
            }
            
            public Builder setTargetProcessOutput(final TargetProcessOutput_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.targetProcessOutputBuilder_ == null) {
                            this.targetProcessOutput_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.targetProcessOutputBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }
            
            public Builder mergeTargetProcessOutput(final TargetProcessOutput_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       82
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: sipush          128
                //    14: iand           
                //    15: sipush          128
                //    18: if_icmpne       70
                //    21: goto            28
                //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    27: athrow         
                //    28: aload_0        
                //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    35: if_acmpeq       70
                //    38: goto            45
                //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    44: athrow         
                //    45: aload_0        
                //    46: aload_0        
                //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast$Builder;
                //    53: aload_1        
                //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast$Builder;
                //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    63: goto            75
                //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    69: athrow         
                //    70: aload_0        
                //    71: aload_1        
                //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$TargetProcessOutput_Broadcast;
                //    75: aload_0        
                //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    79: goto            91
                //    82: aload_0        
                //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.targetProcessOutputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    86: aload_1        
                //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    90: pop            
                //    91: aload_0        
                //    92: dup            
                //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    96: sipush          128
                //    99: ior            
                //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   103: aload_0        
                //   104: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      21     24     28     Ljava/lang/NullPointerException;
                //  7      38     41     45     Ljava/lang/NullPointerException;
                //  28     66     66     70     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearTargetProcessOutput() {
                Label_0033: {
                    try {
                        if (this.targetProcessOutputBuilder_ == null) {
                            this.targetProcessOutput_ = TargetProcessOutput_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.targetProcessOutputBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }
            
            public TargetProcessOutput_Broadcast.Builder getTargetProcessOutputBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return (TargetProcessOutput_Broadcast.Builder)this.k().getBuilder();
            }
            
            public TargetProcessOutput_BroadcastOrBuilder getTargetProcessOutputOrBuilder() {
                try {
                    if (this.targetProcessOutputBuilder_ != null) {
                        return (TargetProcessOutput_BroadcastOrBuilder)this.targetProcessOutputBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.targetProcessOutput_;
            }
            
            private SingleFieldBuilder<TargetProcessOutput_Broadcast, TargetProcessOutput_Broadcast.Builder, TargetProcessOutput_BroadcastOrBuilder> k() {
                try {
                    if (this.targetProcessOutputBuilder_ == null) {
                        this.targetProcessOutputBuilder_ = (SingleFieldBuilder<TargetProcessOutput_Broadcast, TargetProcessOutput_Broadcast.Builder, TargetProcessOutput_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.targetProcessOutput_, this.getParentForChildren(), this.isClean());
                        this.targetProcessOutput_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.targetProcessOutputBuilder_;
            }
            
            public boolean hasModulesLoaded() {
                try {
                    if ((this.bitField0_ & 0x100) == 0x100) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public ModulesLoaded_Broadcast getModulesLoaded() {
                try {
                    if (this.modulesLoadedBuilder_ == null) {
                        return this.modulesLoaded_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ModulesLoaded_Broadcast)this.modulesLoadedBuilder_.getMessage();
            }
            
            public Builder setModulesLoaded(final ModulesLoaded_Broadcast modulesLoaded_Broadcast) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.modulesLoadedBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ModulesLoaded_Broadcast modulesLoaded_Broadcast2 = modulesLoaded_Broadcast;
                                if (modulesLoaded_Broadcast2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ModulesLoaded_Broadcast modulesLoaded_Broadcast2 = modulesLoaded_Broadcast;
                                if (modulesLoaded_Broadcast2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.modulesLoaded_ = modulesLoaded_Broadcast;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.modulesLoadedBuilder_.setMessage((GeneratedMessage)modulesLoaded_Broadcast);
                }
                this.bitField0_ |= 0x100;
                return this;
            }
            
            public Builder setModulesLoaded(final ModulesLoaded_Broadcast.Builder builder) {
                Label_0038: {
                    try {
                        if (this.modulesLoadedBuilder_ == null) {
                            this.modulesLoaded_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.modulesLoadedBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x100;
                return this;
            }
            
            public Builder mergeModulesLoaded(final ModulesLoaded_Broadcast p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoadedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       82
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    11: sipush          256
                //    14: iand           
                //    15: sipush          256
                //    18: if_icmpne       70
                //    21: goto            28
                //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    27: athrow         
                //    28: aload_0        
                //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoaded_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    35: if_acmpeq       70
                //    38: goto            45
                //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    44: athrow         
                //    45: aload_0        
                //    46: aload_0        
                //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoaded_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast$Builder;
                //    53: aload_1        
                //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast$Builder;
                //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoaded_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    63: goto            75
                //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    69: athrow         
                //    70: aload_0        
                //    71: aload_1        
                //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoaded_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ModulesLoaded_Broadcast;
                //    75: aload_0        
                //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.onChanged:()V
                //    79: goto            91
                //    82: aload_0        
                //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.modulesLoadedBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    86: aload_1        
                //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    90: pop            
                //    91: aload_0        
                //    92: dup            
                //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //    96: sipush          256
                //    99: ior            
                //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.bitField0_:I
                //   103: aload_0        
                //   104: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      21     24     28     Ljava/lang/NullPointerException;
                //  7      38     41     45     Ljava/lang/NullPointerException;
                //  28     66     66     70     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearModulesLoaded() {
                Label_0033: {
                    try {
                        if (this.modulesLoadedBuilder_ == null) {
                            this.modulesLoaded_ = ModulesLoaded_Broadcast.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.modulesLoadedBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }
            
            public ModulesLoaded_Broadcast.Builder getModulesLoadedBuilder() {
                this.bitField0_ |= 0x100;
                this.onChanged();
                return (ModulesLoaded_Broadcast.Builder)this.h().getBuilder();
            }
            
            public ModulesLoaded_BroadcastOrBuilder getModulesLoadedOrBuilder() {
                try {
                    if (this.modulesLoadedBuilder_ != null) {
                        return (ModulesLoaded_BroadcastOrBuilder)this.modulesLoadedBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.modulesLoaded_;
            }
            
            private SingleFieldBuilder<ModulesLoaded_Broadcast, ModulesLoaded_Broadcast.Builder, ModulesLoaded_BroadcastOrBuilder> h() {
                try {
                    if (this.modulesLoadedBuilder_ == null) {
                        this.modulesLoadedBuilder_ = (SingleFieldBuilder<ModulesLoaded_Broadcast, ModulesLoaded_Broadcast.Builder, ModulesLoaded_BroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.modulesLoaded_, this.getParentForChildren(), this.isClean());
                        this.modulesLoaded_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.modulesLoadedBuilder_;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public interface CompositeBroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasProcessInterrupted();
        
        ProcessInterrupted_Broadcast getProcessInterrupted();
        
        ProcessInterrupted_BroadcastOrBuilder getProcessInterruptedOrBuilder();
        
        boolean hasProcessRunning();
        
        ProcessRunning_Broadcast getProcessRunning();
        
        ProcessRunning_BroadcastOrBuilder getProcessRunningOrBuilder();
        
        boolean hasProcessExited();
        
        ProcessExited_Broadcast getProcessExited();
        
        ProcessExited_BroadcastOrBuilder getProcessExitedOrBuilder();
        
        boolean hasLogMessage();
        
        LogMessage_Broadcast getLogMessage();
        
        LogMessage_BroadcastOrBuilder getLogMessageOrBuilder();
        
        boolean hasChangePrompt();
        
        ChangePrompt_Broadcast getChangePrompt();
        
        ChangePrompt_BroadcastOrBuilder getChangePromptOrBuilder();
        
        boolean hasReadyForCommand();
        
        ReadyForCommands_Broadcast getReadyForCommand();
        
        ReadyForCommands_BroadcastOrBuilder getReadyForCommandOrBuilder();
        
        boolean hasInterpreterMessage();
        
        CommandsInterpreter_Broadcast getInterpreterMessage();
        
        CommandsInterpreter_BroadcastOrBuilder getInterpreterMessageOrBuilder();
        
        boolean hasTargetProcessOutput();
        
        TargetProcessOutput_Broadcast getTargetProcessOutput();
        
        TargetProcessOutput_BroadcastOrBuilder getTargetProcessOutputOrBuilder();
        
        boolean hasModulesLoaded();
        
        ModulesLoaded_Broadcast getModulesLoaded();
        
        ModulesLoaded_BroadcastOrBuilder getModulesLoadedOrBuilder();
    }
    
    public interface CommandsInterpreter_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasMessage();
        
        String getMessage();
        
        ByteString getMessageBytes();
    }
    
    public interface ReadyForCommands_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasReady();
        
        int getReady();
    }
    
    public interface ChangePrompt_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasPrompt();
        
        String getPrompt();
        
        ByteString getPromptBytes();
    }
    
    public interface ModulesLoaded_BroadcastOrBuilder extends MessageOrBuilder
    {
        List<String> getModulesList();
        
        int getModulesCount();
        
        String getModules(final int p0);
        
        ByteString getModulesBytes(final int p0);
    }
    
    public interface LogMessage_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasVerbosity();
        
        Verbosity getVerbosity();
        
        boolean hasMessage();
        
        String getMessage();
        
        ByteString getMessageBytes();
    }
    
    public interface TargetProcessOutput_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasText();
        
        String getText();
        
        ByteString getTextBytes();
        
        boolean hasOutputType();
        
        Model.OutputType getOutputType();
    }
    
    public interface ProcessExited_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasExitCode();
        
        int getExitCode();
        
        boolean hasExitDescription();
        
        String getExitDescription();
        
        ByteString getExitDescriptionBytes();
    }
    
    public interface ProcessRunning_BroadcastOrBuilder extends MessageOrBuilder
    {
    }
    
    public interface ProcessInterrupted_BroadcastOrBuilder extends MessageOrBuilder
    {
        boolean hasThread();
        
        Model.LLDBThread getThread();
        
        Model.LLDBThreadOrBuilder getThreadOrBuilder();
        
        boolean hasFrame();
        
        Model.LLDBFrame getFrame();
        
        Model.LLDBFrameOrBuilder getFrameOrBuilder();
    }
}
