package edu.virginia.cs.sde.efficiency;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List.of(10, 100, 1000, 10000, 100000, 1000000, 10000000).forEach(Main::runBenchmark);
    }

    private static void runBenchmark(int size) {
        System.out.println("\nBenchmark for size = " + size);
        Path coordinateArray = new CoordinateArrayPath(size);
        Path pointArrayList = new PointListPath(size);
        double[] xCoordinates = randomArray(size);
        double[] yCoordinates = randomArray(size);
        printBenchMarkTimes(size, coordinateArray, xCoordinates, yCoordinates);
        System.out.println("\t ---");
        printBenchMarkTimes(size, pointArrayList, xCoordinates, yCoordinates);
    }

    private static void printBenchMarkTimes(int size, Path pointList, double[] xCoordinates, double[] yCoordinates) {
        long addTime = calculateAddTime(size, pointList, xCoordinates, yCoordinates);
//        System.out.printf("\t%-20s      add = %12dms\n", pointList.getClass().getName(), addTime);
        long distanceTime = calculateDistanceTime(pointList);
        System.out.printf("\t%-20s distance = %12dms\n", pointList.getClass().getName(), distanceTime);
//        System.out.printf("\t%-20s    total = %12dms\n", pointList.getClass().getName(), addTime + distanceTime);
    }

    private static long calculateAddTime(int size, Path coordinateArray, double[] xCoordinates, double[] yCoordinates) {
        long startTimeMilliseconds = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            coordinateArray.add(xCoordinates[i], yCoordinates[i]);
        }
        long endTimeMilliseconds = System.currentTimeMillis();
        return endTimeMilliseconds - startTimeMilliseconds;
    }

    private static long calculateDistanceTime(Path pointList) {
        long startTimeNanoseconds = System.currentTimeMillis();
        pointList.totalDistance();
        long endTimeNanoseconds = System.currentTimeMillis();
        return endTimeNanoseconds - startTimeNanoseconds;
    }

    private static double[] randomArray(int size) {
        double[] array = new double[size];
        for(int i = 0; i < size; i++) {
            array[i] = Math.random();
        }
        return array;
    }
}
