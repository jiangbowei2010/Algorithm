
/*
 * http://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
 * Given a polygon and a point ‘p’, find if ‘p’ lies inside the polygon or not.
 * The points lying on the border are considered inside.
 */
public class Point_lies_inside_or_outside_a_polygon {
	public boolean insidePolygon(Point[] polygon, Point r) {
		if (polygon.length < 3)
			return false;
		int intersect = 0, n = polygon.length;
		for (int i = 0; i < n; i++) {
			Point p = polygon[i], q = polygon[(i + 1) % n];
			double d = direction(p, q, r);
			if (d == 0 && onSection(p, q, r))
				return true;
			// q.y > p.y clock wise; q.y < p.y then conter clock wise -- is on left side of p-->q
			if (((d > 0 && q.y > p.y) || (d < 0 && q.y < p.y)) && r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y))
				intersect++;
		}
		return intersect % 2 == 1;
	}

	private double direction(Point p, Point q, Point r) {
		return (q.x - p.x) * (r.y - p.y) - (r.x - p.x) * (q.y - p.y);
	}

	private boolean onSection(Point p, Point q, Point r) {
		double minX = Math.min(p.x, q.x), maxX = Math.max(p.x, q.x);
		double minY = Math.min(p.y, q.y), maxY = Math.max(p.y, q.y);
		return r.x <= maxX && r.x >= minX && r.y <= maxY && r.y >= minY;
	}
	
	public static void main(String[] args) {
		Point_lies_inside_or_outside_a_polygon solution = new Point_lies_inside_or_outside_a_polygon();
		Point[] polygon = {new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10)};
		System.out.println(solution.insidePolygon(polygon, new Point(20, 20))); //false
		System.out.println(solution.insidePolygon(polygon, new Point(5, 5))); //true
		polygon = new Point[]{new Point(0, 0), new Point(5, 5), new Point(5, 0)};
		System.out.println(solution.insidePolygon(polygon, new Point(3, 3))); //true
		System.out.println(solution.insidePolygon(polygon, new Point(5, 1))); //true
		System.out.println(solution.insidePolygon(polygon, new Point(8, 1))); //false
	}
}
