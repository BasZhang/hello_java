package zorg.hello.disruptor;

import com.lmax.disruptor.EventFactory;

public class SimpleEvent {
	public static final EventFactory<SimpleEvent> EVENT_FACTORY = new SimpleEventFactory();
	private volatile String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static EventFactory<SimpleEvent> getEventFactory() {
		return EVENT_FACTORY;
	}

	private static class SimpleEventFactory implements
			EventFactory<SimpleEvent> {
		@Override
		public SimpleEvent newInstance() {
			return new SimpleEvent();
		}
	}
}