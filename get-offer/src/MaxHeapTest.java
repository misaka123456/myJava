import model.MyMaxHeap;

public class MaxHeapTest {


    public static void main(String[] args) {
        MyMaxHeap heap = new MyMaxHeap();
        for (int i = 0; i < 20; i = i + 2) {
            heap.insert(i);
        }
        System.out.println(heap);
        // [18, 16, 10, 12, 14, 2, 8, 0, 6, 4]
        for (int i = 1; i < 20; i = i + 2) {
            heap.insert(i);
        }
        System.out.println(heap);
        // [19, 18, 10, 16, 17, 5, 9, 12, 15, 14, 1, 2, 3, 7, 8, 0, 11, 6, 13, 4]
        System.out.println(heap.popMax()); // 19
        System.out.println(heap);
        // [18, 17, 10, 16, 14, 5, 9, 12, 15, 4, 1, 2, 3, 7, 8, 0, 11, 6, 13]
        System.out.println(heap.replace(5)); // 18
        System.out.println(heap);
        // [17, 16, 10, 15, 14, 5, 9, 12, 13, 4, 1, 2, 3, 7, 8, 0, 11, 6, 5]
        MyMaxHeap heap1 = new MyMaxHeap(new int[] {41, 22, 30, 13, 15, 28, 19, 17, 18});
        System.out.println(heap1);
//        System.out.println(heap1.deep());
        // [41, 22, 30, 18, 15, 28, 19, 13, 17]

        MyMaxHeap myMaxHeap = new MyMaxHeap(new int[]{3373, 3323, 33473, 272223, 334273,
                334273, 333, 334273, 3373, 334273, 334273, 334273, 334273,
                334273, 334273, 334273, 33273, 3373, 373, });
        System.out.println(myMaxHeap);
        myMaxHeap.print();


    }
}
