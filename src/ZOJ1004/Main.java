package ZOJ1004;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String origin = input.nextLine();
            String target = input.nextLine();
            System.out.println("[");
            if (origin.length() == target.length()) {
                dfs(new boolean[0], origin.length() * 2, origin, target);
            }
            System.out.println("]");
        }
    }

    private static final boolean[] moves = new boolean[] { false, true };
    private static void dfs(boolean[] moves, int length, String origin, String target) {
        try {
            if (moves.length >= length) {
                WordStack stack = new WordStack(origin, target);
                for (boolean move : moves) {
                    if (move) {
                        stack.pop();
                    } else {
                        stack.push();
                    }
                }
                if (stack.compare()) {
                    System.out.println(stack.moves());
                }
                return;
            }
            for (boolean move : Main.moves) {
                boolean[] next = Arrays.copyOf(moves, moves.length + 1);
                next[next.length - 1] = move;
                dfs(next, length, origin, target);
            }
        } catch (NoSuchElementException ignore) { }
    }
}

class WordStack {
    private final LinkedList<Character> origin = new LinkedList<>();
    private final LinkedList<Character> stack = new LinkedList<>();
    private final LinkedList<Character> result = new LinkedList<>();
    private final String target;

    private final StringBuilder moves = new StringBuilder();

    WordStack(String origin, String target) {
        this.target = target;
        for (char c : origin.toCharArray()) {
            this.origin.addLast(c);
        }
    }

    public void pop() {
        result.addLast(stack.pop());
        moves.append("o").append(" ");
    }

    public void push() {
        stack.push(origin.pop());
        moves.append("i").append(" ");
    }

    public boolean compare() {
        if (!stack.isEmpty() || !origin.isEmpty()) {
            return false;
        }
        StringBuilder result = new StringBuilder();
        while (!this.result.isEmpty()) {
            result.append(this.result.pop());
        }
        return result.toString().equals(target);
    }

    public String moves(){
        return moves.toString();
    }
}
