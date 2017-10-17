// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.ipcUtils;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.execution.ExecutionFinishedException;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Conditions;
import java.util.ArrayList;
import java.util.Iterator;
import com.intellij.openapi.application.ApplicationManager;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.util.Collection;
import java.nio.ByteOrder;
import java.io.IOException;
import java.nio.ByteBuffer;
import com.intellij.util.Producer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Semaphore;
import java.util.List;
import com.intellij.util.containers.Queue;
import com.intellij.util.Consumer;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.concurrency.QueueProcessor;
import java.util.concurrent.atomic.AtomicInteger;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Model;
import com.intellij.openapi.diagnostic.Logger;
import com.google.protobuf.GeneratedMessage;

public class ProtobufServer<T extends GeneratedMessage>
{
    private static final Logger LOG;
    private static final Model.Initialized_Message INITIALIZED_MESSAGE;
    private static final AtomicInteger ourCount;
    private final int myPort;
    private long myDefaultTimeout;
    @NotNull
    private final QueueProcessor<GeneratedMessage> myInboxProcessor;
    @NotNull
    private final QueueProcessor<Pair<GeneratedMessage, Consumer<GeneratedMessage>>> myOutboxProcessor;
    private final Queue<Pair<Consumer, Class>> myResponseHandlers;
    private final Object mySocketLock;
    private final List<Semaphore> myToRelease;
    private final int myUid;
    private final long myInitializationTime;
    private SocketChannel mySocketChannel;
    private ServerSocketChannel myServerSocket;
    private volatile boolean myCancelAcceptAttempts;
    private ProtobufParser<T> myResponseParser;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public int getPort() {
        return this.myPort;
    }
    
    private void a(final SocketChannel socketChannel) throws IOException {
        try {
            ByteBuffer byteBuffer = a(66560);
            while (true) {
                final int read = socketChannel.read(byteBuffer);
                try {
                    if (read == -1) {
                        break;
                    }
                }
                catch (ClosedChannelException ex) {
                    throw b(ex);
                }
                try {
                    if (byteBuffer.position() == 0) {
                        continue;
                    }
                }
                catch (ClosedChannelException ex2) {
                    throw b(ex2);
                }
                int position = 0;
                final int position2 = byteBuffer.position();
                byteBuffer.rewind();
                int int1 = 0;
                while (true) {
                    Label_0084: {
                        try {
                            if (byteBuffer.position() >= position2) {
                                break;
                            }
                            final int n = position2;
                            final ByteBuffer byteBuffer2 = byteBuffer;
                            final int n2 = byteBuffer2.position();
                            final int n3 = n - n2;
                            final int n4 = 4;
                            if (n3 < n4) {
                                break Label_0084;
                            }
                            break Label_0084;
                        }
                        catch (ClosedChannelException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final int n = position2;
                            final ByteBuffer byteBuffer2 = byteBuffer;
                            final int n2 = byteBuffer2.position();
                            final int n3 = n - n2;
                            final int n4 = 4;
                            if (n3 < n4) {
                                break;
                            }
                        }
                        catch (ClosedChannelException ex4) {
                            throw b(ex4);
                        }
                    }
                    int1 = byteBuffer.getInt();
                    try {
                        if (position2 - byteBuffer.position() < int1) {
                            break;
                        }
                    }
                    catch (ClosedChannelException ex5) {
                        throw b(ex5);
                    }
                    final byte[] array = new byte[int1];
                    byteBuffer.get(array);
                    final GeneratedMessage parse = this.myResponseParser.parse(array);
                    this.a((Producer<String>)(() -> "res:" + parse));
                    this.myInboxProcessor.add((Object)a(parse, this.myResponseParser));
                    position = byteBuffer.position();
                }
                final int n5 = position2 - position;
                byteBuffer.position(position);
                final byte[] array2 = new byte[n5];
                byteBuffer.get(array2);
                if (int1 > byteBuffer.capacity()) {
                    byteBuffer = a(int1 + 4);
                }
                byteBuffer.rewind();
                byteBuffer.put(array2);
            }
        }
        catch (ClosedChannelException ex6) {}
    }
    
    private static ByteBuffer a(final int n) {
        final ByteBuffer allocate = ByteBuffer.allocate(n);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        return allocate;
    }
    
    public void setDefaultTimeout(final long myDefaultTimeout) {
        this.myDefaultTimeout = myDefaultTimeout;
    }
    
