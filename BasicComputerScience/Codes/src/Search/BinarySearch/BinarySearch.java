package Search.BinarySearch;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, 3, 4, 5};

        System.out.println(binarySearch(nums, 2));
    }

    public static int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while(l <= r) {
            int mid = (l + r) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }
}
