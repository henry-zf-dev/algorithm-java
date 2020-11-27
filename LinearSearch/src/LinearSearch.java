public class LinearSearch {

    // 声明构造方法为 private，阻止类的外部 new LinearSearch
    private LinearSearch(){}

    // 循环不变量：data[0...i-1] 中没有找到目标
    // 循环体：维持循环不变量
    // "证明"算法的正确性
    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            // 类对象的相等判断需要用 equals
            if (data[i].equals(target)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // java 泛型不能接收基本数据类型，而需要用基本数据类型对应的包装类
        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        int res = LinearSearch.search(data, 16);
        System.out.println(res);

        int res2 = LinearSearch.search(data, 666);
        System.out.println(res2);

        Student[] students = {new Student("'Alice'"), new Student("Henry"), new Student("Charles")};
        Student Henry = new Student("henry");
        int res3 = LinearSearch.search(students, Henry);
        System.out.println(res3);

        // 测试时间性能
        int[] dataSize = {1000000, 10000000};
        for (int n: dataSize) {
            Integer[] data2 = ArrayGenerator.generateOrderedArray(n);

            long startTime = System.nanoTime();
            // 通过重复执行，减小误差
            for (int k = 0; k < 100; k++) {
                LinearSearch.search(data2, n);
            }
            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("n = " + n + ", 100 runs : " + time + " s");
        }
    }
}
