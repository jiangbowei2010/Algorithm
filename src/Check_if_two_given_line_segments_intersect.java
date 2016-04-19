/*
 * http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
 * Given two line segments (p1, q1) and (p2, q2), find if the given line
 * segments intersect with each other.
 */
class Point {
	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

public class Check_if_two_given_line_segments_intersect {
	public boolean isIntersect(Point p1, Point q1, Point p2, Point q2) {
		double d1 = direction(p1, q1, p2);
		double d2 = direction(p1, q1, q2);
		double d3 = direction(p2, q2, p1);
		double d4 = direction(p2, q2, q1);
		if (((d1 < 0 && d2 > 0) || (d1 > 0 && d2 < 0)) && ((d3 < 0 && d4 > 0) || (d3 > 0 && d4 < 0)))
			return true;
		if (d1 == 0 && onSegment(p1, q1, p2))
			return true;
		if (d2 == 0 && onSegment(p1, q1, q2))
			return true;
		if (d3 == 0 && onSegment(p2, q2, p1))
			return true;
		if (d4 == 0 && onSegment(p2, q2, q1))
			return true;
		return false;
	}

	private double direction(Point p, Point q, Point r) {
		return (q.x - p.x) * (r.y - p.y) - (r.x - p.x) * (q.y - p.y);
	}

	private boolean onSegment(Point p, Point q, Point r) {
		double minX = Math.min(p.x, q.x);
		double maxX = Math.max(p.x, q.x);
		double minY = Math.min(p.y, q.y);
		double maxY = Math.max(p.y, q.y);
		return r.x <= maxX && r.x >= minX && r.y <= maxY && r.y >= minY;
	}
	
	public static void main(String[] args) {
		Check_if_two_given_line_segments_intersect solution = new Check_if_two_given_line_segments_intersect();
		System.out.println(solution.isIntersect(new Point(1, 1), new Point(2, 2), new Point(1, 2), new Point(2, 1)));
		System.out.println(solution.isIntersect(new Point(1, 1), new Point(2, 2), new Point(1, 2), new Point(2, 3)));
	}
}