    @NotNull
    private static <T extends GeneratedMessage> GeneratedMessage a(@NotNull final GeneratedMessage generatedMessage, final ProtobufParser<T> protobufParser) {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compositeResponse", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "unpackComposite"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0101: {
            GeneratedMessage generatedMessage2 = null;
            Label_0066: {
                try {
                    if (protobufParser.decompose(generatedMessage)) {
                        break Label_0101;
                    }
                    generatedMessage2 = generatedMessage;
                    if (generatedMessage2 == null) {
                        break Label_0066;
                    }
                    return generatedMessage2;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    generatedMessage2 = generatedMessage;
                    if (generatedMessage2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "unpackComposite"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            return generatedMessage2;
        }
        final Collection<GeneratedMessage> values = generatedMessage.getAllFields().values();
        Label_0136: {
            try {
                if (ProtobufServer.$assertionsDisabled) {
                    break Label_0136;
                }
                final Collection<GeneratedMessage> collection = values;
                final int n = collection.size();
                final boolean b = true;
                if (n != (b ? 1 : 0)) {
                    break Label_0136;
                }
                break Label_0136;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final Collection<GeneratedMessage> collection = values;
                final int n = collection.size();
                final boolean b = true;
                if (n != (b ? 1 : 0)) {
                    throw new AssertionError((Object)"More than 1 message packed in one composite response");
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        final GeneratedMessage generatedMessage3 = values.iterator().next();
        GeneratedMessage a;
        try {
            a = a(generatedMessage3, (ProtobufParser<GeneratedMessage>)protobufParser);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "unpackComposite"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return a;
    }
    
    private boolean b(@NotNull final GeneratedMessage generatedMessage) {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "generatedMessage", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "doSendMessage"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        synchronized (this.mySocketLock) {
            if (this.mySocketChannel == null) {
                return false;
            }
            final byte[] byteArray = generatedMessage.toByteArray();
            final ByteBuffer a = a(byteArray.length + 4);
            a.putInt(byteArray.length);
            a.put(byteArray);
            a.rewind();
            this.a((Producer<String>)(() -> {
                try {
                    if (generatedMessage == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "generatedMessage", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "lambda$doSendMessage$1"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return "req(" + (byteArray.length + 4) + "): " + generatedMessage;
            }));
            try {
                this.mySocketChannel.write(a);
            }
            catch (IOException ex2) {
                return false;
            }
            return true;
        }
    }
    
    private void a(final Producer<String> producer) {
        if (ProtobufServer.LOG.isDebugEnabled()) {
            ProtobufServer.LOG.debug(("[protobuf client " + this.myUid + "] " + (System.currentTimeMillis() - this.myInitializationTime) + " " + (String)producer.produce()).trim());
        }
    }
    
    private int b() throws IOException {
        synchronized (this.mySocketLock) {
            (this.myServerSocket = ServerSocketChannel.open()).configureBlocking(false);
            this.myServerSocket.socket().bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0));
            final int localPort = this.myServerSocket.socket().getLocalPort();
            SocketChannel mySocketChannel;
            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                try {
                    synchronized (this.mySocketLock) {
                        while (true) {
                            Label_0031_1: {
                                try {
                                    if (this.mySocketChannel == null) {
                                        if (!this.myCancelAcceptAttempts) {
                                            break Label_0031_1;
                                        }
                                    }
                                    else {
                                        break;
                                    }
                                }
                                catch (InterruptedException ex) {
                                    throw b(ex);
                                }
                                return;
                            }
                            this.mySocketChannel = this.myServerSocket.accept();
                            try {
                                Thread.sleep(5L);
                                continue;
                            }
                            catch (InterruptedException ex3) {
                                return;
                            }
                            break;
                        }
                        mySocketChannel = this.mySocketChannel;
                    }
                    this.myInboxProcessor.add((Object)ProtobufServer.INITIALIZED_MESSAGE);
                    this.a(mySocketChannel);
                }
                catch (IOException ex2) {
                    this.handleIOException(ex2);
                }
                return;
            });
            return localPort;
        }
    }
    
    protected void handleIOException(final IOException ex) {
    }
    
    public void tearDown() {
        this.releaseAll();
        this.myCancelAcceptAttempts = true;
        synchronized (this.mySocketLock) {
            try {
                if (this.mySocketChannel != null) {
                    this.mySocketChannel.close();
                }
                try {
                    if (this.myServerSocket != null) {
                        this.myServerSocket.close();
                    }
                }
                catch (IOException ex) {
                    throw b(ex);
                }
            }
            catch (IOException ex2) {}
            finally {
                this.mySocketChannel = null;
                this.myServerSocket = null;
            }
        }
    }
    
    public void releaseAll() {
        this.myInboxProcessor.clear();
        this.myOutboxProcessor.clear();
        synchronized (this.myToRelease) {
            final Iterator<Semaphore> iterator = this.myToRelease.iterator();
            while (iterator.hasNext()) {
                iterator.next().release();
            }
        }
    }
    
    public ProtobufServer(@NotNull final Consumer<GeneratedMessage> consumer, final ProtobufParser<T> myResponseParser) throws IOException {
        if (consumer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inboxConsumer", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "<init>"));
        }
        this.myDefaultTimeout = 30000L;
        this.myResponseHandlers = (Queue<Pair<Consumer, Class>>)new Queue(100);
        this.mySocketLock = new Object();
        this.myToRelease = new ArrayList<Semaphore>();
        this.myUid = ProtobufServer.ourCount.incrementAndGet();
        this.myInitializationTime = System.currentTimeMillis();
        this.myResponseParser = myResponseParser;
        this.myInboxProcessor = (QueueProcessor<GeneratedMessage>)new QueueProcessor(generatedMessage -> {
            try {
                if (consumer == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inboxConsumer", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "lambda$new$3"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            synchronized (this.myResponseHandlers) {
                final Pair pair = this.myResponseHandlers.isEmpty() ? null : ((Pair)this.myResponseHandlers.peekFirst());
                Label_0126: {
                    try {
                        if (pair == null || pair.second != generatedMessage.getClass()) {
                            break Label_0126;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    ((Consumer)pair.first).consume((Object)generatedMessage);
                    this.myResponseHandlers.pullFirst();
                    return;
                }
            }
            consumer.consume((Object)generatedMessage);
        }, Conditions.alwaysFalse());
        this.myOutboxProcessor = (QueueProcessor<Pair<GeneratedMessage, Consumer<GeneratedMessage>>>)new QueueProcessor(pair -> {
            final boolean b = this.b((GeneratedMessage)pair.first);
            Consumer consumer = null;
            Object o2 = null;
            Label_0052: {
                Label_0037: {
                    try {
                        if (pair.second == null) {
                            return;
                        }
                        final Pair pair2 = pair;
                        final Object o = pair2.second;
                        consumer = (Consumer)o;
                        final boolean b2 = b;
                        if (b2) {
                            break Label_0037;
                        }
                        break Label_0037;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final Pair pair2 = pair;
                        final Object o = pair2.second;
                        consumer = (Consumer)o;
                        final boolean b2 = b;
                        if (b2) {
                            o2 = pair.first;
                            break Label_0052;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                o2 = null;
            }
            consumer.consume(o2);
        }, Conditions.alwaysFalse());
        this.myPort = this.b();
    }
    
    public <T extends GeneratedMessage> void sendMessageAndWaitUntilSent(final GeneratedMessage generatedMessage, @Nullable final Class<T> clazz, @Nullable final Consumer<T> consumer) throws ProtobufTimedOutException {
        this.sendMessageAndWaitUntilSent(generatedMessage, clazz, consumer, this.myDefaultTimeout);
    }
    
    public <T extends GeneratedMessage> void sendMessageAndWaitUntilSent(final GeneratedMessage generatedMessage, @Nullable final Class<T> clazz, @Nullable final Consumer<T> consumer, final long n) throws ProtobufTimedOutException {
        final Semaphore semaphore = new Semaphore(0);
        synchronized (this.myToRelease) {
            this.myToRelease.add(semaphore);
        }
        this.sendMessage(generatedMessage, clazz, consumer, (Consumer<GeneratedMessage>)(generatedMessage -> semaphore.release()));
        try {
            if (!semaphore.tryAcquire(n, TimeUnit.MILLISECONDS)) {
                throw new ProtobufTimedOutException();
            }
        }
        catch (InterruptedException ex) {
            synchronized (this.myToRelease) {
                this.myToRelease.remove(semaphore);
            }
        }
        finally {
            synchronized (this.myToRelease) {
                this.myToRelease.remove(semaphore);
            }
        }
    }
    
    public <ResponseType extends GeneratedMessage> void sendMessage(@NotNull final GeneratedMessage generatedMessage, @Nullable final Class<ResponseType> clazz, @Nullable final Consumer<ResponseType> consumer, @Nullable final Consumer<GeneratedMessage> consumer2) {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0097: {
            try {
                if (clazz == null || consumer == null) {
                    break Label_0097;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            synchronized (this.myResponseHandlers) {
                this.myResponseHandlers.addLast((Object)new Pair((Object)consumer, (Object)clazz));
            }
        }
        this.myOutboxProcessor.add((Object)Pair.create((Object)generatedMessage, (Object)consumer2));
    }
    
    public <ResponseType extends GeneratedMessage> void sendMessage(@NotNull final GeneratedMessage generatedMessage, @Nullable final Class<ResponseType> clazz, @Nullable final Consumer<ResponseType> consumer) {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.sendMessage(generatedMessage, clazz, consumer, null);
    }
    
    public <ResponseType extends GeneratedMessage> void sendMessageAndWaitForReply(@NotNull final GeneratedMessage generatedMessage, @NotNull final Class<ResponseType> clazz, @NotNull final Consumer<ResponseType> consumer, final long n) throws ProtobufTimedOutException, ExecutionFinishedException {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseClass", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (InterruptedException ex2) {
            throw b(ex2);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseHandler", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (InterruptedException ex3) {
            throw b(ex3);
        }
        final Semaphore semaphore = new Semaphore(0);
        synchronized (this.myToRelease) {
            this.myToRelease.add(semaphore);
        }
        final boolean[] array = { false };
        final boolean[] array2 = { false };
        synchronized (this.myResponseHandlers) {
            this.myResponseHandlers.addLast((Object)Pair.create((Object)(o -> {
                try {
                    if (consumer == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseHandler", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "lambda$sendMessageAndWaitForReply$6"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    if (!array[0]) {
                        consumer.consume((Object)o);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                semaphore.release();
            }), (Object)clazz));
        }
        this.myOutboxProcessor.add((Object)new Pair((Object)generatedMessage, (Object)(generatedMessage -> {
            try {
                if (generatedMessage == null) {
                    array2[0] = true;
                    semaphore.release();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        })));
        try {
            Label_0303: {
                if (n > 0L) {
                    try {
                        if (!semaphore.tryAcquire(n, TimeUnit.MILLISECONDS)) {
                            array[0] = true;
                            throw new ProtobufTimedOutException();
                        }
                        break Label_0303;
                    }
                    catch (InterruptedException ex4) {
                        throw b(ex4);
                    }
                }
                semaphore.acquire();
            }
        }
        catch (InterruptedException ex6) {
            array[0] = true;
            synchronized (this.myToRelease) {
                this.myToRelease.remove(semaphore);
            }
        }
        finally {
            synchronized (this.myToRelease) {
                this.myToRelease.remove(semaphore);
            }
        }
        try {
            if (array2[0]) {
                throw new ExecutionFinishedException();
            }
        }
        catch (InterruptedException ex5) {
            throw b(ex5);
        }
    }
    
    public <T extends GeneratedMessage> void sendMessageAndWaitForReply(@NotNull final GeneratedMessage generatedMessage, @NotNull final Class<T> clazz, @NotNull final Consumer<T> consumer) throws ProtobufTimedOutException, ExecutionFinishedException {
        try {
            if (generatedMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (ProtobufTimedOutException ex) {
            throw b((Exception)ex);
        }
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseClass", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (ProtobufTimedOutException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "responseHandler", "com/jetbrains/cidr/execution/ipcUtils/ProtobufServer", "sendMessageAndWaitForReply"));
            }
        }
        catch (ProtobufTimedOutException ex3) {
            throw b((Exception)ex3);
        }
        this.sendMessageAndWaitForReply(generatedMessage, clazz, consumer, 0L);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!ProtobufServer.class.desiredAssertionStatus()) {
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
        LOG = CidrDebuggerLog.LOG;
        INITIALIZED_MESSAGE = Model.Initialized_Message.getDefaultInstance();
        ourCount = new AtomicInteger(0);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public interface ProtobufParser<T extends GeneratedMessage>
    {
        T parse(final byte[] p0) throws IOException;
        
        boolean decompose(final GeneratedMessage p0);
    }
}
