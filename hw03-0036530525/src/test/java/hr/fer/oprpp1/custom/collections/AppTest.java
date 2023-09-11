package hr.fer.oprpp1.custom.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
    @Test
    public void shouldAnswerWithTrue()
    {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(Integer.valueOf(1), "Ana");
        dic.put(Integer.valueOf(2), "Ivan");
        dic.put(Integer.valueOf(3), "Pero");
        
        assertEquals(dic.size(), 3);
        
    }
}
