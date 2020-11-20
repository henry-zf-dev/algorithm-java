public class LinearSearch {

    // 声明构造方法为 private，阻止类的外部 new LinearSearch
    private LinearSearch(){}

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
    }
}
