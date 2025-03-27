import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main{
    public static void main(String[] args){
        List<Integer> list = List.of(80,4,5,1,0,34,12,56);
        //int [] arr = new int[]{2,4,6,8,1,0,34,12,56};
        //System.out.println("Before Sorting" + Arrays.toString(arr));
        //SortClass.mergeSort(arr,0,arr.length-1);
        //System.out.println("After Sorting" + Arrays.toString(arr));
        ExecutorService es = Executors.newFixedThreadPool(1);
        SortClass sc = new SortClass(list);
        Future<List<Integer>> mergedFuture= es.submit(sc);
        try{
            System.out.println("The merged array is " + mergedFuture.get());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}