import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class China implements StringChecker {
  public boolean checkString(String s){
    return !s.equals("China");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilter(){
      China check = new China();
      List<String> input1 = new ArrayList<String>();
      input1.add("China");
      input1.add("China");
      input1.add("Mother");
      input1 = ListExamples.filter(input1, check);
      assertEquals("Mother", input1.get(0));
      input1.add("Haha");
      input1 = ListExamples.filter(input1, check);
      assertEquals("Mother", input1.get(0));
      assertEquals("Haha", input1.get(1));
  }
}

