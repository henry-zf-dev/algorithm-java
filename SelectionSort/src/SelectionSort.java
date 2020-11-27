public class SelectionSort {

    private SelectionSort() {
    }

    /*
     * 选择排序算法
     * 循环不变量：arr[0, i) 已完成排序，arr[i, n) 还未完成排序
     * 循环体：找出 arr[i, n) 中最小的元素，并和第 i 个元素交换位置，维持循环不变量
     * 使用泛型，需要 extends Comparable，让泛型可比较
     * 复杂度：1+2+3+...+n = 1/2n^2 + 1/2n = n^2
     * */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 选择 arr[i...n) 中最小值的索引
            int minIdx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            // 此时 minIdx 是 arr[i...n) 中最小值的索引

            // 交换 i 和 minIdx 索引对应的元素
            swap(arr, i, minIdx);
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 3, 6, 5};
        SelectionSort.sort(arr);
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();

        Student[] students = {new Student("Alice", 98), new Student("Henry", 100), new Student("Charles", 66)};
        SelectionSort.sort(students);
        for (Student student: students) {
            System.out.print(student + " ");
        }
        System.out.println();

        int[] dataSize = {10000, 100000};
        for (int n: dataSize) {
            Integer[] rndArr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", rndArr);
        }
    }
}
