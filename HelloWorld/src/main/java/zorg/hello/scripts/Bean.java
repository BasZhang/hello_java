package zorg.hello.scripts;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1594965227821061064L;
	private transient int f1;
	private transient double f2;

	public int getF1() {
		return f1;
	}

	public void setF1(int f1) {
		this.f1 = f1;
	}

	public double getF2() {
		return f2;
	}

	public void setF2(double f2) {
		this.f2 = f2;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeInt(f1);
		oos.writeDouble(f2);
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
		f1 = ois.readInt();
		f2 = ois.readDouble();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + f1;
		long temp;
		temp = Double.doubleToLongBits(f2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Bean other = (Bean) obj;
		if (f1 != other.f1) {
			return false;
		}
		if (Double.doubleToLongBits(f2) != Double.doubleToLongBits(other.f2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean [f1=").append(f1).append(", f2=").append(f2)
				.append("]");
		return builder.toString();
	}
}