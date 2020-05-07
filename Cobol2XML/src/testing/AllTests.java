package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ AlternationTest.class, EndToEndTests.class, TokenizerTest.class, TokenTest.class, CobolTest.class, 
ConstantValueTest.class, DivisionTest.class, SectionTest.class, NonContiguousDataItemTest.class,
DisplayTest.class, DateWrittenTest.class, ProgramIDTest.class })
public class AllTests
{

}
