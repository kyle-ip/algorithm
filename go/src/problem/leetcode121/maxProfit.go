package leetcode121

func maxProfit(prices []int) int {
	if len(prices) < 2 {
		return 0;
	}
	ret, buy := 0, prices[0]
	for _, price := range prices {
		if price < buy {
			buy = price
		} else {
			ret = max(ret, price - buy)
		}
	}
	return ret
}

func max(num1, num2 int) int {
	if num1 > num2 {
		return num1
	}
	return num2
}