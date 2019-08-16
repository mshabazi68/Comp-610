
//mohammadreza shahbazi 

//stable marrage probelm 

// project1 

import java.util.Scanner;

import java.io.File;

import java.io.FileNotFoundException;

public class Project1 {

	public static int maxPeopleResult;

	/*
	 * Here in this function only swaps two elements of the array in place
	 */

	public static void swap(int[] arr, int i, int j) {

		int temp = arr[i];

		arr[i] = arr[j];

		arr[j] = temp;

	}

	/*
	 * 
	 * Here in this functions checks for every two persons (of opposite sex) that
	 * 
	 * are not engaged with each other according to the definition, if they prefer
	 * 
	 * each other more than their current match then this match is not stable, we
	 * 
	 * assume that i-th man is matched with perm[i]-th woman
	 */

	public static boolean isStable(int n, int[][] man, int[][] woman, int[] perm) {

		int manPerson;

		int womanPerson;

		int currentMan;

		int currentWoman;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				// if they are not engaged already

				// then we have to check whether they prefer each other

				// more than their current match?

				if (perm[i] != j) {

					manPerson = i;

					womanPerson = j;

					// likeXMoreThanY(int n, int woman[][], int womanPerson, int x, int y)

					currentMan = -1;

					for (int k = 0; k < n; k++) {

						if (perm[k] == womanPerson) {

							currentMan = k;

							break;

						}

					}

					currentWoman = perm[manPerson];

					if (likeXMoreThanY(n, woman, womanPerson, manPerson, currentMan)

							&& likeXMoreThanY(n, man, manPerson, womanPerson, currentWoman)) {

						return false;

					}

				}

			}

		}

		return true;
	}

	/*
	 * 
	 * Here in this function generates all permutaitons and when a permutation is
	 * 
	 * found it checks using method "isStable", if the permutation is valid
	 * 
	 * (stable), then counts the number of men and women that got their first choice
	 * 
	 * the answer is updated in variable named "maxPeopleResult"
	 */

	public static void check(int n, int[][] man, int[][] woman, int[] perm, int l, int r) {

		if (l == r) {

			// for(int i = 0; i < n; i ++) {

			// System.out.print("-" + perm[i]);

			// }

			// System.out.println("");

			if (isStable(n, man, woman, perm)) {

				int firstChoice = 0;

				for (int i = 0; i < n; i++) {

					if (man[i][0] == perm[i]) {

						firstChoice++;

					}

				}
				for (int i = 0; i < n; i++) {
					int womanPerson = perm[i];

					if (woman[womanPerson][0] == i) {

						firstChoice++;

					}

				}

				if (firstChoice > maxPeopleResult) {

					maxPeopleResult = firstChoice;
				}

			}

		} else {

			for (int i = l; i <= r; i++) {

				swap(perm, l, i);

				check(n, man, woman, perm, l + 1, r);
				swap(perm, l, i);

			}

		}

	}

	/*
	 * 
	 * This function only initializes perm array and calls "check" method
	 */

	public static int maxPeople(int n, int[][] man, int[][] woman) {

		int[] perm = new int[n];

		for (int i = 0; i < n; i++) {

			perm[i] = i;

		}

		maxPeopleResult = -1;

		check(n, man, woman, perm, 0, n - 1);

		return maxPeopleResult;
	}

	public static int stableMatching(int n, int man[][], int woman[][]) {

		boolean[] manFree = new boolean[n];

		boolean[] womanFree = new boolean[n];

		int[] manCurrentPrefIndex = new int[n];

		int[] womanCurrentMan = new int[n];

		for (int i = 0; i < n; i++) {

			manFree[i] = womanFree[i] = true;

			manCurrentPrefIndex[i] = -1;

			womanCurrentMan[i] = -1;

		}

		int manMatched = 0;

		while (manMatched < n) {

			int manPerson = -1;

			for (int i = 0; i < n; i++) {

				if (manFree[i] == true) {

					manPerson = i;

					break;

				}

			}

			manCurrentPrefIndex[manPerson]++;

			while (true) {

				int womanIndex = manCurrentPrefIndex[manPerson];

				int womanPerson = man[manPerson][womanIndex];

				if (womanFree[womanPerson] == true) {

					womanCurrentMan[womanPerson] = manPerson;

					womanFree[womanPerson] = false;

					manFree[manPerson] = false;
					manMatched++;

					break;

				} else {

					int currentMan = womanCurrentMan[womanPerson];

					if (likeXMoreThanY(n, woman, womanPerson, manPerson, currentMan)) {

						manFree[manPerson] = false;

						manFree[currentMan] = true;

						womanCurrentMan[womanPerson] = manPerson;

						break;

					} else {

						manCurrentPrefIndex[manPerson]++;

					}

				}

			}

		}

		int firstChoice = 0;

		for (int i = 0; i < n; i++) {

			if (manCurrentPrefIndex[i] == 0) {

				firstChoice++;

			}

		}

		return firstChoice;
	}

	public static boolean likeXMoreThanY(int n, int woman[][], int womanPerson, int x, int y) {
		for (int i = 0; i < n; i++) {

			if (woman[womanPerson][i] == x) {

				return true;

			}

			if (woman[womanPerson][i] == y) {

				return false;

			}

		}

		return false;

	}

	// driver method that we read the file and call the stable maching methods.
	public static void main(String[] args) throws FileNotFoundException {

		int n;
		int[][] man;

		int[][] woman;

		// Scanner scanner = new Scanner(System.in);

		Scanner scanner = new Scanner(new File("input1.txt"));

		n = scanner.nextInt();
		man = new int[n][n];

		woman = new int[n][n];
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				int womanPerson = scanner.nextInt();
				womanPerson--;

				man[i][j] = womanPerson;
			}

		}

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				int manPerson = scanner.nextInt();
				manPerson--;

				woman[i][j] = manPerson;

			}

		}

		System.out.println(stableMatching(n, man, woman));

		System.out.println(stableMatching(n, woman, man));

		System.out.println(maxPeople(n, man, woman));

	}

}
