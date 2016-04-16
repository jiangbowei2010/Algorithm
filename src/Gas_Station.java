/*
 * Leetcode 134: https://leetcode.com/problems/gas-station/
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * Note: The solution is guaranteed to be unique.
 */
public class Gas_Station {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int tank = 0, min = 0, index = -1;
		for (int i = 0; i < gas.length; i++) {
			tank = tank + gas[i] - cost[i];
			if (tank < min) {
				min = tank;
				index = i;
			}
		}
		if (tank < 0)
			return -1;
		return index + 1;
	}
	
	public static void main(String[] args) {
		int[] gas = {4};
		int[] cost = {5};
		Gas_Station solution = new Gas_Station();
		System.out.println(solution.canCompleteCircuit(gas, cost));
		gas = new int[]{2, 3, 6, 1, 3};
		cost = new int[]{3, 1, 5, 1, 1};
		System.out.println(solution.canCompleteCircuit(gas, cost));
	}
}
