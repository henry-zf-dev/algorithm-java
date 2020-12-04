import java.util.Stack;

public class ValidParentheses {

    // 检验括号有效性
    public boolean isValid(String s) {
        // 使用自己实现的栈来实现算法
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.peek();
                if (c == ')' && topChar != '(') return false;
                if (c == ']' && topChar != '[') return false;
                if (c == '}' && topChar != '{') return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new ValidParentheses()).isValid("()[]{}"));
        System.out.println((new ValidParentheses()).isValid("([)"));
    }
}
