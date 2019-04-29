#include <vector>
#include <iostream>

void main() {

	std::vector<int> x = {1, 2, 9};
	std::vector<int> all_sums = {};
	int sum(0);

	for (int a(0); a <= 10; a++) {
		sum = 0;

		for (int i(0); i < 3; i++) {
			sum += (x[i] - a) ^ 2;
		}
		all_sums.push_back(sum);
	}

	for (int j(0); j < all_sums.size(); j++) {
		std::cout << all_sums[j] << " ";
	}
}