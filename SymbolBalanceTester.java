public class SymbolBalanceTester {

    public static void main(String [] args) {
      //Test 1
      SymbolBalance test1 = new SymbolBalance();
      test1.setFile("TestFiles/Test1.java");
      System.out.println("Test1.java: " + test1.checkFile());

       //Test 2
      SymbolBalance test2 = new SymbolBalance();
      test2.setFile("TestFiles/Test2.java");
      System.out.println("Test2.java: " + test2.checkFile());

       //Test 3
      SymbolBalance test3 = new SymbolBalance();
      test3.setFile("TestFiles/Test3.java");
      System.out.println("Test3.java: " + test3.checkFile());

       //Test 4
      SymbolBalance test4 = new SymbolBalance();
      test4.setFile("TestFiles/Test4.java");
      System.out.println("Test4.java: " + test4.checkFile());

       //Test 5
      SymbolBalance test5 = new SymbolBalance();
      test5.setFile("TestFiles/Test5.java");
      System.out.println("Test5.java: " + test5.checkFile());

       //Test 6
      SymbolBalance test6 = new SymbolBalance();
      test6.setFile("TestFiles/Test6.java");
      System.out.println("Test6.java: " + test6.checkFile());

    }
    
}
