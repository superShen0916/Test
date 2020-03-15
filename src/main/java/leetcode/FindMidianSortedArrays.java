package leetcode;

/**
 * @Description:
 *               给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 *               请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 *               你可以假设 nums1 和 nums2 不会同时为空。
 *
 *               示例 1:
 *
 *               nums1 = [1, 3]
 *               nums2 = [2]
 *
 *               则中位数是 2.0
 *               示例 2:
 *
 *               nums1 = [1, 2]
 *               nums2 = [3, 4]
 *
 *               则中位数是 (2 + 3)/2 = 2.5
 *
 * 
 * @Author: shenpeng
 * @Date: 2019-12-31
 */
public class FindMidianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] allNums = new int[nums1.length + nums2.length];
        int max = allNums.length / 2;
        int index1 = 0, index2 = 0;
        for (int i = 0; i < allNums.length; i++) {
            if (index2 >= nums2.length) {
                allNums[i] = nums1[index1++];
            } else if (index1 >= nums1.length) {
                allNums[i] = nums2[index2++];
            } else if (nums1[index1] <= nums2[index2]) {
                allNums[i] = nums1[index1++];
            } else {
                allNums[i] = nums2[index2++];
            }
        }
        if (allNums.length == 1) {
            return allNums[0];
        }
        if (allNums.length % 2 == 0) {
            return 1.0 * (allNums[allNums.length / 2] + allNums[allNums.length / 2 - 1]) / 2;
        } else {
            return allNums[allNums.length / 2];
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        if (nums1.length < 1) {
            if (nums2.length % 2 == 0) {
                return (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2;
            } else {
                return nums2[nums2.length / 2];
            }
        }

        int index1 = nums1.length / 2;
        int index2 = nums2.length / 2;
        while (index1 != 0 && index1 != nums1.length) {

        }

        if (nums1[index1] == nums2[index2]) {
            if ((nums1.length % 2 == 0) && (nums2.length % 2 == 0)) {
                int value2 = Math.max(nums1[index1 - 1], nums2[index2 - 1]);
                return 1.0 * (value2 + nums1[index1]) / 2;
            } else {
                return nums1[index1];
            }
        } else if (nums1[index1] < nums2[index2]) {

        }
        return 1;//sptodo
    }

    public double findMedian(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return findMedianSortedArrays3(B, A); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMax + iMin) / 2;
            int j = (m + n + 1) / 2;
            if (j != 0 && i != m && B[j - 1] > A[i]) {
                iMin = i + 1;
            } else if (i != 0 && j != n && A[i - 1] > B[j]) {
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if (m + n % 2 == 0) {
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == m) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return findMedianSortedArrays3(B, A); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && B[j - 1] > A[i]) { // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && A[i - 1] > B[j]) { // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] A = { 1, 2 };
        int[] B = { 3, 4, 5 };
        System.out.println(findMedianSortedArrays3(A, B));
    }

    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int pre = 0;
        int right = 0;
        int mid = len / 2;
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i <= mid; i++) {
            pre = right;
            if (index1 < nums1.length
                    && (index2 >= nums2.length || nums1[index1] < nums2[index2])) {
                right = nums1[index1];
                index1++;
            } else {
                right = nums2[index2];
                index2++;
            }
        }
        double result = 0;
        if (len % 2 == 1) {
            result = right;
        } else {
            result = (right + pre) / 2.0;
        }
        return result;
    }

    //    public double findMedianK(int[] nums1, int[] nums2) {
    //        if (nums1.length > nums2.length) {
    //            return findMedianK(nums2, nums1);
    //        }
    //        int totalSize = nums1.length + nums2.length;
    //        if (totalSize % 2 == 0) {
    //            return (findK(nums1, nums2, (totalSize + 1) / 2)
    //                    + findK(nums1, nums2, (totalSize + 2) / 2)) / 2;
    //        } else {
    //            return findK(nums1, nums2, (totalSize + 1) / 2);
    //        }
    //
    //    }
    //
    //    public double findK(int[] nums1, int[] nums2, int K) {
    //        int step1, step2;
    //        int start1 = 0;
    //        int start2 = 0;
    //        while (K > 0) {
    //            step1 = Math.min(nums1.length - 1 - start1, K / 2);
    //            step2 = K - step1 - 1;
    //            if ((start2 + step2) > (nums2.length - 1)) {
    //                step2 = nums2.length - 1 - start2;
    //            }
    //            if (nums1[start1 + step1] > nums2[start2 + step2]) {
    //                if (step2 == 0) {
    //                    return nums1[start2 + step2];
    //                } else {
    //
    //                }
    //            } else if (nums1[start1 + step1] < nums2[start2 + step2]) {
    //                if (step1 == 0) {
    //                    return nums1[start1 + step1];
    //                }
    //                start1 = start1 + step1;
    //                K = K - step1;
    //                step1 = Math.min(nums1.length - 1 - start1, halfK);
    //                step2 = Math.min(nums2.length - 1 - start2, K - step1);
    //            } else {
    //                start1 = start1 + index1;
    //                start2 = start2 + index2;
    //                break;
    //            }
    //
    //        }
    //    }

}
