package com.zero.bugs.demo.algorithm.repository;

import java.util.Arrays;

public class SortAlgorithmService {
    /**
     * 冒泡排序
     *
     * @param data
     */
    public String bubbleSort(int[] data) {
        if (data == null || data.length == 0) {
            return Arrays.toString(data);
        }

        int length = data.length;
        int i, j, tmp;
        boolean needSort = true;
        for (i = 0; i < length - 1 && needSort; ++i) {
            needSort = false;
            for (j = 0; j < length - i - 1; ++j) {
                if (data[j] > data[j + 1]) {
                    swap(data, j + 1, j);
                    needSort = true;
                }
            }
        }
        StackTraceElement[] obj = Thread.currentThread().getStackTrace();
        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
    }

    /**
     * 选择排序
     *
     * @param data
     */
    public String selectSort(int[] data) {
        if (data == null || data.length == 0) {
            return Arrays.toString(data);
        }

        int length = data.length;
        int i, j, tmp;
        int min;
        boolean needChange;
        for (i = 0; i < length; ++i) {
            min = i;
            needChange = false;
            for (j = i + 1; j < length; ++j) {
                if (data[j] < data[min]) {
                    min = j;
                    needChange = true;
                }
            }
            if (needChange) {
                swap(data, min, i);
            }
        }
        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
    }

    /**
     * 插入排序
     *
     * @param data
     */
    public String insertSort(int[] data) {
        if (judgeEmpty(data)) {
            return Arrays.toString(data);
        }

        int length = data.length;
        // 希尔排序特殊情况，设置步长为1
        int gap = 1;
        int i, j;
        int currentValue;
        for (i = gap; i < length; i += gap) {
            j = i;
            currentValue = data[i];
            while (j - gap >= 0 && data[j - gap] > currentValue) {
                data[j] = data[j - gap];
                j -= gap;
            }
            data[j] = currentValue;
        }
        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
    }

    /**
     * 希尔排序
     *
     * @param data
     */
    public String shellSort(int[] data) {
        if (judgeEmpty(data)) {
            return Arrays.toString(data);
        }

        int length = data.length;
        int i, j, gap;
        int currentValue;
        for (gap = length / 2; gap > 0; gap = gap / 2) {
            for (i = gap; i < length; ++i) {
                j = i;
                currentValue = data[j];
                while (j - gap >= 0 && data[j - gap] > currentValue) {
                    data[j] = data[j - gap];
                    j -= gap;
                }
                data[j] = currentValue;
            }
        }
        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
    }

    /**
     * 归并排序
     *
     * @param data
     * @param low
     * @param high
     */
    public String mergeSort(int[] data, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边分治
            mergeSort(data, low, mid);

            // 右边分治
            mergeSort(data, mid + 1, high);

            // 合并治理
            merge(data, low, mid, high);
            if (high - low + 1 == data.length) {
                return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
            }
        }
        return Arrays.toString(data);
    }

    private void merge(int[] data, int low, int mid, int high) {
        int[] tmpArr = new int[high - low + 1];
        int startLow = low;
        int startHigh = mid + 1;
        int tmpArrIndex = 0;
        while (startLow <= mid && startHigh <= high) {
            if (data[startLow] < data[startHigh]) {
                tmpArr[tmpArrIndex++] = data[startLow++];
            } else {
                tmpArr[tmpArrIndex++] = data[startHigh++];
            }
        }

        while (startLow <= mid) {
            tmpArr[tmpArrIndex++] = data[startLow++];
        }
        while (startHigh <= high) {
            tmpArr[tmpArrIndex++] = data[startHigh++];
        }
        for (startLow = low, tmpArrIndex = 0; startLow <= high; ++startLow, ++tmpArrIndex) {
            data[startLow] = tmpArr[tmpArrIndex];
        }
    }

    /**
     * 快速排序
     *
     * @param data
     * @param low
     * @param high
     */
    public String quickSort(int[] data, int low, int high) {
        int partitionIndex;
        if (low < high) {
            partitionIndex = partition(data, low, high);
            quickSort(data, low, partitionIndex - 1);
            quickSort(data, partitionIndex + 1, high);
            if (high - low + 1 == data.length) {
                return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
            }
        }
        return Arrays.toString(data);
    }

    private int partition(int[] data, int low, int high) {
        int pivotIndex = low;
        int markIndex = low + 1;
        int i;
        for (i = markIndex; i <= high; ++i) {
            if (data[i] < data[pivotIndex]) {
                swap(data, markIndex, i);
                ++markIndex;
            }
        }
        swap(data, markIndex - 1, pivotIndex);
        return markIndex - 1;
    }

    private void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp;
        tmp = data[j];
        data[j] = data[i];
        data[i] = tmp;
    }

    /**
     * 堆排序
     *
     * @param data
     * @return
     */
    public String heapSort(int[] data) {
        buildMaxHeap(data);
        int i;
        int length = data.length;
        for (i = length - 1; i > 0; --i) {
            swap(data, 0, i);
            heapJudge(data, 0, i);
        }

        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(data);
    }

    private void buildMaxHeap(int[] data) {
        int i = data.length / 2 - 1;
        for (; i >= 0; --i) {
            heapJudge(data, i, data.length);
        }
    }

    private void heapJudge(int[] data, int currentNodeIndex, int length) {
        int leftNodeIndex = 2 * currentNodeIndex + 1;
        int rightNodeIndex = 2 * currentNodeIndex + 2;
        int largestIndex = currentNodeIndex;
        if (leftNodeIndex < length && data[leftNodeIndex] > data[largestIndex]) {
            largestIndex = leftNodeIndex;
        }

        if (rightNodeIndex < length && data[rightNodeIndex] > data[largestIndex]) {
            largestIndex = rightNodeIndex;
        }

        if (largestIndex != currentNodeIndex) {
            swap(data, currentNodeIndex, largestIndex);
            heapJudge(data, largestIndex, length);
        }
    }

    private boolean judgeEmpty(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 最小的k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public String smallestK(int[] arr, int k) {
        if (arr==null||arr.length==0 || k<=0){
            return Arrays.toString(new int[0]);
        }

        if (arr.length<=k){
            return Arrays.toString(arr);
        }

        int[] bigHeap = new int[k];
        System.arraycopy(arr,0,bigHeap,0,k);

        buildMaxHeap(bigHeap);

        int i;
        for(i=k;i<arr.length;++i){
            if(arr[i]<bigHeap[0]){
                bigHeap[0]=arr[i];
                heapJudge(bigHeap,0,k);
            }
        }

        return Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + Arrays.toString(bigHeap);
    }
}
