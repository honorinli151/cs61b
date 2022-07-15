public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (int i=0; i<word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }

        return wordDeque;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> dq = wordToDeque(word);
        int front = 0, back = dq.size()-1;
        while (front < back) {
            if (dq.get(front) == dq.get(back)) {
                front++;
                back--;
            }
            else{
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq = wordToDeque(word);
        int front = 0, back = dq.size()-1;
        while (front < back) {
            if (cc.equalChars(dq.get(front), dq.get(back))) {
                front++;
                back--;
            }
            else{
                return false;
            }
        }

        return true;
    }
}
