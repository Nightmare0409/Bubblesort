// The Bubblesort package contains the classes and methods for sorting arrays.
package Bubblesort;

// Import the InputMismatchException class to handle invalid user input.
// Import the Random class to generate random numbers.
// Import the java.io and java.util packages to perform input/output operations and use the Scanner class.
import java.util.InputMismatchException;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

// The Bubblesort class contains the main method and the createRandomArray method.
public class Bubblesort {

  // The createRandomArray method takes an integer as a parameter and returns an array of random integers of that length
  public static int[] createRandomArray(int arrayLength) {
    // Creates a new array of the given length.
    int[] array = new int[arrayLength];

    // Creating a new random number generator object.
    Random random = new Random();

    // Looping through the array and assigning random values.
    for (int i = 0; i < array.length; i++) {
      // Generate a random integer between 0 and 100 (inclusive) and store it in the array
      array[i] = random.nextInt(101);
    }

    // Returning the created array.
    return array;
  }


  // The writeArrayToFile method takes an array of integers and a filename as parameters and writes the array elements to the file
public static void writeArrayToFile(int[] array, String filename) {
    // Creating a new file object with the given filename.
    File file = new File(filename);
  
    // Creating a new buffered writer object to write to the file.
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // Looping through the array and writing each element to a new line.
      for (int element : array) {
        writer.write(String.valueOf(element));
        writer.newLine();
      }
    } catch (IOException e) {
      // Handling any IO exceptions.
    }
  }
  
  // The bubbleSort method takes an array of integers as a parameter and sorts it in ascending order using the bubble sort algorithm.
  public static void bubbleSort(int[] array) {
    // Getting the length of the array.
    int n = array.length;
  
    // Looping through the array n-1 times.
    for (int i = 0; i < n - 1; i++) {
      // Looping through the array from 0 to n-i-1.
      for (int j = 0; j < n - i - 1; j++) {
        // Comparing the adjacent elements and swaping them if they are out of order.
        if (array[j] > array[j + 1]) {
          // Swaping array[j] and array[j+1]
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
    // The bubble sort algorithm compares each pair of adjacent elements and swaps them if they are in the wrong order.
    // The algorithm repeats this process until no swaps are needed, which means the array is sorted.
  }
  
// The main method is the entry point of the program.
// It takes arrays of strings as a parameter, which represents the command-line arguments.
public static void main(String[] args) {
    // Declaring an array to store the input.
    int[] array;

    // Using a try-with-resources statement to create and close the Scanner object.
    // The Scanner object is used to read the user's keyboard input.
    try (Scanner keyboard = new Scanner(System.in)) {
        // Ask the user for the input mode
        System.out.println("Enter 1 to type in an integer or 2 to give an input file:");
        int mode = keyboard.nextInt();

        // Checking the input.
        // If input is 1, the user will type in an integer.
        // If input is 2, the user will give an input file.
        // If input is neither 1 or 2, the program will terminate.
        if (mode == 1) {
            // Asking the user for the length of the array.
            System.out.println("Enter the length of the array:");
            int length = keyboard.nextInt();

            // Creating a random array of the given length.
            array = createRandomArray(length);
        } else if (mode == 2) {
            // Asking the user for the name of the input file.
            System.out.println("Enter the name of the input file:");
            String filename = keyboard.next();

            // Creating a BufferedReader object to read the file.
            // Using a nested try-catch block to handle any IO exceptions.
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                // Converting the BufferedReader to an IntStream and then to an array.
                // The IntStream is a stream of primitive int values.
                // The toArray method returns an array containing the elements of the stream.
                array = reader.lines().mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                // Handling the exception.
                System.out.println("IO error: " + e.getMessage());
                return;
            }
        } else {
            // Invalid input mode message.
            System.out.println("Invalid input mode: " + mode);
            return;
        }
    } catch (InputMismatchException e) {
        // Handling the exception.
        System.out.println("Invalid input: " + e.getMessage());
        return;
    }

    // Writing the array to a file named "output.txt".
    writeArrayToFile(array, "output.txt");

    // Sorting the array using bubble sort.
    bubbleSort(array);

    // Writing the sorted array to a file named "sorted.txt".
    writeArrayToFile(array, "sorted.txt");
}
}