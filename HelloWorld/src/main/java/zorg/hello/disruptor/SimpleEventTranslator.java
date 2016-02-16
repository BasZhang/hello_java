package zorg.hello.disruptor;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;

public class SimpleEventTranslator implements EventTranslator<SimpleEvent> {
	SimpleEventTranslator translator = new SimpleEventTranslator();
	private RingBuffer<SimpleEvent> ringBuffer;

	@Override
	public void translateTo(SimpleEvent event, long sequence) {
	}
}
