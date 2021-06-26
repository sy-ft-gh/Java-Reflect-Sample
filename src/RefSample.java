public class RefSample {
	public final int MIN_TIMES = 0;
	private int times = 0;

	public RefSample(int t) {
		this.setTimes(t);
	}
	private void checkTimeInvalid(int t) {
		if (t < MIN_TIMES) throw new IllegalArgumentException("Illegal time value");
	}
	public void setTimes(int t) {
		checkTimeInvalid(t);
		this.times = t;
	}
	public void hello(String msg) {
		this.hello(msg, this.times);
	}
	public void hello(String msg, int t) {
		System.out.println("Hello, " + msg + " x" + t);
	}
}
