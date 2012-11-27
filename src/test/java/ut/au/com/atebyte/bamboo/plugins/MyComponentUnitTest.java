package ut.au.com.atebyte.bamboo.plugins;

import org.junit.Test;
import au.com.atebyte.bamboo.plugins.MyPluginComponent;
import au.com.atebyte.bamboo.plugins.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}