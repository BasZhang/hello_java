package zorg.hello.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class CodeExampleDisruptor2x {
	final EventHandler<SimpleEvent> handler = new EventHandler<SimpleEvent>() {
		public void onEvent(final SimpleEvent event, final long sequence,
				final boolean endOfBatch) throws Exception {
			// process a new event.
		}
	};
	private int RING_SIZE = 1 << 4;
	private final ExecutorService EXECUTOR = Executors
			.newScheduledThreadPool(1);

	private void ringbuffer2x() {
		RingBuffer<SimpleEvent> ringBuffer = RingBuffer.create(
				ProducerType.SINGLE, SimpleEvent.EVENT_FACTORY, RING_SIZE,
				new SleepingWaitStrategy());

		SequenceBarrier barrier = ringBuffer.newBarrier();
		BatchEventProcessor<SimpleEvent> eventProcessor = new BatchEventProcessor<SimpleEvent>(
				ringBuffer, barrier, handler);
		ringBuffer.addGatingSequences(eventProcessor.getSequence());

		// Each EventProcessor can run on a separate thread
		EXECUTOR.submit(eventProcessor);
		// Publishers claim events in sequence
		long sequence = ringBuffer.next();
		SimpleEvent event = ringBuffer.get(sequence);

		event.setValue("1234"); // this could be more complex with multiple
								// fields

		// make the event available to EventProcessors
		ringBuffer.publish(sequence);
	}

	private void disrupt2x() {
		Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(
				SimpleEvent.EVENT_FACTORY, RING_SIZE, EXECUTOR,
				ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWith(handler);
		RingBuffer<SimpleEvent> ringBuffer = disruptor.start();
		disruptor.publishEvent(new SimpleEventTranslator());

	}

	public static void main(String[] args) {
	}
}
