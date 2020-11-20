public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
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
}
