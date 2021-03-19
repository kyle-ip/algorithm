package leetcode134

func canCompleteCircuit(gas []int, cost []int) int {
	total, tank, start := 0, 0, 0
	for i := 0; i < len(gas); i++ {
		total += gas[i] - cost[i]
		tank += gas[i] - cost[i]
		if tank < 0 {
			tank = 0
			start = i + 1
		}
	}
	if total < 0 {
		return -1
	} else {
		return start
	}
}