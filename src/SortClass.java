import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SortClass implements Callable<List<Integer>> {
    private List<Integer> arr;

    public SortClass(List<Integer> arr) {
        this.arr = arr;
    }

    @Override
    public List<Integer> call() throws Exception {
        // Base case
        if (arr.size() <= 1) {
            return arr;
        }

        int mid = arr.size() / 2;
        List<Integer> left = new ArrayList<>(arr.subList(0, mid));
        List<Integer> right = new ArrayList<>(arr.subList(mid, arr.size()));

        // Sorting left and right sublists in separate threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<Integer>> leftArrayFuture = executorService.submit(new SortClass(left));
        Future<List<Integer>> rightArrayFuture = executorService.submit(new SortClass(right));

        List<Integer> sortedLeftArray = leftArrayFuture.get();
        List<Integer> sortedRightArray = rightArrayFuture.get();

        executorService.shutdown(); // Ensure proper shutdown after sorting

        return merge(sortedLeftArray, sortedRightArray);
    }

    // Merging two sorted lists
    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> mergedArray = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                mergedArray.add(left.get(i));
                i++;
            } else {
                mergedArray.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            mergedArray.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            mergedArray.add(right.get(j));
            j++;
        }

        return mergedArray;
    }
}
