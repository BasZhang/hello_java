package zorg.hello.aspectj;

@SuppressWarnings("all")
aspect PointAssertions {

	private boolean Point.assertX(int x) {
		return (x <= 100 && x >= 0);
	}

	private boolean Point.assertY(int y) {
		return (y <= 100 && y >= 0);
	}

	before(Point p, int x): target(p) && args(x) && call(void setX(int)) {
		if (!p.assertX(x)) {
			System.out.println("Illegal value for x");
			return;
		}
	}

	before(Point p, int y): target(p) && args(y) && call(void setY(int)) {
		System.out.println("Entering " + thisJoinPoint + " in " + p);
		if (!p.assertY(y)) {
			System.out.println("Illegal value for y");
			return;
		}
	}
}