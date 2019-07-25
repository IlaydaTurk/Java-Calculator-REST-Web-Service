package calculation.calculate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CalculatorImpl.class, Class1.class })
public class PowerMockDemoTest {

	/*
	 * @BeforeEach void setUp() throws Exception { CalculatorImpl cal = new
	 * CalculatorImpl(); }
	 */

	@Test
	public void test() throws Exception {
		Class1 mock = PowerMockito.mock(Class1.class);
		PowerMockito.whenNew(Class1.class).withNoArguments().thenReturn(mock);
		PowerMockito.when(mock.getTmp()).thenReturn(0);
		
		CalculatorImpl cal = new CalculatorImpl();
		int result = cal.returnCalculator(5, "-", 4);
		assertEquals("1", String.valueOf(result));
		int result1 = cal.returnCalculator(2, "-", 4);
		assertEquals("-2", String.valueOf(result1));
		int result2 = cal.returnCalculator(0, "!", 4);
		assertEquals("24", String.valueOf(result2));
		// fail("Not yet implemented");
	}

}
