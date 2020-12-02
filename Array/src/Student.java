public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // equals 是重写 Object 父类的方法，声明的类型必须是 Object
    @Override
    public boolean equals(Object student) {
        // 将 Object 强制转化为 Student，转化前需要做类型判断
        if (student == null) return false;
        if (this == student) return true;
        if (this.getClass() != student.getClass()) return false;
        Student another = (Student)student;
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }

    // 重写 Comparable 接口的 compareTo 方法，让类可比较
    @Override
    public int compareTo(Student another) {
        return this.score - another.score;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)", name, score);
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        arr.addLast(new Student("Alice", 100));
        arr.addLast(new Student("Bob", 66));
        arr.addLast(new Student("Charlie", 88));
        System.out.println(arr);
    }
}

