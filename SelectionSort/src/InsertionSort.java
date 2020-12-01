import java.util.Arrays;

public class InsertionSort {

    private InsertionSort() {}

    /*
     * 插入排序算法
     * 循环不变量：arr[0, i) 已完成排序，arr[i, n) 还未完成排序，和选择排序是一样的
     * 循环体：把 arr[i] 放到 arr[0, i) 中合适的位置，维持循环不变量
     * 复杂度：1+2+3+...+n = 1/2n^2 + 1/2n = n^2
     * */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }

            // 以上代码等价于
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
//                swap(arr, j, j - 1);
//            }
        }
    }

    // 插入排序的一个优化（较少元素交换位置，只是常数级别的优化）
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            // 先用一个临时变量暂存 arr[i]，再遍历 i 之前的所有元素，找出 arr[i] 应该存放的位置
            // 遍历过程中，只要比 arr[i] 大的元素，就全部向后平移一位，而不是每次都 swap
            E tmp = arr[i];
            // 存放最终 arr[i] 需要放置的索引
            int j;
            for (j = i; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n: dataSize) {

            System.out.println("Random Array: ");

            Integer[] rndArr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] rndArr2 = Arrays.copyOf(rndArr, rndArr.length);
            Integer[] rndArr3 = Arrays.copyOf(rndArr, rndArr.length);

            SortingHelper.sortTest("InsertionSort", rndArr);
            SortingHelper.sortTest("InsertionSort2", rndArr2);
            SortingHelper.sortTest("SelectionSort", rndArr3);

            System.out.println();

            System.out.println("Ordered Array: ");

            rndArr = ArrayGenerator.generateOrderedArray(n);
            rndArr2 = Arrays.copyOf(rndArr, rndArr.length);
            rndArr3 = Arrays.copyOf(rndArr, rndArr.length);

            SortingHelper.sortTest("InsertionSort", rndArr);
            SortingHelper.sortTest("InsertionSort2", rndArr2);
            SortingHelper.sortTest("SelectionSort", rndArr3);

            System.out.println();

        }
    }
}
