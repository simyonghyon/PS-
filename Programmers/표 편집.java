class Solution {
    //    private int[] dx = {1, 0, -1, 0};
     //   private int[] dy = {0, 1, 0, -1};
    
        public String solution(int n, int k, String[] cmd) {
            String answer = "";
    
            Node root = new Node(-1, null);
            Node prev = root;
    
            Node[] nodes = new Node[n];
    
            for(int i = 0; i < n; i++){
                Node node = new Node(i, prev);
                nodes[i] = node;
                if(prev != null){
                    prev.next = node;
                }
                prev = node;
            }
            prev.next = new Node(n, prev);
            Node cursor = nodes[k];
    
            Stack<Node> deletedNodes = new Stack<>();
            for(int i = 0; i < cmd.length; i++){
                String s = cmd[i];
    
                if(s.charAt(0) == 'U' || s.charAt(0) == 'D'){
                    String[] ss = s.split(" ");
    
                    if(ss[0].equals("U")){
                        int x = Integer.valueOf(ss[1]);
                        while(x > 0){
                            cursor = cursor.prev;
                            x--;
                        }
    
                    } else if(ss[0].equals("D")){
                        int x = Integer.valueOf(ss[1]);
                        while(x > 0){
                            cursor = cursor.next;
                            x--;
                        }
                    }
    
                } else if(s.charAt(0) == 'C'){
                    deletedNodes.add(cursor);
                    cursor.prev.next = cursor.next;
                    cursor.next.prev = cursor.prev;
    
                    cursor.isDeleted = true;
                    if(cursor.next.index != n)
                        cursor = cursor.next;
                    else
                        cursor = cursor.prev;
    
                } else if(s.charAt(0) == 'Z'){
                    Node node = deletedNodes.pop();
                    node.isDeleted = false;
                    prev = node.prev;
                    while(prev.isDeleted){
                        prev = prev.prev;
                    }
                    node.prev = prev;
                    node.prev.next = node;
                    Node next = node.next;
                    while(next.isDeleted){
                        next = next.next;
                    }
                    node.next = next;
                    node.next.prev = node;
                }
            }
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < n; i++){
                if(nodes[i].isDeleted) sb.append("X");
                else sb.append("O");
            }
            return sb.toString();
        }
    
    
        class Node{
            private Node prev = null;
            private Node next = null;
            private int index;
            boolean isDeleted = false;
    
            public Node(int index, Node prev) {
                this.index = index;
                this.prev = prev;
            }
    
            public Node getPrev() {
                return prev;
            }
    
            public void setPrev(Node prev) {
                this.prev = prev;
            }
    
            public Node getNext() {
                return next;
            }
    
            public void setNext(Node next) {
                this.next = next;
            }
    
            public int getIndex() {
                return index;
            }
    
            public void setIndex(int index) {
                this.index = index;
            }
        }
    }