public class InsertionSort {

    private InsertionSort() {}

    /*
     * 插入排序算法
     * 循环不变量：arr[0, i) 已完成排序，arr[i, n) 还未完成排序，和选择排序是一样的
     * 循环体：把 arr[i] 放到 arr[0, i) 中合适的位置，维持循环不变量
     * 复杂度：1+2+3+...+n = 1/2n^2 + 1/2n = n^2
     * */
    public static <E extends Comparable<E>> void sort(E[] arr) {
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

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n: dataSize) {
            Integer[] rndArr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("InsertionSort", rndArr);
        }
    }
}
