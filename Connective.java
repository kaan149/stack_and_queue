import java.io.*;
import java.util.*;

public class Connective {
    public void read(String path) {
        FileWriter myWriter = null;
        FileWriter myWriter2 = null;
        try {
            myWriter = new FileWriter("queueOut.txt",true);
            myWriter2 = new FileWriter("stackOut.txt",true);

            File myObj = new File("stack.txt");
            File myObj2 = new File("queue.txt");
            File commands = new File(path);

            Scanner myReader = new Scanner(myObj);
            Scanner myReader2 = new Scanner(myObj2);
            Scanner myReader3 = new Scanner(commands);

            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new Queue<>();

            while (myReader.hasNextInt()) {
                stack.push(myReader.nextInt());
            }

            while (myReader2.hasNextInt()) {
                queue.enqueue(myReader2.nextInt());
            }

            myReader.close();
            myReader2.close();

            while(myReader3.hasNextLine()) {
                ArrayList<String> line = new ArrayList<>(Arrays.asList(myReader3.nextLine().split(" ")));
                if (line.get(0).equals("Q")) {

                    switch (line.get(1)) {
                        case "removeGreater":
                            int limit = Integer.parseInt(line.get(2));
                            settingQ(queue, limit);
                            myWriter.write("After removeGreater " + limit + ":\n");
                            writingQ(queue, myWriter);
                            break;
                        case "addOrRemove": {
                            int value = Integer.parseInt(line.get(2));
                            addOrRemoveForQ(queue, value);
                            myWriter.write("After addOrRemove " + value + ":\n");
                            writingQ(queue, myWriter);
                            break;
                        }
                        case "reverse": {
                            int value = Integer.parseInt(line.get(2));
                            queue = ReverseForQ(queue, value);
                            myWriter.write("After reverse " + value + ":\n");
                            writingQ(queue, myWriter);
                            break;
                        }
                        case "sortElements":
                            queue.sortQ();
                            myWriter.write("After sortElements:\n");
                            writingQ(queue, myWriter);
                            break;
                        case "distinctElements": {
                            Queue<Integer> tempQueue = new Queue<>();
                            Queue<Integer> savingQ = new Queue<>();
                            savingQ.addAllQ(queue);
                            while (!savingQ.isEmpty()) {
                                if (tempQueue.containsQ(savingQ.element())) {
                                    savingQ.poll();
                                } else {
                                    tempQueue.enqueue(savingQ.element());
                                    savingQ.poll();
                                }
                            }
                            int totalDistinct = tempQueue.size();
                            myWriter.write("After distinctElements:\n");
                            myWriter.write("Total distinct element=" + totalDistinct + "\n");
                            break;
                        }
                        case "calculateDistance": {
                            Queue<Integer> tempQ = new Queue<>();
                            tempQ.addAllQ(queue);
                            int x = DistanceOfQ(tempQ);
                            myWriter.write("After calculateDistance:\n");
                            myWriter.write("Total distance=" + x + "\n");
                            break;
                        }
                    }
                }
                else if(line.get(0).equals("S")) {

                    switch (line.get(1)) {
                        case "removeGreater":
                            int limit = Integer.parseInt(line.get(2));
                            settingS(stack, limit);
                            myWriter2.write("After removeGreater " + limit + ":\n");
                            writingS(stack, myWriter2);
                            break;
                        case "addOrRemove": {
                            int value = Integer.parseInt(line.get(2));
                            addOrRemoveForS(stack, value);
                            myWriter2.write("After addOrRemove " + value + ":\n");
                            writingS(stack, myWriter2);
                            break;
                        }
                        case "reverse": {
                            int value = Integer.parseInt(line.get(2));
                            stack = ReverseForS(stack, value);
                            myWriter2.write("After reverse " + value + ":\n");
                            writingS(stack, myWriter2);
                            break;
                        }
                        case "sortElements":
                            stack.sortS();
                            myWriter2.write("After sortElements:\n");
                            writingS(stack, myWriter2);
                            break;
                        case "distinctElements": {
                            Queue<Integer> tempQueue = new Queue<>();
                            Stack<Integer> savingS = new Stack<>();
                            savingS.addAllS(stack);
                            while (!savingS.isEmpty()) {
                                if (tempQueue.containsQ(savingS.peek())) {
                                    savingS.pop();
                                } else {
                                    tempQueue.enqueue(savingS.peek());
                                    savingS.pop();
                                }
                            }
                            int totalDistinct = tempQueue.size();
                            myWriter2.write("After distinctElements:\n");
                            myWriter2.write("Total distinct element=" + totalDistinct + "\n");
                            break;
                        }
                        case "calculateDistance": {
                            Stack<Integer> tempS = new Stack<>();
                            tempS.addAllS(stack);
                            int x = DistanceOfS(tempS);
                            myWriter2.write("After calculateDistance:\n");
                            myWriter2.write("Total distance=" + x + "\n");
                            break;
                        }
                    }
                }
            }
            updateFiles(stack,queue,"stack.txt","queue.txt");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (myWriter != null) {
                    myWriter.close();
                }
                if (myWriter2 != null) {
                    myWriter2.close();
                }
            } catch (IOException e) {
                System.out.println("File did not close");
            }
        }
    }

    //Setting queue according to command that remove greater than given value
    public void settingQ(Queue<Integer> q, Integer limit){
        Queue<Integer> tempQueue = new Queue<>();
        while(!q.isEmpty()){
            if(q.element() <= limit) {
                tempQueue.enqueue(q.element());
                q.poll();
            }
            else if(q.element() > limit) {
                q.poll();
            }
        }
        while(!tempQueue.isEmpty()) {
            q.enqueue(tempQueue.element());
            tempQueue.poll();
        }
    }

    //Setting stack according to command that remove greater than given value
    public void settingS(Stack<Integer> s, Integer limit){
        Stack<Integer> tempStack = new Stack<>();
        while(!s.isEmpty()){
            if(s.peek() <= limit) {
                tempStack.push(s.peek());
                s.pop();
            }
            else if(s.peek() > limit) {
                s.pop();
            }
        }
        while(!tempStack.isEmpty()) {
            s.push(tempStack.peek());
            tempStack.pop();
        }
    }

    //Adding or removing random elements for queue according to given value depends on value's sign ( positive or negative )
    public void addOrRemoveForQ(Queue<Integer> q, int value){
        if (value > 0){
            while (value > 0) {
                Random rand = new Random();
                int n = rand.nextInt(51);
                q.enqueue(n);
                value--;
            }
        }

        else if (value < 0) {
            while (value < 0) {
                q.poll();
                value++;
            }
        }

    }

    //Adding or removing random elements for stack according to given value depends on value's sign ( positive or negative )
    public void addOrRemoveForS(Stack<Integer> s, Integer value){
        if (value > 0){
            while (value > 0) {
                Random rand = new Random();
                int n = rand.nextInt(51);
                s.push(n);
                value--;
            }
        }

        else if (value < 0) {
            while (value < 0) {
                s.pop();
                value++;
            }
        }

    }

    // For reversing queue
    public Queue<Integer> ReverseForQ(Queue<Integer> q, int value){
        Stack<Integer> tempS = new Stack<>();
        Queue<Integer> mainQ = new Queue<>();
        while (value > 0) {
            tempS.push(q.poll());
            value--;
        }
        while (!tempS.isEmpty()) {
            mainQ.enqueue(tempS.pop());
        }
        mainQ.addAllQ(q);
        return mainQ;
    }

    // For reversing stack
    public Stack<Integer> ReverseForS(Stack<Integer> s, int value){
        Stack<Integer> tempS = new Stack<>();
        Stack<Integer> mainS = new Stack<>();
        Queue<Integer> tempQ = new Queue<>();
        while (!s.isEmpty()) {
            tempS.push(s.pop());
        }
        while (value > 0) {
            tempQ.enqueue(tempS.pop());
            value--;
        }
        while (!tempQ.isEmpty()) {
            s.push(tempQ.poll());
        }
        while(!s.isEmpty()) {
            mainS.push(s.pop());
        }
        while (!tempS.isEmpty()) {
            tempQ.enqueue(tempS.pop());
        }
        mainS.addAllS(tempQ);
        return mainS;
    }

    // It counts the sum of the distances of all elements to other all elements for stack
    public int DistanceOfS(Stack<Integer> s) {
        int d = 0;
        while (!s.isEmpty()) {
            int q;
            Stack<Integer> s2 = new Stack<>();
            s2.addAllS(s);
            while (!s2.isEmpty()) {
                q = Math.abs(s.peek() - s2.pop());
                d += q;
            }
            s.pop();
        }
        return d;
    }

    // It counts the sum of the distances of all elements to other all elements for queue
    public int DistanceOfQ(Queue<Integer> q) {
        int d = 0;
        while (!q.isEmpty()) {
            int i;
            Queue<Integer> q2 = new Queue<>();
            q2.addAllQ(q);
            while (!q2.isEmpty()) {
                i = Math.abs(q.element() - q2.poll());
                d += i;
            }
            q.poll();
        }
        return d;
    }

    // It writes queue to the queueOut.txt
    public void writingQ(Queue<Integer> queue, FileWriter fileWriter) {
        for (Integer integer : queue) {
            try {
                fileWriter.write(integer + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // It writes stack to the stackOut.txt
    public void writingS(Stack<Integer> stack, FileWriter fileWriter) {
        for (Integer integer : stack) {
            try {
                fileWriter.write(integer + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updating stack.txt and queue.txt files
    public void updateFiles(Stack<Integer> s, Queue<Integer> q, String file1, String file2) {
        try {
            FileWriter fw = new FileWriter(file1);
            FileWriter fw2 = new FileWriter(file2);
            Queue<Integer> fibberQ = new Queue<>();
            fibberQ.addAllQ(s);

            for(int i: fibberQ) {
                fw.write(i + " ");
            }
            fw.close();
            for(int i: q) {
                fw2.write(i + " ");
            }
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
